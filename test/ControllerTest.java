import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.HashMap;

import cs3500.animator.controller.Controller;
import cs3500.animator.controller.IController;
import cs3500.animator.model.Dimension;
import cs3500.animator.model.IModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.ModelImpl;
import cs3500.animator.model.ShapeImpl;
import cs3500.animator.model.Shape;
import cs3500.animator.view.EditView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SvgView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.VisualView;

import static org.junit.Assert.assertEquals;

public class ControllerTest {
  IModel model = new ModelImpl();

  // GO VIEW!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

  // testing edit
  // calling edit on TextView
  @Test (expected = UnsupportedOperationException.class)
  public void testEdit1() {
    IView v = new TextView("", model);
    IController c = new Controller(model ,v);
    c.edit();
  }

  // calling edit on SvgView
  @Test (expected = UnsupportedOperationException.class)
  public void testEdit2() {
    IView v = new SvgView("", 5, model);
    IController c = new Controller(model ,v);
    c.edit();
  }

  // calling edit on VisualView
  @Test (expected = UnsupportedOperationException.class)
  public void testEdit3() {
    IView v = new VisualView(5, model);
    IController c = new Controller(model ,v);
    c.edit();
  }

  // no exception, if called on EditView
  @Test
  public void testEdit4() {
    VisualView v = new VisualView(5, model);
    IView v1 = new EditView(5, v);
    IController c = new Controller(model ,v1);
    c.edit();
  }

