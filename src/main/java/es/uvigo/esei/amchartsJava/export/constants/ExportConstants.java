package es.uvigo.esei.amchartsJava.export.constants;

import es.uvigo.esei.amchartsJava.core.constants.paths.AmchartsJavaPaths;


/**
 * This class contains constants used to format output html.
 * @author Iago Fernández Cuñarro
 *
 */
public final class ExportConstants {
	
	/**
	 * Script to make chart
	 */
	public static final String SCRIPT="<script>"+System.lineSeparator()+"\tvar chart = "
			+ "AmCharts.makeChart(AMCHARTS_DIVID,AMCHARTS_CONFIG"
			+System.lineSeparator()+"  );"
			+System.lineSeparator()+"  </script>";
	
	/**
	 * Path to template report.
	 */
	public static final String REPORT_TEMPLATE_PATH;
	
	
	public static final String TEMPLATE_DIVID = "AMCHARTS_DIVID";
	/**
	 * Mark where insert amcharts config in basic report.
	 */
	public static final String TEMPLATE_AMCHARTS_CONFIG = "AMCHARTS_CONFIG";
	
	/**
	 * template para css, replace FILE by name file css
	 */
	public static final String LINKCSS = "<link rel=\"stylesheet\" href=\"FILE\" "
			+ "type=\"text/css\">";
	
	/*
	 * Names of javascript files used by amcharts
	 */
	public static final String AMCHARTSJS_FILENAME = "amcharts.js";
	public static final String SERIALJS_FILENAME = "serial.js";
	public static final String FUNNELJS_FILENAME = "funnel.js";
	public static final String PIEJS_FILENAME = "pie.js";
	public static final String GAUGEJS_FILENAME = "gauge.js";
	public static final String RADARJS_FILENAME = "radar.js";
	public static final String XYJS_FILENAME = "xy.js";
	/**
	 * template to links js files amcharts, replace FILEJS by correct name file
	 */
	public static final String AMCHARTSJS_TEMPLATE =   "<script src=\""
								+AmchartsJavaPaths.AMCHARTS_PATH+"/FILEJS"+"\" "
								+ "type=\"text/javascript\"></script>";
	/**
	 * template para theme amcharts, replace FILEJS by correct name theme
	 */
	public static final String THEMESJS =   "<script src=\""
			+AmchartsJavaPaths.THEMES_PATH+"FILEJS\" type=\"text/javascript\"></script>";
	/**
	 * load css to export amcharts
	 */
	public static final String EXPORTCSS = "<link rel=\"stylesheet\" href=\""
			+AmchartsJavaPaths.AMCHARTS_PATH+"/plugins/export/export.css\" type=\"text/css\">";
	/**
	 * load export amcharts
	 */
	public static final String EXPORT = "<script src=\""
			+AmchartsJavaPaths.AMCHARTS_PATH+"/plugins/export/export.js"+"\" type=\"text/javascript\"></script>";
	
	/**
	 * load default config export amcharts(need also load export.js)
	 */
	public static final String EXPORT_DEFAULT = "<script src=\""
			+AmchartsJavaPaths.AMCHARTS_PATH+"/plugins/export/examples/export.config.default.js"+"\" "
					+ "type=\"text/javascript\"></script>";
	
	/**
	 * div that contains chart
	 */
	public static final String DIV_CHART ="<div id=\"AMCHARTS_DIVID\""
			+ "style=\"width:WIDTHpx; height:HEIGHTpx;\"></div>";
	
	/**
	 * Mark where insert width in div chart
	 */
	public static final String WIDTH="WIDTH";
	/**
	 * Mark where insert height in div chart
	 */
	public static final String HEIGHT="HEIGHT";
	
	static{
		if("debug".equals(ExportConfig.getString("mode"))){
			REPORT_TEMPLATE_PATH = ExportConfig.getString("debugTemplatePath");
		}else{
			REPORT_TEMPLATE_PATH = ExportConfig.getString("releaseTemplatePath");
		}
	}
	
	private ExportConstants(){
		
	}

}
