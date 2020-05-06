# CSCI1082final

# Final Project
 

# Proposal : Calendar/Day Planner application, with scripting/input

-Program that provides a day planner for appointments, events, or reserving rooms.  Multi-owner editor functionality may be simulated.

  Major subdivisions of program:
  1) Graphical layer that provides visual feedback and the means for user input .
  2) Central data structure for calendar.
  3) Possible email capability?
  4) Receiving "text" or email input that can be parsed to perform actions.
  4) Simulated outside traffic via some kind of "script".

What the user sees:

Entering the application:
  1) Upon launching, the options to create a new calendar or enter an existing one.
  2) Creating a new city prompts for calendar label, loading brings in existing calendar.
  3) Loads calendar view with button interface.
  4) Status bar that gives some kind of time value and current status.
  5) User can scroll the display pane.

Making changes:
  1) Can select specific month, day, time slots.
  2) User can reserve slot(s), or cancel reservation.
  3) Simulated traffice from outside users can try to concurrently perform similar actions.

Save/Load/Exit
  1) User may opt to exit and save.
  2) If running a script, may save progress and reload later if needed.
  
  # Example UI
  ![](https://github.com/patrick-a-miller/CSCI1082final/blob/master/UImkI.jpg)

Additional step changes:
 1) Arranged class files into packages per their use: data objects used as part of the model, and calendar files that hold data pulled into the application.
 2) calendar files load from formatted text files
 3) calendar file objects also provide a sorted array of the object type associated with their data in the data objects package
 4) Date structure reorganized.
 4) Scrollable calendar pane created.
 5) Navigation by user-typed text field implemented.
 6) Add/remove event buttons implemented.
 7) Calendar stores reserved slots to a file on exit and reloads them upon restart.
