// ─── Mostrar / ocultar contraseña ───────────────────────────────────────────
function togglePass() {
    const input = document.getElementById("clave");
    const icon  = document.getElementById("eyeIcon");
    if (input.type === "password") {
        input.type = "text";
        icon.className = "bi bi-eye-slash";
    } else {
        input.type = "password";
        icon.className = "bi bi-eye";
    }
}

// ─── Mostrar alerta ──────────────────────────────────────────────────────────
function mostrarAlerta(msg) {
    const alerta = document.getElementById("alerta");
    document.getElementById("alertaMensaje").textContent = msg;
    alerta.style.display = "flex";
}

function ocultarAlerta() {
    document.getElementById("alerta").style.display = "none";
}

// ─── Iniciar sesión ──────────────────────────────────────────────────────────
async function iniciarSesion() {
    ocultarAlerta();

    const nombreUsuario = document.getElementById("nombreUsuario").value.trim();
    const clave         = document.getElementById("clave").value;

    if (!nombreUsuario || !clave) {
        mostrarAlerta("Por favor completa todos los campos.");
        return;
    }

    const btn = document.getElementById("btnIngresar");
    btn.disabled = true;
    btn.innerHTML = `<span class="spinner-border spinner-border-sm me-2"></span>Verificando...`;

    try {
        const response = await fetch(API.login, {
            method:  "POST",
            headers: { "Content-Type": "application/json" },
            body:    JSON.stringify({ nombreUsuario, clave })
        });

        if (!response.ok) throw new Error("Error del servidor: " + response.status);

        const data = await response.json();

        if (data.exito) {
            sessionStorage.setItem("usuario", JSON.stringify(data.usuario));
            window.location.href = "../index.html";
        } else {
            mostrarAlerta(data.mensaje || "Usuario o contraseña incorrectos.");
        }

    } catch (error) {
        console.error("Error de login:", error);
        mostrarAlerta("No se pudo conectar con el servidor. Verifique que el backend esté activo.");
    } finally {
        btn.disabled = false;
        btn.innerHTML = `<i class="bi bi-box-arrow-in-right"></i> Ingresar`;
    }
}
