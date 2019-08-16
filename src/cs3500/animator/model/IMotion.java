package cs3500.animator.model;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Represents a motion, or a change, made to an IShape. Can printMotion, return this IMotion as a
 * String, get the start and end time, and get TickStatus at a certain time. Lastly, have getters
 * for the location, dimension and color at a certain time/tick.
 */
public interface IMotion {

  /**
   * Returns this IMotion in textual description, by printing all of IMotion's fields.
   *
   * @return String containing all of this IMotion's fields, first for start then for end
   */
  String printMotion();

  /**
   * Returns the start time of this IMotion.
   *
   * @return an integer representing this IMotion's start time
   */
  int getStartTime();

  /**
   * Returns the end time of this IMotion.
   *
   * @return an integer representing this IMotion's end time
   */
  int getEndTime();

  /**
   * Returns the Point2D Location at the given integer time.
   *
   * @param time integer representing a tick
   * @return Point2D representing the location at the given tick
   * @throws IllegalArgumentException if the given time is not within this IMotion's duration
   */
  Point2D getLocation(int time) throws IllegalArgumentException;

  /**
   * Returns the Dimension at the given integer time.
   *
   * @param time integer representing a tick
   * @return Dimension representing the dimension at the given tick
   * @throws IllegalArgumentException if the given time is not within this IMotion's duration
   */
  Dimension getDim(int time) throws IllegalArgumentException;

  /**
   * Returns the Color at the given integer time.
   *
   * @param time integer representing a tick
   * @return Color representing the color at the given tick
   * @throws IllegalArgumentException if the given time is not within this IMotion's duration
   */
  Color getColor(int time) throws IllegalArgumentException;
}
