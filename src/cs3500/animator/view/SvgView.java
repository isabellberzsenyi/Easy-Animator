package cs3500.animator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import cs3500.animator.controller.IController;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IShape;
import javafx.util.Pair;

/**
 * This class is an IView that renders the IModel as an svg file.
 */
public class SvgView implements IView {
  private IViewModel viewModel;
  private final int speed;
  private String out;

  /**
   * Constructs a SvgView, which outputs a file to the output Path.
   *
   * @param output is the output path
   * @param speed  is the given speed used to run the file
   */
  public SvgView(String output, int speed, IViewModel viewModel) {
    super();
    this.speed = speed;
    this.out = output;
    this.viewModel = viewModel;
  }

  /**
   * Creates the background of the SVG view.
   *
   * @return a string used to generate svg view
   */
  private String createFile() {
    StringBuilder sb = new StringBuilder();
    // svg background
    sb.append("<svg width=\"");
    sb.append(viewModel.getW() + viewModel.getX());
    sb.append("\" height=\"");
    sb.append(viewModel.getH() + viewModel.getY());
    sb.append("\" version=\"1.1\"\n");
    sb.append("xmlns=\"http://www.w3.org/2000/svg\">\n");

    // get a list of unique ids
    ArrayList<Pair<String, ArrayList<IMotion>>> pairs = new ArrayList<>(viewModel.getMotions());

    for (int i = 0; i < pairs.size(); i++) {
      String id = pairs.get(i).getKey();
      ArrayList<IMotion> motions = viewModel.getMotionList(id);
      IShape s = viewModel.getShapes().get(id);
      if (s.printType().equals("RECTANGLE")) {
        sb.append(this.rectSVG(s, id, motions));
      } else {
        sb.append(this.ovalSVG(s, id, motions));
      }
    }
    sb.append("</svg>");

    return sb.toString();

  }

  /**
   * Creates a StringBuilder for what an Oval is as an SVG.
   *
   * @param s       the IShape
   * @param id      the IShape's unique String id
   * @param motions the IShape's list of IMotions
   * @return StringBuilder containing all of the given IShape's characteristics
   */
  private StringBuilder rectSVG(IShape s, String id, ArrayList<IMotion> motions) {
    StringBuilder rect = new StringBuilder();

    rect.append("<rect id=\"");
    rect.append(id); //name of shape
    rect.append("\" x=\"");
    rect.append(s.getStartLocation().getX()); // x posn of shape
    rect.append("\" y=\"");
    rect.append(s.getStartLocation().getY()); // y posn of shape
    rect.append("\" width=\"");
    rect.append(s.getDimension().getWidth()); // width of shape
    rect.append("\" height=\"");
    rect.append(s.getDimension().getHeight()); // height of shape
    rect.append("\" fill=\"rgb(");
    rect.append(s.getColor().getRed()).append(", ");
    rect.append(s.getColor().getGreen()).append(", ");
    rect.append(s.getColor().getBlue());

    rect.append(")\" visibility=\"visible\" >\n");

    for (int j = 0; j < motions.size(); j++) {
      int startTime = motions.get(j).getStartTime();
      int endTime = motions.get(j).getEndTime();

      rect.append("<animate attributeName=\"x\" ").append("attributeType=\"xml\" ")
              .append("begin=\"").append(startTime * speed).append("ms\"")
              .append(" dur=\"").append((endTime - motions.get(j).getStartTime()) * speed)
              .append("ms\" fill=\"freeze\"")
              .append(" from=\"").append(motions.get(j).getLocation(startTime).getX()).
              append("\"").append(" to=\"").append(motions.get(j).getLocation(endTime).getY())
              .append("\"").append(" />\n");

      rect.append("<animate attributeName=\"y\" ").append("attributeType=\"xml\" ")
              .append("begin=\"").append(startTime * speed).append("ms\"")
              .append(" dur=\"").append((endTime - startTime) * speed)
              .append("ms\" fill=\"freeze\"")
              .append(" from=\"").append(motions.get(j).getLocation(startTime).getY()).append("\"")
              .append(" to=\"").append(motions.get(j).getLocation(endTime).getY())
              .append("\"").append(" />\n");

      rect.append("<animate attributeName=\"width\" ").append("attributeType=\"xml\" ")
              .append("begin=\"").append(startTime * speed).append("ms\"")
              .append(" dur=\"").append((endTime - startTime) * speed)
              .append("ms\" fill=\"freeze\"")
              .append(" from=\"").append(motions.get(j).getDim(startTime).getWidth()).append("\"").
              append(" to=\"").append(motions.get(j).getDim(endTime).getWidth())
              .append("\"").append(" />\n");

      rect.append("<animate attributeName=\"height\" ").append("attributeType=\"xml\" ")
              .append("begin=\"").append(startTime * speed).append("ms\"")
              .append(" dur=\"").append((endTime - startTime) * speed)
              .append("ms\" fill=\"freeze\"")
              .append(" from=\"").append(motions.get(j).getDim(startTime).getHeight()).
              append("\"").append(" to=\"").append(motions.get(j).getDim(endTime).getHeight())
              .append("\"").append(" />\n");
    }
    rect.append("</rect>\n");

    return rect;
  }

