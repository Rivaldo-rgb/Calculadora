let editandoId = null;

document.addEventListener("DOMContentLoaded", () => {

    console.log("JS cargado");

    const form = document.getElementById("formDocente");

    form.addEventListener("submit", guardar);

    listar();
});

async function listar() {

    try {
        const response = await fetch(API.docentes);

        if (!response.ok) {
            console.error("Error al listar docentes:", response.status);
            return;
        }

        const data = await response.json();
        const tabla = document.getElementById("tablaDocentes");
        tabla.innerHTML = "";

        data.forEach(d => {
            tabla.innerHTML += `
            <tr>
                <td>${d.docenteId}</td>
                <td>${d.tipoDocumento}</td>
                <td>${d.nombre}</td>
                <td>${d.apellido}</td>
                <td>${d.area}</td>
                <td>${d.grado}</td>
                <td>${d.salario}</td>

                <td>

                    <button class="btn btn-warning btn-sm"
                    onclick="editar(${d.docenteId})">
                    Editar
                    </button>

                    <button class="btn btn-danger btn-sm"
                    onclick="eliminar(${d.docenteId})">
                    Eliminar
                    </button>

                </td>
            </tr>
            `;
        });

    } catch (error) {
        console.error("No se pudo conectar con el servidor:", error);
        alert("No se pudo conectar con el servidor. Verifique que el backend esté corriendo en el puerto 5052.");
    }
}

async function guardar(e) {

    e.preventDefault();

    console.log("Guardar presionado");

    const docenteIdVal = document.getElementById("docenteId").value;
    const salarioVal = document.getElementById("salario").value;

    const docente = {
        docenteId: docenteIdVal ? parseInt(docenteIdVal) : null,
        tipoDocumento: document.getElementById("tipoDocumento").value,
        nombre: document.getElementById("nombre").value,
        apellido: document.getElementById("apellido").value,
        fechaNacimiento: document.getElementById("fechaNacimiento").value,
        nivelEstudios: document.getElementById("nivelEstudios").value,
        area: document.getElementById("area").value,
        grado: document.getElementById("grado").value,
        eps: document.getElementById("eps").value,
        salario: salarioVal ? parseFloat(salarioVal) : null
    };

    try {
        let response;

        if (editandoId !== null) {
            // MODO EDICIÓN - PUT
            response = await fetch(`${API.docentes}/${editandoId}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(docente)
            });
        } else {
            // MODO CREACIÓN - POST
            response = await fetch(API.docentes, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(docente)
            });
        }

        if (!response.ok) {
            const errorText = await response.text();
            console.error("Error del servidor:", errorText);
            alert("Error al guardar: " + errorText);
            return;
        }

        alert(editandoId !== null ? "Docente actualizado" : "Docente guardado");

        editandoId = null;
        document.getElementById("formDocente").reset();

        // Restaurar texto del botón
        document.querySelector("#formDocente button[type='submit']").innerHTML =
            '<i class="bi bi-save"></i> Guardar';

        listar();

    } catch (error) {
        console.error("Error de conexión al guardar:", error);
        alert("No se pudo conectar con el servidor.");
    }
}

async function editar(id) {

    try {
        const response = await fetch(`${API.docentes}/${id}`);

        if (!response.ok) {
            alert("No se pudo cargar el docente.");
            return;
        }

        const d = await response.json();

        // Rellenar formulario con los datos del docente
        document.getElementById("docenteId").value = d.docenteId;
        document.getElementById("tipoDocumento").value = d.tipoDocumento;
        document.getElementById("nombre").value = d.nombre;
        document.getElementById("apellido").value = d.apellido;
        document.getElementById("fechaNacimiento").value = d.fechaNacimiento
            ? d.fechaNacimiento.split("T")[0]
            : "";
        document.getElementById("nivelEstudios").value = d.nivelEstudios;
        document.getElementById("area").value = d.area;
        document.getElementById("grado").value = d.grado;
        document.getElementById("eps").value = d.eps;
        document.getElementById("salario").value = d.salario;

        editandoId = id;

        // Cambiar texto del botón para indicar modo edición
        document.querySelector("#formDocente button[type='submit']").innerHTML =
            '<i class="bi bi-pencil-square"></i> Actualizar';

        // Scroll al formulario
        document.getElementById("formDocente").scrollIntoView({ behavior: "smooth" });

    } catch (error) {
        console.error("Error al cargar docente:", error);
        alert("No se pudo conectar con el servidor.");
    }
}

async function eliminar(id) {

    if (!confirm("¿Eliminar docente?")) return;

    try {
        const response = await fetch(`${API.docentes}/${id}`, {
            method: "DELETE"
        });

        if (!response.ok) {
            alert("Error al eliminar el docente.");
            return;
        }

        listar();

    } catch (error) {
        console.error("Error al eliminar:", error);
        alert("No se pudo conectar con el servidor.");
    }
}
