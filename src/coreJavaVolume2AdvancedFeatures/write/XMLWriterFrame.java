package coreJavaVolume2AdvancedFeatures.write;

import org.w3c.dom.Document;
import sun.jvm.hotspot.oops.OopUtilities;

import javax.swing.*;
import javax.xml.crypto.dsig.TransformException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author zhangjinglong
 * @date 2020-03-21-9:50 下午
 * <p>
 * A frame with a component for showing a modern drawing.
 */

public class XMLWriterFrame extends JFrame {
    private RectangleCompoent comp;
    private JFileChooser chooser;

    public XMLWriterFrame() {
        chooser = new JFileChooser();
        //add compoent to frame

        comp = new RectangleCompoent();
        add(comp);

        //set up menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem newItem = new JMenuItem("New");
        menu.add(newItem);
        newItem.addActionListener(event -> comp.newDrawing());

        JMenuItem saveItem = new JMenuItem("Save with DOM/XSLT");
        menu.add(saveItem);
        saveItem.addActionListener(event -> saveDocument());

        JMenuItem savaStAXItem = new JMenuItem("Save with StAX");
        menu.add(savaStAXItem);
        savaStAXItem.addActionListener(event -> savaStAX());

        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(event -> System.exit(0));
        pack();
    }

    /**
     * Saves the drawing in SVG format,using DOM/XSLT.
     */
    public void saveDocument() {
        try {
            if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
            ;
            File file = chooser.getSelectedFile();
            Document doc = comp.buildDocument();
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
                    "http://www.w3.org/TR/2000/CR-SVG-20000802/DTD/svg-20000802.dtd");
            t.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "-//W3C//DTD SVG 20000802//EN");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            t.transform(new DOMSource(doc), new StreamResult(Files.newOutputStream(file.toPath())));
        } catch (TransformerException | IOException ex) {
            ex.printStackTrace();
        }


    }

    /**
     * Saves the drawing in SVG formamt, uing StAX.
     */
    public void savaStAX() {
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = chooser.getSelectedFile();
        XMLOutputFactory factory = XMLOutputFactory.newFactory();
        try {
            XMLStreamWriter writer = factory.createXMLStreamWriter(Files.newOutputStream(file.toPath()));
            try {
                comp.writeDocument(writer);
            } finally {
                writer.close();//Not autocloseable
            }
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

    }
}