  /**
   * Creates a StringBuilder for what an Oval is as an SVG.
   *
   * @param s       the IShape
   * @param id      the IShape's unique String id
   * @param motions the IShape's list of IMotions
   * @return StringBuilder containing all of the given IShape's characteristics
   */
  private StringBuilder ovalSVG(IShape s, String id, ArrayList<IMotion> motions) {
    StringBuilder oval = new StringBuilder();

    // create the original shape
    oval.append("<ellipse id=\"");
    oval.append(id); //name of shape
    oval.append("\" cx=\"");
    oval.append(s.getStartLocation().getX()); // x posn of shape
    oval.append("\" cy=\"");
    oval.append(s.getStartLocation().getY()); // y posn of shape
    oval.append("\" rx=\"");
    oval.append(s.getDimension().getWidth()); // width of shape
    oval.append("\" ry=\"");
    oval.append(s.getDimension().getHeight()); // height of shape
    oval.append("\" fill=\"rgb(");
    oval.append(s.getColor().getRed()).append(", ");
    oval.append(s.getColor().getGreen()).append(", ");
    oval.append(s.getColor().getBlue());

    oval.append(")\" visibility=\"visible\" >\n");

    for (int j = 0; j < motions.size(); j++) {
      IMotion m = motions.get(j);
      int startTime = m.getStartTime();
      int endTime = m.getEndTime();

      // Location - x (Start - End)
      oval.append("<animate attributeName=\"x\" ").append("attributeType=\"xml\" ")
              .append("begin=\"").append(motions.get(j).getStartTime() * speed).append("ms\"")
              .append(" dur=\"").append((motions.get(j).getEndTime()
              - motions.get(j).getStartTime()) * speed)
              .append("ms\" fill=\"freeze\"")
              .append(" from=\"").append(motions.get(j).getLocation(startTime).getX()).
              append("\"").append(" to=\"").append(motions.get(j).getLocation(endTime).getX())
              .append("\"").append(" />\n");

      // Location - y (Start - End)
      oval.append("<animate attributeName=\"y\" ").append("attributeType=\"xml\" ")
              .append("begin=\"").append(motions.get(j).getStartTime() * speed).append("ms\"")
              .append(" dur=\"").append((motions.get(j).getEndTime() -
              motions.get(j).getStartTime()) * speed)
              .append("ms\" fill=\"freeze\"")
              .append(" from=\"").append(motions.get(j).getLocation(startTime).getY()).
              append("\"").append(" to=\"").append(motions.get(j).getLocation(endTime).getY())
              .append("\"").append(" />\n");

      // Dimension - Width (Start - End)
      oval.append("<animate attributeName=\"width\" ").append("attributeType=\"xml\" ")
              .append("begin=\"").append(motions.get(j).getStartTime() * speed).append("ms\"")
              .append(" dur=\"").append((motions.get(j).getEndTime() - motions.get(j)
              .getStartTime()) * speed)
              .append("ms\" fill=\"freeze\"")
              .append(" from=\"").append(motions.get(j).getDim(startTime).getWidth()).append("\"").
              append(" to=\"").append(motions.get(j).getDim(endTime).getWidth())
              .append("\"").append(" />\n");

      // Dimension - Height (Start - End)
      oval.append("<animate attributeName=\"height\" ").append("attributeType=\"xml\" ")
              .append("begin=\"").append(motions.get(j).getStartTime() * speed).append("ms\"")
              .append(" dur=\"").append((motions.get(j).getEndTime() -
              motions.get(j).getStartTime()) * speed)
              .append("ms\" fill=\"freeze\"")
              .append(" from=\"").append(motions.get(j).getDim(startTime).getHeight()).
              append("\"").append(" to=\"").append(motions.get(j).getDim(endTime).getHeight())
              .append("\"").append(" />\n");
    }


    oval.append("</ellipse>\n");
    return oval;
  }

  /**
   * Creates the svg file and prints it to the output file, if the output file is "" then it is
   * printed to System.out
   */
  private void run() {
    if (this.out.equals("")) {
      System.setOut(System.out);
      System.out.println(this.createFile());
    } else {
      try {
        PrintStream o = new PrintStream(new File(this.out));
        System.setOut(o);
        System.out.print(this.createFile());
      } catch (FileNotFoundException f) {
        System.out.print("Uh oh! " + f.getMessage());
      }

    }
  }

  @Override
  public void render(IViewModel viewModel) {
    run();
  }

  @Override
  public void edit(IController controller) {
    throw new UnsupportedOperationException("Cannot edit this view.");
  }

}
