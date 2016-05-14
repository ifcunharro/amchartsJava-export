package es.uvigo.esei.amchartsjava.export.constants.lang;

import static es.uvigo.esei.amchartsJava.core.constants.lang.I18n.getLanguage;


/**
 * This class manage idiom of export.
 * @author Iago Fernández Cuñarro
 *
 */
public final class I18n {
	
	private I18n(){
		
	}
	
	
	/**
	 * Get text export.
	 * @param key Key asociated to text searched.
	 * @return String Text searched.
	 */
	public static String get(final String key) {
		final String lang = getLanguage().getClass().getSimpleName();
		switch (lang) {
			case "ES":
				return ES.getInstance().get(key);
			case "EN":
				return EN.getInstance().get(key);
			default:
				return null;
		}
	}

}
