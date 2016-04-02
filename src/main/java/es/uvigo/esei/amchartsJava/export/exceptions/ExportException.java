package es.uvigo.esei.amchartsJava.export.exceptions;


/**
 * Custom exception for export.
 * @author Iago Fernández Cuñarro
 *
 */
public class ExportException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor with custom message.
	 * 
	 * @param message
	 *            String, custom message.
	 */
	public ExportException(String message) {
		super(message);
	}

	/**
	 * Default constructor.
	 */
	public ExportException() {
		super();
	}
}
