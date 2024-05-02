import React, { useState } from 'react';
import axios from 'axios'; // Axios is a popular library for making HTTP requests
import './AddData.css'; // Importing a CSS file for styling

// Component for rendering a simple horizontal tab
const Tab = () => {
  return <span style={{ marginLeft: '1em' }} />;
};

// Main component for adding data
const AddData = () => {
  // State variables to store input values
  const [itemId, setItemId] = useState('');
  const [itemName, setItemName] = useState('');
  const [itemPrice, setItemPrice] = useState('');
  const [itemCategory, setItemCategory] = useState('');
  const [itemStockQuantity, setItemStockQuantity] = useState('');
  const [itemDescription, setItemDescription] = useState('');

  // Function to handle adding a new item
  const handleAddItem = () => {
    // Create a new item object with input values
    const newItem = {
      id: parseInt(itemId), // Parse to integer
      product_Name: itemName,
      price: parseFloat(itemPrice), // Ensure price is a number
      category: itemCategory,
      stock_quantity: parseInt(itemStockQuantity), // Ensure stock_quantity is an integer
      description: itemDescription,
    };

    // Make a POST request to the server with the new item data
    axios.post('http://localhost:8081/itemss/add', newItem)
      .then((response) => {
        console.log('Item added successfully:', response.data);

        // Reset state variables to clear input fields after successful addition
        setItemId('');
        setItemName('');
        setItemPrice('');
        setItemCategory('');
        setItemStockQuantity('');
        setItemDescription('');
      })
      .catch((error) => {
        console.error('Error adding item:', error);
      });
  };

  // Render the component with input fields and a button
  return (
    <div>
      <h2>Add Item</h2>
      <br/>
      <label>ID:</label>
      <Tab />
      <input type="text" value={itemId} onChange={(e) => setItemId(e.target.value)} />
      <br/>
      <label>Name:</label>
      <Tab />
      <input type="text" value={itemName} onChange={(e) => setItemName(e.target.value)} />
      <br/>
      <label>Price:</label>
      <Tab />
      <input type="text" value={itemPrice} onChange={(e) => setItemPrice(e.target.value)} />
      <br/>
      <Tab />
      <label>Category:</label>
      <Tab />
      <input type="text" value={itemCategory} onChange={(e) => setItemCategory(e.target.value)} />
      <br/>
      <label>Stock Quantity:</label>
      <Tab />
      <input type="text" value={itemStockQuantity} onChange={(e) => setItemStockQuantity(e.target.value)} />
      <br/>
      <label>Description:</label>
      <Tab />
      <input type="text" value={itemDescription} onChange={(e) => setItemDescription(e.target.value)} />
      <br/>
      <br/>
      <button onClick={handleAddItem}>Add Item</button>
    </div>
  );
};

export default AddData;
