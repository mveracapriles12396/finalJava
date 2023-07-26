/**
 * Final Project
 * Group:
 * 1. Priyanka, Priyanka,991676014
 * 2. Virk, Sukhanparteek kaur, 991712335
 * 3. Vera, Moises, 991716524
 * @author Moises Vera
 * @version 1.0
 * @since 2023-07-26
 */


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class CreateStore extends Application implements EventHandler<ActionEvent> {

    // Declare variables
    private Label nameLabel, passwordLabel, addressLabel;
    private TextField nameField, addressField;
    private PasswordField passwordField;
    private Button createButton, backButton;

    @Override
    public void start(Stage primaryStage){
        Font labelFont = new Font("Arial", 16);
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setPadding(new Insets(25,25,25,25));

        nameLabel = new Label("Store Name");
        nameLabel.setTextFill(Color.GREY);
        nameLabel.setFont(labelFont);
        pane.add(nameLabel,0,0);
        
        nameField = new TextField();
        pane.add(nameField,0,1);

        passwordLabel = new Label("Password");
        passwordLabel.setFont(labelFont);
        passwordLabel.setTextFill(Color.GREY);
        pane.add(passwordLabel, 0, 2);

        passwordField = new PasswordField();
        pane.add(passwordField, 0,3);

        addressLabel= new Label("Address");
        addressLabel.setFont(labelFont);
        addressLabel.setTextFill(Color.GREY);
        pane.add(addressLabel, 0, 4);

        addressField = new TextField();
        pane.add(addressField, 0,5);

        createButton = new Button("Create store");
        createButton.setStyle("-fx-background-color: #32CD32; -fx-text-fill: white;");
        createButton.setOnAction(this);
        pane.add(createButton, 0, 7);

        backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #FFA500; -fx-text-fill: white;");
        backButton.setOnAction(this);
        pane.add(backButton,1,7);

        Scene scene = new Scene(pane,500,400);
        primaryStage.setTitle("Create Store");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event){
        if(event.getSource() == backButton){
            ((Stage) backButton.getScene().getWindow()).close();
            LogIn logIn = new LogIn();
            Stage createLogIn = new Stage();
            logIn.start(createLogIn);

        }else if(event.getSource() == createButton){

            try {
                // getting the values of the fields
                String name = nameField.getText();
                String password = passwordField.getText();
                String address = addressField.getText();

                if(name.length()<4 || password.length()<4 || address.length()<4){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("The fields are empty or are too short should be longer than 3 characters");
                    alert.showAndWait();
                    return;
                }

                // create the store
                Store.createdStore(new Store(name, password, address , new Product[]{}));

                // empty the fields
                nameField.setText("");
                passwordField.setText("");
                addressField.setText("");

                // succes message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Congratulations!");
                alert.setContentText("You have succesfully create an account");
                alert.showAndWait();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Unexpected error");
                alert.setContentText("You could not create an account an error have occours, this is the error:" + e.getMessage());
                alert.showAndWait();
            }
        }
    }
}