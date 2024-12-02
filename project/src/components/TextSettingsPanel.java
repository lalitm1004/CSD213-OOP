package components;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

import utils.*;

public class TextSettingsPanel extends JPanel {

    private JLabel fontSizeLabel;
    private JButton boldButton;
    private JButton italicButton;
    private JLabel tokenCountLabel;
    private int fontSize = Constants.defaultFontSize;
    private static final int MAX_FONT_SIZE = 96;
    private static final int MIN_FONT_SIZE = 12;
    private EditorPane editorPane;
    private boolean isBold = false;
    private boolean isItalic = false;

    public TextSettingsPanel(EditorPane editorPane) {
        this.editorPane = editorPane;
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setBackground(Constants.main);
        setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Constants.accent));

        // font type settings
        JMenuBar fontTypeMenuBar = new JMenuBar();
        fontTypeMenuBar.setBackground(Constants.main);
        fontTypeMenuBar.setBorder(BorderFactory.createEmptyBorder());
        JMenu fontTypeMenu = new FontTypeOperations(editorPane).getMenu();
        fontTypeMenuBar.add(fontTypeMenu);
        add(fontTypeMenuBar);

        // separator
        addSeparatorLabel();

        // font size settings
        JButton decreaseButton = createFontSettingButton("- ", _ -> changeFontSize(-2));
        JButton increaseButton = createFontSettingButton(" +", _ -> changeFontSize(2));

        fontSizeLabel = new JLabel("Font size > " + fontSize);
        fontSizeLabel.setFont(Constants.controlsFont18);
        fontSizeLabel.setForeground(Constants.text);

        JPanel fontSizePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fontSizePanel.setBackground(Constants.main);
        fontSizePanel.add(decreaseButton);
        fontSizePanel.add(fontSizeLabel);
        fontSizePanel.add(increaseButton);
        add(fontSizePanel);

        // separator
        addSeparatorLabel();
        
        // font modifiers
        boldButton = createFontSettingButton("B ", _ -> toggleBoldModifier());

        italicButton = createFontSettingButton("I", _ -> toggleItalicModifier());

        JPanel fontModifierPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fontModifierPanel.setBackground(Constants.main);
        fontModifierPanel.add(boldButton);
        fontModifierPanel.add(italicButton);

        add(fontModifierPanel);

        addSeparatorLabel();

        tokenCountLabel = new JLabel("Word/Char > " + "0/0");
        tokenCountLabel.setForeground(Constants.text);
        tokenCountLabel.setFont(Constants.controlsFont18);
        add(tokenCountLabel);

        editorPane.getTextPane().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTokenCount();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTokenCount();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

        // input maps

        InputMap inputMap = editorPane.getTextPane().getInputMap(JComponent.WHEN_FOCUSED);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, KeyEvent.CTRL_DOWN_MASK), "increaseFontSize");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK), "decreaseFontSize");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK), "toggleBoldModifier");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK), "toggleItalicModifier");

        ActionMap actionMap = editorPane.getTextPane().getActionMap();
        actionMap.put("increaseFontSize", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFontSize(2);
            }
        });
        actionMap.put("decreaseFontSize", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFontSize(-2);
            }
        });
        actionMap.put("toggleBoldModifier", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleBoldModifier();
            } 
        });
        actionMap.put("toggleItalicModifier", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleItalicModifier();
            } 
        });
    }

    private JButton createFontSettingButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        Helper.setColor(button, Constants.main, Constants.text);
        button.setFont(Constants.controlsFont16);
        button.setBorder(BorderFactory.createEmptyBorder(5, 2, 5, 2));
        button.addActionListener(listener);

        return button;
    }

    private void updateTokenCount() {
        String text = editorPane.getTextPane().getText();
        int numWords = text != "" ? text.split(" ").length : 0;
        int numChars = text != "" ? text.split("").length: 0;
        tokenCountLabel.setText("Word/Char > " + numWords + "/" + numChars);
    }

    private void changeFontSize(int delta) {
        fontSize += delta;
        if (fontSize < MIN_FONT_SIZE) fontSize = MIN_FONT_SIZE;
        if (fontSize > MAX_FONT_SIZE) fontSize = MAX_FONT_SIZE;
        fontSizeLabel.setText("Font size > " + fontSize);

        StyleConstants.setFontSize(editorPane.getCurrentAttributes(), fontSize);

        int start = editorPane.getTextPane().getSelectionStart();
        int end = editorPane.getTextPane().getSelectionEnd();
        if (start != end) {
            StyledDocument doc = editorPane.getTextPane().getStyledDocument();
            doc.setCharacterAttributes(start, end - start, editorPane.getCurrentAttributes(), false);
        } else {
            editorPane.getTextPane().setCharacterAttributes(editorPane.getCurrentAttributes(), true);
        }
    }

    private void toggleBoldModifier() {
        isBold = !isBold;
        boldButton.setForeground(isBold ? Constants.accent : Constants.text);
        StyleConstants.setBold(editorPane.getCurrentAttributes(), isBold);

        int start = editorPane.getTextPane().getSelectionStart();
        int end = editorPane.getTextPane().getSelectionEnd();
        if (start != end) {
            StyledDocument doc = editorPane.getTextPane().getStyledDocument();
            doc.setCharacterAttributes(start, end - start, editorPane.getCurrentAttributes(), false);
        } else {
            editorPane.getTextPane().setCharacterAttributes(editorPane.getCurrentAttributes(), true);
        }
    }

    private void toggleItalicModifier() {
        isItalic = !isItalic;
        italicButton.setForeground(isItalic ? Constants.accent : Constants.text);
        StyleConstants.setItalic(editorPane.getCurrentAttributes(), isItalic);

        int start = editorPane.getTextPane().getSelectionStart();
        int end = editorPane.getTextPane().getSelectionEnd();
        if (start != end) {
            StyledDocument doc = editorPane.getTextPane().getStyledDocument();
            doc.setCharacterAttributes(start, end - start, editorPane.getCurrentAttributes(), false);
        } else {
            editorPane.getTextPane().setCharacterAttributes(editorPane.getCurrentAttributes(), true);
        }
    }

    private void addSeparatorLabel() {
        JLabel separatorLabel = new JLabel(" | ");
        Helper.setColor(separatorLabel, Constants.main, Constants.accent);
        separatorLabel.setFont(Constants.controlsFont18);
        add(separatorLabel);
    }
}
