package ec.org.isspol.mic.reporte.shared;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import ec.org.isspol.common.IsspolProcessException;
import ec.org.isspol.log.IsspolLogger;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.Reporte;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.ReporteSubreporte;
import ec.org.isspol.mic.reporte.pojo.ext.DatoCabecera;
import ec.org.isspol.mic.reporte.pojo.ext.DatoPie;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class UtilReporteIsspol {

    public static final UtilReporteIsspol UTIL = new UtilReporteIsspol();

    private static String WATERMARK_TEXT_NO_PRINT = "CONFIDENCIAL\n$IDUSUARIO\nNO AUTORIZADO PARA IMPRESION.";
    private static String WATERMARK_TEXT_CHECK_REPORTE = "CONFIDENCIAL\nORIGINAL.";
    private static String COMPRESSION_TYPE_LZW = "LZW";
    private static Integer FONT_SIZE = 30;

    private UtilReporteIsspol() {

    }

    public static UtilReporteIsspol getInstance() {
        return UTIL;
    }


    /**
     * Marca de AGUA para archivo PDF
     *
     * @param inputFile
     * @return
     */
    public byte[] waterMarkPdf(byte[] inputFile) {
        byte[] outputFile = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            PdfReader reader = new PdfReader(inputFile);
            PdfStamper stamper = new PdfStamper(reader, baos);
            BaseFont base = BaseFont.createFont(BaseFont.TIMES_ROMAN, "",
                    BaseFont.NOT_EMBEDDED);
            int total = reader.getNumberOfPages() + 1;
            PdfContentByte under;
            for (int i = 1; i < total; i++) {
                under = stamper.getUnderContent(i);

                float centerX = reader.getPageSizeWithRotation(1).getWidth() / 3;
                float centerY = reader.getPageSizeWithRotation(1).getHeight() / 2;

                under.setColorFill(BaseColor.LIGHT_GRAY);
                under.setFontAndSize(base, FONT_SIZE);
                under.beginText();

                for (String s : WATERMARK_TEXT_CHECK_REPORTE.split("\n")) {
                    under.showTextAligned(Element.ALIGN_JUSTIFIED, s, centerX, centerY, 0);
                    centerY = centerY + FONT_SIZE;
                }

                under.endText();
            }
            stamper.close();
            outputFile = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputFile;
    }

    /**
     * Marca de AGUA para archivo TIFF
     *
     * @param inputFile
     * @param idUsuario
     * @return
     */
    public byte[] waterMarkTiff(byte[] inputFile, String idUsuario) {
        byte[] outputFile = null;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
            ImageWriter writer = ImageIO.getImageWritersByFormatName("tiff").next();
            writer.setOutput(ios);

            ImageWriteParam param = writer.getDefaultWriteParam();
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionType(COMPRESSION_TYPE_LZW);

            //Prepare the Image Writer
            writer.prepareWriteSequence(null);

            ImageInputStream isb = ImageIO.createImageInputStream(new ByteArrayInputStream(inputFile));
            Iterator<ImageReader> iterator = ImageIO.getImageReaders(isb);
            if (iterator == null || !iterator.hasNext()) {
                throw new IOException("Image file format not supported by ImageIO: ");
            }

            ImageReader reader = (ImageReader) iterator.next();
            iterator = null;
            reader.setInput(isb);

            String line = WATERMARK_TEXT_NO_PRINT.replace("$IDUSUARIO", StringUtils.defaultIfBlank(idUsuario, "GUEST")).toUpperCase(Locale.ROOT);

            int nbPages = reader.getNumImages(true);
            for (int p = 0; p < nbPages; p++) {
                BufferedImage sourceImage = reader.read(p);

                Graphics2D graphics = sourceImage.createGraphics();

                AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
                graphics.setComposite(alphaChannel);
                graphics.setColor(Color.lightGray);
                graphics.setFont(new Font("Times New Roman", Font.BOLD, FONT_SIZE));

                int centerX = sourceImage.getWidth() / 4;
                int centerY = sourceImage.getHeight() / 2;

                for (String s : line.split("\n")) {
                    graphics.drawString(s, centerX, centerY);
                    centerY = centerY + FONT_SIZE;
                }

                graphics.dispose();

                writer.writeToSequence(new IIOImage(sourceImage, null, null), param);
                sourceImage.flush();
            }
            ios.flush();
            ios.close();
            writer.dispose();
            outputFile = baos.toByteArray();
        } catch (Exception e) {
            IsspolLogger.getInstance().error("Error MARCA-AGUA ", e);
        }
        return outputFile;
    }

    public String generarNumeroTemplateParametrosProceimiento(Map<String, Object> parametros) {
        int tamanioParametro = parametros != null ? parametros.size() : 0;
        String numeroParametros = "";
        if (tamanioParametro != 0) {
            for (int indice = 0; indice < tamanioParametro; indice++) {
                numeroParametros += "?";
                if (indice + 1 < tamanioParametro) {
                    numeroParametros += ", ";
                }
            }
        }

        return numeroParametros;
    }

    public void agregarParametroProcedimiento(CallableStatement callableStatement, Map<String, Object> parametros, Integer tipoSalida)
            throws IsspolProcessException {
        if (tipoSalida != null) {
            try {
                callableStatement.registerOutParameter(1, tipoSalida);
            } catch (Exception e) {
                IsspolLogger.getInstance().error("Error al agregar el parametro OUT al procedimiento", e);
                throw new IsspolProcessException(e);
            }
        }
        for (Map.Entry<String, Object> entry : parametros.entrySet()) {
            try {
                callableStatement.setObject(entry.getKey(), entry.getValue());
            } catch (SQLException e) {
                IsspolLogger.getInstance().error("Ocurrio un error al agregar el parametro al procedimiento", e);
                throw new IsspolProcessException(e);
            }
        }
    }

    public Map<String, String> obtenerDirectorioNombreArchivo(String token) throws Exception {
        Map<String, String> resultado = new LinkedHashMap<String, String>();
        Claims claims;
        try {
            claims = Jwts.parser().parseClaimsJwt(token).getBody();
            resultado.put("directorio", claims.get("directorio").toString().replace("//", "/"));
            resultado.put("documento", claims.get("sub").toString().concat("-").concat(claims.get("secuencial").toString()));
            return resultado;
        } catch (Exception e) {
            IsspolLogger.getInstance().error("Error", e);
            throw new Exception(e);
        }
    }

    public byte[] generarReporte(Connection connection, List<DatoCabecera> resultadoCabecera, List<DatoPie> resultadoPie, String nombreReporte, Map<String, Object> parametros, boolean guardarEnBase, String mimeType, boolean esDinamico, boolean debugMode, boolean esExcel, Reporte reporte, Collection<ReporteSubreporte> lista) throws IsspolProcessException {

        if (guardarEnBase) {
            parametros.put("token_reporte", RandomStringUtils.randomAlphanumeric(6));
        }
        Map<String, Object> parametrosOriginal = (Map<String, Object>) ObjectUtils.clone(parametros);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream jasperReportInputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        JasperPrint jasperPrint = null;
        byte[] reporteBytes = null;

        try {

            if (!esExcel || ObjectUtils.equals(reporte.getPlantillaExcel(), null)) {
                if (ObjectUtils.notEqual(reporte.getPlantilla(), null)) {
                    byteArrayInputStream = new ByteArrayInputStream(reporte.getPlantilla());
                    jasperReportInputStream = byteArrayInputStream;
                } else {
                    throw new IsspolProcessException("No existe un plantilla valida para el reporte: " + nombreReporte);
                }
            } else {
                if (ObjectUtils.notEqual(reporte.getPlantillaExcel(), null)) {
                    IsspolLogger.getInstance().info("tiene plantilla excel {}", reporte.getIdentificador());
                    byteArrayInputStream = new ByteArrayInputStream(reporte.getPlantillaExcel());
                    jasperReportInputStream = byteArrayInputStream;
                } else {
                    throw new IsspolProcessException("No existe un plantilla valida para el reporte: " + nombreReporte);
                }
            }


            //Codigo para subreporte
            try {
                JasperReport jasperReportSR;
                for (ReporteSubreporte reporteSubreporte : lista) {
                    jasperReportSR = (JasperReport) JRLoader.loadObject(new ByteArrayInputStream(reporteSubreporte.getReporteByIdReporteHijo().getPlantilla()));
                    parametros.put(StringUtils.isNotEmpty(reporteSubreporte.getParametro()) ? reporteSubreporte.getParametro() : reporteSubreporte.getReporteByIdReporteHijo().getIdentificador(), jasperReportSR);
                }
            } catch (Exception e) {
                IsspolLogger.getInstance().error("Error ejemplo subreporte", e);
            }

            //Agregar el locale 'en_US'
            parametros.put(JRParameter.REPORT_LOCALE, Locale.US);

            //Agregar LIST de CABECERA
            //List<DatoCabecera> resultadoCabecera = ReportePersistenceUtil.getInstance().procReporteObtenerCabecera(parametros);
            parametros.put("dsCabeceraReporte", resultadoCabecera);

            //Agregar LIST de PIE
//            List<DatoPie> resultadoPie = ReportePersistenceUtil.getInstance().procReporteObtenerPie(parametros);
            parametros.put("dsPieReporte", resultadoPie);

            //Generar REPORTE
            jasperPrint = JasperFillManager.fillReport(jasperReportInputStream, parametros, connection);
            switch (mimeType) {
                case "application/pdf": {
                    JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
                    if (debugMode) {
                        JasperViewer visor = new JasperViewer(jasperPrint, false);
                        visor.setVisible(true);
                    }

                    break;
                }

                case "application/vnd.ms-excel": {
                    jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.graphics", "true");
                    jasperPrint.setProperty("net.sf.jasperreports.export.xls.one.page.per.sheet", "true");
                    jasperPrint.setProperty("net.sf.jasperreports.export.xls.remove.empty.space.between.rows", "true");
                    jasperPrint.setProperty("net.sf.jasperreports.export.xls.remove.empty.space.between.columns", "true");
                    jasperPrint.setProperty("net.sf.jasperreports.export.xls.white.page.background", "false");
                    jasperPrint.setProperty("net.sf.jasperreports.export.xls.detect.cell.type", "true");
                    jasperPrint.setProperty("net.sf.jasperreports.page.break.no.pagination", "apply");
                    jasperPrint.setProperty("net.sf.jasperreports.export.xls.sheet.names.all", "Data/Footnotes");
                    jasperPrint.setProperty("net.sf.jasperreports.print.keep.full.text", "true");
                    jasperPrint.setProperty("net.sf.jasperreports.exports.xls.font.size.fix.enabled", "false");

                    JRXlsxExporter exporter = new JRXlsxExporter();
                    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));

                    SimpleXlsxReportConfiguration xlsReportConfiguration = new SimpleXlsxReportConfiguration();
                    xlsReportConfiguration.setOnePagePerSheet(Boolean.FALSE);
                    xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                    xlsReportConfiguration.setDetectCellType(Boolean.TRUE);
                    xlsReportConfiguration.setWhitePageBackground(Boolean.FALSE);
                    exporter.setConfiguration(xlsReportConfiguration);

                    exporter.exportReport();
                    break;
                }

                case "application/msword": {
                    jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.graphics", "true");
                    jasperPrint.setProperty("net.sf.jasperreports.export.xls.one.page.per.sheet", "true");
                    jasperPrint.setProperty("net.sf.jasperreports.export.xls.one.page.per.sheet", "true");
                    jasperPrint.setProperty("net.sf.jasperreports.export.xls.remove.empty.space.between.rows", "true");
                    jasperPrint.setProperty("net.sf.jasperreports.export.xls.remove.empty.space.between.columns", "true");
                    jasperPrint.setProperty("net.sf.jasperreports.export.xls.white.page.background", "false");
                    jasperPrint.setProperty("net.sf.jasperreports.export.xls.detect.cell.type", "true");
                    jasperPrint.setProperty("net.sf.jasperreports.page.break.no.pagination", "apply");
                    jasperPrint.setProperty("net.sf.jasperreports.export.xls.sheet.names.all", "Data/Footnotes");
                    jasperPrint.setProperty("net.sf.jasperreports.print.keep.full.text", "true");
                    jasperPrint.setProperty("net.sf.jasperreports.exports.xls.font.size.fix.enabled", "false");
                    JRDocxExporter exporter = new JRDocxExporter();
                    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));

                    exporter.exportReport();
                    break;
                }
            }
            reporteBytes = byteArrayOutputStream.toByteArray();
            // An Async task always executes in new thread
         /*   new Thread(new Runnable() {
                public void run() {
                    guardarReporteGenerado(guardarEnBase, reporte, parametrosOriginal);
                }
            }).start();*/
        } catch (Exception ex) {
            throw new IsspolProcessException(ex);
        } finally {
            try {
                if (ObjectUtils.notEqual(byteArrayInputStream, null)) {
                    byteArrayInputStream.close();
                }
                if (ObjectUtils.notEqual(jasperReportInputStream, null)) {
                    jasperReportInputStream.close();
                }

                if (ObjectUtils.notEqual(byteArrayOutputStream, null)) {
                    byteArrayOutputStream.close();
                }
                if (ObjectUtils.notEqual(connection, null)) {
                    connection.close();
                }
            } catch (Exception e) {
                IsspolLogger.getInstance().error("Error", e);
            }
        }

        return reporteBytes;
    }


}
