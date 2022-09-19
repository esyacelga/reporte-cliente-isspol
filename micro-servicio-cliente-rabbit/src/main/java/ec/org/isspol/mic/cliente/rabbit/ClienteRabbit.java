package ec.org.isspol.mic.cliente.rabbit;

import ec.org.isspol.log.IsspolLogger;
import ec.org.isspol.mic.cliente.rabbit.exception.RabbitException;
import ec.org.isspol.mic.reporte.shared.IsspolReporteConstante;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.jackrabbit.JcrConstants;
import org.apache.jackrabbit.servlet.ServletRepository;

import javax.jcr.AccessDeniedException;
import javax.jcr.Binary;
import javax.jcr.GuestCredentials;
import javax.jcr.InvalidItemStateException;
import javax.jcr.ItemExistsException;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.ReferentialIntegrityException;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.version.VersionException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Calendar;


/**
 * Permite la conexion con el servidor de archivos
 * Genera y Retora un documento
 *
 * @author roberto.chasipanta
 */
public class ClienteRabbit {


    private static ClienteRabbit single_instance = null;

    public ClienteRabbit() {
    }

    public static ClienteRabbit getInstance()
    {
        if (single_instance == null)
            single_instance = new ClienteRabbit();

        return single_instance;
    }





    public void rabbitConnection(ServletContext servletContext, String rutaArchivo, byte[] bytes, String mimeType) {
        // Acquire ServletContext
        ServletContext jackrabbitCtx = servletContext.getContext("/" + IsspolReporteConstante.SRV_JACK_RABBIT_CONTEXT);

        // Create Repository through using of ServletRepository
        Repository repository = new ServletRepository((HttpServlet) jackrabbitCtx.getAttribute("repository.access.servlet"));

        // Print out reference variable
        IsspolLogger.getInstance().info(String.valueOf(repository));

        ClienteRabbit clienteRabbit = new ClienteRabbit();
        try {
            clienteRabbit.grabarArchivo(repository, rutaArchivo, bytes, mimeType);
        } catch (RabbitException e) {
            IsspolLogger.getInstance().error("Ocurrio un error al grabar el archivo en el directorio", e);
        }
    }
    /**
     * @return
     * @throws Exception
     */
    public byte[] buscarArchivo(Repository repository, String rutaArchivo) throws RabbitException {
        Session session = null;
        Binary binary = null;
        InputStream stream = null;

        try {
            session = repository.login(new GuestCredentials());
            binary = session.getNode("/" + rutaArchivo).getNode("jcr:content").getProperty("jcr:data").getBinary();
            stream = binary.getStream();
            return IOUtils.toByteArray(stream);
        } catch (Exception e) {
            IsspolLogger.getInstance().error("No se encontre el Archivo en jackrabbbit: " + rutaArchivo, e);
            throw new RabbitException(e);
        } finally {
            try {
                session.logout();
                if (stream != null) {
                    stream.close();
                }
            } catch (Exception e) {
                IsspolLogger.getInstance().error("Error al cerrar sesion", e);
            }
        }

    }

    /**
     * @param repository
     * @param ruta
     * @param bytes
     * @param mimeType
     * @throws RabbitException
     */
    public void grabarArchivo(Repository repository, String ruta, byte[] bytes, String mimeType) throws
            RabbitException {

        Session session = null;
        try {
            session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
        } catch (RepositoryException e) {
            throw new RabbitException("Ocurrio un error al logearse en el repositorio", e);
        }
        final InputStream inputStream = new ByteArrayInputStream(bytes);
        Binary binary = null;
        try {
            Node root = session.getRootNode();

            //Si ya existe eliminar
            if (session.nodeExists(root.getPath().concat(ruta))) {
                root.getNode(ruta).remove();
            }

            Node fileNode = root;

            String[] arregloRuta = StringUtils.split(ruta, "/");

            int contador = 0;
            for (String path : arregloRuta) {
                Boolean nodeExist = Boolean.TRUE;
                try {
                    fileNode = fileNode.getNode(path);
                } catch (Exception ex) {
                    nodeExist = Boolean.FALSE;
                }

                if (!nodeExist) {
                    if (contador < arregloRuta.length - 1) {
                        fileNode = fileNode.addNode(path, "nt:folder");
                    } else {
                        fileNode = fileNode.addNode(path, "nt:file");
                    }
                }

                contador++;
            }

            Node resNode = fileNode.addNode("jcr:content", "nt:resource");


            binary = session.getValueFactory().createBinary(inputStream);

            resNode.setProperty("jcr:data", binary);
            resNode.setProperty("jcr:mimeType", mimeType);
            resNode.addMixin(JcrConstants.MIX_VERSIONABLE);
            Calendar lastModified = Calendar.getInstance();
            resNode.setProperty("jcr:lastModified", lastModified);

            session.save();


        } catch (PathNotFoundException e) {
            IsspolLogger.getInstance().error("La ruta especificada no existe", e);
        } catch (ItemExistsException e) {
            IsspolLogger.getInstance().error("El Item ya existe", e);
        } catch (ReferentialIntegrityException e) {
            IsspolLogger.getInstance().error("ReferentialIntegrityException", e);
        } catch (AccessDeniedException e) {
            IsspolLogger.getInstance().error("AccessDeniedException", e);
        } catch (LockException e) {
            IsspolLogger.getInstance().error("LockException", e);
        } catch (ConstraintViolationException e) {
            IsspolLogger.getInstance().error("ConstraintViolationException", e);
        } catch (InvalidItemStateException e) {
            IsspolLogger.getInstance().error("InvalidItemStateException", e);
        } catch (NoSuchNodeTypeException e) {
            IsspolLogger.getInstance().error("NoSuchNodeTypeException", e);
        } catch (ValueFormatException e) {
            IsspolLogger.getInstance().error("ValueFormatException", e);
        } catch (VersionException e) {
            IsspolLogger.getInstance().error("VersionException", e);
        } catch (RepositoryException e) {
            IsspolLogger.getInstance().error("AccessDeniedException", e);
        } finally {
            try {
                session.logout();
                if (ObjectUtils.notEqual(inputStream, null)) {
                    inputStream.close();
                }
                if (ObjectUtils.notEqual(binary, null)) {
                    binary.dispose();
                }
            } catch (Exception e) {
                IsspolLogger.getInstance().error("Error", e);
            }
        }
    }

    /**
     * @param repository
     * @param ruta
     * @throws RabbitException
     */
    public void eliminarArchivo(Repository repository, String ruta) throws
            RabbitException {

        Session session = null;
        try {
            session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
        } catch (RepositoryException e) {
            throw new RabbitException("Ocurrio un error al logearse en el repositorio", e);
        }
        try {
            Node root = session.getRootNode();

            //Si ya existe eliminar
            if (session.nodeExists(root.getPath().concat(ruta))) {
                root.getNode(ruta).remove();
            }

            session.save();


        } catch (Exception e) {
            IsspolLogger.getInstance().error("AccessDeniedException", e);
        } finally {
            try {
                session.logout();
            } catch (Exception e) {
                IsspolLogger.getInstance().error("Error", e);
            }
        }
    }
}
