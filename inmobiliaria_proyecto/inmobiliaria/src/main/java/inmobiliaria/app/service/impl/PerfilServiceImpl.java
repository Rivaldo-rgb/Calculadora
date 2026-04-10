package inmobiliaria.app.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import inmobiliaria.app.model.Perfil;
import inmobiliaria.app.repository.PerfilRepository;
import inmobiliaria.app.service.PerfilService;

@Service
@Transactional
public class PerfilServiceImpl implements PerfilService {

    private final PerfilRepository perfilRepository;

    public PerfilServiceImpl(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    @Override
    public Perfil crear(@NonNull Perfil p) { return perfilRepository.save(Objects.requireNonNull(p)); }

    @Override
    public List<Perfil> listar() { return perfilRepository.findAll(); }

    @Override
    public Perfil buscarPorId(@NonNull Long id) {
        return perfilRepository.findById(Objects.requireNonNull(id))
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado con id: " + id));
    }

    @Override
    public Perfil editar(@NonNull Long id, @NonNull Perfil p) {
        return perfilRepository.findById(Objects.requireNonNull(id)).map(perfil -> {
            perfil.setDescripcion(Objects.requireNonNull(p).getDescripcion());
            return perfilRepository.save(perfil);
        }).orElseThrow(() -> new RuntimeException("Perfil no encontrado con id: " + id));
    }

    @Override
    public void eliminar(@NonNull Long id) { perfilRepository.deleteById(Objects.requireNonNull(id)); }
}
