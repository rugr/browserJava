package browserJava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Ruslan on 18.12.2017.
 */
public class StartBrowserJava extends Application {

    @Override
    public void init() throws Exception {
        System.out.println("===== init StartBrowserJava =======");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        System.out.println("===== start StartBrowserJava =======");
    }

    @Override
    public void stop() throws Exception {
        System.out.println("===== stop StartBrowserJava =======");
    }
}
