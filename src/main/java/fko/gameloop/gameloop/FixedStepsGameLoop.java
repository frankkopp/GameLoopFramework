package fko.gameloop.gameloop;

import fko.gameloop.model.Model;
import fko.gameloop.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>FixedStepsGameLoop</b>
 *
 * <p/>A FixedStepsGameLoop uses a fixed time to update the model. Advantage of a fixed time update is that the
 * model always is updated by the same "amount" thus allowing deterministic behavior which is easier to understand
 * and to debug.
 * Disadvantage is that it often lags a bit behind thus accumulating time which might lead to repeated updates of the model
 * during on e cycle which leads to "jumps" in the rendered output
 *
 * <p/>
 * Inspiration by <a href="http://svanimpe.be/blog/game-loops-fx">Game loops Applying the theory
 * to JavaFX</a> by Steven Van Imp
 */
public class FixedStepsGameLoop extends GameLoop {

  private static final Logger LOG = LoggerFactory.getLogger(FixedStepsGameLoop.class);

  public static final float TIME_STEP = 1f/60f; // 60 fps

  private long previousTime;
  private float accumulatedTime = 0;

  private float secondsElapsedSinceLastFpsUpdate = 0f;
  private int framesSinceLastFpsUpdate = 0;

  public FixedStepsGameLoop(final Model model, final View view) {
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
    previousTime = now;

    float secondsElapsedCapped = Math.min(secondsElapsed, getMaxStep());
    accumulatedTime += secondsElapsedCapped;

    LOG.debug("Elapsed: " + secondsElapsed + " Capped: " + secondsElapsedCapped + " Now: " + now);

    // update model with maximum time step as often as possible during the step
    int counter=0;
    while (accumulatedTime >= TIME_STEP) {
      LOG.debug("AccTime: " + accumulatedTime);
      if (++counter > 1)
        LOG.info("REPEAT MODEL UPDATE");

      model.update(TIME_STEP);

      accumulatedTime -= TIME_STEP;
    }
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
