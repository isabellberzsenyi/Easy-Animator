import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

import cs3500.animator.model.Dimension;
import cs3500.animator.model.IModel;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IShape;
import cs3500.animator.model.ModelImpl;
import cs3500.animator.model.MotionImpl;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeImpl;
import cs3500.animator.view.IReadOnlyShape;
import javafx.util.Pair;

import static org.junit.Assert.assertEquals;

/**
 * This class holds all the tests for ModelImpl. This includes: getMotions, getShapes, getMotionList
 * (please not that getShapes, getMotions and getMotionList are thoroughly tested by using them to
 * test other methods), addShape, addMotion, and compare.
 */
public class ModelImplTest {
  IModel model = new ModelImpl();

  IShape rect = new ShapeImpl(Shape.RECTANGLE, new Dimension(5, 2),
          new Point2D.Double(0, 0), new Color(0, 0, 255));
  IShape oval = new ShapeImpl(Shape.OVAL, new Dimension(2, 2),
          new Point2D.Double(0, 0), new Color(255, 0, 0));
  IMotion motion1 = new MotionImpl(0, 100, new Point2D.Double(0, 0),
          new Point2D.Double(6, 7), new Dimension(7, 9), new Dimension(7, 9),
          new Color(0, 0, 255), new Color(0, 0, 255));
  IMotion motion3 = new MotionImpl(0, 10, new Point2D.Double(0, 0),
          new Point2D.Double(6, 7), new Dimension(2, 2), new Dimension(7, 9),
          new Color(0, 0, 255), new Color(255, 0, 0));
  IMotion motion4 = new MotionImpl(0, 10, new Point2D.Double(0, 0),
          new Point2D.Double(6, 7), new Dimension(5, 2), new Dimension(7, 9),
          new Color(0, 0, 255), new Color(255, 0, 0));

  IMotion motion5 = new MotionImpl(25, 30, new Point2D.Double(0, 0),
          new Point2D.Double(6, 7), new Dimension(7, 9), new Dimension(7, 9),
          new Color(0, 0, 255), new Color(0, 0, 255));

  IMotion motion6 = new MotionImpl(60, 100, new Point2D.Double(0, 0),
          new Point2D.Double(6, 7), new Dimension(7, 9), new Dimension(13, 1),
          new Color(0, 0, 255), new Color(0, 0, 255));

  // testing animationEndTime

  // testing addShape
  @Test
  public void testAddShape() {
    HashMap<String, IShape> hashMap = new HashMap<>();
    model.addShape("Rect", rect);
    hashMap.put("Rect", rect);
    assertEquals(hashMap, this.model.getShapes());
    model.addShape("R", rect);
    hashMap.put("R", rect);
    assertEquals(hashMap, this.model.getShapes());
    model.addShape("O", oval);
    hashMap.put("O", oval);
    assertEquals(hashMap, this.model.getShapes());
    model.addShape("oval", oval);
    hashMap.put("oval", oval);
    assertEquals(hashMap, this.model.getShapes());
  }

  @Test
  public void testAddShape1() {
    HashMap<String, IShape> hashMap = new HashMap<>();
    model.addShape("Rect", rect);
    hashMap.put("Rect", rect);
    assertEquals(hashMap, this.model.getShapes());
    model.addShape("R", rect);
    hashMap.put("R", rect);
    assertEquals(hashMap, this.model.getShapes());
  }

