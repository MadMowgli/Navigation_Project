# Project Report
> Your project report should document the three phases of your project. There is no specific length
requirement. Include enough information for the instructor to understand what your did, and what
the results were. Include code-snippets as needed, but don’t just fill the document with code.
Your report should be complete. At the same time, please consider:
Brevity is the soul of wit / In der Kürze liegt die Würze / Omit needless words

## Phase 1: Create map data
- During this initial phase of the project, all of the group members collected data as explained in the guidelines.
- However, due to persistent issues with the consolidated data, we opted to utilize a smaller dataset, encompassing the cities or places: Lostorf, Basel, Luzern, Baden, Remigen, Turgi, Aarau, and Unterentfelden (approximately 186 nodes from 8 students).


## Phase 2: Test existing algorithms
- Phase 2 was containing the most workload. We decided not to just copy-paste and adapt the existing algorithms from the course repository, but seeked to understand the underlying concept, re-implementing them by our own.
- We've initially split up the development of each algorithm across the group members. With the time being, all the implementations ended up being a group effort, however.
- All of us grasped the concepts of the Depth-First and the Breadth-First pretty fast. To understand the concepts behind the Best-First and the A* algorithm, some more time was required.

### Timing, Results and Util
- The `Loader` class defines several static methods (static since we're only really exposing functionality) which are used to load the csv files and transform the data into algorithm-specific class representations of nodes (and maybe edges, depending on the algorithm).
- The `WatchDog` class serves as an utility class, which enables the user to take measurements using the `.startMeasurement()` and `.stopMeasurement()` methods.
  - Measurements are taken using the `System.nanoTime()` method.
  - Measurements can be retrieved in either nanoseconds, milliseconds or seconds.
- The `iNode` serves as a basic contract (interface), which each algorithm-specific `Node` implements.
  - Every module, hence every algorithm, has its own representation of a Node, which only so slightly differs from the representation of another algorithm. This is due to the minimal differences about how much data the algorithm really is requesting to work properly.
  - Having each Node representation implement this `iNode` interface enabled us to create the `Results` class **generically typed**.
- The `Results` class serves as a data wrapper, containing the performance measurements (as a WatchDog object) as well as the shortest path from the respective algorithm.
  - The iNode interface explained above enabled us to use the Node representation of each algorithm, but still make it return a Results object containing the shortest Path (composed of Node objects of the respective algorithm) and the performance timer for each algorithm execution.

### Questions

1) Does the search work?
2) How long does it take?
3) How good is the result?

4) How do the algorithms perform on graphs of varying sizes (small, medium, large)?
5) Measure the memory consumption of each algorithm. Does it scale with the size of the graph?
6) How well do the algorithms handle invalid input (e.g., trying to find a path in a non-existent node)?

#### Results Depth First

- Depth First Search (DFS): Traverses a graph by starting at an initial node and explores as far along each branch as possible before backtracking.

    **1) Does the search work?**
    - | Success Criteria                  | ok/failed |
      |-----------------------------------|-----------|
      | Successfully reaches goal node     |           |

    **2) How good is the result?**
  -   | Test Case Description | Input Data                                        | Expected Output        | Actual Output |
      |-----------------------|--------------------------------------------------|-----------------------|---------------|
      | Simple Graph          | Graph: [Nodes: A, B, C, D; Edges: AB, BC, CD]      | Reachable node D      |               |
      | No circular Loop      | Graph: [Nodes: A, B, C, D; Edges: AB, BC, CD, DA]  | All nodes in loop      |               |
    
  **3) How long does it take?**

  **4) How do the algorithms perform on graphs of varying sizes (small, medium, large)?**

  **5) Measure the memory consumption of each algorithm. Does it scale with the size of the graph?**

  **6) How well do the algorithms handle invalid input (e.g., trying to find a path in a non-existent node)?**


#### Results Breadth First

