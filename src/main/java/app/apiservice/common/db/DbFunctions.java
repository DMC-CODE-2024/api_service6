package app.apiservice.common.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class DbFunctions {

	private static final Logger logger = LogManager.getLogger(DbFunctions.class);

	private static String dateFunction;
	private static String dateFormat;

	public static String getDate(String dialect) {
		logger.info("Get date functions for DB : " + dialect);

		if(Objects.isNull(dialect)) {
			logger.warn("Dialect can't be null.");
			dateFunction = null;
		}
		
		if(Objects.isNull(dateFunction)) {
			if(dialect.toLowerCase().contains("oracle")) {
				dateFunction = "TO_CHAR";
			}else if (dialect.toLowerCase().contains("mysql")) {
				dateFunction = "STR_TO_DATE";
			}else if (dialect.toLowerCase().contains("H2")) {
				dateFunction = "PARSEDATETIME";
			}else {
				dateFunction = null;
			}
		}

		logger.info("Date functions for DB [" + dialect + "] is " + dateFunction);
		
		return dateFunction;
	}


	public static String getDate(String dialect,String otherParam) {
		logger.info("Get date functions for DB : " + dialect);
logger.info("----------------------------");
		if(Objects.isNull(dialect)) {
			logger.warn("Dialect can't be null.");
			dateFunction = null;
		}

		if(Objects.isNull(dateFunction)) {
			if(dialect.toLowerCase().contains("oracle")) {
				dateFunction = "TO_CHAR";
			}
			else if (dialect.toLowerCase().contains("mysql")) {
				dateFunction = "DATE";
			}
			else {
				dateFunction = null;
			}
		}

		logger.info("Date functions for DB [" + dialect + "] is " + dateFunction);

		return dateFunction;
	}

	public static String getDateFormat(String dialect) {
		logger.info("Get dateFormat functions for DB : " + dialect);

		if(Objects.isNull(dialect)) {
			logger.warn("Dialect can't be null.");
			dateFunction = null;
		}
		
		if(Objects.isNull(dateFormat)) {
			if(dialect.toLowerCase().contains("oracle")) {
				dateFormat = "YYYY-MM-DD";
			}else if (dialect.toLowerCase().contains("mysql")) {
				dateFormat = "%Y-%m-%d";
			}else {
				dateFormat = null;
			}
		}

		logger.info("DateFormat functions for DB : " + dialect + " is " + dateFormat);
		
		return dateFormat;
	}

}
