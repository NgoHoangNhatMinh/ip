
# Lux User Guide

Lux is your personal assistant chatbot for managing tasks from the command line. It supports both Maven and Gradle builds, and is organized using Java packages for better maintainability.

## Features

- Add todo, deadline, and event tasks
- List all tasks
- Mark/unmark tasks as done
- Delete tasks
- Save and load tasks automatically
- Robust command parsing (supports `/by`, `/from`, `/to` keywords)
- Date and time parsing for deadlines and events
- Persistent storage in the `data/` folder
- Modular codebase with packages: `lux.commands`, `lux.data`, `lux.parser`, `lux.storage`, `lux.ui`, `lux.exception`

## Usage

### Compiling, Running, and Testing

#### Using Make (Maven or Gradle)

To compile the project:

```
make compile
```

To run the chatbot:

```
make run
```

To run the tests:

```
make test
```

#### Using Gradle Directly

To build:

```
./gradlew build
```

To run:

```
./gradlew run
```

To run tests:

```
./gradlew test
```

## Commands

### Adding a Todo

Add a simple todo task:

```
todo read book
```

### Adding a Deadline

Add a task with a deadline. Date/time format: `yyyy-MM-dd HHmm` (e.g., `2025-08-30 2359`)

```
deadline submit report /by 2025-08-30 2359
```

### Adding an Event

Add an event with a start and end time. Date/time format: `yyyy-MM-dd HHmm`

```
event meeting /from 2025-08-30 1000 /to 2025-08-30 1100
```

### Listing Tasks

Show all current tasks:

```
list
```

### Marking/Unmarking Tasks

Mark a task as done:

```
mark 2
```

Unmark a task:

```
unmark 2
```

### Deleting a Task

Delete a task by its number:

```
delete 2
```

### Exiting

Exit the chatbot:

```
bye
```

## Notes

- All data is saved in the `data/` folder and automatically loaded on startup.
- The chatbot supports robust error handling and input validation.
- For custom builds or advanced usage, see the `build.gradle` and `makefile` in the project root.
