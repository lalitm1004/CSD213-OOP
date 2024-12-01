package utils;

import java.awt.Color;
import javax.swing.JComponent;

public class Helper {
    public static void setColor(
        JComponent component,
        Color backgroundColor,
        Color foregroundColor
    ) {
        if (component == null) return;
        if (backgroundColor != null) component.setBackground(backgroundColor);
        if (foregroundColor != null) component.setForeground(foregroundColor);
    }
}
