package src;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.*;
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

    public void start(Stage primaryStage) throws Exception {
        ;
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

    private void switchScenes(String next, String curr, boolean pushVisited, boolean clearForward) {
        if (clearForward) {
            forwardScenes.clear();
        }
        if (pushVisited) {
            visitedScenes.push(curr);
        }
        out.reset();
        Scene scene = scenes.get(next);
        ((BorderPane) scene.getRoot()).setTop(createHomeBar(next, curr));
        primaryStage.setScene(scene);
    }

    private HBox createHomeBar(String next, String curr) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);

        if (!next.equals("Main")) {
            Image img = new Image("homeButton.png", 40, 40, true, true);
            ImageView view = new ImageView(img);
            Button home = new Button();
            home.setPrefSize(40, 40);
            home.setGraphic(view);
            home.setOnAction(
                    e -> {
                        switchScenes("Main", curr, true, false);
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
                        switchScenes(visitedScenes.pop(), curr, false, false);
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
                        switchScenes(forwardScenes.pop(), curr, true, false);
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
                    switchScenes("Make", "Main", true, true);
                }
        );
        Button print = new Button("Print Status");
        print.setOnAction(
                e -> {
                    if (!scenes.containsKey("Print")) {
                        scenes.put("Print", createPrintScene());
                    }
                    switchScenes("Print", "Main", true, true);
                }
        );
        Button control = new Button("Change State");
        control.setOnAction(
                e -> {
                    if (!scenes.containsKey("Control")) {
                        scenes.put("Control", createControlScene());
                    }
                    switchScenes("Control", "Main", true, true);
                }
        );

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
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
                    switchScenes("MakeIngredient", "Make", true, true);
                }
        );
        Button location = new Button("Make Location");
        location.setOnAction(
                e -> {
                    if (!scenes.containsKey("MakeLocation")) {
                        scenes.put("MakeLocation", makeLocation());
                    }
                    switchScenes("MakeLocation", "Make", true, true);
                }
        );
        Button deliveryService = new Button("Make Delivery Service");
        deliveryService.setOnAction(
                e -> {
                    if (!scenes.containsKey("MakeDeliveryService")) {
                        scenes.put("MakeDeliveryService", makeDeliveryService());
                    }
                    switchScenes("MakeDeliveryService", "Make", true, true);
                }
        );
        Button restaurant = new Button("Make Restaurant");
        restaurant.setOnAction(
                e -> {
                    if (!scenes.containsKey("MakeRestaurant")) {
                        scenes.put("MakeRestaurant", makeRestaurant());
                    }
                    switchScenes("MakeRestaurant", "Make", true, true);
                }
        );
        Button drone = new Button("Make Drone");
        drone.setOnAction(
                e -> {
                    if (!scenes.containsKey("MakeDrone")) {
                        scenes.put("MakeDrone", makeDrone());
                    }
                    switchScenes("MakeDrone", "Make", true, true);
                }
        );
        Button person = new Button("Make Person");
        person.setOnAction(
                e -> {
                    if (!scenes.containsKey("MakePerson")) {
                        scenes.put("MakePerson", makePerson());
                    }
                    switchScenes("MakePerson", "Make", true, true);
                }
        );

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
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
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(barcode, name, weight, create);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(12, 12, 12, 12));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(hbox, response);

        BorderPane pane = new BorderPane();
        pane.setCenter(vbox);
        Scene scene = new Scene(pane, 1920, 1080);
        return scene;
    }

    private Scene makeLocation() {
        TextField name = new TextField();
        name.setPromptText("Type name here");
        TextField xCoord = new TextField();
        xCoord.setPromptText("Type x coordinate here (int)");
        xCoord.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        TextField yCoord = new TextField();
        yCoord.setPromptText("Type y coordinate here (int)");
        yCoord.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        TextField spaceLimit = new TextField();
        spaceLimit.setPromptText("Type space limit here");
        spaceLimit.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

        Button create = new Button("Create!");
        Label response = new Label();
        create.setOnAction(
                e -> {
                    if (name.getText().isEmpty() || xCoord.getText().isEmpty() || yCoord.getText().isEmpty() || spaceLimit.getText().isEmpty()) {
                        System.out.println("You cannot leave a field blank.");
                    } else {
                        simulator.makeLocation(name.getText(), Integer.parseInt(xCoord.getText()), Integer.parseInt(yCoord.getText()), Integer.parseInt(spaceLimit.getText()));
                    }
                    response.setText(out.toString());
                }
        );

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(name, xCoord, yCoord, spaceLimit, create);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(12, 12, 12, 12));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(hbox, response);

        BorderPane pane = new BorderPane();
        pane.setCenter(vbox);
        Scene scene = new Scene(pane, 1920, 1080);
        return scene;
    }

    private Scene makeDeliveryService() {
        TextField name = new TextField();
        name.setPromptText("Type name here");
        TextField revenue = new TextField();
        revenue.setPromptText("Type revenue here (int)");
        revenue.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        ComboBox location = new ComboBox();
        location.setPromptText("Select location here");
        location.setItems(Location.locationsGUI);

        Button create = new Button("Create!");
        Label response = new Label();
        create.setOnAction(
                e -> {
                    if (name.getText().isEmpty() || revenue.getText().isEmpty() || location.getSelectionModel().isEmpty()) {
                        System.out.println("You cannot leave a field blank.");
                    } else {
                        simulator.makeDeliveryService(name.getText(), Integer.parseInt(revenue.getText()), (String) location.getValue());
                    }
                    response.setText(out.toString());
                }
        );

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(name, revenue, location, create);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(12, 12, 12, 12));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(hbox, response);

        BorderPane pane = new BorderPane();
        pane.setCenter(vbox);
        Scene scene = new Scene(pane, 1920, 1080);
        return scene;
    }

    private Scene makeRestaurant() {
        TextField name = new TextField();
        name.setPromptText("Type name here");
        ComboBox location = new ComboBox();
        location.setPromptText("Select location here");
        location.setItems(Location.locationsGUI);

        Button create = new Button("Create!");
        Label response = new Label();
        create.setOnAction(
                e -> {
                    if (name.getText().isEmpty() || location.getSelectionModel().isEmpty()) {
                        System.out.println("You cannot leave a field blank.");
                    } else {
                        simulator.makeRestaurant(name.getText(), (String) location.getValue());
                    }
                    response.setText(out.toString());
                }
        );

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(name, location, create);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(12, 12, 12, 12));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(hbox, response);

        BorderPane pane = new BorderPane();
        pane.setCenter(vbox);
        Scene scene = new Scene(pane, 1920, 1080);
        return scene;
    }


    private Scene makeDrone() {
        ComboBox deliveryService = new ComboBox();
        deliveryService.setPromptText("Select delivery service here");
        deliveryService.setItems(DeliveryService.deliveryServicesGUI);
        TextField tag = new TextField();
        tag.setPromptText("Type drone tag here (int)");
        tag.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        TextField capacity = new TextField();
        capacity.setPromptText("Type capacity here (int)");
        capacity.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        TextField fuel = new TextField();
        fuel.setPromptText("Type fuel here (int)");
        fuel.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

        Button create = new Button("Create!");
        Label response = new Label();
        create.setOnAction(
                e -> {
                    if (deliveryService.getSelectionModel().isEmpty() || tag.getText().isEmpty() || capacity.getText().isEmpty() || fuel.getText().isEmpty()) {
                        System.out.println("You cannot leave a field blank.");
                    } else {
                        simulator.makeDrone((String) deliveryService.getValue(), Integer.parseInt(tag.getText()), Integer.parseInt(capacity.getText()), Integer.parseInt(fuel.getText()));
                    }
                    response.setText(out.toString());
                }
        );

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(deliveryService, tag, capacity, fuel, create);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(12, 12, 12, 12));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(hbox, response);

        BorderPane pane = new BorderPane();
        pane.setCenter(vbox);
        Scene scene = new Scene(pane, 1920, 1080);
        return scene;
    }

    private Scene makePerson() {
        TextField username = new TextField();
        username.setPromptText("Type username here");
        TextField fName = new TextField();
        fName.setPromptText("Type first name here");
        TextField lName = new TextField();
        lName.setPromptText("Type last name here");
        TextField year = new TextField();
        year.setPromptText("Type year born here (int)");
        year.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        TextField month = new TextField();
        month.setPromptText("Type month born here (int 1-12)");
        month.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        TextField date = new TextField();
        date.setPromptText("Type date born here");
        date.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        TextField address = new TextField();
        address.setPromptText("Type address here");

        Button create = new Button("Create!");
        Label response = new Label();
        create.setOnAction(
                e -> {
                    if (username.getText().isEmpty() || fName.getText().isEmpty() || lName.getText().isEmpty() || year.getText().isEmpty() || month.getText().isEmpty() || date.getText().isEmpty() || address.getText().isEmpty()) {
                        System.out.println("You cannot leave a field blank.");
                    } else {
                        simulator.makePerson(username.getText(), fName.getText(), lName.getText(), Integer.parseInt(year.getText()), Integer.parseInt(month.getText()), Integer.parseInt(date.getText()), address.getText());
                    }
                    response.setText(out.toString());
                }
        );

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(username, fName, lName, year, month, date, address, create);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(12, 12, 12, 12));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(hbox, response);

        BorderPane pane = new BorderPane();
        pane.setCenter(vbox);
        Scene scene = new Scene(pane, 1920, 1080);
        return scene;
    }

    private Scene createPrintScene() {
        Label output = new Label();

        Button ingredients = new Button("Display Ingredients");
        ingredients.setOnAction(
                e -> {
                    out.reset();
                    simulator.displayIngredients();
                    output.setText(out.toString());
                }
        );

        Button locations = new Button("Display Locations");
        locations.setOnAction(
                e -> {
                    out.reset();
                    simulator.displayLocations();
                    output.setText(out.toString());
                }
        );

        ComboBox fromLocation = new ComboBox();
        fromLocation.setPromptText("Select \"from\" location");
        fromLocation.setItems(Location.locationsGUI);

        ComboBox toLocation = new ComboBox();
        toLocation.setPromptText("Select \"to\" location");
        toLocation.setItems(Location.locationsGUI);

        Button distance = new Button("Check Location Distance");
        distance.setOnAction(
                e -> {
                    out.reset();
                    if (fromLocation.getSelectionModel().isEmpty() || toLocation.getSelectionModel().isEmpty()) {
                        System.out.println("Please select both locations.");
                    } else {
                        simulator.checkDistance((String) fromLocation.getValue(), (String) toLocation.getValue());
                    }
                    output.setText(out.toString());
                }
        );

        VBox locBox = new VBox();
        locBox.setPadding(new Insets(12, 12, 12, 12));
        locBox.setSpacing(10);
        locBox.getChildren().addAll(fromLocation, toLocation, distance);


        Button deliveryServices = new Button("Display Delivery Services");
        deliveryServices.setOnAction(
                e -> {
                    out.reset();
                    simulator.displayServices();
                    output.setText(out.toString());
                }
        );

        Button restaurants = new Button("Display Restaurants");
        restaurants.setOnAction(
                e -> {
                    out.reset();
                    simulator.displayRestaurants();
                    output.setText(out.toString());
                }
        );

        ComboBox deliveryService = new ComboBox();
        deliveryService.setPromptText("Select Delivery Service");
        deliveryService.setItems(DeliveryService.deliveryServicesGUI);

        Button someDrones = new Button("Display a Service's Drones");
        someDrones.setOnAction(
                e -> {
                    out.reset();
                    if (deliveryService.getSelectionModel().isEmpty()) {
                        System.out.println("Please select the delivery service.");
                    } else {
                        simulator.displayDrones((String) deliveryService.getValue());
                    }
                    output.setText(out.toString());
                }
        );

        VBox droneBox = new VBox();
        droneBox.setPadding(new Insets(12, 12, 12, 12));
        droneBox.setSpacing(10);
        droneBox.getChildren().addAll(deliveryService, someDrones);

        Button allDrones = new Button("Display All Drones");
        allDrones.setOnAction(
                e -> {
                    out.reset();
                    simulator.displayAllDrones();
                    output.setText(out.toString());
                }
        );

        Button persons = new Button("Display People");
        persons.setOnAction(
                e -> {
                    out.reset();
                    simulator.displayPersons();
                    output.setText(out.toString());
                }
        );

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(ingredients, locations, locBox, deliveryServices, restaurants, droneBox, allDrones, persons);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(12, 12, 12, 12));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(hbox, output);

        BorderPane pane = new BorderPane();
        pane.setCenter(vbox);
        Scene scene = new Scene(pane, 1920, 1080);
        return scene;
    }

    private Scene createControlScene() {
        Button flyDrone = new Button("Fly Drone");
        flyDrone.setOnAction(
                e -> {
                    if (!scenes.containsKey("FlyDrone")) {
                        scenes.put("FlyDrone", flyDrone());
                    }
                    switchScenes("FlyDrone", "Control", true, true);
                }
        );
        Button loadIngredient = new Button("Load Ingredient");
        loadIngredient.setOnAction(
                e -> {
                    if (!scenes.containsKey("LoadIngredient")) {
                        scenes.put("LoadIngredient", loadIngredient());
                    }
                    switchScenes("LoadIngredient", "Control", true, true);
                }
        );
        Button loadFuel = new Button("Load Fuel");
        Button purchaseIngredient = new Button("Purchase Ingredient");
        Button hireWorker = new Button("Hire Worker");
        Button fireWorker = new Button("Fire Worker");
        Button appointManager = new Button("Appoint Manager");
        Button trainPilot = new Button("Train Pilot");
        Button appointPilot = new Button("Appoint Pilot");
        Button joinSwarm = new Button("Join Swarm");
        Button leaveSwarm = new Button("Leave Swarm");
        Button collectRevenue = new Button("Collect Revenue");

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(flyDrone, loadIngredient, loadFuel, purchaseIngredient, hireWorker, fireWorker, appointManager, trainPilot, appointPilot, joinSwarm, leaveSwarm, collectRevenue);

        BorderPane pane = new BorderPane();
        pane.setCenter(hbox);
        Scene scene = new Scene(pane, 1920, 1080);
        return scene;
    }

    private Scene flyDrone() {
        ComboBox deliveryService = new ComboBox();
        deliveryService.setPromptText("Select delivery service here");
        deliveryService.setItems(DeliveryService.deliveryServicesGUI);
        ComboBox tag = new ComboBox();
        tag.setPromptText("Select drone tag here");
        deliveryService.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object oldDS, Object newDS) {
                tag.setItems(DeliveryService.deliveryServices.get(newDS).getDronesGUI());
            }
        });
        ComboBox location = new ComboBox();
        location.setPromptText("Select location here");
        location.setItems(Location.locationsGUI);

        Button create = new Button("Create!");
        Label response = new Label();
        create.setOnAction(
                e -> {
                    if (deliveryService.getSelectionModel().isEmpty() || tag.getSelectionModel().isEmpty() || location.getSelectionModel().isEmpty()) {
                        System.out.println("You cannot leave a field blank.");
                    } else {
                        simulator.flyDrone((String) deliveryService.getValue(), (Integer) tag.getValue(), (String) location.getValue());
                    }
                    response.setText(out.toString());
                }
        );

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(deliveryService, tag, location, create);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(12, 12, 12, 12));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(hbox, response);

        BorderPane pane = new BorderPane();
        pane.setCenter(vbox);
        Scene scene = new Scene(pane, 1920, 1080);
        return scene;
    }

    private Scene loadIngredient() {
        ComboBox deliveryService = new ComboBox();
        deliveryService.setPromptText("Select delivery service here");
        deliveryService.setItems(DeliveryService.deliveryServicesGUI);
        ComboBox tag = new ComboBox();
        tag.setPromptText("Select drone tag here");
        deliveryService.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object oldDS, Object newDS) {
                tag.setItems(DeliveryService.deliveryServices.get(newDS).getDronesGUI());
            }
        });
        ComboBox ingredient = new ComboBox();
        ingredient.setPromptText("Select location here");
        ingredient.setItems(IngredientInfo.ingredientInfosGUI);
        TextField quantity = new TextField();
        quantity.setPromptText("Type quantity here (int)");
        quantity.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        TextField unitPrice = new TextField();
        unitPrice.setPromptText("Type unit price here (int)");
        unitPrice.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

        Button create = new Button("Create!");
        Label response = new Label();
        create.setOnAction(
                e -> {
                    if (deliveryService.getSelectionModel().isEmpty() || tag.getSelectionModel().isEmpty() || ingredient.getSelectionModel().isEmpty() || quantity.getText().isEmpty() || unitPrice.getText().isEmpty()) {
                        System.out.println("You cannot leave a field blank.");
                    } else {
                        simulator.loadIngredient((String) deliveryService.getValue(), (Integer) tag.getValue(), (String) ingredient.getValue(), Integer.parseInt(quantity.getText()), Integer.parseInt(unitPrice.getText()));
                    }
                    response.setText(out.toString());
                }
        );

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(deliveryService, tag, ingredient, quantity, unitPrice, create);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(12, 12, 12, 12));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(hbox, response);

        BorderPane pane = new BorderPane();
        pane.setCenter(vbox);
        Scene scene = new Scene(pane, 1920, 1080);
        return scene;
    }

    private Scene loadIngredient() {
        ComboBox deliveryService = new ComboBox();
        deliveryService.setPromptText("Select delivery service here");
        deliveryService.setItems(DeliveryService.deliveryServicesGUI);
        ComboBox tag = new ComboBox();
        tag.setPromptText("Select drone tag here");
        deliveryService.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object oldDS, Object newDS) {
                tag.setItems(DeliveryService.deliveryServices.get(newDS).getDronesGUI());
            }
        });
        TextField petrol = new TextField();
        petrol.setPromptText("Type petrol here (int)");
        petrol.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

        Button create = new Button("Create!");
        Label response = new Label();
        create.setOnAction(
                e -> {
                    if (deliveryService.getSelectionModel().isEmpty() || tag.getSelectionModel().isEmpty() || petrol.getText().isEmpty()) {
                        System.out.println("You cannot leave a field blank.");
                    } else {
                        simulator.loadFuel((String) deliveryService.getValue(), (Integer) tag.getValue(), Integer.parseInt(petrol.getText()));
                    }
                    response.setText(out.toString());
                }
        );

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(deliveryService, tag, petrol, create);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(12, 12, 12, 12));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(hbox, response);

        BorderPane pane = new BorderPane();
        pane.setCenter(vbox);
        Scene scene = new Scene(pane, 1920, 1080);
        return scene;
    }
}
