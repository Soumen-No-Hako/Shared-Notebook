<#
.SYNOPSIS
    Reads every .java file under the current directory (recursively)
    and appends its filename and contents into one text file.

.PARAMETER OutputFile
    The name (or path) of the file to create. Defaults to "AllJavaFiles.txt".

.EXAMPLE
    PS> .\Merge-AllJava.ps1
    PS> .\Merge-AllJava.ps1 -OutputFile "JavaSourcesDump.txt"
#>
param(
    [string]$OutputFile = "AllJavaFiles.txt"
)

# Start fresh
if (Test-Path $OutputFile) {
    Remove-Item $OutputFile -Force
}

# Process every .java under this folder
Get-ChildItem -Path . -Recurse -Include *.java |
    Sort-Object FullName |
    ForEach-Object {
        $header = "===== File: $($_.FullName) ====="
        $header | Out-File -FilePath $OutputFile -Encoding UTF8 -Append

        Get-Content -Path $_.FullName |
            Out-File -FilePath $OutputFile -Encoding UTF8 -Append

        # Separator
        "" | Out-File -FilePath $OutputFile -Encoding UTF8 -Append
    }

Write-Host "Merged all .java files into `"$OutputFile`""