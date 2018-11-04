package fko.gameloop;

import fko.gameloop.controller.Controller;
import fko.gameloop.gameloop.GameLoop;
import fko.gameloop.gameloop.VariableStepsGameLoop;
import fko.gameloop.model.Model;
import fko.gameloop.view.View;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GameLoopFramework
 */
public class GameLoopFramework extends Application {

  private static final Logger LOG = LoggerFactory.getLogger(GameLoopFramework.class);

  private static Stage pStage;
  private Scene scene;

  /**
   * Main
   */
  public static void main(String[] args) {
    // setting up logging
    LOG.info("Launching Application starting with args: {}", (Object) args);
    launch(args);
  }

  /**
   * @see javafx.application.Application#start(javafx.stage.Stage)
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    LOG.info("Starting JavaFX Application");
    pStage = primaryStage;

    // create model, view, controller
    Model model = new Model();
    Controller controller = new Controller(model);
    View view = new View(model, controller);
    GameLoop gameloop = new VariableStepsGameLoop(model, view);
    controller.setGameLoop(gameloop);

    // set initial window title - can be extended in controller
    primaryStage.setTitle("Application by Frank Kopp");
    scene = new Scene(view.asParent());
    primaryStage.setScene(scene);
    primaryStage.centerOnScreen();
    primaryStage.setResizable(true);
    primaryStage.setHeight(300d);
    primaryStage.setWidth(400d);
    // closeAction
    primaryStage.setOnCloseRequest(event -> exit());
    // finally show window
    primaryStage.show();

    LOG.info("JavaFX Application started");
  }

  /**
   * @see javafx.application.Application#init()
   */
  @Override
  public void init() throws Exception {
    super.init();

    /* TODO: do stuff e.g. load fonts etc. */
  }

  /**
   * Clean up and exit the application
   */
  public static void exit() {
    LOG.info("Application shutting down...");

    /* TODO: Clean up */

    LOG.info("Application shut down");
    exit(0);
  }

  /**
   * Clean up and exit the application
   */
  private static void exit(int returnCode) {
    Platform.exit();
    System.exit(returnCode);
  }

  public static Stage getPrimaryStage() { return pStage; }

}
