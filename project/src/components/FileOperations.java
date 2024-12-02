package components;

import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.StyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

import utils.*;

public class FileOperations {

    private JMenu fileMenu;

    public FileOperations(EditorPane editorPane) {
        fileMenu = new JMenu("File");
        fileMenu.setFont(Constants.controlsFont18);
        fileMenu.setForeground(Constants.text);

        JMenuItem newFile = createMenuItem("New", _ -> editorPane.getTextPane().setText(""));
        JMenuItem open = createMenuItem("Open", _ -> openFile(editorPane));
        JMenuItem save = createMenuItem("Save", _ -> saveFile(editorPane));

        fileMenu.add(newFile);
        fileMenu.add(open);
        fileMenu.add(save);
    }

    public JMenu getMenu() {
        return fileMenu;
    }

    private JMenuItem createMenuItem(String text, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(text);
        Helper.setColor(menuItem, Constants.main, Constants.text);
        menuItem.setFont(Constants.controlsFont16);
        menuItem.addActionListener(listener);
        return menuItem;
    }

    private void openFile(EditorPane textPane) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("RTF Files", "rtf"));
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try (FileInputStream fis = new FileInputStream(fileChooser.getSelectedFile())) {
                RTFEditorKit rtfEditorKit = new RTFEditorKit();
                StyledDocument doc = (StyledDocument) rtfEditorKit.createDefaultDocument();
                rtfEditorKit.read(fis, doc, 0);
                textPane.getTextPane().setStyledDocument(doc);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void saveFile(EditorPane editorPane) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("RTF Files", "rtf"));
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try (FileOutputStream fos = new FileOutputStream(fileChooser.getSelectedFile())) {
                RTFEditorKit rtfEditorKit = new RTFEditorKit();
                rtfEditorKit.write(fos, editorPane.getTextPane().getStyledDocument(), 0, editorPane.getTextPane().getStyledDocument().getLength());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
