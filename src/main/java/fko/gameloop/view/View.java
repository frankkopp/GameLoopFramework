package fko.gameloop.view;

import fko.gameloop.controller.Controller;
import fko.gameloop.model.Model;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * View
 */
public class View {

  private static final Logger LOG = LoggerFactory.getLogger(View.class);

  private final Pane root;

  public View(final Model model, final Controller controller) {
    LOG.info("Creating View...");

    Button startButton = new Button();
    startButton.isDefaultButton();
    startButton.setText("START");
    startButton.setOnAction(controller::startButtonAction);
    Button stopButton = new Button();
    stopButton.isDefaultButton();
    stopButton.setText("STOP");
    stopButton.setOnAction(controller::stopButtonAction);

    root  = new FlowPane();
    root.getChildren().addAll(startButton, stopButton);

    LOG.info("View created.");
  }

  /** @return root pane from loaded FXML */
  public Parent asParent() {
    return root;
  }

  public void update() {

  }
}

