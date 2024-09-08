import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from "./pages/Home"
import Restaurant from './pages/Restaurant';
import RestaurantForm from './pages/RestaurantForm';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />  {/* Ruta para la página de inicio (Home) */}
        <Route path="/restaurants" element={<Restaurant />} /> {/* Ruta para la página de restaurantes */}
        <Route path="/restaurant-form" element={<RestaurantForm />}/>
      </Routes>
    </Router>
  );
}

export default App;
