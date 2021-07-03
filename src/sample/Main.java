package sample;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;


public class Main extends Application {
    Player player;
    FileChooser fileChooser;

    //MenuItem open=new MenuItem("Open");
    @Override
    public void start(Stage primaryStage) throws Exception{
        Menu file=new Menu("File");
        MenuItem open= new MenuItem("Open");
        MenuBar bar=new MenuBar();
        file.getItems().add(open);
        bar.getMenus().add(file);
        fileChooser=new FileChooser();
        open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                player.player.pause();
                File file=fileChooser.showOpenDialog(primaryStage);
                if (file!=null)
                {
                    try {
                        player=new Player(file.toURI().toURL().toExternalForm());
                        Scene scene=new Scene(player,720,800, Color.BLACK);
                        primaryStage.setScene(scene);
                    }catch (MalformedURLException e)
                    {
                        e.printStackTrace();
                    }
                }

            }

        });
        player=new Player("file:///D:/JavaProjects/MediaPlayer/video.mp4");
        player.setTop(bar);

        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        Scene scene=new Scene(player,720,800, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
