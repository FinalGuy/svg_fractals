import koch.snowflake.Point;
import koch.snowflake.Triangle;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

public class App {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        String initial = """
                <?xml version="1.0" encoding="UTF-8"?>
                <svg xmlns="http://www.w3.org/2000/svg"
                        xmlns:xlink="http://www.w3.org/1999/xlink"
                        version="1.1" baseProfile="full"
                        width="300" height="300">
                    <title>Koch'sche Schneeflocke</title>
                    <desc>...</desc>
                    
                    #FIRST
                    
                </svg>
                """;

        File file = new File("fractal_initial.svg");
        try (FileWriter fileWriter = new FileWriter(file)) {
            Triangle firstTriangle = Triangle.centeredAt(Point.fromCommaSeparatedCoordinates("150,150"));
            fileWriter.append(initial.replace("#FIRST", firstTriangle.asSvgPolygon()));
        }

        File firstTransformation = new File("fractal_1.svg");
        try (FileWriter fileWriter = new FileWriter(firstTransformation, false)) {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(file, new SvgNextGenerationHandler(fileWriter));
        }

    }
}    