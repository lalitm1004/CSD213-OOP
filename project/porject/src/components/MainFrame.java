package components;

import java.awt.*;
import javax.swing.*;

import utils.Constants;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Porject");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);

        EditorPane textPane = new EditorPane();
        add(textPane, BorderLayout.CENTER);

        TitleBar titleBar = new TitleBar(this, textPane);
        add(titleBar, BorderLayout.NORTH);

        TextSettingsPanel textSettingsPanel = new TextSettingsPanel(textPane);
        add(textSettingsPanel, BorderLayout.SOUTH);

        JRootPane rootPane = getRootPane();
        rootPane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Constants.accent));

        setVisible(true);
    }
}
