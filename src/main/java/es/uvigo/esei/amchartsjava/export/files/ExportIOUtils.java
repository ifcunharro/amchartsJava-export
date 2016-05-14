package es.uvigo.esei.amchartsjava.export.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import es.uvigo.esei.amchartsJava.core.constants.paths.AmchartsJavaPaths;
import es.uvigo.esei.amchartsJava.core.files.AmChartsIOUtils;
import es.uvigo.esei.amchartsjava.export.constants.ExportConstants;
import es.uvigo.esei.amchartsjava.export.constants.lang.I18n;

/**
 * This class load template report and save report html.
 * @author Iago Fernández Cuñarro
 *
 */
public final class ExportIOUtils {
	
	private ExportIOUtils(){
		
	}

	/**
	 * Save report.
	 * @param file File where save html.
	 * @param writer Text html.
	 */
	public static void saveReport(final File file, final Document writer){
		
		saveLibAmchartsToReportDirectory(file);
		try (FileOutputStream fos = new FileOutputStream(file)) {
			IOUtils.write(writer.toString(), fos);
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		
		System.out.println(I18n.get("ReportFinished")+file.getParentFile());
	}
	
	/**
	 * Get report template to basic report.
	 * @return StringBuilder Content of report template
	 * @throws IOException If report template doesn't found
	 */
	public static Document getDocument() throws IOException{
		URL templatePath = ExportIOUtils.class.getProtectionDomain()
				.getCodeSource()
				.getLocation();
		try {
			templatePath = new URL(URLDecoder.decode(templatePath.toString(),"UTF-8"));
			templatePath = new URL(templatePath,ExportConstants.REPORT_TEMPLATE_PATH+"template.html");
		} catch (MalformedURLException | UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		Document template = Jsoup.parse(new File(templatePath.getFile()), StandardCharsets.UTF_8.toString(),"");
		
		return template;
	}
	
	
	
	/**
	 * Copy amcharts folder to report directory
	 * @param file report file html
	 */
	private static void saveLibAmchartsToReportDirectory(final File file){
		URL amchartsPATH = AmChartsIOUtils.class.getProtectionDomain()
				.getCodeSource()
				.getLocation();
		
		try {
			amchartsPATH = new URL(URLDecoder.decode(amchartsPATH.toString(),"UTF-8"));
			amchartsPATH = new URL(amchartsPATH,AmchartsJavaPaths.AMCHARTS_PATH);
		} catch (MalformedURLException | UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			final File srcDir = new File(amchartsPATH.getPath()).getParentFile();
			final File destDir = new File(file.getParentFile(),
						   		new File(amchartsPATH.getPath())
								.getParentFile()
								.getName()
						   );
			FileUtils.copyDirectory(
					srcDir,
					destDir,
					new NotFileFilter(
						new RegexFileFilter("samples|temp|lang|.*html|.*txt")
					)
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
