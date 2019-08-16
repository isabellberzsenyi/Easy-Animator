package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.view.IReadOnlyShape;
import javafx.util.Pair;

/**
 * Represents an IModel. This Model has a HashMap that keeps track of IShape and it's String id, and
 * an ArrayList that keeps track of pairs of an String id and an ArrayList of it's motions.
 */
public class ModelImpl implements IModel {
  private int start;
  private int x;
  private int y;
  private int w;
  private int h;

  private final HashMap<String, IShape> shapes;
  private final ArrayList<Pair<String, ArrayList<IMotion>>> motions;

  /**
   * Public constructor of a ModelImpl. Sets each ArrayList and HashMap to be empty.
   */
  public ModelImpl() {
    this.start = 0;
    this.x = 0;
    this.y = 0;
    this.w = 1000;
    this.h = 1000;
    this.shapes = new HashMap<>();
    this.motions = new ArrayList<>();
  }

  @Override
  public HashMap<String, IShape> getShapes() {
    HashMap<String, IShape> hashMap = new HashMap<>();
    hashMap.putAll(this.shapes);
    return hashMap;
  }

  @Override
  public ArrayList<Pair<String, ArrayList<IMotion>>> getMotions() {
    ArrayList<Pair<String, ArrayList<IMotion>>> list =
            new ArrayList<>();
    for (int i = 0; i < this.motions.size(); i++) {
      list.add(this.motions.get(i));
    }
    return list;
  }

  @Override
  public ArrayList<IMotion> getMotionList(String id) {
    ArrayList<IMotion> am = new ArrayList<>();
    for (int j = 0; j < this.motions.size(); j++) {
      Pair<String, ArrayList<IMotion>> pair = this.motions.get(j);
      if (pair.getKey().equals(id)) {
        am = pair.getValue();
      }
    }
    return am;
  }

  @Override
  public ArrayList<IReadOnlyShape> getShapesAtTick(int tick) {
    ArrayList<IReadOnlyShape> shapesReturned = new ArrayList<>();
    ArrayList<String> idList = new ArrayList<>(shapes.keySet());
    for (int i = 0; i < idList.size(); i++) {
      IShape s = this.shapes.get(idList.get(i));
      if (tick >= this.start) {
        ArrayList<IMotion> sMotions = this.getMotionList(idList.get(i));
        for (int j = 0; j < sMotions.size(); j++) {
          IMotion m = sMotions.get(j);
          if (tick < m.getEndTime()
                  && tick >= m.getStartTime()) {
            int newX = this.tweening(m.getStartTime(), m.getEndTime(), tick, m.getLocation(m.getStartTime()).getX(),
                    m.getLocation(m.getEndTime()).getX());
            int newY = this.tweening(m.getStartTime(), m.getEndTime(), tick, m.getLocation(m.getStartTime()).getY(),
                    m.getLocation(m.getEndTime()).getY());
            int newR = this.tweening(m.getStartTime(), m.getEndTime(), tick, m.getColor(m.getStartTime()).getRed(),
                    m.getColor(m.getEndTime()).getRed());
            int newG = this.tweening(m.getStartTime(), m.getEndTime(), tick, m.getColor(m.getStartTime()).getGreen(),
                    m.getColor(m.getEndTime()).getGreen());
            int newB = this.tweening(m.getStartTime(), m.getEndTime(), tick, m.getColor(m.getStartTime()).getBlue(),
                    m.getColor(m.getEndTime()).getBlue());
            int newW = this.tweening(m.getStartTime(), m.getEndTime(), tick, m.getDim(m.getStartTime()).getWidth(),
                    m.getDim(m.getEndTime()).getWidth());
            int newH = this.tweening(m.getStartTime(), m.getEndTime(), tick, m.getDim(m.getStartTime()).getHeight(),
                    m.getDim(m.getEndTime()).getHeight());
            s.setLocation(new Point2D.Double(newX, newY));
            s.setColor(new Color(newR, newG, newB));
            s.setDimension(new Dimension(newW, newH));
            shapesReturned.add(s);
          }
        }
      }
    }
    return shapesReturned;
  }

  /**
   * Created to cut down on repetitive code... find a value at the given tick from the given start
   * and end time and start and end values.
   *
   * @param start      integer representing start time
   * @param end        integer representing end time
   * @param newTick    integer representing the tick want to calculate value for
   * @param startValue double representing value at the start tick
   * @param endValue   double representing value at the end tick
   */
  private int tweening(int start, int end, int newTick, double startValue, double endValue) {
    double f1 = ((double) (end - newTick) / (end - start));
    double f2 = ((double) (newTick - start) / (end - start));
    return (int) (startValue * f1 + endValue * f2);
  }

