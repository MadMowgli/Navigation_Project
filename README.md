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

1) **Does the search work?**
- | Algorithm     | ok (&#x2714;) / failed (&#x2718;) |Comment |
  |---------------|-----------------------------------|--------|
  | Depth First   | &#x2714;                          |        |
  | Breadth First | &#x2714;                          |        |
  | Best First    | &#x2714;                          |        |
  | A*            | &#x2718;                          |Single runs work. However, if we test the algorithm with multiple runs (loop) it fails to find the destination nodes in most cases. We tried to figure out the root cause of this problem. However, we were not able to detect and solve it in time.        |
  | Dijkstra's    | &#x2718;                       |Single runs work. However, if we test the algorithm with multiple runs (loop) it fails to find the destination nodes in most cases. We tried to figure out the root cause of this problem. However, we were not able to detect and solve it in time.        |
2) **How good is the result?**
-   | Algorithm     | Test                                                             | Input Data                  | Output                                                                                                                                                |
    |---------------|------------------------------------------------------------------|-----------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|
    | Depth First   | Start node `Aarau/Asylstrasse/1` to end node `Aarau/Zollrain/16` | edges.csv (Example: 0 - 30) | **BAD** - _Example:_ Result is not ideal, because algorithm goes from AArau to Turgi, to Remigen, back to Turgi, to Baden and subsequentlly to Aarau. |
    | Breadth First | Start node `Aarau/Asylstrasse/1` to end node `Aarau/Zollrain/16` | edges.csv (Example: 0 - 30) | **GOOD** - _Example:_ The result is good. Compared to Depth First, the algorithm stays in Aarau when we choose the same start and destination.     |
    | Best First    | Start node `Aarau/Asylstrasse/1` to end node `Aarau/Zollrain/16` | edges.csv (Example: 0 - 30) | **GOOD** - _Example:_ The result is good. Compared to Depth First, the algorithm stays in Aarau when we choose the same start and destination. |
    | A*            | Start node `Aarau/Asylstrasse/1` to end node `Aarau/Zollrain/16` | edges.csv (Example: 0 - 30) | **GOOD** - _Example:_ The result is good. Compared to Depth First, the algorithm stays in Aarau when we choose the same start and destination. |
    | Dijkstra's    | Start node `Aarau/Asylstrasse/1` to end node `Aarau/Zollrain/16` | edges.csv (Example: 0 - 30) | **MEDIUM** - _Example:_ The algorithm uses more steps to get to the destination node. However, it stays in Aarau.|


3) **How long does it take?**
- ![measurements.png](src%2Fmain%2Fresources%2Fdocs%2Fmeasurements.png)
- Conclusion from this analysis:
  - We compared several runs for different algorithms and measured the time in nanoseconds.
  - Therefore we used the same starting and ending nodes and tested those for the algorithms Depth First, Breadth First, Best First.
  - The diagram indicates fast runs with a green bar, medium runs with a yellow bar, and slow runs with a red one.
  - In 7/10 runs, the algorithm Best First had the fastest runtime.
  - In 6/10 runs, the algorithm Depth First had the slowest runtime.

4) **How do the algorithms perform on graphs of varying sizes (small, large)?**
   - TODO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! @JOEL THE GOAT MOWGLIIII 
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