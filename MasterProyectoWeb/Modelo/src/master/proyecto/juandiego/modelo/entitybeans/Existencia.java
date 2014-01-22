package master.proyecto.juandiego.modelo.entitybeans;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumBooleano;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumEstadoExistencia;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumTipoExistencia;

@Entity
@SequenceGenerator(sequenceName = "JBRF_IDSECUENCIAL", name = "SEC_PK", initialValue = 2, allocationSize = 1)
@NamedQueries( { @NamedQuery(name = "Existencia.findAll", query = "select o from Existencia o") })
@Table(name = "JBRF_EXISTENCIA")
@DiscriminatorColumn(name = "TIPO_EXISTENCIA" , discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
public class Existencia implements Serializable {

    @Id
    @Column(name = "ID_EXISTENCIA", nullable = false, length = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "SEC_PK")
    private Long idExistencia;

    private Integer descuento;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private EnumEstadoExistencia estado;

    @Column(name = "TIENE_DESCUENTO", length = 20)
    @Enumerated(EnumType.STRING)
    private EnumBooleano tieneDescuento;

    @Column(name = "TIPO_EXISTENCIA", nullable = false, length = 20)
    private String tipoExistencia;
    
    @Column(name = "NPERSONAS_VOTACION")
    private Integer npersonaVotacion;
    
    private Double puntuacion;

    @Column(name = "ES_NOVEDAD", length = 20)
    @Enumerated(EnumType.STRING)
    private EnumBooleano esNovedad;
    
    @Column(name = "ES_PROXIMAMENTE", length = 20)
    @Enumerated(EnumType.STRING)
    private EnumBooleano esProximamente;
    
    @OneToMany(mappedBy = "Existencia")
    private List<Produccion> ProduccionList;

    @OneToMany(mappedBy = "Existencia")
    private List<Detallepedido> DetallepedidoList;

    @OneToOne(mappedBy = "Existencia")
    private Puntosexistencias Puntosexistencias;

    @OneToOne(mappedBy = "Existencia")
    private Udsexistencia Udsexistencia;

    @OneToMany(mappedBy = "Existencia")
    private List<Opinion> OpinionList;

    public Existencia() {
    }

    public Existencia(Integer descuento, EnumBooleano tieneDescuento,String tipoExistencia,
                      EnumBooleano esNovedad,EnumBooleano esProximamente) {
        
        this.descuento = descuento;
        this.tieneDescuento = tieneDescuento;
        this.tipoExistencia = tipoExistencia;
        this.esNovedad = esNovedad;
        this.esProximamente = esProximamente;
        
        this.estado = EnumEstadoExistencia.CATALOGADO;
        this.npersonaVotacion=0;
        this.puntuacion=0.0;
        
        
        //INICIALIZAMOS COLECCIONES
        OpinionList = new ArrayList<Opinion>();
        DetallepedidoList = new ArrayList<Detallepedido>();
        ProduccionList = new ArrayList<Produccion>();
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public EnumEstadoExistencia getEstado() {
        return estado;
    }

    public void setEstado(EnumEstadoExistencia estado) {
        this.estado = estado;
    }

    public Long getIdExistencia() {
        return idExistencia;
    }

    public void setIdExistencia(Long idExistencia) {
        this.idExistencia = idExistencia;
    }

    public EnumBooleano getTieneDescuento() {
        return tieneDescuento;
    }

    public void setTieneDescuento(EnumBooleano tieneDescuento) {
        this.tieneDescuento = tieneDescuento;
    }

    public String getTipoExistencia() {
        return tipoExistencia;
    }

    public void setTipoExistencia(String tipoExistencia) {
        this.tipoExistencia = tipoExistencia;
    }

    public Integer getNpersonaVotacion() {
        return npersonaVotacion;
    }

    public void setNpersonaVotacion(Integer npersonaVotacion) {
        this.npersonaVotacion = npersonaVotacion;
    }
    
    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }
    

    public EnumBooleano getEsNovedad() {
        return esNovedad;
    }

    public void setEsNovedad(EnumBooleano esNovedad) {
        this.esNovedad = esNovedad;
    }

    public EnumBooleano getEsProximamente() {
        return esProximamente;
    }

    public void setEsProximamente(EnumBooleano esProximamente) {
        this.esProximamente = esProximamente;
    }
    
    public List<Produccion> getProduccionList() {
        return ProduccionList;
    }

    public void setProduccionList(List<Produccion> ProduccionList) {
        this.ProduccionList = ProduccionList;
    }

    public Produccion addProduccion(Produccion produccion) {
        getProduccionList().add(produccion);
        produccion.setExistencia(this);
        return produccion;
    }

    public Produccion removeProduccion(Produccion produccion) {
        getProduccionList().remove(produccion);
        produccion.setExistencia(null);
        return produccion;
    }

    public List<Detallepedido> getDetallepedidoList() {
        return DetallepedidoList;
    }

    public void setDetallepedidoList(List<Detallepedido> DetallepedidoList) {
        this.DetallepedidoList = DetallepedidoList;
    }

    public Detallepedido addDetallepedido(Detallepedido detallepedido) {
        getDetallepedidoList().add(detallepedido);
        detallepedido.setExistencia(this);
        return detallepedido;
    }

    public Detallepedido removeDetallepedido(Detallepedido detallepedido) {
        getDetallepedidoList().remove(detallepedido);
        detallepedido.setExistencia(null);
        return detallepedido;
    }

    public Puntosexistencias getPuntosexistencias() {
        return Puntosexistencias;
    }

    public void setPuntosexistenciasList(Puntosexistencias Puntosexistencias) {
        this.Puntosexistencias = Puntosexistencias;
    }
    
    public Udsexistencia getUdsexistencia() {
        return Udsexistencia;
    }

    public void setUdsexistencia(Udsexistencia Udsexistencia) {
        this.Udsexistencia = Udsexistencia;
    }

    public List<Opinion> getOpinionList() {
        return OpinionList;
    }

    public void setOpinionList(List<Opinion> OpinionList) {
        this.OpinionList = OpinionList;
    }

    public Opinion addOpinion(Opinion opinion) {
        getOpinionList().add(opinion);
        opinion.setExistencia(this);
        return opinion;
    }

    public Opinion removeOpinion(Opinion opinion) {
        getOpinionList().remove(opinion);
        opinion.setExistencia(null);
        return opinion;
    }
}
