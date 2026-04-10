package inmobiliaria.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "TIPO_PERSONA")
public class TipoPersona {

    @Id
    @SequenceGenerator(name = "seq_tipo_persona", sequenceName = "SEQ_TIPO_PERSONA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tipo_persona")
    @Column(name = "ID_TIPO_PERSONA")
    private Long idTipoPersona;

    @Column(name = "DESCRIPCION", nullable = false, length = 30)
    private String descripcion;

    public TipoPersona() {
    }

    public Long getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(Long idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoPersona{" +
                "idTipoPersona=" + idTipoPersona +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
