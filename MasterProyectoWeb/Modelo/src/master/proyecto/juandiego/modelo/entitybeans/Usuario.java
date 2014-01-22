package master.proyecto.juandiego.modelo.entitybeans;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumBooleano;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumEstadoUsuario;

@Entity
@SequenceGenerator(sequenceName = "JBRF_IDSECUENCIAL", name = "SEC_PK", initialValue = 2, allocationSize = 1)
@NamedQueries( { @NamedQuery(name = "Usuario.findAll", query = "select o from Usuario o") })
@Table(name = "JBRF_USUARIO")
public class Usuario implements Serializable {
    
    @Id
    @Column(name = "ID_USUARIO", nullable = false, length = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "SEC_PK")
    private Long idUsuario;
    
    @Column(length = 20)
    private String apellido;
    
    @Column(length = 20)
    private String contrasenya;
    
    @Column(length = 20)
    private String correo;
    
    @Column(name = "ES_ADMIN", length = 20)
    @Enumerated(EnumType.STRING)
    private EnumBooleano esAdmin;
    
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private EnumEstadoUsuario estado;
    
    private Integer movil;   
    
    @Column(length = 20)
    private  String nombre;
        
    private Integer puntos;
    
    private Integer telefono;
    
    @OneToOne(mappedBy = "Usuario")
    private Direccion Direccion;
    
    @OneToMany(mappedBy = "Usuario")
    private List<Notificacion> NotificacionList;
    
    @OneToMany(mappedBy = "Usuario")
    private List<Opinion> OpinionList;
    
    @OneToMany(mappedBy = "Usuario")
    private List<Pedido> PedidoList;
    
    
    public Usuario() {
    }

    public Usuario(String nombre,String apellido, String contrasenya, 
                   String correo,Integer movil, Integer telefono) {
        this.apellido = apellido;
        this.contrasenya = contrasenya;
        this.correo = correo;
        this.movil = movil;
        this.nombre = nombre;
        this.telefono = telefono;
        
        //VALORES PREDETERMINADOS PARA UN USUARIO INICIAL
        estado = EnumEstadoUsuario.ACTIVO;
        esAdmin = EnumBooleano.NO;
        this.puntos = 100000;
        //INICIALIZAMOS COLECCIONES
        NotificacionList = new ArrayList<Notificacion>();
        OpinionList = new ArrayList<Opinion>();
        PedidoList = new ArrayList<Pedido>();
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public EnumBooleano getEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(EnumBooleano esAdmin) {
        this.esAdmin = esAdmin;
    }

    public EnumEstadoUsuario getEstado() {
        return estado;
    }

    public void setEstado(EnumEstadoUsuario estado) {
        this.estado = estado;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getMovil() {
        return movil;
    }

    public void setMovil(Integer movil) {
        this.movil = movil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Direccion getDireccion() {
        return Direccion;
    }

    public void setDireccion(Direccion Direccion) {
        this.Direccion = Direccion;
    }
    
    public List<Opinion> getOpinionList() {
        return OpinionList;
    }

    public void setOpinionList(List<Opinion> OpinionList) {
        this.OpinionList = OpinionList;
    }

    public Opinion addOpinion(Opinion opinion) {
        getOpinionList().add(opinion);
        opinion.setUsuario(this);
        return opinion;
    }

    public Opinion removeOpinion(Opinion opinion) {
        getOpinionList().remove(opinion);
        opinion.setUsuario(null);
        return opinion;
    }

    public List<Pedido> getPedidoList() {
        return PedidoList;
    }

    public void setPedidoList(List<Pedido> PedidoList) {
        this.PedidoList = PedidoList;
    }

    public Pedido addPedido(Pedido pedido) {
        getPedidoList().add(pedido);
        pedido.setUsuario(this);
        return pedido;
    }

    public Pedido removePedido(Pedido pedido) {
        getPedidoList().remove(pedido);
        pedido.setUsuario(null);
        return pedido;
    }


    public void setNotificacionList(List<Notificacion> NotificacionList) {
        this.NotificacionList = NotificacionList;
    }

    public List<Notificacion> getNotificacionList() {
        return NotificacionList;
    }
    
    public Notificacion addNotificacion(Notificacion notificacion) {
        getNotificacionList().add(notificacion);
        notificacion.setUsuario(this);
        return notificacion;
    }

    public Notificacion removeNotificacion(Notificacion notificacion) {
        getNotificacionList().remove(notificacion);
        notificacion.setUsuario(this);
        return notificacion;
    }
}
