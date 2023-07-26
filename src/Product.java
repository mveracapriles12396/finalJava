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

public class Product implements Serializable {

	// Declare variables
	private int qty;
	private String name;
	private double cost, price;
	private String description, condition;

	public Product(String name, double cost, double price, int qty, String description, String condition) {
		this.name = name;
        this.cost = cost;
        this.price = price;
        this.qty = qty;
        this.description = description;
        this.condition = condition;
	}

	// getters and setters
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public double getCost() {
		return this.cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getPrice() {
		return this.price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public int getQty() {
		return this.qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getCondition() {
		return this.condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}

	// toString 
	@Override
	public String toString() {
		return String.format("Product name: %s%nCost: %.2f%nPrice: %.2f%nQuantity: %d%nDescription: %s%nCondition: %s%n",
				getName(), getCost(), getPrice(), getQty(), getDescription(), getCondition());
	}
}