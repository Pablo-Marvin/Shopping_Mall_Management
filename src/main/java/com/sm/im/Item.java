package com.sm.im;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


//Used to mark this class as a JPA entity
@Entity 

// Indicates that this class is a JPA entity, representing a database table.
@Table

public class Item {

    @Id  // Denotes the primary key of the entity.
    
    private int id;
    private String product_name;
    private float price;
    private String category;
    private int stock_quantity;
    private String description;
    
 // Default constructor
	public Item() {
		super();
	}

	 // Parameterized constructor
	public Item(int id, String name, float price, String category, int stock_quantity, String description) {
		super();
		this.id = id;
		this.product_name = name;
		this.price = price;
		this.category = category;
		this.stock_quantity = stock_quantity;
		this.description = description;
	}

	// Getter and setter methods for each field

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getProduct_Name() {
		return product_name;
	}



	public void setProduct_Name(String name) {
		this.product_name = name;
	}



	public float getPrice() {
		return price;
	}



	public void setPrice(float price) {
		this.price = price;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public int getStock_quantity() {
		return stock_quantity;
	}



	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}

	// toString method for generating a string representation of the object
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + product_name + ", price=" + price + ", category=" + category + ", stock_quantity="
				+ stock_quantity + ", description=" + description + ", getId()=" + getId() + ", getProduct_Name()=" + getProduct_Name()
				+ ", getPrice()=" + getPrice() + ", getCategory()=" + getCategory() + ", getStock_quantity()="
				+ getStock_quantity() + ", getDescription()=" + getDescription() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	
	

    
}
