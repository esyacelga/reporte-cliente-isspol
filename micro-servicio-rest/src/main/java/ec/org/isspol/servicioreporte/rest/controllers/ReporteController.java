package ec.org.isspol.servicioreporte.rest.controllers;

import ec.org.isspol.common.IsspolProcessException;
import ec.org.isspol.common.IsspolSearchException;
import ec.org.isspol.log.IsspolLogger;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.Reporte;
import ec.org.isspol.mic.reporte.persistence.service.ReporteRepository;
import ec.org.isspol.mic.reporte.shared.IsspolReporteConstante;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@RestController
public class ReporteController {

    @Autowired
    private ServletContext context;
    @Autowired
    private ReporteRepository iReporte;


    @RequestMapping("/")
    public String home(Locale locale, Model model) throws Exception {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);
        return "home.....";
    }


    @GetMapping("/buscar/{id}")
    public Reporte buscarReporte(@PathVariable Integer id) throws Exception {
        Optional<Reporte> reporte = iReporte.findById(id);
        return reporte.get();
    }

    /*@Autowired
    private IReporteJpa iReporteJpa;
*/
/*
    @RequestMapping("/")
    public String home(Locale locale, Model model) throws Exception {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);
        return "home.....";
    }
*/

    @RequestMapping(value = "/generarString", method = {RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    public String generarReporteString(@RequestBody Map<String, Object> reporteParametro) throws IsspolProcessException, IsspolSearchException {
        byte[] reporteResult = null;
        boolean grabarReporte = MapUtils.getBoolean(reporteParametro, "grabarReporte", Boolean.FALSE);
        boolean esExcel = MapUtils.getBoolean(reporteParametro, "esExcel", Boolean.FALSE);
        String nombreReporte = MapUtils.getString(reporteParametro, "nombreReporte");
        String rutaReporte = MapUtils.getString(reporteParametro, "rutaReporte");
        Boolean esDinamico = MapUtils.getBoolean(reporteParametro, "esDinamico", Boolean.FALSE);
        String mimeType = MapUtils.getString(reporteParametro, "mimeType", IsspolReporteConstante.MIME_DEFECTO_PDF);
        Map<String, Object> parametroReporte = (Map<String, Object>) MapUtils.getObject(reporteParametro, "parametroReporte");
        try {
            reporteResult = iReporte.generaReporteRepository(nombreReporte, parametroReporte, grabarReporte, mimeType, esDinamico, esExcel);
            //Generacion reporte en el file system
            //Solo si tiene RUTA
        /*    if (grabarReporte && StringUtils.isNotEmpty(rutaReporte)) {
                rabbitConnection(rutaReporte, reporteResult, mimeType);
            }*/
            return Base64Utils.encodeToString(reporteResult);
        } catch (IsspolProcessException e) {
            IsspolLogger.getInstance().error("Error generando el reporte como string " + parametroReporte.toString(), e);
            throw e;
        }

    }
/*
    @RequestMapping(value = "/generarExpediente", method = {RequestMethod.POST})
    @ResponseBody
    public String generarExpedienteByte(@RequestBody Map<String, Object> reporteParametro) throws IsspolProcessException{
        byte[] expedienteResult = null;

        try {
            expedienteResult = iReporte.buscarExpediente(reporteParametro);
        } catch (IsspolSearchException e) {
            IsspolLogger.getInstance().error("Error encontrando el expediente", e);
        }

        return Base64Utils.encodeToString(expedienteResult);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/obtenerArchivoRepositorio", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<InputStreamResource> obtenerArchivoRepositorio(@RequestParam(value = "rutaArchivo") String rutaArchivo,
                                                                         @RequestParam(value = "tipoArchivo", required = false) String tipoArchivo) throws IsspolProcessException {

        InputStream inputStream = null;
        byte[] bytes = null;
        String extArchivo = null;
        String nombreArchivo = null;
        String mimeType = tipoArchivo;
        HttpHeaders headers = new HttpHeaders();

        // Acquire ServletContext
        ServletContext jackrabbitCtx = context.getContext("/" + IsspolReporteConstante.SRV_JACK_RABBIT_CONTEXT);

        // Create Repository through using of ServletRepository
        Repository repository = new ServletRepository((HttpServlet) jackrabbitCtx.getAttribute("repository.access.servlet"));

        try {
            bytes = ClienteRabbit.getInstance().buscarArchivo(repository, rutaArchivo);
            inputStream = new ByteArrayInputStream(bytes);
            if (StringUtils.isBlank(mimeType)) {
                mimeType = MimeTypeUtil.getContextUtil().getMimeType(FilenameUtils.getExtension(rutaArchivo));
                if (StringUtils.isBlank(mimeType)) {
                    mimeType = (String) ObjectUtils.defaultIfNull(URLConnection.guessContentTypeFromStream(inputStream), IsspolReporteConstante.MIME_DEFECTO_PDF);
                }
            }

            MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
            extArchivo = allTypes.forName(mimeType).getExtension();
            nombreArchivo = FilenameUtils.getBaseName(rutaArchivo);

            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.setContentDispositionFormData(nombreArchivo + extArchivo, nombreArchivo + extArchivo);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(
                            MediaType.parseMediaType(mimeType))
                    .body(new InputStreamResource(inputStream));


        } catch (Exception e) {
            IsspolLogger.getInstance().error("Ocurrio un error buscando el archivo", e);
            throw new IsspolProcessException(e);
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                IsspolLogger.getInstance().error("Error cerrar InputStream", e);
            }
        }

    }

*/

}
