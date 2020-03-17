package coreJavaVolumeFundamentals.colorChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author zhangjinglong
 * @date 2020-03-15-5:36 下午
 * <p>
 * A panel with buttons to pop up three types of color choosers
 */

public class ColorChooserPanel extends JPanel {

    public ColorChooserPanel() {
        JButton modalButton = new JButton("Modal");
        modalButton.addActionListener(new ModalListener());
        add(modalButton);

        JButton modalessButton = new JButton("Modaless");
        modalessButton.addActionListener(new ModalessListener());
        add(modalessButton);

        JButton immediateButton = new JButton("Immediate");
        immediateButton.addActionListener(new ImmediateListener());
        add(immediateButton);


    }

    /**
     * This listener pops up a modal color chooser
     */
    private class ModalListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color defaultColor = getBackground();
            Color selected = JColorChooser.showDialog(ColorChooserPanel.this, "Set background", defaultColor);
            if (selected != null) setBackground(selected);
        }
    }


    /**
     * This listener pops up a modaless color chooser. The panel color is changed when the user clicks the OK button
     */
    private class ModalessListener implements ActionListener {
        private JDialog dialog;
        private JColorChooser chooser;

        public ModalessListener() {
            chooser = new JColorChooser();
            dialog = JColorChooser.createDialog(ColorChooserPanel.this, "Background Color", false /*not modal*/,
                    chooser, event -> setBackground(chooser.getColor()), null/*no Cancel button listener*/);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            chooser.setColor(getBackground());
            dialog.setVisible(true);

        }
    }


    /**
     * This listener pops up a modaless color chooser. The panel color is changed immediately when the user picks a new color.
     */
    private class ImmediateListener implements ActionListener {
        private JDialog dialog;
        private JColorChooser chooser;


        public ImmediateListener() {
            chooser = new JColorChooser();
            chooser.getSelectionModel().addChangeListener(
                    event -> setBackground(chooser.getColor())
            );

            dialog = new JDialog((Frame) null, false/*not modal*/);
            dialog.add(chooser);
            dialog.pack();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            chooser.setColor(getBackground());
            dialog.setVisible(true);
        }
    }
}
