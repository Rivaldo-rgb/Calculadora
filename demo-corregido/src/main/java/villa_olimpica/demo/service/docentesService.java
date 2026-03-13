package villa_olimpica.demo.service;

import java.util.List;

import villa_olimpica.demo.model.docentes;

public interface docentesService {

    docentes crear(docentes d);

    docentes editar(Long id, docentes d);

    List<docentes> listar();

    docentes buscarPorId(Long id);

    void eliminar(Long id);

}