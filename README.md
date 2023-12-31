# Project Report
> Your project report should document the three phases of your project. There is no specific length
requirement. Include enough information for the instructor to understand what your did, and what
the results were. Include code-snippets as needed, but don’t just fill the document with code.
Your report should be complete. At the same time, please consider:
Brevity is the soul of wit / In der Kürze liegt die Würze / Omit needless words

## Project Members
- Joel Läubin
- Margareta Karaqi
- Nico Sträuli

## Phase 1: Create map data
- During this initial phase of the project, all of the group members collected data as explained in the guidelines.
- However, due to persistent issues with the consolidated data, we opted to utilize a smaller dataset, containing only the cities: Lostorf, Basel, Luzern, Baden, Remigen, Turgi, Aarau, and Unterentfelden (approximately 186 nodes from 8 students).


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

1) **Does the search work?**
- | Algorithm     | ok (&#x2714;) /<br> ok with conditions **~** / <br> failed (&#x2718;) | Comment                                                                                                                                                                                                                                                                                             |
  |---------------|-----------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
  | Depth First   | &#x2714;                                                              |                                                                                                                                                                                                                                                                                                     |
  | Breadth First | &#x2714;                                                              |                                                                                                                                                                                                                                                                                                     |
  | Best First    | &#x2714;                                                              |                                                                                                                                                                                                                                                                                                     |
  | A*            | **~**                                                                 | Single runs work. However, if we test the algorithm with multiple runs (loop) it fails to find the destination nodes in most cases. We tried to figure out the root cause of this problem. However, we were not able to detect and solve it in time, despite the heavy effort of all group members. |
  | Dijkstra's    | **~**                                                                 | Single runs work. However, if we test the algorithm with multiple runs (loop) it fails to find the destination nodes in most cases. We tried to figure out the root cause of this problem. However, we were not able to detect and solve it in time, despite the heavy effort of all group members.                                              |
2) **How good is the result?**

-  **Depth First** 

    - | Nodes                                                            | Input Data                    | Output                                                                                                                                                                                                         |
      |------------------------------------------------------------------|-------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
      | Start node `Aarau/Asylstrasse/1` to end node `Aarau/Zollrain/16` | edges.csv Nodes: [0] - [30]   | **BAD** - The result is not ideal, because algorithm chooses a diversionary path: `Aarau` &rarr;  `Turgi` &rarr; `Remigen` &rarr; `Turgi` &rarr; `Baden` &rarr; `Aarau`.                                       |
      | Start node `Aarau/Zollrain/16` to end node `Basel/Birmannsgasse/49` | edges.csv Nodes: [30] - [60]  | **BAD** - The result is not ideal, because algorithm chooses a diversionary path: `Aarau` &rarr;  `Turgi` &rarr; `Remigen` &rarr; `Turgi` &rarr; `Baden` &rarr; `Basel`.                                       |
      | Start node `Basel/Birmannsgasse/49` to end node `Lostorf/Hofmattstrasse/20` | edges.csv Nodes: [60] - [90]  | **BAD** - The result is not ideal, because algorithm chooses a diversionary path: `Basel` &rarr;  `Baden` &rarr; `Emmen` &rarr; `Suhr` &rarr; `Aarau` &rarr; `Unterentfelden` &rarr; `Aarau` &rarr; `Lostorf`. |
      | Start node `Lostorf/Hofmattstrasse/20` to end node `Luzern/Zentralstrasse/1` | edges.csv Nodes: [90] - [120] | **BAD** - The result is not ideal, because algorithm chooses a diversionary path: `Lostorf` &rarr;  `Basel` &rarr; `Turgi` &rarr; `Remigen` &rarr; `Turgi` &rarr; `Baden` &rarr; `Emmen` &rarr; `Luzern`.      |