  @Override
  public void setX(int x) {
    if (x >= 0) {
      this.x = x;
    } else {
      throw new IllegalArgumentException("X must be >= 0");
    }
  }

  @Override
  public void setY(int y) throws IllegalArgumentException {
    if (y >= 0) {
      this.y = y;
    } else {
      throw new IllegalArgumentException("X must be >= 0");
    }
  }

  @Override
  public void setH(int h) throws IllegalArgumentException {
    if (h >= 0) {
      this.h = h;
    } else {
      throw new IllegalArgumentException("X must be >= 0");
    }
  }

  @Override
  public void setW(int w) throws IllegalArgumentException {
    if (w >= 0) {
      this.w = w;
    } else {
      throw new IllegalArgumentException("X must be >= 0");
    }
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }

  @Override
  public int getH() {
    return h;
  }

  @Override
  public int getW() {
    return w;
  }

  @Override
  public int animationEndTime() {
    int maxEndTime = 0;
    for (Pair<String, ArrayList<IMotion>> p : this.motions) {
      if (p.getValue().size() > 0) {
        int last = p.getValue().size() - 1;
        IMotion m = p.getValue().get(last);
        maxEndTime = Math.max(maxEndTime, m.getEndTime());
      }
    }
    return maxEndTime;
  }

  @Override
  public void addShape(String id, IShape shape) throws IllegalArgumentException {
    if (!this.shapes.containsKey(id)) {
      this.shapes.put(id, shape);
      Pair<String, ArrayList<IMotion>> p = new Pair<>(id, new ArrayList<>());
      this.motions.add(p);
    } else {
      throw new IllegalArgumentException("This id is already used.");
    }
  }

  @Override
  public void deleteShape(String id, Shape shape) throws IllegalArgumentException {
    if (this.shapes.containsKey(id)) {
      if (this.shapes.get(id).getShape().equals(shape)) {
        this.shapes.remove(id);
      } else {
        throw new IllegalArgumentException("The id and shape type don't match!");
      }
    } else {
      throw new IllegalArgumentException("This id is not valid!");
    }
  }

  @Override
  public void addMotion(String id, IMotion i) throws IllegalArgumentException {
    if (!this.shapes.containsKey(id)) {
      throw new IllegalArgumentException("BAD!");
    } else {
      ArrayList<IMotion> list = this.getMotionList(id);
      ArrayList<IMotion> copy = (ArrayList<IMotion>) list.clone();
      if (inBoundary(i.getStartTime())) {
        copy.add(i);
        sort(copy);
      } else {
        throw new IllegalArgumentException("Out of Bound!!!");
      }

      if (this.isOverlap(copy, copy.size())) {
        throw new IllegalArgumentException("Overlap!!!");
      } else {
        list.add(i);
        sort(list);
      }
    }
  }

