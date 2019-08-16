import org.junit.Test;

import cs3500.animator.model.Dimension;

import static org.junit.Assert.assertEquals;

/**
 * This class holds all the tests for Dimension. Testing includes: constructor, getWidth &
 * getHeight.
 */
public class DimensionTest {
  Dimension d1 = new Dimension(25, 50);
  Dimension d2 = new Dimension(100, 30);

  // testing constructor
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    new Dimension(-5, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    new Dimension(0, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    new Dimension(50, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4() {
    new Dimension(50, -30);
  }

  // testing getWidth
  @Test
  public void testGetWidth1() {
    assertEquals(25, this.d1.getWidth(), 0.01);
  }

  @Test
  public void testGetWidth2() {
    assertEquals(100, this.d2.getWidth(), 0.01);
  }

  // testing getHeight
  @Test
  public void testGetHeight1() {
    assertEquals(50, this.d1.getHeight(), 0.01);
  }

  @Test
  public void testGetHeight2() {
    assertEquals(30, this.d2.getHeight(), 0.01);
  }
}