package master.proyecto.juandiego.modelo.entitybeans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.PrimaryKeyJoinColumn;

import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumBooleano;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumEstadoExistencia;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumGeneroVideo;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumTipoExistencia;

@Entity
@NamedQueries( { @NamedQuery(name = "Video.findAll", query = "select o from Video o") })
@Table(name = "JBRF_VIDEO")
@PrimaryKeyJoinColumn(name = "ID_EXISTENCIA")
public class Video extends Existencia implements Serializable {
    
    @Temporal(TemporalType.DATE)
    private Date anyo;
    
    private Integer autorizado;
    
    private Integer duracion;
    
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private EnumGeneroVideo genero;
    
    @Column(name = "IMAGEN_GRANDE", length = 100)
    private String imagenGrande;
    
    @Column(name = "IMAGEN_PEQUENYA", length = 100)
    private String imagenPequenya;
    
    @Column(length = 40)
    private String nombre;
    
    @Column(length = 500)
    private String sinopsis;
    
    @Column(length = 100)
    private String trailer;

    public Video() {
        super();
    }

    public Video(Date anyo, Integer autorizado, Integer duracion, EnumBooleano esNovedad, EnumBooleano esProximamente,
                 EnumGeneroVideo genero, String imagenGrande, String imagenPequenya, String nombre,
                String sinopsis, String trailer,Integer descuento, 
                 EnumBooleano tieneDescuento) {
        
        super(descuento,tieneDescuento,"Video",esNovedad,esProximamente);
        
        this.anyo = anyo;
        this.autorizado = autorizado;
        this.duracion = duracion;
        this.genero = genero;
        this.imagenGrande = imagenGrande;
        this.imagenPequenya = imagenPequenya;
        this.nombre = nombre;
        this.sinopsis = sinopsis;
        this.trailer = trailer;
    }

    public Date getAnyo() {
        return anyo;
    }

    public void setAnyo(Date anyo) {
        this.anyo = anyo;
    }

    public Integer getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(Integer autorizado) {
        this.autorizado = autorizado;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public EnumGeneroVideo getGenero() {
        return genero;
    }

    public void setGenero(EnumGeneroVideo genero) {
        this.genero = genero;
    }


    public String getImagenGrande() {
        return imagenGrande;
    }

    public void setImagenGrande(String imagenGrande) {
        this.imagenGrande = imagenGrande;
    }

    public String getImagenPequenya() {
        return imagenPequenya;
    }

    public void setImagenPequenya(String imagenPequenya) {
        this.imagenPequenya = imagenPequenya;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    } 

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
}
