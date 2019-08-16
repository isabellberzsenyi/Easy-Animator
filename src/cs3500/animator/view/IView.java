package cs3500.animator.view;

import java.util.ArrayList;

import cs3500.animator.controller.Controller;
import cs3500.animator.controller.IController;

/**
 * Represents the viewable animation that is created. It renders the animation as various types of
 * views.
 */
public interface IView {

  /**
   * Renders this IView as a visual, text or svg.
   *
   * @param model  the IViewModel that this IView is using
   */
  void render(IViewModel model);

  void edit(IController controller);
}
