
package components.sketchpad;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

import utils.Constants;
import utils.Helper;

public class SketchMenu {
    private static JComboBox<String> shapeMenu = createShapeMenu();
    private static JButton closeButton = createCloseButton();
    public static JMenuBar menuBar = createMenuBar();
    public static ShapeType currentShapeType = ShapeType.RECTANGLE;
    public enum ShapeType {RECTANGLE, OVAL, LINE, TRIANGLE, PENTAGON};

    private static JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Constants.main);
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Constants.accent));
        menuBar.setPreferredSize(new Dimension(325, 40));
        menuBar.add(Box.createHorizontalStrut(10));
        menuBar.add(shapeMenu);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(closeButton);
        menuBar.add(Box.createHorizontalStrut(20));

        return menuBar;
    }

    private static JButton createCloseButton() {
        JButton closeButton = new JButton("✖");
        closeButton.setBackground(Constants.main);
        Helper.setColor(closeButton, Constants.main, Constants.text);

        closeButton.addActionListener(_ -> {
            SketchFrame.sketchFrame.setVisible(false);
        });

        return closeButton;
    }

    private static JComboBox<String> createShapeMenu() {
        UIManager.put("ComboBox.selectionBackground", Constants.main);
        UIManager.put("ComboBox.selectionForeground",Constants.text);
        UIManager.put("ComboBox.background", Constants.main);
        UIManager.put("ComboBox.foreground", Constants.text);
        UIManager.put("ComboBox.border", BorderFactory.createEmptyBorder());

        String[] shapeOptions = {"Rectangle", "Oval", "Line", "Triangle", "Pentagon"};
        JComboBox<String> shapeMenu = new JComboBox<>(shapeOptions);

        shapeMenu.setUI(new BasicComboBoxUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                super.paint(g, c);
            }

            @Override
            protected JButton createArrowButton() {
                JButton arrowButton = new JButton("v");
                Helper.setColor(arrowButton, Constants.main, Constants.text);
                return arrowButton;
            }
        });

        shapeMenu.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setFont(Constants.controlsFont18);
                label.setBackground(Constants.main);
                label.setForeground(Constants.text);

                return label;
            }
        });

        shapeMenu.setFont(Constants.controlsFont18);
        shapeMenu.setBackground(Constants.main);
        shapeMenu.setForeground(Constants.text);
        shapeMenu.setBorder(BorderFactory.createEmptyBorder());
        shapeMenu.setMaximumSize(new Dimension(200, 30));

        shapeMenu.addActionListener(_ -> {
            int index = shapeMenu.getSelectedIndex();
            currentShapeType = ShapeType.values()[index];
        });

        return shapeMenu;
    }
}