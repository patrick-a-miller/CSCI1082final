# CSCI1082final

# Final Project
 

# Proposal 1 : Basic Simulated Town Prototype

-Program that presents a visual representation of a simulated town or city, like a simplified SimCity or other building game.

  Major subdivisions of program:
  1) Graphical layer that provides visual feedback and the means for user input into the simulation.
      - manages screen region or regions responsible for user input (button bar, sliders, text input?).
        - residential, commercial, industrial zone buttons
        - civic buildings - ex. police, parks
        - infrastructure - roads, power
        - status bar with time and other ongoing information such as population, income, demand ratios.
        - miscellaneous options? speed?
     - manages screen region that provides visual representation of simulation state, with bitmap or similar 2D pixel-filled squares
       - viewport that may track what sub-section of the region is visible at a given time
       - 2D representation
     - updates appearance of simulation region as time progresses, queries potentially visible tiles for changes.
     - tracks area of screen user is interacting with (toolbar or display pane), queries whether affected area is valid for some actions.
  2) Internal simulation loop of a region with one or more layers of simulation, which updates with regular time steps.
     - simulated land divided into tiles, each tile has properties that serve as inputs into rules that govern the next time-step state.
       - rules or rule sets correspond to the "type" or activity that has been associated with a tile's location
       - setting down a structure or zone indicates what rules the tiles beneath use, which dictate appearance iand what actions the area can take.
     - each tile's properties might be part of a "layer" composed of the relevant state of all tiles for a given rule or sub-system
       - ex. a tile can have a land value, which different development types are influenced by to different degrees
       - some types can in turn create an influence on their internal value or what they might broadcast around them.
       - the total set of such local tile values make up a "layer" of the simulation, which rule types can interact with to varying degrees.
       - crime or polution can be other layers, which some structure or building types are affected by, ignored by, or can affect positively or negatively
    - tile state evaluation can make it update a layer's value for its location, and some may update neighboring tile states
    - a few overall properties like income, expense, demand or can affect growth or what structures are available.
  3) Sets of rules or elements of the simulation that tiles or structures can be associated with.  
     - control how a tile interacts with the various layers, and determine which are considered relevant
       - ex. factory affected less by land value versus houses
     - basic residential, commercial, industrial with levels of density/development with differing requirements for updates.
     - infrastructure like transport and electricity that can serve as requirements for levels of development
     - civic tiles that might influence neighboring tiles (police:crime, parks:land value)
    - some rules are shared by multiple sets, such as proximity to desired tiles or structures
     - certain behaviors that are not purely local or have global component, such as determining if road links to needed destinations (path-finding) or if there is enough electrical power for all structures on the grid.
  4) Infrastructure layer for the program, for managing storage and methods used by the other layers for handling I/O 
     - save/load a specific simulation
     - bring in file data that can be used to provide data like the appearance of tiles in various states
     - content like the visual elements loaded by the UI?

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
  
  
