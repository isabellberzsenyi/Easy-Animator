package cs3500.animator.view;

import java.util.ArrayList;

/**
 * Created to draw objects for an animation.
 */
public interface IDrawingPanel {

  /**
   * Draws the given list of IReadOnlyShapes based on their characteristics.
   *
   * @param shapes list of IReadOnlyShapes
   */
  void draw(ArrayList<IReadOnlyShape> shapes);
}
