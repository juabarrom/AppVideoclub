package master.proyecto.juandiego.modelo.entitybeans;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(sequenceName = "JBRF_IDSECUENCIAL", name = "SEC_PK", initialValue = 2, allocationSize = 1)
@NamedQueries( { @NamedQuery(name = "Pedido.findAll", query = "select o from Pedido o") })
@Table(name = "JBRF_PEDIDO")
public class Pedido implements Serializable {
    
    @Id
    @Column(name = "ID_PEDIDO", nullable = false, length = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "SEC_PK")
    private Long idPedido;
    
    @Column(name = "FECHA_ENTREGA")
    private String fechaEntrega;
    
    @Column(name = "FECHA_PEDIDO")
    private String fechaPedido;
    
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Usuario Usuario;
    
    @OneToMany(mappedBy = "Pedido")
    private List<Detallepedido> DetallepedidoList;

    public Pedido() {
    }

    public Pedido(String fechaEntrega, String fechaPedido) {
        this.fechaEntrega = fechaEntrega;
        this.fechaPedido = fechaPedido;
        
        //INICIALIZAMOS COLECCIONES
        DetallepedidoList = new ArrayList<Detallepedido>();
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }


    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }  

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    public List<Detallepedido> getDetallepedidoList() {
        return DetallepedidoList;
    }

    public void setDetallepedidoList(List<Detallepedido> DetallepedidoList) {
        this.DetallepedidoList = DetallepedidoList;
    }

    public Detallepedido addDetallepedido(Detallepedido detallepedido) {
        getDetallepedidoList().add(detallepedido);
        detallepedido.setPedido(this);
        return detallepedido;
    }

    public Detallepedido removeDetallepedido(Detallepedido detallepedido) {
        getDetallepedidoList().remove(detallepedido);
        detallepedido.setPedido(null);
        return detallepedido;
    }
}
