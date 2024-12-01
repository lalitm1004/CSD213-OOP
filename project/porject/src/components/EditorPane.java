package components;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.*;

import utils.*;

public class EditorPane extends JScrollPane {

    private JTextPane textPane;
    private MutableAttributeSet currentAttributes;

    public EditorPane() {
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setBorder(BorderFactory.createEmptyBorder());

        currentAttributes = new SimpleAttributeSet();

        textPane = new JTextPane();

        Helper.setColor(textPane, Constants.main, Constants.text);
        textPane.setCaretColor(Constants.text);
        textPane.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Constants.accent));
        textPane.setFont(new Font("OCR A Extended", Font.PLAIN, 24));

        setViewportView(textPane);
    }

    public JTextPane getTextPane() {
        return this.textPane;
    }

    public MutableAttributeSet getCurrentAttributes() {
        return this.currentAttributes;
    }
}
