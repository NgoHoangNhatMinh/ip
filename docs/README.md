# Lux User Guide

Lux is your personal assistant chatbot for managing tasks from the command line.

## Features

- Add todo, deadline, and event tasks
- List all tasks
- Mark/unmark tasks as done
- Delete tasks
- Save and load tasks automatically

## Usage

### Compiling, Running, and Testing

You can use the following commands:

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

### Adding a Todo

Add a simple todo task.

```
todo read book
```

### Adding a Deadline

Add a task with a deadline.

```
deadline submit report /by tomorrow
```

### Adding an Event

Add an event with a start and end time.

```
event meeting /from 10am /to 11am
```

### Listing Tasks

Show all current tasks.

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
