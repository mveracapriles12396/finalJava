import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Optional;

public class ProductList extends GridPane {

    private ArrayList<Product> productList;
    private Store mainStore;
    private int startIndex = 0;
    private int batchSize = 10; // Display 10 products per page

    public ProductList(Store mainStore, String regularExp, int batchSize) {
        this.productList = mainStore.getProducts();
        this.mainStore = mainStore;
        this.batchSize = batchSize;
    
        setVgap(10);
        setHgap(10);
    
        refreshProductList(regularExp);
    }

    public void refreshProductList(String regularExp) {
        // Clear the current product list
        getChildren().clear();

        int endIndex = Math.min(startIndex + batchSize, productList.size());
        int displayedProducts = 0;

        int totalOfProducts = 0;
        for (Product product : productList) {
            if (product.getName().matches(regularExp)){
                totalOfProducts++;
            }
        }
        

        if (productList.size() != 0) {
            for (int i = startIndex; i < endIndex; i++) {
                if (productList.get(i).getName().matches(regularExp)) {
                    Label name = new Label(productList.get(i).getName());
                    add(name, 0, displayedProducts + 1);

                    Label cost = new Label(String.valueOf(productList.get(i).getCost()));
                    add(cost, 1, displayedProducts + 1);

                    Label price = new Label(String.valueOf(productList.get(i).getPrice()));
                    add(price, 2, displayedProducts + 1);

                    Label qty = new Label(String.valueOf(productList.get(i).getQty()));
                    add(qty, 3, displayedProducts + 1);

                    Button deleteButton = new Button("Delete");
                    add(deleteButton, 4, displayedProducts + 1);
                    deleteButton.setStyle("-fx-background-color: #DA2700; -fx-text-fill: white;");

                    Button editButton = new Button("Edit");
                    editButton.setStyle("-fx-background-color: #0BEA52; -fx-text-fill: white;");
                    add(editButton, 5, displayedProducts + 1);

                    Button viewDetails = new Button("Details");
                    viewDetails.setStyle("-fx-background-color: #0B63EA; -fx-text-fill: white;");
                    add(viewDetails, 6, displayedProducts + 1);

                    final int index = i;
                    deleteButton.setOnAction(event -> {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("Do you want to delete this product?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            mainStore.removeProduct(index);
                            refreshProductList(regularExp);
                        }
                    });
                    editButton.setOnAction(event -> {
                        Product selectedProduct = productList.get(index);
                        AddEditProduct edit = new AddEditProduct(mainStore, selectedProduct, index);
                        Stage stage = new Stage();
                        edit.start(stage);
                        ((Stage) editButton.getScene().getWindow()).close();
                    });
                    viewDetails.setOnAction(event -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Details");
                        alert.setContentText(productList.get(index).toString());
                        alert.showAndWait();
                    });
                    
                    displayedProducts++;
                }
            }
        } 

        Button previousButton = new Button("Previous");
        previousButton.setStyle("-fx-background-color: #0B63EA; -fx-text-fill: white;");
        previousButton.setOnAction(event -> {
            if (startIndex >= batchSize) {
                startIndex -= batchSize;
                refreshProductList(regularExp);
            }
        });
        add(previousButton, 0, displayedProducts + 1);

        Button nextButton = new Button("Next");
        nextButton.setStyle("-fx-background-color: #0B63EA; -fx-text-fill: white;");
        nextButton.setOnAction(event -> {
            if (startIndex + batchSize < productList.size()) {
                startIndex += batchSize;
                refreshProductList(regularExp);
            }
        });
        add(nextButton, 1, displayedProducts + 1);

        
        Label totalProductsLabel = new Label("Total:" + totalOfProducts);
        add(totalProductsLabel, 3, displayedProducts + 1, 2, 1);
    }
}




