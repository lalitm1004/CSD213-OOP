package components;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

import utils.*;

public class TextSettingsPanel extends JPanel {

    private JLabel fontSizeLabel;
    private int fontSize = 24;
    private static final int MAX_FONT_SIZE = 40;
    private static final int MIN_FONT_SIZE = 12;
    private EditorPane textPane;

    public TextSettingsPanel(
        EditorPane textPane
    ) {
        this.textPane = textPane;
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0 ));
        setBackground(Constants.main);

        // font type settings
        JMenuBar fontTypeMenuBar = new JMenuBar();
        fontTypeMenuBar.setBackground(Constants.main);
        fontTypeMenuBar.setBorder(BorderFactory.createEmptyBorder());
        JMenu fontTypeMenu = new FontTypeOperations(textPane).getMenu();
        fontTypeMenuBar.add(fontTypeMenu);
        add(fontTypeMenuBar);

        JLabel separatorLabel = new JLabel(" | ");
        Helper.setColor(separatorLabel, Constants.main, Constants.accent);
        separatorLabel.setFont(Constants.controlsFont18);
        add(separatorLabel);

        // font size settings
        JButton decreaseButton = createFontSizeSettingButton("-", _ -> changeFontSize(-2));
        JButton increaseButton = createFontSizeSettingButton("+", _ -> changeFontSize(2));

        fontSizeLabel = new JLabel("Font size > " + fontSize);
        fontSizeLabel.setFont(Constants.controlsFont18);
        fontSizeLabel.setForeground(Constants.text);

        JPanel fontSizePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fontSizePanel.setBackground(Constants.main);
        fontSizePanel.add(decreaseButton);
        fontSizePanel.add(fontSizeLabel);
        fontSizePanel.add(increaseButton);

        InputMap inputMap = textPane.getTextPane().getInputMap(JComponent.WHEN_FOCUSED);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, KeyEvent.CTRL_DOWN_MASK), "increaseFontSize");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK), "decreaseFontSize");

        ActionMap actionMap = textPane.getTextPane().getActionMap();
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

        add(fontSizePanel);
    }

    private JButton createFontSizeSettingButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        Helper.setColor(button, Constants.main, Constants.text);
        button.setFont(Constants.controlsFont16);
        button.setBorder(BorderFactory.createEmptyBorder(5, 2, 5, 2));
        button.addActionListener(listener);

        return button;
    }

    private void changeFontSize(int delta) {
        fontSize += delta;
        if (fontSize < MIN_FONT_SIZE) fontSize = MIN_FONT_SIZE;
        if (fontSize > MAX_FONT_SIZE) fontSize = MAX_FONT_SIZE;
        fontSizeLabel.setText("Font size > " + fontSize);

        StyleConstants.setFontSize(textPane.getCurrentAttributes(), fontSize);

        int start = textPane.getTextPane().getSelectionStart();
        int end = textPane.getTextPane().getSelectionEnd();
        if (start != end) {
            StyledDocument doc = textPane.getTextPane().getStyledDocument();
            doc.setCharacterAttributes(start, end - start, textPane.getCurrentAttributes(), false);
        } else {
            textPane.getTextPane().setCharacterAttributes(textPane.getCurrentAttributes(), true);
        }
    }
}
