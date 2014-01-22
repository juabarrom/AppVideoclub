package master.proyecto.juandiego.modelo.sessionbeans.stateless;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import master.proyecto.juandiego.modelo.entitybeans.Direccion;
import master.proyecto.juandiego.modelo.entitybeans.Juego;
import master.proyecto.juandiego.modelo.entitybeans.Usuario;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumBooleano;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumEstadoUsuario;
import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.ClienteDaoLocal;

@Stateless(name = "ClienteDao", mappedName = "MasterProyectoWeb-Modelo-ClienteDao")
public class ClienteDaoBean implements ClienteDaoLocal {
    @Resource
    SessionContext sessionContext;
    
    @PersistenceContext(unitName = "Modelo")
    private EntityManager emUsuario;

    public ClienteDaoBean() {
    }

    /** <code>select o from Direccion o</code> */
    public List<Direccion> getDireccionFindAll() {
        return emUsuario.createNamedQuery("Direccion.findAll").getResultList();
    }

    /** <code>select o from Usuario o</code> */
    public List<Usuario> getUsuarioFindAll() {
        return emUsuario.createNamedQuery("Usuario.findAll").getResultList();
    }
    
    @Override
       public String persistUsuario(String nombre,String apellido, String contrasenya, 
        String correo,Integer movil, Integer telefono,
        String ciudad, Integer codPostal, String direccion, String provincia) {
           
           
           String res= new String("0");    
           Usuario usuario = new Usuario(nombre,apellido,contrasenya,correo,movil,telefono);
           emUsuario.persist(usuario);
           
           Direccion dir = new Direccion(ciudad,codPostal,direccion,provincia);
           dir.setUsuario(usuario);
           emUsuario.persist(dir);
           
           return res;
       }

    @Override
    public int inactivarCuenta(Long idUsuario) {
        int res = 0;
        Usuario u;
        u = emUsuario.find(Usuario.class,idUsuario); 
        if(u!=null){
            u.setEstado(EnumEstadoUsuario.INACTIVO);
            emUsuario.persist(u);
            res=1;
        }
        return res;
    }


    @Override
    public int activarCuenta(Long idUsuario) {
        int res = 0;
        Usuario u;
        u = emUsuario.find(Usuario.class,idUsuario); 
        if(u!=null){
            u.setEstado(EnumEstadoUsuario.ACTIVO);
            emUsuario.persist(u);
            res=1;
        }
        return res;
    }
    
    @Override
    public int convertirEnAdmin(Long idUsuario) {
        int res = 0;
        Usuario u;
        u = emUsuario.find(Usuario.class,idUsuario); 
        if(u!=null){
            u.setEsAdmin(EnumBooleano.SI);
            emUsuario.persist(u);
            res=1;
        }
        return res;
    }

    @Override
    public int modificarDatosDireccion(Long idUsuario, String ciudad, Integer codPostal, String direccion,
                                       String provincia) {
        
        int res = 0;
        Usuario u;
    
        u = emUsuario.find(Usuario.class,idUsuario); 
        if(u!=null){
            Direccion d = u.getDireccion();
            d.setCiudad(ciudad);
            d.setCodPostal(codPostal);
            d.setDireccion(direccion);
            d.setProvincia(provincia);
            emUsuario.persist(u);
            res=1;
        }
        return res;
    }

    @Override
    public int modificarDatosUsuario(Long idUsuario, String nombre, String apellido, String contrasenya,
                                     String correo,Integer movil, Integer telefono) {
        int res = 0;
        Usuario u;
        u = emUsuario.find(Usuario.class,idUsuario); 
        if(u != null){
            u.setNombre(nombre);
            u.setApellido(apellido);
            u.setContrasenya(contrasenya);
            u.setCorreo(correo);
            u.setMovil(movil);
            u.setTelefono(telefono);
            
            emUsuario.persist(u);
            res=1;
        }
        return res;
    }

