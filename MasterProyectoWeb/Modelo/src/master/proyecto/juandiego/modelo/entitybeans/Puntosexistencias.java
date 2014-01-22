package master.proyecto.juandiego.modelo.entitybeans;

import java.io.Serializable;
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
@NamedQueries( { @NamedQuery(name = "Puntosexistencias.findAll", query = "select o from Puntosexistencias o") })
@Table(name = "JBRF_PUNTOSEXISTENCIAS")
public class Puntosexistencias implements Serializable {
    
    @Id
    @Column(name = "ID_PUNTOSEXISTENCIAS", nullable = false, length = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "SEC_PK")
    private Long idPuntosexistencias;
    
    @Column(name = "PUNTOS_BLURAY")
    private Integer puntosBluray;
    
    @Column(name = "PUNTOS_DVD")
    private Integer puntosDvd;
    
    @Column(name = "PUNTOS_JUEGO")
    private Integer puntosJuego;
    
    @OneToOne
    @JoinColumn(name = "ID_EXISTENCIAS")
    private Existencia Existencia;

    public Puntosexistencias() {
    }

    public Puntosexistencias(Integer puntosBluray,Integer puntosDvd, Integer puntosJuego) {
        this.puntosBluray = puntosBluray;
        this.puntosDvd = puntosDvd;
        this.puntosJuego = puntosJuego;
    }


    public Long getIdPuntosexistencias() {
        return idPuntosexistencias;
    }

    public void setIdPuntosexistencias(Long idPuntosexistencias) {
        this.idPuntosexistencias = idPuntosexistencias;
    }

    public Integer getPuntosBluray() {
        return puntosBluray;
    }

    public void setPuntosBluray(Integer puntosBluray) {
        this.puntosBluray = puntosBluray;
    }

    public Integer getPuntosDvd() {
        return puntosDvd;
    }

    public void setPuntosDvd(Integer puntosDvd) {
        this.puntosDvd = puntosDvd;
    }

    public Integer getPuntosJuego() {
        return puntosJuego;
    }

    public void setPuntosJuego(Integer puntosJuego) {
        this.puntosJuego = puntosJuego;
    }

    public Existencia getExistencia() {
        return Existencia;
    }

    public void setExistencia(Existencia Existencia) {
        this.Existencia = Existencia;
    }
}
