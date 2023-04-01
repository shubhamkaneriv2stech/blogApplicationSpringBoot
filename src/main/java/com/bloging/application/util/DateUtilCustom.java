package com.bloging.application.util;



import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.time.zone.ZoneOffsetTransition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.Fraction;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * =========================Modification History======================= S.No. Date Name Comments 1. 30-Jun-2011 Nikhila#1 Ticket 198 Changes
 **/

/**
 * Date Utility Class This is used to convert Strings to Dates and Timestamps.
 * <p>
 * <a href="DateUtil.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by
 *         <a href="mailto:dan@getrolling.com">Dan Kibler </a> to correct time
 *         pattern. Minutes should be mm not MM (MM is month).
 * @version $Revision: 1.7.2.1 $ $Date: 2006-10-03 12:58:45 -0600 (Tue, 03 Oct
 *          2006) $
 */
public final class DateUtilCustom {

	private static final String FULL_TIME_FORMAT = "%02d:%02d";
	private static final String MINUTE_TIME_FORMAT = "00:%02d";

	public static final String DATE_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";

	public static final String DATE_PATTERN_DD = "dd";

	public static final String DATE_PATTERN_DAY_DD_MM = "E dd/MM";

	public static final String DATE_PATTERN_DAY = "E";

	/** No of seconds in minutes. */
	public static final Long SECONDS_IN_MINUTE = 60L;

	/** No of milliseconds in second. */
	public static final Long MILLISECONDS_IN_SECOND = 1000L;

	/** No of milliseconds in hour. */
	public static final Long MILLISECONDS_IN_HOUR = 3600000L;

	/** Default Date Pattern. */
	public static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";

	/** Default Date Pattern. */
	public static final String DEFAULT_TIME_PATTERN = "HH:mm";

	public static final String TIME_PATTERN_WITH_SINGLE_DOT = "HH.mm";

	/** Time Constant. */
	public static final long TIME_CONSTANT = 10;

	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(DateUtilCustom.class);

	/* Date time format */
	public static final String DATE_TIME_FORMAT = "dd/MM/yy hh:mm aaa";

	/** Default Date/time Pattern. */
	public static final String DEFAULT_DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm";

	/** Default Date/time Pattern. */
	public static final String ACTION_DATE_TIME_PATTERN = "dd-MM-yyyy - HH:mm";

	public static final String DASED_DATE_TIME_PATTERN = "dd-MM-yy - HH:mm";

	public static final String DATE_PATTERN_YYYY_M_DD = "yyyy-M-dd";

	public static final String DATE_PATTERN_YYYY_M_D = "yyyy-M-d";
	
	/** Date Pattern with dash. */
	public static final String DATE_PATTERN_WITH_DASH = "dd-MM-yyyy";

	public static final String DATETIME_PATTERN_WITH_DASH = "dd-MM-yyyy HH:mm";

	public static final String DATETIME_PATTERN_FOR_LOCALDATETIME = "dd-MM-yyyy HH:mm:ss.SSS";

	public static final String DATETIME_PATTERN_FOR_LOCALDATE = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final String START_TIME_OF_DAY = "00:00:00";

	public static final String TIME_START_OF_DAY_HH_MM = "00:00";

	public static final String TIME_END_OF_DAY_HH_MM = "23:00";

	public static final String END_TIME_OF_DAY = "23:59:59";

	public static final String DATE_PATTERN_DD_SLASH_MM = "dd/MM";

	public static final String DATE_TIME_PATTERN_DD_SLASH_MM = "dd/MM HH:mm";

	public static final String DATE_MONTH_PATTERN = "dd MMMM";

	public static final String DATE_MONTH_YEAR_PATTERN = "dd MMMM yyyy";

	public static final String NOON_12 = "12:00:00";

	public static final String DATE_FORMATTER_FOR_FILE_EXT = "dd-MM-yyyy_HHmm";

	public static final String DEFAULT_DATE_TIME_PATTERN_WITH_SECONDS = "dd/MM/yyyy HH:mm:ss";

	public static final String DATE_PATTERN_DD_MM = "dd-MM";

	public static final String DATETIME_PATTERN_WITH_SECONDS_AND_DASH = "dd-MM-yyyy HH:mm:ss";

	/**
	 * Private Constructor.
	 */
	private DateUtilCustom() {

	}

	// Methods
	// ================================================================

	/**
	 * Return default datePattern (dd/MM/yyyy).
	 *
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDatePattern() {
		return DateUtilCustom.DEFAULT_DATE_PATTERN;
	}

	/**
	 * This method attempts to convert an Oracle-formatted date in the form
	 * dd-MMM-yyyy to mm/dd/yyyy.
	 *
	 * @param aDate date from database as a string
	 * @return formatted string for the ui
	 */
	public static String getDate(final Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return returnValue;
	}

