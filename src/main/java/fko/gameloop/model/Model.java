package fko.gameloop.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Model
 */
public class Model {

  private static final Logger LOG = LoggerFactory.getLogger(Model.class);

  private double timeInWorld = 0;

  public Model() {
    LOG.info("Creating Model...");
    LOG.info("Model created.");
  }

  public void update(final float secondsElapsed) {
    LOG.info("Update model for "+secondsElapsed+ "sec time delta");
    timeInWorld += secondsElapsed;
    /* SIMULATE MODEL UPDATE */
    try {
      Thread.sleep((long) (Math.random()*20));
      //Thread.sleep(20);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public double getTimeInWorld() {
    return timeInWorld;
  }
}
