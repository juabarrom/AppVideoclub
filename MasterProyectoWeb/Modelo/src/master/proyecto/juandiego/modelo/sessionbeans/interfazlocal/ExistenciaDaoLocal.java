package master.proyecto.juandiego.modelo.sessionbeans.interfazlocal;

import java.util.List;

import javax.ejb.Local;

import master.proyecto.juandiego.modelo.entitybeans.Existencia;
import master.proyecto.juandiego.modelo.entitybeans.Juego;
import master.proyecto.juandiego.modelo.entitybeans.Puntosexistencias;
import master.proyecto.juandiego.modelo.entitybeans.Udsexistencia;
import master.proyecto.juandiego.modelo.entitybeans.Video;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumBooleano;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumGeneroJuego;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumPlataformaJuego;
import master.proyecto.juandiego.modelo.entitybeans.enumerados.EnumTipoExistencia;

@Local
//OJO QUERY CON ENUM EN UN PAR DE METODOS
public interface ExistenciaDaoLocal {

    List<Existencia> getExistenciaFindAll();

    List<Video> getVideoFindAll();

    List<Juego> getJuegoFindAll();
 
    //A„ADE UN JUEGO A LA BASE DE DATOS   
    int persistJuego(
                    //ATRIBUTOS DEL JUEGOS
                    String nombre,EnumGeneroJuego genero,EnumPlataformaJuego plataforma,String descripcion, 
                    String gameplay,String imagenGrande,String imagenPequenya,
                    EnumBooleano tieneDescuento, Integer descuento,
                    EnumBooleano esNovedad,EnumBooleano esProximamente,
                     
                    //ATRIBUTO DE LOS PUNTOS QUE TIENE ESE JUEGO
                     Integer puntosJuego,
                    
                    //ATRIBUTO DE LAS UNIDADES QUE TIENE ESE JUEGO
                    Integer udsJuego
                     );
    
    
    //int persistVideo();
    
    //CAMBIAS LOS PUNTOS(PRECIO) EN UNA EXISTENCIA
    //RES->0 NO EXISTE LA EXISTENCIA
    //RES->1 EXISTE LA EXISTENCIA
    int cambiarPuntosEnExistencia(Long idExistencia,Integer puntosBlueRay,Integer puntosDvd,Integer puntosJuego);
    
    //PONES UNA CANTIDAD DE UNIDADES EN UNA EXISTENCIA
    //RES->0 NO EXISTE LA EXISTENCIA
    //RES->1 EXISTE LA EXISTENCIA
    int anyadirUnidadesEnExistencia(Long idExistencia,Integer unidadesBlueRay,Integer unidadesDvd,Integer unidadesJuego);
    
    //PONES UNA CANTIDAD DE DESCUENTO EN UNA EXISTENCIA
    //RES->0 NO EXISTE LA EXISTENCIA
    //RES->1 EXISTE LA EXISTENCIA
    int ponerDescuentoEnExistencia(Long idExistencia,Integer descuento);
    
    //QUITAS SI LA TIENE UNA CANTIDAD DE DESCUENTO EN UNA EXISTENCIA
    //RES->0 NO EXISTE LA EXISTENCIA
    //RES->1 EXISTE LA EXISTENCIA
    int quitarDescuentoEnExistencia(Long idExistencia,Integer descuento);
    
    //MUESTRA TODAS LAS EXISTENCIAS QUE TENGAN DESCUENTO
    List<Juego> mostrarTodosLosProductosConDescuento();
    
    //MUESTRA TODAS LAS EXISTENCIAS QUE SON NOVEDADES
    //OJO QUERY CON ENUM
    List<Juego> mostrarNovedades();
    
    //MUESTRA TODAS LAS EXISTENCIAS QUE SON PROXIMAMENTE
    //OJO QUERY CON ENUM
    List<Juego> mostrarProximamente();
    
    //QUITAS DE NOVEDADES LA EXISTENCIA
    //RES->0 NO EXISTE LA EXISTENCIA
    //RES->1 EXISTE LA EXISTENCIA    
    int quitarExistenciaDeNovedad(Long idExistencia);
    
    //QUITAS DE PROXIMAMENTE LA EXISTENCIA Y LA COLOCAS EN NOVEDADES
    //RES->0 NO EXISTE LA EXISTENCIA
    //RES->1 EXISTE LA EXISTENCIA
    int quitarExistenciaDeProximamente(Long idExistencia);

    //DESCATALOGAS LA EXISTENCIA
    //RES->0 NO EXISTE LA EXISTENCIA
    //RES->1 EXISTE LA EXISTENCIA        
    int descatalogarProducto(Long idExistencia);
        
    int modificarJuegog(Long idJuego,
                    //ATRIBUTOS DEL JUEGOS
                    String nombre,EnumGeneroJuego genero,EnumPlataformaJuego plataforma,String descripcion, 
                    String gameplay,String imagenGrande,String imagenPequenya,
                    EnumBooleano tieneDescuento, Integer descuento,
                    EnumBooleano esNovedad,EnumBooleano esProximamente,
                     
                    //ATRIBUTO DE LOS PUNTOS QUE TIENE ESE JUEGO
                     Integer puntosJuego,
                    
                    //ATRIBUTO DE LAS UNIDADES QUE TIENE ESE JUEGO
                    Integer udsJuego
                     );
 
    Juego mostrarJuegoPorId(Long id);
    
}
