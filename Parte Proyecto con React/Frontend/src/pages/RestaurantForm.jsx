import React from 'react';
import { useForm } from 'react-hook-form';

function RestaurantForm() {
  const { register, formState: { errors }, handleSubmit, reset } = useForm();

  const onSubmit = async (data) => {
    const formData = new URLSearchParams();  // Usamos URLSearchParams para datos tipo formulario

    // Llenamos el formData con los datos del formulario
    formData.append('name', data.name);
    formData.append('number', data.number);
    formData.append('food_type', data.food_type);
    formData.append('address', data.address);

    try {
      const response = await fetch('http://127.0.0.1:8000/restaurant_registration/', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',  // Tipo de contenido correcto
        },
        body: formData.toString(),  // Convierte los datos a formato URL-encoded
      });

      if (response.ok) {
        const result = await response.json();
        console.log('Form successfully submitted:', result);
        alert('Restaurant successfully registered!');
        reset();  // Limpia el formulario después de enviarlo
      } else {
        console.error('Failed to submit form:', response.statusText);
        alert('Failed to register restaurant');
      }
    } catch (error) {
      console.error('Error:', error);
      alert('An error occurred while submitting the form');
    }
  };

  return (
    <>
      <div>
        <h2>Restaurant Form</h2>
      </div>
      <form onSubmit={handleSubmit(onSubmit)} method="POST">
        {/* Campo para el nombre del restaurante */}
        <div>
          <label htmlFor="restaurantName">Restaurant Name: </label>
          <input
            type="text"
            name="restaurantName"
            {...register('name', { required: "Restaurant name is required" })}
          />
          {errors.name && <p>{errors.name.message}</p>}
        </div>

        {/* Campo para el número de teléfono */}
        <div>
          <label htmlFor="number">Phone Number: </label>
          <input
            type="number"
            name="number"
            {...register('number', {
              required: "Phone number is required"
            })}
          />
          {errors.number && <p>{errors.number.message}</p>}
        </div>

        {/* Campo para el tipo de comida */}
        <div>
          <label htmlFor="foodType">Food Type: </label>
          <input
            type="text"
            name="foodType"
            {...register('food_type', { required: "Food type is required" })}
          />
          {errors.food_type && <p>{errors.food_type.message}</p>}
        </div>

        {/* Campo para la dirección */}
        <div>
          <label htmlFor="address">Address: </label>
          <input
            type="text"
            name="address"
            {...register('address', { required: "Address is required" })}
          />
          {errors.address && <p>{errors.address.message}</p>}
        </div>

        <input type="submit" value="Submit" />
      </form>
    </>
  );
}

export default RestaurantForm;