-  **Breadth First**

    - | Nodes                                                             | Input Data                    | Output                                                                                                                                                                                      |
      |------------------------------------------------------------------|-------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
      | Start node `Aarau/Asylstrasse/1` to end node `Aarau/Zollrain/16` | edges.csv Nodes: [0] - [30]   | **GOOD** - The result is good. Compared to Depth First, the algorithm stays in `Aarau` when we choose the same start and destination.                                                       |
      | Start node `Aarau/Zollrain/16` to end node `Basel/Birmannsgasse/49` | edges.csv Nodes: [30] - [60]  | **MEDIUM** - The result is suboptimal, though it surpasses the depth algorithm in efficiency with fewer stops along the path: `Aarau` &rarr;  `Baden` &rarr; `Basel`.                       |
      | Start node `Basel/Birmannsgasse/49` to end node `Lostorf/Hofmattstrasse/20` | edges.csv Nodes: [60] - [90]  | **GOOD** - The result is commendable, as the path is straightforward: `Basel` &rarr;  `Lostorf`.                                                                                            |
      | Start node `Lostorf/Hofmattstrasse/20` to end node `Luzern/Zentralstrasse/1` | edges.csv Nodes: [90] - [120] | **BAD** - The result is not ideal, because the algorithm chooses a diversionary path: `Lostorf` &rarr;  `Basel` &rarr; `Turgi` &rarr; `Aarau` &rarr; `Suhr` &rarr; `Emmen` &rarr; `Luzern`. |


-  **Best First**

    - | Nodes                                                             | Input Data                    | Output                                                                                                                                                                                             |
      |------------------------------------------------------------------|-------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
      | Start node `Aarau/Asylstrasse/1` to end node `Aarau/Zollrain/16` | edges.csv Nodes: [0] - [30]  | **GOOD** - The result is good. Compared to Depth First, the algorithm stays in `Aarau` when we choose the same start and destination.                                                              |
      | Start node `Aarau/Zollrain/16` to end node `Basel/Birmannsgasse/49` | edges.csv Nodes: [30] - [60]  | **MEDIUM** - While not optimal, it outperforms the e.g. the depth first algorithm in efficiency along the path:: `Aarau` &rarr;  `Turgi` &rarr; `Baden` &rarr; `Basel`.                            |
      | Start node `Basel/Birmannsgasse/49` to end node `Lostorf/Hofmattstrasse/20` | edges.csv Nodes: [60] - [90]  | **GOOD** - The outcome is satisfactory, but due to a slight gap in Lostorf's connection, it opts to travel from Basel to Baden initially: `Basel` &rarr;  `Baden` &rarr; `Aarau` &rarr; `Lostorf`. |
      | Start node `Lostorf/Hofmattstrasse/20` to end node `Luzern/Zentralstrasse/1` | edges.csv Nodes: [90] - [120] | **GOOD** - The result is great, because the algorithm finds a suitable path: `Lostorf` &rarr;  `Aarau` &rarr; `Unterentfelden` &rarr; `Emmen` &rarr; `Luzern`.                                     |


-  **A***

    - | Nodes                                                             | Input Data                | Output                                                                                                                                                        |
      |------------------------------------------------------------------|---------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------|
      | Start node `Aarau/Asylstrasse/1` to end node `Aarau/Zollrain/16` | edges.csv Nodes: [0] - [30] | **GOOD** - The result is good. Compared to Depth First, the algorithm stays in `Aarau` when we choose the same start and destination.                         |
      | Start node `Aarau/Zollrain/16` to end node `Basel/Birmannsgasse/49` | edges.csv Nodes: [30] - [60]  | **GOOD** - The result is fine, since the path is almost straight forward and considering our map: `Aarau` &rarr;  `Baden` &rarr; `Basel`.                     |
      | Start node `Basel/Birmannsgasse/49` to end node `Lostorf/Hofmattstrasse/20` | edges.csv Nodes: [60] - [90]  | **GOOD** - The outcome is good, as the path is suitable: `Basel` &rarr;  `Aarau` &rarr; `Lostorf`.                                                            |
      | Start node `Lostorf/Hofmattstrasse/20` to end node `Luzern/Zentralstrasse/1` | edges.csv Nodes: [90] - [120] | **GOOD** - The result is good, because the algorithm finds a suitable path: `Lostorf` &rarr;  `Aarau` &rarr; `Unterentfelden` &rarr; `Emmen` &rarr; `Luzern`. |

