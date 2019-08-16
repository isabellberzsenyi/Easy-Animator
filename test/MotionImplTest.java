import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs3500.animator.model.Dimension;
import cs3500.animator.model.MotionImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class holds all the tests for MotionImpl. This includes testing of: constructor,
 * printMotion, getStartTime, getEndTime, getTickStatus, getLocation, getColor & getDim.
 */
public class MotionImplTest {
  MotionImpl m1 = new MotionImpl(0, 500, new Point2D.Double(0, 0),
          new Point2D.Double(5, 5), new Dimension(5, 5),
          new Dimension(5, 5), new Color(0, 0, 255),
          new Color(0, 0, 255));
  MotionImpl m2 = new MotionImpl(60, 71, new Point2D.Double(5, 5),
          new Point2D.Double(0, 6), new Dimension(9, 9),
          new Dimension(5, 5), new Color(43, 0, 255),
          new Color(0, 72, 255));
  MotionImpl m3 = new MotionImpl(100, 125, new Point2D.Double(5, 5),
          new Point2D.Double(5, 5), new Dimension(9, 9),
          new Dimension(5, 5), new Color(5, 0, 255),
          new Color(0, 8, 255));

  // test constructor
  // bad startTime
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    new MotionImpl(-2, 3, new Point2D.Double(0, 0),
            new Point2D.Double(5, 5), new Dimension(5, 5),
            new Dimension(6, 6), new Color(0, 0, 255),
            new Color(0, 0, 0));
  }

  // bad endTime
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    new MotionImpl(6, 3, new Point2D.Double(2, 2),
            new Point2D.Double(5, 5), new Dimension(5, 5),
            new Dimension(6, 6), new Color(255, 0, 255),
            new Color(0, 0, 0));
  }

  // bad Color
  // bad r
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor7() {
    new MotionImpl(0, 50, new Point2D.Double(0, 0),
            new Point2D.Double(0, 0), new Dimension(5, 5),
            new Dimension(5, 5), new Color(-1, 0, 255),
            new Color(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor8() {
    new MotionImpl(0, 50, new Point2D.Double(0, 0),
            new Point2D.Double(0, 0), new Dimension(5, 5),
            new Dimension(5, 5), new Color(77, 0, 255),
            new Color(260, 0, 0));
  }

  // bad g
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor9() {
    new MotionImpl(0, 50, new Point2D.Double(0, 0),
            new Point2D.Double(0, 0), new Dimension(5, 5),
            new Dimension(5, 5), new Color(99, 0, 255),
            new Color(0, 300, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor10() {
    new MotionImpl(0, 50, new Point2D.Double(0, 0),
            new Point2D.Double(0, 0), new Dimension(5, 5),
            new Dimension(5, 5), new Color(11, -9, 255),
            new Color(0, 0, 0));
  }

  // bad b
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor11() {
    new MotionImpl(0, 50, new Point2D.Double(0, 0),
            new Point2D.Double(0, 0), new Dimension(5, 5),
            new Dimension(5, 5), new Color(99, 0, -9),
            new Color(0, 300, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor12() {
    new MotionImpl(0, 50, new Point2D.Double(0, 0),
            new Point2D.Double(0, 0), new Dimension(5, 5),
            new Dimension(5, 5), new Color(11, 12, 255),
            new Color(0, 0, 400));
  }

  // test printMotion
  @Test
  public void testPrintMotion() {
    assertEquals("0 0.0 0.0 5.0 5.0 0 0 255     500 5.0 5.0 5.0 5.0 0 0 255\n",
            this.m1.printMotion());
    assertEquals("60 5.0 5.0 9.0 9.0 43 0 255     71 0.0 6.0 5.0 5.0 0 72 255\n",
            this.m2.printMotion());
    assertEquals("100 5.0 5.0 9.0 9.0 5 0 255     125 5.0 5.0 5.0 5.0 0 8 255\n",
            this.m3.printMotion());
  }

  // test getStartTime
  @Test
  public void testGetStartTime() {
    assertEquals(0, this.m1.getStartTime());
    assertEquals(60, this.m2.getStartTime());
    assertEquals(100, this.m3.getStartTime());
  }

  // test getEndTime
  @Test
  public void testGetEndTime() {
    assertEquals(500, this.m1.getEndTime());
    assertEquals(71, this.m2.getEndTime());
    assertEquals(125, this.m3.getEndTime());
  }

  // test getLocation
  @Test(expected = IllegalArgumentException.class)
  public void testGetLoc() {
    this.m2.getLocation(99);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetLoc1() {
    this.m3.getLocation(99);
  }

  @Test
  public void testGetLoc2() {
    assertEquals(new Point2D.Double(5, 5), this.m2.getLocation(60));
    assertEquals(new Point2D.Double(2, 5), this.m2.getLocation(65));
    assertEquals(new Point2D.Double(0, 6), this.m2.getLocation(71));
  }

  @Test
  public void testGetLoc3() {
    assertEquals(new Point2D.Double(5, 5), this.m3.getLocation(100));
    assertEquals(new Point2D.Double(5, 5), this.m3.getLocation(110));
    assertEquals(new Point2D.Double(5, 5), this.m3.getLocation(125));
  }

  // test getDim
  @Test(expected = IllegalArgumentException.class)
  public void testGetDim1() {
    this.m3.getDim(150);
  }

  @Test
  public void testGetDim2() {
    assertEquals(new Dimension(9, 9), this.m2.getDim(60));
    assertEquals(new Dimension(7, 7), this.m2.getDim(65));
    assertEquals(new Dimension(5, 5), this.m2.getDim(71));
  }

  @Test
  public void testGetDim3() {
    // start
    assertEquals(new Dimension(9, 9), this.m3.getDim(100));
    // middle
    assertEquals(new Dimension(7, 7), this.m3.getDim(110));
    // end
    assertEquals(new Dimension(5, 5), this.m3.getDim(125));
  }

  // test getColor
  @Test(expected = IllegalArgumentException.class)
  public void testGetColor1() {
    this.m1.getColor(555);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetColor2() {
    this.m2.getColor(59);
  }

  @Test
  public void testGetColor3() {
    // startTime
    assertEquals(new Color(43, 0, 255), this.m2.getColor(60));
    // endTime
    assertEquals(new Color(0, 72, 255), this.m2.getColor(71));
    // middle
    assertEquals(new Color(24, 32, 255), this.m2.getColor(65));
  }

  @Test
  public void testGetColor4() {
    // startTime
    assertEquals(new Color(5, 0, 255), this.m3.getColor(100));
    // middle
    assertEquals(new Color(3, 3, 255), this.m3.getColor(110));
    // endTime
    assertEquals(new Color(0, 8, 255), this.m3.getColor(125));
  }
}
