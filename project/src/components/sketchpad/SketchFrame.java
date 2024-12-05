package components.sketchpad;

import javax.swing.*;

import utils.Constants;

public class SketchFrame {

    public static JFrame sketchFrame = createFrame();

    private static JFrame createFrame() {
        JFrame sketchFrame = new JFrame("Sektchpad");
        sketchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sketchFrame.setSize(400, 500);
        sketchFrame.setUndecorated(true);
        sketchFrame.getContentPane().setBackground(Constants.main);
        sketchFrame.setLocation(1000, 100);
        sketchFrame.setJMenuBar(SketchMenu.menuBar);
        sketchFrame.add(new SketchPanel());

        JRootPane rootPane = sketchFrame.getRootPane();
        rootPane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Constants.accent));

        return sketchFrame;
    }
}
