package browserJava.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Controller {
    @FXML
    public TabPane tabPane;
    public Tab tab1;

    public void initialize(){
        System.out.println("====== initialize Controller =======");
        addTab ();
    }

    @FXML
    void addTab (){
        tabPane.getTabs().remove(tab1);
        Tab newTab = new Tab();
        AnchorPane newAnchorPane = new AnchorPane();
        TextField newTextField = new TextField();
        WebView newWebView = new WebView();
        ProgressBar progressBar = new ProgressBar();
        newWebView.setLayoutY(26);
        WebEngine newEngine = newWebView.getEngine();
        newEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>() {
                    @Override public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {
                        progressBar.setVisible(true);
                        if (newState == Worker.State.SUCCEEDED) {
                            progressBar.setVisible(false);
                            newTab.setText(newEngine.getTitle());
                            newTextField.setText(newEngine.getLocation());
                            try {
                                String faviconUrl = String.format("http://www.google.com/s2/favicons?domain_url=" + newEngine.getLocation(), URLEncoder.encode("charset", "UTF-8"));
                                Image favicon = new Image(faviconUrl, true);
                                ImageView iv = new ImageView(favicon);
                                newTab.setGraphic(iv);
                            } catch (UnsupportedEncodingException ex) {
                                throw new RuntimeException(ex); // not expected
                            }
                        }

                    }
                });

        newTextField.setOnAction((event) -> {
            newEngine.load(newTextField.getText());
            newTab.setText(newTextField.getText());

        });

        Worker<Void> worker = newEngine.getLoadWorker();
        progressBar.progressProperty().bind(worker.progressProperty());
        newEngine.load("https://www.google.com.ua");
        newAnchorPane.getChildren().add(newTextField);
        AnchorPane.setLeftAnchor(newTextField, 0.0);
        AnchorPane.setRightAnchor(newTextField, 0.0);
        newAnchorPane.getChildren().add(newWebView);
        AnchorPane.setLeftAnchor(newWebView, 0.0);
        AnchorPane.setRightAnchor(newWebView, 0.0);
        AnchorPane.setTopAnchor(newWebView, 26.0);
        AnchorPane.setBottomAnchor(newWebView, 0.0);
        newAnchorPane.getChildren().add(progressBar);
        progressBar.setPrefHeight(25);
        newTab.setContent(newAnchorPane);
        tabPane.getTabs().add(newTab);
        tabPane.getSelectionModel().select(newTab);
        tabPane.getTabs().add(tab1);
    }
}
