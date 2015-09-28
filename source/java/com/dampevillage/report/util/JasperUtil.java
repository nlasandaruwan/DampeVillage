package com.dampevillage.report.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import com.dampevillage.util.PropertyUtil;

public class JasperUtil {

	private static PropertyUtil propertyUtil;

	private static String databaseName;
	private static String userName;
	private static String password;
	private static String reportLocation;
	private static Connection jdbcConnection;

	static {
		try {
			propertyUtil = new PropertyUtil();
			databaseName = propertyUtil.getValue("databaseName");
			userName = propertyUtil.getValue("userName");
			password = propertyUtil.getValue("password");
			reportLocation = propertyUtil.getValue("report.saving.folder");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void runReport(String reportFile, String reportType, Map<String, Object> parameterMap)
			throws JRException, ClassNotFoundException, SQLException {

		JasperDesign jasperDesign = JRXmlLoader.load(Class.forName("com.dampevillage.report.util.JasperUtil")
				.getResourceAsStream("/reports/" + reportType + "/" + reportFile + ".jrxml"));

		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			jdbcConnection = connectDB(databaseName, userName, password);
		}

		JasperPrint jasperPrint = null;
		if (parameterMap == null) {
			jasperPrint = JasperFillManager.fillReport(jasperReport, null, jdbcConnection);
		} else {
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, jdbcConnection);
		}

		JasperExportManager.exportReportToPdfFile(jasperPrint, reportLocation + reportFile + ".pdf");

		JasperViewer.viewReport(jasperPrint, false);
	}

	private static Connection connectDB(String databaseName, String userName, String password)
			throws ClassNotFoundException, SQLException {
		Connection jdbcConnection = null;

		Class.forName("com.mysql.jdbc.Driver");
		jdbcConnection = DriverManager.getConnection(databaseName, userName, password);

		return jdbcConnection;
	}
}
