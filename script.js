let pantalla = document.getElementById('pantalla');

function agregarNumero(numero) {
    if (pantalla.value === '0' && numero !== '.') {
        pantalla.value = numero;
    } else if (numero === '.' && pantalla.value.includes('.')) {
        return;
    } else {
        pantalla.value += numero;
    }
}

function agregarOperador(operador) {
    if (pantalla.value === '') return;
    pantalla.value += ' ' + operador + ' ';
}

function calcular() {
    try {
        let resultado = eval(pantalla.value.replace('×', '*'));
        pantalla.value = resultado;
    } catch (error) {
        pantalla.value = 'Error';
    }
}

function limpiar() {
    pantalla.value = '0';
}

function borrar() {
    pantalla.value = pantalla.value.slice(0, -1);
    if (pantalla.value === '') {
        pantalla.value = '0';
    }
}