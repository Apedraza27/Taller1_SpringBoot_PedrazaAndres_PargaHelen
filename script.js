const apiUrlProductos = 'http://localhost:8080/api/producto';
const apiUrlMovimientos = 'http://localhost:8080/api/movimientos';

document.addEventListener('DOMContentLoaded', () => {
    cargarProductos();
    cargarMovimientos();
    document.getElementById('productoForm').addEventListener('submit', agregarProducto);
    document.getElementById('movimientoForm').addEventListener('submit', agregarMovimiento);
});

// Cargar productos
async function cargarProductos() {
    const response = await fetch(apiUrlProductos);
    const productos = await response.json();
    const productosTableBody = document.querySelector('#productosTable tbody');
    productosTableBody.innerHTML = '';

    productos.forEach(producto => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${producto.id_producto}</td>
            <td>${producto.nombre_producto}</td>
            <td>${producto.descripcion}</td>
            <td>${producto.precio}</td>
            <td>
                <button class="btn btn-info" onclick="editarProducto(${producto.id_producto})">Editar</button>
                <button class="btn btn-danger" onclick="eliminarProducto(${producto.id_producto})">Eliminar</button>
            </td>
        `;
        productosTableBody.appendChild(row);
    });
}

// Agregar producto
async function agregarProducto(event) {
    event.preventDefault();
    const nombre = document.getElementById('nombre_producto').value;
    const descripcion = document.getElementById('descripcion').value;
    const precio = document.getElementById('precio').value;

    const producto = {
        nombre_producto: nombre,
        descripcion,
        precio: parseFloat(precio) // Asegúrate de que sea un número
    };

    const response = await fetch(apiUrlProductos, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(producto)
    });

    if (response.ok) {
        $('#productoModal').modal('hide');
        cargarProductos();
    } else {
        console.error('Error al agregar el producto');
    }
}

// Editar producto
async function editarProducto(id) {
    const response = await fetch(`${apiUrlProductos}/${id}`);
    const producto = await response.json();

    document.getElementById('nombre_producto').value = producto.nombre_producto;
    document.getElementById('descripcion').value = producto.descripcion;
    document.getElementById('precio').value = producto.precio;

    document.getElementById('productoForm').onsubmit = async (event) => {
        event.preventDefault();
        const updatedProducto = {
            nombre_producto: document.getElementById('nombre_producto').value,
            descripcion: document.getElementById('descripcion').value,
            precio: parseFloat(document.getElementById('precio').value)
        };

        const updateResponse = await fetch(`${apiUrlProductos}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(updatedProducto)
        });

        if (updateResponse.ok) {
            $('#productoModal').modal('hide');
            cargarProductos();
        } else {
            console.error('Error al actualizar el producto');
        }
    };

    $('#productoModal').modal('show');
}

// Eliminar producto
async function eliminarProducto(id) {
    const response = await fetch(`${apiUrlProductos}/${id}`, {
        method: 'DELETE'
    });

    if (response.ok) {
        cargarProductos();
    } else {
        console.error('Error al eliminar el producto');
    }
}

// Cargar movimientos
async function cargarMovimientos() {
    const response = await fetch(apiUrlMovimientos);
    const movimientos = await response.json();
    const movimientosTableBody = document.querySelector('#movimientosTable tbody');
    movimientosTableBody.innerHTML = '';

    movimientos.forEach(movimiento => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${movimiento.id_movimiento}</td>
            <td>${movimiento.id_producto}</td>
            <td>${movimiento.cantidad}</td>
            <td>${movimiento.tipo_movimiento}</td>
            <td>${new Date(movimiento.fecha).toLocaleString()}</td>
            <td>
                <button class="btn btn-danger" onclick="eliminarMovimiento(${movimiento.id_movimiento})">Eliminar</button>
            </td>
        `;
        movimientosTableBody.appendChild(row);
    });
}

// Agregar movimiento
async function agregarMovimiento(event) {
    event.preventDefault();
    const id_producto = document.getElementById('id_producto_movimiento').value;
    const cantidad = document.getElementById('cantidad').value;
    const tipo_movimiento = document.getElementById('tipo_movimiento').value;

    const movimiento = {
        id_producto,
        cantidad: parseInt(cantidad),
        tipo_movimiento,
        fecha: new Date().toISOString()
    };

    const response = await fetch(apiUrlMovimientos, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(movimiento)
    });

    if (response.ok) {
        $('#movimientoModal').modal('hide');
        cargarMovimientos();
    } else {
        console.error('Error al agregar el movimiento');
    }
}

// Eliminar movimiento
async function eliminarMovimiento(id) {
    const response = await fetch(`${apiUrlMovimientos}/${id}`, {
        method: 'DELETE'
    });

    if (response.ok) {
        cargarMovimientos();
    } else {
        console.error('Error al eliminar el movimiento');
    }
}
