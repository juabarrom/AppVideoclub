package master.proyecto.juandiego.modelo.entitybeans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.PrimaryKeyJoinColumn;

import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumBooleano;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumEstadoExistencia;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumGeneroJuego;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumPlataformaJuego;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumTipoExistencia;

@Entity
@NamedQueries( { @NamedQuery(name = "Juego.findAll", query = "select o from Juego o") })
@Table(name = "JBRF_JUEGO")
@PrimaryKeyJoinColumn(name = "ID_EXISTENCIA")
public class Juego extends Existencia implements Serializable {
    
    @Column(length = 500)
    private String descripcion;
    
    @Column(length = 100)
    private String gameplay;
    
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private EnumGeneroJuego genero;
    
    @Column(name = "IMAGEN_GRANDE", length = 100)
    private String imagenGrande;
    
    @Column(name = "IMAGEN_PEQUENYA", length = 100)
    private String imagenPequenya;
    
    @Column(length = 20)
    private String nombre;
    
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private EnumPlataformaJuego plataforma;
    
    

    public Juego() {
        super();
    }

    public Juego(String descripcion, String gameplay, EnumGeneroJuego genero, String imagenGrande,
                 String imagenPequenya, String nombre, EnumPlataformaJuego plataforma,
                 Integer descuento, EnumBooleano tieneDescuento,
                 EnumBooleano esNovedad,EnumBooleano esProximamente) {
        
        super(descuento,tieneDescuento,"Juego",esNovedad,esProximamente);
        this.descripcion = descripcion;
        this.gameplay = gameplay;
        this.genero = genero;
        this.imagenGrande = imagenGrande;
        this.imagenPequenya = imagenPequenya;
        this.nombre = nombre;
        this.plataforma = plataforma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGameplay() {
        return gameplay;
    }

    public void setGameplay(String gameplay) {
        this.gameplay = gameplay;
    }

    public EnumGeneroJuego getGenero() {
        return genero;
    }

    public void setGenero(EnumGeneroJuego genero) {
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


    public EnumPlataformaJuego getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(EnumPlataformaJuego plataforma) {
        this.plataforma = plataforma;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Juego)) {
            return false;
        }
        final Juego other = (Juego)object;
        if (!(nombre == null ? other.nombre == null : nombre.equals(other.nombre))) {
            return false;
        }
        if (!(plataforma == null ? other.plataforma == null : plataforma.equals(other.plataforma))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 37;
        int result = super.hashCode();
        result = PRIME * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = PRIME * result + ((plataforma == null) ? 0 : plataforma.hashCode());
        return result;
    }
}
