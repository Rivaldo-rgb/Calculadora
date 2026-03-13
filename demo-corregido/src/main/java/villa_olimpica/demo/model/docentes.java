package villa_olimpica.demo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "DOCENTES")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class docentes {

    @Id
    @Column(name = "DOCENTE_ID")
    private Long docenteId;

    @Column(name = "TIPO_DOCUMENTO", nullable = false)
    private String tipoDocumento;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "APELLIDO", nullable = false)
    private String apellido;

    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    private Date fechaNacimiento;

    @Column(name = "NIVEL_ESTUDIOS", nullable = false)
    private String nivelEstudios;

    @Column(name = "AREA", nullable = false)
    private String area;

    @Column(name = "GRADO", nullable = false)
    private String grado;

    @Column(name = "EPS", nullable = false)
    private String eps;

    @Column(name = "SALARIO", nullable = false)
    private Double salario;

    public docentes(Long docenteId, String tipoDocumento, String nombre, String apellido,
                    Date fechaNacimiento, String nivelEstudios, String area,
                    String grado, String eps, Double salario) {

        this.docenteId = docenteId;
        this.tipoDocumento = tipoDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nivelEstudios = nivelEstudios;
        this.area = area;
        this.grado = grado;
        this.eps = eps;
        this.salario = salario;
    }
}
