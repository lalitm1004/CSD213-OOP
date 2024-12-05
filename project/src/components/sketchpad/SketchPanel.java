package components.sketchpad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

import utils.Constants;

public class SketchPanel extends JPanel {
    private ArrayList<Shape> shapes = new ArrayList<>();
    private ArrayList<Color> colors = new ArrayList<>();
    private Point startPoint;
    private Point endPoint;

    public SketchPanel() {
        setBackground(Constants.main);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint();
                if (startPoint != null && endPoint != null) {
                    Shape shape = createShape(startPoint, endPoint, SketchMenu.currentShapeType);
                    if (shape != null) {
                        shapes.add(shape);
                        colors.add(Constants.text);
                    }

                    startPoint = null;
                    endPoint = null;
                    repaint();
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endPoint = e.getPoint();
                repaint();
            }
        });
    }

    private Shape createShape(Point start, Point end, SketchMenu.ShapeType type) {
        int x1 = Math.min(start.x, end.x);
        int y1 = Math.min(start.y, end.y);
        int x2 = Math.max(start.x, end.x);
        int y2 = Math.max(start.y, end.y);
        int width = x2 - x1;
        int height = y2 - y1;

        switch (type) {
            case RECTANGLE:
                return new Rectangle(x1, y1, width, height);
            case OVAL:
                return new Ellipse2D.Double(x1, y1, width, height);
            case LINE:
                return new Line2D.Double(start, end);
            case TRIANGLE:
                return createTriangle(start, end);
            case PENTAGON:
                return createPentagon(start, end);
        }

        return null;
    }

    private Polygon createTriangle(Point start, Point end) {
        int[] xPoints = {start.x, end.x, (start.x + end.x) / 2};
        int[] yPoints = {end.y, end.y, start.y};
        return new Polygon(xPoints, yPoints, 3);
    }

    private Polygon createPentagon(Point start, Point end) {
        int centerX = (start.x + end.x) / 2;
        int centerY = (start.y + end.y) / 2;
        int radius = Math.min(end.x - start.x, end.y - start.y) / 2;
        int[] xPoints = new int[5];
        int[] yPoints = new int[5];

        for (int i = 0; i < 5; i++) {
            double angle = Math.toRadians(72 * i - 90);
            xPoints[i] = (int) (centerX + radius * Math.cos(angle));
            yPoints[i] = (int) (centerY + radius * Math.sin(angle));
        }
        return new Polygon(xPoints, yPoints, 5);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i < shapes.size(); i++) {
            g2.setColor(colors.get(i));
            g2.draw(shapes.get(i));
        }

        if (startPoint != null && endPoint != null) {
            g2.setColor(Constants.accent);
            Shape preview = createShape(startPoint, endPoint, SketchMenu.currentShapeType);
            if (preview != null) {
                g2.draw(preview);
            }
        }
    }
}