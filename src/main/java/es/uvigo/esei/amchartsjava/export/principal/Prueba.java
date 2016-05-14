package es.uvigo.esei.amchartsjava.export.principal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

import es.uvigo.esei.amchartsJava.core.constants.config.DefaultConfigCharts;
import es.uvigo.esei.amchartsJava.core.controllers.charts.AmSerialChartController;
import es.uvigo.esei.amchartsJava.core.files.AmChartsIOUtils;
import es.uvigo.esei.amchartsjava.export.Report;
import es.uvigo.esei.amchartsjava.export.exceptions.ExportException;
import es.uvigo.esei.amchartsjava.export.files.ExportIOUtils;

public class Prueba {
	
	public static void main(String[] args) {
		AmSerialChartController chart = new AmSerialChartController();
		chart.setTheme("dark");
		Report rep = new Report();
		List<String> css = new ArrayList<>(); 
		css.add("style.css");
		css.add("mou.css");
		rep.setTitle("molas");
		rep.setWidth(300);
		rep.setHeight(200);
		rep.addFilesCss(css);
		//save to amcharts temp to test report
		String reportFile = AmChartsIOUtils.getJsonDirectoryToSave()+"output.html";
		try {
			Document report = rep.getBasicReport(DefaultConfigCharts.initDefaultCandleStickGraphToBEW());
			ExportIOUtils.saveReport(new File(reportFile,""),report);
		} catch (IOException | ExportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//test to launch exception if chart div don't found
		try {
			rep.updateReport(chart, new Document(""));
		} catch (ExportException e) {
			e.printStackTrace();
		}
		
	}
}
