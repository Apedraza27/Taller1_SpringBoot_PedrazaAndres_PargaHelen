// Obtener todos los productos
fetch('http://localhost:8080/api/productos')
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    return response.json();
  })
  .then(data => {
    console.log(data); // Maneja los datos recibidos
  })
  .catch(error => {
    console.error('There was a problem with the fetch operation:', error);
  });

  import axios from 'axios';

// Obtener todos los productos
axios.get('http://localhost:8080/api/productos')
  .then(response => {
    console.log(response.data); // Maneja los datos recibidos
  })
  .catch(error => {
    console.error('There was an error fetching the data:', error);
  });

  