  @Override
  public void addKeyframe(String name, int t)
          throws IllegalArgumentException {
    ArrayList<IMotion> motionList = this.getMotionList(name);
    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("Cannot add KeyFrame for shape that does not exist");
    } else {
      if (motionList.size() == 0) {
        IShape s = this.shapes.get(name);
        IMotion m = new MotionImpl(t, t + 50, s.getStartLocation(),
                s.getStartLocation(), s.getDimension(), s.getDimension(), s.getColor(),
                s.getColor());
        this.addMotion(name, m);
      } else {
        for (IMotion m : motionList) {
          int startTime = m.getStartTime();
          int endTime = m.getEndTime();
          IMotion m1;
          IMotion m2;

          Point2D newPoint = new Point2D.Double(this.tweening(startTime, endTime, t, m.getLocation(startTime).getX(),
                  m.getLocation(endTime).getX()), this.tweening(startTime, endTime, t, m.getLocation(startTime).getY(),
                  m.getLocation(endTime).getY()));

          Color newColor = new Color(this.tweening(startTime, endTime, t, m.getColor(startTime).getRed(),
                  m.getColor(endTime).getRed()),
                  this.tweening(startTime, endTime, t, m.getColor(startTime).getGreen(),
                          m.getColor(endTime).getGreen()),
                  this.tweening(startTime, endTime, t, m.getColor(startTime).getBlue(),
                          m.getColor(endTime).getBlue()));

          Dimension newDim = new Dimension(this.tweening(startTime, endTime, t, m.getDim(startTime).getWidth(),
                  m.getDim(endTime).getWidth()), this.tweening(startTime, endTime, t, m.getDim(startTime).getHeight(),
                  m.getDim(endTime).getHeight()));

          if (t > startTime && t < endTime) {
            m1 = new MotionImpl(startTime, t, m.getLocation(startTime), newPoint,
                    m.getDim(startTime), newDim, m.getColor(startTime), newColor);
            m2 = new MotionImpl(t, endTime, newPoint, m.getLocation(endTime),
                    newDim, m.getDim(endTime), newColor, m.getColor(endTime));

            for (int i = 0; i < this.motions.size(); i++) {
              if (this.motions.get(i).getKey() == name) {
                this.motions.get(i).getValue().remove(m);
                this.motions.get(i).getValue().add(m1);
                this.motions.get(i).getValue().add(m2);
              }
            }
          }
        }
      }
    }
  }

  @Override
  public void deleteKeyframe(String name, int t) throws IllegalArgumentException {
    ArrayList<IMotion> motionList = this.getMotionList(name);

    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("Shape does not exist");
    } else {
      for (int j = 0; j < motionList.size(); j++) {
        IMotion m = motionList.get(j);
        int startTime = m.getStartTime();
        int endTime = m.getEndTime();

        if (j < motionList.size() - 1) {
          IMotion next = motionList.get(j + 1);
          if (t == endTime && t == next.getStartTime()) {
            for (int i = 0; i < this.motions.size(); i++) {
              if (this.motions.get(i).getKey() == name) {
                IMotion m1 = new MotionImpl(m.getStartTime(), next.getEndTime(), m.getLocation(m.getStartTime()),
                        next.getLocation(next.getEndTime()), m.getDim(m.getStartTime()), next.getDim(next.getEndTime()),
                        m.getColor(m.getStartTime()), next.getColor(next.getEndTime()));
                this.motions.get(i).getValue().remove(m);
                this.motions.get(i).getValue().remove(next);
                this.motions.get(i).getValue().add(m1);

              }
            }
          }
        } else if (t == endTime && t == startTime) {
          for (int i = 0; i < this.motions.size(); i++) {
            if (this.motions.get(i).getKey() == name) {
              this.motions.get(i).getValue().remove(m);
            }
          }
        }
      }
    }
  }

  @Override
  public void modifyKeyFrame(String name, int t, int x, int y, int w, int h, int r, int g, int b) throws IllegalArgumentException {
    ArrayList<IMotion> motionList = this.getMotionList(name);
    List<IMotion> list = new ArrayList<>();

    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("Shape does not exist");
    } else {

      for (int i = 0; i < motionList.size(); ++i) {
        list.add(motionList.get(i));
      }
      for (IMotion m : motionList) {
        int startTime = m.getStartTime();
        int endTime = m.getEndTime();
        IMotion m1;
        IMotion m2;
        if (t == startTime) {
          m1 = new MotionImpl(t, endTime, new Point2D.Double(x, y), m.getLocation(endTime),
                  new Dimension(w, h), m.getDim(endTime), new Color(r, g, b), m.getColor(startTime));

          for (int i = 0; i < this.motions.size(); i++) {
            if (this.motions.get(i).getKey() == name) {
              this.motions.get(i).getValue().add(m1);
              this.motions.get(i).getValue().remove(m);
              sort(this.motions.get(i).getValue());
            }
          }
        }
        if (t == endTime) {
          m2 = new MotionImpl(startTime, t, m.getLocation(startTime), new Point2D.Double(x, y),
                  m.getDim(startTime), new Dimension(w, h), m.getColor(startTime), new Color(r, g, b));

          for (int i = 0; i < this.motions.size(); i++) {
            if (this.motions.get(i).getKey() == name) {
              this.motions.get(i).getValue().remove(m);
              this.motions.get(i).getValue().add(m2);
            }
          }
        }

      }
    }
  }

  @Override
  public String textDescription() {
    StringBuilder sb = new StringBuilder();
    ArrayList<String> ids = new ArrayList<>();
    for (String s : this.shapes.keySet()) {
      ids.add(s);
    }
    for (String s : ids) {
      String shape = this.shapes.get(s).printType();


      sb.append("shape " + s + " " + shape + "\n");
      for (int i = 0; i < this.motions.size(); i++) {
        if (s.equals(this.motions.get(i).getKey())) {
          ArrayList<IMotion> idMotionList = this.motions.get(i).getValue();
          for (int j = 0; j < idMotionList.size(); j++) {
            sb.append("motion " + s + " ");
            sb.append(idMotionList.get(j).printMotion());
          }
        }
        sb.append("\n");
      }
    }
    String result = sb.toString();
    return result;
  }

  /**
   * Determines whether or not the given start and end time are within this id's IShape's start and
   * end time.
   *
   * @param start integer representing start time comparing
   * @return boolean for the given start and end integers are within IShape's startTime and endTime
   */
  private boolean inBoundary(int start) {
    return this.start <= start;
  }

  /**
   * Sorts an ArrayList by the IMotion's startTime.
   *
   * @param list ArrayList of IMotions
   * @return the sorted ArrayList of IMotion's by the IMotion's startTime
   */
  private static ArrayList<IMotion> sort(ArrayList<IMotion> list) {
    Collections.sort(list, new SortbyTick());
    return list;
  }

  /**
   * Represents a Comparator class. Returns if IMotion a's startTime is greater than or less than
   * IMotion b's startTime. If a - b (startTime) is negative then a comes before b.
   */
  static class SortbyTick implements Comparator<IMotion> {

    /**
     * Compares two IMotions' startTimes, returns a negative value if IMotion a is before b.
     *
     * @param a an IMotion, comparing to b
     * @param b an IMotion, comparing to a
     * @return an integer representing IMotion a startTime - IMotion b startTimes
     */
    public int compare(IMotion a, IMotion b) {
      return a.getStartTime() - b.getStartTime();
    }
  }


  /**
   * Determines whether two intervals overlap.
   *
   * @param list list of IMotions
   * @param n    size of the list
   * @return boolean representing whether any two intervals overlap
   */
  boolean isOverlap(ArrayList<IMotion> list, int n) {
    Boolean result = false;
    // Sort intervals in increasing order of start time
    sort(list);

    // In the sorted array, if start time of an interval
    // is less than end of previous interval, then there
    // is an overlap
    for (int i = 1; i < n; i++) {
      result = list.get(i - 1).getEndTime() > list.get(i).getStartTime();
    }
    return result;
  }

  /**
   * Creates a Builder() for an animation to be created by the Builder class.
   *
   * @return AnimationBuilder that is used to build an IModel
   */
  public static AnimationBuilder<IModel> getBuilder() {
    return new Builder();
  }

  /**
   * Builder class to build an IModel, implementing necessary functions through AnimationBuilder.
   */
  public static final class Builder implements AnimationBuilder<IModel> {
    IModel m;

    /**
     * Public constructor for Builder, creates an IModel.
     */
    public Builder() {
      this.m = new ModelImpl();
    }

    @Override
    public IModel build() {
      return m;
    }

    @Override
    public AnimationBuilder<IModel> setBounds(int x, int y, int width, int height) {
      m.setX(x);
      m.setY(y);
      m.setH(height);
      m.setW(width);
      return this;
    }

    @Override
    public AnimationBuilder<IModel> declareShape(String name, String type) {
      Shape t;
      if (type.equalsIgnoreCase("rectangle")) {
        // default shape info
        IShape r = new ShapeImpl(Shape.RECTANGLE, new Dimension(5, 5),
                new Point2D.Double(0, 0), new Color(0, 0, 0));
        this.m.addShape(name, r);
      } else if (type.equalsIgnoreCase("ellipse")) {
        // default shape info
        IShape c = new ShapeImpl(Shape.OVAL, new Dimension(5, 5),
                new Point2D.Double(0, 0), new Color(0, 0, 0));
        this.m.addShape(name, c);
      }
      return this;
    }

    @Override
    public AnimationBuilder<IModel> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
                                              int r1, int g1, int b1, int t2, int x2, int y2,
                                              int w2, int h2, int r2, int g2, int b2) {
      IMotion m = new MotionImpl(t1, t2, new Point2D.Double(x1, y1), new Point2D.Double(x2, y2),
              new Dimension(w1, h1), new Dimension(w2, h2), new Color(r1, g1, b1),
              new Color(r2, g2, b2));
      this.build().addMotion(name, m);
      return this;
    }

    @Override
    public AnimationBuilder<IModel> addKeyframe(String name, int t, int x, int y, int w, int h,
                                                int r, int g, int b) {
      this.build().addKeyframe(name, t);
      this.build().modifyKeyFrame(name, t, x, y, w, h, r, g, b);
      return this;
    }
  }
}
