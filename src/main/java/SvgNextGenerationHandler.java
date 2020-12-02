import koch.snowflake.Triangle;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileWriter;
import java.io.IOException;

public class SvgNextGenerationHandler extends DefaultHandler {

    private FileWriter fileWriter;

    public SvgNextGenerationHandler(FileWriter fileWriter) throws IOException {
        this.fileWriter = fileWriter;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "polygon":
                applyTransformation(attributes);
                return;
            default:
                copyStartElementToOutput(qName, attributes);
        }

    }

    private void applyTransformation(Attributes attributes) {
        Triangle triangle = Triangle.fromCoordinates(attributes.getValue("points"));
        try {
            fileWriter.append(triangle.asSvgPolygon()).append("\n\t");
            for (Triangle subTriangle : triangle.applyIteration()) {
                fileWriter.append(subTriangle.asSvgPolygon()).append("\n\t");
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
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "polygon":
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
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
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
