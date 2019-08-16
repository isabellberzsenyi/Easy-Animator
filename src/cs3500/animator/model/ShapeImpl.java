package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Represents an IShape, containing a type of Shape, a Dimension for boundary box, a Point2D for
 * starting location, a Color representing color of this IShape, and start and end time.
 */
public class ShapeImpl implements IShape {
  private final Shape type;
  private Dimension dimension;
  private Point2D startLocation;
  private Color color;

  /**
   * Public constructor for a ShapeImpl that takes in a Shape, Dimension, Point2D, Color, int and
   * int.
   *
   * @param type          type of Shape (one of RECTANGLE and OVAL)
   * @param dimension     Dimension representing the dimension of this IShape
   * @param startLocation Point2D representing start location of this IShape
   * @param color         Color representing the color of this IShape
   * @throws IllegalArgumentException if startTime or endTime are negative, or if endTime is smaller
   *                                  than startTime
   */
  public ShapeImpl(Shape type, Dimension dimension, Point2D startLocation,
                   Color color) throws IllegalArgumentException {
    this.type = type;
    this.dimension = dimension;
    this.startLocation = startLocation;
    this.color = color;

  }

  @Override
  public String printType() {
    return this.type.toString();
  }

  @Override
  public Dimension getDimension() {
    return new Dimension(this.dimension.getWidth(), this.dimension.getHeight());
  }

  @Override
  public Point2D getStartLocation() {
    return new Point2D.Double(this.startLocation.getX(), this.startLocation.getY());
  }

  @Override
  public Color getColor() {
    int r = this.color.getRed();
    int g = this.color.getGreen();
    int b = this.color.getBlue();
    return new Color(r, g, b);
  }

  @Override
  public Shape getShape() {
    return this.type;
  }

  @Override
  public void setLocation(Point2D point2D) {
    this.startLocation = point2D;
  }

  @Override
  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public void setDimension(Dimension dimension) {
    this.dimension = dimension;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ShapeImpl)) {
      return false;
    }
    ShapeImpl that = (ShapeImpl) o;
    return that.dimension.equals(this.dimension)
            && that.color.equals(this.color)
            && that.startLocation.equals(this.startLocation)
            && that.type.equals(this.type);
  }

  @Override
  public int hashCode() {
    return this.type.hashCode() + this.startLocation.hashCode() + this.color.hashCode() +
            this.dimension.hashCode();
  }
}