-  **Dijkstra's**

    - | Nodes                                                             | Input Data                | Output                                                                                                                                                                                                 |
      |------------------------------------------------------------------|---------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
      | Start node `Aarau/Asylstrasse/1` to end node `Aarau/Zollrain/16` | edges.csv Nodes: [0] - [30] | **MEDIUM** - The algorithm uses more steps to get to the destination node. However, it stays in `Aarau`.                                                                                               |
      | Start node `Aarau/Zollrain/16` to end node `Basel/Birmannsgasse/49` | edges.csv Nodes: [30] - [60]  | **GOOD** - The result is fine, since the path is almost straight forward and considering our map: `Aarau` &rarr;  `Baden` &rarr; `Basel`.                                                              |
      | Start node `Basel/Birmannsgasse/49` to end node `Lostorf/Hofmattstrasse/20` | edges.csv Nodes: [60] - [90]  | **GOOD** - _The outcome is good, as the path is straight forward: `Basel` &rarr;  `Lostorf`.                                                                                                           |
      | Start node `Lostorf/Hofmattstrasse/20` to end node `Luzern/Zentralstrasse/1` | edges.csv Nodes: [90] - [120] | **BAD** - The result is suboptimal, because the algorithm selects a diversion: `Lostorf` &rarr;  `Basel` &rarr; `Turgi` &rarr; `Remigen` &rarr; `Turgi` &rarr; `Baden` &rarr; `Emmen` &rarr; `Luzern`. |


3) **How long does it take?**
- ![measurements_big.png](src%2Fmain%2Fresources%2Fdocs%2Fgraph_big%2Fmeasurements_big.png)
- Conclusion from this analysis:
  - We compared Depth First, Breadth First and Best First in a total of 10 scenarios, where each scenario has a given start and destination node.
  - The diagram indicates fast runs with a green bar, medium runs with a yellow bar, and slow runs with a red one.
  - The diagram indicates the fastest runtime with a green bar, the 2'nd fastest runtime with a yellow bar and the slowest runtime with a red bar.
  - In 7/10 runs, the Best First algorithm had the fastest runtime.
  - In 6/10 runs, the Depth First algorithm had the slowest runtime.

4) **How do the algorithms perform on graphs of varying sizes (small, large)?**
- ![measurements_small.png](src%2Fmain%2Fresources%2Fdocs%2Fgraph_small%2Fmeasurements_small.png)
- Conclusion from this analysis:
  - During this test, we also compared Depth First, Breadth First and Best first in a total of 10 scenarios, where each scenario has a given start and destination node.
  - The main difference between the test mentioned above is that during this test, the underlying graph only contained 23 nodes.
  - The two graphs are not congruent, hence the results between the two graphs are incomparable.
  - However, it can be stated that with a decreasing number of nodes, the overall runtime for each algorithm decreases as well.
  - In 4/10 runs, the Breadth First algorithm had the fastest runtime. Both other algorithms only scored this rank 3/10 times.
  - In 4/10 runs, the Depth First algorithm had the slowest runtime.
5) **Measure the memory consumption of each algorithm. Does it scale with the size of the graph?**
   - **Comment:** When trying to measure memory consumption for each algorithm, we noticed that the memory stays almost unaffected during the runtime of each algorithm. This is due to not many objects being created during the algorithms itself.
       What obviously has an influence on memory consumption, however, is the loading of the data. We have slightly different representations of a Node for each algorithm, but since these differences are minimal (only a few attributes), the differences in memory consumption are minimal as well. Hence, we decided to not graphically display these differences, nor to write any measurements down.
6) **How well do the algorithms handle invalid input (e.g., trying to find a path in a non-existent node)?**
   - **Comment:** Each algorithm method contains exception handling for invalid inputs. At the beginning of each method, exception handling checks if any of the parameters of the method are null and if so, return a specific IllegalArgumentException with a defined message.


## Phase 3: New or adapted algorithm

### Decision
- We decided to implement **Dijkstra's algorithm** for the final phase of the project. Similar to the other algorithms in Phase 2, we followed a systematic approach. We created a method within the Loader Class specifically tailored to load the dataset required for the Dijkstra algorithm. 
- All the testing procedures and inquiries conducted in Phase 2 were replicated using this newly implemented algorithm.
- Check the question overview for test results.

## Future outlook for project improvements
As we look to the future, there are several avenues we can explore to further refine this project:

- Optimizing Search Strategies
- Visualizations and User Interface
- JUnit Tests