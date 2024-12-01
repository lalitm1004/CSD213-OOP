package components;

import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import utils.*;

public class FontTypeOperations {
    private EditorPane textPane;
    private JMenu fontTypeMenu;
    private static final String[] fontTypes = {
        "OCR A Extended",
        "Arial",
        "Courier New",
        "Verdana",
        "Wide Latin",
    };

    public FontTypeOperations(EditorPane textPane) {
        this.textPane = textPane;
        fontTypeMenu = new JMenu(" Font Menu");
        fontTypeMenu.setFont(Constants.controlsFont18);
        Helper.setColor(fontTypeMenu, Constants.main, Constants.text);

        fontTypeMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                JMenu menu = (JMenu) e.getSource();
                JPopupMenu popupMenu = menu.getPopupMenu();

                popupMenu.show(menu, 0, -popupMenu.getPreferredSize().height);
            }

            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });

        for (String fontType: fontTypes) {
            fontTypeMenu.add(
                createMenuItem(fontType, _ -> changeFontType(fontType))
            );
        }
    }

    public JMenu getMenu() {
        return this.fontTypeMenu;
    }

    private JMenuItem createMenuItem(String text, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(text);
        Helper.setColor(menuItem, Constants.main, Constants.text);
        menuItem.setFont(Constants.controlsFont16);
        menuItem.addActionListener(listener);
        return menuItem;
    }

    private void changeFontType(String fontType) {
        if (fontType == null) return;

        StyleConstants.setFontFamily(textPane.getCurrentAttributes(), fontType);
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
