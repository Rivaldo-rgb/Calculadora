package inmobiliaria.app.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import inmobiliaria.app.model.TipoPersona;
import inmobiliaria.app.repository.TipoPersonaRepository;
import inmobiliaria.app.service.TipoPersonaService;

@Service
@Transactional
public class TipoPersonaServiceImpl implements TipoPersonaService {

    private final TipoPersonaRepository tipoPersonaRepository;

    public TipoPersonaServiceImpl(TipoPersonaRepository tipoPersonaRepository) {
        this.tipoPersonaRepository = tipoPersonaRepository;
    }

    @Override
    public TipoPersona crear(@NonNull TipoPersona t) { return tipoPersonaRepository.save(Objects.requireNonNull(t)); }

    @Override
    public List<TipoPersona> listar() { return tipoPersonaRepository.findAll(); }

    @Override
    public TipoPersona buscarPorId(@NonNull Long id) {
        return tipoPersonaRepository.findById(Objects.requireNonNull(id))
                .orElseThrow(() -> new RuntimeException("TipoPersona no encontrado con id: " + id));
    }

    @Override
    public TipoPersona editar(@NonNull Long id, @NonNull TipoPersona t) {
        return tipoPersonaRepository.findById(Objects.requireNonNull(id)).map(tipo -> {
            tipo.setDescripcion(Objects.requireNonNull(t).getDescripcion());
            return tipoPersonaRepository.save(tipo);
        }).orElseThrow(() -> new RuntimeException("TipoPersona no encontrado con id: " + id));
    }

    @Override
    public void eliminar(@NonNull Long id) { tipoPersonaRepository.deleteById(Objects.requireNonNull(id)); }
}
