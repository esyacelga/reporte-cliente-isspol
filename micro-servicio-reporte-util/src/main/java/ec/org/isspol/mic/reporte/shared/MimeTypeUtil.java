package ec.org.isspol.mic.reporte.shared;

import java.util.HashMap;
import java.util.Locale;

public final class MimeTypeUtil {

    private static final MimeTypeUtil CONTEXT_UTIL = new MimeTypeUtil();

    private static final String MIME_APPLICATION_ANDREW_INSET = "application/andrew-inset";
    private static final String MIME_APPLICATION_JSON = "application/json";
    private static final String MIME_APPLICATION_ZIP = "application/zip";
    private static final String MIME_APPLICATION_X_GZIP = "application/x-gzip";
    private static final String MIME_APPLICATION_TGZ = "application/tgz";
    private static final String MIME_APPLICATION_MSWORD = "application/msword";
    private static final String MIME_APPLICATION_MSWORD_2007 = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    private static final String MIME_APPLICATION_VND_TEXT = "application/vnd.oasis.opendocument.text";
    private static final String MIME_APPLICATION_POSTSCRIPT = "application/postscript";
    private static final String MIME_APPLICATION_PDF = "application/pdf";
    private static final String MIME_APPLICATION_JNLP = "application/jnlp";
    private static final String MIME_APPLICATION_MAC_BINHEX40 = "application/mac-binhex40";
    private static final String MIME_APPLICATION_MAC_COMPACTPRO = "application/mac-compactpro";
    private static final String MIME_APPLICATION_MATHML_XML = "application/mathml+xml";
    private static final String MIME_APPLICATION_OCTET_STREAM = "application/octet-stream";
    private static final String MIME_APPLICATION_ODA = "application/oda";
    private static final String MIME_APPLICATION_RDF_XML = "application/rdf+xml";
    private static final String MIME_APPLICATION_JAVA_ARCHIVE = "application/java-archive";
    private static final String MIME_APPLICATION_RDF_SMIL = "application/smil";
    private static final String MIME_APPLICATION_SRGS = "application/srgs";
    private static final String MIME_APPLICATION_SRGS_XML = "application/srgs+xml";
    private static final String MIME_APPLICATION_VND_MIF = "application/vnd.mif";
    private static final String MIME_APPLICATION_VND_MSEXCEL = "application/vnd.ms-excel";
    private static final String MIME_APPLICATION_VND_MSEXCEL_2007 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final String MIME_APPLICATION_VND_SPREADSHEET = "application/vnd.oasis.opendocument.spreadsheet";
    private static final String MIME_APPLICATION_VND_MSPOWERPOINT = "application/vnd.ms-powerpoint";
    private static final String MIME_APPLICATION_VND_RNREALMEDIA = "application/vnd.rn-realmedia";
    private static final String MIME_APPLICATION_X_BCPIO = "application/x-bcpio";
    private static final String MIME_APPLICATION_X_CDLINK = "application/x-cdlink";
    private static final String MIME_APPLICATION_X_CHESS_PGN = "application/x-chess-pgn";
    private static final String MIME_APPLICATION_X_CPIO = "application/x-cpio";
    private static final String MIME_APPLICATION_X_CSH = "application/x-csh";
    private static final String MIME_APPLICATION_X_DIRECTOR = "application/x-director";
    private static final String MIME_APPLICATION_X_DVI = "application/x-dvi";
    private static final String MIME_APPLICATION_X_FUTURESPLASH = "application/x-futuresplash";
    private static final String MIME_APPLICATION_X_GTAR = "application/x-gtar";
    private static final String MIME_APPLICATION_X_HDF = "application/x-hdf";
    private static final String MIME_APPLICATION_X_JAVASCRIPT = "application/x-javascript";
    private static final String MIME_APPLICATION_X_KOAN = "application/x-koan";
    private static final String MIME_APPLICATION_X_LATEX = "application/x-latex";
    private static final String MIME_APPLICATION_X_NETCDF = "application/x-netcdf";
    private static final String MIME_APPLICATION_X_OGG = "application/x-ogg";
    private static final String MIME_APPLICATION_X_SH = "application/x-sh";
    private static final String MIME_APPLICATION_X_SHAR = "application/x-shar";
    private static final String MIME_APPLICATION_X_SHOCKWAVE_FLASH = "application/x-shockwave-flash";
    private static final String MIME_APPLICATION_X_STUFFIT = "application/x-stuffit";
    private static final String MIME_APPLICATION_X_SV4CPIO = "application/x-sv4cpio";
    private static final String MIME_APPLICATION_X_SV4CRC = "application/x-sv4crc";
    private static final String MIME_APPLICATION_X_TAR = "application/x-tar";
    private static final String MIME_APPLICATION_X_RAR_COMPRESSED = "application/x-rar-compressed";
    private static final String MIME_APPLICATION_X_TCL = "application/x-tcl";
    private static final String MIME_APPLICATION_X_TEX = "application/x-tex";
    private static final String MIME_APPLICATION_X_TEXINFO = "application/x-texinfo";
    private static final String MIME_APPLICATION_X_TROFF = "application/x-troff";
    private static final String MIME_APPLICATION_X_TROFF_MAN = "application/x-troff-man";
    private static final String MIME_APPLICATION_X_TROFF_ME = "application/x-troff-me";
    private static final String MIME_APPLICATION_X_TROFF_MS = "application/x-troff-ms";
    private static final String MIME_APPLICATION_X_USTAR = "application/x-ustar";
    private static final String MIME_APPLICATION_X_WAIS_SOURCE = "application/x-wais-source";
    private static final String MIME_APPLICATION_VND_MOZZILLA_XUL_XML = "application/vnd.mozilla.xul+xml";
    private static final String MIME_APPLICATION_XHTML_XML = "application/xhtml+xml";
    private static final String MIME_APPLICATION_XSLT_XML = "application/xslt+xml";
    private static final String MIME_APPLICATION_XML = "application/xml";
    private static final String MIME_APPLICATION_XML_DTD = "application/xml-dtd";
    private static final String MIME_IMAGE_BMP = "image/bmp";
    private static final String MIME_IMAGE_CGM = "image/cgm";
    private static final String MIME_IMAGE_GIF = "image/gif";
    private static final String MIME_IMAGE_IEF = "image/ief";
    private static final String MIME_IMAGE_JPEG = "image/jpeg";
    private static final String MIME_IMAGE_TIFF = "image/tiff";
    private static final String MIME_IMAGE_PNG = "image/png";
    private static final String MIME_IMAGE_SVG_XML = "image/svg+xml";
    private static final String MIME_IMAGE_VND_DJVU = "image/vnd.djvu";
    private static final String MIME_IMAGE_WAP_WBMP = "image/vnd.wap.wbmp";
    private static final String MIME_IMAGE_X_CMU_RASTER = "image/x-cmu-raster";
    private static final String MIME_IMAGE_X_ICON = "image/x-icon";
    private static final String MIME_IMAGE_X_PORTABLE_ANYMAP = "image/x-portable-anymap";
    private static final String MIME_IMAGE_X_PORTABLE_BITMAP = "image/x-portable-bitmap";
    private static final String MIME_IMAGE_X_PORTABLE_GRAYMAP = "image/x-portable-graymap";
    private static final String MIME_IMAGE_X_PORTABLE_PIXMAP = "image/x-portable-pixmap";
    private static final String MIME_IMAGE_X_RGB = "image/x-rgb";
    private static final String MIME_AUDIO_BASIC = "audio/basic";
    private static final String MIME_AUDIO_MIDI = "audio/midi";
    private static final String MIME_AUDIO_MPEG = "audio/mpeg";
    private static final String MIME_AUDIO_X_AIFF = "audio/x-aiff";
    private static final String MIME_AUDIO_X_MPEGURL = "audio/x-mpegurl";
    private static final String MIME_AUDIO_X_PN_REALAUDIO = "audio/x-pn-realaudio";
    private static final String MIME_AUDIO_X_WAV = "audio/x-wav";
    private static final String MIME_CHEMICAL_X_PDB = "chemical/x-pdb";
    private static final String MIME_CHEMICAL_X_XYZ = "chemical/x-xyz";
    private static final String MIME_MODEL_IGES = "model/iges";
    private static final String MIME_MODEL_MESH = "model/mesh";
    private static final String MIME_MODEL_VRLM = "model/vrml";
    private static final String MIME_TEXT_PLAIN = "text/plain";
    private static final String MIME_TEXT_RICHTEXT = "text/richtext";
    private static final String MIME_TEXT_RTF = "text/rtf";
    private static final String MIME_TEXT_HTML = "text/html";
    private static final String MIME_TEXT_CALENDAR = "text/calendar";
    private static final String MIME_TEXT_CSS = "text/css";
    private static final String MIME_TEXT_SGML = "text/sgml";
    private static final String MIME_TEXT_TAB_SEPARATED_VALUES = "text/tab-separated-values";
    private static final String MIME_TEXT_VND_WAP_XML = "text/vnd.wap.wml";
    private static final String MIME_TEXT_VND_WAP_WMLSCRIPT = "text/vnd.wap.wmlscript";
    private static final String MIME_TEXT_X_SETEXT = "text/x-setext";
    private static final String MIME_TEXT_X_COMPONENT = "text/x-component";
    private static final String MIME_VIDEO_QUICKTIME = "video/quicktime";
    private static final String MIME_VIDEO_MPEG = "video/mpeg";
    private static final String MIME_VIDEO_VND_MPEGURL = "video/vnd.mpegurl";
    private static final String MIME_VIDEO_X_MSVIDEO = "video/x-msvideo";
    private static final String MIME_VIDEO_X_MS_WMV = "video/x-ms-wmv";
    private static final String MIME_VIDEO_X_SGI_MOVIE = "video/x-sgi-movie";
    private static final String MIME_X_CONFERENCE_X_COOLTALK = "x-conference/x-cooltalk";