  // testing addShape
  @Test
  public void testAddShape() {
    IShape rect = new ShapeImpl(Shape.RECTANGLE, new Dimension(5, 2),
            new Point2D.Double(0, 0), new Color(0, 0, 255));
    model.addShape("R", rect);
    VisualView visual = new VisualView(50, model);
    IView edit = new EditView(50, visual);
    IController c = new Controller(model, edit);

    // BEFORE: one shape for R
    HashMap<String, IShape> hashMap = new HashMap<>();
    hashMap.put("R", rect);
    assertEquals(hashMap, model.getShapes());
    // Adding two shapes
    IShape s = new ShapeImpl(Shape.OVAL, new Dimension(0, 0),
            new Point2D.Double(50, 50), new Color(0, 0, 0));
    c.addShape("O", Shape.OVAL);
    hashMap.put("O", s);

    IShape s1 = new ShapeImpl(Shape.RECTANGLE, new Dimension(0, 0),
            new Point2D.Double(50, 50), new Color(0, 0, 0));
    c.addShape("D", Shape.RECTANGLE);
    hashMap.put("D", s1);

    // checking that they are all in the model
    String[] shapeName = {"R", "O", "D"};
    for (int i = 0; i < hashMap.size(); i++) {
      hashMap.get(shapeName[i]).equals(this.model.getShapes().get(shapeName[i]));
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShape2() {
    IShape rect = new ShapeImpl(Shape.RECTANGLE, new Dimension(5, 2),
            new Point2D.Double(0, 0), new Color(0, 0, 255));
    model.addShape("R", rect);
    VisualView visual = new VisualView(50, model);
    IView edit = new EditView(50, visual);
    IController c = new Controller(model, edit);

    c.addShape("R", Shape.OVAL);
  }

  // testing deleteShape
  @Test
  public void testDeleteShape() {
    IShape rect = new ShapeImpl(Shape.RECTANGLE, new Dimension(5, 2),
            new Point2D.Double(0, 0), new Color(0, 0, 255));
    IShape oval = new ShapeImpl(Shape.OVAL, new Dimension(2, 2),
            new Point2D.Double(0, 0), new Color(255, 0, 0));
    model.addShape("R", rect);
    model.addShape("O", oval);
    VisualView visual = new VisualView(50, model);
    IView edit = new EditView(50, visual);
    IController c = new Controller(model, edit);
    HashMap<String, IShape> hashMap = new HashMap<>();
    hashMap.put("R", rect);
    hashMap.put("O", oval);

    // have 2 shapes
    assertEquals(hashMap, this.model.getShapes());
    c.deleteShape("O", Shape.OVAL);
    hashMap.remove("O");
    // removing 1
    assertEquals(hashMap, this.model.getShapes());
    c.deleteShape("R", Shape.RECTANGLE);
    hashMap.remove("R");
    // removing the other
    assertEquals(hashMap, this.model.getShapes());
    assertEquals(new HashMap<String, IShape>(), this.model.getShapes());
  }

  // id name does not exist
  @Test(expected = IllegalArgumentException.class)
  public void testDeleteShape2() {
    IShape rect = new ShapeImpl(Shape.RECTANGLE, new Dimension(5, 2),
            new Point2D.Double(0, 0), new Color(0, 0, 255));
    IShape oval = new ShapeImpl(Shape.OVAL, new Dimension(2, 2),
            new Point2D.Double(0, 0), new Color(255, 0, 0));
    model.addShape("R", rect);
    model.addShape("O", oval);
    VisualView visual = new VisualView(50, model);
    IView edit = new EditView(50, visual);
    IController c = new Controller(model, edit);
    c.deleteShape("R", Shape.RECTANGLE);
    c.deleteShape("O", Shape.RECTANGLE);
  }

  // id and shape type do not match
  @Test(expected = IllegalArgumentException.class)
  public void testDeleteShape3() {
    IShape rect = new ShapeImpl(Shape.RECTANGLE, new Dimension(5, 2),
            new Point2D.Double(0, 0), new Color(0, 0, 255));
    IShape oval = new ShapeImpl(Shape.OVAL, new Dimension(2, 2),
            new Point2D.Double(0, 0), new Color(255, 0, 0));
    model.addShape("R", rect);
    model.addShape("O", oval);
    VisualView visual = new VisualView(50, model);
    IView edit = new EditView(50, visual);
    IController c = new Controller(model, edit);
    c.deleteShape("R", Shape.OVAL);
  }

  // testing addKeyframe
  //************************INSERT***********************
  // id does not exist
  @Test(expected = IllegalArgumentException.class)
  public void testAddKF2() {
    IShape rect = new ShapeImpl(Shape.RECTANGLE, new Dimension(5, 2),
            new Point2D.Double(0, 0), new Color(0, 0, 255));
    IShape oval = new ShapeImpl(Shape.OVAL, new Dimension(2, 2),
            new Point2D.Double(0, 0), new Color(255, 0, 0));
    model.addShape("R", rect);
    model.addShape("O", oval);
    VisualView visual = new VisualView(50, model);
    IView edit = new EditView(50, visual);
    IController c = new Controller(model, edit);

    c.addKeyFrame("C", 5);
  }

  // testing deleteKeyframe
  //************************INSERT***********************
  @Test(expected = IllegalArgumentException.class)
  public void testDeleteKF2() {
    IShape rect = new ShapeImpl(Shape.RECTANGLE, new Dimension(5, 2),
            new Point2D.Double(0, 0), new Color(0, 0, 255));
    IShape oval = new ShapeImpl(Shape.OVAL, new Dimension(2, 2),
            new Point2D.Double(0, 0), new Color(255, 0, 0));
    model.addShape("R", rect);
    model.addShape("O", oval);
    VisualView visual = new VisualView(50, model);
    IView edit = new EditView(50, visual);
    IController c = new Controller(model, edit);

    c.deleteKeyFrame("C", 5);
  }

  // testing modifyKeyframe
  //************************INSERT***********************
  @Test(expected = IllegalArgumentException.class)
  public void testModifyKF2() {
    IShape rect = new ShapeImpl(Shape.RECTANGLE, new Dimension(5, 2),
            new Point2D.Double(0, 0), new Color(0, 0, 255));
    IShape oval = new ShapeImpl(Shape.OVAL, new Dimension(2, 2),
            new Point2D.Double(0, 0), new Color(255, 0, 0));
    model.addShape("R", rect);
    model.addShape("O", oval);
    VisualView visual = new VisualView(50, model);
    IView edit = new EditView(50, visual);
    IController c = new Controller(model, edit);

    c.modifyKeyFrame("C", 5, 2, 2, 4, 33, 0, 0, 34);
  }
}