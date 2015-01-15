package com.agnie.gwt.bootstrap.proto.admin.client.timezone;

import java.io.Serializable;

import com.google.gwt.core.client.JsDate;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Timer;

public class AgnieDate implements Cloneable, Comparable<AgnieDate>, Serializable {

	private static final long	serialVersionUID	= 1L;
	private static String		timeZoneId			= "";
	private EventBus			eventBus			= new SimpleEventBus();

	/**
	 * Encapsulates static data to avoid Date itself having a static initializer.
	 */
	private static class StringData {
		public static final String[]	DAYS	= { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

		public static final String[]	MONTHS	= { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	}

	public static long parse(String s) {
		double parsed = JsDate.parse(s);
		if (Double.isNaN(parsed)) {
			throw new IllegalArgumentException();
		}
		return (long) parsed;
	}

	// CHECKSTYLE_OFF: Matching the spec.
	public static long UTC(int year, int month, int date, int hrs, int min, int sec) {
		return (long) JsDate.UTC(year + 1900, month, date, hrs, min, sec, 0);
	}

	// CHECKSTYLE_ON

	/**
	 * Ensure a number is displayed with two digits.
	 * 
	 * @return a two-character base 10 representation of the number
	 */
	protected static String padTwo(int number) {
		if (number < 10) {
			return "0" + number;
		} else {
			return String.valueOf(number);
		}
	}

	/**
	 * Package private factory for JSNI use, to allow cheap creation of dates from doubles.
	 */
	static AgnieDate createFrom(double milliseconds) {
		return new AgnieDate(milliseconds, false);
	}

	/**
	 * JavaScript Date instance.
	 */
	private JsDate	jsdate;

	public AgnieDate() {
		jsdate = JsDate.create();

		if (!timeZoneId.equals("")) {
			setTimeZone(timeZoneId);
		}

	}

	public AgnieDate(int year, int month, int date) {
		this(year, month, date, 0, 0, 0);
	}

	public AgnieDate(int year, int month, int date, int hrs, int min) {
		this(year, month, date, hrs, min, 0);
	}

	public AgnieDate(int year, int month, int date, int hrs, int min, int sec) {
		jsdate = JsDate.create();
		jsdate.setFullYear(year + 1900, month, date);
		jsdate.setHours(hrs, min, sec, 0);
		fixDaylightSavings(hrs);
	}

	public AgnieDate(long date) {
		jsdate = JsDate.create(date);
	}

	public AgnieDate(String date) {
		this(AgnieDate.parse(date));
	}

	/**
	 * For use by {@link #createFrom(double)}, should inline away.
	 */
	AgnieDate(double milliseconds, boolean dummyArgForOverloadResolution) {
		jsdate = JsDate.create(milliseconds);
	}

	public boolean after(AgnieDate when) {
		return getTime() > when.getTime();
	}

	public boolean before(AgnieDate when) {
		return getTime() < when.getTime();
	}

	public Object clone() {
		return new AgnieDate(getTime());
	}

	public int compareTo(AgnieDate other) {
		return Long.signum(getTime() - other.getTime());
	}

	@Override
	public boolean equals(Object obj) {
		return ((obj instanceof AgnieDate) && (getTime() == ((AgnieDate) obj).getTime()));
	}

	public int getDate() {
		return jsdate.getDate();
	}

	public int getDay() {
		return jsdate.getDay();
	}

	public int getHours() {
		return jsdate.getHours();
	}

	public int getMinutes() {
		return jsdate.getMinutes();
	}

	public int getMonth() {
		return jsdate.getMonth();
	}

	public int getSeconds() {
		return jsdate.getSeconds();
	}

	public long getTime() {
		return (long) jsdate.getTime();
	}

	public int getTimezoneOffset() {
		return jsdate.getTimezoneOffset();
	}

	public int getYear() {
		return jsdate.getFullYear() - 1900;
	}

	@Override
	public int hashCode() {
		long time = getTime();
		return (int) (time ^ (time >>> 32));
	}

	public void setDate(int date) {
		int hours = jsdate.getHours();
		jsdate.setDate(date);
		fixDaylightSavings(hours);
	}

	public void setHours(int hours) {
		jsdate.setHours(hours);
		fixDaylightSavings(hours);
	}

	public void setMinutes(int minutes) {
		int hours = getHours() + minutes / 60;
		jsdate.setMinutes(minutes);
		fixDaylightSavings(hours);
	}

	public void setMonth(int month) {
		int hours = jsdate.getHours();
		jsdate.setMonth(month);
		fixDaylightSavings(hours);
	}

	public void setSeconds(int seconds) {
		int hours = getHours() + seconds / (60 * 60);
		jsdate.setSeconds(seconds);
		fixDaylightSavings(hours);
	}

	public void setTime(long time) {
		jsdate.setTime(time);
	}

	public void setYear(int year) {
		int hours = jsdate.getHours();
		jsdate.setFullYear(year + 1900);
		fixDaylightSavings(hours);
	}

	public String toGMTString() {
		return jsdate.getUTCDate() + " " + StringData.MONTHS[jsdate.getUTCMonth()] + " " + jsdate.getUTCFullYear() + " " + padTwo(jsdate.getUTCHours()) + ":" + padTwo(jsdate.getUTCMinutes()) + ":"
				+ padTwo(jsdate.getUTCSeconds()) + " GMT";
	}

	public String toLocaleString() {
		return jsdate.toLocaleString();
	}

	@Override
	public String toString() {
		return padTwo(jsdate.getDate()) + " " + StringData.DAYS[jsdate.getDay()] + " " + StringData.MONTHS[jsdate.getMonth()] + " " + jsdate.getFullYear() + " - " + padTwo(jsdate.getHours()) + ":"
				+ padTwo(jsdate.getMinutes()) + ":" + padTwo(jsdate.getSeconds());
	}

	public HandlerRegistration addSecondsChangeHandler(SecondsChangeHandler handler) {
		return eventBus.addHandler(SecondsChangeEvent.TYPE, handler);
	}

	/*
	 * Some browsers have the following behavior:
	 * 
	 * // Assume a U.S. time zone with daylight savings // Set a non-existent time: 2:00 am Sunday March 8, 2009 var
	 * date = new Date(2009, 2, 8, 2, 0, 0); var hours = date.getHours(); // returns 1
	 * 
	 * The equivalent Java code will return 3. To compensate, we determine the amount of daylight savings adjustment by
	 * comparing the time zone offsets for the requested time and a time one day later, and add the adjustment to the
	 * hours and minutes of the requested time.
	 */

	/**
	 * Detects if the requested time falls into a non-existent time range due to local time advancing into daylight
	 * savings time. If so, push the requested time forward out of the non-existent range.
	 */
	private void fixDaylightSavings(int hours) {
		if ((jsdate.getHours() % 24) != (hours % 24)) {
			JsDate copy = JsDate.create(jsdate.getTime());
			copy.setDate(copy.getDate() + 1);
			int timeDiff = jsdate.getTimezoneOffset() - copy.getTimezoneOffset();

			// If the time zone offset is changing, advance the hours and
			// minutes from the initially requested time by the change amount
			if (timeDiff > 0) {
				int timeDiffHours = timeDiff / 60;
				int timeDiffMinutes = timeDiff % 60;
				int day = jsdate.getDate();
				int badHours = jsdate.getHours();
				if (badHours + timeDiffHours >= 24) {
					day++;
				}
				JsDate newTime = JsDate.create(jsdate.getFullYear(), jsdate.getMonth(), day, hours + timeDiffHours, jsdate.getMinutes() + timeDiffMinutes, jsdate.getSeconds(),
						jsdate.getMilliseconds());
				jsdate.setTime(newTime.getTime());
			}
		}
	}

	/*
	 * This method converts systems current date from systems default timezone into given timezone date.
	 * 
	 * For ex: if system in Indian timezone, then jsDate will provide the date in Indian timezone. But if we want time
	 * in Sydney timezone, then this method will convert the time into sydney timezone.
	 */
	public void setTimeZone(String timeZoneId) {

		TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);

		// difference between current timezone and required timezone
		int offsetConversion = jsdate.getTimezoneOffset() - timeZone.timeZoneContainer.getTimeZone().getStandardOffset();

		if (offsetConversion != 0) {
			jsdate.setMinutes(jsdate.getMinutes() + offsetConversion);
			// jsdate.setMinutes(jsdate.getMinutes() +
			// timeZone.timeZoneContainer.getTimeZone().getDaylightAdjustment(jsdate));
		}
	}

	/*
	 * Date constructor to create date object with required timezone
	 */
	public AgnieDate(String timeZone, boolean dummy) {
		jsdate = JsDate.create();

		timeZoneId = timeZone;
		setTimeZone(timeZoneId);
		Timer timer = new Timer() {
			public void run() {
				// TODO: at present after every second we are creating recreating JsDate, where as this should run after
				// every 1000 milisecond and update respective seconds, hours till year. Rather than creating new date
				// object every time.
				jsdate = JsDate.create();
				setTimeZone(timeZoneId);
				eventBus.fireEvent(new SecondsChangeEvent(AgnieDate.this));
			}
		};
		// Execute the timer to expire 2 seconds in the future
		timer.scheduleRepeating(1000);
	}

}