    private static final String MIME_TEXT_XML = "text/xml";

    private static HashMap<String, String> mimeTypeMapping;
    private static HashMap<String, String> extMapping;

    private MimeTypeUtil() {
        this.init();
    }

    private void init() {
        mimeTypeMapping = new HashMap<String, String>();
        extMapping = new HashMap<String, String>();

        mimeTypeMapping.put("xul", MIME_APPLICATION_VND_MOZZILLA_XUL_XML);
        mimeTypeMapping.put("json", MIME_APPLICATION_JSON);
        mimeTypeMapping.put("ice", MIME_X_CONFERENCE_X_COOLTALK);
        mimeTypeMapping.put("movie", MIME_VIDEO_X_SGI_MOVIE);
        mimeTypeMapping.put("avi", MIME_VIDEO_X_MSVIDEO);
        mimeTypeMapping.put("wmv", MIME_VIDEO_X_MS_WMV);
        mimeTypeMapping.put("m4u", MIME_VIDEO_VND_MPEGURL);
        mimeTypeMapping.put("mxu", MIME_VIDEO_VND_MPEGURL);
        mimeTypeMapping.put("htc", MIME_TEXT_X_COMPONENT);
        mimeTypeMapping.put("etx", MIME_TEXT_X_SETEXT);
        mimeTypeMapping.put("wmls", MIME_TEXT_VND_WAP_WMLSCRIPT);
        mimeTypeMapping.put("wml", MIME_TEXT_VND_WAP_XML);
        mimeTypeMapping.put("tsv", MIME_TEXT_TAB_SEPARATED_VALUES);
        mimeTypeMapping.put("sgm", MIME_TEXT_SGML);
        mimeTypeMapping.put("sgml", MIME_TEXT_SGML);
        mimeTypeMapping.put("css", MIME_TEXT_CSS);
        mimeTypeMapping.put("ifb", MIME_TEXT_CALENDAR);
        mimeTypeMapping.put("ics", MIME_TEXT_CALENDAR);
        mimeTypeMapping.put("wrl", MIME_MODEL_VRLM);
        mimeTypeMapping.put("vrlm", MIME_MODEL_VRLM);
        mimeTypeMapping.put("silo", MIME_MODEL_MESH);
        mimeTypeMapping.put("mesh", MIME_MODEL_MESH);
        mimeTypeMapping.put("msh", MIME_MODEL_MESH);
        mimeTypeMapping.put("iges", MIME_MODEL_IGES);
        mimeTypeMapping.put("igs", MIME_MODEL_IGES);
        mimeTypeMapping.put("rgb", MIME_IMAGE_X_RGB);
        mimeTypeMapping.put("ppm", MIME_IMAGE_X_PORTABLE_PIXMAP);
        mimeTypeMapping.put("pgm", MIME_IMAGE_X_PORTABLE_GRAYMAP);
        mimeTypeMapping.put("pbm", MIME_IMAGE_X_PORTABLE_BITMAP);
        mimeTypeMapping.put("pnm", MIME_IMAGE_X_PORTABLE_ANYMAP);
        mimeTypeMapping.put("ico", MIME_IMAGE_X_ICON);
        mimeTypeMapping.put("ras", MIME_IMAGE_X_CMU_RASTER);
        mimeTypeMapping.put("wbmp", MIME_IMAGE_WAP_WBMP);
        mimeTypeMapping.put("djv", MIME_IMAGE_VND_DJVU);
        mimeTypeMapping.put("djvu", MIME_IMAGE_VND_DJVU);
        mimeTypeMapping.put("svg", MIME_IMAGE_SVG_XML);
        mimeTypeMapping.put("ief", MIME_IMAGE_IEF);
        mimeTypeMapping.put("cgm", MIME_IMAGE_CGM);
        mimeTypeMapping.put("bmp", MIME_IMAGE_BMP);
        mimeTypeMapping.put("xyz", MIME_CHEMICAL_X_XYZ);
        mimeTypeMapping.put("pdb", MIME_CHEMICAL_X_PDB);
        mimeTypeMapping.put("ra", MIME_AUDIO_X_PN_REALAUDIO);
        mimeTypeMapping.put("ram", MIME_AUDIO_X_PN_REALAUDIO);
        mimeTypeMapping.put("m3u", MIME_AUDIO_X_MPEGURL);
        mimeTypeMapping.put("aifc", MIME_AUDIO_X_AIFF);
        mimeTypeMapping.put("aif", MIME_AUDIO_X_AIFF);
        mimeTypeMapping.put("aiff", MIME_AUDIO_X_AIFF);
        mimeTypeMapping.put("mp3", MIME_AUDIO_MPEG);
        mimeTypeMapping.put("mp2", MIME_AUDIO_MPEG);
        mimeTypeMapping.put("mp1", MIME_AUDIO_MPEG);
        mimeTypeMapping.put("mpga", MIME_AUDIO_MPEG);
        mimeTypeMapping.put("kar", MIME_AUDIO_MIDI);
        mimeTypeMapping.put("mid", MIME_AUDIO_MIDI);
        mimeTypeMapping.put("midi", MIME_AUDIO_MIDI);
        mimeTypeMapping.put("dtd", MIME_APPLICATION_XML_DTD);
        mimeTypeMapping.put("xsl", MIME_APPLICATION_XML);
        mimeTypeMapping.put("xml", MIME_APPLICATION_XML);
        mimeTypeMapping.put("xslt", MIME_APPLICATION_XSLT_XML);
        mimeTypeMapping.put("xht", MIME_APPLICATION_XHTML_XML);
        mimeTypeMapping.put("xhtml", MIME_APPLICATION_XHTML_XML);
        mimeTypeMapping.put("src", MIME_APPLICATION_X_WAIS_SOURCE);
        mimeTypeMapping.put("ustar", MIME_APPLICATION_X_USTAR);
        mimeTypeMapping.put("ms", MIME_APPLICATION_X_TROFF_MS);
        mimeTypeMapping.put("me", MIME_APPLICATION_X_TROFF_ME);
        mimeTypeMapping.put("man", MIME_APPLICATION_X_TROFF_MAN);
        mimeTypeMapping.put("roff", MIME_APPLICATION_X_TROFF);
        mimeTypeMapping.put("tr", MIME_APPLICATION_X_TROFF);
        mimeTypeMapping.put("t", MIME_APPLICATION_X_TROFF);
        mimeTypeMapping.put("texi", MIME_APPLICATION_X_TEXINFO);
        mimeTypeMapping.put("texinfo", MIME_APPLICATION_X_TEXINFO);
        mimeTypeMapping.put("tex", MIME_APPLICATION_X_TEX);
        mimeTypeMapping.put("tcl", MIME_APPLICATION_X_TCL);
        mimeTypeMapping.put("sv4crc", MIME_APPLICATION_X_SV4CRC);
        mimeTypeMapping.put("sv4cpio", MIME_APPLICATION_X_SV4CPIO);
        mimeTypeMapping.put("sit", MIME_APPLICATION_X_STUFFIT);
        mimeTypeMapping.put("swf", MIME_APPLICATION_X_SHOCKWAVE_FLASH);
        mimeTypeMapping.put("shar", MIME_APPLICATION_X_SHAR);
        mimeTypeMapping.put("sh", MIME_APPLICATION_X_SH);
        mimeTypeMapping.put("cdf", MIME_APPLICATION_X_NETCDF);
        mimeTypeMapping.put("nc", MIME_APPLICATION_X_NETCDF);
        mimeTypeMapping.put("latex", MIME_APPLICATION_X_LATEX);
        mimeTypeMapping.put("skm", MIME_APPLICATION_X_KOAN);
        mimeTypeMapping.put("skt", MIME_APPLICATION_X_KOAN);
        mimeTypeMapping.put("skd", MIME_APPLICATION_X_KOAN);
        mimeTypeMapping.put("skp", MIME_APPLICATION_X_KOAN);
        mimeTypeMapping.put("js", MIME_APPLICATION_X_JAVASCRIPT);
        mimeTypeMapping.put("hdf", MIME_APPLICATION_X_HDF);
        mimeTypeMapping.put("gtar", MIME_APPLICATION_X_GTAR);
        mimeTypeMapping.put("spl", MIME_APPLICATION_X_FUTURESPLASH);
        mimeTypeMapping.put("dvi", MIME_APPLICATION_X_DVI);
        mimeTypeMapping.put("dxr", MIME_APPLICATION_X_DIRECTOR);
        mimeTypeMapping.put("dir", MIME_APPLICATION_X_DIRECTOR);
        mimeTypeMapping.put("dcr", MIME_APPLICATION_X_DIRECTOR);
        mimeTypeMapping.put("csh", MIME_APPLICATION_X_CSH);
        mimeTypeMapping.put("cpio", MIME_APPLICATION_X_CPIO);
        mimeTypeMapping.put("pgn", MIME_APPLICATION_X_CHESS_PGN);
        mimeTypeMapping.put("vcd", MIME_APPLICATION_X_CDLINK);
        mimeTypeMapping.put("bcpio", MIME_APPLICATION_X_BCPIO);
        mimeTypeMapping.put("rm", MIME_APPLICATION_VND_RNREALMEDIA);
        mimeTypeMapping.put("ppt", MIME_APPLICATION_VND_MSPOWERPOINT);
        mimeTypeMapping.put("mif", MIME_APPLICATION_VND_MIF);
        mimeTypeMapping.put("grxml", MIME_APPLICATION_SRGS_XML);
        mimeTypeMapping.put("gram", MIME_APPLICATION_SRGS);
        mimeTypeMapping.put("smil", MIME_APPLICATION_RDF_SMIL);
        mimeTypeMapping.put("smi", MIME_APPLICATION_RDF_SMIL);
        mimeTypeMapping.put("rdf", MIME_APPLICATION_RDF_XML);
        mimeTypeMapping.put("ogg", MIME_APPLICATION_X_OGG);
        mimeTypeMapping.put("oda", MIME_APPLICATION_ODA);
        mimeTypeMapping.put("dmg", MIME_APPLICATION_OCTET_STREAM);
        mimeTypeMapping.put("lzh", MIME_APPLICATION_OCTET_STREAM);
        mimeTypeMapping.put("so", MIME_APPLICATION_OCTET_STREAM);
        mimeTypeMapping.put("lha", MIME_APPLICATION_OCTET_STREAM);
        mimeTypeMapping.put("dms", MIME_APPLICATION_OCTET_STREAM);
        mimeTypeMapping.put("bin", MIME_APPLICATION_OCTET_STREAM);
        mimeTypeMapping.put("mathml", MIME_APPLICATION_MATHML_XML);
        mimeTypeMapping.put("cpt", MIME_APPLICATION_MAC_COMPACTPRO);
        mimeTypeMapping.put("hqx", MIME_APPLICATION_MAC_BINHEX40);
        mimeTypeMapping.put("jnlp", MIME_APPLICATION_JNLP);
        mimeTypeMapping.put("ez", MIME_APPLICATION_ANDREW_INSET);
        mimeTypeMapping.put("txt", MIME_TEXT_PLAIN);
        mimeTypeMapping.put("ini", MIME_TEXT_PLAIN);
        mimeTypeMapping.put("c", MIME_TEXT_PLAIN);
        mimeTypeMapping.put("h", MIME_TEXT_PLAIN);
        mimeTypeMapping.put("cpp", MIME_TEXT_PLAIN);
        mimeTypeMapping.put("cxx", MIME_TEXT_PLAIN);
        mimeTypeMapping.put("cc", MIME_TEXT_PLAIN);
        mimeTypeMapping.put("chh", MIME_TEXT_PLAIN);
        mimeTypeMapping.put("java", MIME_TEXT_PLAIN);
        mimeTypeMapping.put("csv", MIME_TEXT_PLAIN);
        mimeTypeMapping.put("bat", MIME_TEXT_PLAIN);
        mimeTypeMapping.put("cmd", MIME_TEXT_PLAIN);
        mimeTypeMapping.put("asc", MIME_TEXT_PLAIN);
        mimeTypeMapping.put("rtf", MIME_TEXT_RTF);
        mimeTypeMapping.put("rtx", MIME_TEXT_RICHTEXT);
        mimeTypeMapping.put("html", MIME_TEXT_HTML);
        mimeTypeMapping.put("htm", MIME_TEXT_HTML);
        mimeTypeMapping.put("zip", MIME_APPLICATION_ZIP);
        mimeTypeMapping.put("rar", MIME_APPLICATION_X_RAR_COMPRESSED);
        mimeTypeMapping.put("gzip", MIME_APPLICATION_X_GZIP);
        mimeTypeMapping.put("gz", MIME_APPLICATION_X_GZIP);
        mimeTypeMapping.put("tgz", MIME_APPLICATION_TGZ);
        mimeTypeMapping.put("tar", MIME_APPLICATION_X_TAR);
        mimeTypeMapping.put("gif", MIME_IMAGE_GIF);
        mimeTypeMapping.put("jpeg", MIME_IMAGE_JPEG);
        mimeTypeMapping.put("jpg", MIME_IMAGE_JPEG);
        mimeTypeMapping.put("jpe", MIME_IMAGE_JPEG);
        mimeTypeMapping.put("tiff", MIME_IMAGE_TIFF);
        mimeTypeMapping.put("tif", MIME_IMAGE_TIFF);
        mimeTypeMapping.put("png", MIME_IMAGE_PNG);
        mimeTypeMapping.put("au", MIME_AUDIO_BASIC);
        mimeTypeMapping.put("snd", MIME_AUDIO_BASIC);
        mimeTypeMapping.put("wav", MIME_AUDIO_X_WAV);
        mimeTypeMapping.put("mov", MIME_VIDEO_QUICKTIME);
        mimeTypeMapping.put("qt", MIME_VIDEO_QUICKTIME);
        mimeTypeMapping.put("mpeg", MIME_VIDEO_MPEG);
        mimeTypeMapping.put("mpg", MIME_VIDEO_MPEG);
        mimeTypeMapping.put("mpe", MIME_VIDEO_MPEG);
        mimeTypeMapping.put("abs", MIME_VIDEO_MPEG);
        mimeTypeMapping.put("doc", MIME_APPLICATION_MSWORD);
        mimeTypeMapping.put("docx", MIME_APPLICATION_MSWORD_2007);
        mimeTypeMapping.put("odt", MIME_APPLICATION_VND_TEXT);
        mimeTypeMapping.put("xls", MIME_APPLICATION_VND_MSEXCEL);
        mimeTypeMapping.put("xlsx", MIME_APPLICATION_VND_MSEXCEL_2007);
        mimeTypeMapping.put("ods", MIME_APPLICATION_VND_SPREADSHEET);
        mimeTypeMapping.put("eps", MIME_APPLICATION_POSTSCRIPT);
        mimeTypeMapping.put("ai", MIME_APPLICATION_POSTSCRIPT);
        mimeTypeMapping.put("ps", MIME_APPLICATION_POSTSCRIPT);
        mimeTypeMapping.put("pdf", MIME_APPLICATION_PDF);
        mimeTypeMapping.put("exe", MIME_APPLICATION_OCTET_STREAM);
        mimeTypeMapping.put("dll", MIME_APPLICATION_OCTET_STREAM);
        mimeTypeMapping.put("class", MIME_APPLICATION_OCTET_STREAM);
        mimeTypeMapping.put("jar", MIME_APPLICATION_JAVA_ARCHIVE);
        mimeTypeMapping.put("xml", MIME_TEXT_XML);

        extMapping.put(MIME_APPLICATION_VND_MOZZILLA_XUL_XML, "xul");
        extMapping.put(MIME_APPLICATION_JSON, "json");
        extMapping.put(MIME_X_CONFERENCE_X_COOLTALK, "ice");
        extMapping.put(MIME_VIDEO_X_SGI_MOVIE, "movie");
        extMapping.put(MIME_VIDEO_X_MSVIDEO, "avi");
        extMapping.put(MIME_VIDEO_X_MS_WMV, "wmv");
        extMapping.put(MIME_VIDEO_VND_MPEGURL, "m4u");
        extMapping.put(MIME_TEXT_X_COMPONENT, "htc");
        extMapping.put(MIME_TEXT_X_SETEXT, "etx");
        extMapping.put(MIME_TEXT_VND_WAP_WMLSCRIPT, "wmls");
        extMapping.put(MIME_TEXT_VND_WAP_XML, "wml");
        extMapping.put(MIME_TEXT_TAB_SEPARATED_VALUES, "tsv");
        extMapping.put(MIME_TEXT_SGML, "sgml");
        extMapping.put(MIME_TEXT_CSS, "css");
        extMapping.put(MIME_TEXT_CALENDAR, "ics");
        extMapping.put(MIME_MODEL_VRLM, "vrlm");
        extMapping.put(MIME_MODEL_MESH, "mesh");
        extMapping.put(MIME_MODEL_IGES, "iges");
        extMapping.put(MIME_IMAGE_X_RGB, "rgb");
        extMapping.put(MIME_IMAGE_X_PORTABLE_PIXMAP, "ppm");
        extMapping.put(MIME_IMAGE_X_PORTABLE_GRAYMAP, "pgm");
        extMapping.put(MIME_IMAGE_X_PORTABLE_BITMAP, "pbm");
        extMapping.put(MIME_IMAGE_X_PORTABLE_ANYMAP, "pnm");
        extMapping.put(MIME_IMAGE_X_ICON, "ico");
        extMapping.put(MIME_IMAGE_X_CMU_RASTER, "ras");
        extMapping.put(MIME_IMAGE_WAP_WBMP, "wbmp");
        extMapping.put(MIME_IMAGE_VND_DJVU, "djvu");
        extMapping.put(MIME_IMAGE_SVG_XML, "svg");
        extMapping.put(MIME_IMAGE_IEF, "ief");
        extMapping.put(MIME_IMAGE_CGM, "cgm");
        extMapping.put(MIME_IMAGE_BMP, "bmp");
        extMapping.put(MIME_CHEMICAL_X_XYZ, "xyz");
        extMapping.put(MIME_CHEMICAL_X_PDB, "pdb");
        extMapping.put(MIME_AUDIO_X_PN_REALAUDIO, "ra");
        extMapping.put(MIME_AUDIO_X_MPEGURL, "m3u");
        extMapping.put(MIME_AUDIO_X_AIFF, "aiff");
        extMapping.put(MIME_AUDIO_MPEG, "mp3");
        extMapping.put(MIME_AUDIO_MIDI, "midi");
        extMapping.put(MIME_APPLICATION_XML_DTD, "dtd");
        extMapping.put(MIME_APPLICATION_XML, "xml");
        extMapping.put(MIME_APPLICATION_XSLT_XML, "xslt");
        extMapping.put(MIME_APPLICATION_XHTML_XML, "xhtml");
        extMapping.put(MIME_APPLICATION_X_WAIS_SOURCE, "src");
        extMapping.put(MIME_APPLICATION_X_USTAR, "ustar");
        extMapping.put(MIME_APPLICATION_X_TROFF_MS, "ms");
        extMapping.put(MIME_APPLICATION_X_TROFF_ME, "me");
        extMapping.put(MIME_APPLICATION_X_TROFF_MAN, "man");
        extMapping.put(MIME_APPLICATION_X_TROFF, "roff");
        extMapping.put(MIME_APPLICATION_X_TEXINFO, "texi");
        extMapping.put(MIME_APPLICATION_X_TEX, "tex");
        extMapping.put(MIME_APPLICATION_X_TCL, "tcl");
        extMapping.put(MIME_APPLICATION_X_SV4CRC, "sv4crc");
        extMapping.put(MIME_APPLICATION_X_SV4CPIO, "sv4cpio");
        extMapping.put(MIME_APPLICATION_X_STUFFIT, "sit");
        extMapping.put(MIME_APPLICATION_X_SHOCKWAVE_FLASH, "swf");
        extMapping.put(MIME_APPLICATION_X_SHAR, "shar");
        extMapping.put(MIME_APPLICATION_X_SH, "sh");
        extMapping.put(MIME_APPLICATION_X_NETCDF, "cdf");
        extMapping.put(MIME_APPLICATION_X_LATEX, "latex");
        extMapping.put(MIME_APPLICATION_X_KOAN, "skm");
        extMapping.put(MIME_APPLICATION_X_JAVASCRIPT, "js");
        extMapping.put(MIME_APPLICATION_X_HDF, "hdf");
        extMapping.put(MIME_APPLICATION_X_GTAR, "gtar");
        extMapping.put(MIME_APPLICATION_X_FUTURESPLASH, "spl");
        extMapping.put(MIME_APPLICATION_X_DVI, "dvi");
        extMapping.put(MIME_APPLICATION_X_DIRECTOR, "dir");
        extMapping.put(MIME_APPLICATION_X_CSH, "csh");
        extMapping.put(MIME_APPLICATION_X_CPIO, "cpio");
        extMapping.put(MIME_APPLICATION_X_CHESS_PGN, "pgn");
        extMapping.put(MIME_APPLICATION_X_CDLINK, "vcd");
        extMapping.put(MIME_APPLICATION_X_BCPIO, "bcpio");
        extMapping.put(MIME_APPLICATION_VND_RNREALMEDIA, "rm");
        extMapping.put(MIME_APPLICATION_VND_MSPOWERPOINT, "ppt");
        extMapping.put(MIME_APPLICATION_VND_MIF, "mif");
        extMapping.put(MIME_APPLICATION_SRGS_XML, "grxml");
        extMapping.put(MIME_APPLICATION_SRGS, "gram");
        extMapping.put(MIME_APPLICATION_RDF_SMIL, "smil");
        extMapping.put(MIME_APPLICATION_RDF_XML, "rdf");
        extMapping.put(MIME_APPLICATION_X_OGG, "ogg");
        extMapping.put(MIME_APPLICATION_ODA, "oda");
        extMapping.put(MIME_APPLICATION_MATHML_XML, "mathml");
        extMapping.put(MIME_APPLICATION_MAC_COMPACTPRO, "cpt");
        extMapping.put(MIME_APPLICATION_MAC_BINHEX40, "hqx");
        extMapping.put(MIME_APPLICATION_JNLP, "jnlp");
        extMapping.put(MIME_APPLICATION_ANDREW_INSET, "ez");
        extMapping.put(MIME_TEXT_PLAIN, "txt");
        extMapping.put(MIME_TEXT_RTF, "rtf");
        extMapping.put(MIME_TEXT_RICHTEXT, "rtx");
        extMapping.put(MIME_TEXT_HTML, "html");
        extMapping.put(MIME_APPLICATION_ZIP, "zip");
        extMapping.put(MIME_APPLICATION_X_RAR_COMPRESSED, "rar");
        extMapping.put(MIME_APPLICATION_X_GZIP, "gzip");
        extMapping.put(MIME_APPLICATION_TGZ, "tgz");
        extMapping.put(MIME_APPLICATION_X_TAR, "tar");
        extMapping.put(MIME_IMAGE_GIF, "gif");
        extMapping.put(MIME_IMAGE_JPEG, "jpg");
        extMapping.put(MIME_IMAGE_TIFF, "tiff");
        extMapping.put(MIME_IMAGE_PNG, "png");
        extMapping.put(MIME_AUDIO_BASIC, "au");
        extMapping.put(MIME_AUDIO_X_WAV, "wav");
        extMapping.put(MIME_VIDEO_QUICKTIME, "mov");
        extMapping.put(MIME_VIDEO_MPEG, "mpg");
        extMapping.put(MIME_APPLICATION_MSWORD, "doc");
        extMapping.put(MIME_APPLICATION_MSWORD_2007, "docx");
        extMapping.put(MIME_APPLICATION_VND_TEXT, "odt");
        extMapping.put(MIME_APPLICATION_VND_MSEXCEL, "xls");
        extMapping.put(MIME_APPLICATION_VND_SPREADSHEET, "ods");
        extMapping.put(MIME_APPLICATION_POSTSCRIPT, "ps");
        extMapping.put(MIME_APPLICATION_PDF, "pdf");
        extMapping.put(MIME_APPLICATION_OCTET_STREAM, "exe");
        extMapping.put(MIME_APPLICATION_JAVA_ARCHIVE, "jar");
        extMapping.put(MIME_TEXT_XML, "xml");
    }

    /**
     * Returns the corresponding MIME type to the given extension.
     * If no MIME type was found it returns 'application/octet-stream' type.
     */
    public String getMimeType(String ext) {
        String mimeType = lookupMimeType(ext);
        if (mimeType == null) {
            mimeType = "";
        }
        return mimeType;
    }

    /**
     * Simply returns MIME type or <code>null</code> if no type is found.
     */
    private String lookupMimeType(String ext) {
        return mimeTypeMapping.get(ext.toLowerCase(Locale.getDefault()));
    }

    /**
     * Simply returns Ext or <code>null</code> if no Mimetype is found.
     */
    private String lookupExt(String mimeType) {
        return extMapping.get(mimeType.toLowerCase(Locale.getDefault()));
    }

    /**
     * Returns the default Ext to the given MimeType.
     * If no MIME type was found it returns 'unknown' ext.
     */
    public String getDefaultExt(String mimeType) {
        String ext = lookupExt(mimeType);
        if (ext == null) {
            ext = "unknown";
        }
        return ext;
    }

    public static MimeTypeUtil getContextUtil() {
        return CONTEXT_UTIL;
    }
}
