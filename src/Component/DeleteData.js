
import React, { useState } from 'react';
import axios from 'axios'; 
import './AddData.css'; 
// Functional component for deleting data
const DeleteData = () => {
  // State hook to manage the ID to be deleted
  const [idToDelete, setIdToDelete] = useState('');

  // Function to handle the delete button click
  const handleDelete = () => {
    // Making a DELETE request using Axios to a specific endpoint with the provided ID
    axios
      .delete(`http://localhost:8081/itemss/delete/${idToDelete}`)
      .then((response) => {
        // If the deletion is successful, log the response data to the console
        console.log('Item deleted successfully:', response.data);
        // Resetting the ID to be deleted to an empty string
        setIdToDelete('');
      })
      .catch((error) => {
        // If there is an error during the deletion, log the error to the console
        console.error('Error deleting item:', error);
      });
  };

  // Rendered JSX for the DeleteData component
  return (
    <div>
      <h2>Delete Item</h2>
      <br/>
      {/* Input field to enter the ID of the item to be deleted */}
      <input
        type="text"
        value={idToDelete}
        onChange={(e) => setIdToDelete(e.target.value)}
        placeholder="Enter ID to delete"
      />
      <br />
      <br />
      {/* Button to trigger the handleDelete function when clicked */}
      <button onClick={handleDelete}>Delete</button>
      <br />
      <br />
    </div>
  );
};


export default DeleteData;
