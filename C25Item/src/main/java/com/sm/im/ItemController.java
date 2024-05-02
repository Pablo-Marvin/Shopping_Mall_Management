package com.sm.im;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Controller class for managing items
@RestController
@CrossOrigin("http://localhost:3002")
public class ItemController {

    // Autowired annotation for dependency injection of ItemService
    @Autowired
    private ItemService service;
    
    // Retrieval endpoint for listing all items
    @GetMapping("/itemss")
    public List<Item> list() {
        return service.listAll();
    }

    // Retrieval endpoint for getting an item by ID
    @GetMapping("/items/{id}")
    public ResponseEntity<Item> get(@PathVariable Integer id) {
        try {
            Item item = service.get(id);
            return new ResponseEntity<>(item, HttpStatus.OK);
        } catch (Exception e) {
            // Return 404 Not Found status if the item with the specified ID is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin("http://localhost:3001")
    // Create endpoint for adding a new item
    @PostMapping("/itemss/add")
    public ResponseEntity<?> add(@RequestBody Item item) {
        // Save the item using the ItemService and return 201 Created status
        service.save(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Update endpoint for modifying an existing item
    @PutMapping("/itemss/update/{id}")
    public ResponseEntity<?> update(@RequestBody Item item, @PathVariable Integer id) {
        try {
            // Retrieve the existing item by ID
            @SuppressWarnings("unused")
			Item existItem = service.get(id);
            // Save the updated item using the ItemService
            service.save(item);
            // Return 200 OK status
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // Return 404 Not Found status if the item with the specified ID is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete endpoint for removing an item by ID
    @DeleteMapping("/itemss/delete/{id}")
    public void delete(@PathVariable Integer id) {
        // Delete the item using the ItemService
        service.delete(id);
    }
}
