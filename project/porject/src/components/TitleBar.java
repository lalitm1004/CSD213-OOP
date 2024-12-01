package components;

import java.awt.*;
import javax.swing.*;

import utils.*;

public class TitleBar extends JPanel {
    
    public TitleBar(
        JFrame textEditorFrame,
        EditorPane editorPane
    ) {
        setLayout(new BorderLayout());
        setBackground(Constants.main);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Constants.main);
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.accent));

        JLabel projectTitle = new JLabel(" CSD213 Project | ");
        projectTitle.setForeground(Constants.accent);
        projectTitle.setFont(Constants.controlsFont18);

        JMenu fileMenu = new FileOperations(editorPane).getMenu();

        // custom window controls
        JButton closeButton = new JButton("x");
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        Helper.setColor(closeButton, Constants.destructive, Constants.main);
        closeButton.setFont(Constants.controlsFont18);
        closeButton.addActionListener(_ -> System.exit(0));

        JPanel windowControls = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        windowControls.setOpaque(false);
        windowControls.add(closeButton);

        menuBar.add(projectTitle);
        menuBar.add(fileMenu);

        add(menuBar, BorderLayout.CENTER);
        add(windowControls, BorderLayout.EAST);
    }
}