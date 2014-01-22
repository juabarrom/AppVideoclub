package master.proyecto.juandiego.modelo.sessionbeans.interfazlocal;

import java.util.List;

import javax.ejb.Local;

import master.proyecto.juandiego.modelo.entitybeans.Direccion;
import master.proyecto.juandiego.modelo.entitybeans.Usuario;

@Local
public interface ClienteDaoLocal {
    
    
    
        /*
        * METODOS RELACIONADOS CON LAS DIRECCIONES
        */ 
    
    
        List<Direccion> getDireccionFindAll();
        
        
        //MODIFICA LA DIRECCION DE UN USUARIO CON RESPECTO A UN USUARIO DETERMINADO
        //DESVUELVE 0 SI NO EXISTE DICHO USUARIO
        //DEVUELVE 1 SI EXISTE DICHO USUARIO Y MODIFICA LA DIRECCION
        int modificarDatosDireccion(Long idUsuario,String ciudad, Integer codPostal, String direccion, String provincia); 


        //MUESTRA AL USUARIO REPRESENTADO POR EL IDUSUARIO
        Direccion mostrarDireccionUsuario(Long idUsuario);

        /*
         * METODOS RELACIONADOS CON LOS USUARIOS
         */ 
        
        List<Usuario> getUsuarioFindAll();

        
        //PERSISTE LOS DATOS DE USUARIO EN LA BD JUNTO CON LA DIRECCION DE ÉSTE
        String persistUsuario
        //DATOS USUARIO
        (String nombre,String apellido, String contrasenya, String correo, Integer movil, Integer telefono,
         //DATOS DIRECCION
         String ciudad, Integer codPostal, String direccion, String provincia);
        
        
        
        //CAMBIO <ESTADO> QUE ES UN ENUMERADO DEL TIPO ENUMESTADOUSUARIO
        //DESVUELVE 0 SI NO EXISTE DICHO USUARIO
        //DEVUELVE 1 SI EXISTE DICHO USUARIO Y LO DA DE BAJA
        //ESTADO.INACTIVO
        int inactivarCuenta(Long idUsuario);
        
        
        
        //CAMBIO <ESTADO> ES UN ENUMERADO DEL TIPO ENUMESTADOUSUARIO
        //DESVUELVE 0 SI NO EXISTE DICHO USUARIO
        //DEVUELVE 1 SI EXISTE DICHO USUARIO Y LO DA DE BAJA
        //ESTADO.ACTIVO
        int activarCuenta(Long idUsuario);
        
        
        //COMPROBAR SI EL USUARIO ESTA INSCRITO EN LA APLICACION
        //DEPENDE TAMBIEN DEL ESTADO DEL USUARIO DENTRO DE LA APLICACION
        /*
         * RES=0 EL USARIO NO EXISTE EN LA APP
         * RES=1 EL USUARIO EXISTE PERO NO COINCIDE CON LA CONTRASENYA
         * RES=2 EL USUARIO EXISTE Y LA CONTRASENYA COINCIDE
         * RES=3 EL USUARIO EXISTE Y LA CONTRASENYA CONINCIDE ADEMAS ESTA ACTIVO
         * RES=4 EL USUARIO EXISTE Y LA CONTRASENYA CONINCIDE ADEMAS ESTA ACTIVO Y ES EL ADMINISTRADOR
         */
        int Login(String correo,String contrasenya);
        
        
        //SABER EL ID A TRAVES DEL CORREO DEL USUARIO
        //LOS CORREOS SON UNICOS EN LA APP
        Long idPorCorreo(String correo);
        
        
        //CONVIERTE A UN USUARIO EN ADMINISTRADOR
        //DESVUELVE 0 SI NO EXISTE DICHO USUARIO
        //DEVUELVE 1 SI EXISTE DICHO USUARIO Y LO CONVIERTE EN ADMIN
        //ESADMIN.SI
        int convertirEnAdmin(Long idUsuario);
          
          
        //MODIFICA LOS DATOS DEL USUARIO
        //DESVUELVE 0 SI NO EXISTE DICHO USUARIO
        //DEVUELVE 1 SI EXISTE DICHO USUARIO Y MODIFICA LOS DATOS
        int modificarDatosUsuario(Long idUsuario,String nombre,String apellido, String contrasenya, 
                                  String correo,Integer movil, Integer telefono);
        
        //MODIFICA LA CONTRASEÑA DEL USUARIO
        //DESVUELVE 0 SI NO EXISTE DICHO USUARIO
        //DEVUELVE 1 SI EXISTE DICHO USUARIO Y MODIFICA LOS DATOS
        int modificarContrasenyaUsuario(Long idUsuario, String contrasenya);
        
        //MODIFICA LOS TELEFONOS DE CONTACTOS DEL USUARIO
        //DESVUELVE 0 SI NO EXISTE DICHO USUARIO
        //DEVUELVE 1 SI EXISTE DICHO USUARIO Y MODIFICA LOS DATOS
        int modificarTelefonoUsuario(Long idUsuario, Integer movil, Integer telefono);
        
        //MODIFICA LOS PUNTOS DEL USUARIO
        //DESVUELVE 0 SI NO EXISTE DICHO USUARIO
        //DEVUELVE 1 SI EXISTE DICHO USUARIO Y MODIFICA LOS PUNTOS DEL USUARIO
        int anyadirPuntos(Long idUsuario,Integer puntos);
        
        
        //MUESTRA AL USUARIO REPRESENTADO POR EL IDUSUARIO
        Usuario mostrarDatosUsuario(Long idUsuario);
        
        List<Usuario> mostrarActivos();
        
        List<Usuario> mostrarAdministradores();
        
    
}
