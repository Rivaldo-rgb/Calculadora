package inmobiliaria.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import inmobiliaria.app.model.Usuario;
import inmobiliaria.app.repository.PersonaRepository;
import inmobiliaria.app.repository.UsuarioRepository;
import inmobiliaria.app.service.UsuarioService;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PersonaRepository personaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    public Map<String, Object> login(String nombreUsuario, String clave) {
        Map<String, Object> resp = new HashMap<>();
        Optional<Usuario> opt = usuarioRepository.findByNombreUsuario(nombreUsuario);
        if (opt.isPresent() && opt.get().getClave().equals(clave)) {
            resp.put("exito", true);
            resp.put("usuario", opt.get());
        } else {
            resp.put("exito", false);
            resp.put("mensaje", "Usuario o contraseña incorrectos");
        }
        return resp;
    }

    @Override
    public Usuario registrar(@NonNull Usuario u) {
        if (usuarioRepository.existsByNombreUsuario(Objects.requireNonNull(u).getNombreUsuario()))
            throw new RuntimeException("El nombre de usuario ya está en uso");
        personaRepository.save(Objects.requireNonNull(u.getPersona()));
        return usuarioRepository.save(u);
    }

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(@NonNull Long id) {
        return usuarioRepository.findById(Objects.requireNonNull(id))
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    @Override
    public Usuario editar(@NonNull Long id, @NonNull Usuario u) {
        return usuarioRepository.findById(Objects.requireNonNull(id)).map(usuario -> {
            usuario.setNombreUsuario(Objects.requireNonNull(u).getNombreUsuario());
            usuario.setClave(u.getClave());
            usuario.setPerfil(u.getPerfil());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    @Override
    public void eliminar(@NonNull Long id) {
        usuarioRepository.deleteById(Objects.requireNonNull(id));
    }
}
