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


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Store implements FileDataManager, Serializable {
    
    // Declare variables
    private ArrayList<Product> products;
    private String storeName, password, address;

    public Store(String storeName, String password, String address, Product[] products) {
        this.storeName = storeName;
        this.password = password;
        this.address = address;
        this.products = new ArrayList<>(Arrays.asList(products));
    }

    // getters and setters
    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfProducts() {
        return products.size();
    }
    //

    
    // this will be use to edit an existing product
    public void editProduct(int index,Product product) throws Exception {
        products.set(index, product);
    }

    // this will be use to add a new product
    public void addProduct(Product product) {
        products.add(product);
    }

     // this will be use to delete products
    public void removeProduct(int i) {
        products.remove(i);
    }
    
    // This checks if the store already exists; if not, it creates a new one.

    public static void createdStore(Store newStore) throws Exception {
        Store[] stores = (Store[]) FileDataManager.getData("store.dat");
        if (stores == null) {
            Store[] newArray = new Store[1];
            newArray[0] = newStore;
            FileDataManager.saveData("store.dat", newArray);
            return;
        }
        int l = stores.length;
        Store[] newArray = new Store[l + 1];
        for (int i = 0; i < l; i++) {
            newArray[i] = stores[i];
            if (stores[i].getStoreName().equals(newStore.getStoreName())) 
                throw new Exception("Store already exists. Please try with another name.");
        }
        newArray[l] = newStore;
        FileDataManager.saveData("store.dat", newArray);
    }

    public static Store checkPassword(String password, String name) throws Exception {
        Store[] stores = (Store[]) FileDataManager.getData("store.dat");
        if(stores == null)
            return null;
        for (Store store : stores) {
        if (store != null && store.getStoreName().equals(name) && store.getPassword().equals(password)) 
            return store;
        }
        return null;
    }


    // this is use to save the changes on the store
    public static void saveChanges(Store store) throws Exception {
        Store[] stores= (Store[]) FileDataManager.getData("store.dat");
        for (int i = 0; i < stores.length; i++) {
            Store existingStore = stores[i];
            if (existingStore != null && existingStore.getStoreName().equals(store.getStoreName())) {
                stores[i] = store;
                break;
            }
        }
        FileDataManager.saveData("store.dat",stores);
    }

    @Override
    public String toString() {
        return storeName;
    }
}






