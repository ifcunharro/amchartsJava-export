package es.uvigo.esei.amchartsjava.export.constants.lang;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import es.uvigo.esei.amchartsJava.core.constants.lang.Idiom;

/**
 * This class contains all texts used by amcharts export in spanish idiom.
 * @author Iago Fernández Cuñarro
 *
 */
public final class ES implements Idiom {

	private static Map<String,String> textsES = new ConcurrentHashMap<String,String>();
	
	static{
		textsES.put("NullChartException", "No puedes exportar un chart nulo");
		textsES.put("DivChartException", "Debe exixtir en tu html un div para amcharts");
		textsES.put("ReportFinished", "Reporte finalizado, se encuentra en ");
	}
	
	private ES(){
		
	}
	
	private static class InitSingleton{
		private static ES INSTANCE = new ES();
	}
	
	/**
	 * Get a singleton of this class.
	 * @return Idiom Instance of app language.
	 */
	public static Idiom getInstance(){
		return InitSingleton.INSTANCE;
	}
	
	/**
	 * Get a text in spanish.
	 * @param key Key asociated to text searched.
	 * @return String Text searched.
	 */
	public String get(final String key){
		return  textsES.get(key);
	}
	
}
