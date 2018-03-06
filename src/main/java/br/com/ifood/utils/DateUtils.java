package br.com.ifood.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DateUtils {

	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
	private static final Locale LOCALE = new Locale("pt", "BR");

	public static final String convertDateToString(Date data) {
		return convertDateToString(data, "dd/MM/yyyy");
	}

	public static final String convertDateToStringWithHours(Date data) {
		if (data == null)
			return "";
		return convertDateToString(data, "dd/MM/yyyy - HH:mm:ss");
	}

	public static final String convertDateToString(Date data, String format) {
		if (data == null || (format == null || format.isEmpty())) {
			return null;
		}
		return new SimpleDateFormat(format).format(data);
	}

	public static final Date convertStringToDate(String data) {
		if (data == null || data.isEmpty())
			return null;
		try {
			return convertStringToDate(data, "dd/MM/yyyy");
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static final Date convertStringToDate(String data, String format) throws ParseException {
		return new SimpleDateFormat(format).parse(data);
	}

	public static final boolean isValidDate(String date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Date getDateWithoutTime(Date date) {
		Calendar cal = Calendar.getInstance(LOCALE);
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	public static final Date getBeginDateFrom(Date date) {
		Calendar calendarIni = Calendar.getInstance(LOCALE);

		calendarIni.setTime(date);
		calendarIni.set(Calendar.HOUR_OF_DAY, calendarIni.getMinimum(Calendar.HOUR_OF_DAY));
		calendarIni.set(Calendar.MINUTE, calendarIni.getMinimum(Calendar.MINUTE));
		calendarIni.set(Calendar.SECOND, calendarIni.getMinimum(Calendar.SECOND));
		calendarIni.set(Calendar.MILLISECOND, calendarIni.getMinimum(Calendar.MILLISECOND));

		return calendarIni.getTime();
	}

	public static final Date getEndDateFrom(Date date) {
		Calendar calendarFim = Calendar.getInstance(LOCALE);

		calendarFim.setTime(date);
		calendarFim.set(Calendar.HOUR_OF_DAY, calendarFim.getMaximum(Calendar.HOUR_OF_DAY));
		calendarFim.set(Calendar.MINUTE, calendarFim.getMaximum(Calendar.MINUTE));
		calendarFim.set(Calendar.SECOND, calendarFim.getMaximum(Calendar.SECOND));
		calendarFim.set(Calendar.MILLISECOND, calendarFim.getMaximum(Calendar.MILLISECOND));

		return calendarFim.getTime();
	}

	public static final String getDataPorExtenso(Date data) {
		String meses[] = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" };

		Calendar calendario = Calendar.getInstance();
		calendario.setTime(data);

		StringBuilder builder = new StringBuilder();
		builder.append(calendario.get(Calendar.DAY_OF_MONTH));
		builder.append(" de ");
		builder.append(meses[calendario.get(Calendar.MONTH)]);
		builder.append(" de ");
		builder.append(calendario.get(Calendar.YEAR));

		return builder.toString();
	}

	public static int getYear(Date date) {
		return new Integer((convertDateToString(date, "yyyy").trim()));
	}

	public static Integer getCountDaysBetweenDates(Date d1, Date d2) {
		GregorianCalendar dataInicio = new GregorianCalendar();
		dataInicio.setTime(d1);
		dataInicio.set(Calendar.AM_PM, Calendar.AM);
		dataInicio.set(Calendar.HOUR, 0);
		dataInicio.set(Calendar.MINUTE, 0);
		dataInicio.set(Calendar.SECOND, 0);
		dataInicio.set(Calendar.MILLISECOND, 0);

		GregorianCalendar dataFim = new GregorianCalendar();
		dataFim.setTime(d2);
		dataFim.set(Calendar.AM_PM, Calendar.AM);
		dataFim.set(Calendar.HOUR, 0);
		dataFim.set(Calendar.MINUTE, 0);
		dataFim.set(Calendar.SECOND, 0);
		dataFim.set(Calendar.MILLISECOND, 0);

		if (dataFim.get(Calendar.YEAR) > dataInicio.get(Calendar.DAY_OF_YEAR)) {
			long diffMillis = dataFim.getTimeInMillis() - dataInicio.getTimeInMillis();
			long diffDays = diffMillis / (24 * 60 * 60 * 1000);
			return Long.valueOf(diffDays).intValue();
		}
		return 0;
	}

	public static Date getDate(int dia, int mes, int ano) {
		Calendar cal = Calendar.getInstance();
		cal.set(ano, (mes - 1), dia);
		return cal.getTime();
	}

	public static Date sqlDateToDate(java.sql.Date date) {
		return new Date(date.getTime());
	}

	public static Date addWeeksTo(Date date, int weeks) {
		Calendar cal = Calendar.getInstance(LOCALE);

		cal.setTime(date);
		cal.add(Calendar.WEEK_OF_MONTH, weeks);

		return cal.getTime();
	}

	public static Date addDaysTo(Date date, int days) {
		Calendar cal = Calendar.getInstance(LOCALE);

		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, days);

		return cal.getTime();
	}

	public static Date addHoursTo(Date date, int hours) {
		Calendar cal = Calendar.getInstance(LOCALE);

		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, hours);

		return cal.getTime();
	}

	public static Date addMinutesTo(Date date, int minutes) {
		Calendar cal = Calendar.getInstance(LOCALE);

		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutes);

		return cal.getTime();
	}

	public static Date addSecondsTo(Date date, int seconds) {
		Calendar cal = Calendar.getInstance(LOCALE);

		cal.setTime(date);
		cal.add(Calendar.SECOND, seconds);

		return cal.getTime();
	}

	public static Date clearHourMinuteAndSecondFrom(Date date) {
		Calendar cal = Calendar.getInstance(LOCALE);

		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);

		return cal.getTime();
	}

	public static Date getMaxHourMinuteAndSecondFrom(Date date) {
		Calendar cal = Calendar.getInstance(LOCALE);

		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);

		return cal.getTime();
	}

}
