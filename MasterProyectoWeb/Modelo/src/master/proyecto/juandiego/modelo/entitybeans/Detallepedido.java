package master.proyecto.juandiego.modelo.entitybeans;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumDetallePedido;

@Entity
@NamedQueries( { @NamedQuery(name = "Detallepedido.findAll", query = "select o from Detallepedido o") })
@Table(name = "JBRF_DETALLEPEDIDO")
public class Detallepedido implements Serializable {
    
    @Id
    @Column(name = "ID_EXISTENCIA", nullable = false, length = 20, insertable = false, updatable = false)
    private Long idExistencia;
    @Id
    @Column(name = "ID_PEDIDO", nullable = false, length = 20, insertable = false, updatable = false)
    private Long idPedido;
    
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private EnumDetallePedido estado;
    
    @Column(name = "UDS_PRODUCTO")
    private Integer udsProducto;
    
    @ManyToOne
    @JoinColumn(name = "ID_EXISTENCIA")
    private Existencia Existencia;
    
    @ManyToOne
    @JoinColumn(name = "ID_PEDIDO")
    private Pedido Pedido;

    public Detallepedido() {
    }


    public Detallepedido(Integer udsProducto, Existencia Existencia, Pedido Pedido) {
        this.estado = EnumDetallePedido.ENVIADO;
        this.udsProducto = udsProducto;
        this.Existencia=Existencia;
        this.Pedido=Pedido;
    }


    
    public EnumDetallePedido getEstado() {
        return estado;
    }

    public void setEstado(EnumDetallePedido estado) {
        this.estado = estado;
    }

    public Integer getUdsProducto() {
        return udsProducto;
    }

    public void setUdsProducto(Integer udsProducto) {
        this.udsProducto = udsProducto;
    }


    public void setIdExistencia(Long idExistencia) {
        this.idExistencia = idExistencia;
    }

    public Long getIdExistencia() {
        return idExistencia;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setExistencia(Existencia Existencia) {
        this.Existencia = Existencia;
        if(Existencia != null){
            this.idExistencia=Existencia.getIdExistencia();
        }
    }

    public Existencia getExistencia() {
        return Existencia;
    }

    public void setPedido(Pedido Pedido) {
        this.Pedido = Pedido ;
        if(Pedido != null){
            this.idPedido = Pedido.getIdPedido();
        }
    }

    public Pedido getPedido() {
        return Pedido;
    }
}
