package cs3500.animator.controller;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs3500.animator.model.Dimension;
import cs3500.animator.model.IModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeImpl;
import cs3500.animator.view.IView;

/**
 * This class is an object of IController. It interacts between the IModel and IView.
 */
public class Controller implements IController {
  IModel model;
  IView view;

  /**
   * Public constructor of Controller, must take in a model and view.
   *
   * @param model IModel that is used for this animation
   * @param view  IView that is used for this animation
   */
  public Controller(IModel model, IView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void goView() {
    this.view.render(this.model);
  }

  @Override
  public void edit() {
    this.view.edit(this);
  }

  @Override
  public void addShape(String name, Shape shapeType) throws IllegalArgumentException {
    IShape s = new ShapeImpl(shapeType, new Dimension(50, 50),
            new Point2D.Double(50, 50), new Color(0, 0, 0));
    this.model.addShape(name, s);
  }

  @Override
  public void deleteShape(String name, Shape shapeType) throws IllegalArgumentException {
    this.model.deleteShape(name, shapeType);
  }

  @Override
  public void addKeyFrame(String name, int time) throws IllegalArgumentException {
    this.model.addKeyframe(name, time);
  }

  @Override
  public void deleteKeyFrame(String name, int time) throws IllegalArgumentException {
    this.model.deleteKeyframe(name, time);
  }

  @Override
  public void modifyKeyFrame(String name, int t, int x, int y, int w, int h, int r, int g, int b)
          throws IllegalArgumentException {
    this.model.modifyKeyFrame(name, t, x, y, w, h, r, g, b);
  }
}
