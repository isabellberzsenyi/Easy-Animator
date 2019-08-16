package cs3500.animator.model;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Represents an IMotion that contains a Motion's start and end value of: time, location, dimension,
 * color and this IMotion's list of types of motion.
 */
public class MotionImpl implements IMotion {
  private int startTime;
  private int endTime;
  private Point2D startPoint;
  private Point2D endPoint;
  private Dimension startDimension;
  private Dimension endDimension;
  private Color startColor;
  private Color endColor;

  /**
   * Public constructor of MotionImpl.
   *
   * @param startTime      integer representing this IMotion's start tick
   * @param endTime        integer representing this IMotion's end tick
   * @param startPoint     Point2D representing this IMotion's start point
   * @param endPoint       Point2D representing this IMotion's end point
   * @param startDimension Dimension representing this IMotion's start dimension
   * @param endDimension   Dimension representing this IMotion's end dimension
   * @param startColor     Color representing this IMotion's start color
   * @param endColor       Color representing this IMotion's end color
   * @throws IllegalArgumentException if startTime is
   */
  public MotionImpl(int startTime, int endTime, Point2D startPoint, Point2D endPoint,
                    Dimension startDimension, Dimension endDimension, Color startColor,
                    Color endColor) throws IllegalArgumentException {
    if (startTime >= 0) {
      this.startTime = startTime;
    } else {
      throw new IllegalArgumentException("Start time cannot be negative");
    }
    if (endTime >= startTime) {
      this.endTime = endTime;
    } else {
      throw new IllegalArgumentException("End time must be greater than start time.");
    }
    this.startPoint = startPoint;
    this.endPoint = endPoint;
    this.startDimension = startDimension;
    this.endDimension = endDimension;
    this.startColor = startColor;
    this.endColor = endColor;
  }

  @Override
  public String printMotion() {
    StringBuilder sb = new StringBuilder();
    sb.append(startTime + " " + startPoint.getX() + " " + startPoint.getY() + " " +
            startDimension.getWidth() + " " + startDimension.getHeight() + " " +
            startColor.getRed() + " " + startColor.getGreen() + " " + startColor.getBlue() +
            "     " + endTime + " " + endPoint.getX() + " " +
            endPoint.getY() + " " + endDimension.getWidth() + " " + endDimension.getHeight() + " " +
            endColor.getRed() + " " + endColor.getGreen() + " " + endColor.getBlue() + "\n");

    return sb.toString();
  }

  @Override
  public int getStartTime() {
    return this.startTime;
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }

  @Override
  public Point2D getLocation(int time) throws IllegalArgumentException {
    if (time == this.startTime) {
      return new Point2D.Double(this.startPoint.getX(), this.startPoint.getY());
    } else if (time == this.endTime) {
      return new Point2D.Double(this.endPoint.getX(), this.endPoint.getY());
    } else if (this.startTime < time && time < this.endTime) {
      double x = (((time - this.startTime) * (this.endPoint.getX() - this.startPoint.getX()))
              / (this.endTime - this.startTime)) + this.startPoint.getX();
      double y = (((time - this.startTime) * (this.endPoint.getY() - this.startPoint.getY()))
              / (this.endTime - this.startTime)) + this.startPoint.getY();
      return new Point2D.Double((int) x, (int) y);
    } else {
      throw new IllegalArgumentException("Time is not within this motion");
    }
  }

  @Override
  public Dimension getDim(int time) throws IllegalArgumentException {
    if (time == this.startTime) {
      return new Dimension(this.startDimension.getWidth(), this.startDimension.getHeight());
    } else if (time == this.endTime) {
      return new Dimension(this.endDimension.getWidth(), this.endDimension.getHeight());
    } else if (this.startTime < time && time < this.endTime) {
      double w = (((time - this.startTime) * (this.endDimension.getWidth() -
              this.endDimension.getWidth())) / (this.endTime - this.startTime))
              + this.startDimension.getWidth();
      double h = (((time - this.startTime) * (this.endDimension.getHeight() -
              this.endDimension.getHeight())) / (this.endTime - this.startTime))
              + this.startDimension.getHeight();
      return new Dimension((int) w, (int) h);
    } else {
      throw new IllegalArgumentException("Time is not within this motion");
    }
  }

  @Override
  public Color getColor(int time) throws IllegalArgumentException {
    if (time == this.startTime) {
      return new Color(this.startColor.getRed(), this.startColor.getGreen(),
              this.startColor.getBlue());
    } else if (time == this.endTime) {
      return new Color(this.endColor.getRed(), this.endColor.getGreen(), this.endColor.getBlue());
    } else if (this.startTime < time && time < this.endTime) {
      double r = (((this.endColor.getRed() - this.startColor.getRed()) * (time - startTime))
              / (this.endTime - this.startTime)) + (this.startColor.getRed());
      double g = (((this.endColor.getGreen() - this.startColor.getGreen()) * (time - startTime))
              / (this.endTime - this.startTime)) + (this.startColor.getGreen());
      double b = (((this.endColor.getBlue() - this.startColor.getBlue()) * (time - startTime))
              / (this.endTime - this.startTime)) + (this.startColor.getBlue());
      return new Color((int) r, (int) g, (int) b);
    } else {
      throw new IllegalArgumentException("Time is not within this motion");
    }
  }

}
