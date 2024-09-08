import React, { useEffect, useState } from "react";
import RestaurantCard from "../components/RestaurantCard";

function Restaurant() {
  // Estado para almacenar los datos de los restaurantes
  const [restaurantData, setRestaurantData] = useState([]);
  const [loading, setLoading] = useState(true); // Estado para manejar el estado de carga
  const [error, setError] = useState(null); // Estado para manejar errores

  // Función para obtener los datos de los restaurantes desde la API
  useEffect(() => {
    const fetchRestaurants = async () => {
      try {
        const response = await fetch('http://127.0.0.1:8000/restaurants/?skip=0&limit=100', {
          method: 'GET',
          headers: {
            'accept': 'application/json',
          },
        });

        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json(); // Convertimos la respuesta en JSON
        setRestaurantData(data); // Guardamos los datos en el estado
      } catch (err) {
        setError(err.message); // Guardamos el error en el estado si ocurre uno
      } finally {
        setLoading(false); // Cambiamos el estado de carga cuando termina la solicitud
      }
    };

    fetchRestaurants(); // Llamamos a la función al montar el componente
  }, []); // [] asegura que useEffect solo se ejecute una vez al montar el componente

  // Muestra de errores
  if (error) {
    return <p>Error: {error}</p>;
  }

  // Muestra de un mensaje de carga mientras se obtienen los datos
  if (loading) {
    return <p>Loading...</p>;
  }

  return (
    <>
      <div>
        <h1>Restaurants Page</h1>
        <p>Here is the list of restaurants...</p>

        {/* Mapeamos los datos de los restaurantes para crear las tarjetas */}
        {restaurantData.length > 0 ? (
          restaurantData.map((restaurant) => (
            <RestaurantCard key={restaurant.id} restaurant={restaurant} />
          ))
        ) : (
          <p>No restaurants found.</p>
        )}
      </div>
    </>
  );
}

export default Restaurant;
