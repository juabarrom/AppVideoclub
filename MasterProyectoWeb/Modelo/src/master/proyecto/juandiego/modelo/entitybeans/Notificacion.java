package master.proyecto.juandiego.modelo.entitybeans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.EmbeddedId;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumEstadoNotificacion;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumTipoNotificacion;

@Entity
@SequenceGenerator(sequenceName = "JBRF_IDSECUENCIAL", name = "SEC_PK", initialValue = 2, allocationSize = 1)
@NamedQueries( { @NamedQuery(name = "Notificacion.findAll", query = "select o from Notificacion o") })
@Table(name = "JBRF_NOTIFICACION")
public class Notificacion implements Serializable {
    
    @Id
    @Column(name = "ID_NOTIFICACION", nullable = false, length = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "SEC_PK")
    private Long idNotificacion;
    
    @Column(length = 500)
    private String descripcion;
    
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private EnumEstadoNotificacion estado;
    
    private String fecha;

    @Column(length = 20)
    private String nombre;
    
    @Enumerated(EnumType.STRING)
    private EnumTipoNotificacion Tiponotificacion;
    
    
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario Usuario;

    public Notificacion() {
    }

    public Notificacion(String descripcion, String fecha, String nombre,EnumTipoNotificacion Tiponotificacion,Usuario usuario) {
        
        this.descripcion = descripcion;
        this.fecha = fecha;        
        this.nombre = nombre;
        this.Tiponotificacion=Tiponotificacion;
        this.Usuario = usuario;
        //VALORES POR DEFECTO
        this.estado = EnumEstadoNotificacion.NO_LEIDA;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EnumEstadoNotificacion getEstado() {
        return estado;
    }

    public void setEstado(EnumEstadoNotificacion estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    public void setIdNotificacion(Long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Long getIdNotificacion() {
        return idNotificacion;
    }

    public void setTiponotificacion(EnumTipoNotificacion Tiponotificacion) {
        this.Tiponotificacion = Tiponotificacion;
    }

    public EnumTipoNotificacion getTiponotificacion() {
        return Tiponotificacion;
    }
}
