package master.proyecto.juandiego.modelo.sessionbeans.stateless;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import master.proyecto.juandiego.modelo.entitybeans.Adg;
import master.proyecto.juandiego.modelo.entitybeans.Produccion;
import master.proyecto.juandiego.modelo.sessionbeans.interfazlocal.ProduccionDaoLocal;

@Stateless(name = "ProduccionDao", mappedName = "MasterProyectoWeb-Modelo-ProduccionDao")
public class ProduccionDaoBean implements ProduccionDaoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "Modelo")
    private EntityManager em;

    public ProduccionDaoBean() {
    }

    public Adg persistAdg(Adg adg) {
        em.persist(adg);
        return adg;
    }

    /** <code>select o from Adg o</code> */
    public List<Adg> getAdgFindAll() {
        return em.createNamedQuery("Adg.findAll").getResultList();
    }

    public Produccion persistProduccion(Produccion produccion) {
        em.persist(produccion);
        return produccion;
    }

    /** <code>select o from Produccion o</code> */
    public List<Produccion> getProduccionFindAll() {
        return em.createNamedQuery("Produccion.findAll").getResultList();
    }
}
