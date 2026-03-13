package villa_olimpica.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import villa_olimpica.demo.model.docentes;
import villa_olimpica.demo.service.docentesService;

@RestController
@RequestMapping("/api/docentes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class docentesController {

    private final docentesService docentesService;

    // CREAR DOCENTE
    @PostMapping
    public docentes crear(@RequestBody docentes d) {
        return docentesService.crear(d);
    }

    // LISTAR DOCENTES
    @GetMapping
    public List<docentes> listar() {
        return docentesService.listar();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public docentes buscar(@PathVariable Long id) {
        return docentesService.buscarPorId(id);
    }

    // EDITAR DOCENTE
    @PutMapping("/{id}")
    public docentes editar(@PathVariable Long id, @RequestBody docentes d) {
        return docentesService.editar(id, d);
    }

    // ELIMINAR DOCENTE
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        docentesService.eliminar(id);
    }
}