  // id already used
  @Test(expected = IllegalArgumentException.class)
  public void testAddShape2() {
    HashMap<String, IShape> hashMap = new HashMap<>();
    model.addShape("O", oval);
    hashMap.put("O", oval);
    assertEquals(hashMap, this.model.getShapes());
    model.addShape("O", oval);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShape3() {
    HashMap<String, IShape> hashMap = new HashMap<>();
    model.addShape("O", oval);
    hashMap.put("O", oval);
    assertEquals(hashMap, this.model.getShapes());
    model.addShape("O", rect);
  }

  // testing deleteShape
  @Test
  public void testDeleteShape() {
    HashMap<String, IShape> hashMap = new HashMap<>();
    model.addShape("O", oval);
    hashMap.put("O", oval);
    model.addShape("R", rect);
    hashMap.put("R", rect);
    // before
    assertEquals(hashMap, this.model.getShapes());
    model.deleteShape("O", Shape.OVAL);
    hashMap.remove("O");
    assertEquals(hashMap, this.model.getShapes());
    model.deleteShape("R", Shape.RECTANGLE);
    hashMap.remove("R");
    assertEquals(hashMap, this.model.getShapes());
  }

  // shape does not exist
  @Test(expected = IllegalArgumentException.class)
  public void testDeleteShape2() {
    HashMap<String, IShape> hashMap = new HashMap<>();
    model.addShape("O", oval);
    hashMap.put("O", oval);
    model.addShape("O", rect);
    model.deleteShape("C", Shape.RECTANGLE);
  }

  // shape and shapeType do not match
  @Test(expected = IllegalArgumentException.class)
  public void testDeleteShape3() {
    HashMap<String, IShape> hashMap = new HashMap<>();
    model.addShape("O", oval);
    hashMap.put("O", oval);
    model.addShape("O", rect);
    model.deleteShape("O", Shape.RECTANGLE);
  }

  // testing addMotion
  @Test
  public void testAddMotion() {
    ArrayList<Pair<String, ArrayList<IMotion>>> a = new ArrayList<>();
    ArrayList<IMotion> list = new ArrayList<IMotion>();
    assertEquals(a, model.getMotions());
    model.addShape("R", rect);
    a.add(new Pair<>("R", new ArrayList<>()));
    assertEquals(1, model.getMotions().size());

    assertEquals(list, model.getMotionList("R"));
    model.addMotion("R", motion1);
    list.add(motion1);
    assertEquals(1, model.getMotionList("R").size());
    assertEquals(list, model.getMotionList("R"));

  }

  @Test
  public void testAddMotion1() {
    ArrayList<Pair<String, ArrayList<IMotion>>> a = new ArrayList<>();
    ArrayList<IMotion> list = new ArrayList<>();
    // before adding shape
    assertEquals(a, model.getMotions());
    model.addShape("R", rect);
    a.add(new Pair<>("R", new ArrayList<>()));
    assertEquals(1, model.getMotions().size());
    assertEquals(a, model.getMotions());

    // adding one motion
    assertEquals(list, model.getMotionList("R"));
    model.addMotion("R", motion4);
    list.add(motion4);
    assertEquals(1, model.getMotionList("R").size());
    assertEquals(list, model.getMotionList("R"));

    // add second motion
    model.addMotion("R", motion5);
    list.add(motion5);
    assertEquals(2, model.getMotionList("R").size());
    assertEquals(list, model.getMotionList("R"));

    // add third motion
    model.addMotion("R", motion6);
    list.add(motion6);
    assertEquals(3, model.getMotionList("R").size());
    assertEquals(list, model.getMotionList("R"));

  }

  // no such shape exist
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddMotionWrongShape() {
    ArrayList<Pair<String, ArrayList<IMotion>>> a = new ArrayList<>();
    ArrayList<IMotion> list = new ArrayList<IMotion>();
    assertEquals(a, model.getMotions());
    model.addShape("R", rect);
    a.add(new Pair<>("R", new ArrayList<>()));
    assertEquals(1, model.getMotions().size());

    assertEquals(list, model.getMotionList("R"));
    model.addMotion("C", motion1);
    list.add(motion1);
    assertEquals(1, model.getMotionList("C").size());
    assertEquals(list, model.getMotionList("C"));

  }

  // overlap
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddMotion2() {
    ArrayList<Pair<String, ArrayList<IMotion>>> a = new ArrayList<>();
    ArrayList<IMotion> list = new ArrayList<IMotion>();
    assertEquals(a, model.getMotions());
    model.addShape("R", rect);
    a.add(new Pair<>("R", new ArrayList<>()));
    assertEquals(1, model.getMotions().size());

    // adding one motion
    assertEquals(list, model.getMotionList("R"));
    model.addMotion("R", motion4);
    list.add(motion4);
    assertEquals(1, model.getMotionList("R").size());
    assertEquals(list, model.getMotionList("R"));

    assertEquals(list, model.getMotionList("R"));
    model.addMotion("R", motion4);
    list.add(motion4);
    assertEquals(1, model.getMotionList("R").size());
    assertEquals(list, model.getMotionList("R"));

  }

  // overlap case 2
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddMotion3() {
    ArrayList<Pair<String, ArrayList<IMotion>>> a = new ArrayList<>();
    ArrayList<IMotion> list = new ArrayList<IMotion>();
    assertEquals(a, model.getMotions());
    model.addShape("R", rect);
    a.add(new Pair<>("R", new ArrayList<>()));
    assertEquals(1, model.getMotions().size());

    // adding one motion
    assertEquals(list, model.getMotionList("R"));
    model.addMotion("R", motion4);
    list.add(motion4);
    assertEquals(1, model.getMotionList("R").size());
    assertEquals(list, model.getMotionList("R"));

    assertEquals(list, model.getMotionList("R"));
    model.addMotion("R", motion1);
    list.add(motion1);
    assertEquals(1, model.getMotionList("R").size());
    assertEquals(list, model.getMotionList("R"));

  }

  // addKeyframe
  //************************INSERT***********************

  // invalid adding
  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyFrame2() {
    model.addKeyframe("S", 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyFrame3() {
    model.addShape("R", rect);
    model.addKeyframe("R", 3);
    model.addKeyframe("O", 5);
  }

  // deleteKeyframe
  //************************INSERT***********************

  // invalid deleting
  @Test(expected = IllegalArgumentException.class)
  public void testDeleteKF2() {
    model.deleteKeyframe("S", 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteKF3() {
    model.addShape("R", rect);
    model.deleteKeyframe("T", 4);
  }

  // modifyKeyframe
  //************************INSERT***********************

  // invalid modifying
  @Test(expected = IllegalArgumentException.class)
  public void testModifyKF2() {
    model.modifyKeyFrame("S", 3, 4, 5, 6, 7, 8, 4, 22);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModifyKF3() {
    model.addShape("R", rect);
    model.modifyKeyFrame("S", 3, 4, 5, 6, 7, 8, 4, 22);
  }

  // testing of text Description
  @Test
  public void testTextDescription1() {
    // empty model
    assertEquals("", this.model.textDescription());
  }

  // shapes no motions
  @Test
  public void testTextDescription2() {
    // adding a shape
    model.addShape("R", rect);
    assertEquals("shape R RECTANGLE\n\n", this.model.textDescription());
    // adding another shape
    model.addShape("C", oval);
    assertEquals("shape R RECTANGLE\n\n\n" +
            "shape C OVAL\n\n\n", this.model.textDescription());
  }

  // one shape and motion
  @Test
  public void testTextDescription3() {
    model.addShape("R", rect);
    model.addMotion("R", motion4);
    assertEquals("shape R RECTANGLE\n" +
                    "motion R 0 0.0 0.0 5.0 2.0 0 0 255     10 6.0 7.0 7.0 9.0 255 0 0\n\n",
            this.model.textDescription());
  }

  // two shapes with motions
  @Test
  public void testTextDescription4() {
    model.addShape("R", rect);
    model.addMotion("R", motion4);
    model.addShape("C", oval);
    assertEquals("shape R RECTANGLE\n" +
                    "motion R 0 0.0 0.0 5.0 2.0 0 0 255     10 6.0 7.0 7.0 9.0 255 0 0\n\n\n" +
                    "shape C OVAL\n\n\n",
            this.model.textDescription());
  }


  // getShapesAtTick
  @Test
  public void testGetShapesAtTick() {
    model.addShape("R", rect);
    model.addMotion("R", motion4);
    model.addMotion("R", motion5);
    ArrayList<IReadOnlyShape> list = new ArrayList<>();
    IReadOnlyShape s = rect;
    list.add(s);
    assertEquals(list, this.model.getShapesAtTick(0));
  }

  @Test
  public void testGetShapesAtTick2() {
    model.addShape("R", rect);
    model.addShape("O", oval);
    model.addMotion("O", motion3);
    model.addMotion("R", motion4);
    model.addMotion("R", motion5);
    ArrayList<IReadOnlyShape> list = new ArrayList<>();
    IReadOnlyShape s = new ShapeImpl(Shape.RECTANGLE, new Dimension(7, 9),
            new Point2D.Double(3, 3), new Color(127, 0, 127));
    IReadOnlyShape s1 = new ShapeImpl(Shape.OVAL, new Dimension(0, 0),
            new Point2D.Double(3, 3), new Color(127, 0, 127));
    list.add(s);
    list.add(s1);
    assertEquals(list, this.model.getShapesAtTick(5));
  }

  // setX
  @Test
  public void testSetX() {
    IModel m = new ModelImpl();
    assertEquals(0, m.getX());
    m.setX(5);
    assertEquals(5, m.getX());
    m.setX(155);
    assertEquals(155, m.getX());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetX2() {
    IModel m = new ModelImpl();
    assertEquals(0, m.getX());
    m.setY(-19);
  }

  // setY
  @Test
  public void testSetY() {
    IModel m = new ModelImpl();
    assertEquals(0, m.getY());
    m.setY(15);
    assertEquals(15, m.getY());
    m.setY(2);
    assertEquals(2, m.getY());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetY2() {
    IModel m = new ModelImpl();
    assertEquals(0, m.getY());
    m.setY(-1);
  }

  // setH
  @Test
  public void testSetH() {
    IModel m = new ModelImpl();
    assertEquals(1000, m.getH());
    m.setH(23);
    assertEquals(23, m.getH());
    m.setH(11);
    assertEquals(11, m.getH());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetH2() {
    IModel m = new ModelImpl();
    assertEquals(1000, m.getH());
    m.setH(-1000);
  }

  // setW
  @Test
  public void testSetW() {
    IModel m = new ModelImpl();
    assertEquals(1000, m.getW());
    m.setW(32);
    assertEquals(32, m.getW());
    m.setW(1);
    assertEquals(1, m.getW());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetW2() {
    IModel m = new ModelImpl();
    assertEquals(1000, m.getH());
    m.setH(-2);
  }
}