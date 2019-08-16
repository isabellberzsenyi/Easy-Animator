package cs3500.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import static java.lang.Integer.parseInt;

/**
 * An ActionListener that listens to the action of modifying a KeyFrame when enter is pressed.
 */
public class ModifyKFListener implements ActionListener {
  private EditView view;
  private String name;
  int t;
  int x;
  int y;
  int w;
  int h;
  int r;
  int g;
  int b;

  /**
   * Public constructor for ModifyKFListener.
   *
   * @param view EditView that the methods are called to
   */
  public ModifyKFListener(EditView view) {
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    name = this.view.modifyID.getText();

    int[] fields = {t, x, y, w, h, r, g, b};
    String[] strings = {this.view.modifyT.getText(), this.view.modifyX.getText(),
            this.view.modifyY.getText(), this.view.modifyX.getText(), this.view.modifyW.getText(),
            this.view.modifyH.getText(), this.view.modifyR.getText(),
            this.view.modifyG.getText(), this.view.modifyB.getText()};

    // parse all of the fields to integers for all the fields
    for (int i = 0; i < fields.length; i++) {
      try {
        fields[i] = parseInt(strings[i]);
      } catch (NumberFormatException n) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Error! " + n.getMessage() + fields[i]);
      }
    }
    // modifies the key frame
    if (e.getActionCommand().equals("Modify")) {
      this.view.modifyKeyFrame(name, t, x, y, w, h, r, g, b);
      System.out.println("Modify");
    }
  }
}
