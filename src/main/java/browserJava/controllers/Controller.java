package browserJava.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Controller {

    @FXML
    public WebView webView = new WebView();
    @FXML
    public TextField webLocation;

    public WebEngine engine;

    @FXML
    Tab tab1;

    public void initialize(){
        System.out.println("====== initialize Controller =======");
        startWebView();
    }


    void startWebView() {
//        Worker<Void> worker = engine.getLoadWorker();
//        progressBar.progressProperty().bind(worker.progressProperty());
//        progressIndicator.progressProperty().bind(worker.progressProperty());
        engine = webView.getEngine();

        engine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>() {
                    @Override public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {

                        if (newState == Worker.State.SUCCEEDED) {
                            setTabText ();
                        }

                    }
                });

        engine.load("https://www.google.com");
    }

    @FXML
    public void setTabText () {
        tab1.setText(engine.getTitle());
        webLocation.setText(engine.getLocation());
    }

    @FXML
    void goToLocation (){
        engine.load(webLocation.getText());
        tab1.setText(webLocation.getText());
    }
}
