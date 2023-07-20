import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddEditProduct extends Application {
    private Button backButton, addButton;
    private TextField name, cost, price, qty, description, condition;
    private Label detail, nameLabel, costLabel, prLabel, qLabel, dLabel, cLabel;
    private Store newStore;
    private Product product;
    private String finalName, finalCost, finalPrice, finalQty, finalDescrip, finalCond;
    private String buttonText = "Add";
    private int index;

    public AddEditProduct(Store newStore){
        this.newStore = newStore;
    }
    public AddEditProduct(Store newStore, Product product,int index) {
            this.newStore = newStore;
            this.product = product;
            this.index = index;
            finalName = product.getName();
            finalCost = String.valueOf(product.getCost());
            finalPrice = String.valueOf(product.getPrice());
            finalQty = String.valueOf(product.getQty());
            finalCond = product.getCondition();
            finalDescrip = product.getCondition();
            buttonText = "Edit";
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));

        detail = new Label("Enter the details of the product:");
        pane.add(detail, 0, 0);

        nameLabel = new Label("Name:");
        pane.add(nameLabel, 0, 1);
        name = new TextField(finalName);
        pane.add(name, 1, 1);

        costLabel = new Label("Cost:");
        pane.add(costLabel, 0, 2);
        cost = new TextField(finalCost);
        pane.add(cost, 1, 2);

        prLabel = new Label("Price:");
        pane.add(prLabel, 0, 3);
        price = new TextField(finalPrice);
        pane.add(price, 1, 3);

        qLabel = new Label("Qty:");
        pane.add(qLabel, 0, 4);
        qty = new TextField(finalQty);
        pane.add(qty, 1, 4);

        dLabel = new Label("Description:");
        pane.add(dLabel, 0, 5);
        description = new TextField(finalDescrip);
        pane.add(description, 1, 5);

        cLabel = new Label("Condition:");
        pane.add(cLabel, 0, 6);
        condition = new TextField(finalCond);
        pane.add(condition, 1, 6);

        addButton = new Button(buttonText);
        addButton.setStyle("-fx-background-color: #0B63EA; -fx-text-fill: white;");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                String productName = name.getText();
                double productCost = Double.parseDouble(cost.getText());
                double productPrice = Double.parseDouble(price.getText());
                int productQty = Integer.parseInt(qty.getText());
                String productDesc = description.getText();
                String productCond = condition.getText();

                if( productName.length()==0 || 
                    productDesc.length()==0 ||
                    productCond.length()==0 
                    ){
                    throw new Exception("All fields should not be empty");
                }

                Product newProduct = new Product( productName, productCost, productPrice,
                        productQty, productDesc, productCond);
                if (product == null) {
                     newStore.addProduct(newProduct);
                     succes("You add a new product");
                }else{
                    newStore.editProduct(index,newProduct);
                    succes("You edit product");
                }

                } catch (NullPointerException ex) {
                    error("Price,cost and qty must be numbers");
                } catch(Exception ex){
                    error(ex.getMessage());
                }
            }
        });
        pane.add(addButton, 0, 7);

        backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                MainMenu diseno = new MainMenu(newStore);
                Stage stage = new Stage();
                try {
                    diseno.start(stage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                primaryStage.close();
            }
        });
        pane.add(backButton, 1, 7);

        Scene scene = new Scene(pane, 720, 720);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add/Edit Product");
        primaryStage.show();
    }
    public void error(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
       
    }

    public void succes(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congratulations!");
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
         if (result.isPresent() && result.get() == ButtonType.OK) {
                MainMenu main = new MainMenu(newStore);
                Stage stage = new Stage();
                main.start(stage);
                ((Stage)backButton.getScene().getWindow()).close();
        }
    }
}


