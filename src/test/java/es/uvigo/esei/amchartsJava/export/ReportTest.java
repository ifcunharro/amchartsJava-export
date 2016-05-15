package es.uvigo.esei.amchartsJava.export;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import es.uvigo.esei.amchartsJava.core.constants.config.Config;
import es.uvigo.esei.amchartsJava.core.controllers.charts.AmSerialChartController;
import es.uvigo.esei.amchartsJava.export.constants.ExportConstants;
import es.uvigo.esei.amchartsJava.export.exceptions.ExportException;
import es.uvigo.esei.amchartsJava.export.files.ExportIOUtils;

public class ReportTest {
	
	@Rule
	public final ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void getBasicReport_return_default_template_correctly_if_chart_is_not_null(){
		Report rep = new Report();
		AmSerialChartController serial = new AmSerialChartController();
		Document report = null;
		try {
			report = rep.getBasicReport(serial);
		} catch (IOException | ExportException e) {
			e.printStackTrace();
		}
		Element divChart = report.getElementById(rep.getDivChart());
		assertEquals("Report amchartsJava",report.title());
		String head = report.head().toString();
		assertTrue(head.contains(ExportConstants.EXPORT));
		assertTrue(head.contains(ExportConstants.EXPORT_DEFAULT));
		assertNotNull(divChart);
		assertTrue(divChart.attr("style").contains(rep.getHeight().toString()));
		assertTrue(divChart.attr("style").contains(rep.getWidth().toString()));
	}
	
	@Test
	public void getBasicReport_launch_exception_if_chart_is_null() throws ExportException{
		thrown.expect(ExportException.class);
		switch (Config.getString("lang")) {
			case "en":
				thrown.expectMessage("Can't export a null chart");
				break;
			case "es":
				thrown.expectMessage("No puedes exportar un chart nulo");
				break;
		}
		final Report rep = new Report();
		try {
			rep.getBasicReport(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateReport_launch_exception_if_chart_is_null() throws ExportException{
		thrown.expect(ExportException.class);
		switch (Config.getString("lang")) {
			case "en":
				thrown.expectMessage("Can't export a null chart");
				break;
			case "es":
				thrown.expectMessage("No puedes exportar un chart nulo");
				break;
		}
		Report rep = new Report();
		Document doc = null;
		try {
			doc = ExportIOUtils.getDocument();
		} catch (IOException e) {
			e.printStackTrace();
		}
		rep.updateReport(null,doc);
	}
	
	@Test
	public void updateReport_return_null_if_document_is_null() throws ExportException{
		Report rep = new Report();
		AmSerialChartController serial = new AmSerialChartController();
		assertNull(rep.updateReport(serial,null));
	}
	
}
