package es.uvigo.esei.amchartsJava.export.constants.lang;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import es.uvigo.esei.amchartsJava.core.constants.lang.Idiom;

/**
 * This class contains all texts used by amcharts export in english idiom.
 * @author Iago Fernández Cuñarro
 *
 */
public final class EN implements Idiom {

	private static Map<String,String> textsEN = new ConcurrentHashMap<String,String>();
	
	static{
		textsEN.put("NullChartException", "Can't export a null chart");
		textsEN.put("DivChartException", "Must exist in your html a div to amcharts");
		textsEN.put("ReportFinished", "Report finished, found in ");
	}
	
	private EN(){
		
	}
	
	private static class InitSingleton{
		private static EN INSTANCE = new EN();
	}
	
	/**
	 * Get a singleton of this class.
	 * @return Idiom Instance of app language.
	 */
	public static Idiom getInstance(){
		return InitSingleton.INSTANCE;
	}
	
	/**
	 * Get a text in english.
	 * @param key Key asociated to text searched.
	 * @return String Text searched.
	 */
	public String get(final String key){
		return  textsEN.get(key);
	}
	
}
