package coreJavaVolume2AdvancedFeatures.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.net.URL;

/**
 * @author zhangjinglong
 * @date 2020-03-21-8:25 下午
 * <p>
 * This program demonstrates how to use a SAX parser,The program prints all hyperlinks of
 * an XHTML web page.
 */

public class SAXTest {
    public static void main(String[] args) throws Exception {
        String url;
        if (args.length == 0) {
            url = "http://www.w3c.org";
            System.out.println("Using " + url);
        } else url = args[0];


        DefaultHandler handler = new DefaultHandler() {
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (localName.equals("a") && attributes != null) {
                    for (int i = 0; i < attributes.getLength(); i++) {
                        String aname = attributes.getLocalName(i);
                        if (aname.equals("href")) System.out.println(attributes.getValue(i));
                    }
                }

            }

        };

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        SAXParser saxParser = factory.newSAXParser();
        InputStream in = new URL(url).openStream();
        saxParser.parse(in, handler);

    }

}
