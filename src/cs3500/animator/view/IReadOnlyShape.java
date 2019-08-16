package cs3500.animator.view;

import java.awt.*;
import java.awt.geom.Point2D;

import cs3500.animator.model.Dimension;
import cs3500.animator.model.Shape;

/**
 * Interface that allows IDrawingPanel to draw an IShape by giving it getters that give information
 * about an IShape, without mutating it.
 */
public interface IReadOnlyShape {

  /**
   * Gives the Color the IShape.
   *
   * @return the color
   */
  Color getColor();

  /**
   * Gives the type of Shape of this IShape.
   *
   * @return the type of Shape
   */
  Shape getShape();

  /**
   * Gives the Dimensions of the boundary box of this IShape.
   *
   * @return the Dimension
   */
  Dimension getDimension();

  /**
   * Gives the Location of this IShape.
   *
   * @return the Point2D representing the location
   */
  Point2D getStartLocation();

}
