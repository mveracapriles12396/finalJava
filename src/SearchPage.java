import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SearchPage extends Application {
    private Store mainStore;
    private String text;
    private Label label, title;
    private Button backButton;

    SearchPage(Store mainStore, String text) {
        this.mainStore = mainStore;
        this.text = text;
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));

        title = new Label("Result from:");
        pane.add(title, 0, 0);

        label = new Label(text);
        pane.add(label, 0, 1);

        GridPane headerPane = new GridPane();
        headerPane.setVgap(10);
        headerPane.setHgap(10);
        Label nameLabel = new Label("Name");
        headerPane.add(nameLabel, 0, 0,2,1);
        Label costLabel = new Label("Cost");
        headerPane.add(costLabel, 5, 0);
        Label priceLabel = new Label("Price");
        headerPane.add(priceLabel, 6, 0);
        Label qtyLabel = new Label("Qty");
        headerPane.add(qtyLabel, 7, 0);
        Label actLabel = new Label("Actions");
        headerPane.add(actLabel, 8, 0, 2, 1);
        pane.add(headerPane, 0, 2, 8, 1);

        ProductList list = new ProductList(mainStore, "(.*)"+text+"(.*)");
        pane.add(list, 0, 3);

        backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                MainMenu main = new MainMenu(mainStore);
                Stage stage = new Stage();
                main.start(stage);
                primaryStage.close();
            }
        });
        pane.add(backButton, 0, 7);
        Scene scene = new Scene(pane, 720, 720);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Search");
        primaryStage.show();
    }
}

