const API_BASE = "http://localhost:5052/api";

const API = {
    health:      `${API_BASE}/health`,
    login:       `${API_BASE}/usuarios/login`,
    registrar:   `${API_BASE}/usuarios/registrar`,
    usuarios:    `${API_BASE}/usuarios`,
    perfiles:    `${API_BASE}/perfiles`,
    tiposPersona:`${API_BASE}/tipo-persona`
};
