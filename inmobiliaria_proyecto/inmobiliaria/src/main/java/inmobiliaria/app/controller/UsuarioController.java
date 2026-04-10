package inmobiliaria.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inmobiliaria.app.model.Usuario;
import inmobiliaria.app.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // LOGIN
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        return usuarioService.login(body.get("nombreUsuario"), body.get("clave"));
    }

    // REGISTRAR (crear cuenta)
    @PostMapping("/registrar")
    public Usuario registrar(@RequestBody @NonNull Usuario u) {
        return usuarioService.registrar(u);
    }

    // LISTAR
    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable @NonNull Long id) {
        return usuarioService.buscarPorId(id);
    }

    // EDITAR
    @PutMapping("/{id}")
    public Usuario editar(@PathVariable @NonNull Long id, @RequestBody @NonNull Usuario u) {
        return usuarioService.editar(id, u);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable @NonNull Long id) {
        usuarioService.eliminar(id);
    }
}
