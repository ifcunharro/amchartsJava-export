package es.uvigo.esei.amchartsJava.export;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.uvigo.esei.amchartsJava.core.controllers.charts.AmChartController;
import es.uvigo.esei.amchartsJava.core.validators.PropertyValidator;
import es.uvigo.esei.amchartsJava.export.constants.ExportConstants;
import es.uvigo.esei.amchartsJava.export.constants.lang.I18n;
import es.uvigo.esei.amchartsJava.export.exceptions.ExportException;
import es.uvigo.esei.amchartsJava.export.files.ExportIOUtils;

/**
 * This class generate all neccesary to amcharts report or to add to your report.
 * @author Iago Fernández Cuñarro
 *
 */
public class Report {
	
	private Map<String,Object> data = new HashMap<>();
	
	{
		data.put("divId","chartdiv");
		data.put("title", "Report amchartsJava");
		data.put("width", 500);
		data.put("height", 400);
	}
	
	public Report(){
		
	}
	
	/**
	 * Get a basic report html with an unique div.
	 * @param chart some chart AmCharts.
	 * @return Document Html document with only amcharts.
	 * @throws IOException Template doesn't found
	 * @throws ExportException chart is null
	 */
	@SuppressWarnings("unchecked")
	public Document getBasicReport(final AmChartController<?> chart) throws IOException, ExportException{
		if(chart == null){
			throw new ExportException(I18n.get("NullChartException"));
		}
		
		Document template = ExportIOUtils.getDocument();
		Element head = template.head();
		template.title(data.get("title").toString());
		final List<String> css = (List<String>) data.get("filesCss");
		if(css != null){
			for(String fileCss:css){
				head.append(ExportConstants.LINKCSS.replace("FILE", fileCss));
			}
		}
		
		final Object export = chart.getExport();
		if(export != null){
			head.append(ExportConstants.EXPORTCSS);
		}
		
		for(String fileJs: getJSFilesAmCharts(chart)){
			head.append(fileJs);
		}
		
		final List<String> config =  getConfigAmCharts(chart);
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(config.get(0)+System.lineSeparator());
		
		for(int i=1;i<config.size()-1;i++){
			builder.append("\t  ");
			builder.append(config.get(i)+System.lineSeparator());
		}
		builder.append("\t"+config.get(config.size()-1));
		head.append(ExportConstants
				.SCRIPT
				.replace(ExportConstants.TEMPLATE_DIVID,
					data.get("divId").toString()
				)
				.replace(ExportConstants.TEMPLATE_AMCHARTS_CONFIG, 
					builder.toString()
				)
		);
		
		template.body().append(ExportConstants
				.DIV_CHART
				.replace(ExportConstants.TEMPLATE_DIVID,
							data.get("divId").toString()
						)
						.replace(ExportConstants.WIDTH,
							data.get("width").toString()
						)
						.replace(ExportConstants.HEIGHT,
							data.get("height").toString()
						)
				);
		return template;
		
		
	}
	
	/**
	 * Update your document with content amcharts.
	 * @param chart some chart AmCharts.
	 * @param doc Your document html
	 * @return Document document html updated with amcharts or null if doc is null.
	 * @throws ExportException chart is null or html doesn't contain a div with id equals to divChart
	 */
	@SuppressWarnings("unchecked")
	public Document updateReport(final AmChartController<?> chart, Document doc) throws ExportException{
		if(chart == null){
			throw new ExportException(I18n.get("NullChartException"));
		}
		if(doc == null){
			return null;
		}
		final String idChart = data
				.get("divId")
				.toString()
				.substring(1, 
						data
						.get("divId")
						.toString()
						.length()-1
				);
		final Element divChart = doc.getElementById(idChart);
		if(divChart == null){
			throw new ExportException(I18n.get("DivChartException"));
		}
		final Element head = doc.head();
		final String title = data.get("title").toString();
		if(!"Report amchartsJava".equals(title)){
			doc.title(title);
		}
		List<String> css = (List<String>) data.get("filesCss");
		if(css != null){
			for(String fileCss:css){
				head.append(ExportConstants.LINKCSS.replace("FILE", fileCss));
			}
		}
		for(String fileJs: getJSFilesAmCharts(chart)){
			head.append(fileJs);
		}
		
		final List<String> config =  getConfigAmCharts(chart);
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(config.get(0)+System.lineSeparator());
		
		for(int i=1;i<config.size()-1;i++){
			builder.append("\t  ");
			builder.append(config.get(i)+System.lineSeparator());
		}
		builder.append("\t"+config.get(config.size()-1));
		head.append(ExportConstants
				.SCRIPT
				.replace(ExportConstants.TEMPLATE_DIVID,
					data.get("divId").toString()
				)
				.replace(ExportConstants.TEMPLATE_AMCHARTS_CONFIG, 
					builder.toString()
				)
		);
		
		return doc;
	}
	
