package com.example.sharednotepad.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CurlPrinter {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void printCurl(String method, String url, Object requestBody) {
        try {
            String json = mapper.writeValueAsString(requestBody);
            String curl = String.format("curl -X %s \"%s\" -H \"Content-Type: application/json\" -d '%s'",
                    method.toUpperCase(),
                    url,
                    json);
            System.out.println("[cURL] " + curl);
        } catch (Exception e) {
            System.out.println("[cURL] Failed to generate JSON: " + e.getMessage());
        }
    }

    public static void printCurl(String method, String url) {
        String curl = String.format("curl -X %s \"%s\"", method.toUpperCase(), url);
        System.out.println("[cURL] " + curl);
    }
}