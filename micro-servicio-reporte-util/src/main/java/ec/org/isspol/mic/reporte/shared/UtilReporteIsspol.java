package ec.org.isspol.mic.reporte.shared;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import ec.org.isspol.common.IsspolProcessException;
import ec.org.isspol.log.IsspolLogger;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
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


}
