## Laboratorio 2 - Paradigmas de Programacion - FAMAF UNC

### Dependecias:
Java development kit (JKD)

`sudo apt install openjdk-17-jdk`

### Compilar:


`javac -cp "lib/json-20250107.jar" -d out $(find src -name "*.java")`


`java -cp "lib/json-20250107.jar:out" FeedReaderMain [heuristic]`


No arguments: prints the article in a human-readable format.
With one argument: uses a heuristic to compute a set of named entities from the feeds and their number of occurrences (global, by class, and by subclass), and displays the results in a table format.

