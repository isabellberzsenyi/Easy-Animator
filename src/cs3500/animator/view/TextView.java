package cs3500.animator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import cs3500.animator.controller.IController;

/**
 * An IView object that renders an IModel as a text file.
 */
public class TextView implements IView {
  private String out;
  IViewModel viewModel;


  /**
   * Public constructor for a TextView that takes in the output file.
   *
   * @param out output file that text file is put into
   */
  public TextView(String out, IViewModel viewModel) {
    this.out = out;
    this.viewModel = viewModel;
  }

  @Override
  public void render(IViewModel viewModel) {
    if (this.out.equals("")) {
      System.setOut(System.out);
      System.out.println(viewModel.textDescription());
    } else {
      try {
        PrintStream o = new PrintStream(new File(this.out));
        System.setOut(o);
        System.out.print(viewModel.textDescription());
      } catch (FileNotFoundException f) {
        System.out.print("Uh oh! " + f.getMessage());
      }
    }
  }

  @Override
  public void edit(IController controller) {
    throw new UnsupportedOperationException("Cannot edit this view.");
  }

}