    @Override
    public int anyadirPuntos(Long idUsuario, Integer puntos) {
        int res = 0;
        Usuario u;
        u = emUsuario.find(Usuario.class,idUsuario); 
        if(u != null){
            u.setPuntos(u.getPuntos()+puntos);
            emUsuario.persist(u);
            res=1;
        }
        return res;
    }


    @Override
    public Usuario mostrarDatosUsuario(Long idUsuario) {
        Usuario u;
        u = emUsuario.find(Usuario.class,idUsuario); 
        return u;
    }

    @Override
    public Direccion mostrarDireccionUsuario(Long idUsuario) {
        Usuario u;
        u = emUsuario.find(Usuario.class,idUsuario); 
        Direccion d =null;
        if(u!=null){
            d=u.getDireccion();
        }
    
        return d;
    }

    @Override
    public int Login(String correo, String contrasenya) {
        int res=0;
        Query query = emUsuario.createQuery("select o from Usuario o where o.correo='"+correo+"'");
        try{
            Usuario usuario = (Usuario)query.getSingleResult();
            if(usuario != null){
                res=1;
                if(usuario.getContrasenya().equals(contrasenya)){
                    res=2;
                    if(usuario.getEstado().toString().equals("ACTIVO")){
                        res=3;
                        if(usuario.getEsAdmin().toString().equals("SI")){
                            res=4;
                        }    
                    }
                }
            }    
        }catch(NoResultException e){
            System.out.println("Metodo login, no hay resultado: "+e);
        }catch(NonUniqueResultException e){
            System.out.println("Metodo login,hay mas de un resultado: "+e);
        }
        
        return res;
    }


    @Override
    public Long idPorCorreo(String correo) {
        Query query = emUsuario.createQuery("select o from Usuario o where o.correo='"+correo+"'");
        Long res = new Long(-1);
        try{
            Usuario usuario = (Usuario)query.getSingleResult();
            if(usuario != null){
               res=usuario.getIdUsuario();
            }    
        }catch(NoResultException e){
            System.out.println("Metodo idPorCorreo, no hay resultado: "+e);
        }catch(NonUniqueResultException e){
            System.out.println("Metodo idPorCorreo,hay mas de un resultado: "+e);
        }
        return res;
    }

    @Override
    public int modificarContrasenyaUsuario(Long idUsuario, String contrasenya) {
        int res = 0;
        Usuario u;
        u = emUsuario.find(Usuario.class,idUsuario); 
        if(u != null){
            u.setContrasenya(contrasenya);
            emUsuario.persist(u);
            res=1;
        }
        return res;
    }

    @Override
    public int modificarTelefonoUsuario(Long idUsuario, Integer movil, Integer telefono) {
        int res = 0;
        Usuario u;
        u = emUsuario.find(Usuario.class,idUsuario); 
        if(u != null){
            u.setMovil(movil);
            u.setTelefono(telefono);
            
            emUsuario.persist(u);
            res=1;
        }
        
        return res;
    }


    @Override
    public List<Usuario> mostrarActivos() {
        List<Usuario> usuario = new ArrayList<Usuario>();
        Query query = emUsuario.createQuery("select o from Usuario o where o.esAdmin=?1 and o.estado=?2");
        EnumBooleano NO = EnumBooleano.NO;
        EnumEstadoUsuario estadoUsuario = EnumEstadoUsuario.ACTIVO;
        query.setParameter(1, NO);
        query.setParameter(2, estadoUsuario);
        
        List<Usuario> novedades = (List<Usuario>)query.getResultList();
        if(!novedades.isEmpty()){
            usuario.addAll(novedades);
        }
        
        return usuario;
    }

    @Override
    public List<Usuario> mostrarAdministradores() {
        List<Usuario> usuario = new ArrayList<Usuario>();
        Query query = emUsuario.createQuery("select o from Usuario o where o.esAdmin=?1");
        EnumBooleano SI = EnumBooleano.SI;
        query.setParameter(1, SI);
        
        List<Usuario> novedades = (List<Usuario>)query.getResultList();
        if(!novedades.isEmpty()){
            usuario.addAll(novedades);
        }
        
        return usuario;
    }
}
