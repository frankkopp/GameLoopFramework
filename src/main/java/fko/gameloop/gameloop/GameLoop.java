package fko.gameloop.gameloop;

import fko.gameloop.controller.Controller;
import fko.gameloop.model.Model;
import fko.gameloop.view.View;
import javafx.animation.AnimationTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GameLoop
 */
public abstract class GameLoop extends AnimationTimer {

 private static final Logger LOG = LoggerFactory.getLogger(GameLoop.class);

  private float maxStep = Float.MAX_VALUE;

  protected Model model;
  protected View view;

  public GameLoop(final Model model, final View view) {
    this.model = model;
    this.view = view;
  }

  /**
   * @return maximum possible time step
   */
  public float getMaxStep() { return maxStep; }

  /**
   * Sets maximum possible time step
   * @param maxStep
   * */
  public void setMaxStep(final float maxStep) { this.maxStep = maxStep; }

}