	/**
	 * Get title html report.
	 * Default: Report amchartsJava
	 * @return title report
	 */
	public String getTitle(){
		return data.get("title").toString();
	}
	
	/**
	 * Add a title to html report.
	 * @param title Text of html title.
	 */
	public void setTitle(final String title){
		if(PropertyValidator.isValidString(title)){
			data.put("title", title);
		}
	}
	
	/**
	 * Add names of your css files.
	 * @param cssFiles lists of css file names
	 */
	public void addFilesCss(final List<String> cssFiles){
		if(cssFiles != null){
			data.put("filesCss", cssFiles);
		}
	}
	
	/**
	 * Get div id where insert chart.
	 * @return div id
	 */
	public String getDivChart(){
		return data.get("divId").toString();
	}
	
	/**
	 * Div id where insert chart.
	 * Default: chartdiv.
	 * @param idDiv Div id.
	 */
	public void setDivChart(final String idDiv){
		if(PropertyValidator.isValidString(idDiv)){
			data.put("divId", idDiv);
		}
	}
	
	public Integer getWidth(){
		return  (Integer)data.get("width");
	}
	
	/**
	 * Set width div chart.
	 * @param divChartWidth width in pixels.
	 */
	public void setWidth(final Integer divChartWidth){
		if(divChartWidth != null && divChartWidth>0){
			data.put("width", divChartWidth);
		}
	}
	
	public Integer getHeight(){
		return (Integer)data.get("height");
	}
	
	/**
	 * Set height div chart.
	 * @param divChartHeight height in pixels.
	 */
	public void setHeight(final Integer divChartHeight){
		if(divChartHeight != null && divChartHeight>0){
			data.put("height", divChartHeight);
		}
	}
	
	/**
	 * Get lines to get JsFiles amcharts.
	 * @param chart Chart used to know jsFiles to include.
	 * @return amcharts JsFiles
	 */
	private List<String> getJSFilesAmCharts(AmChartController<?> chart){
		List<String> jsFiles = new LinkedList<>();
		jsFiles.add(ExportConstants.AMCHARTSJS_TEMPLATE.replace("FILEJS", 
				ExportConstants.AMCHARTSJS_FILENAME));
		final String typeChart = chart.getType();
		switch (typeChart) {
			//serial, pie, xy, radar, funnel, gauge;	
			case "serial":
				jsFiles.add(ExportConstants.AMCHARTSJS_TEMPLATE.replace("FILEJS", 
						ExportConstants.SERIALJS_FILENAME));
				break;
			case "pie":
				jsFiles.add(ExportConstants.AMCHARTSJS_TEMPLATE.replace("FILEJS", 
						ExportConstants.PIEJS_FILENAME));
				break;
			case "xy":
				jsFiles.add(ExportConstants.AMCHARTSJS_TEMPLATE.replace("FILEJS", 
						ExportConstants.XYJS_FILENAME));
				break;
			case "radar":
				jsFiles.add(ExportConstants.AMCHARTSJS_TEMPLATE.replace("FILEJS", 
						ExportConstants.RADARJS_FILENAME));
				break;
			case "funnel":
				jsFiles.add(ExportConstants.AMCHARTSJS_TEMPLATE.replace("FILEJS", 
						ExportConstants.FUNNELJS_FILENAME));
				break;
			case "gauge":
				jsFiles.add(ExportConstants.AMCHARTSJS_TEMPLATE.replace("FILEJS", 
						ExportConstants.GAUGEJS_FILENAME));
				break;
		}
		
		final String theme = chart.getTheme();
		
		if(theme != null){
			if(theme.endsWith(".js")){
				jsFiles.add(ExportConstants.THEMESJS.replace("FILEJS", theme));
			}else{
				jsFiles.add(ExportConstants.THEMESJS.replace("FILEJS", theme+".js"));
			}
		}
		final Object export = chart.getExport();
		if(export != null){
			jsFiles.add(ExportConstants.EXPORT);
			jsFiles.add(ExportConstants.EXPORT_DEFAULT);
		}
		
		
		return jsFiles;
	}
	
	/**
	 * Get lines config amcharts.
	 * @param chart chart amcharts
	 * @return config amcharts
	 */
	private List<String> getConfigAmCharts(AmChartController<?> chart){
		List<String> linesConfig = null;
		if(chart != null){
			final ObjectMapper mapper = new ObjectMapper();
			
			try {
				final String config = mapper
						.writerWithDefaultPrettyPrinter()
						.writeValueAsString(chart);
				
				linesConfig =  Arrays.asList(config.split(System.lineSeparator()));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		return linesConfig;
	}
	
}
