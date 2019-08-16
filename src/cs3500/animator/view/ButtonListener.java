package cs3500.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This class is an implementation of ActionListener, for the Buttons in the EditView. The various
 * commands for each button are invoked when necessary.
 */
public class ButtonListener implements ActionListener {
  EditView view;

  /**
   * Public constructor for the ButtonListener, taking in an EditView.
   *
   * @param view EditView, as the ButtonListener is created just for this case
   */
  public ButtonListener(EditView view) {
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();
    switch (cmd) {
      case "Start":
        this.view.startTime();
        break;
      case "Pause":
        this.view.pause();
        break;
      case "Resume":
        this.view.resume();
        break;
      case "Restart":
        this.view.restart();
        break;
      case "Enable Looping":
        this.view.loop(true);
        break;
      case "Disable Looping":
        this.view.loop(false);
        break;
      case "Up":
        this.view.changeSpeed(true);
        break;
      case "Down":
        this.view.changeSpeed(false);
        break;
    }
  }
}
