package cs3500.animator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import cs3500.animator.controller.Controller;
import cs3500.animator.controller.IController;
import cs3500.animator.model.IModel;
import cs3500.animator.model.ModelImpl;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.EditView;
import cs3500.animator.view.IView;
import cs3500.animator.view.IViewModel;
import cs3500.animator.view.SvgView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.VisualView;
import javafx.util.Pair;

import static java.lang.Integer.parseInt;

/**
 * Main class to run this whole program.
 */
public final class Excellence {

  /**
   * Main runs the program.
   *
   * @param args Array of Strings containing arguments for this program.
   */
  public static void main(String[] args) {
    IModel m;
    IView v;

    ArrayList<Pair<String, String>> argPairs = new ArrayList<>();
    String in = null;
    String view = null;
    String out = null;
    int speed = 0;

    for (int i = 0; i < args.length; i = i + 2) {
      Pair<String, String> p = new Pair<>(args[i], args[i + 1]);
      argPairs.add(p);
    }

    for (int i = 0; i < argPairs.size(); i++) {
      String one = argPairs.get(i).getKey();
      switch (one) {
        case "-in":
          if (in == null) {
            in = argPairs.get(i).getValue();
          } else {
            throw new IllegalArgumentException("Already gave an input file");
          }
          break;
        case "-out":
          if (out == null) {
            out = argPairs.get(i).getValue();
          } else {
            throw new IllegalArgumentException("Already gave an output file");
          }
          break;
        case "-view":
          if (view == null) {
            view = argPairs.get(i).getValue();
          } else {
            throw new IllegalArgumentException("Already gave a view");
          }
          break;
        case "-speed":
          if (speed == 0) {
            try {
              int s = parseInt(argPairs.get(i).getValue());
              speed = s;
            } catch (NumberFormatException e) {
              System.out.print("BAD");
            }
          } else {
            throw new IllegalArgumentException("Already gave a speed");
          }
          break;
        default:
          throw new IllegalArgumentException("Bad word");
      }
    }

    if (in != null && view != null) {
      if (speed == 0) {
        speed = 1000;
      }
      if (out == null) {
        out = "";
      }
    } else {
      throw new IllegalArgumentException("Must specify input file and view type");
    }
    try {
      FileReader fileReader = new FileReader(in);
      AnimationReader reader = new AnimationReader();
      m = reader.parseFile(fileReader, ModelImpl.getBuilder());
      v = createView(view, (1000 / speed), out, m);
      IController c = new Controller(m, v);
      if (view.equals("edit")) {
        c.edit();
      }
      c.goView();

    } catch (FileNotFoundException e) {
      System.out.print(in + " " + e.getMessage());
    }
  }

  /**
   * Factory for the IViews, creates an IView from given String.
   *
   * @param v     String representing the type of IView
   * @param speed integer representing the speed of the animation
   * @param out   String for the output file
   * @param model IViewModel that allows IView to receive information
   * @return the IView that corresponds with given v String
   */
  static IView createView(String v, int speed, String out, IViewModel model) {
    switch (v) {
      case "text":
        return new TextView(out, model);
      case "visual":
        return new VisualView(speed, model);
      case "svg":
        return new SvgView(out, speed, model);
      case "edit":
        return new EditView(speed, new VisualView(speed, model));
      default:
        throw new IllegalArgumentException("View type unacceptable");
    }
  }
}
