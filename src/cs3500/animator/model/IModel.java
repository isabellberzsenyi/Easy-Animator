package cs3500.animator.model;

import java.util.ArrayList;
import java.util.HashMap;

import cs3500.animator.view.IReadOnlyShape;
import cs3500.animator.view.IViewModel;
import javafx.util.Pair;

/**
 * Represents a simple animation. One IModel is one animation, can add shapes and motions to enhance
 * the animations. In addition, can call textDescription to return this IModel as a String
 * description. Lastly, have getters for getting the IShapes and the list of IMotions in this
 * IModel.
 */
public interface IModel extends IViewModel {

  /**
   * Returns the end time for this IModel animation.
   *
   * @return integer of the last endTime of the last motion
   */
  int animationEndTime();

  /**
   * Adds an IShape to this IModel.
   *
   * @param id    represents the name of the shape, as a String
   * @param shape shape added to this IModel
   * @throws IllegalArgumentException if the given String id is already used
   */
  void addShape(String id, IShape shape) throws IllegalArgumentException;

  /**
   * Removes an IShape of the given string & Shape type, from this Model.
   *
   * @param id    String representing the IShape's unique id
   * @param shape type of IShape
   * @throws IllegalArgumentException if the given String id does not exist or the name and type do
   *                                  not match for an existing shape
   */
  void deleteShape(String id, Shape shape) throws IllegalArgumentException;

  /**
   * Adds an IMotion to this IModel.
   *
   * @param id String representing the unique name of the IShape
   * @param i  IMotion that is desired to be added
   * @throws IllegalArgumentException if the given IMotion overlaps time with pre-existing IMotions,
   *                                  or if the IMotion is not continuous.
   */
  void addMotion(String id, IMotion i) throws IllegalArgumentException;

  /**
   * Adds a KeyFrame for the given IShape name at the given time.
   *
   * @param name String representing the unique name of the IShape
   * @param t    integer time of KeyFrame want to add
   * @throws IllegalArgumentException if the IShape does not exist
   */
  void addKeyframe(String name, int t) throws IllegalArgumentException;

  /**
   * Deletes a KeyFrame for the given IShape at the given time.
   *
   * @param name String representing the unique name of the IShape
   * @param t    integer time of KeyFrame wish to delete
   * @throws IllegalArgumentException if the IShape does not exist
   */
  void deleteKeyframe(String name, int t) throws IllegalArgumentException;

  /**
   * Modifies the KeyFrame for the given IShape at the given time, to the given characteristics.
   *
   * @param name String representing the unique name of the IShape
   * @param t    integer time of KeyFrame
   * @param x    integer representing x location desired
   * @param y    integer representing y location desired
   * @param w    integer representing width of boundary box desired
   * @param h    integer representing height of boundary box desired
   * @param r    integer representing r value of color desired
   * @param g    integer representing g value of color desired
   * @param b    integer representing b value of color desired
   * @throws IllegalArgumentException if the IShape does not exit
   */
  void modifyKeyFrame(String name, int t, int x, int y, int w, int h, int r, int g, int b)
          throws IllegalArgumentException;

  /**
   * Creates a textual representation of this IModel, with each IShape printed with all the
   * corresponding motions.
   *
   * @return a String contain the IModel representation
   */
  String textDescription();

  /**
   * Returns a copy of the HashMap that contains an IShape's string id and the IShape. This is used
   * for testing purposes, to test addShape.
   *
   * @returnn copy of the HashMap that contains an IShape's string id and the IShape
   */
  HashMap<String, IShape> getShapes();

  /**
   * Returns a copy of the ArrayList that contains pairs of IShape's ids with their corresponding
   * list of motions. This is used for testing purposes, to test addMotion.
   *
   * @returnn copy of the ArrayList that contains pairs of IShape's ids with their list of motions
   */
  ArrayList<Pair<String, ArrayList<IMotion>>> getMotions();

  /**
   * Returns the motions ArrayList for a specific shape.
   *
   * @return a copy of ArrayList<> that is the list of motions for a specific String id
   */
  ArrayList<IMotion> getMotionList(String id);

  /**
   * Tweening method to get what each shape looks like at the given tick.
   *
   * @param tick unitless tick in the IModel.
   * @return a list of IReadOnlyShapes that represent how they appear at the given tick
   */
  ArrayList<IReadOnlyShape> getShapesAtTick(int tick);

  /**
   * Sets the X location of the boundary box to the given value.
   *
   * @param x represents the X location of the boundary box
   */
  void setX(int x) throws IllegalArgumentException;

  /**
   * Sets the Y location of the boundary box to the given value.
   *
   * @param y represents the Y location of the boundary box
   */
  void setY(int y) throws IllegalArgumentException;

  /**
   * Sets the Height of the boundary box to the given value.
   *
   * @param h represents the height of the boundary box
   */
  void setH(int h) throws IllegalArgumentException;

  /**
   * Sets the Width of the boundary box to the given value.
   *
   * @param w represents the width of the boundary box
   */
  void setW(int w) throws IllegalArgumentException;

  /**
   * Returns the x coordinate of the boundary box.
   *
   * @return x coordinate of the boundary box
   */
  int getX();

  /**
   * Returns the y coordinate of the boundary box.
   *
   * @return y coordinate of the boundary box
   */
  int getY();

  /**
   * Returns the height of the boundary box.
   *
   * @return height of the boundary box
   */
  int getH();

  /**
   * Returns the width of the boundary box.
   *
   * @return width of the boundary box
   */
  int getW();

}
