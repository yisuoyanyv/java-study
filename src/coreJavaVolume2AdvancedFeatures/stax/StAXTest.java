package coreJavaVolume2AdvancedFeatures.stax;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.net.URL;

/**
 * @author zhangjinglong
 * @date 2020-03-21-9:00 下午
 * <p>
 * This program demonstrates how to use a StAX parser. The program prints all
 * hyperlinks of an XHTML web page.
 */

public class StAXTest {
    public static void main(String[] args) throws Exception {
        String ulrString;
        if (args.length == 0) {
            ulrString = "http://www.w3c.org";
            System.out.println("Using:" + ulrString);
        } else ulrString = args[0];

        URL url = new URL(ulrString);
        InputStream in = url.openStream();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser = factory.createXMLStreamReader(in);
        while (parser.hasNext()) {
            int event = parser.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                if (parser.getLocalName().equals("a")) {
                    String href = parser.getAttributeValue(null, "href");
                    if (href != null) {
                        System.out.println(href);
                    }
                }
            }
        }
    }
}
