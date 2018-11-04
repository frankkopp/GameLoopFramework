package fko.gameloop.gameloop;

import fko.gameloop.model.Model;
import fko.gameloop.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>VariableStepsGameLoop</b>
 *
 * <p>A VariableGameLoop uses a variable time to update the model. Basically the time elapsed since
 * last update. This works fine as long as the elapsed time is not too small (precession problems in
 * floats) or too large (model updates too big leading to physics issues like tunneling of objects
 * during collision detection)<br>
 * It has a therefore a maximum Step size for the model update. This step size needs to be chosen in
 * way that the model update works correctly (e.g. collision detection).<br>
 * We will ignore step sizes becoming too small as this i not a likely scenario in Java as model
 * updates will always take some time.<br>
 * <p/>
 * Inspiration by <a href="http://svanimpe.be/blog/game-loops-fx">Game loops Applying the theory
 * to JavaFX</a> by Steven Van Imp
 */
public class VariableStepsGameLoop extends GameLoop {

  private static final Logger LOG = LoggerFactory.getLogger(VariableStepsGameLoop.class);

  private long previousTime;
  private float secondsElapsedSinceLastFpsUpdate = 0f;
  private int framesSinceLastFpsUpdate = 0;

  public VariableStepsGameLoop(final Model model, final View view) {
    super(model, view);
  }

  @Override
  public void handle(final long now) {

    if (previousTime == 0) {
      previousTime = now;
      return;
    }

    // calculate elapsed time for this step
    float secondsElapsed = (now - previousTime) / 1e9f; /* nanoseconds to seconds */
    float secondsElapsedCapped = Math.min(secondsElapsed, getMaxStep());
    previousTime = now;
    LOG.debug("Elapsed: " + secondsElapsed + " Capped: " + secondsElapsedCapped + " Now: " + now);
    if (secondsElapsed > secondsElapsedCapped) {
      LOG.info("Model update too slow --> FPS too low");
    }

    // update model with maximum time step
    model.update(secondsElapsedCapped);
    // update view
    view.update();

    // FPS reporting
    secondsElapsedSinceLastFpsUpdate += secondsElapsed;
    framesSinceLastFpsUpdate++;
    int fps = Math.round(framesSinceLastFpsUpdate / secondsElapsedSinceLastFpsUpdate);
    LOG.debug("FPS: " + fps);
    secondsElapsedSinceLastFpsUpdate = 0;
    framesSinceLastFpsUpdate = 0;
  }

  @Override
  public void stop()
  {
    previousTime = 0;
    secondsElapsedSinceLastFpsUpdate = 0f;
    framesSinceLastFpsUpdate = 0;
    super.stop();
  }
}
