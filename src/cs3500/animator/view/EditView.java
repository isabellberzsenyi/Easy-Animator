package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;

import cs3500.animator.controller.IController;
import cs3500.animator.model.Shape;

/**
 * An IView object that renders an IModel as a visual and also has a pop-up so that the animation
 * can be edited. You can play, pause, resume, restart, then enable or disable looping.
 */
public class EditView implements IView {
  Timer timer;
  private int tick = 1;
  IViewModel viewModel;
  private IController controller;

  private int speed;

  // visual fields
  JFrame editFrame;
  JPanel main;
  boolean loop;
  JButton start;
  JLabel speedLabel;
  JButton speedUp;
  JButton speedDown;

  ButtonGroup addDeleteShape;
  JTextField shapeName;
  JTextField shapeType;

  JTextField kfName;
  JTextField kfTime;

  JTextField modifyID;
  JTextField modifyT;
  JTextField modifyX;
  JTextField modifyY;
  JTextField modifyW;
  JTextField modifyH;
  JTextField modifyR;
  JTextField modifyG;
  JTextField modifyB;

  /**
   * Public constructor to create and EditView object.
   *
   * @param speed      integer for the speed of the animation
   * @param visualView Visual View that will show the animation
   */
  public EditView(int speed, VisualView visualView) {
    this.speed = speed;

    // Creating frame everything is in
    editFrame = new JFrame("EDIT VIEW");
    editFrame.setLayout(new BorderLayout());
    editFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // main panel
    main = new JPanel();
    main.setLayout(new BoxLayout(main, BoxLayout.PAGE_AXIS));

    // title & start button -> starts animation
    JLabel title = new JLabel("Edit view! Please edit below");
    title.setFont(new Font("TimesRoman", Font.PLAIN, 16));
    main.add(title);

    start = new JButton("Press to Start");
    start.setActionCommand("Start");
    start.addActionListener(new ButtonListener(this));
    start.setFont(new Font("TimesRoman", Font.PLAIN, 18));
    main.add(start);
    start.setBackground(Color.GREEN);

    // panel for pausing, resuming or restarting
    JPanel startPanel = new JPanel();
    startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.PAGE_AXIS));
    startPanel.setBorder(BorderFactory.createTitledBorder("Start/Pause"));
    main.add(startPanel);

    // panel for enabling/ disabling loop
    JPanel loopPanel = new JPanel();
    loopPanel.setLayout(new BoxLayout(loopPanel, BoxLayout.PAGE_AXIS));
    loopPanel.setBorder(BorderFactory.createTitledBorder("Loop"));
    main.add(loopPanel);

    // panel for editing the animation... so far inc/dec speed
    JPanel editPanel = new JPanel();
    editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.PAGE_AXIS));
    editPanel.setBorder(BorderFactory.createTitledBorder("Increase or Decrease Speed"));
    main.add(editPanel);

    editFrame.add(main);

    // creating the radio buttons for each panel
    JRadioButton[] radioButtons = new JRadioButton[5];
    String[] rbStrings = {"Pause", "Resume", "Restart", "Enable Looping", "Disable Looping"};

    ButtonGroup start = new ButtonGroup();
    ButtonGroup looping = new ButtonGroup();
    for (int i = 0; i < radioButtons.length; i++) {
      JRadioButton b;
      b = new JRadioButton(rbStrings[i]);
      b.setActionCommand(rbStrings[i]);
      b.setFont(new Font("TimesRoman", Font.PLAIN, 16));
      b.addActionListener(new ButtonListener(this));

      if (i <= 2) {
        start.add(b);
        startPanel.add(b);
      } else {
        looping.add(b);
        loopPanel.add(b);
      }
    }

    // buttons to speed up, speed down and label with speed
    speedUp = new JButton("Increase Speed");
    speedUp.setActionCommand("Up");
    speedUp.addActionListener(new ButtonListener(this));

    speedLabel = new JLabel("Speed: " + this.speed + " ticks/sec.");
    speedLabel.setFont(new Font("TimesRoman", Font.PLAIN, 16));

    speedDown = new JButton("Decrease Speed");
    speedDown.setActionCommand("Down");
    speedDown.addActionListener(new ButtonListener(this));

    // adding to the edit panel
    editPanel.add(speedUp);
    editPanel.add(speedLabel);
    editPanel.add(speedDown);

    // adding or deleting shapes in the shapes panel
    JPanel shapes = new JPanel();
    shapes.setLayout(new FlowLayout());
    shapes.setBorder(BorderFactory.createTitledBorder("Adding or deleting shape"));

    // adding shape button
    addDeleteShape = new ButtonGroup();
    JRadioButton add = new JRadioButton("Add a shape");
    add.setActionCommand("AddShape");
    add.addActionListener(new AddShapeListener(this));
    add.setFont(new Font("TimesRoman", Font.PLAIN, 14));
    add.setSelected(true);
    addDeleteShape.add(add);
    shapes.add(add);

    // removing shape button
    JRadioButton delete = new JRadioButton("Delete a shape");
    delete.setActionCommand("DeleteShape");
    delete.addActionListener(new AddShapeListener(this));
    delete.setFont(new Font("TimesRoman", Font.PLAIN, 14));
    addDeleteShape.add(delete);
    shapes.add(delete);

    // enter shape's name & type
    shapeName = new JTextField("Enter unique Shape ID");
    shapes.add(shapeName);
    shapeType = new JTextField("Enter either OVAL or RECTANGLE");
    shapes.add(shapeType);

    JButton enterShape = new JButton("Enter");
    enterShape.setFont(new Font("TimesRoman", Font.PLAIN, 14));
    enterShape.setActionCommand("ShapeEnter");
    enterShape.addActionListener(new AddShapeListener(this));
    shapes.add(enterShape);

    editPanel.add(shapes);

    // adding or deleting keyframe in the keyframe panel
    JPanel keyframes = new JPanel();
    keyframes.setLayout(new FlowLayout());
    keyframes.setBorder(BorderFactory.createTitledBorder("Adding or deleting keyframes"));

    // adding keyframe button
    ButtonGroup addDeleteKF = new ButtonGroup();
    JRadioButton addKF = new JRadioButton("Add a keyframe");
    addKF.setActionCommand("AddKF");
    addKF.addActionListener(new AddKFListener(this));
    addKF.setFont(new Font("TimesRoman", Font.PLAIN, 14));
    addDeleteKF.add(addKF);
    keyframes.add(addKF);

    // removing keyframe button
    JRadioButton deleteKF = new JRadioButton("Delete a keyframe");
    deleteKF.setActionCommand("DeleteKF");
    deleteKF.addActionListener(new AddKFListener(this));
    deleteKF.setFont(new Font("TimesRoman", Font.PLAIN, 14));
    addDeleteKF.add(deleteKF);
    keyframes.add(deleteKF);

    // enter shape's name & type
    kfName = new JTextField("Enter Shape ID for KeyFrame");
    keyframes.add(kfName);
    kfTime = new JTextField("Enter time");
    keyframes.add(kfTime);

    JButton enterKF = new JButton("Enter");
    enterKF.setFont(new Font("TimesRoman", Font.PLAIN, 14));
    enterKF.setActionCommand("KFEnter");
    enterKF.addActionListener(new AddKFListener(this));
    keyframes.add(enterKF);

    // time, color, location, dimension
    editPanel.add(keyframes);

    // modify keyframe
    JPanel modify = new JPanel();
    modify.setLayout(new FlowLayout());
    modify.setBorder(BorderFactory.createTitledBorder("Modify keyframes"));

    modifyID = new JTextField("Shape ID");
    modify.add(modifyID);
    modifyT = new JTextField("Time");
    modify.add(modifyT);
    modifyX = new JTextField("X   ");
    modify.add(modifyX);
    modifyY = new JTextField("Y   ");
    modify.add(modifyY);
    modifyW = new JTextField("W   ");
    modify.add(modifyW);
    modifyH = new JTextField("H   ");
    modify.add(modifyH);
    modifyR = new JTextField("R   ");
    modify.add(modifyR);
    modifyG = new JTextField("G   ");
    modify.add(modifyG);
    modifyB = new JTextField("B   ");
    modify.add(modifyB);

    JButton modifyKF = new JButton("Modify KeyFrame");
    modifyKF.setFont(new Font("TimesRoman", Font.PLAIN, 14));
    modifyKF.setActionCommand("Modify");
    modifyKF.addActionListener(new ModifyKFListener(this));
    modify.add(modifyKF);

    editPanel.add(modify);
    editFrame.pack();

    timer = new Timer(this.speed,
            new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                ArrayList<IReadOnlyShape> shapesRender = viewModel.getShapesAtTick(tick++);
                visualView.draw(shapesRender);
                speedUp.setEnabled(true);
                speedDown.setEnabled(true);
                if (loop) {
                  if (tick == viewModel.animationEndTime()) {
                    timer.stop();
                    tick = 1;
                    timer.restart();
                  }
                }
              }
            }
    );
  }

  @Override
  public void render(IViewModel model) {
    this.viewModel = model;
    this.editFrame.setVisible(true);
  }

  @Override
  public void edit(IController controller) {
    this.controller = controller;
  }

  /**
   * Starts the timer, and changes the text on the JButton of stop.
   */
  protected void startTime() {
    timer.start();
    this.start.setEnabled(false);
    this.start.setText("Motion is playing.");
  }

  /**
   * Increases or decreases speed by 10 if the boolean is true or false.
   *
   * @param up boolean representing if speed should be increased
   */
  protected void changeSpeed(boolean up) {

    if (up && (this.speed > 0)) {
      this.speed = this.speed - 10;
    } else {
      this.speed = this.speed + 10;
    }
    this.timer.setDelay(this.speed);
    this.speedLabel.setText("Speed: " + this.speed + " ticks/sec.");
  }

  /**
   * Pauses the timer, disables the ability to increase or decrease speed.
   */
  protected void pause() {
    this.timer.stop();
    this.start.setText("Motion is paused.");
    this.start.setBackground(Color.RED);
  }

  /**
   * Resumes the timer, enables the ability to increase or decrease speed again.
   */
  protected void resume() {
    this.speedUp.setEnabled(true);
    this.speedDown.setEnabled(true);
    this.start.setText("Motion is resumed.");
    this.start.setBackground(Color.GREEN);
    timer.restart();
  }

  /**
   * Restarts the animation from the start.
   */
  protected void restart() {
    tick = 1;
    timer.restart();
    this.start.setText("Motion is restarted.");
    this.start.setBackground(Color.GREEN);
  }

  /**
   * Sets the loop field to the boolean given.
   *
   * @param l boolean representing whether or not looping ability should be enabled
   */
  protected void loop(boolean l) {
    this.loop = l;
  }

  /**
   * Adds an IShape to the IModel, with the given name and Shape type.
   *
   * @param name String representing unique name for the new Shape
   * @param type Shape type for the new Shape
   */
  protected void addShape(String name, Shape type) {
    try {
      this.controller.addShape(name, type);
    } catch (IllegalArgumentException i) {
      JFrame frame = new JFrame();
      JOptionPane.showMessageDialog(frame, "Error! " + i.getMessage());
    }
  }

  /**
   * Removes an IShape from the IModel, the name and Shape type must match and exist.
   *
   * @param name String representing unique name for the new Shape
   * @param type Shape type for the new Shape
   */
  protected void deleteShape(String name, Shape type) {
    try {
      this.controller.deleteShape(name, type);
    } catch (Exception i) {
      JFrame frame = new JFrame();
      JOptionPane.showMessageDialog(frame, "Error! " + i.getMessage());
    }
  }

  /**
   * Adds a KeyFrame for the given Shape at the given time.
   *
   * @param name String representing unique name of the Shape
   * @param time time of wanted Keyframe
   */
  protected void addKeyFrame(String name, int time) {
    try {
      this.controller.addKeyFrame(name, time);
    } catch (Exception i) {
      JFrame frame = new JFrame();
      JOptionPane.showMessageDialog(frame, "Error! " + i.getMessage());
    }
  }

  /**
   * Deletes a KeyFrame for the given Shape at the given time.
   *
   * @param name String representing unique name of the Shape
   * @param time time at which want KeyFrame to be deleted
   */
  protected void deleteKeyFrame(String name, int time) {
    try {
      this.controller.deleteKeyFrame(name, time);
    } catch (IllegalArgumentException i) {
      JFrame frame = new JFrame();
      JOptionPane.showMessageDialog(frame, "Error! " + i.getMessage());
    }
  }

  /**
   * Modifies KeyFrame for the given Shape to the given characteristics.
   *
   * @param name String representing unique name of the Shape
   * @param t    time at which want to modify KeyFrame
   * @param x    new X location of the Shape
   * @param y    new Y location of the Shape
   * @param w    new width boundary box for the Shape
   * @param h    new height boundary box for the Shape
   * @param r    new r value for the Color for the Shape
   * @param g    new g value for the Color for the Shape
   * @param b    new b value for the Color for the Shape
   */
  protected void modifyKeyFrame(String name, int t, int x, int y, int w, int h, int r, int g, int b) {
    try {
      this.controller.modifyKeyFrame(name, t, x, y, w, h, r, g, b);
      this.timer.restart();
    } catch (IllegalArgumentException i) {
      JFrame frame = new JFrame();
      JOptionPane.showMessageDialog(frame, "Error! " + i.getMessage());
    }
  }
}
