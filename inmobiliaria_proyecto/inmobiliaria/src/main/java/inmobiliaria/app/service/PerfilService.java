package inmobiliaria.app.service;

import java.util.List;

import org.springframework.lang.NonNull;

import inmobiliaria.app.model.Perfil;

public interface PerfilService {
    Perfil crear(@NonNull Perfil p);
    List<Perfil> listar();
    Perfil buscarPorId(@NonNull Long id);
    Perfil editar(@NonNull Long id, @NonNull Perfil p);
    void eliminar(@NonNull Long id);
}
