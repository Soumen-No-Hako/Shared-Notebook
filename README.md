# Shared Notebook

A collaborative platform for sharing and editing notes in real time. Users can create instant rooms, invite others to join via unique room codes or URLs, and work together on shared notes with live updates. All participation is permission-controlled by room owners.

## Features

- **Shared Notes**: Anyone with permission can read/write notes in a shared document.
- **Instant Room Creation**: Easily create a room with a unique 5-letter alphanumeric code (e.g., `/room/85421`). Join rooms via code or direct URL.
- **Live Collaboration**: Notes are updated live; changes are visible to all room members instantly.
- **User Profiles**: Registration with profile details (name, email, userId, password).
- **Room Management**: Owners can assign roles (read, write, create) and manage members.
- **Recent Activity**: View who shared notes and invited members.
- **Export & Commit**: Export notes and commit changes after group agreement.

## Project Structure

### Database

- **MongoDB** is used for data storage.
- Tables:
  - `Profile`: Stores user information.
  - `Notes`: Stores note details, owners, members, and note data.
  - `Room`: Stores room code, owner, notes created during the session, members, and roles.

### Backend

- Built with **Java**, **Spring Boot**, and **Maven**.
- REST APIs:
  - `/app/home`: User homepage showing rooms, notes, and recent activity.
  - `/app/room/create`: Create a new room (unique code).
  - `/app/room/{Room_code}`: Room details and notes list.
  - `/app/note/create`: Create a new note with a unique ID.
  - Live update system for note data.

### Frontend

- Not yet implemented

## Setup

### Prerequisites

- [Java JDK 17+](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/)
- [MongoDB](https://www.mongodb.com/try/download/community)

### Backend Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Soumen-No-Hako/Shared-Notebook.git
   cd Shared-Notebook
   ```

2. **Configure MongoDB:**
   - Start MongoDB locally or provide a URI in your `application.properties`.

3. **Build and run backend:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

### Configuration

- Update database connection settings in `src/main/resources/application.properties`.
- visit http://localhost:8080/v3/api-docs for full documentation after running

## Usage

- Register a profile.
- Create or join a room via code.
- Create and edit notes collaboratively.
- Export notes or commit changes as needed.

## Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

## License

This project is licensed under the [MIT License](LICENSE).

---

> For more details, see [`Shared-Notepad-project.txt`](Shared-Notepad-project.txt) and visit [Soumen-No-Hako/Shared-Notebook on GitHub](https://github.com/Soumen-No-Hako/Shared-Notebook).
