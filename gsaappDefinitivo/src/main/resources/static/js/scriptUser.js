// Obtener el botón por su ID
const myButton = document.getElementById('dropdown');

// Agregar un event listener para el clic
myButton.addEventListener('click', function() {
  // Agregar la clase 'clicked' al botón
  myButton.classList.add('clicked');
});