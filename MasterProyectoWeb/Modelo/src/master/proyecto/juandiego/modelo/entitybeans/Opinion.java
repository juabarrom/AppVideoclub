package master.proyecto.juandiego.modelo.entitybeans;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumEstadoOpinion;

@Entity
@NamedQueries( { @NamedQuery(name = "Opinion.findAll", query = "select o from Opinion o") })
@Table(name = "JBRF_OPINION")
public class Opinion implements Serializable {
    
    @Id
    @Column(name = "ID_EXISTENCIA", nullable = false, length = 20, insertable = false, updatable = false)
    private Long idExistencia;
    @Id
    @Column(name = "ID_USUARIO", nullable = false, length = 20, insertable = false, updatable = false)
    private Long idUsuario;
    
    @Column(length = 500)
    private String comentario;
    
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private EnumEstadoOpinion estado;
    
    private String fecha;

    private Double puntuacion;
    
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario Usuario;
    
    @ManyToOne
    @JoinColumn(name = "ID_EXISTENCIA")
    private Existencia Existencia;

    public Opinion() {
    }

    public Opinion(String comentario,String fecha,Double puntuacion,Existencia existencia, Usuario usuario) {
        this.comentario = comentario;
        this.fecha = fecha;
        this.puntuacion = puntuacion;
        this.Usuario = usuario;
        this.Existencia = existencia;
        
        //VALOR POR DEFECTO
        this.estado = EnumEstadoOpinion.ACEPTADA;
    }
    

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public EnumEstadoOpinion getEstado() {
        return estado;
    }

    public void setEstado(EnumEstadoOpinion estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
        if (Usuario != null) {
            this.idUsuario =  Usuario.getIdUsuario();
        }
    }

    public Existencia getExistencia() {
        return Existencia;
    }

    public void setExistencia(Existencia Existencia) {
        this.Existencia = Existencia;
        if (Existencia != null) {
            this.idExistencia =Existencia.getIdExistencia();
        }
    }


    public void setIdExistencia(Long idExistencia) {
        this.idExistencia = idExistencia;
    }

    public Long getIdExistencia() {
        return idExistencia;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }
}
