package src;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.util.converter.IntegerStringConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class GUI extends Application {
    private InterfaceLoop simulator;
    private Map<String, Scene> scenes;
    private Stage primaryStage;
    private Stack<String> visitedScenes;
    private Stack<String> forwardScenes;
    private java.io.ByteArrayOutputStream out;

    public void start(Stage primaryStage) throws Exception {;
        simulator = new InterfaceLoop();
        System.out.println("Welcome to the Restaurant Supply Express System!");
        out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));

        this.primaryStage = primaryStage;
        scenes = new HashMap<String, Scene>();
        visitedScenes = new Stack<String>();
        forwardScenes = new Stack<String>();
        visitedScenes.push("Main");
        scenes.put("Main", createMainScene());

        this.primaryStage.setTitle("Drone Ingredient Delivery Services");
        this.primaryStage.setScene(scenes.get("Main"));
        this.primaryStage.show();
    }

    private void switchScenes(String next, boolean clearForward) {
        if (clearForward) {
            forwardScenes.clear();
        }
        out.reset();
        Scene scene = scenes.get(next);
        ((BorderPane) scene.getRoot()).setTop(createHomeBar(next));
        primaryStage.setScene(scene);
    }

    private HBox createHomeBar(String next) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15,12,15,12));
        hbox.setSpacing(10);

        if (!next.equals("Main")) {
            Image img = new Image("homeButton.png", 40, 40, true, true);
            ImageView view = new ImageView(img);
            Button home = new Button();
            home.setPrefSize(40, 40);
            home.setGraphic(view);
            home.setOnAction(
                    e -> {
                        visitedScenes.push(next);
                        switchScenes("Main", true);
                    }
            );
            hbox.getChildren().addAll(home);
        }

        if (!visitedScenes.isEmpty()) {
            Image img2 = new Image("backButton.png", 40, 40, true, true);
            ImageView view2 = new ImageView(img2);
            Button back = new Button();
            back.setPrefSize(40, 40);
            back.setGraphic(view2);
            back.setOnAction(
                    e -> {
                        forwardScenes.push(next);
                        switchScenes(visitedScenes.pop(), false);
                    }
            );
            hbox.getChildren().addAll(back);
        }

        if (!forwardScenes.isEmpty()) {
            Image img3 = new Image("forwardButton.png", 40, 40, true, true);
            ImageView view3 = new ImageView(img3);
            Button forward = new Button();
            forward.setPrefSize(40, 40);
            forward.setGraphic(view3);
            forward.setOnAction(
                    e -> {
                        visitedScenes.push(next);
                        switchScenes(forwardScenes.pop(), false);
                    }
            );
            hbox.getChildren().addAll(forward);
        }
        return hbox;
    }

    private Scene createMainScene() {
        Button create = new Button("Create an Object");
        create.setOnAction(
                e -> {
                    if (!scenes.containsKey("Make")) {
                        scenes.put("Make", createMakeScene());
                    }
                    switchScenes("Make", true);
                }
        );
        Button print = new Button("Print Status");
        Button control = new Button("Change State");


        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15,12,15,12));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(create, print, control);

        BorderPane pane = new BorderPane();
        pane.setCenter(hbox);
        Scene scene = new Scene(pane, 1920, 1080);
        return scene;
    }

    private Scene createMakeScene() {
        Button ingredient = new Button("Make Ingredient");
        ingredient.setOnAction(
                e -> {
                    if (!scenes.containsKey("MakeIngredient")) {
                        scenes.put("MakeIngredient", makeIngredient());
                    }
                    switchScenes("MakeIngredient", true);
                }
        );
        Button location = new Button("Make Location");
        Button deliveryService = new Button("Make Delivery Service");
        Button restaurant = new Button("Make Restaurant");
        Button drone = new Button("Make Drone");
        Button person = new Button("Make Person");

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15,12,15,12));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(ingredient, location, deliveryService, restaurant, drone, person);

        BorderPane pane = new BorderPane();
        pane.setCenter(hbox);
        Scene scene = new Scene(pane, 1920, 1080);
        return scene;
    }

    private Scene makeIngredient() {
        TextField barcode = new TextField();
        barcode.setPromptText("Type barcode here");
        TextField name = new TextField();
        name.setPromptText("Type name here");
        TextField weight = new TextField();
        weight.setPromptText("Type weight here (int)");
        weight.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        Button create = new Button("Create!");
        Label response = new Label();
        create.setOnAction(
                e -> {
                    if (barcode.getText().isEmpty() || name.getText().isEmpty() || weight.getText().isEmpty()) {
                        System.out.println("You cannot leave a field blank.");
                    } else {
                        simulator.makeIngredient(barcode.getText(), name.getText(), Integer.parseInt(weight.getText()));
                    }
                    response.setText(out.toString());
                }
        );

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15,12,15,12));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(barcode, name, weight, create, response);

        VBox vbox = new VBox();
        hbox.setPadding(new Insets(12,12,12,12));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(hbox, response);

        BorderPane pane = new BorderPane();
        pane.setCenter(vbox);
        Scene scene = new Scene(pane, 1920, 1080);
        return scene;
    }
}
