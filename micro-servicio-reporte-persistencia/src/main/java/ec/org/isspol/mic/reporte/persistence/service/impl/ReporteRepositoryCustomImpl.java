package ec.org.isspol.mic.reporte.persistence.service.impl;

import ec.org.isspol.log.IsspolLogger;
import ec.org.isspol.mic.reporte.persistence.service.ReporteRepositoryCustom;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.Reporte;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class ReporteRepositoryCustomImpl implements ReporteRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Reporte findReporte(String nameReport) throws Exception {

        entityManager.clear();
        Query query = entityManager.createNamedQuery("Reporte.findByIdentificador").setParameter("identificador", nameReport);

        try {
            Reporte reporte = (Reporte) query.getSingleResult();
            entityManager.getEntityManagerFactory().getCache().evict(Reporte.class, reporte.getIdReporte());
            return reporte;
        } catch (Exception ex) {
            IsspolLogger.getInstance().error("Error al recuperar ", ex);
            throw new Exception("No se encontro el reporte con el identificador: " + nameReport, ex);
        } finally {
            entityManager.close();
        }
    }


/*    @Override
    public void findReporte(String nameReport) throws Exception {
        System.out.println("110");
        System.out.println("110");
        System.out.println("110");
        System.out.println("110");
    }*/
/*    @Override
    public void findReporte(String nameReport) throws Exception {
        System.out.println("110");
        System.out.println("110");
        System.out.println("110");
        System.out.println("110");


    }*/
  /*  @Override
    public findReporte(String nameReport) throws Exception {
        System.out.println("110");
*/

/*    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Reporte findReporte(String nameReport) throws Exception {
  *//*      entityManager.clear();
        Query query = entityManager.createNamedQuery("Reporte.findByIdentificador").setParameter("identificador", nameReport);

        try {
            Reporte reporte = (Reporte) query.getSingleResult();
            entityManager.getEntityManagerFactory().getCache().evict(Reporte.class, reporte.getIdReporte());
            return reporte;
        } catch (Exception ex) {
            IsspolLogger.getInstance().error("Error al recuperar ", ex);
            throw new Exception("No se encontro el reporte con el identificador: " + nameReport, ex);
        } finally {
            entityManager.close();
        }*//*
        return null;
    }*/
}
