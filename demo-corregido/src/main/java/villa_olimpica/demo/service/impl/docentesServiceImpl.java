package villa_olimpica.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import villa_olimpica.demo.model.docentes;
import villa_olimpica.demo.repository.docentesRepository;
import villa_olimpica.demo.service.docentesService;

@Service
@RequiredArgsConstructor
@Transactional
public class docentesServiceImpl implements docentesService {

    private final docentesRepository docentesRepository;

    @Override
    public docentes crear(docentes d) {
        return docentesRepository.save(d);
    }

    @Override
    public docentes editar(Long id, docentes d) {

        return docentesRepository.findById(id).map(docente -> {

            docente.setTipoDocumento(d.getTipoDocumento());
            docente.setNombre(d.getNombre());
            docente.setApellido(d.getApellido());
            docente.setFechaNacimiento(d.getFechaNacimiento());
            docente.setNivelEstudios(d.getNivelEstudios());
            docente.setArea(d.getArea());
            docente.setGrado(d.getGrado());
            docente.setEps(d.getEps());
            docente.setSalario(d.getSalario());

            return docentesRepository.save(docente);

        }).orElseThrow(() -> new RuntimeException("Docente no encontrado"));
    }

    @Override
    public List<docentes> listar() {
        return docentesRepository.findAll();
    }

    @Override
    public docentes buscarPorId(Long id) {
        return docentesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        docentesRepository.deleteById(id);
    }
}