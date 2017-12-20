package browserJava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        Parent root = FXMLLoader.load(getClass().getResource("/views/container.fxml"));
        primaryStage.setTitle("RG BROWSER");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.getIcons().add(new Image(getClass().getResource("/images/browser.png").toString()));
        primaryStage.show();
        System.out.println("===== start StartBrowserJava =======");
    }

    @Override
    public void stop() throws Exception {
        System.out.println("===== stop StartBrowserJava =======");
    }
}
