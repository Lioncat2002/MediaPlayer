package sample;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;

import static javafx.scene.media.MediaPlayer.*;

public class MediaBar extends HBox {
    Slider time=new Slider();
    Slider vol=new Slider();
    Button playbutton=new Button("||");
    Label volume=new Label("Volume: ");
    MediaPlayer player;
    public MediaBar(MediaPlayer p) {
        player=p;
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5,10,5,10));
        vol.setPrefWidth(70);
        vol.setPrefHeight(30);
        vol.setValue(100);
        HBox.setHgrow(time, Priority.ALWAYS);
        playbutton.setPrefWidth(30);

        getChildren().add(playbutton);
        getChildren().add(time);
        getChildren().add(vol);
        getChildren().add(volume);

        playbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Status status=player.getStatus();
                if (status==status.PLAYING)
                {
                    if (player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration()))
                    {
                        player.seek(player.getStartTime());
                        player.play();
                    }else {
                        player.pause();
                        playbutton.setText(">");

                    }

                }
                if (status==status.HALTED||status==status.STOPPED||status==status.PAUSED)
                {
                    player.play();
                    playbutton.setText("||");
                }
            }
        });


        time.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (vol.isPressed()){
                    player.setVolume(vol.getValue()/100);
                }
            }
        });
    }
    protected void updatesValue()
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                time.setValue((player.getTotalDuration().toMillis()/player.getCurrentTime().toMillis())*100);
            }
        });
    }
}
