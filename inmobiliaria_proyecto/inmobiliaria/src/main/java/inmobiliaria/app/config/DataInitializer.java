package inmobiliaria.app.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import inmobiliaria.app.model.Perfil;
import inmobiliaria.app.model.TipoPersona;
import inmobiliaria.app.repository.PerfilRepository;
import inmobiliaria.app.repository.TipoPersonaRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final TipoPersonaRepository tipoPersonaRepository;
    private final PerfilRepository perfilRepository;

    public DataInitializer(TipoPersonaRepository tipoPersonaRepository, 
                          PerfilRepository perfilRepository) {
        this.tipoPersonaRepository = tipoPersonaRepository;
        this.perfilRepository = perfilRepository;
    }

    @Override
    public void run(String... args) {
        // Inicializar Tipos de Persona
        List<TipoPersona> tipos = tipoPersonaRepository.findAll();

        boolean tieneEmpleado = tipos.stream()
                .anyMatch(tipo -> "Empleado".equalsIgnoreCase(tipo.getDescripcion()));
        boolean tieneAdministrador = tipos.stream()
                .anyMatch(tipo -> "Administrador".equalsIgnoreCase(tipo.getDescripcion()));

        if (!tieneEmpleado) {
            TipoPersona empleado = new TipoPersona();
            empleado.setDescripcion("Empleado");
            tipoPersonaRepository.save(empleado);
            System.out.println("✓ TipoPersona 'Empleado' creado");
        }

        if (!tieneAdministrador) {
            TipoPersona administrador = new TipoPersona();
            administrador.setDescripcion("Administrador");
            tipoPersonaRepository.save(administrador);
            System.out.println("✓ TipoPersona 'Administrador' creado");
        }

        // Inicializar Perfiles
        List<Perfil> perfiles = perfilRepository.findAll();

        boolean tienePerfilAdmin = perfiles.stream()
                .anyMatch(perfil -> "Administrador".equalsIgnoreCase(perfil.getDescripcion()));
        boolean tienePerfilAgente = perfiles.stream()
                .anyMatch(perfil -> "Agente".equalsIgnoreCase(perfil.getDescripcion()));

        if (!tienePerfilAdmin) {
            Perfil admin = new Perfil();
            admin.setDescripcion("Administrador");
            perfilRepository.save(admin);
            System.out.println("✓ Perfil 'Administrador' creado");
        }

        if (!tienePerfilAgente) {
            Perfil agente = new Perfil();
            agente.setDescripcion("Agente");
            perfilRepository.save(agente);
            System.out.println("✓ Perfil 'Agente' creado");
        }
    }
}
