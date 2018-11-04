package fko.gameloop.controller;

import fko.gameloop.gameloop.GameLoop;
import fko.gameloop.model.Model;
import javafx.event.ActionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller
 */
public class Controller {

  private static final Logger LOG = LoggerFactory.getLogger(Controller.class);

  private Model model;
  private GameLoop gameLoop;

  public Controller(final Model model) {
    this.model = model;

    LOG.info("Creating Controller...");
    /* TODO */
    LOG.info("Controller created.");
  }

  public void startButtonAction(final ActionEvent actionEvent) {
    LOG.debug("Start Button pressed");
    if (gameLoop == null) {
      final String e = "GameLoop for Controller is not set.";
      LOG.error(e);
      throw new RuntimeException(e);
    }
    // set 60 frame per second as max step
    gameLoop.setMaxStep(1f/30f);
    gameLoop.start();
  }

  public void stopButtonAction(final ActionEvent actionEvent) {
    LOG.debug("Stop Button pressed");
    if (gameLoop == null) {
      final String e = "GameLoop for Controller is not set.";
      LOG.error(e);
      throw new RuntimeException(e);
    }
    gameLoop.stop();

  }

  public void setGameLoop(final GameLoop gameloop) {
    this.gameLoop = gameloop;
  }
}
