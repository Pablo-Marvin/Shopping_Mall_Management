
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './styles.css';


const Tab = () => {
  return <span style={{ marginLeft: '1em' }} />;
};


function GetData() {
  // State variables for managing item data and specific item details
  const [items, setItems] = useState([]);          // State for all items
  const [specificId, setSpecificId] = useState(''); // State for a specific item ID
  const [specificData, setSpecificData] = useState(null); // State for specific item details

  // useEffect hook to fetch all items when the component mounts
  useEffect(() => {
    axios.get('http://localhost:8081/itemss')
      .then(res => {
        setItems(res.data);
      })
      .catch(err => {
        console.log(err);
      });
  }, []);

  // Function to handle the "View All Items" button click
  const handleViewAll = () => {
    setSpecificData(null); // Clear specific item data
  };

  // Function to handle the "View particular Item" button click
  const handleViewSpecific = () => {
    // Fetch specific item data based on the entered ID
    axios.get(`http://localhost:8081/items/${specificId}`)
      .then(res => {
        console.log('API Response:', res.data);
        setSpecificData(res.data); // Set specific item data
      })
      .catch(err => {
        console.error('Error fetching specific item:', err);
      });
  };

  // JSX rendering for the component
  return (
    <div>
      <h1>ITEM DETAILS</h1>
      {/* Button to view all items */}
      <button onClick={handleViewAll}>View All Items</button>
      <br />
      <br/>
      {/* Input field to enter specific item ID */}
      <input
        type="text"
        value={specificId}
        onChange={(e) => setSpecificId(e.target.value)}
        placeholder="Enter ID to view specific item"
      />
      {/* Horizontal tab component */}
      <Tab/>
      {/* Button to view a specific item */}
      <button onClick={handleViewSpecific}>View particular Item</button>
      <br />

      {/* Conditional rendering based on whether specific item data is available */}
      {specificData ? (
        // Display specific item details
        <div>
          <h2> Item Details</h2>
          <br/>
          <p>ID: {specificData.id}</p>
          <br/>
          <p>Name: {specificData.product_Name}</p>
          <br/>
          <p>Price: {specificData.price}</p>
          <br/>
          <p>Category: {specificData.category}</p>
          <br/>
          <p>Stock Quantity: {specificData.stock_quantity}</p>
          <br/>
          <p>Description: {specificData.description}</p>
          <br/>
        </div>
      ) : (
        // Display all item details in a table
        <div>
          <h2>All Item Details</h2>
          <center>
            {/* Table to display all items */}
            <table border='1'>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Price</th>
                  <th>Category</th>
                  <th>Stock Quantity</th>
                  <th>Description</th>
                </tr>
              </thead>
              <tbody>
                {/* Map through items and display each item in a table row */}
                {items.map((item) => (
                  <tr key={item.id}>
                    <td>{item.id}</td>
                    <td>{item.product_Name}</td>
                    <td>{item.price}</td>
                    <td>{item.category}</td>
                    <td>{item.stock_quantity}</td>
                    <td>{item.description}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </center>
        </div>
      )}
    </div>
  );
}

export default GetData;
