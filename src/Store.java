import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Store implements FileDataManager, Serializable {
    private ArrayList<Product> products;
    private String storeName, password, address;

    public Store(String storeName, String password, String address, Product[] products) {
        this.storeName = storeName;
        this.password = password;
        this.address = address;
        this.products = new ArrayList<>(Arrays.asList(products));
    }

    public void editProduct(int index,Product product) throws Exception {
        products.set(index, product);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(int i) {
        products.remove(i);
    }

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






