package cs3500.animator.view;

import java.util.ArrayList;
import java.util.HashMap;

import cs3500.animator.model.IMotion;
import cs3500.animator.model.IShape;
import javafx.util.Pair;

/**
 * Represents an IViewModel that gives information about the IModel, without mutating it, to the
 * IView.
 */
public interface IViewModel {

  /**
   * Gets the width of the canvas.
   *
   * @return the integer representing the width of the IModel's canvas
   */
  int getW();

  /**
   * Gets the height of the canvas.
   *
   * @return the integer representing the height of the IModel's canvas
   */
  int getH();

  /**
   * Gets the x location of the canvas.
   *
   * @return the integer representing the x location of the IModel's canvas
   */
  int getX();

  /**
   * Gets the y location of the canvas.
   *
   * @return the integer representing the y of the IModel's canvas
   */
  int getY();

  /**
   * Gets the IModel's map of IShapes and String ids.
   *
   * @return HashMap of IShape and it's id
   */
  HashMap<String, IShape> getShapes();

  /**
   * Gets the IModel's list of id's with their IMotions.
   *
   * @return list of pairs containing the id and list of IMotions
   */
  ArrayList<Pair<String, ArrayList<IMotion>>> getMotions();

  /**
   * Gets the given id's list of IMotions.
   *
   * @param id String id representing name of IShape
   * @return list of IMotions for the given IShape
   */
  ArrayList<IMotion> getMotionList(String id);

  /**
   * Text description of all the IShapes' IMotions.
   *
   * @return String containing all of the IShapes' IMotions
   */
  String textDescription();

  ArrayList<IReadOnlyShape> getShapesAtTick(int tick);

  int animationEndTime();
}
