package inmobiliaria.app.controller;

import java.util.List;

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

import inmobiliaria.app.model.Perfil;
import inmobiliaria.app.service.PerfilService;

@RestController
@RequestMapping("/api/perfiles")
@CrossOrigin(origins = "*")
public class PerfilController {

    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @PostMapping
    public Perfil crear(@RequestBody @NonNull Perfil p) { return perfilService.crear(p); }

    @GetMapping
    public List<Perfil> listar() { return perfilService.listar(); }

    @GetMapping("/{id}")
    public Perfil buscar(@PathVariable @NonNull Long id) { return perfilService.buscarPorId(id); }

    @PutMapping("/{id}")
    public Perfil editar(@PathVariable @NonNull Long id, @RequestBody @NonNull Perfil p) {
        return perfilService.editar(id, p);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable @NonNull Long id) { perfilService.eliminar(id); }
}
