import koch.snowflake.Point;
import koch.snowflake.Triangle;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

public class App {

    private static final int ITERATIONS = 5;

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        String initial = """
                <?xml version="1.0" encoding="UTF-8"?>
                <svg xmlns="http://www.w3.org/2000/svg"
                        xmlns:xlink="http://www.w3.org/1999/xlink"
                        version="1.1" baseProfile="full"
                        width="1000" height="1000">
                    <title>Koch'sche Schneeflocke</title>
                    <desc>...</desc>
                    
                    #FIRST
                    
                </svg>
                """;

        File file = new File("fractal_0.svg");
        try (FileWriter fileWriter = new FileWriter(file)) {
            Triangle firstTriangle = Triangle.initialTriangleCenteredAt(Point.fromCommaSeparatedCoordinates("500,500"));
            fileWriter.append(initial.replace("#FIRST", firstTriangle.asSvgPolygon()));
        }

        for (int i = 0; i <= ITERATIONS; i++) {
            File input = new File("fractal_" + i + ".svg");
            File output = new File("fractal_" + (i + 1) + ".svg");
            try (FileWriter outputWriter = new FileWriter(output, false)) {
                SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
                SAXParser saxParser = saxParserFactory.newSAXParser();
                saxParser.parse(input, new SvgNextGenerationHandler(i + 1, outputWriter));
            }
        }


    }
}    