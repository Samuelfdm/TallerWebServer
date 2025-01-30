// Función para cambiar el color de fondo del body
function cambiarColorDeFondo(color) {
    document.body.style.backgroundColor = color;
}

// Función para agregar un elemento a la página
function agregarElemento(tipo, texto) {
    const nuevoElemento = document.createElement(tipo);
    nuevoElemento.textContent = texto;
    document.body.appendChild(nuevoElemento);
}

// Llamar a las funciones cuando se carga la página
window.onload = function () {
    // Agregar un botón para cambiar el color de fondo
    const botonColor = document.createElement("button");
    botonColor.textContent = "Cambiar color de fondo :)";
    botonColor.onclick = function () {
        cambiarColorDeFondo("#5f818d");
    };
    document.body.appendChild(botonColor);

    // Agregar un párrafo con texto
    agregarElemento("p", "Este es un párrafo agregado desde javascript.js");
};