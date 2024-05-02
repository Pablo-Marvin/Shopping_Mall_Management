package com.sm.im;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service // Indicates that this class is a service component in a Spring application
@Transactional // Specifies that methods should run in a transactional context

public class ItemService {

 private final ItemRepository repository; // Item repository for database operations

 // Constructor injection of ItemRepository
 public ItemService(ItemRepository repository) {
     this.repository = repository;
 }

 // Method to list all items, throwing an exception if none are found
 public List<Item> listAll() {
     List<Item> items = repository.findAll();
     if (items.isEmpty()) {
         throw new NoItemsFoundException("No items found.");
     }
     return items;
 }

 // Method to get an item by ID, throwing an exception if not found
 public Item get(Integer id) {
     try {
         return repository.findById(id)
                 .orElseThrow(() -> new NoSuchElementException("Item not found with ID: " + id));
     } catch (Exception e) {
         throw new ItemNotFoundException("Item not found with ID: " + id);
     }
 }

 // Method to save an item, validating it first
 public void save(Item item) {
     validateItem(item);
     repository.save(item);
 }

 // Method to delete an item by ID, throwing an exception if not found
 public void delete(Integer id) {
     try {
         repository.deleteById(id);
     } catch (EmptyResultDataAccessException e) {
         throw new ItemNotFoundException("Item with ID " + id + " not found.");
     }
 }

 // Private method to validate an item
 private void validateItem(Item item) {
	    if (item == null) {
	        throw new InvalidItemException("Item is null.");
	    }

	    if (item.getProduct_Name() == null || item.getProduct_Name().trim().isEmpty()) {
	        throw new InvalidItemException("Item name is null or empty.");
	    }

	    if (item.getId() <= 0) {
	        throw new InvalidItemException("Item ID should be greater than 0.");
	    }

	    if (item.getPrice() <= 0) {
	        throw new InvalidItemException("Item price should be greater than 0.");
	    }

	    if (item.getCategory() == null || item.getCategory().trim().isEmpty()) {
	        throw new InvalidItemException("Item category is null or empty.");
	    }

	    if (item.getStock_quantity() < 0) {
	        throw new InvalidItemException("Item stock quantity should not be negative.");
	    }

	    if (item.getDescription() == null || item.getDescription().trim().isEmpty()) {
	        throw new InvalidItemException("Item description is null or empty.");
	    }
	}

}
