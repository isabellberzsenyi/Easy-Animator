import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import cs3500.animator.controller.Controller;
import cs3500.animator.controller.IController;
import cs3500.animator.model.Dimension;
import cs3500.animator.model.IModel;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IShape;
import cs3500.animator.model.ModelImpl;
import cs3500.animator.model.MotionImpl;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeImpl;
import cs3500.animator.view.IView;
import cs3500.animator.view.SvgView;
import cs3500.animator.view.TextView;

/**
 * Tests for SVG.
 */
public class TestSVGView {

  @Test
  public void testCreateSvg() {
    IModel m = new ModelImpl();
    IShape rect = new ShapeImpl(Shape.RECTANGLE, new Dimension(5, 2),
            new Point2D.Double(0, 0), new Color(0, 0, 255));
    IMotion motion1 = new MotionImpl(0, 100, new Point2D.Double(0, 0),
            new Point2D.Double(6, 7), new Dimension(7, 9), new Dimension(
            7, 9),
            new Color(0, 0, 255), new Color(0, 0, 255));
    m.addShape("R", rect);
    m.addMotion("R", motion1);
    SvgView v = new SvgView("out.svg", 10, m);
    File file = new File("out.svg");
    IController c = new Controller(m, v);

    assertTrue(file.exists());
  }


  @Test
  public void testOval() {
    IShape oval = new ShapeImpl(Shape.OVAL, new Dimension(5, 2),
            new Point2D.Double(0, 0), new Color(0, 0, 255));
    IMotion motion1 = new MotionImpl(0, 100, new Point2D.Double(0, 0),
            new Point2D.Double(6, 7), new Dimension(7, 9), new Dimension(
            7, 9),
            new Color(0, 0, 255), new Color(0, 0, 255));
    File file = new File("out.svg");
    IModel m = new ModelImpl();
    SvgView v = new SvgView("out.svg", 10, m);
    m.addShape("O", oval);
    m.addMotion("O", motion1);
    IController c = new Controller(m, v);
    c.goView();


    assertTrue(file.exists());

    String text = "";
    try {
      text = new String(Files.readAllBytes(Paths.get("out.svg")), "UTF-8");
    } catch (IOException i) {
      i.printStackTrace();
    }

    assertEquals("<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n" +
            "xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<ellipse id=\"O\" cx=\"0.0\" cy=\"0.0\" rx=\"5.0\" ry=\"2.0\" fill=\"rgb(0, 0, 255)\""
            +
            " visibility=\"visible\" >\n" +
            "<animate attributeName=\"x\" attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\"" +
            " fill=\"" +
            "freeze\" from=\"0.0\" to=\"6.0\" />\n" +
            "<animate attributeName=\"y\" attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" " +
            "fill=\"freeze\" from=\"0.0\" to=\"7.0\" />\n" +
            "<animate attributeName=\"width\" attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" " +
            "" +
            "fill=\"freeze\" from=\"7.0\" to=\"7.0\" />\n" +
            "<animate attributeName=\"height\" attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\"" +
            " " +
            "fill=\"freeze\" from=\"9.0\" to=\"9.0\" />\n" +
            "</ellipse>\n" +
            "</svg>", text);
  }

  @Test (expected = UnsupportedOperationException.class)
  public void testEdit() {
    IModel m = new ModelImpl();
    IView v = new SvgView("", 5,  m);
    IController c = new Controller(m, v);
    v.edit(c);
  }
}
