package master.proyecto.juandiego.modelo.entitybeans;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(sequenceName = "JBRF_IDSECUENCIAL", name = "SEC_PK", initialValue = 2, allocationSize = 1)
@NamedQueries( { @NamedQuery(name = "Direccion.findAll", query = "select o from Direccion o") })
@Table(name = "JBRF_DIRECCION")
public class Direccion implements Serializable {
    
    @Id
    @Column(name = "ID_DIRECCION", nullable = false, length = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "SEC_PK")
    private Long idDireccion;
    
    @Column(length = 20)
    private String ciudad;
    
    @Column(name = "COD_POSTAL")
    private Integer codPostal;
    
    @Column(length = 100)
    private String direccion;
    
    @Column(length = 20)
    private String provincia;
    
    @OneToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Usuario Usuario;

    public Direccion() {
    }

    public Direccion(String ciudad, Integer codPostal, String direccion, String provincia) {
        this.ciudad = ciudad;
        this.codPostal = codPostal;
        this.direccion = direccion;
        this.provincia = provincia;
    }


    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(Integer codPostal) {
        this.codPostal = codPostal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public Long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Long idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }
}
