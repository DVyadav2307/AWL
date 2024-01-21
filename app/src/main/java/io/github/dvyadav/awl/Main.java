package io.github.dvyadav.awl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application   {
    
  
    @Override
    public void start(Stage primaryStage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("Sigin-Login-Page.fxml"));
      Scene scene  = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.setTitle("AWL: Attendence Watch Log");
      primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/interface/icon.png")));
      primaryStage.setResizable(false);
      primaryStage.show();
       
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
