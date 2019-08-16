package cs3500.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.String;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import static java.lang.Integer.parseInt;

/**
 * An ActionListener that listens to the action of adding or deleting a KeyFrame when the Enter
 * button for the KeyFrame is entered. This AddKFListener has an EditView field, name for the IShape
 * and the time for the KeyFrame.
 */
public class AddKFListener implements ActionListener {
  EditView view;
  String name;
  int time;

  /**
   * Public constructor for AddKFListener that takes in an EditView.
   *
   * @param view an EditView to call the correct method on
   */
  public AddKFListener(EditView view) {
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("KFEnter")) {
      // get the name of the IShape
      name = this.view.kfName.getText();

      // parse the next field to get the time, error message thrown if incorrect
      try {
        time = parseInt(this.view.kfTime.getText());
      } catch (NumberFormatException n) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Error! " + n.getMessage());
      }

      // get the selection of either adding or deleting the shape
      String addOrDelete = this.view.addDeleteShape.getSelection().getActionCommand();

      // correct method is called
      if (addOrDelete.equals("AddShape")) {
        this.view.addKeyFrame(name, time);
      }
      if (addOrDelete.equals("DeleteShape")) {
        this.view.deleteKeyFrame(name, time);
      }
    }
  }
}
