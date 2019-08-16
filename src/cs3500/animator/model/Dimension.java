package cs3500.animator.model;

/**
 * Overriding the java class of Dimension. This constricts the ability to have negative values in
 * the Dimension, also the width and height are taken in as doubles.
 */
public class Dimension extends java.awt.Dimension {
  private final double width;
  private final double height;

  /**
   * Public constructor of Dimension that takes in non-negative width and height.
   *
   * @param width  width of given dimension as a double
   * @param height height of given dimension as a double
   * @throws IllegalArgumentException if width or height are equal to or less than 0
   */
  public Dimension(double width, double height) throws IllegalArgumentException {
    this.width = width;
    this.height = height;
  }

  /**
   * Overriding the java class Dimension getHeight to return the height value.
   *
   * @return the height of this Dimension as a double
   */
  @Override
  public double getHeight() {
    return this.height;
  }

  /**
   * Overriding the java class Dimension getWidth to return the width value.
   *
   * @return the width of this Dimension as a double
   */
  @Override
  public double getWidth() {
    return this.width;
  }
}
