# Project Report
> Your project report should document the three phases of your project. There is no specific length
requirement. Include enough information for the instructor to understand what your did, and what
the results were. Include code-snippets as needed, but don’t just fill the document with code.
Your report should be complete. At the same time, please consider:
Brevity is the soul of wit / In der Kürze liegt die Würze / Omit needless words

## Phase 1: Create map data
- During this initial phase of the project, all of the group members collected data as explained in the guidelines


## Phase 2: Test existing algorithms
- Phase 2 was containing the most workload. We decided not to just copy-paste and adapt the existing algorithms from the course repository, but seeked to understand the underlying concept, re-implementing them by our own.
- We've initially split up the development of each algorithm across the group members. With the time being, all the implementations ended up being a group effort, however.
- All of us grasped the concepts of the Depth-First and the Breadth-First pretty fast. To understand the concepts behind the Best-First and the A* algorithm, some more time was required.

### Timing, Results and Util
- The Loader class defines several static methods (static since we're only really exposing functionality) which are used to load the csv files and transform the data into algorithm-specific class representations of nodes (and maybe edges, depending on the algorithm).
- The WatchDog class serves as an utility class, which enables the user to take measurements using the `.startMeasurement()` and `.stopMeasurement()` methods.
  - Measurements are taken using the `System.nanoTime()` method.
  - Measurements can be retrieved in either nanoseconds, milliseconds or seconds.
- The `iNode` serves as a basic contract (interface), which each algorithm-specific `Node` implements.
  - Every module, hence every algorithm, has its own representation of a Node, which only so slightly differs from the representation of another algorithm. This is due to the minimal differences about how much data the algorithm really is requesting to work properly.
  - Having each Node representation implement this `iNode` interface enabled us to create the `Results` class **generically typed**.
- The `Results` class serves as a data wrapper, containing the performance measurements (as a WatchDog object) as well as the shortest path from the respective algorithm.
  - The iNode interface explained above enabled us to use the Node representation of each algorithm, but still make it return a Results object containing the shortest Path (composed of Node objects of the respective algorithm) and the performance timer for each algorithm execution.


## Phase 3: New or adapted algorithm