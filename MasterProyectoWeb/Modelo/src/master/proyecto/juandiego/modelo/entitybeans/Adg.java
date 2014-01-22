package master.proyecto.juandiego.modelo.entitybeans;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(sequenceName = "JBRF_IDSECUENCIAL", name = "SEC_PK", initialValue = 2, allocationSize = 1)
@NamedQueries( { @NamedQuery(name = "Adg.findAll", query = "select o from Adg o") })
@Table(name = "JBRF_ADG")
public class Adg implements Serializable {
    
    @Id
    @Column(name = "ID_ADG", nullable = false, length = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "SEC_PK")
    private Long idAdg;

    @Column(name = "NOMBRE_ARTISTICO", length = 40)
    private String nombreArtistico;

    @OneToMany(mappedBy = "Adg")
    private List<Produccion> ProduccionList;

    public Adg() {
    }

    public Adg(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
        
        //INICIALIZAMOS LAS COLECCIONES
        ProduccionList = new ArrayList<Produccion>(); 
    }
    

    public Long getIdAdg() {
        return idAdg;
    }

    public void setIdAdg(Long idAdg) {
        this.idAdg = idAdg;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public List<Produccion> getProduccionList() {
        return ProduccionList;
    }

    public void setProduccionList(List<Produccion> ProduccionList) {
        this.ProduccionList = ProduccionList;
    }

    public Produccion addProduccion(Produccion produccion) {
        getProduccionList().add(produccion);
        produccion.setAdg(this);
        return produccion;
    }

    public Produccion removeProduccion(Produccion produccion) {
        getProduccionList().remove(produccion);
        produccion.setAdg(null);
        return produccion;
    }
}
