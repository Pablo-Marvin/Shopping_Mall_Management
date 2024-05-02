package com.sm.im;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.NoSuchElementException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest
class C25ItemApplicationTests {

 // Annotation to inject mocks into the tested object
 @InjectMocks
 private ItemController itemController;

 // Mocks for the service and repository dependencies
 @Mock
 private ItemService itemService;
 @Mock
 private ItemRepository itemRepository;

 // Test case for listing items
 @Test
 public void testListItems() {
     // Arranged some datas:To Create sample items
     Item item1 = new Item(6, "Product1", 10.0f, "Category1", 100, "Description1");
     Item item2 = new Item(7, "Product2", 20.0f, "Category2", 200, "Description2");

     // Mock the behavior of the service to return the sample items
     when(itemService.listAll()).thenReturn(List.of(item1, item2));

     // Act: Call the list method in the controller
     List<Item> itemList = itemController.list();

     // Assert: Check if the returned list has the expected size
     assertEquals(2, itemList.size());
 }

 // Test case for getting an item by ID
 @Test
 public void testGetItemById() {
     // Arrange: Create a sample item
     Item item = new Item(1, "Product1", 10.0f, "Category1", 100, "Description1");

     // Mock the behavior of the service to return the sample item when ID is 1
     when(itemService.get(1)).thenReturn(item);

     // Act: Call the get method in the controller with ID 1
     ResponseEntity<Item> responseEntity = itemController.get(1);

     // Assert: Check if the response is OK and contains the expected item
     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
     assertEquals(item, responseEntity.getBody());
 }

 // Test case for handling the scenario where an item is not found by ID
 @Test
 public void testGetItemByIdNotFound() {
     // Arrange: Mock the behavior of the service to throw an exception for a non-existing item
     when(itemService.get(1)).thenThrow(new NoSuchElementException("Item not found with ID: 1"));

     // Act: Call the get method in the controller with ID 1
     ResponseEntity<Item> responseEntity = itemController.get(1);

     // Assert: Check if the response is NOT_FOUND
     assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
 }

 // Test case for adding a new item
 @Test
 public void testAddItem() {
     // Arrange: Create a new item
     Item newItem = new Item(3, "NewProduct", 15.0f, "NewCategory", 50, "NewDescription");

     // Act: Call the add method in the controller with the new item
     ResponseEntity<?> responseEntity = itemController.add(newItem);

     // Assert: Check if the response is CREATED and the service's save method is called
     assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
     verify(itemService, times(1)).save(newItem);
 }

 // Test case for updating an existing item
 @Test
 public void testUpdateItem() {
     // Arrange: Create an updated item and mock the behavior of the service
     int itemId = 1;
     Item updatedItem = new Item(itemId, "UpdatedProduct", 25.0f, "UpdatedCategory", 75, "UpdatedDescription");
     when(itemService.get(itemId)).thenReturn(new Item(itemId, "OriginalProduct", 20.0f, "OriginalCategory", 50, "OriginalDescription"));

     // Act: Call the update method in the controller with the updated item and ID
     ResponseEntity<?> responseEntity = itemController.update(updatedItem, itemId);

     // Assert: Check if the response is OK and the service's save method is called
     assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
     verify(itemService, times(1)).save(updatedItem);
 }

 // Test case for deleting an item
 @Test
 public void testDeleteItem() {
     // Arrange: Set an item ID
     int itemId = 1;

     // Act: Call the delete method in the controller with the item ID
     itemController.delete(itemId);

     // Assert: Check if the service's delete method is called
     verify(itemService, times(1)).delete(itemId);
 }


}
