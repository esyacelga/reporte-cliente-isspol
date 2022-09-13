package ec.org.isspol.mic.reporte.persistence.service.impl;

import ec.org.isspol.log.IsspolLogger;
import ec.org.isspol.mic.reporte.persistence.entities.expediente.Expediente;
import ec.org.isspol.mic.reporte.persistence.service.IExpediente;
import ec.org.isspol.mic.reporte.shared.exception.ReportException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class ExpedienteImpl implements IExpediente {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Expediente findExpediente(Integer identificador, String cedula) throws ReportException {
        EntityManager em = entityManager;
        em.clear();
        Query query = em.createNamedQuery("Expediente.findByIdentificador").setParameter("idExpediente", identificador).setParameter("cedula", cedula);

        try {
            Expediente expediente = (Expediente) query.getSingleResult();
            em.getEntityManagerFactory().getCache().evict(Expediente.class, expediente.getIdExpediente());
            return expediente;
        } catch (Exception ex) {
            IsspolLogger.getInstance().error("Error al recuperar ", ex);
            throw new ReportException("No se encontro el expediente con el identificador: " + identificador, ex);
        } finally {
            em.close();
        }
    }
}
