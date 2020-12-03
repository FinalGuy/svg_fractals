import koch.snowflake.Line;
import koch.snowflake.Point;
import org.jetbrains.annotations.NotNull;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileWriter;
import java.io.IOException;

public class SvgNextGenerationHandler extends DefaultHandler {

    private final FileWriter fileWriter;

    public SvgNextGenerationHandler(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "line":
                applyTransformation(attributes);
                return;
            default:
                copyStartElementToOutput(qName, attributes);
        }

    }

    private void applyTransformation(Attributes attributes) {
        Point start = pointFrom(attributes, "x1", "y1");
        Point end = pointFrom(attributes, "x2", "y2");
        Line line = start.vectorTo(end).startingAt(start);

        try {
            for (Line newLine : line.applyIteration()) {
                fileWriter.append(newLine.asSvg()).append("\n\t");
            }
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    private Point pointFrom(Attributes attributes, String x1, String y1) {
        return Point.fromCoordinates(attributes.getValue(x1), attributes.getValue(y1));
    }


    private void copyStartElementToOutput(String qName, Attributes attributes) {
        try {
            fileWriter.append("<").append(qName).append(" ");
            for (int i = 0; i < attributes.getLength(); i++) {
                fileWriter.append(attributes.getQName(i)).append("=").append("\"").append(attributes.getValue(i)).append("\"").append(" ");
            }
            fileWriter.append(">").flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "line":
                break;
            default:
                copyEndElementToOutput(qName);
        }
    }

    private void copyEndElementToOutput(String qName) {
        try {
            fileWriter.append("</").append(qName).append(">").flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) {
        this.writeToOutput(ch, start, length);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        writeToOutput(ch, start, length);
    }

    private void writeToOutput(char[] ch, int start, int length) {
        try {
            for (int i = start; i < start + length; i++) {
                fileWriter.append(ch[i]);
            }
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
