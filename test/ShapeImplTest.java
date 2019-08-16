import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs3500.animator.model.Dimension;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class holds all the tests for ShapeImpl. Testing includes: constructor, getTickStatus,
 * printType, getDimension, getStartLocation, getColor, getStartTime & getEndTime.
 */
public class ShapeImplTest {
  IShape rect = new ShapeImpl(Shape.RECTANGLE, new Dimension(5, 5),
          new Point2D.Double(-2, 0),
          new Color(0, 0, 255));

  IShape a = new ShapeImpl(Shape.OVAL, new Dimension(7, 13),
          new Point2D.Double(0, -1),
          new Color(1, 1, 255));

  IShape b = new ShapeImpl(Shape.OVAL, new Dimension(50, 50),
          new Point2D.Double(5, 7),
          new Color(1, 1, 255));

  // r < 0
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShape2() {
    new ShapeImpl(Shape.RECTANGLE, new Dimension(13, 2),
            new Point2D.Double(-3, 0),
            new Color(-1, 1, 255));
  }

  // g < 0
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShape3() {
    new ShapeImpl(Shape.RECTANGLE, new Dimension(13, 2),
            new Point2D.Double(-3, 0),
            new Color(3, -1, 255));
  }

  // b < 0
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShape5() {
    new ShapeImpl(Shape.RECTANGLE, new Dimension(13, 2),
            new Point2D.Double(-3, 0),
            new Color(0, 1, -255));
  }

  // r < 0 && g < 0
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShape6() {
    new ShapeImpl(Shape.RECTANGLE, new Dimension(13, 2),
            new Point2D.Double(-3, 0),
            new Color(-1, -2, 255));
  }

  // r < 0 && b < 0
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShape7() {
    new ShapeImpl(Shape.RECTANGLE, new Dimension(13, 2),
            new Point2D.Double(-3, 0),
            new Color(-7, 1, -255));
  }

  // g < 0 && b < 0
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShape8() {
    new ShapeImpl(Shape.RECTANGLE, new Dimension(13, 2),
            new Point2D.Double(-3, 0),
            new Color(0, -8, -205));
  }

  // all negatives
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShape4() {
    new ShapeImpl(Shape.RECTANGLE, new Dimension(13, 2),
            new Point2D.Double(9, 3),
            new Color(-9, -3, -255));
  }

  @Test
  public void testPrintType() {
    assertEquals(this.rect.printType(), Shape.RECTANGLE.toString());
    assertEquals(this.a.printType(), Shape.OVAL.toString());
    assertEquals(this.a.printType(), "OVAL");
    assertEquals("RECTANGLE", this.rect.printType());
  }

  @Test
  public void testGetDimension() {
    assertEquals(this.rect.getDimension(),
            new Dimension(5, 5));
    assertEquals(this.a.getDimension(),
            new Dimension(7, 13));
    assertEquals(this.b.getDimension(),
            new Dimension(50, 50));
  }

  @Test
  public void testGetStartLocation() {
    assertEquals(this.rect.getStartLocation(), new Point2D.Double(-2, 0));
    assertEquals(this.a.getStartLocation(), new Point2D.Double(0, -1));
    assertEquals(this.b.getStartLocation(), new Point2D.Double(5, 7));
  }

  @Test
  public void testGetColor() {
    assertEquals(this.rect.getColor(), this.rect.getColor());
    assertEquals(this.rect.getColor(), new Color(0, 0, 255));
    assertEquals(new Color(0, 0, 255), this.rect.getColor());

    assertEquals(this.a.getColor(), this.a.getColor());
    assertEquals(this.a.getColor(), new Color(1, 1, 255));
    assertEquals(new Color(1, 1, 255), this.a.getColor());
  }

  @Test
  public void testSetLocation() {
    // before
    assertEquals(new Point2D.Double(-2, 0), this.rect.getStartLocation());
    // after
    this.rect.setLocation(new Point2D.Double(0, 22));
    assertEquals(new Point2D.Double(0, 22), this.rect.getStartLocation());
  }

  @Test
  public void testSetColor() {
    // before
    assertEquals(this.rect.getColor(), new Color(0, 0, 255));
    // after
    this.rect.setColor(new Color(22, 233, 12));
    assertEquals(this.rect.getColor(), new Color(22, 233, 12));
  }

  @Test
  public void testDim() {
    // before
    assertEquals(this.rect.getDimension(), new Dimension(5, 5));
    this.rect.setDimension(new Dimension(222, 0));
    assertEquals(this.rect.getDimension(), new Dimension(222, 0));
  }

  @Test
  public void testEquals() {
    assertTrue(this.rect.equals(this.rect));
    assertTrue(this.rect.equals(new ShapeImpl(Shape.RECTANGLE, new Dimension(5, 5),
            new Point2D.Double(-2, 0), new Color(0, 0, 255))));
    assertFalse(this.rect.equals(this.b));
  }

  @Test
  public void testHashCode() {
    IShape a = new ShapeImpl(Shape.RECTANGLE, new Dimension(5, 5),
            new Point2D.Double(-2, 0), new Color(0, 0, 255));
    assertEquals(this.rect.hashCode(), this.rect.hashCode());
    assertEquals(this.rect.hashCode(), a.hashCode());
    assertEquals(this.rect.hashCode(), (Shape.RECTANGLE.hashCode() + new Dimension(5, 5).hashCode() +
            new Point2D.Double(-2, 0).hashCode() + new Color(0, 0, 255).hashCode()));
  }
}