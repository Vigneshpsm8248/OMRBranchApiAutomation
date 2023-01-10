package com.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.base.BaseClass;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class Reporting extends BaseClass {
	
	/**
	 * @see Generate JVM Report With the Help of Jsonfile 
	 * @param jsonfile
	 * @throws FileNotFoundException
	 * @throws IOException
	 */

	public static void generateJVMReport(String jsonfile) throws FileNotFoundException, IOException {

		File file = new File(getProjectpath() + getPropertyFileValue("jvm"));

		Configuration configure = new Configuration(file, "Adaction Hotel App Automation");

		configure.addClassifications("Browser", "Chrome");
		configure.addClassifications("Verison", "108");
		configure.addClassifications("OS", "WINDOWS - 10");
		configure.addClassifications("Sprint", "32");

		List<String> jsonfiles = new ArrayList<String>();

		jsonfiles.add(jsonfile);

		ReportBuilder builder = new ReportBuilder(jsonfiles, configure);

		builder.generateReports();

	}


}
