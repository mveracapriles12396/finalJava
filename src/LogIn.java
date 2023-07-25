import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class LogIn extends Application implements EventHandler<ActionEvent>{

    private TextField storeName;
    private PasswordField password;
    private Button create, signIn;
    private Label userLabel, userPassword, title;

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Final Project");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
       
        title = new Label("Welcome Back!");
        title.setTextFill(Color.GREY);
        title.setFont(new javafx.scene.text.Font("Arial", 26));
        gridPane.add(title, 0, 0, 2, 1);

        userLabel = new Label("Enter your username");
        userLabel.setTextFill(Color.GREY);
        userLabel.setFont(new javafx.scene.text.Font("Arial", 16));
        gridPane.add(userLabel, 0, 1);

        storeName = new TextField();
        storeName.setFont(new javafx.scene.text.Font("Arial", 14));
        gridPane.add(storeName, 0, 2);

        userPassword = new Label("Enter your password");
        userPassword.setTextFill(Color.GREY);
        userPassword.setFont(new javafx.scene.text.Font("Arial", 16));
        gridPane.add(userPassword, 0, 3);

        password = new PasswordField();
        password.setFont(new javafx.scene.text.Font("Arial", 14));
        gridPane.add(password, 0, 4);

        signIn = new Button("Sign In");
        signIn.setFont(new javafx.scene.text.Font("Arial", 14));
        signIn.setStyle("-fx-background-color: #32CD32; -fx-text-fill: white;");
        signIn.setOnAction(this);
        gridPane.add(signIn, 0, 5);

        create = new Button("Create Account");
        create.setFont(new javafx.scene.text.Font("Arial", 14));
        create.setStyle("-fx-background-color: #FFA500; -fx-text-fill: white;");
        create.setOnAction(this);
        gridPane.add(create, 1, 5);

        Scene scene = new Scene(gridPane, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == signIn) {
            String finalName = storeName.getText();
            String finalPassword = password.getText();
            if(finalName.length()==0 || finalPassword.length()==0){
                error("The fields are empty");
                return;
            }
            try {
                Store store = Store.checkPassword(finalPassword, finalName);
                if (store != null) {
                    ((Stage) signIn.getScene().getWindow()).close();
                    MainMenu main = new MainMenu(store);
                    Stage createMain = new Stage();
                    main.start(createMain);
                } else {
                        error("The user and password do not match or the store do not exist");
                        return;
                }
            } catch (Exception ex) {
                error(ex.getMessage());
            }
        } else if (event.getSource() == create) {
            ((Stage) create.getScene().getWindow()).close();
            CreateStore createStore = new CreateStore();
            Stage createStoreStage = new Stage();
            createStore.start(createStoreStage);            
        }
    }
    public static void error(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


