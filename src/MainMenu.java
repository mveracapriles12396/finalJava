import javafx.scene.control.TextField;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class MainMenu extends Application implements EventHandler<ActionEvent> {
    
    private Button addButton, saveChanges, search;
    private Label mainWelcome, searchLabel;
    private TextField searchField;
    private Store mainStore;
    private ProductList productList;

    public MainMenu(Store mainStore){
        this.mainStore = mainStore;
    }

    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));

        mainWelcome = new Label("Welcome to your store! ");
        mainWelcome.setFont(new Font("Arial", 18));
        pane.add(mainWelcome, 0, 0);

        addButton = new Button("Add product");
        addButton.setStyle("-fx-background-color: #FFA500; -fx-text-fill: white;");
        pane.add(addButton, 2, 0);
        addButton.setOnAction(this);

        saveChanges = new Button("Save Changes");
        saveChanges.setStyle("-fx-background-color: #0B63EA; -fx-text-fill: white;");
        pane.add(saveChanges, 3, 0);
        saveChanges.setOnAction(this);

        // search bar
        searchLabel = new Label("Search product:");
        pane.add(searchLabel, 0, 1);
        searchField = new TextField();
        pane.add(searchField, 1, 1);
        search = new Button("Search");
        pane.add(search, 2, 1);
        search.setOnAction(this);

        // Details panel
        GridPane detailsPane = new GridPane();
        detailsPane.setVgap(10);
        detailsPane.setHgap(10);
        
        Label storeNameLabel = new Label("Store Name:");
        Label storeAddressLabel = new Label("Address:");
        Label storeNameValue = new Label(mainStore.getStoreName());
        Label storeAddressValue = new Label(mainStore.getAddress());
        Label productsLabel = new Label("Product list:");

        detailsPane.add(storeNameLabel, 0, 0);
        detailsPane.add(storeNameValue, 1, 0);
        detailsPane.add(storeAddressLabel, 0, 1);
        detailsPane.add(storeAddressValue, 1, 1);
        detailsPane.add(productsLabel, 0, 2);
        pane.add(detailsPane, 0, 3, 4, 1);


        // Product List
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
        pane.add(headerPane, 0, 5, 8, 1);

        productList = new ProductList(mainStore, "(.*)");
        pane.add(productList, 0, 6, 5, 1);

        Scene scene = new Scene(pane, 700, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main menu");
        primaryStage.setOnCloseRequest(event -> {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you want to save the changes?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
            Store.saveChanges(mainStore);
        }catch (Exception e) {
            e.printStackTrace();
        }
        }
        primaryStage.close();
    });

    primaryStage.setTitle("Final Proyect");
    primaryStage.show();

    }

    @Override
    public void handle(ActionEvent e) {
        if (e.getSource() == addButton) {
            AddEditProduct product = new AddEditProduct(mainStore);
            Stage stage = new Stage();
            product.start(stage);
            ((Stage) addButton.getScene().getWindow()).close();
        } else if (e.getSource() == saveChanges) {
            try {
                Store.saveChanges(mainStore);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Changes saved!");
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        } else if (e.getSource() == search) {
            String text = searchField.getText();
            SearchPage searchPage = new SearchPage(mainStore, text);
            Stage stage = new Stage();
            searchPage.start(stage);
            ((Stage) search.getScene().getWindow()).close();
        }
    }
}