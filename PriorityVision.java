import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class PriorityVision extends Application {
    int count = 0;
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        //root.setBorder(new Border(new BorderStroke(Color.DARKGREY, BorderStrokeStyle.SOLID,
                //CornerRadii.EMPTY, new BorderWidths(5))));
        Scene scene = new Scene(root, 1300, 700);

        // Setting up header strip
        GridPane header = new GridPane();
        header.setStyle("-fx-background-color: #4473C5;");
        header.getColumnConstraints().add(new ColumnConstraints(1097));
        header.getColumnConstraints().add(new ColumnConstraints(300));

        Label clock = new Label();
        Label name = new Label("PriorityVision");

        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/uuuu HH:mm:ss");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            LocalDateTime now = LocalDateTime.now();
            clock.setText(format.format(now));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        name.setStyle("-fx-font-family: Arial; -fx-font-size: 20px; -fx-text-fill: white; -fx-padding: 9 0 9 10;");
        clock.setStyle("-fx-font-family: Arial; -fx-font-size: 20px; -fx-text-fill: white; -fx-padding: 9 0 9 0;");
        header.add(name, 0, 0);
        header.add(clock, 1, 0);

        // Setting up Accidents Location and Accident Priority Dashboard Strip
        GridPane row2 = new GridPane();
        row2.setGridLinesVisible(true);
        Label al = new Label("Accidents Location");
        Label ap = new Label("Accident Priority Dashboard");
        row2.setStyle("-fx-background-color: #FFE699;");
        row2.getColumnConstraints().add(new ColumnConstraints(0.4 * 1300));
        row2.getColumnConstraints().add(new ColumnConstraints(0.6 * 1300));

        row2.add(new StackPane(al), 0, 0);
        row2.add(new StackPane(ap), 1, 0);

        al.setStyle("-fx-font-family: Arial; -fx-font-size: 18px; -fx-text-fill: black; -fx-padding: 9 0 9 5; -fx-alignment: center;");
        ap.setStyle("-fx-font-family: Arial; -fx-font-size: 18px; -fx-text-fill: black; -fx-padding: 9 0 9 0; -fx-alignment: center;");

        // Setting up
        GridPane row3 = new GridPane();
        row3.getColumnConstraints().add(new ColumnConstraints(0.4 * 1300));
        row3.getColumnConstraints().add(new ColumnConstraints(0.6 * 1300));

        ImageView image = null;
        try {
            image = new ImageView(new Image(new FileInputStream("Map.png")));
        } catch (FileNotFoundException ex) {}
        StackPane map = new StackPane(image);
        image.setFitWidth(0.4 * 1300);
        image.setFitHeight(620);

        //Dashboard goes here
        ObservableList<Accident> data =
                FXCollections.observableArrayList(
                        new Accident("#1", "High", "College Park", "Feed 7", "Passenger Vehicle", "Caller Description")
                );

        TableView<Accident> table = new TableView<>();
        table.setEditable(true);
        TableColumn num = new TableColumn();
        num.setMinWidth(50);
        num.setMaxWidth(50);
        num.setCellValueFactory(
                new PropertyValueFactory<Accident, String>("num"));

        TableColumn priority = new TableColumn("Priority");
        priority.setMinWidth(100);
        priority.setCellValueFactory(
                new PropertyValueFactory<Accident, String>("priority"));

        TableColumn location = new TableColumn("Location");
        location.setMinWidth(150);
        location.setCellValueFactory(
                new PropertyValueFactory<Accident, String>("location"));

        TableColumn feedLink = new TableColumn("Feed Link");
        feedLink.setMinWidth(150);
        feedLink.setCellValueFactory(
                new PropertyValueFactory<Accident, String>("feedLink"));

        TableColumn vehicles = new TableColumn("Vehicles Involved");
        vehicles.setMinWidth(150);
        vehicles.setCellValueFactory(
                new PropertyValueFactory<Accident, String>("vehicles"));

        TableColumn notes = new TableColumn("Notes");
        notes.setMinWidth(175);
        notes.setCellValueFactory(
                new PropertyValueFactory<Accident, String>("notes"));

        table.setItems(data);
        table.getColumns().addAll(num, priority, location, feedLink, vehicles, notes);

        row3.add(map, 0, 0);
        row3.add(table, 1,  0);

        root.add(header, 0, 0);
        root.add(row2, 0, 1);
        root.add(row3, 0, 2);

        root.setGridLinesVisible(true);
        
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static class Accident {
        private SimpleStringProperty num;
        private SimpleStringProperty priority;
        private SimpleStringProperty location;
        private SimpleStringProperty feedLink;
        private SimpleStringProperty vehicles;
        private SimpleStringProperty notes;

        private Accident(String num, String priority, String location, String feedLink, String vehicles, String notes) {
            this.num = new SimpleStringProperty(num);
            this.priority = new SimpleStringProperty(priority);
            this.location = new SimpleStringProperty(location);
            this.feedLink = new SimpleStringProperty(feedLink);
            this.vehicles = new SimpleStringProperty(vehicles);
            this.notes = new SimpleStringProperty(notes);
        }

        public String getNum() {
            return num.get();
        }

        public void setNum(String num) {
            this.num.set(num);
        }

        public String getPriority() {
            return priority.get();
        }

        public void setPriority(String priority) {
            this.priority.set(priority);
        }

        public String getLocation() {
            return location.get();
        }

        public void setLocation(String location) {
            this.location.set(location);
        }

        public String getFeedLink() {
            return feedLink.get();
        }

        public void setFeedLink(String feedLink) {
            this.feedLink.set(feedLink);
        }

        public String getVehicles() {
            return vehicles.get();
        }

        public void setVehicles(String vehicles) {
            this.vehicles.set(vehicles);
        }

        public String getNotes() {
            return notes.get();
        }

        public void getNotes(String notes) {
            this.notes.set(notes);
        }

    }
}
