package cs3500.animator.view;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Represents an object of IDrawingPanel, extending JPanel, to draw shapes for an animation.
 */
public class DrawingPanel extends JPanel implements IDrawingPanel {
  ArrayList<IReadOnlyShape> shapes = null;

  /**
   * Public constructor for DrawPanel, using super constructor.
   */
  public DrawingPanel() {
    super();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (shapes != null) {
      for (IReadOnlyShape s : shapes) {
        g.setColor(s.getColor());
        switch (s.getShape()) {
          case RECTANGLE:
            g.fillRect((int) s.getStartLocation().getX(), (int) s.getStartLocation().getY(),
                    (int) s.getDimension().getWidth(), (int) s.getDimension().getHeight());
            break;
          case OVAL:
            g.fillOval((int) s.getStartLocation().getX(), (int) s.getStartLocation().getY(),
                    (int) s.getDimension().getWidth(), (int) s.getDimension().getHeight());
            break;
          default:
            throw new IllegalArgumentException("Bad shape");
        }
      }
    }
  }

  @Override
  public void draw(ArrayList<IReadOnlyShape> shapes) {
    this.shapes = shapes;
    repaint();
  }
}
