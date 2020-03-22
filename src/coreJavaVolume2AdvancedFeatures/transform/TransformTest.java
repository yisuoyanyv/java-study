package coreJavaVolume2AdvancedFeatures.transform;

import org.xml.sax.*;
import org.xml.sax.helpers.AttributesImpl;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;

/**
 * @author zhangjinglong
 * @date 2020-03-22-8:27 下午
 * <p>
 * This program demonstrates XLS transformations . It applies a transformatin to a set of employee records.
 * The records are stroed in the file employee.dat and truend into XML format.
 * Specify the stylee on the command line, e.g.
 */

public class TransformTest {
    public static void main(String[] args) throws Exception {
        Path path;
        if (args.length > 0) path = Paths.get(args[0]);
        else path = Paths.get("src/coreJavaVolume2AdvancedFeatures/transform", "makehtml.xsl");
        try (InputStream styleIn = Files.newInputStream(path)) {
            StreamSource styleSource = new StreamSource(styleIn);

            Transformer t = TransformerFactory.newInstance().newTransformer(styleSource);
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            try (InputStream docIn = Files.newInputStream(Paths.get("src/coreJavaVolume2AdvancedFeatures/transform", "employee.dat"))) {
                t.transform(new SAXSource(new EmployeeReader(), new InputSource(docIn)), new StreamResult(System.out));
            }
        }
    }
}

/**
 * This class reads the flat file employee.dat and reports SAX parser event to act as if it was parsing an XML file.
 */
class EmployeeReader implements XMLReader {
    private ContentHandler handler;


    @Override
    public void parse(InputSource input) throws IOException, SAXException {
        InputStream stream = input.getByteStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String rootElement = "staff";
        AttributesImpl atts = new AttributesImpl();

        if (handler == null) throw new SAXException("No content handler");

        handler.startDocument();
        handler.startElement("", rootElement, rootElement, atts);
        String line;

        while ((line = in.readLine()) != null) {
            handler.startElement("", "employee", "employee", atts);
            StringTokenizer t = new StringTokenizer(line, "|");

            handler.startElement("", "name", "name", atts);
            String s = t.nextToken();
            handler.characters(s.toCharArray(), 0, s.length());
            handler.endElement("", "name", "name");

            handler.startElement("", "salary", "salary", atts);
            s = t.nextToken();
            handler.characters(s.toCharArray(), 0, s.length());
            handler.endElement("", "salary", "salary");

            atts.addAttribute("", "year", "year", "CDATA", t.nextToken());
            atts.addAttribute("", "month", "month", "CDATA", t.nextToken());
            atts.addAttribute("", "day", "day", "CDATA", t.nextToken());
            handler.startElement("", "hiredate", "hiredate", atts);
            handler.endElement("", "hiredate", "hiredate");
            atts.clear();

            handler.endElement("", "employee", "employee");

        }

        handler.endElement("", rootElement, rootElement);
        handler.endDocument();
    }

    @Override
    public void setContentHandler(ContentHandler newValue) {
        handler = newValue;
    }

    @Override
    public ContentHandler getContentHandler() {
        return handler;
    }

    //the following methods are just do-nothing implementations
    @Override
    public void parse(String systemId) throws IOException, SAXException {

    }

    @Override
    public void setErrorHandler(ErrorHandler handler) {

    }

    @Override
    public ErrorHandler getErrorHandler() {
        return null;
    }

    @Override
    public void setDTDHandler(DTDHandler handler) {

    }

    @Override
    public DTDHandler getDTDHandler() {
        return null;
    }

    @Override
    public void setEntityResolver(EntityResolver resolver) {

    }

    @Override
    public EntityResolver getEntityResolver() {
        return null;
    }

    @Override
    public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {

    }

    @Override
    public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        return null;
    }

    @Override
    public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {

    }

    @Override
    public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        return false;
    }
}