package cs3500.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs3500.animator.model.IShape;
import cs3500.animator.model.Shape;

/**
 * An ActionListener that listens to the action of adding or deleting a Shape when the Enter button
 * for the Shape is entered.
 */
public class AddShapeListener implements ActionListener {
  EditView view;
  Shape type;
  String name;

  /**
   * Public constructor for AddShapeListener.
   *
   * @param view EditView to call the correct method on
   */
  public AddShapeListener(EditView view) {
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();
    if (cmd.equals("ShapeEnter")) {
      name = this.view.shapeName.getText();
      String shapeTypeString = this.view.shapeType.getText();
      if (shapeTypeString.equalsIgnoreCase("rectangle")) {
        type = Shape.RECTANGLE;
      } else if (shapeTypeString.equalsIgnoreCase("oval")) {
        type = Shape.OVAL;
      } else {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Error! shape must be OVAL or RECTANGLE");
      }
      String addOrDelete = this.view.addDeleteShape.getSelection().getActionCommand();

      if (addOrDelete.equals("AddShape")) {
        this.view.addShape(name, type);
        ArrayList<IShape> idList = new ArrayList<>(this.view.viewModel.getShapes().values());
        for (IShape s : idList) {
          System.out.println(s.getShape());
        }
      }
      if (addOrDelete.equals("DeleteShape")) {
        this.view.deleteShape(name, type);
        ArrayList<IShape> idList = new ArrayList<>(this.view.viewModel.getShapes().values());
        for (IShape s : idList) {
          System.out.println(s.getShape());
        }
      }
    }
  }
}
