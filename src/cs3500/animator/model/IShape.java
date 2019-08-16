package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs3500.animator.view.IReadOnlyShape;

/**
 * Represents a Shape in an IModel, animation.
 */
public interface IShape extends IReadOnlyShape {

  /**
   * Prints the enumerated type of this Shape.
   *
   * @return Shape, one of RECTANGLE, OVAL
   */
  String printType();

  /**
   * Returns the boundary box dimensions for this Shape.
   *
   * @return a Dimension, containing the width and height of the shape
   */
  Dimension getDimension();

  /**
   * Returns the starting location of this Shape.
   *
   * @return a Point2D, containing the x and y coordinates
   */
  Point2D getStartLocation();

  /**
   * Returns the color of this Shape.
   *
   * @return a Color, containing the r, g and b values
   */
  Color getColor();

  /**
   * Sets the location of this IShape to the given Point2D.
   *
   * @param point2D represents the new location of this IShape
   */
  void setLocation(Point2D point2D);

  /**
   * Sets the Color of this IShape to the given Color.
   *
   * @param color represents the new color of this IShape
   */
  void setColor(Color color);

  /**
   * Sets the Dimension of this IShape to the given Dimension.
   *
   * @param dimension represents the new Dimension of this IShape
   */
  void setDimension(Dimension dimension);
}