	/**
	 * This method attempts to convert an Oracle-formatted date in the form
	 * dd-MMM-yyyy to mm/dd/yyyy.
	 *
	 * @param aDate date from database as a string
	 * @param aPattern pattern for date conversion
	 * @return formatted string for the ui
	 */
	public static String getDate(final Date aDate, final String aPattern) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(aPattern);
			returnValue = df.format(aDate);
		}

		return returnValue;
	}
	/**
	 * This method generates a string representation of a date/time in the format
	 * you specify on input.
	 *
	 * @param aMask   the date pattern the string is in
	 * @param strDate a string representation of a date
	 * @return a converted Date object
	 * @throws ParseException the parse exception
	 * @see java.text.SimpleDateFormat
	 */
	public static Date convertStringToDate(final String aMask, final String strDate) throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		try {
			if (strDate != null) {
				date = df.parse(strDate);
			} else {
				return null;
			}
		} catch (final ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return date;
	}

	public static Time convertStringToTime(final String strTime) {
		return Time.valueOf(strTime + ":00");
	}

	/**
	 * This method generates a string representation of a date/time in the format
	 * you specify on input.If the date is not parsed it returns null
	 */
	public static Date convertStringToDateWithOutException(final String strDate, final String aMask) {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);
		try {
			if (strDate != null) {
				date = df.parse(strDate);
			}
		} catch (final Exception pe) {
			LOG.error("Date parsing exception expected format: ".concat(aMask).concat(" Date recieved :")
					.concat(strDate));
			return null;
		}
		return date;
	}

	public static Time convertStringToTimeWithOutException(final String strDate, final String aMask) {
		SimpleDateFormat df = null;
		if (StringUtils.isNotBlank(strDate) && StringUtils.isNotBlank(aMask)) {
			try {
				df = new SimpleDateFormat(aMask);
				return new Time(df.parse(strDate).getTime());
			} catch (final ParseException pe) {
				return null;
			}
		}
		return null;
	}

	/**
	 * This method returns the current date in the format: MM/dd/yyyy.
	 *
	 * @return the current date
	 * @throws ParseException the parse exception
	 */
	public static Calendar getToday() throws ParseException {
		final Date today = new Date();
		final SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		final String todayAsString = df.format(today);
		final Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * This method generates a string representation of a date's date/time in the
	 * format you specify on input.
	 *
	 * @param aMask the date pattern the string is in
	 * @param aDate a date object
	 * @return a formatted string representation of the date
	 * @see java.text.SimpleDateFormat
	 */
	public static String getDateTime(final String aMask, final Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			LOG.debug("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return returnValue;
	}

	/**
	 * This method generates a string representation of a date based on the System
	 * Property 'dateFormat' in the format you specify on input.
	 *
	 * @param aDate A date to convert
	 * @return a string representation of the date
	 */
	public static String convertDateToString(final Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	/**
	 * This method generates a string representation of a date based on the
	 * dateFormat in the format you specify on input.
	 *
	 * @param aDate      A date to convert
	 * @param dateFormat A date format to use for conversion
	 * @return a string representation of the date
	 */
	public static String convertDateToString(final Date aDate, final String dateFormat) {
		if (aDate == null || StringUtils.isBlank(dateFormat)) {
			return "";
		}
		return getDateTime(dateFormat, aDate);
	}

	/**
	 * This method generates a string representation of a date based on the System
	 * Property 'dateFormat' in the format you specify on input.
	 *
	 * @param aDate A date to convert
	 * @return a string representation of the date
	 */
	public static String convertTimeToString(final Date aDate) {
		return getDateTime(DEFAULT_TIME_PATTERN, aDate);
	}

	/**
	 * This method generates a string representation of a date based on the System
	 * Property 'dateFormat' in the format you specify on input.
	 *
	 * @param aDate A date to convert
	 * @return a string representation of the date
	 */
	public static String convertDatetimeToString(final Date aDate) {
		return getDateTime(DEFAULT_DATE_PATTERN + " " + DEFAULT_TIME_PATTERN, aDate);
	}

	/**
	 * This method generates string representation of date with timestamp using
	 * DATE_TIME_FORMAT
	 *
	 * @param aDate
	 * @return a string representation of the date with timestamp
	 */
	public static String convertDatetimeToStringWithTime(final Date aDate) {
		return getDateTime(DATE_TIME_FORMAT, aDate);
	}

	/**
	 * This method generates a string representation of a date based on the System
	 * Property 'dateFormat' in the format you specify on input.
	 *
	 * @param aDate           A date to convert
	 * @param dateTimePattern Date time pattern used for conversion
	 * @return a string representation of the date
	 */
	public static String convertDatetimeToString(final Date aDate, final String dateTimePattern) {
		if (aDate == null) {
			return "";
		}

		String convertedDate = getDateTime(dateTimePattern, aDate);
		return StringUtils.isNotBlank(convertedDate) ? convertedDate : "";
	}

	/**
	 * This method converts a String to a date using the datePattern.
	 *
	 * @param strDate the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 * @throws ParseException the parse exception
	 */
	public static Date convertStringToDate(final String strDate) throws ParseException {
		Date aDate = null;

		try {
			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (final ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());

		}

		return aDate;
	}

	/**
	 * Gets the diff between dates.
	 *
	 * @param fromDate the from date
	 * @param toDate   the to date
	 * @return the diff between dates
	 */
	public static long getDiffBetweenDates(final Date fromDate, final Date toDate) {
		return fromDate.getTime() - toDate.getTime();
	}

	/**
	 * Increments timestamp by 'x' no of days as provided in input.
	 *
	 * @param timetamp Timetamp to increment
	 * @param noOfDays no of days to incremented
	 * @return incremented timestamp
	 */
	public static Timestamp convertStringToTimestamp(final String date) {

		if (date == null) {
			return null;
		}

		Timestamp timestamp = null;
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("converting date with pattern: ".concat(getDatePattern()));
			}

			timestamp = new Timestamp(DateUtilCustom.convertStringToDate(date).getTime());

		} catch (NullPointerException | ParseException pe) {
			LOG.error("Error", pe);
		}

		return timestamp;
	}

	/**
	 * Returns current timestamp.
	 *
	 * @return current timestamp
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * Gets today's date as timestamp.
	 *
	 * @return today's date as timestamp
	 */
	public static Timestamp getTodayAsTimestamp() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		return convertStringToTimestamp(simpleDateFormat.format(new Date()));
	}

	/**
	 * Gets the duration between two dates/.
	 *
	 * @param fromDate the from date
	 * @param toDate   the to date
	 * @return the duration between dates
	 */
	public static long getDurationBetweenDates(final Date fromDate, final Date toDate) {
		long duration;

		if (fromDate != null && toDate != null) {
			final long startDate = toDate.getTime();
			final long endDate = fromDate.getTime();

			duration = endDate - startDate;

			duration = TimeUnit.HOURS.convert(duration, TimeUnit.MILLISECONDS);
			return duration;
		} else {
			return 0;
		}
	}

	/**
	 * returns a formatted string of am/pm.
	 *
	 * @param inputDate as inputDate
	 * @return string
	 **/
	public static String convertTimeInAmPmFormat(final Date inputDate) {
		final String strDateFormat = "h:mm aa";
		final SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		return sdf.format(inputDate);
	}

	/**
	 * This method is used to return a date in dateTime format.
	 *
	 * @param inputDate for Date
	 * @param inputHr   for hours
	 * @param inputMin  for Mins
	 * @return date time format
	 **/
	public static Date convertInDateTimeFormat(final Date inputDate, final Integer inputHr, final Integer inputMin) {
		Date outputDate = null;
		String hrs = null;
		String mins = null;
		try {
			if (inputDate != null && inputHr != null && inputMin != null) {
				if (inputHr.longValue() < TIME_CONSTANT) {
					hrs = "0" + inputHr.toString();
				} else {
					hrs = inputHr.toString();
				}
				if (inputMin.longValue() < TIME_CONSTANT) {
					mins = "0" + inputMin.toString();
				} else {
					mins = inputMin.toString();
				}
				final String concatenatedDate = inputDate.toString() + " " + hrs + ":" + mins + ":00";
				outputDate = convertStringToDate(DATE_PATTERN_YYYY_MM_DD + " HH:mm:ss", concatenatedDate);
			}
		} catch (final ParseException pe) {
			LOG.error("Error parsing date", pe);
		}

		return outputDate;
	}

	/***
	 * This method is used to convert time in milliseconds to mins.
	 *
	 * @param timeInMilliseconds as milliseconds
	 * @return mins in string format.
	 **/
	public static String convertTimeInMillisecondsToMins(final long timeInMilliseconds) {
		String timeInMins = null;

		timeInMins = String.format("%d min(s)", TimeUnit.MILLISECONDS.toMinutes(timeInMilliseconds));

		return timeInMins;
	}

	/**
	 * This method is used to convert the time in decimal format.
	 *
	 * @param timeInMills for mins to be converted.
	 * @return double for time in decimal
	 **/
	public static double getTimeInDecimalFormat(final long timeInMills) {
		final Fraction fraction = Fraction
				.getFraction(Long.toString(TimeUnit.MILLISECONDS.toMinutes(timeInMills)) + "/60");
		return fraction.doubleValue();
	}

	/**
	 * This method takes in a date and +ve difference as parameters and creates a
	 * new Date based on the difference.
	 *
	 * @param originalDate - Original date - This is the reference point from which
	 *                     a future date is to be calculated.
	 * @param difference   - The difference in number of days.
	 * @return - The newly created date
	 */
	public static Date populateDate(final Date originalDate, final int difference) {
		return new Date(originalDate.getTime() + (difference * 24 * 60 * 60 * 1000));
	}

	public static Time getTimeFromString(String time) {
		SimpleDateFormat df = null;
		Time t = null;
		if (time != null) {
			try {
				df = new SimpleDateFormat(DEFAULT_TIME_PATTERN);
				long ms = df.parse(time).getTime();
				t = new Time(ms);
			} catch (ParseException e) {
				LOG.error("Error parsing time.", e);
			}
		}
		return t;
	}

	public static String convertTimeToString(Time inputTime) {
		return new SimpleDateFormat(DEFAULT_TIME_PATTERN).format(inputTime);
	}

	/**
	 * This method will accept shift start time and end time and returns the shift
	 * duration in hours and minutes. For e.g. 1. If startTime = 09:00 and endTime =
	 * 17:00 then it will return 8 hours and 0 minutes 2. If startTime = 20:00 and
	 * endTime = 05:00 then it will return 9 hours and 0 minutes
	 *
	 * @param startTime
	 * @param endTime
	 * @return int array of hours and minutes
	 */
	public static int[] getHoursAndMinutes(org.joda.time.LocalTime startTime, org.joda.time.LocalTime endTime) {
		int[] hoursAndMinutesArr = { 0, 0 };
		if (startTime == null || endTime == null) {
			return hoursAndMinutesArr;
		}
		int timeStartHours = startTime.getHourOfDay();
		int timeEndHours = endTime.getHourOfDay();
		int timeStartMin = startTime.getMinuteOfHour();
		int timeEndMin = endTime.getMinuteOfHour();
		int hourDiff;
		int minuteDiff;

		if (timeStartHours == timeEndHours) {
			setDetailsIfBothAreSame(hoursAndMinutesArr, timeStartMin, timeEndMin);
		} else if (timeStartHours < timeEndHours) {
			hourDiff = timeEndHours - timeStartHours;
			if (timeStartMin < timeEndMin) {
				minuteDiff = timeEndMin - timeStartMin;
			} else if (timeStartMin == timeEndMin) {
				minuteDiff = 0;
			} else {
				minuteDiff = 60 - (timeStartMin - timeEndMin);
				hourDiff = hourDiff - 1;
			}
			hoursAndMinutesArr[0] = hourDiff;
			hoursAndMinutesArr[1] = minuteDiff;
		} else {
			hourDiff = (timeEndHours + 24) - timeStartHours;
			if (timeStartMin < timeEndMin) {
				minuteDiff = timeEndMin - timeStartMin;
			} else if (timeStartMin == timeEndMin) {
				minuteDiff = 0;
			} else {
				minuteDiff = 60 - (timeStartMin - timeEndMin);
				hourDiff = hourDiff - 1;
			}
			hoursAndMinutesArr[0] = hourDiff;
			hoursAndMinutesArr[1] = minuteDiff;
		}
		return hoursAndMinutesArr;
	}

	private static void setDetailsIfBothAreSame(int[] hoursAndMinutesArr, int timeStartMin, int timeEndMin) {
		int hourDiff;
		int minuteDiff;
		hourDiff = 0;
		if (timeStartMin < timeEndMin) {
			minuteDiff = timeEndMin - timeStartMin;
		} else if (timeStartMin == timeEndMin) {
			minuteDiff = 0;
		} else {
			minuteDiff = 60 - (timeStartMin - timeEndMin);
			hourDiff = 23;
		}
		hoursAndMinutesArr[0] = hourDiff;
		hoursAndMinutesArr[1] = minuteDiff;
	}

	public static String getDateInCustomFormat(Date startDate) {
		DateTime startDateTime = new DateTime(startDate);
		StringBuilder dateStr = new StringBuilder();
		dateStr.append(dateOrdinal(startDateTime.getDayOfMonth())).append(" ")
				.append(startDateTime.monthOfYear().getAsText());
		return dateStr.toString();
	}

	private static String dateOrdinal(int d) {

		if (31 == d || 21 == d || 1 == d) {
			return d + "<sup>st</sup>";
		} else if (22 == d || 2 == d) {
			return d + "<sup>nd</sup>";
		} else if (23 == d || 3 == d) {
			return d + "<sup>rd</sup>";
		} else {
			return d + "<sup>th</sup>";
		}
	}

	public static boolean isFutureDate(Date readingDate) {
		final Date today = new Date();
		return today.before(readingDate);
	}

	/**
	 * This method will accept java.time.LocalDateTime and returns java.util.Date.
	 *
	 * @param LocalDateTime
	 * @return java.util.Date
	 */
	public static Date convertLocalDateTimeToDate(LocalDateTime dateTime) {
		ZoneId zoneId = ZoneId.systemDefault();
		ZoneOffsetTransition zoneOffsetTransition = zoneId.getRules().getTransition(dateTime);
		if (null != zoneOffsetTransition && zoneOffsetTransition.isGap()) {
			LocalDateTime afterGap = zoneOffsetTransition.getDateTimeAfter();
			dateTime = afterGap;
		}
		return Date.from(dateTime.atZone(zoneId).toInstant());
	}

	/**
	 * This method will accept date in string and returns java.time.LocalDateTime.
	 *
	 * @param String
	 * @return java.time.LocalDateTime
	 */
	public static LocalDateTime convertStringToLocalDateTime(String dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN_FOR_LOCALDATE);
		return LocalDateTime.parse(dateTime, formatter);
	}

	public static LocalDate convertStringToLocalDate(String dateTime, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return LocalDate.parse(dateTime, formatter);
	}

	/**
	 * This method will accept date in java.util.Date and returns
	 * java.time.LocalDateTime.
	 *
	 * @param java.util.Date
	 * @return java.time.LocalDateTime
	 */
	public static LocalDateTime convertDateToLocalDateTime(Date dateToConvert) {
		if (dateToConvert != null) {
			return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		} else {
			return null;
		}
	}

	public static Date convertLocalDateToDate(LocalDate localDateToConvert) {
		return Date.from(localDateToConvert.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * This method will accept date in java.util.Date and returns
	 * java.time.LocalDate.
	 *
	 * @param java.util.Date
	 * @return java.time.LocalDate
	 */
	public static LocalDate convertDateToLocalDate(Date dateToConvert) {
		if (dateToConvert != null) {
			return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		} else {
			return null;
		}
	}

	public static LocalTime convertTimeToLocalTime(Time timeToConvert) {
		return Instant.ofEpochMilli(timeToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
	}

	public static LocalDateTime convertStringToLocalDateTime(String dateTime, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return LocalDateTime.parse(dateTime, formatter);
	}

	/**
	 * This method returns future date value by adding number of days in current
	 * date
	 * 
	 * @param date
	 * @param noOfDays
	 * @return
	 */
	public static Date getFutureDateFromCurrentDate(Date date, Integer noOfDays) {
		if (date != null && noOfDays != null) {
			LocalDate localDate = convertDateToLocalDate(date);
			return Date.from(localDate.plusDays(noOfDays).atStartOfDay(ZoneId.systemDefault()).toInstant());
		} else {
			return null;
		}
	}

	public static DateTime getFutureDateFromCurrentDateInDateTimeFormat(Date date, Integer noOfDays) {
		if (date != null && noOfDays != null) {
			LocalDate localDate = convertDateToLocalDate(date);
			return new DateTime(
					Date.from(localDate.plusDays(noOfDays).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		} else {
			return null;
		}
	}

	public static Date getPastDateFromCurrentDate(Date date, Integer noOfDays) {
		if (date != null && noOfDays != null) {
			LocalDate localDate = convertDateToLocalDate(date);
			return Date.from(localDate.minusDays(noOfDays).atStartOfDay(ZoneId.systemDefault()).toInstant());
		} else {
			return null;
		}
	}

	public static LocalDate convertStringToLocalDate(String dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN_WITH_DASH);
		return LocalDate.parse(dateTime, formatter);
	}

	public static String convertLocalDateToString(LocalDate date, String dateFormat) {
		return date.format(DateTimeFormatter.ofPattern(dateFormat));
	}

	public static String convertLocalDateTimeToString(LocalDateTime date, String dateFormat) {
		return date.format(DateTimeFormatter.ofPattern(dateFormat));
	}

	/**
	 * This method is use to merge date and time in a single unit
	 * 
	 * 
	 * @param dateObj
	 * @param timeObj
	 * @return Date
	 */
	public static Date getMergedDateTime(Date dateObj, Time timeObj) {

		if (dateObj != null && timeObj != null) {
			LocalDateTime dt = LocalDateTime.of(convertDateToLocalDate(dateObj), convertTimeToLocalTime(timeObj));
			return convertLocalDateTimeToDate(dt);
		} else {
			return null;
		}
	}

	public static LocalDate getNearestMondayLocalDate(Date selectedDate) {
		if (selectedDate != null) {
			LocalDate ld = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
			return ld;
		} else {
			return null;
		}
	}

	public static LocalDate getCuurentMondayLocalDateFromDate(Date selectedDate) {
		if (selectedDate != null) {
			LocalDate ld = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			ld = ld.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
			return ld;
		} else {
			return null;
		}
	}

	public static Date getNextMondayDate(Date selectedDate) {
		if (selectedDate != null) {
			LocalDate ld = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			ld = ld.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
			return convertLocalDateToDate(ld);
		} else {
			return null;
		}
	}

	public static Date getNearestMondayDate(Date selectedDate) {
		if (selectedDate != null) {
			LocalDate ld = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			ld = ld.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
			return convertLocalDateToDate(ld);
		} else {
			return null;
		}
	}

	/**
	 * This method will accept shift start time and end time and returns the shift
	 * duration in hours and minutes. For e.g. 1. If startTime = 09:00 and endTime =
	 * 17:00 then it will return 8 hours and 0 minutes 2. If startTime = 20:00 and
	 * endTime = 05:00 then it will return 9 hours and 0 minutes
	 *
	 * @param startTime
	 * @param endTime
	 * @return int array of hours and minutes
	 */
	public static int[] getHoursAndMinutes(LocalTime startTime, LocalTime endTime) {
		int[] hoursAndMinutesArr = { 0, 0 };
		if (startTime == null || endTime == null) {
			return hoursAndMinutesArr;
		}
		int timeStartHours = startTime.getHour();
		int timeEndHours = endTime.getHour();
		int timeStartMin = startTime.getMinute();
		int timeEndMin = endTime.getMinute();
		Integer hourDiff = null;
		Integer minuteDiff = null;

		if (timeStartHours == timeEndHours) {
			setIfBothAreSame(hoursAndMinutesArr, timeStartMin, timeEndMin);

		} else if (timeStartHours < timeEndHours) {
			setDetailsIfStartIsEarly(hoursAndMinutesArr, timeStartHours, timeEndHours, timeStartMin, timeEndMin);
		} else {
			hourDiff = (timeEndHours + 24) - timeStartHours;
			if (timeStartMin < timeEndMin) {
				minuteDiff = timeEndMin - timeStartMin;
			} else if (timeStartMin == timeEndMin) {
				minuteDiff = 0;
			} else {
				minuteDiff = 60 - (timeStartMin - timeEndMin);
				hourDiff = hourDiff - 1;
			}
			hoursAndMinutesArr[0] = hourDiff;
			hoursAndMinutesArr[1] = minuteDiff;
		}
		return hoursAndMinutesArr;
	}

	private static void setDetailsIfStartIsEarly(int[] hoursAndMinutesArr, int timeStartHours, int timeEndHours,
			int timeStartMin, int timeEndMin) {
		Integer hourDiff;
		Integer minuteDiff;
		hourDiff = timeEndHours - timeStartHours;
		if (timeStartMin < timeEndMin) {
			minuteDiff = timeEndMin - timeStartMin;
		} else if (timeStartMin == timeEndMin) {
			minuteDiff = 0;
		} else {
			minuteDiff = 60 - (timeStartMin - timeEndMin);
			hourDiff = hourDiff - 1;
		}
		hoursAndMinutesArr[0] = hourDiff;
		hoursAndMinutesArr[1] = minuteDiff;
	}

	private static void setIfBothAreSame(int[] hoursAndMinutesArr, int timeStartMin, int timeEndMin) {
		Integer hourDiff;
		Integer minuteDiff;
		hourDiff = 0;
		if (timeStartMin < timeEndMin) {
			minuteDiff = timeEndMin - timeStartMin;
		} else if (timeStartMin == timeEndMin) {
			minuteDiff = 0;
		} else {
			minuteDiff = 60 - (timeStartMin - timeEndMin);
			hourDiff = 23;
		}
		hoursAndMinutesArr[0] = hourDiff;
		hoursAndMinutesArr[1] = minuteDiff;
	}

	public static DayOfWeek getDayFromDate(Date dateObj) {
		if (dateObj == null) {
			return null;
		}
		return DateUtilCustom.convertDateToLocalDate(dateObj).getDayOfWeek();
	}

	public static Date mergeDateTime(Date dateObj, Time timeObj) {
		try {
			String startingDate = new SimpleDateFormat(DEFAULT_DATE_PATTERN).format(dateObj);
			String startingTime = new SimpleDateFormat(DEFAULT_TIME_PATTERN).format(timeObj);
			return convertStringToDate(DEFAULT_DATE_TIME_PATTERN, (startingDate + " " + startingTime));
		} catch (Exception e) {
			return null;
		}

	}

	public static Date mergeDateTime(Date dateObj, Date timeObj) {
		try {
			String startingDate = new SimpleDateFormat(DEFAULT_DATE_PATTERN).format(dateObj);
			String startingTime = new SimpleDateFormat(DEFAULT_TIME_PATTERN).format(timeObj);
			return convertStringToDate(DEFAULT_DATE_TIME_PATTERN, (startingDate + " " + startingTime));
		} catch (Exception e) {
			return null;
		}

	}

	public static Time convertDateToTime(Date dateObj) {
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
		return Time.valueOf(localDateFormat.format(dateObj));
	}

	public static Long getMinutesBetween2Dates(Date dateTime1, Date dateTime2) {

		if (dateTime1 == null || dateTime2 == null) {
			return Long.valueOf(0);
		}

		return Duration.between(convertDateToLocalDateTime(dateTime1), convertDateToLocalDateTime(dateTime2))
				.toMinutes();
	}

	public static Long getMinutesBetweenTwoTimeObj(Time dateTime1, Time dateTime2) {
		if (dateTime1 != null && dateTime2 != null) {
			return (dateTime2.getTime() - dateTime1.getTime()) / (60 * 1000);
		} else {
			return null;
		}
	}

	public static Integer getWeekNumberByDate(Date requestDate) {
		if (requestDate != null) {
			LocalDate date = convertDateToLocalDate(requestDate);
			// Or use a specific locale, or configure your own rules
			WeekFields weekFields = WeekFields.of(Locale.getDefault());
			return date.get(weekFields.weekOfWeekBasedYear());
		}
		return null;
	}

	public static Integer getYearByDate(Date requestDate) {
		if (requestDate != null) {
			return convertDateToLocalDate(requestDate).getYear();
		}
		return null;
	}

	public static Integer getDayNumberByDateConsideringMondayAsFirst(Date requestDate) {

		DayOfWeek dow = getDayFromDate(requestDate);
		if (DayOfWeek.SUNDAY.compareTo(dow) == 0) {
			return 7;
		} else if (DayOfWeek.MONDAY.compareTo(dow) == 0) {
			return 1;
		} else if (DayOfWeek.TUESDAY.compareTo(dow) == 0) {
			return 2;
		} else if (DayOfWeek.WEDNESDAY.compareTo(dow) == 0) {
			return 3;
		} else if (DayOfWeek.THURSDAY.compareTo(dow) == 0) {
			return 4;
		} else if (DayOfWeek.FRIDAY.compareTo(dow) == 0) {
			return 5;
		} else if (DayOfWeek.SATURDAY.compareTo(dow) == 0) {
			return 6;
		} else {
			return null;
		}
	}

	public static Long getHoursBetween2Dates(Date dateTime1, Date dateTime2) {
		return Duration.between(convertDateToLocalDateTime(dateTime1), convertDateToLocalDateTime(dateTime2)).toHours();
	}

	public static java.math.BigDecimal getBigDecimalHoursBetween2Dates(Date dateTime1, Date dateTime2) {
		return BigDecimal.valueOf(Duration
				.between(convertDateToLocalDateTime(dateTime1), convertDateToLocalDateTime(dateTime2)).toHours());
	}

	public static List<Date> getMondayWeekDatesInMonth(Date dateToConvert) {

		LocalDate localdate = convertDateToLocalDate(dateToConvert);
		if (localdate != null) {
			YearMonth yearMonthObj = YearMonth.of(localdate.getYear(), localdate.getMonthValue());
			YearMonth testYearMonth = yearMonthObj;

			List<Date> firstDaysOfWeeks = new ArrayList<>();
			for (LocalDate ld = firstDayOfCalendar(yearMonthObj); stillInCalendar(yearMonthObj,
					ld); ld = ld.plusWeeks(1)) {
				if (testYearMonth.compareTo(YearMonth.of(ld.getYear(), ld.getMonthValue())) == 0) {
					firstDaysOfWeeks.add(convertLocalDateToDate(ld));
				}
			}
			return firstDaysOfWeeks;
		}
		return Collections.emptyList();
	}

	private static LocalDate firstDayOfCalendar(YearMonth month) {
		DayOfWeek firstDayOfWeek = DayOfWeek.MONDAY;
		return month.atDay(1).with(firstDayOfWeek);
	}

	private static boolean stillInCalendar(YearMonth yearMonth, LocalDate day) {
		return !day.isAfter(yearMonth.atEndOfMonth());
	}

	/**
	 * This method will return minutes between 2 times.
	 *
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static Integer getMinutesBetweenTime(Time startTime, Time endTime) {
		try {
			int[] hoursAndMinutesArr = getHoursAndMinutes(startTime.toLocalTime(), endTime.toLocalTime());
			return hoursAndMinutesArr[0] * 60 + hoursAndMinutesArr[1];
		} catch (Exception e) {
			return 0;
		}
	}

	public static List<Date> getListOfDatesBtwnTwoDate(Date startDate, Date endDate) {
		List<Date> response = new ArrayList<>();
		LocalDate startDateld = convertDateToLocalDate(startDate);
		LocalDate endDateld = convertDateToLocalDate(endDate);
		if (startDateld != null && endDateld != null) {
			while (!startDateld.isAfter(endDateld)) {
				response.add(convertLocalDateToDate(startDateld));
				startDateld = startDateld.plusDays(1);
			}
		}
		return response;
	}

	public static Date getEndDateWithTime(Date dateObj) {
		return DateUtilCustom.mergeDateTime(dateObj, Time.valueOf(END_TIME_OF_DAY));
	}

	public static List<LocalDateTime> getWeekDays(Date startDate) {
		List<LocalDateTime> datesArr = new ArrayList<>();
		LocalDate now = startDate != null ? DateUtilCustom.convertDateToLocalDate(startDate) : LocalDate.now();
		datesArr.add(now.with(DayOfWeek.MONDAY).atStartOfDay());
		datesArr.add(now.with(DayOfWeek.TUESDAY).atStartOfDay());
		datesArr.add(now.with(DayOfWeek.WEDNESDAY).atStartOfDay());
		datesArr.add(now.with(DayOfWeek.THURSDAY).atStartOfDay());
		datesArr.add(now.with(DayOfWeek.FRIDAY).atStartOfDay());
		datesArr.add(now.with(DayOfWeek.SATURDAY).atStartOfDay());
		datesArr.add(now.with(DayOfWeek.SUNDAY).atStartOfDay());
		return datesArr;
	}

	public static Date getFirstDateOfTheMonth(Date request) {
		if (request == null) {
			return null;
		}
		return convertLocalDateToDate(convertDateToLocalDate(request).withDayOfMonth(1));
	}

	public static Date getLastDateOfTheMonth(Date request) {
		LocalDate requestedDate = convertDateToLocalDate(request);
		if (requestedDate != null) {
			LocalDate lastDateOfMonth = requestedDate.withDayOfMonth(requestedDate.lengthOfMonth());
			return convertLocalDateToDate(lastDateOfMonth);
		}
		return null;
	}

	public static boolean isDateisForMonday(Date dateToBeTested) {
		LocalDate ld = convertDateToLocalDate(dateToBeTested);

		return ld != null ? (ld.getDayOfWeek().compareTo(DayOfWeek.MONDAY) == 0) : null;
	}

	public static boolean isDateisForSunday(Date dateToBeTested) {
		LocalDate ld = convertDateToLocalDate(dateToBeTested);

		return ld != null ? (ld.getDayOfWeek().compareTo(DayOfWeek.SUNDAY) == 0) : null;
	}

	public static Date getDateOfComingSunday(Date requestDate) {
		if (requestDate != null) {
			LocalDate ld = requestDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
			return convertLocalDateToDate(ld);
		} else {
			return null;
		}
	}

	public static List<List<Date>> getWeekDatesBrokenInWeekSlotsOnlyConstrainMondayToSunday(Date startDate,
			Date endDate) {
		List<List<Date>> response = new ArrayList<>();

		for (int i = 0; i < 50; i++) {
			Date dateAfterNext7Days = getFutureDateFromCurrentDate(startDate, 6);
			if (dateAfterNext7Days != null && dateAfterNext7Days.compareTo(endDate) > 0) {
				break;
			}
			response.add(getListOfDatesBtwnTwoDate(startDate, dateAfterNext7Days));
			startDate = getFutureDateFromCurrentDate(dateAfterNext7Days, 1);
		}
		return response;
	}

	public static List<List<Date>> getWeekDatesBrokenInWeekSlots(Date startDate, Date endDate) {
		List<List<Date>> response = new ArrayList<>();

		List<Date> singleWeek = new ArrayList<>();
		int counter = 0;

		while (true) {
			Date dateAfterNext7Days = getFutureDateFromCurrentDate(startDate, counter++);
			if ((dateAfterNext7Days != null && dateAfterNext7Days.compareTo(endDate) > 0) || counter == 365) {
				response.add(singleWeek);
				break;
			}
			singleWeek.add(dateAfterNext7Days);

			if (singleWeek.size() > 6 || isDateisForSunday(dateAfterNext7Days)) {
				response.add(singleWeek);
				singleWeek = new ArrayList<>();
			}
		}
		return response;

	}

	public static String getDayByDate(Date requestDate) {
		if (requestDate == null) {
			return null;
		}
		return convertDateToLocalDate(requestDate).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	}

	public static String getDayByDateInShortFormat(Date requestDate) {
		if (requestDate == null) {
			return null;
		}
		return convertDateToLocalDate(requestDate).getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
	}

	public static String getMonthByDate(Date requestDate) {
		if (requestDate == null) {
			return null;
		}
		return convertDateToLocalDate(requestDate).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	}

	public static Time getStartTimeOfTheDay() {
		return Time.valueOf(START_TIME_OF_DAY);
	}

	public static boolean isStartTimeOfTheDay(Time requestTime) {
		return (requestTime != null && requestTime.compareTo(getStartTimeOfTheDay()) == 0);
	}

	public static Date getIncrementedDateTimeByMinute(Date requestDate, Integer incrementTimeValue) {
		if (requestDate != null) {
			return convertLocalDateTimeToDate(convertDateToLocalDateTime(requestDate).plusMinutes(incrementTimeValue));
		} else {
			return null;
		}
	}
	
	public static Date getIncrementedDateTimeByMonths(Date requestDate, Integer incrementMonthValue) {
		if (requestDate != null) {
			return convertLocalDateTimeToDate(convertDateToLocalDateTime(requestDate).plusMonths(incrementMonthValue));
		} else {
			return null;
		}
	}

	public static Date getIncrementedDateTimeByMinute(Date requestDate, Long incrementTimeValue) {
		if (requestDate != null) {
			return convertLocalDateTimeToDate(convertDateToLocalDateTime(requestDate).plusMinutes(incrementTimeValue));
		} else {
			return null;
		}
	}

	public static Date getIncrementedDateTimeByHours(Date requestDate, Integer incrementTimeValue) {
		if (requestDate != null) {
			return convertLocalDateTimeToDate(convertDateToLocalDateTime(requestDate).plusHours(incrementTimeValue));
		} else {
			return null;
		}
	}

	public static Date getIncrementedDateTimeByDay(Date requestDate, Integer incrementDayValue) {
		if (requestDate != null) {
			return convertLocalDateTimeToDate(convertDateToLocalDateTime(requestDate).plusDays(incrementDayValue));
		} else {
			return null;
		}
	}
	
	public static Time getIncrementedTimeByMinute(Time requestDate, Long incrementTimeValue) {
		if (requestDate != null) {
			return Time.valueOf(requestDate.toLocalTime().plusMinutes(incrementTimeValue));
		} else {
			return null;
		}
	}

	public static Time getIncrementedTimeByMinute(Time requestDate, Integer incrementTimeValue) {
		if (requestDate != null) {
			return Time.valueOf(requestDate.toLocalTime().plusMinutes(incrementTimeValue));
		} else {
			return null;
		}
	}

	public static Date getIncrementedTimeByMinute(Date requestDate, Long incrementTimeValue) {

		if (requestDate != null) {
			return convertLocalDateTimeToDate(convertDateToLocalDateTime(requestDate).plusMinutes(incrementTimeValue));
		} else {
			return null;
		}
	}

	public static Time getDecrementedTimeByMinute(Time requestDate, Integer decrementMinuteValue) {
		if (requestDate != null) {
			return Time.valueOf(requestDate.toLocalTime().minusMinutes(decrementMinuteValue));
		} else {
			return null;
		}
	}

	public static Time getDecrementedTimeByMinute(Time requestDate, Long decrementMinuteValue) {
		if (requestDate != null) {
			return Time.valueOf(requestDate.toLocalTime().minusMinutes(decrementMinuteValue));
		} else {
			return null;
		}
	}

	public static Date getDecrementedDateTimeByDay(Date requestDate, Integer decrementDayValue) {
		if (requestDate != null) {
			return convertLocalDateTimeToDate(convertDateToLocalDateTime(requestDate).minusDays(decrementDayValue));
		} else {
			return null;
		}
	}
	
	public static Date getDecrementedDateByMinute(Date requestDate, Integer decrementMinuteValue) {
		if (requestDate != null) {
			return convertLocalDateTimeToDate(
					convertDateToLocalDateTime(requestDate).minusMinutes(decrementMinuteValue));
		} else {
			return null;
		}
	}

	public static Date getDecrementedDateByHours(Date requestDate, Integer decrementMinuteValue) {
		if (requestDate != null) {
			return convertLocalDateTimeToDate(convertDateToLocalDateTime(requestDate).minusHours(decrementMinuteValue));
		} else {
			return null;
		}
	}

	public static Date getDecrementedDateByMinute(Date requestDate, Long decrementMinuteValue) {
		if (requestDate != null) {
			return convertLocalDateTimeToDate(
					convertDateToLocalDateTime(requestDate).minusMinutes(decrementMinuteValue));
		} else {
			return null;
		}
	}

	public static Date getStartDateTimeOfDate(Date requestDate) {
		if (requestDate != null) {
			return DateUtilCustom.mergeDateTime(requestDate, DateUtilCustom.getStartTimeOfTheDay());
		} else {
			return null;
		}
	}

	public static boolean isGreaterOrEqual(Date first, Date second) {
		return first.compareTo(second) > -1;
	}

	public static boolean isGreater(Date first, Date second) {
		return first.compareTo(second) > 0;
	}

	public static boolean isSmallerOrEqual(Date first, Date second) {
		return first.compareTo(second) < 1;
	}

	public static boolean isSmaller(Date first, Date second) {
		return first.compareTo(second) < 0;
	}

	public static Long getMinutesBetweenTwoTimeObject(Time dateTime1, Time dateTime2) {
		if (dateTime1 != null && dateTime2 != null) {
			if (dateTime2.getTime() > dateTime1.getTime()) {
				return (dateTime2.getTime() - dateTime1.getTime()) / (60 * 1000);
			} else {
				return (dateTime1.getTime() - dateTime2.getTime()) / (60 * 1000);
			}
		} else {
			return null;
		}
	}

	public static Time getIncrementedTimeByTimeString(Time requestedTime, String time) {
		if (time != null && requestedTime != null) {
			String[] parts = time.split(":");
			Duration d = Duration.ZERO;
			if (parts.length == 3) {
				int hours = Integer.parseInt(parts[0]);
				int minutes = Integer.parseInt(parts[1]);
				int seconds = Integer.parseInt(parts[2]);
				d = d.plusHours(hours).plusMinutes(minutes).plusSeconds(seconds);
			} else if (parts.length == 2) {
				int hours = Integer.parseInt(parts[0]);
				int minutes = Integer.parseInt(parts[1]);
				d = d.plusHours(hours).plusMinutes(minutes);
			} else {
				LOG.error("ERROR - Unexpected input");
			}
			return Time.valueOf(requestedTime.toLocalTime().plusSeconds(d.getSeconds()));
		}
		return null;
	}

	public static boolean isItTodaysDate(Date requestDate) {
		Date start = DateUtilCustom.getStartDateTimeOfDate(requestDate);
		return (requestDate != null && start != null
				&& start.compareTo(DateUtilCustom.getStartDateTimeOfDate(new Date())) == 0);
	}

	public static List<Date> getFewMondayDatesIncludingCurrentWeek(Date requestedDate, Integer countOfModayDates) {

		List<Date> response = new ArrayList<>();
		Date currentWeekMondayDate = DateUtilCustom.getNearestMondayDate(requestedDate);

		response.add(currentWeekMondayDate);
		for (int i = 1; i < countOfModayDates; i++) {
			response.add(getFutureDateFromCurrentDate(currentWeekMondayDate, i * 7));
		}
		return response;
	}

	public static String secondToFullTime(long second) {
		return timeUnitToFullTime(second, TimeUnit.SECONDS);
	}

	public static String timeUnitToFullTime(long time, TimeUnit timeUnit) {
		long hour = timeUnit.toHours(time) % 24;
		long minute = timeUnit.toMinutes(time) % 60;
		long second = timeUnit.toSeconds(time) % 60;
		if (hour > 0) {
			return String.format(FULL_TIME_FORMAT, hour, minute);
		} else {
			return String.format(FULL_TIME_FORMAT, minute, second);
		}
	}

	public static List<Date> getWeekDayorWeekEndList(List<Date> request, boolean weekDay) {

		List<Date> weekDayResponse = new ArrayList<>();
		List<Date> weekEndResponse = new ArrayList<>();

		for (Date date : request) {

			LocalDate ld = convertDateToLocalDate(date);
			DayOfWeek day = ld != null ? DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK)) : null;

			if (day != null) {
				if (day.getValue() != 6 && day.getValue() != 7) {
					weekDayResponse.add(date);
				} else {
					weekEndResponse.add(date);
				}
			}
		}
		return weekDay ? weekDayResponse : weekEndResponse;
	}

	public static Long convertTimeToMinutes(Time minutes) {
		if (minutes != null) {
			LocalTime localTime = convertTimeToLocalTime(minutes);
			return Long.valueOf((localTime.getHour() * 60) + localTime.getMinute());
		}
		return Long.valueOf(0);
	}

	public static BigDecimal convertMinutesIntoHours(Long minutes) {
		if (minutes != null) {
			BigDecimal bigSixty = BigDecimal.valueOf(60);
			BigDecimal bigHundred = BigDecimal.valueOf(100);
			BigDecimal hrs = BigDecimal.valueOf(minutes).divide(BigDecimal.valueOf(60), 0, RoundingMode.DOWN);
			BigDecimal min = (BigDecimal.valueOf(minutes).remainder(bigSixty)).divide(bigHundred).setScale(2,
					RoundingMode.HALF_UP);
			return hrs.add(min);
		}
		return BigDecimal.ZERO;
	}

	public static Long convertHoursIntoMinutes(BigDecimal hoursAndMinutes) {
		Long totalMin = 0L;
		BigDecimal bigSixty = BigDecimal.valueOf(60);
		if (null != hoursAndMinutes) {
			String str = String.valueOf(hoursAndMinutes);
			if (str.contains(".")) {
				String[] arr = str.split("\\.");
				if (arr.length > 0) {
					totalMin = (new BigDecimal(arr[0]).multiply(bigSixty).add(new BigDecimal(arr[1]))).longValue();
					if (StringUtils.isNotBlank(arr[1]) && Integer.parseInt(arr[1]) < 9) {
						totalMin = (new BigDecimal(arr[0]).multiply(bigSixty)
								.add(new BigDecimal(arr[1]).multiply(new BigDecimal(10)))).longValue();
					} else {
						totalMin = (new BigDecimal(arr[0]).multiply(bigSixty).add(new BigDecimal(arr[1]))).longValue();
					}
				}
			} else {
				totalMin = hoursAndMinutes.multiply(bigSixty).longValue();
			}
		}
		return totalMin;
	}

	public static boolean isDateInBetween(Date startDate, Date endDate, Date requestedDate) {
		return DateUtilCustom.isSmallerOrEqual(startDate, requestedDate) && DateUtilCustom.isGreater(endDate, requestedDate);
	}

	public static Time convertMinutesToTime(Long duration) {
		if (duration != null && duration > 0) {
			int minutes = duration.intValue();
			int hour = minutes / 60;
			int min = minutes % 60;
			LocalDate defNow = LocalDate.now();
			LocalDateTime dateTime = defNow.atTime(hour, min);
			Date defDate = DateUtilCustom.convertLocalDateTimeToDate(dateTime);
			return new Time(defDate.getTime());
		} else {
			return getTimeFromString("00:00");
		}
	}
	
	public static String minutesToFullTime(long minutes) {
		return timeMinuteUnitToFullTime(minutes, TimeUnit.SECONDS);
	}
	
	public static String timeMinuteUnitToFullTime(long time, TimeUnit timeUnit) {
		long hour = timeUnit.toHours(time) % 24;
		long minute = timeUnit.toMinutes(time) % 60;
		if (hour > 0) {
			return String.format(FULL_TIME_FORMAT, hour, minute);
		} else {
			return String.format(MINUTE_TIME_FORMAT, minute, minute);
		}
	}
	
	public static String convertMinutesIntoHoursWithColon(Long minutes) {
		return convertMinutesIntoHours(minutes).toString().replace(".", ":");
	}
	
	public static String convertMinutesIntoHours(BigDecimal minutes1, Long minutes2) {
		String time = "00:00";
		if (minutes1 != null) {
			BigDecimal bigSixty = BigDecimal.valueOf(60);
			BigDecimal bigHundred = BigDecimal.valueOf(100);
			BigDecimal hrs = minutes1.divide(BigDecimal.valueOf(60), 0, RoundingMode.DOWN);
			BigDecimal min = (minutes1.remainder(bigSixty)).divide(bigHundred).setScale(2, RoundingMode.HALF_UP);
			time = hrs.add(min).toString();
			if(time.contains(".")) {
				String [] arr = time.split("\\.");
				int hr = Integer.parseInt(arr[0]);
				if(hr < 0) {
					if (hr > -10) {
						time = "-0" + (hr*-1) + ":" + arr[1];
					} else {
						time = hr + ":" + arr[1];
					} 
				}else if(hr >= 0 && hr < 10) {
					time = "0" + hr + ":" + arr[1];
				}else {
					time = time.replace(".", ":");
				}
			}
		}else if (minutes2 != null) {
			BigDecimal bigSixty = BigDecimal.valueOf(60);
			BigDecimal bigHundred = BigDecimal.valueOf(100);
			BigDecimal hrs = BigDecimal.valueOf(minutes2).divide(BigDecimal.valueOf(60), 0, RoundingMode.DOWN);
			BigDecimal min = (BigDecimal.valueOf(minutes2).remainder(bigSixty)).divide(bigHundred).setScale(2,
					RoundingMode.HALF_UP);
			time = hrs.add(min).toString();
			if(time.contains(".")) {
				String [] arr = time.split("\\.");
				int hr = Integer.parseInt(arr[0]);
				if(hr < 0) {
					if (hr > -10) {
						time = "-0" + (hr*-1) + ":" + arr[1];
					} else {
						time = hr + ":" + arr[1];
					} 
				}else if(hr >= 0 && hr < 10) {
					time = "0" + hr + ":" + arr[1];
				}else {
					time = time.replace(".", ":");
				}
			}
		}
		return time;
	}
	
	public static BigDecimal convertMinutesIntoHoursInDecimal(BigDecimal minutes1, Long minutes2) {
		if (minutes1 != null) {
			BigDecimal bigSixty = BigDecimal.valueOf(60);
			BigDecimal bigHundred = BigDecimal.valueOf(100);
			BigDecimal hrs = minutes1.divide(BigDecimal.valueOf(60), 0, RoundingMode.DOWN);
			BigDecimal min = (minutes1.remainder(bigSixty)).divide(bigHundred).setScale(2, RoundingMode.HALF_UP);
			return hrs.add(min);
		}else if (minutes2 != null) {
			BigDecimal bigSixty = BigDecimal.valueOf(60);
			BigDecimal bigHundred = BigDecimal.valueOf(100);
			BigDecimal hrs = BigDecimal.valueOf(minutes2).divide(BigDecimal.valueOf(60), 0, RoundingMode.DOWN);
			BigDecimal min = (BigDecimal.valueOf(minutes2).remainder(bigSixty)).divide(bigHundred).setScale(2,
					RoundingMode.HALF_UP);
			return hrs.add(min);
		}
		return BigDecimal.ZERO;
	}
	
	public static Date getDecrementedDateTimeByMonth(Date requestDate, Integer decrementMonthValue) {
		if (requestDate != null) {
			return convertLocalDateTimeToDate(convertDateToLocalDateTime(requestDate).minusMonths(decrementMonthValue));
		} else {
			return null;
		}
	}

	public static long getDurationInMinBetweenDates(LocalDateTime startTime, LocalDateTime endTime) {
		Duration duration = Duration.between(startTime, endTime);
		return Math.abs(duration.toMinutes());
	}
	
	public static long getDaysBetween2LoalDates(LocalDate startDate, LocalDate endDate) {
		return ChronoUnit.DAYS.between(startDate, endDate);
	}
	
	public static int calculateAge(Date dob) {
		if (dob != null) {
			LocalDate dateOfBirth = convertDateToLocalDate(dob);
			LocalDate curDate = LocalDate.now();
			return Period.between(dateOfBirth, curDate).getYears();
		} else {
			return 0;
		}
	}

	public static String convertHoursIntoHours(BigDecimal hour) {
		String time = "00:00";
		if (hour != null) {
			time = hour.toString();
			if(time.contains(".")) {
				String [] arr = time.split("\\.");
				BigDecimal actualHrs = new BigDecimal(arr[0]);
				BigDecimal actualMin = new BigDecimal(arr[1]);
				BigDecimal bigSixty = BigDecimal.valueOf(60);
				if(actualMin.compareTo(bigSixty) > 0) {
					BigDecimal hrs = actualMin.divide(bigSixty, 0, RoundingMode.DOWN);
					actualMin = (actualMin.remainder(bigSixty)).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);
					actualHrs = actualHrs.add(hrs);
					time = actualHrs.add(actualMin).toString();
				}
				if(actualHrs.compareTo(BigDecimal.valueOf(0)) >= 0 && actualHrs.compareTo(BigDecimal.valueOf(10)) <= -1) {
					time = "0" + time.replace(".", ":");
				}else {
					time = time.replace(".", ":");
				}
			}
		}
		return time;
	}
	
	public static String convertToDaysHoursMinutes(long minutes) {
		int day = (int) TimeUnit.MINUTES.toDays(minutes);
		long hours = TimeUnit.MINUTES.toHours(minutes) - (day * 24);
		long minute = TimeUnit.MINUTES.toMinutes(minutes) - (TimeUnit.MINUTES.toHours(minutes) * 60);
		String result = "";
		if (day != 0) {
			result += day;
			if (day == 1) {
				result += " day" + (hours != 0 || minute != 0 ? ", " : " ");
			} else {
				result += " days" + (hours != 0 || minute != 0 ? ", " : " ");
			}
		}
		if (hours != 0) {
			result += hours;
			if (hours == 1) {
				result += " hr" + (minute != 0 ? ", " : " ");
			} else {
				result += " hrs" + (minute != 0 ? ", " : " ");
			}
		}
		if (minute != 0) {
			result += minute;
			if (minute == 1) {
				result += " min";
			} else {
				result += " mins";
			}
		}
		return result;
	}

	public static BigDecimal getHoursBetweenDates(Date dateTime1, Date dateTime2) {
		Long minute = Duration.between(convertDateToLocalDateTime(dateTime1), convertDateToLocalDateTime(dateTime2))
				.toMinutes();
		return convertMinutesIntoHoursInDecimal(null, minute);
	}

	public static Date getIncrementedDateTimeByYears(Date requestDate, Integer incrementMonthValue) {
		if (requestDate != null) {
			return convertLocalDateTimeToDate(convertDateToLocalDateTime(requestDate).plusYears(incrementMonthValue));
		} else {
			return null;
		}
	}
}