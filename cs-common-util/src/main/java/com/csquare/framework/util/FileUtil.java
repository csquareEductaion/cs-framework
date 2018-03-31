package com.csquare.framework.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.csquare.framework.util.SystemUtil.SystemKey;

public enum FileUtil {

	/* LEAD_CREATE_MAIL_TEMPLATE */
	LEAD_CREATE_MAIL_TEMPLATE("quick_enquiry_mail_template.html"),
	/* TUTOR_ENROLL_MAIL_TEMPLATE */
	TUTOR_ENROLL_MAIL_TEMPLATE("tutor_enroll_mail_template.html"),
	/* STUDENT_ENROLL_MAIL_TEMPLATE */
	STUDENT_ENROLL_MAIL_TEMPLATE("student_enroll_mail_template.html");

	private String templateContent;
	/** value **/
	private final String value;

	private FileUtil(String value) throws RuntimeException {
		this.value = value;
	}

	public String getFileAsString() {
		if(StringUtil.isNotEmpty(templateContent)) {
			return templateContent;
		}

		InputStream input = null;
		try {
			input = getFileIS();
			templateContent = readAsString(input);
		} finally {
			CommonUtil.close(input);
		}
		return templateContent;
	}

	public InputStream getFileIS() {
String fileName=this.value;
		InputStream input = null;
		try {
			String appConfigPath = SystemUtil.getEnv(SystemKey.CS_APPCONFIG_PATH);
			input = new FileInputStream(appConfigPath + "/email_template/" + fileName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return input;
	}

	/**
	 * Method to read data from InputStream
	 *
	 * @param is
	 *            - The InputStream
	 * @return fileContent - The String
	 */
	private static String readAsString(InputStream is) {

		StringBuilder sb = new StringBuilder();
		try {
			int i;
			while ((i = is.read()) > 0) {
				sb.append((char) i);
			}
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
		return sb.toString();
	}

}