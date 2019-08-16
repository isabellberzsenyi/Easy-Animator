import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import cs3500.animator.controller.Controller;
import cs3500.animator.controller.IController;
import cs3500.animator.model.Dimension;
import cs3500.animator.model.Shape;
import cs3500.animator.model.IModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.ModelImpl;
import cs3500.animator.model.ShapeImpl;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Holds all teh tests for TextView.
 */
public class TextViewTest {
  IShape rect = new ShapeImpl(Shape.RECTANGLE, new Dimension(5, 2),
          new Point2D.Double(0, 0), new Color(0, 0, 255));
  IShape oval = new ShapeImpl(Shape.OVAL, new Dimension(2, 2),
          new Point2D.Double(0, 0), new Color(255, 0, 0));


  // testing render
  // file exists and can be read
  // empty model
  @Test
  public void testRender1() {
    IModel m = new ModelImpl();
    IView v1 = new TextView("iz.txt", m);
    v1.render(m);
    File file = new File("iz.txt");
    IController c = new Controller(m, v1);
    assertTrue(file.exists());
    assertTrue(file.canRead());

    String text = "";
    try {
      text = new String(Files.readAllBytes(Paths.get("iz.txt")), "UTF-8");
    } catch (IOException i) {
      i.printStackTrace();
    }

    assertEquals("", text);
  }

  @Test
  public void testRender2() {
    IModel m = new ModelImpl();
    m.addShape("R", rect);
    m.addShape("C", oval);
    IView v1 = new TextView("iz.txt", m);
    v1.render(m);
    File file = new File("iz.txt");

    String text = "";
    try {
      text = new String(Files.readAllBytes(Paths.get("iz.txt")), "UTF-8");
    } catch (IOException i) {
      i.printStackTrace();
    }

    assertEquals("shape R RECTANGLE\n\n\nshape C OVAL\n\n\n", text);
  }

  @Test (expected = UnsupportedOperationException.class)
  public void testEdit() {
    IModel m = new ModelImpl();
    IView v = new TextView("", m);
    IController c = new Controller(m, v);
    v.edit(c);
  }
}