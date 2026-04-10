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

import inmobiliaria.app.model.TipoPersona;
import inmobiliaria.app.service.TipoPersonaService;

@RestController
@RequestMapping("/api/tipo-persona")
@CrossOrigin(origins = "*")
public class TipoPersonaController {

    private final TipoPersonaService tipoPersonaService;

    public TipoPersonaController(TipoPersonaService tipoPersonaService) {
        this.tipoPersonaService = tipoPersonaService;
    }

    @PostMapping
    public TipoPersona crear(@RequestBody @NonNull TipoPersona t) { return tipoPersonaService.crear(t); }

    @GetMapping
    public List<TipoPersona> listar() { return tipoPersonaService.listar(); }

    @GetMapping("/{id}")
    public TipoPersona buscar(@PathVariable @NonNull Long id) { return tipoPersonaService.buscarPorId(id); }

    @PutMapping("/{id}")
    public TipoPersona editar(@PathVariable @NonNull Long id, @RequestBody @NonNull TipoPersona t) {
        return tipoPersonaService.editar(id, t);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable @NonNull Long id) { tipoPersonaService.eliminar(id); }
}
