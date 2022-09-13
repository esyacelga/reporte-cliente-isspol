package ec.org.isspol.mic.reporte.shared.exception;

/**
 * Created by roberto.chasipanta on 6/22/2017.
 */
@SuppressWarnings("serial")
public class ReportException extends Exception {
	public ReportException() {
	}

	public ReportException(String message) {
		super(message);
	}

	public ReportException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReportException(Throwable cause) {
		super(cause);
	}
}

