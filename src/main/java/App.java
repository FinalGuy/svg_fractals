import java.io.*;

public class App {

    public static void main(String[] args) throws IOException {

        String prefix = """
                <?xml version="1.0" encoding="UTF-8"?>
                <svg xmlns="http://www.w3.org/2000/svg"
                        xmlns:xlink="http://www.w3.org/1999/xlink"
                        version="1.1" baseProfile="full"
                        width="800" height="800">
                        <title>Titel der Datei</title>
                        <desc>...</desc>
                        
                """;

        String content = """
                    <line x1="400" y1="100" x2="700" y2="500" stroke="red" />
                    <line x1="400" y1="100" x2="100" y2="500" stroke="green" />
                    <line x1="100" y1="500" x2="700" y2="500" stroke="yellow" />
                    <line x1="300" y1="500" x2="500" y2="500" stroke="blue" transform="rotate(-60 300 500)" />
                    <line x1="300" y1="500" x2="500" y2="500" stroke="pink" transform="rotate(60 500 500)" />
                """;

        String suffix = "</svg>";

        File file = new File("fractal.svg");
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.append(prefix).append(content).append(suffix);
        }

        FileReader fileReader = new FileReader(file);
        LineNumberReader lineNumberReader = new LineNumberReader(fileReader);

        while (lineNumberReader.ready()){
            System.out.println(lineNumberReader.getLineNumber());
            System.out.println(lineNumberReader.readLine());
        }

    }
}    