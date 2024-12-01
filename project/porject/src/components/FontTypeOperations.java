package components;

import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

import utils.*;

public class FontTypeOperations {
    
    private EditorPane editorPane;
    private JMenu fontTypeMenu;
    private static final String[] fontTypes = {
        "OCR A Extended",
        "Arial",
        "Courier New",
        "Verdana",
        "Wide Latin",
        "Aptos Display",
        "Papyrus",
        "Jokerman",
        "Jujutsu Kaisen", // remove before submission
    };

    public FontTypeOperations(EditorPane editorPane) {
        this.editorPane = editorPane;
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
        menuItem.setFont(new Font(text, Font.PLAIN, 18));
        menuItem.addActionListener(listener);
        return menuItem;
    }

    private void changeFontType(String fontType) {
        if (fontType == null) return;

        StyleConstants.setFontFamily(editorPane.getCurrentAttributes(), fontType);
        int start = editorPane.getTextPane().getSelectionStart();
        int end = editorPane.getTextPane().getSelectionEnd();
        if (start != end) {
            StyledDocument doc = editorPane.getTextPane().getStyledDocument();
            doc.setCharacterAttributes(start, end - start, editorPane.getCurrentAttributes(), false);
        } else {
            editorPane.getTextPane().setCharacterAttributes(editorPane.getCurrentAttributes(), true);
        }
    }
}
