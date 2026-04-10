// ─── Al cargar la página ─────────────────────────────────────────────────────
document.addEventListener("DOMContentLoaded", () => {
    console.log("Página cargada, iniciando cargarSelects()...");
    cargarSelects();
});

// ─── Cargar combos de TipoPersona y Perfil ───────────────────────────────────
async function cargarSelects() {
    console.log("API.tiposPersona =", API.tiposPersona);
    console.log("API.perfiles =", API.perfiles);
    
    // Cargar Tipos de Persona
    try {
        console.log("Llamando a:", API.tiposPersona);
        const resTipo = await fetch(API.tiposPersona);
        console.log("Response status:", resTipo.status);
        
        if (!resTipo.ok) {
            throw new Error(`HTTP ${resTipo.status}: ${resTipo.statusText}`);
        }
        
        const tipos = await resTipo.json();
        console.log("Tipos recibidos:", tipos);
        
        const selTipo = document.getElementById("tipoPersona");
        selTipo.innerHTML = `<option value="">Seleccionar tipo...</option>`;
        
        tipos
            .filter(t => ["Empleado", "Administrador"].includes(t.descripcion))
            .forEach(t => {
                console.log("Agregando opción:", t.descripcion);
                selTipo.innerHTML += `<option value="${t.idTipoPersona}">${t.descripcion}</option>`;
            });
        
        console.log("✓ TipoPersona cargado exitosamente");
    } catch (e) {
        console.error("✗ Error cargando TipoPersona:", e);
        const selTipo = document.getElementById("tipoPersona");
        selTipo.innerHTML = `<option value="">Error: ${e.message}</option>`;
    }

    // Cargar Perfiles
    try {
        console.log("Llamando a:", API.perfiles);
        const resPerfil = await fetch(API.perfiles);
        console.log("Response status:", resPerfil.status);
        
        if (!resPerfil.ok) {
            throw new Error(`HTTP ${resPerfil.status}: ${resPerfil.statusText}`);
        }
        
        const perfiles = await resPerfil.json();
        console.log("Perfiles recibidos:", perfiles);
        
        const selPerfil = document.getElementById("perfil");
        selPerfil.innerHTML = `<option value="">Seleccionar perfil...</option>`;
        
        perfiles.forEach(p => {
            console.log("Agregando opción:", p.descripcion);
            selPerfil.innerHTML += `<option value="${p.idPerfil}">${p.descripcion}</option>`;
        });
        
        console.log("✓ Perfiles cargados exitosamente");
    } catch (e) {
        console.error("✗ Error cargando Perfiles:", e);
        const selPerfil = document.getElementById("perfil");
        selPerfil.innerHTML = `<option value="">Error: ${e.message}</option>`;
    }
    
    console.log("Fin de cargarSelects()");
}

// ─── Mostrar / ocultar contraseña ───────────────────────────────────────────
function togglePass(inputId, iconId) {
    const input = document.getElementById(inputId);
    const icon  = document.getElementById(iconId);
    if (input.type === "password") {
        input.type    = "text";
        icon.className = "bi bi-eye-slash";
    } else {
        input.type    = "password";
        icon.className = "bi bi-eye";
    }
}

// ─── Barra de fuerza de contraseña ──────────────────────────────────────────
function evaluarFuerza() {
    const clave  = document.getElementById("clave").value;
    const barra  = document.getElementById("barraFuerza");
    const texto  = document.getElementById("textoFuerza");

    let puntaje = 0;
    if (clave.length >= 6)  puntaje++;
    if (clave.length >= 10) puntaje++;
    if (/[A-Z]/.test(clave)) puntaje++;
    if (/[0-9]/.test(clave)) puntaje++;
    if (/[^A-Za-z0-9]/.test(clave)) puntaje++;

    const niveles = [
        { color: "bg-danger",  ancho: "20%",  label: "Muy débil" },
        { color: "bg-warning", ancho: "40%",  label: "Débil"     },
        { color: "bg-info",    ancho: "60%",  label: "Regular"   },
        { color: "bg-primary", ancho: "80%",  label: "Fuerte"    },
        { color: "bg-success", ancho: "100%", label: "Muy fuerte"}
    ];

    if (clave.length === 0) {
        barra.className = "password-strength bg-secondary";
        barra.style.width = "0";
        texto.textContent = "";
        return;
    }

    const nivel = niveles[Math.min(puntaje - 1, 4)];
    barra.className  = `password-strength ${nivel.color}`;
    barra.style.width = nivel.ancho;
    texto.textContent = nivel.label;
    texto.style.color = "";
}