- Breadth First Search (BFS): Explores a graph by visiting all neighbors of a node before moving on to the next level, effectively moving in a "wave" or breadth-first manner.

    **1) Does the search work?**
  - | Success Criteria                | ok/failed |
    |---------------------------------|-----------|
    | Finds shortest path between nodes|           |

  **2) How good is the result?**
  - | Test Case Description                         | Input Data                                        | Expected Output             | Actual Output |
    |-----------------------------------------------|--------------------------------------------------|-----------------------------|---------------|
    | Simple Graph                                  | Graph: [Nodes: A, B, C, D; Edges: AB, BC, CD]      | Shortest path from A to D   |               | |

  **3) How long does it take?**
  
  **4) How do the algorithms perform on graphs of varying sizes (small, medium, large)?**
  
  **5) Measure the memory consumption of each algorithm. Does it scale with the size of the graph?**

  **6) How well do the algorithms handle invalid input (e.g., trying to find a path in a non-existent node)?**


#### Results Best First

- Best First Search: Prioritizes nodes based on a heuristic function, typically the estimated distance to the goal, to guide the search towards the most promising areas first.

  **1) Does the search work?**
  - | Success Criteria                                           | ok/failed |
    |------------------------------------------------------------|-----------|
    | Reaches the goal node quickly based on heuristic evaluation |           |

    **2) How good is the result?**
  - | Test Case Description                       | Input Data                                           | Expected Output | Actual Output |
    |---------------------------------------------|-----------------------------------------------------|----------------|---------------|
    | Weighted graph with a heuristic function    | Graph: [Nodes: A, B, C, D; Edges: AB, BC, CD]; Heuristic: H(A) = 3, H(B) = 2, H(C) = 1, H(D) = 0 | Quickly reaches goal node |               | |

  **3) How long does it take?**

  **4) How do the algorithms perform on graphs of varying sizes (small, medium, large)?**

  **5) Measure the memory consumption of each algorithm. Does it scale with the size of the graph?**

  **6) How well do the algorithms handle invalid input (e.g., trying to find a path in a non-existent node)?**


#### Results A*

- A* Algorithm: Utilizes a combination of the cost to reach a node (g-value) and an estimate of the cost to reach the goal (h-value) to determine the most efficient path in weighted graphs or grids.
 
  **1) Does the search work?**
  - | Success Criteria                            | ok/failed |
    |---------------------------------------------|-----------|
    | Finds the shortest path between two nodes   |           |

  **2) How good is the result?**

  - | Test Case Description                      | Input Data                                           | Expected Output          | Actual Output |
    |--------------------------------------------|-----------------------------------------------------|-------------------------|---------------|
    | Simple Weighted Graph                       | Graph: [Nodes: A, B, C, D; Edges: AB(3), BC(2), CD(1)] | Shortest path from A to D |               |

  **3) How long does it take?**
  
  **4) How do the algorithms perform on graphs of varying sizes (small, medium, large)?**
  
  **5) Measure the memory consumption of each algorithm. Does it scale with the size of the graph?**
  
  **6) How well do the algorithms handle invalid input (e.g., trying to find a path in a non-existent node)?**


## Phase 3: New or adapted algorithm

### Decision
- We decided to implement **Dijkstra's algorithm** for the final phase of the project. Similar to the other algorithms in Phase 2, we followed a systematic approach. We created a method within the Loader Class specifically tailored to load the dataset required for the Dijkstra algorithm. 
- All the testing procedures and inquiries conducted in Phase 2 were replicated using this newly implemented algorithm.

#### Results Dijkstra's algorithm

- Dijkstra's Algorithm: Determines the shortest path in weighted graphs by iteratively selecting the node with the lowest accumulated cost from the source node, considering both the cost to reach the node and the edge weight.

  **1) Does the search work?**
  - | Success Criteria                     | ok/failed |
    |--------------------------------------|-----------|
    | Finds the shortest path between nodes |           |
    
  **2) How good is the result?**

  - | Test Case Description                      | Input Data                                           | Expected Output          | Actual Output |
    |--------------------------------------------|-----------------------------------------------------|-------------------------|---------------|
    | Simple Weighted Graph                       | Graph: [Nodes: A, B, C, D; Edges: AB(3), BC(2), CD(1)] | Shortest path from A to D |               |

  **3) How long does it take?**

  **4) How do the algorithms perform on graphs of varying sizes (small, medium, large)?**

  **5) Measure the memory consumption of each algorithm. Does it scale with the size of the graph?**

  **6) How well do the algorithms handle invalid input (e.g., trying to find a path in a non-existent node)?**

