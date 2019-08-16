package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import cs3500.animator.controller.IController;

/**
 * An IView object that renders an IModel as a visual.
 */
public class VisualView extends JFrame implements IView {
  Timer timer;
  private int tick = 1;
  IViewModel viewModel;
  DrawingPanel panel;
  JScrollPane scrollPane;
  private int speed;

  /**
   * Public constructor for VisualView.
   *
   * @param speed speed of animation
   */
  public VisualView(int speed, IViewModel viewModel) {
    super();
    this.speed = speed;
    this.viewModel = viewModel;

    setSize(800, 800);
    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(0, 0);

    panel = new DrawingPanel();
    panel.setMinimumSize(new Dimension(500, 500));
    panel.setBackground(Color.WHITE);

    scrollPane = new JScrollPane(panel);

    add(scrollPane);
    setVisible(true);

    timer = new Timer(this.speed,
            new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                ArrayList<IReadOnlyShape> shapesRender = viewModel.getShapesAtTick(tick++);
                draw(shapesRender);
              }
            }
    );
  }

  @Override
  public void render(IViewModel viewModel) {
    this.start();
  }

  @Override
  public void edit(IController controller) {
    throw new UnsupportedOperationException("Cannot edit this view.");
  }

  /**
   * Starts the timer for the visuals to be rendered.
   */
  protected void start() {
    timer.start();
  }

  /**
   * Draws the given list of shapes at each tick onto the panel.
   *
   * @param shapes a list of "Read only" shapes that cannot be mutated
   */
  protected void draw(ArrayList<IReadOnlyShape> shapes) {
    panel.draw(shapes);
  }
}

