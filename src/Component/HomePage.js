import React, { useState } from 'react';
import './HomePage.css';

import AddData from './AddData';
import GetData from './GetData';
import UpdateData from './UpdateData';
import DeleteData from './DeleteData';

// Functional component for the home page
const HomePage = () => {
  // State to track the active component on the page
  const [activeComponent, setActiveComponent] = useState('home');

  // Function to render the appropriate component based on the active state
  const renderComponent = () => {
    switch (activeComponent) {
      // If 'add' is active, render the AddData component
      case 'add':
        return <AddData />;
      // If 'get' is active, render the GetData component
      case 'get':
        return <GetData />;
      // If 'update' is active, render the UpdateData component
      case 'update':
        return <UpdateData />;
      // If 'delete' is active, render the DeleteData component
      case 'delete':
        return <DeleteData />;
      // If none of the above cases match, render the default home section
      default:
        return (
          <section>
            <br />
            <br />
            <br />

            {/* Welcome message and description of the shopping mall */}
            <h1>Welcome to our Shopping Mall!</h1>
            <br/>
            <h3>"Step into a world of endless possibilities at LEO City Shopping Mall.
               Where every aisle is a discovery, and every corner holds a new delight. 
               Embrace the joy of shopping, where your needs meet luxury, and your desires find fulfillment. 
               Join us in the pursuit of a perfect shopping experience because at LEO City Shopping Mall , 
               every purchase is a step towards making your dreams come true."</h3>

            {/* Container for dynamically added item modules */}
            <div className="item-container">
              {/* Item modules will be dynamically added here */}
            </div>
          </section>
        );
    }
  };

  // JSX structure for the home page
  return (
    <div>
      {/* Header section with the mall name */}
      <header>
        <h1>LEO City Mall</h1>
      </header>

      {/* Navigation bar with links to different functionalities */}
      <nav>
        <a onClick={() => setActiveComponent('home')} href="#home">
          Home
        </a>
        <a onClick={() => setActiveComponent('add')} href="#add">
          Add Item
        </a>
        <a onClick={() => setActiveComponent('delete')} href="#delete">
          Delete Item
        </a>
        <a onClick={() => setActiveComponent('update')} href="#update">
          Update Item
        </a>
        <a onClick={() => setActiveComponent('get')} href="#get">
          Get Item
        </a>
      </nav>

      {/* Render the component based on the active state */}
      {renderComponent()}

      {/* Footer section with the copyright information */}
      <footer>&copy; 2023 Shopping Mall. All rights reserved.</footer>
    </div>
  );
};

export default HomePage;
