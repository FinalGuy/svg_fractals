import koch.snowflake.Line;
import koch.snowflake.Point;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileWriter;
import java.io.IOException;

public class SvgNextGenerationHandler extends DefaultHandler {

    private final FileWriter fileWriter;

    public SvgNextGenerationHandler(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if ("line".equals(qName)) {
            applyTransformation(attributes);
        } else {
            copyStartElementToOutput(qName, attributes);
        }
    }

    private void applyTransformation(Attributes attributes) {
        Point start = Point.fromCoordinates(attributes.getValue("x1"), attributes.getValue("y1"));
        Point end = Point.fromCoordinates(attributes.getValue("x2"), attributes.getValue("y2"));
        Line line = new Line(start, end);
        try {
            for (Line newLine : line.applyIteration()) {
                fileWriter.append(newLine.asSvg()).append("\n\t");
            }
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        // no closing tag, because we're using inline tag.
        if (!"line".equals(qName)) {
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
    public void characters(char[] ch, int start, int length) {
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
