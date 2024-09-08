import React from 'react';
import { Link } from 'react-router-dom';

function Home() {
  return (
    <div>
      <h1>Welcome to the Restaurant Portal</h1>
      <p>This is the home page.</p>

      <button>
        <Link to="/restaurants">Go to Restaurants</Link>
      </button>

      <button>
        <Link to="/restaurants">View Restaurants</Link>
      </button>

      <button>
        <Link to="/restaurant-form">Register Restaurant</Link>
      </button>
    </div>
  );
}

export default Home;



