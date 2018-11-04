package fko.gameloop.model;

import fko.gameloop.gameloop.GameLoop;
import fko.gameloop.gameloop.VariableStepsGameLoop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Model
 */
public class Model {

  private static final Logger LOG = LoggerFactory.getLogger(Model.class);

  public Model() {
    LOG.info("Creating Model...");
    LOG.info("Model created.");
  }

  public void update(final float secondsElapsed) {
    //OG.debug("Update model for "+secondsElapsed+ "sec time delta");
    /* SIMULATE MODEL UPDATE */
    try {
      Thread.sleep((long) (Math.random()*1000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
