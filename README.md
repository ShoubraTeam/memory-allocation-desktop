# Memory Allocation Simulation

## Overview

A simulation of a basic memory allocation and scheduling mechanism using Java.

- University: Benha University
- College: Shoubra Faculty of Engineering
- Department: Computer Engineering
- Course: Data Structures

## Features

- Simulates memory allocation for processes with random memory sizes and execution times.
- Manages waiting, running, and memory block lists dynamically.
- Implements memory block reclamation and merging.
- Provides real-time updates on process and memory states.

## Workflow

The system works as follows:

- Memory Initialization:

  - The total available memory is 1024 units (addresses 0 to 1023).
  - Memory blocks are divided and merged.

- Initialize Starting Processs:

  - A total of 10 processes are generated.
  - Each process has:
    - A random memory size (max: 512 units).
    - A random timeout (max: 10 seconds).

- Simulation:

  - Each second, a process from the starting list is dispatched.
  - Processes are allocated to either the running or waiting list based on memory availability.
  - Timeout for running processes decreases every second.
  - Completed processes release their memory blocks, which are then merged with adjacent blocks if possible.
  - Waiting processes move to the running list as memory becomes available.

- Termination:
  - The simulation ends when all lists (starting, running, waiting) are empty.

## Getting Started

#### - Java JDK 22

### Cloning this repo

Clone the repo and navigate to it:

- `git clone https://github.com/ShoubraTeam/memory-allocation-desktop.git`
- `cd memory-allocation-desktop`

### Build and Run the Application

- Open the project in your IDE.
- Compile the project.
- Run the application.
