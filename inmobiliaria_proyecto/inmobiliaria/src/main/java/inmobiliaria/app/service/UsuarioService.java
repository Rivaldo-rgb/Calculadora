package inmobiliaria.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.lang.NonNull;

import inmobiliaria.app.model.Usuario;

public interface UsuarioService {
    Map<String, Object> login(String nombreUsuario, String clave);
    Usuario registrar(@NonNull Usuario usuario);
    List<Usuario> listar();
    Usuario buscarPorId(@NonNull Long id);
    Usuario editar(@NonNull Long id, @NonNull Usuario u);
    void eliminar(@NonNull Long id);
}
