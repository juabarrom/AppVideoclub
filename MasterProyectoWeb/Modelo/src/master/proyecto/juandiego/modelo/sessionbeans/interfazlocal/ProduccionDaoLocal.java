package master.proyecto.juandiego.modelo.sessionbeans.interfazlocal;

import java.util.List;

import javax.ejb.Local;

import master.proyecto.juandiego.modelo.entitybeans.Adg;
import master.proyecto.juandiego.modelo.entitybeans.Produccion;

@Local
public interface ProduccionDaoLocal {
    Adg persistAdg(Adg adg);

    List<Adg> getAdgFindAll();

    Produccion persistProduccion(Produccion produccion);

    List<Produccion> getProduccionFindAll();
}
