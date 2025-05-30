## Laboratorio 2 - Paradigmas de Programacion - FAMAF UNC

### Dependecias:
Java development kit (JDK)

`sudo apt install openjdk-17-jdk`

### Compilar:


`javac -cp "lib/json-20250107.jar" -d out $(find src -name "*.java")`


`java -cp "lib/json-20250107.jar:out" FeedReaderMain [heuristic]`


No arguments: prints the article in a human-readable format.

`java -cp "lib/json-20250107.jar:out" FeedReaderMain`

With one argument: uses a heuristic to compute a set of named entities from the feeds and their number of occurrences (global, by class, and by subclass), and displays the results in a table format.


Available heuristics:

Computes entities using the Quick Heuristic:


`java -cp "lib/json-20250107.jar:out" FeedReaderMain QuickHeuristic`


`java -cp "lib/json-20250107.jar:out" FeedReaderMain q`

Computes entities using the Random Heuristic:


`java -cp "lib/json-20250107.jar:out" FeedReaderMain RandomHeuristic`

`java -cp "lib/json-20250107.jar:out" FeedReaderMain r`

