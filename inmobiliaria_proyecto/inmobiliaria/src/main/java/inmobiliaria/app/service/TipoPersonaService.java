package inmobiliaria.app.service;

import java.util.List;

import org.springframework.lang.NonNull;

import inmobiliaria.app.model.TipoPersona;

public interface TipoPersonaService {
    TipoPersona crear(@NonNull TipoPersona t);
    List<TipoPersona> listar();
    TipoPersona buscarPorId(@NonNull Long id);
    TipoPersona editar(@NonNull Long id, @NonNull TipoPersona t);
    void eliminar(@NonNull Long id);
}
