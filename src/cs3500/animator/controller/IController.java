package cs3500.animator.controller;

import cs3500.animator.model.Shape;

/**
 * Represents a Controller for this animation. Controls the information passed from the IModel to
 * the IView and renders images.
 */
public interface IController {

  /**
   * Runs the controller that will make the view render.
   */
  void goView();

  /**
   * Runs the EditView version of the IView.
   *
   * @throws UnsupportedOperationException if edit is called on the wrong type of IView
   */
  void edit() throws UnsupportedOperationException;

  /**
   * Creates a default IShape from the given Shape and name and adds it to the IController's
   * IModel.
   *
   * @param name      String representing the unique id for an IShape
   * @param shapeType Shape type representing the type of IShape want to add
   * @throws if the given name is already used by another shape
   */
  void addShape(String name, Shape shapeType) throws IllegalArgumentException;

  /**
   * Removes the given IShape name from the IController's IModel, if the IShape name and Shape type
   * match.
   *
   * @param name      String representing the unique id for an IShape
   * @param shapeType Shape type representing the type of IShape want to delete
   * @throws if the given name does not exist
   */
  void deleteShape(String name, Shape shapeType) throws IllegalArgumentException;

  /**
   * Adds a key frame to the IShape, that corresponds to the given name, at the given time.
   *
   * @param name String representing the unique id for an IShape
   * @param time integer representing the time that keyframe should be added to
   */
  void addKeyFrame(String name, int time) throws IllegalArgumentException;

  /**
   * Deletes a key frame to the IShape with the given name at the given time.
   *
   * @param name String representing the unique id for an IShape
   * @param time integer representing the time that keyframe should be added to
   */
  void deleteKeyFrame(String name, int time) throws IllegalArgumentException;

  /**
   * Modifies the key frame for the IShape with the given name at the given time to have the follow-
   * ing characteristics.
   *
   * @param name String representing the unique id for an IShape
   * @param t    integer representing the time of the key frame want to modify
   * @param x    integer representing the x location for the IShape
   * @param y    integer representing the y location for the IShape
   * @param w    integer representing the width value for the IShape boundary box
   * @param h    integer representing the height value for the IShape boundary box
   * @param r    integer representing the red value for the IShape's color
   * @param g    integer representing the green value for the IShape's color
   * @param b    integer representing the blue value for the IShape's color
   */
  void modifyKeyFrame(String name, int t, int x, int y, int w, int h, int r, int g, int b)
          throws IllegalArgumentException;
}
