# CSCI1082final

# Final Project
 

# Proposal 1 : Basic Simulated Town Prototype

-Program that presents a visual representation of a simulated town or city, like a simplified SimCity or other building game.

  Major subdivisions of program:
  1) Graphical layer that provides visual feedback and the means for user input into the simulation.
    a) manages screen region or regions responsible for user input (button bar, sliders, text input?).
    b) manages screen region that provides visual representation of simulation state, with bitmap or similar 2D pixel-filled squares
    c) updates appearance of simulation region as time progresses.
  2) Internal simulation of a simulated region with one or more layers of simulation, which updates with regular time steps.
    a) simulated land divided into tiles, each tile has properties that serve as inputs into rules that govern the next time-step state.
    b) each tile's properties are an element of a "layer" composed of the relevant state of all tiles for a given rule
    c) tile state evaluation can make it update a layer's value for its location, and some may update neighboring tile states
    d) a few overall properties like income, expense, demand or can affect growth or what structures are available.
  3) Set of rules based on tile or structure type, which determine which properties are used/updated or affect outside state
    a) basic residential, commercial, industrial with levels of density/development with differing requirements for updates.
    b) infrastructure like transport and electricity that can serve as requirements for levels of development
    c) civic tiles that might influence neighboring tiles (police:crime, parks:land value)
    d) rules about proximity to desired tiles that can check other tiles for required type or distance
  4) Infrastructure layer for the program, for managing storage and methods used by the other layers for handling I/O 
    a) save/load a specific simulation
    b) bring in file data that can be used to provide data like the appearance of tiles in various states
    c) contant like the visual elements loaded by the UI

What the user sees:
Entering the simulation:
  1) Upon launching, the options to create a new city or load a file.
  2) Creating a new city prompts for a city name, and loading a file will use what is in the file.
  3) Loads toolbar with buttons for setting down buildings, zones, or tools like demolish/remove.
  4) Status bar that gives some kind of time value (in-game clock/calendar) as well as budget.
  5) User can scroll the display pane or click to center on a given point.
Making changes:
  1) Placing buildings/zones can be done if budget allows.
  2) Individual zones check for demand, connection to transport, and power.  Use probabilities and past history to determine if they
     should change in terms of development level.  Can increase in density and development or drop based on current conditions.
  3) User can place non-zone buildings like transport (roads) and civic buildings (police, parks) to improve conditions.
  4) At each time step, civic buildings can incur expense, while zone buildings may provide income.
Save/Load/Exit
  1) User may opt to save at any time, which stores current simulation state.
  2) User may opt to load a saved simulation, replacing the current one entirely.
  3) User may exit, either saving or discarding current simulation.
  
  
