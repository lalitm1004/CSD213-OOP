package components;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.*;

import utils.*;

public class EditorPane extends JScrollPane {

    private JTextPane textPane;
    private MutableAttributeSet currentAttributes;

    public EditorPane() {
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollBar verticalScrollBar = getVerticalScrollBar();
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Constants.text;
                this.trackColor = Constants.main;
            }

            @Override
            public Dimension getPreferredSize(JComponent c) {
                return new Dimension(4, super.getPreferredSize(c).height);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }
        });
        verticalScrollBar.setUnitIncrement(10);
        verticalScrollBar.setBlockIncrement(50);

        JScrollBar horizontalScrollBar = getHorizontalScrollBar();
        horizontalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Constants.text;
                this.trackColor = Constants.main;
            }

            @Override
            public Dimension getPreferredSize(JComponent c) {
                return new Dimension(super.getPreferredSize(c).width, 4);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }
        });
        horizontalScrollBar.setUnitIncrement(10);
        horizontalScrollBar.setBlockIncrement(50);

        setBorder(BorderFactory.createEmptyBorder());

        currentAttributes = new SimpleAttributeSet();

        textPane = new JTextPane();
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setLeftIndent(attributes, 10);
        StyleConstants.setRightIndent(attributes, 10);
        StyleConstants.setSpaceAbove(attributes, 10);
        StyleConstants.setSpaceBelow(attributes, 10);
        doc.setParagraphAttributes(0, doc.getLength(), attributes, false);

        Helper.setColor(textPane, Constants.main, Constants.text);
        textPane.setCaretColor(Constants.text);
        textPane.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Constants.accent));
        textPane.setFont(new Font("OCR A Extended", Font.PLAIN, Constants.defaultFontSize));

        setViewportView(textPane);
    }

    public JTextPane getTextPane() {
        return this.textPane;
    }

    public MutableAttributeSet getCurrentAttributes() {
        return this.currentAttributes;
    }

    private JButton createZeroButton() {
        JButton zeroButton = new JButton();
        Dimension zeroDim = new Dimension(0, 0);
        zeroButton.setPreferredSize(zeroDim);
        zeroButton.setMaximumSize(zeroDim);
        return zeroButton;
    }
}