// ─── Alerta ──────────────────────────────────────────────────────────────────
function mostrarAlerta(msg, tipo = "danger") {
    const alerta = document.getElementById("alerta");
    const iconos = {
        danger:  "bi-exclamation-triangle-fill",
        success: "bi-check-circle-fill",
        warning: "bi-exclamation-circle-fill"
    };
    alerta.className = `alert alert-${tipo} d-flex align-items-center gap-2`;
    alerta.querySelector("i").className = `bi ${iconos[tipo] || iconos.danger}`;
    document.getElementById("alertaMensaje").textContent = msg;
    alerta.style.display = "flex";
    alerta.scrollIntoView({ behavior: "smooth", block: "nearest" });
}

// ─── Registrar cuenta ────────────────────────────────────────────────────────
async function registrar() {
    // Recoger valores
    const nombre          = document.getElementById("nombre").value.trim();
    const apellido        = document.getElementById("apellido").value.trim();
    const correo          = document.getElementById("correo").value.trim();
    const telefono        = document.getElementById("telefono").value.trim();
    const domicilio       = document.getElementById("domicilio").value.trim();
    const fechaNacimiento = document.getElementById("fechaNacimiento").value;
    const idTipoPersona   = document.getElementById("tipoPersona").value;
    const nombreUsuario   = document.getElementById("nombreUsuario").value.trim();
    const idPerfil        = document.getElementById("perfil").value;
    const clave           = document.getElementById("clave").value;
    const confirmarClave  = document.getElementById("confirmarClave").value;

    // ── Validaciones ────────────────────────────────────────────────────────
    if (!nombre || !apellido) {
        mostrarAlerta("El nombre y apellido son obligatorios."); return;
    }
    if (!idTipoPersona) {
        mostrarAlerta("Selecciona un tipo de persona."); return;
    }
    if (!nombreUsuario) {
        mostrarAlerta("El nombre de usuario es obligatorio."); return;
    }
    if (!idPerfil) {
        mostrarAlerta("Selecciona un perfil."); return;
    }
    if (!clave) {
        mostrarAlerta("La contraseña es obligatoria."); return;
    }
    if (clave.length < 6) {
        mostrarAlerta("La contraseña debe tener al menos 6 caracteres."); return;
    }
    if (clave !== confirmarClave) {
        mostrarAlerta("Las contraseñas no coinciden."); return;
    }

    // ── Construir payload ────────────────────────────────────────────────────
    const body = {
        nombreUsuario,
        clave,
        persona: {
            nombre,
            apellido,
            correo:          correo          || null,
            telefono:        telefono        || null,
            domicilio:       domicilio       || null,
            fechaNacimiento: fechaNacimiento || null,
            tipoPersona: { idTipoPersona: parseInt(idTipoPersona) }
        },
        perfil: { idPerfil: parseInt(idPerfil) }
    };

    // ── Enviar ───────────────────────────────────────────────────────────────
    const btn = document.getElementById("btnRegistrar");
    btn.disabled = true;
    btn.innerHTML = `<span class="spinner-border spinner-border-sm me-2"></span>Registrando...`;

    try {
        const response = await fetch(API.registrar, {
            method:  "POST",
            headers: { "Content-Type": "application/json" },
            body:    JSON.stringify(body)
        });

        if (!response.ok) {
            const err = await response.text();
            mostrarAlerta("Error al registrar: " + err);
            return;
        }

        mostrarAlerta("¡Cuenta creada exitosamente! Redirigiendo al login...", "success");
        setTimeout(() => { window.location.href = "../login/login.html"; }, 2000);

    } catch (error) {
        console.error("Error al registrar:", error);
        mostrarAlerta("No se pudo conectar con el servidor. Verifique que el backend esté activo.");
    } finally {
        btn.disabled = false;
        btn.innerHTML = `<i class="bi bi-person-plus"></i> Crear Cuenta`;
    }
}
