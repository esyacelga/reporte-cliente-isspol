package ec.org.isspol.mic.reporte.alternativo.impl;

import ec.org.isspol.mic.reporte.alternativo.ReporteRepositoryCustom;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.Reporte;

import java.util.List;

public class ReporteRepositoryCustomImpl implements ReporteRepositoryCustom {
    @Override
    public List<Reporte> filterBy(String idReporte) {
        return null;
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
