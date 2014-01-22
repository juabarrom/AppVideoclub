package master.proyecto.juandiego.modelo.entitybeans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.EmbeddedId;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.IdClass;

import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumBooleano;

@Entity
@NamedQueries( { @NamedQuery(name = "Produccion.findAll", query = "select o from Produccion o") })
@Table(name = "JBRF_PRODUCCION")
public class Produccion implements Serializable {
    
    @EmbeddedId
    private ProduccionPK idProduccion;
    
    @Column(name = "ES_ACTOR", length = 20)
    @Enumerated(EnumType.STRING)
    private EnumBooleano esActor;
    
    @Column(name = "ES_DIRECTOR", length = 20)
    @Enumerated(EnumType.STRING)
    private EnumBooleano esDirector;
    
    @Column(name = "ES_GUIONISTA", length = 20)
    @Enumerated(EnumType.STRING)
    private EnumBooleano esGuionista;
    
    @ManyToOne
    @JoinColumn(name = "ID_EXISTENCIAS")
    private Existencia Existencia;
    
    @ManyToOne
    @JoinColumn(name = "ID_ADG")
    private Adg Adg;

    public Produccion() {
    }

    public Produccion(EnumBooleano esActor, EnumBooleano esDirector,EnumBooleano esGuionista) {
        this.esActor = esActor;
        this.esDirector = esDirector;
        this.esGuionista = esGuionista;
    }

    public void setIdProduccion(ProduccionPK idProduccion) {
        this.idProduccion = idProduccion;
    }

    public ProduccionPK getIdProduccion() {
        return idProduccion;
    }
    
    public EnumBooleano getEsActor() {
        return esActor;
    }

    public void setEsActor(EnumBooleano esActor) {
        this.esActor = esActor;
    }

    public EnumBooleano getEsDirector() {
        return esDirector;
    }

    public void setEsDirector(EnumBooleano esDirector) {
        this.esDirector = esDirector;
    }

    public EnumBooleano getEsGuionista() {
        return esGuionista;
    }

    public void setEsGuionista(EnumBooleano esGuionista) {
        this.esGuionista = esGuionista;
    }

    public Existencia getExistencia() {
        return Existencia;
    }

    public void setExistencia(Existencia Existencia) {
        this.Existencia = Existencia;
        if (Existencia != null) {
            this.idProduccion.setIdExistencias(Existencia.getIdExistencia());
        }
    }

    public Adg getAdg() {
        return Adg;
    }

    public void setAdg(Adg Adg) {
        this.Adg = Adg;
        if (Adg != null) {
            this.idProduccion.setIdAdg(Adg.getIdAdg());
        }
    }


}
