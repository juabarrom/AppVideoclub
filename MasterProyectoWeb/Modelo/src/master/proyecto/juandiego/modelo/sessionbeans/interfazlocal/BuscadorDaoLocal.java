package master.proyecto.juandiego.modelo.sessionbeans.interfazlocal;

import java.util.List;

import javax.ejb.Local;

import master.proyecto.juandiego.modelo.entitybeans.Juego;

@Local
public interface BuscadorDaoLocal {
    
    /*
     * METODOS USADOS EN LA CABECERA DE LA PAGINA
     */
    
    //BUSQUEDA TODOS LOS JUEGOS CON DESCUENTO
    List<Juego> conDescuento();
    
    //BUSQUEDA JUEGOS POR GENERO
    //SI LA ENTRADA ESTA VACIA BUSCA TODOS LOS JUEGOS POR GENERO
    List<Juego> porGenero(String genero);
    
    //BUSQUEDA JUEGOS POR PLATAFORMA
    //SI LA ENTRADA ESTA VACIA BUSCA TODOS LOS JUEGOS POR PLATAFORMA
    List<Juego> porPlataforma(String plataforma);
    
    //BUSQUEDA JUEGOS POR NOMBRE
    //SI LA ENTRADA ESTA VACIA BUSCA TODOS LOS JUEGOS
    List<Juego> porNombre(String nombre);
    
    
    /*
     * METODOS USADOS EN EL PIE DE PAGINA
     */

    
    //MUESTRA 5 ARTICULOS QUE SON NOVEDAD
    List<Juego> esNovedad();
    
    //MUESTRA 5 ARTICULOS QUE SON PROXIMAMENTE
    List<Juego> esProximamente();
    
    //MUESTRA 5 ARTICULOS QUE SON PROXIMAMENTE
    List<Juego> topVentas();
    
    
    
}
