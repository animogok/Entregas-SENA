import React from 'react';

function RestaurantCard({ restaurant }) {
  return (
    <div>
      <h2>{restaurant.name}</h2>
      <p><strong>Owner:</strong> {restaurant.owner_id} </p>
      <p><strong>Phone Number:</strong> {restaurant.number}</p>
      <p><strong>Food Type:</strong> {restaurant.food_type}</p>
      <p><strong>Address:</strong> {restaurant.address}</p>
      <hr />
    </div>
  );
}

export default RestaurantCard;