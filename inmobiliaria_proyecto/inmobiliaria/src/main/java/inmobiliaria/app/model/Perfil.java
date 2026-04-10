package inmobiliaria.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "PERFIL")
public class Perfil {

    @Id
    @SequenceGenerator(name = "seq_perfil", sequenceName = "SEQ_PERFIL", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_perfil")
    @Column(name = "ID_PERFIL")
    private Long idPerfil;

    @Column(name = "DESCRIPCION", nullable = false, length = 30)
    private String descripcion;

    public Perfil() {
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "idPerfil=" + idPerfil +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
