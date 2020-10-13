/*
 * Zmanim Java API
 * Copyright (C) 2011 - 2020 Eliyahu Hershfeld
 *
 * This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful,but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more
 * details.
 * You should have received a copy of the GNU Lesser General Public License along with this library; if not, write to
 * the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA,
 * or connect to: http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 */
package com.kosherjava.zmanim.hebrewcalendar;

import java.text.SimpleDateFormat;
import java.util.EnumMap;

/**
 * The HebrewDateFormatter class formats a {@link JewishDate}.
 * 
 * The class formats Jewish dates in Hebrew or Latin chars, and has various settings. Sample full date output includes
 * (using various options):
 * <ul>
 * <li>21 Shevat, 5729</li>
 * <li>&#x5DB;&#x5D0; &#x5E9;&#x5D1;&#x5D8; &#x5EA;&#x5E9;&#x5DB;&#x5D8;</li>
 * <li>&#x5DB;&#x5F4;&#x5D0; &#x5E9;&#x5D1;&#x5D8; &#x5D4;&#x5F3;&#x5EA;&#x5E9;&#x5DB;&#x5F4;&#x5D8;</li>
 * <li>&#x5DB;&#x5F4;&#x5D0; &#x5E9;&#x5D1;&#x5D8; &#x5EA;&#x5E9;&#x5F4;&#x05E4; or
 * &#x5DB;&#x5F4;&#x5D0; &#x5E9;&#x5D1;&#x5D8; &#x5EA;&#x5E9;&#x5F4;&#x05E3;</li>
 * <li>&#x05DB;&#x05F3; &#x05E9;&#x05D1;&#x05D8; &#x05D5;&#x05F3; &#x05D0;&#x05DC;&#x05E4;&#x05D9;&#x05DD;</li>
 * </ul>
 * 
 * @see com.kosherjava.zmanim.hebrewcalendar.JewishDate
 * @see com.kosherjava.zmanim.hebrewcalendar.JewishCalendar
 * 
 * @author &copy; Eliyahu Hershfeld 2011 - 2020
 */
public class HebrewDateFormatter {
	private boolean hebrewFormat = false;
	private boolean useLonghebrewYears = false;
	private boolean useGershGershayim = true;
	private boolean longWeekFormat = true;
	private boolean useFinalFormLetters = false;
	private SimpleDateFormat weekFormat = null;
	
	/**
	 * list of transliterated parshiyos using the default Ashkenazi pronounciation. The formatParsha method uses this
	 * for transliterated parsha display. This list can be overridden (for Sephardi English transliteration for example)
	 * by setting the {@link #setTransliteratedParshiosList(EnumMap)}.
	 * 
	 * @see #formatParsha(JewishCalendar)
	 */
	private EnumMap<JewishCalendar.Parsha, String> transliteratedParshaMap;
	
	/**
	 * Unicode list of Hebrew parshiyos.
	 */
	private EnumMap<JewishCalendar.Parsha, String> hebrewParshaMap;
	
	public HebrewDateFormatter() {
		transliteratedParshaMap = new EnumMap<JewishCalendar.Parsha, String>(JewishCalendar.Parsha.class);
		transliteratedParshaMap.put(JewishCalendar.Parsha.NONE, "");
		transliteratedParshaMap.put(JewishCalendar.Parsha.BERESHIS, "Bereshis");
		transliteratedParshaMap.put(JewishCalendar.Parsha.NOACH, "Noach");
		transliteratedParshaMap.put(JewishCalendar.Parsha.LECH_LECHA, "Lech Lecha");
		transliteratedParshaMap.put(JewishCalendar.Parsha.VAYERA, "Vayera");
		transliteratedParshaMap.put(JewishCalendar.Parsha.CHAYEI_SARA, "Chayei Sara");
		transliteratedParshaMap.put(JewishCalendar.Parsha.TOLDOS, "Toldos");
		transliteratedParshaMap.put(JewishCalendar.Parsha.VAYETZEI, "Vayetzei");
		transliteratedParshaMap.put(JewishCalendar.Parsha.VAYISHLACH, "Vayishlach");
		transliteratedParshaMap.put(JewishCalendar.Parsha.VAYESHEV, "Vayeshev");
		transliteratedParshaMap.put(JewishCalendar.Parsha.MIKETZ, "Miketz");
		transliteratedParshaMap.put(JewishCalendar.Parsha.VAYIGASH, "Vayigash");
		transliteratedParshaMap.put(JewishCalendar.Parsha.VAYECHI, "Vayechi");
		transliteratedParshaMap.put(JewishCalendar.Parsha.SHEMOS, "Shemos");
		transliteratedParshaMap.put(JewishCalendar.Parsha.VAERA, "Vaera");
		transliteratedParshaMap.put(JewishCalendar.Parsha.BO, "Bo");
		transliteratedParshaMap.put(JewishCalendar.Parsha.BESHALACH, "Beshalach");
		transliteratedParshaMap.put(JewishCalendar.Parsha.YISRO, "Yisro");
		transliteratedParshaMap.put(JewishCalendar.Parsha.MISHPATIM, "Mishpatim");
		transliteratedParshaMap.put(JewishCalendar.Parsha.TERUMAH, "Terumah");
		transliteratedParshaMap.put(JewishCalendar.Parsha.TETZAVEH, "Tetzaveh");
		transliteratedParshaMap.put(JewishCalendar.Parsha.KI_SISA, "Ki Sisa");
		transliteratedParshaMap.put(JewishCalendar.Parsha.VAYAKHEL, "Vayakhel");
		transliteratedParshaMap.put(JewishCalendar.Parsha.PEKUDEI, "Pekudei");
		transliteratedParshaMap.put(JewishCalendar.Parsha.VAYIKRA, "Vayikra");
		transliteratedParshaMap.put(JewishCalendar.Parsha.TZAV, "Tzav");
		transliteratedParshaMap.put(JewishCalendar.Parsha.SHMINI, "Shmini");
		transliteratedParshaMap.put(JewishCalendar.Parsha.TAZRIA, "Tazria");
		transliteratedParshaMap.put(JewishCalendar.Parsha.METZORA, "Metzora");
		transliteratedParshaMap.put(JewishCalendar.Parsha.ACHREI_MOS, "Achrei Mos");
		transliteratedParshaMap.put(JewishCalendar.Parsha.KEDOSHIM, "Kedoshim");
		transliteratedParshaMap.put(JewishCalendar.Parsha.EMOR, "Emor");
		transliteratedParshaMap.put(JewishCalendar.Parsha.BEHAR, "Behar");
		transliteratedParshaMap.put(JewishCalendar.Parsha.BECHUKOSAI, "Bechukosai");
		transliteratedParshaMap.put(JewishCalendar.Parsha.BAMIDBAR, "Bamidbar");
		transliteratedParshaMap.put(JewishCalendar.Parsha.NASSO, "Nasso");
		transliteratedParshaMap.put(JewishCalendar.Parsha.BEHAALOSCHA, "Beha'aloscha");
		transliteratedParshaMap.put(JewishCalendar.Parsha.SHLACH, "Sh'lach");
		transliteratedParshaMap.put(JewishCalendar.Parsha.KORACH, "Korach");
		transliteratedParshaMap.put(JewishCalendar.Parsha.CHUKAS, "Chukas");
		transliteratedParshaMap.put(JewishCalendar.Parsha.BALAK, "Balak");
		transliteratedParshaMap.put(JewishCalendar.Parsha.PINCHAS, "Pinchas");
		transliteratedParshaMap.put(JewishCalendar.Parsha.MATOS, "Matos");
		transliteratedParshaMap.put(JewishCalendar.Parsha.MASEI, "Masei");
		transliteratedParshaMap.put(JewishCalendar.Parsha.DEVARIM, "Devarim");
		transliteratedParshaMap.put(JewishCalendar.Parsha.VAESCHANAN, "Vaeschanan");
		transliteratedParshaMap.put(JewishCalendar.Parsha.EIKEV, "Eikev");
		transliteratedParshaMap.put(JewishCalendar.Parsha.REEH, "Re'eh");
		transliteratedParshaMap.put(JewishCalendar.Parsha.SHOFTIM, "Shoftim");
		transliteratedParshaMap.put(JewishCalendar.Parsha.KI_SEITZEI, "Ki Seitzei");
		transliteratedParshaMap.put(JewishCalendar.Parsha.KI_SAVO, "Ki Savo");
		transliteratedParshaMap.put(JewishCalendar.Parsha.NITZAVIM, "Nitzavim");
		transliteratedParshaMap.put(JewishCalendar.Parsha.VAYEILECH, "Vayeilech");
		transliteratedParshaMap.put(JewishCalendar.Parsha.HAAZINU, "Ha'Azinu");
		transliteratedParshaMap.put(JewishCalendar.Parsha.VZOS_HABERACHA, "Vezos Habracha");
		transliteratedParshaMap.put(JewishCalendar.Parsha.VAYAKHEL_PEKUDEI, "Vayakhel Pekudei");
		transliteratedParshaMap.put(JewishCalendar.Parsha.TAZRIA_METZORA, "Tazria Metzora");
		transliteratedParshaMap.put(JewishCalendar.Parsha.ACHREI_MOS_KEDOSHIM, "Achrei Mos Kedoshim");
		transliteratedParshaMap.put(JewishCalendar.Parsha.BEHAR_BECHUKOSAI, "Behar Bechukosai");
		transliteratedParshaMap.put(JewishCalendar.Parsha.CHUKAS_BALAK, "Chukas Balak");
		transliteratedParshaMap.put(JewishCalendar.Parsha.MATOS_MASEI, "Matos Masei");
		transliteratedParshaMap.put(JewishCalendar.Parsha.NITZAVIM_VAYEILECH, "Nitzavim Vayeilech");
		transliteratedParshaMap.put(JewishCalendar.Parsha.SHKALIM, "Shekalim");
		transliteratedParshaMap.put(JewishCalendar.Parsha.ZACHOR, "Zachor");
		transliteratedParshaMap.put(JewishCalendar.Parsha.PARA, "Parah");
		transliteratedParshaMap.put(JewishCalendar.Parsha.HACHODESH, "Hachodesh");
		
		hebrewParshaMap = new EnumMap<JewishCalendar.Parsha, String>(JewishCalendar.Parsha.class);
		hebrewParshaMap.put(JewishCalendar.Parsha.NONE, "");
		hebrewParshaMap.put(JewishCalendar.Parsha.BERESHIS, "\u05D1\u05E8\u05D0\u05E9\u05D9\u05EA");
		hebrewParshaMap.put(JewishCalendar.Parsha.NOACH, "\u05E0\u05D7");
		hebrewParshaMap.put(JewishCalendar.Parsha.LECH_LECHA, "\u05DC\u05DA \u05DC\u05DA");
		hebrewParshaMap.put(JewishCalendar.Parsha.VAYERA, "\u05D5\u05D9\u05E8\u05D0");
		hebrewParshaMap.put(JewishCalendar.Parsha.CHAYEI_SARA, "\u05D7\u05D9\u05D9 \u05E9\u05E8\u05D4");
		hebrewParshaMap.put(JewishCalendar.Parsha.TOLDOS, "\u05EA\u05D5\u05DC\u05D3\u05D5\u05EA");
		hebrewParshaMap.put(JewishCalendar.Parsha.VAYETZEI, "\u05D5\u05D9\u05E6\u05D0");
		hebrewParshaMap.put(JewishCalendar.Parsha.VAYISHLACH, "\u05D5\u05D9\u05E9\u05DC\u05D7");
		hebrewParshaMap.put(JewishCalendar.Parsha.VAYESHEV, "\u05D5\u05D9\u05E9\u05D1");
		hebrewParshaMap.put(JewishCalendar.Parsha.MIKETZ, "\u05DE\u05E7\u05E5");
		hebrewParshaMap.put(JewishCalendar.Parsha.VAYIGASH, "\u05D5\u05D9\u05D2\u05E9");
		hebrewParshaMap.put(JewishCalendar.Parsha.VAYECHI, "\u05D5\u05D9\u05D7\u05D9");
		hebrewParshaMap.put(JewishCalendar.Parsha.SHEMOS, "\u05E9\u05DE\u05D5\u05EA");
		hebrewParshaMap.put(JewishCalendar.Parsha.VAERA, "\u05D5\u05D0\u05E8\u05D0");
		hebrewParshaMap.put(JewishCalendar.Parsha.BO, "\u05D1\u05D0");
		hebrewParshaMap.put(JewishCalendar.Parsha.BESHALACH, "\u05D1\u05E9\u05DC\u05D7");
		hebrewParshaMap.put(JewishCalendar.Parsha.YISRO, "\u05D9\u05EA\u05E8\u05D5");
		hebrewParshaMap.put(JewishCalendar.Parsha.MISHPATIM, "\u05DE\u05E9\u05E4\u05D8\u05D9\u05DD");
		hebrewParshaMap.put(JewishCalendar.Parsha.TERUMAH, "\u05EA\u05E8\u05D5\u05DE\u05D4");
		hebrewParshaMap.put(JewishCalendar.Parsha.TETZAVEH, "\u05EA\u05E6\u05D5\u05D4");
		hebrewParshaMap.put(JewishCalendar.Parsha.KI_SISA, "\u05DB\u05D9 \u05EA\u05E9\u05D0");
		hebrewParshaMap.put(JewishCalendar.Parsha.VAYAKHEL, "\u05D5\u05D9\u05E7\u05D4\u05DC");
		hebrewParshaMap.put(JewishCalendar.Parsha.PEKUDEI, "\u05E4\u05E7\u05D5\u05D3\u05D9");
		hebrewParshaMap.put(JewishCalendar.Parsha.VAYIKRA, "\u05D5\u05D9\u05E7\u05E8\u05D0");
		hebrewParshaMap.put(JewishCalendar.Parsha.TZAV, "\u05E6\u05D5");
		hebrewParshaMap.put(JewishCalendar.Parsha.SHMINI, "\u05E9\u05DE\u05D9\u05E0\u05D9");
		hebrewParshaMap.put(JewishCalendar.Parsha.TAZRIA, "\u05EA\u05D6\u05E8\u05D9\u05E2");
		hebrewParshaMap.put(JewishCalendar.Parsha.METZORA, "\u05DE\u05E6\u05E8\u05E2");
		hebrewParshaMap.put(JewishCalendar.Parsha.ACHREI_MOS, "\u05D0\u05D7\u05E8\u05D9 \u05DE\u05D5\u05EA");
		hebrewParshaMap.put(JewishCalendar.Parsha.KEDOSHIM, "\u05E7\u05D3\u05D5\u05E9\u05D9\u05DD");
		hebrewParshaMap.put(JewishCalendar.Parsha.EMOR, "\u05D0\u05DE\u05D5\u05E8");
		hebrewParshaMap.put(JewishCalendar.Parsha.BEHAR, "\u05D1\u05D4\u05E8");
		hebrewParshaMap.put(JewishCalendar.Parsha.BECHUKOSAI, "\u05D1\u05D7\u05E7\u05EA\u05D9");
		hebrewParshaMap.put(JewishCalendar.Parsha.BAMIDBAR, "\u05D1\u05DE\u05D3\u05D1\u05E8");
		hebrewParshaMap.put(JewishCalendar.Parsha.NASSO, "\u05E0\u05E9\u05D0");
		hebrewParshaMap.put(JewishCalendar.Parsha.BEHAALOSCHA, "\u05D1\u05D4\u05E2\u05DC\u05EA\u05DA");
		hebrewParshaMap.put(JewishCalendar.Parsha.SHLACH, "\u05E9\u05DC\u05D7 \u05DC\u05DA");
		hebrewParshaMap.put(JewishCalendar.Parsha.KORACH, "\u05E7\u05E8\u05D7");
		hebrewParshaMap.put(JewishCalendar.Parsha.CHUKAS, "\u05D7\u05D5\u05E7\u05EA");
		hebrewParshaMap.put(JewishCalendar.Parsha.BALAK, "\u05D1\u05DC\u05E7");
		hebrewParshaMap.put(JewishCalendar.Parsha.PINCHAS, "\u05E4\u05D9\u05E0\u05D7\u05E1");
		hebrewParshaMap.put(JewishCalendar.Parsha.MATOS, "\u05DE\u05D8\u05D5\u05EA");
		hebrewParshaMap.put(JewishCalendar.Parsha.MASEI, "\u05DE\u05E1\u05E2\u05D9");
		hebrewParshaMap.put(JewishCalendar.Parsha.DEVARIM, "\u05D3\u05D1\u05E8\u05D9\u05DD");
		hebrewParshaMap.put(JewishCalendar.Parsha.VAESCHANAN, "\u05D5\u05D0\u05EA\u05D7\u05E0\u05DF");
		hebrewParshaMap.put(JewishCalendar.Parsha.EIKEV, "\u05E2\u05E7\u05D1");
		hebrewParshaMap.put(JewishCalendar.Parsha.REEH, "\u05E8\u05D0\u05D4");
		hebrewParshaMap.put(JewishCalendar.Parsha.SHOFTIM, "\u05E9\u05D5\u05E4\u05D8\u05D9\u05DD");
		hebrewParshaMap.put(JewishCalendar.Parsha.KI_SEITZEI, "\u05DB\u05D9 \u05EA\u05E6\u05D0");
		hebrewParshaMap.put(JewishCalendar.Parsha.KI_SAVO, "\u05DB\u05D9 \u05EA\u05D1\u05D5\u05D0");
		hebrewParshaMap.put(JewishCalendar.Parsha.NITZAVIM, "\u05E0\u05D9\u05E6\u05D1\u05D9\u05DD");
		hebrewParshaMap.put(JewishCalendar.Parsha.VAYEILECH, "\u05D5\u05D9\u05DC\u05DA");
		hebrewParshaMap.put(JewishCalendar.Parsha.HAAZINU, "\u05D4\u05D0\u05D6\u05D9\u05E0\u05D5");
		hebrewParshaMap.put(JewishCalendar.Parsha.VZOS_HABERACHA, "\u05D5\u05D6\u05D0\u05EA \u05D4\u05D1\u05E8\u05DB\u05D4 ");
		hebrewParshaMap.put(JewishCalendar.Parsha.VAYAKHEL_PEKUDEI, "\u05D5\u05D9\u05E7\u05D4\u05DC \u05E4\u05E7\u05D5\u05D3\u05D9");
		hebrewParshaMap.put(JewishCalendar.Parsha.TAZRIA_METZORA, "\u05EA\u05D6\u05E8\u05D9\u05E2 \u05DE\u05E6\u05E8\u05E2");
		hebrewParshaMap.put(JewishCalendar.Parsha.ACHREI_MOS_KEDOSHIM, "\u05D0\u05D7\u05E8\u05D9 \u05DE\u05D5\u05EA \u05E7\u05D3\u05D5\u05E9\u05D9\u05DD");
		hebrewParshaMap.put(JewishCalendar.Parsha.BEHAR_BECHUKOSAI, "\u05D1\u05D4\u05E8 \u05D1\u05D7\u05E7\u05EA\u05D9");
		hebrewParshaMap.put(JewishCalendar.Parsha.CHUKAS_BALAK, "\u05D7\u05D5\u05E7\u05EA \u05D1\u05DC\u05E7");
		hebrewParshaMap.put(JewishCalendar.Parsha.MATOS_MASEI, "\u05DE\u05D8\u05D5\u05EA \u05DE\u05E1\u05E2\u05D9");
		hebrewParshaMap.put(JewishCalendar.Parsha.NITZAVIM_VAYEILECH, "\u05E0\u05D9\u05E6\u05D1\u05D9\u05DD \u05D5\u05D9\u05DC\u05DA");
		hebrewParshaMap.put(JewishCalendar.Parsha.SHKALIM, "\u05E9\u05E7\u05DC\u05D9\u05DD");
		hebrewParshaMap.put(JewishCalendar.Parsha.ZACHOR, "\u05D6\u05DB\u05D5\u05E8");
		hebrewParshaMap.put(JewishCalendar.Parsha.PARA, "\u05E4\u05E8\u05D4");
		hebrewParshaMap.put(JewishCalendar.Parsha.HACHODESH, "\u05D4\u05D7\u05D3\u05E9");
	}

	/**
	 * Returns if the {@link #formatDayOfWeek(JewishDate)} will use the long format such as
	 * &#x05E8;&#x05D0;&#x05E9;&#x05D5;&#x05DF; or short such as &#x05D0; when formatting the day of week in
	 * {@link #isHebrewFormat() Hebrew}.
	 * 
	 * @return the longWeekFormat
	 * @see #setLongWeekFormat(boolean)
	 * @see #formatDayOfWeek(JewishDate)
	 */
	public boolean isLongWeekFormat() {
		return longWeekFormat;
	}

	/**
	 * Setting to control if the {@link #formatDayOfWeek(JewishDate)} will use the long format such as
	 * &#x05E8;&#x05D0;&#x05E9;&#x05D5;&#x05DF; or short such as &#x05D0; when formatting the day of week in
	 * {@link #isHebrewFormat() Hebrew}.
	 * 
	 * @param longWeekFormat
	 *            the longWeekFormat to set
	 */
	public void setLongWeekFormat(boolean longWeekFormat) {
		this.longWeekFormat = longWeekFormat;
		if(longWeekFormat) {
			weekFormat = new SimpleDateFormat("EEEE");
		} else {
			weekFormat = new SimpleDateFormat("EEE");
		}
	}

	private static final String GERESH = "\u05F3";
	private static final String GERSHAYIM = "\u05F4";
	private String[] transliteratedMonths = { "Nissan", "Iyar", "Sivan", "Tammuz", "Av", "Elul", "Tishrei", "Cheshvan",
			"Kislev", "Teves", "Shevat", "Adar", "Adar II", "Adar I" };
	private String hebrewOmerPrefix = "\u05D1";

	private String transliteratedShabbosDayOfweek = "Shabbos";

	/**
	 * Returns the day of Shabbos transliterated into Latin chars. The default uses Ashkenazi pronunciation "Shabbos".
	 * This can be overwritten using the {@link #setTransliteratedShabbosDayOfWeek(String)}
	 * 
	 * @return the transliteratedShabbos. The default list of months uses Ashkenazi pronunciation "Shabbos".
	 * @see #setTransliteratedShabbosDayOfWeek(String)
	 * @see #formatDayOfWeek(JewishDate)
	 */
	public String getTransliteratedShabbosDayOfWeek() {
		return transliteratedShabbosDayOfweek;
	}

	/**
	 * Setter to override the default transliterated name of "Shabbos" to alternate spelling such as "Shabbat" used by
	 * the {@link #formatDayOfWeek(JewishDate)}
	 * 
	 * @param transliteratedShabbos
	 *            the transliteratedShabbos to set
	 * 
	 * @see #getTransliteratedShabbosDayOfWeek()
	 * @see #formatDayOfWeek(JewishDate)
	 */
	public void setTransliteratedShabbosDayOfWeek(String transliteratedShabbos) {
		this.transliteratedShabbosDayOfweek = transliteratedShabbos;
	}

	private String[] transliteratedHolidays = { "Erev Pesach", "Pesach", "Chol Hamoed Pesach", "Pesach Sheni",
			"Erev Shavuos", "Shavuos", "Seventeenth of Tammuz", "Tishah B'Av", "Tu B'Av", "Erev Rosh Hashana",
			"Rosh Hashana", "Fast of Gedalyah", "Erev Yom Kippur", "Yom Kippur", "Erev Succos", "Succos",
			"Chol Hamoed Succos", "Hoshana Rabbah", "Shemini Atzeres", "Simchas Torah", "Erev Chanukah", "Chanukah",
			"Tenth of Teves", "Tu B'Shvat", "Fast of Esther", "Purim", "Shushan Purim", "Purim Katan", "Rosh Chodesh",
			"Yom HaShoah", "Yom Hazikaron", "Yom Ha'atzmaut", "Yom Yerushalayim" };

	/**
	 * Returns the list of holidays transliterated into Latin chars. This is used by the
	 * {@link #formatYomTov(JewishCalendar)} when formatting the Yom Tov String. The default list of months uses
	 * Ashkenazi pronunciation in typical American English spelling.
	 * 
	 * @return the list of holidays "Adar", "Adar II", "Adar I". The default list is currently "Erev Pesach", "Pesach",
	 *         "Chol Hamoed Pesach", "Pesach Sheni", "Erev Shavuos", "Shavuos", "Seventeenth of Tammuz", "Tishah B'Av",
	 *         "Tu B'Av", "Erev Rosh Hashana", "Rosh Hashana", "Fast of Gedalyah", "Erev Yom Kippur", "Yom Kippur",
	 *         "Erev Succos", "Succos", "Chol Hamoed Succos", "Hoshana Rabbah", "Shemini Atzeres", "Simchas Torah",
	 *         "Erev Chanukah", "Chanukah", "Tenth of Teves", "Tu B'Shvat", "Fast of Esther", "Purim", "Shushan Purim",
	 *         "Purim Katan", "Rosh Chodesh", "Yom HaShoah", "Yom Hazikaron", "Yom Ha'atzmaut", "Yom Yerushalayim"
	 * 
	 * @see #setTransliteratedMonthList(String[])
	 * @see #formatYomTov(JewishCalendar)
	 * @see #isHebrewFormat()
	 */
	public String[] getTransliteratedHolidayList() {
		return transliteratedHolidays;
	}

	/**
	 * Sets the list of holidays transliterated into Latin chars. This is used by the
	 * {@link #formatYomTov(JewishCalendar)} when formatting the Yom Tov String.
	 * 
	 * @param transliteratedHolidays
	 *            the transliteratedHolidays to set. Ensure that the sequence exactly matches the list returned by the
	 *            default
	 */
	public void setTransliteratedHolidayList(String[] transliteratedHolidays) {
		this.transliteratedHolidays = transliteratedHolidays;
	}

	/**
	 * Hebrew holiday list
	 */
	private String[] hebrewHolidays = { "\u05E2\u05E8\u05D1 \u05E4\u05E1\u05D7", "\u05E4\u05E1\u05D7",
			"\u05D7\u05D5\u05DC \u05D4\u05DE\u05D5\u05E2\u05D3 \u05E4\u05E1\u05D7",
			"\u05E4\u05E1\u05D7 \u05E9\u05E0\u05D9", "\u05E2\u05E8\u05D1 \u05E9\u05D1\u05D5\u05E2\u05D5\u05EA",
			"\u05E9\u05D1\u05D5\u05E2\u05D5\u05EA",
			"\u05E9\u05D1\u05E2\u05D4 \u05E2\u05E9\u05E8 \u05D1\u05EA\u05DE\u05D5\u05D6",
			"\u05EA\u05E9\u05E2\u05D4 \u05D1\u05D0\u05D1", "\u05D8\u05F4\u05D5 \u05D1\u05D0\u05D1",
			"\u05E2\u05E8\u05D1 \u05E8\u05D0\u05E9 \u05D4\u05E9\u05E0\u05D4",
			"\u05E8\u05D0\u05E9 \u05D4\u05E9\u05E0\u05D4", "\u05E6\u05D5\u05DD \u05D2\u05D3\u05DC\u05D9\u05D4",
			"\u05E2\u05E8\u05D1 \u05D9\u05D5\u05DD \u05DB\u05D9\u05E4\u05D5\u05E8",
			"\u05D9\u05D5\u05DD \u05DB\u05D9\u05E4\u05D5\u05E8", "\u05E2\u05E8\u05D1 \u05E1\u05D5\u05DB\u05D5\u05EA",
			"\u05E1\u05D5\u05DB\u05D5\u05EA",
			"\u05D7\u05D5\u05DC \u05D4\u05DE\u05D5\u05E2\u05D3 \u05E1\u05D5\u05DB\u05D5\u05EA",
			"\u05D4\u05D5\u05E9\u05E2\u05E0\u05D0 \u05E8\u05D1\u05D4",
			"\u05E9\u05DE\u05D9\u05E0\u05D9 \u05E2\u05E6\u05E8\u05EA",
			"\u05E9\u05DE\u05D7\u05EA \u05EA\u05D5\u05E8\u05D4", "\u05E2\u05E8\u05D1 \u05D7\u05E0\u05D5\u05DB\u05D4",
			"\u05D7\u05E0\u05D5\u05DB\u05D4", "\u05E2\u05E9\u05E8\u05D4 \u05D1\u05D8\u05D1\u05EA",
			"\u05D8\u05F4\u05D5 \u05D1\u05E9\u05D1\u05D8", "\u05EA\u05E2\u05E0\u05D9\u05EA \u05D0\u05E1\u05EA\u05E8",
			"\u05E4\u05D5\u05E8\u05D9\u05DD", "\u05E4\u05D5\u05E8\u05D9\u05DD \u05E9\u05D5\u05E9\u05DF",
			"\u05E4\u05D5\u05E8\u05D9\u05DD \u05E7\u05D8\u05DF", "\u05E8\u05D0\u05E9 \u05D7\u05D5\u05D3\u05E9",
			"\u05D9\u05D5\u05DD \u05D4\u05E9\u05D5\u05D0\u05D4",
			"\u05D9\u05D5\u05DD \u05D4\u05D6\u05D9\u05DB\u05E8\u05D5\u05DF",
			"\u05D9\u05D5\u05DD \u05D4\u05E2\u05E6\u05DE\u05D0\u05D5\u05EA",
			"\u05D9\u05D5\u05DD \u05D9\u05E8\u05D5\u05E9\u05DC\u05D9\u05DD" };

	/**
	 * Formats the Yom Tov (holiday) in Hebrew or transliterated Latin characters.
	 * 
	 * @param jewishCalendar the JewishCalendar
	 * @return the formatted holiday or an empty String if the day is not a holiday.
	 * @see #isHebrewFormat()
	 */
	public String formatYomTov(com.kosherjava.zmanim.hebrewcalendar.JewishCalendar jewishCalendar) {
		int index = jewishCalendar.getYomTovIndex();
		if (index == JewishCalendar.CHANUKAH) {
			int dayOfChanukah = jewishCalendar.getDayOfChanukah();
			return hebrewFormat ? (formatHebrewNumber(dayOfChanukah) + " " + hebrewHolidays[index])
					: (transliteratedHolidays[index] + " " + dayOfChanukah);
		}
		return index == -1 ? "" : hebrewFormat ? hebrewHolidays[index] : transliteratedHolidays[index];
	}

	/**
	 * Formats a day as Rosh Chodesh in the format of in the format of &#x05E8;&#x05D0;&#x05E9;
	 * &#x05D7;&#x05D5;&#x05D3;&#x05E9; &#x05E9;&#x05D1;&#x05D8; or Rosh Chodesh Shevat. If it
	 * is not Rosh Chodesh, an empty <code>String</code> will be returned.
	 * @param jewishCalendar the JewishCalendar
	 * @return The formatted <code>String</code> in the format of &#x05E8;&#x05D0;&#x05E9;
	 * &#x05D7;&#x05D5;&#x05D3;&#x05E9; &#x05E9;&#x05D1;&#x05D8; or Rosh Chodesh Shevat. If it
	 * is not Rosh Chodesh, an empty <code>String</code> will be returned.
	 */
	public String formatRoshChodesh(com.kosherjava.zmanim.hebrewcalendar.JewishCalendar jewishCalendar) {
		if (!jewishCalendar.isRoshChodesh()) {
			return "";
		}
		String formattedRoshChodesh = "";
		int month = jewishCalendar.getJewishMonth();
		if (jewishCalendar.getJewishDayOfMonth() == 30) {
			if (month < JewishCalendar.ADAR || (month == JewishCalendar.ADAR && jewishCalendar.isJewishLeapYear())) {
				month++;
			} else { // roll to Nissan
				month = JewishCalendar.NISSAN;
			}
		}

		// This method is only about formatting, so we shouldn't make any changes to the params passed in...
		jewishCalendar = (JewishCalendar) jewishCalendar.clone();
		jewishCalendar.setJewishMonth(month);
		formattedRoshChodesh = hebrewFormat ? hebrewHolidays[JewishCalendar.ROSH_CHODESH]
				: transliteratedHolidays[JewishCalendar.ROSH_CHODESH];
		formattedRoshChodesh += " " + formatMonth(jewishCalendar);
		return formattedRoshChodesh;
	}

	/**
	 * Returns if the formatter is set to use Hebrew formatting in the various formatting methods.
	 * 
	 * @return the hebrewFormat
	 * @see #setHebrewFormat(boolean)
	 * @see #format(JewishDate)
	 * @see #formatDayOfWeek(JewishDate)
	 * @see #formatMonth(JewishDate)
	 * @see #formatOmer(JewishCalendar)
	 * @see #formatYomTov(JewishCalendar)
	 */
	public boolean isHebrewFormat() {
		return hebrewFormat;
	}

	/**
	 * Sets the formatter to format in Hebrew in the various formatting methods.
	 * 
	 * @param hebrewFormat
	 *            the hebrewFormat to set
	 * @see #isHebrewFormat()
	 * @see #format(JewishDate)
	 * @see #formatDayOfWeek(JewishDate)
	 * @see #formatMonth(JewishDate)
	 * @see #formatOmer(JewishCalendar)
	 * @see #formatYomTov(JewishCalendar)
	 */
	public void setHebrewFormat(boolean hebrewFormat) {
		this.hebrewFormat = hebrewFormat;
	}

	/**
	 * Returns the Hebrew Omer prefix. By default it is the letter &#x5D1;, but can be set to &#x5DC; (or any other
	 * prefix) using the {@link #setHebrewOmerPrefix(String)}.
	 * 
	 * @return the hebrewOmerPrefix
	 * 
	 * @see #setHebrewOmerPrefix(String)
	 * @see #formatOmer(JewishCalendar)
	 */
	public String getHebrewOmerPrefix() {
		return hebrewOmerPrefix;
	}

	/**
	 * Method to set the Hebrew Omer prefix. By default it is the letter &#x5D1;, but this allows setting it to a
	 * &#x5DC; (or any other prefix).
	 * 
	 * @param hebrewOmerPrefix
	 *            the hebrewOmerPrefix to set. You can use the Unicode &#92;u05DC to set it to &#x5DC;.
	 * @see #getHebrewOmerPrefix()
	 * @see #formatOmer(JewishCalendar)
	 */
	public void setHebrewOmerPrefix(String hebrewOmerPrefix) {
		this.hebrewOmerPrefix = hebrewOmerPrefix;
	}

	/**
	 * Returns the list of months transliterated into Latin chars. The default list of months uses Ashkenazi
	 * pronunciation in typical American English spelling. This list has a length of 14 with 3 variations for Adar -
	 * "Adar", "Adar II", "Adar I"
	 * 
	 * @return the list of months beginning in Nissan and ending in in "Adar", "Adar II", "Adar I". The default list is
	 *         currently "Nissan", "Iyar", "Sivan", "Tammuz", "Av", "Elul", "Tishrei", "Cheshvan", "Kislev", "Teves",
	 *         "Shevat", "Adar", "Adar II", "Adar I"
	 * @see #setTransliteratedMonthList(String[])
	 */
	public String[] getTransliteratedMonthList() {
		return transliteratedMonths;
	}

	/**
	 * Setter method to allow overriding of the default list of months transliterated into into Latin chars. The default
	 * uses Ashkenazi American English transliteration.
	 * 
	 * @param transliteratedMonths
	 *            an array of 14 month names such as { "Nissan", "Iyar", "Sivan", "Tamuz", "Av", "Elul", "Tishrei",
	 *            "Heshvan", "Kislev", "Tevet", "Shevat", "Adar", "Adar II", "Adar I" }
	 * @see #getTransliteratedMonthList()
	 */
	public void setTransliteratedMonthList(String[] transliteratedMonths) {
		this.transliteratedMonths = transliteratedMonths;
	}

	/**
	 * Unicode list of Hebrew months in the format of ["\u05E0\u05D9\u05E1\u05DF","\u05D0\u05D9\u05D9\u05E8",
	 * "\u05E1\u05D9\u05D5\u05DF","\u05EA\u05DE\u05D5\u05D6","\u05D0\u05D1","\u05D0\u05DC\u05D5\u05DC",
	 * "\u05EA\u05E9\u05E8\u05D9","\u05D7\u05E9\u05D5\u05DF","\u05DB\u05E1\u05DC\u05D5","\u05D8\u05D1\u05EA",
	 * "\u05E9\u05D1\u05D8","\u05D0\u05D3\u05E8","\u05D0\u05D3\u05E8 \u05D1","\u05D0\u05D3\u05E8 \u05D0"]
	 * 
	 * @see #formatMonth(JewishDate)
	 */
	private static final String[] hebrewMonths = { "\u05E0\u05D9\u05E1\u05DF", "\u05D0\u05D9\u05D9\u05E8",
			"\u05E1\u05D9\u05D5\u05DF", "\u05EA\u05DE\u05D5\u05D6", "\u05D0\u05D1", "\u05D0\u05DC\u05D5\u05DC",
			"\u05EA\u05E9\u05E8\u05D9", "\u05D7\u05E9\u05D5\u05DF", "\u05DB\u05E1\u05DC\u05D5",
			"\u05D8\u05D1\u05EA", "\u05E9\u05D1\u05D8", "\u05D0\u05D3\u05E8", "\u05D0\u05D3\u05E8 \u05D1",
			"\u05D0\u05D3\u05E8 \u05D0" };

	/**
	 * Unicode list of Hebrew days of week in the format of ["&#x05E8;&#x05D0;&#x05E9;&#x05D5;&#x05DF;",
	 * "&#x05E9;&#x05E0;&#x05D9;","&#x05E9;&#x05DC;&#x05D9;&#x05E9;&#x05D9;","&#x05E8;&#x05D1;&#x05D9;&#x05E2;&#x05D9;",
	 * "&#x05D7;&#x05DE;&#x05D9;&#x05E9;&#x05D9;","&#x05E9;&#x05E9;&#x05D9;","&#x05E9;&#x05D1;&#x05EA;"]
	 */
	private static final String[] hebrewDaysOfWeek = { "\u05E8\u05D0\u05E9\u05D5\u05DF", "\u05E9\u05E0\u05D9",
			"\u05E9\u05DC\u05D9\u05E9\u05D9", "\u05E8\u05D1\u05D9\u05E2\u05D9", "\u05D7\u05DE\u05D9\u05E9\u05D9",
			"\u05E9\u05E9\u05D9", "\u05E9\u05D1\u05EA" };

	/**
	 * Formats the day of week. If {@link #isHebrewFormat() Hebrew formatting} is set, it will display in the format
	 * &#x05E8;&#x05D0;&#x05E9;&#x05D5;&#x05DF; etc. If Hebrew formatting is not in use it will return it in the format
	 * of Sunday etc. There are various formatting options that will affect the output.
	 * 
	 * @param jewishDate the JewishDate Object
	 * @return the formatted day of week
	 * @see #isHebrewFormat()
	 * @see #isLongWeekFormat()
	 */
	public String formatDayOfWeek(com.kosherjava.zmanim.hebrewcalendar.JewishDate jewishDate) {
		if (hebrewFormat) {
			if(isLongWeekFormat()) {
				return hebrewDaysOfWeek[jewishDate.getDayOfWeek() - 1];
			} else {
				if(jewishDate.getDayOfWeek() == 7) {
					return formatHebrewNumber(300);
				} else {
					return formatHebrewNumber(jewishDate.getDayOfWeek());
				}
			}
		} else {
			if(jewishDate.getDayOfWeek() == 7) {
				if(isLongWeekFormat()) {
					return getTransliteratedShabbosDayOfWeek();
				} else {
					return getTransliteratedShabbosDayOfWeek().substring(0,3);
				}
			} else {
				return weekFormat.format(jewishDate.getGregorianCalendar().getTime());
			}
		}
	}

	/**
	 * Returns whether the class is set to use the Geresh &#x5F3; and Gershayim &#x5F4; in formatting Hebrew dates and
	 * numbers. When true and output would look like &#x5DB;&#x5F4;&#x5D0; &#x5E9;&#x5D1;&#x5D8; &#x5EA;&#x5E9;&#x5F4;&#x5DB;
	 * (or &#x5DB;&#x5F4;&#x5D0; &#x5E9;&#x5D1;&#x5D8; &#x5EA;&#x5E9;&#x5F4;&#x5DA;). When set to false, this output
	 * would display as &#x5DB;&#x5D0; &#x5E9;&#x5D1;&#x5D8; &#x5EA;&#x5E9;&#x5DB;.
	 * 
	 * @return true if set to use the Geresh &#x5F3; and Gershayim &#x5F4; in formatting Hebrew dates and numbers.
	 */
	public boolean isUseGershGershayim() {
		return useGershGershayim;
	}

	/**
	 * Sets whether to use the Geresh &#x5F3; and Gershayim &#x5F4; in formatting Hebrew dates and numbers. The default
	 * value is true and output would look like &#x5DB;&#x5F4;&#x5D0; &#x5E9;&#x5D1;&#x5D8; &#x5EA;&#x5E9;&#x5F4;&#x5DB;
	 * (or &#x5DB;&#x5F4;&#x5D0; &#x5E9;&#x5D1;&#x5D8; &#x5EA;&#x5E9;&#x5F4;&#x5DA;). When set to false, this output would
	 * display as &#x5DB;&#x5D0; &#x5E9;&#x5D1;&#x5D8; &#x5EA;&#x5E9;&#x5DB; (or
	 * &#x5DB;&#x5D0; &#x5E9;&#x5D1;&#x5D8; &#x5EA;&#x5E9;&#x5DA;). Single digit days or month or years such as &#x05DB;&#x05F3;
	 * &#x05E9;&#x05D1;&#x05D8; &#x05D5;&#x05F3; &#x05D0;&#x05DC;&#x05E4;&#x05D9;&#x05DD; show the use of the Geresh.
	 * 
	 * @param useGershGershayim
	 *            set to false to omit the Geresh &#x5F3; and Gershayim &#x5F4; in formatting
	 */
	public void setUseGershGershayim(boolean useGershGershayim) {
		this.useGershGershayim = useGershGershayim;
	}

	/**
	 * Returns whether the class is set to use the &#x05DE;&#x05E0;&#x05E6;&#x05E4;&#x05F4;&#x05DA; letters when
	 * formatting years ending in 20, 40, 50, 80 and 90 to produce &#x05EA;&#x05E9;&#x05F4;&#x05E4; if false or
	 * or &#x05EA;&#x05E9;&#x05F4;&#x05E3; if true. Traditionally non-final form letters are used, so the year
	 * 5780 would be formatted as &#x05EA;&#x05E9;&#x05F4;&#x05E4; if the default false is used here. If this returns
	 * true, the format &#x05EA;&#x05E9;&#x05F4;&#x05E3; would be used.
	 * 
	 * @return true if set to use final form letters when formatting Hebrew years. The default value is false.
	 */
	public boolean isUseFinalFormLetters() {
		return useFinalFormLetters;
	}

	/**
	 * When formatting a Hebrew Year, traditionally years ending in 20, 40, 50, 80 and 90 are formatted using non-final
	 * form letters for example &#x05EA;&#x05E9;&#x05F4;&#x05E4; for the year 5780. Setting this to true (the default
	 * is false) will use the final form letters for &#x05DE;&#x05E0;&#x05E6;&#x05E4;&#x05F4;&#x05DA; and will format
	 * the year 5780 as &#x05EA;&#x05E9;&#x05F4;&#x05E3;.
	 * 
	 * @param useFinalFormLetters
	 *            Set this to true to use final form letters when formatting Hebrew years.
	 */
	public void setUseFinalFormLetters(boolean useFinalFormLetters) {
		this.useFinalFormLetters = useFinalFormLetters;
	}

	/**
	 * Returns whether the class is set to use the thousands digit when formatting. When formatting a Hebrew Year,
	 * traditionally the thousands digit is omitted and output for a year such as 5729 (1969 Gregorian) would be
	 * calculated for 729 and format as &#x5EA;&#x5E9;&#x5DB;&#x5F4;&#x5D8;. When set to true the long format year such
	 * as &#x5D4;&#x5F3; &#x5EA;&#x5E9;&#x5DB;&#x5F4;&#x5D8; for 5729/1969 is returned.
	 * 
	 * @return true if set to use the thousands digit when formatting Hebrew dates and numbers.
	 */
	public boolean isUseLongHebrewYears() {
		return useLonghebrewYears;
	}

	/**
	 * When formatting a Hebrew Year, traditionally the thousands digit is omitted and output for a year such as 5729
	 * (1969 Gregorian) would be calculated for 729 and format as &#x5EA;&#x5E9;&#x5DB;&#x5F4;&#x5D8;. This method
	 * allows setting this to true to return the long format year such as &#x5D4;&#x5F3;
	 * &#x5EA;&#x5E9;&#x5DB;&#x5F4;&#x5D8; for 5729/1969.
	 * 
	 * @param useLongHebrewYears
	 *            Set this to true to use the long formatting
	 */
	public void setUseLongHebrewYears(boolean useLongHebrewYears) {
		this.useLonghebrewYears = useLongHebrewYears;
	}
	/**
	 * Formats the Jewish date. If the formatter is set to Hebrew, it will format in the form, "day Month year" for
	 * example &#x5DB;&#x5F4;&#x5D0; &#x5E9;&#x5D1;&#x5D8; &#x5EA;&#x5E9;&#x5DB;&#x5F4;&#x5D8;, and the format
	 * "21 Shevat, 5729" if not.
	 * 
	 * @param jewishDate
	 *            the JewishDate to be formatted
	 * @return the formatted date. If the formatter is set to Hebrew, it will format in the form, "day Month year" for
	 *         example &#x5DB;&#x5F4;&#x5D0; &#x5E9;&#x5D1;&#x5D8; &#x5EA;&#x5E9;&#x5DB;&#x5F4;&#x5D8;, and the format
	 *         "21 Shevat, 5729" if not.
	 */
	public String format(com.kosherjava.zmanim.hebrewcalendar.JewishDate jewishDate) {
		if (isHebrewFormat()) {
			return formatHebrewNumber(jewishDate.getJewishDayOfMonth()) + " " + formatMonth(jewishDate) + " "
					+ formatHebrewNumber(jewishDate.getJewishYear());
		} else {
			return jewishDate.getJewishDayOfMonth() + " " + formatMonth(jewishDate) + ", " + jewishDate.getJewishYear();
		}
	}

	/**
	 * Returns a string of the current Hebrew month such as "Tishrei". Returns a string of the current Hebrew month such
	 * as "&#x5D0;&#x5D3;&#x5E8; &#x5D1;&#x5F3;".
	 * 
	 * @param jewishDate
	 *            the JewishDate to format
	 * @return the formatted month name
	 * @see #isHebrewFormat()
	 * @see #setHebrewFormat(boolean)
	 * @see #getTransliteratedMonthList()
	 * @see #setTransliteratedMonthList(String[])
	 */
	public String formatMonth(com.kosherjava.zmanim.hebrewcalendar.JewishDate jewishDate) {
		final int month = jewishDate.getJewishMonth();
		if (isHebrewFormat()) {
			if (jewishDate.isJewishLeapYear() && month == JewishDate.ADAR) {
				return hebrewMonths[13] + (useGershGershayim ? GERESH : ""); // return Adar I, not Adar in a leap year
			} else if (jewishDate.isJewishLeapYear() && month == JewishDate.ADAR_II) {
				return hebrewMonths[12] + (useGershGershayim ? GERESH : "");
			} else {
				return hebrewMonths[month - 1];
			}
		} else {
			if (jewishDate.isJewishLeapYear() && month == JewishDate.ADAR) {
				return transliteratedMonths[13]; // return Adar I, not Adar in a leap year
			} else {
				return transliteratedMonths[month - 1];
			}
		}
	}

	/**
	 * Returns a String of the Omer day in the form &#x5DC;&#x5F4;&#x5D2; &#x5D1;&#x05E2;&#x05D5;&#x05DE;&#x5E8; if
	 * Hebrew Format is set, or "Omer X" or "Lag BaOmer" if not. An empty string if there is no Omer this day.
	 * 
	 * @param jewishCalendar
	 *            the JewishCalendar to be formatted
	 * 
	 * @return a String of the Omer day in the form or an empty string if there is no Omer this day. The default
	 *         formatting has a &#x5D1;&#x5F3; prefix that would output &#x5D1;&#x05E2;&#x05D5;&#x05DE;&#x5E8;, but this
	 *         can be set via the {@link #setHebrewOmerPrefix(String)} method to use a &#x5DC; and output
	 *         &#x5DC;&#x5F4;&#x5D2; &#x5DC;&#x05E2;&#x05D5;&#x05DE;&#x5E8;.
	 * @see #isHebrewFormat()
	 * @see #getHebrewOmerPrefix()
	 * @see #setHebrewOmerPrefix(String)
	 */
	public String formatOmer(com.kosherjava.zmanim.hebrewcalendar.JewishCalendar jewishCalendar) {
		int omer = jewishCalendar.getDayOfOmer();
		if (omer == -1) {
			return "";
		}
		if (hebrewFormat) {
			return formatHebrewNumber(omer) + " " + hebrewOmerPrefix + "\u05E2\u05D5\u05DE\u05E8";
		} else {
			if (omer == 33) { // if lag b'omer
				return "Lag BaOmer";
			} else {
				return "Omer " + omer;
			}
		}
	}

	/**
	 * Formats a molad.
	 * @todo Experimental and incomplete.
	 * 
	 * @param moladChalakim the chalakim of the molad
	 * @return the formatted molad. FIXME: define proper format in English and Hebrew.
	 */
	private static String formatMolad(long moladChalakim) {
		long adjustedChalakim = moladChalakim;
		int MINUTE_CHALAKIM = 18;
		int HOUR_CHALAKIM = 1080;
		int DAY_CHALAKIM = 24 * HOUR_CHALAKIM;

		long days = adjustedChalakim / DAY_CHALAKIM;
		adjustedChalakim = adjustedChalakim - (days * DAY_CHALAKIM);
		int hours = (int) ((adjustedChalakim / HOUR_CHALAKIM));
		if (hours >= 6) {
			days += 1;
		}
		adjustedChalakim = adjustedChalakim - (hours * (long) HOUR_CHALAKIM);
		int minutes = (int) (adjustedChalakim / MINUTE_CHALAKIM);
		adjustedChalakim = adjustedChalakim - minutes * (long) MINUTE_CHALAKIM;
		return "Day: " + days % 7 + " hours: " + hours + ", minutes " + minutes + ", chalakim: " + adjustedChalakim;
	}

	/**
	 * Returns the kviah in the traditional 3 letter Hebrew format where the first letter represents the day of week of
	 * Rosh Hashana, the second letter represents the lengths of Cheshvan and Kislev ({@link JewishDate#SHELAIMIM
	 * Shelaimim} , {@link JewishDate#KESIDRAN Kesidran} or {@link JewishDate#CHASERIM Chaserim}) and the 3rd letter
	 * represents the day of week of Pesach. For example 5729 (1969) would return &#x5D1;&#x5E9;&#x5D4; (Rosh Hashana on
	 * Monday, Shelaimim, and Pesach on Thursday), while 5771 (2011) would return &#x5D4;&#x5E9;&#x5D2; (Rosh Hashana on
	 * Thursday, Shelaimim, and Pesach on Tuesday).
	 * 
	 * @param jewishYear
	 *            the Jewish year
	 * @return the Hebrew String such as &#x5D1;&#x5E9;&#x5D4; for 5729 (1969) and &#x5D4;&#x5E9;&#x5D2; for 5771
	 *         (2011).
	 */
	public String getFormattedKviah(int jewishYear) {
		JewishDate jewishDate = new JewishDate(jewishYear, JewishDate.TISHREI, 1); // set date to Rosh Hashana
		int kviah = jewishDate.getCheshvanKislevKviah();
		int roshHashanaDayOfweek = jewishDate.getDayOfWeek();
		String returnValue = formatHebrewNumber(roshHashanaDayOfweek);
		returnValue += (kviah == JewishDate.CHASERIM ? "\u05D7" : kviah == JewishDate.SHELAIMIM ? "\u05E9" : "\u05DB");
		jewishDate.setJewishDate(jewishYear, JewishDate.NISSAN, 15); // set to Pesach of the given year
		int pesachDayOfweek = jewishDate.getDayOfWeek();
		returnValue += formatHebrewNumber(pesachDayOfweek);
		returnValue = returnValue.replaceAll(GERESH, "");// geresh is never used in the kviah format
		// boolean isLeapYear = JewishDate.isJewishLeapYear(jewishYear);
		// for efficiency we can avoid the expensive recalculation of the pesach day of week by adding 1 day to Rosh
		// Hashana for a 353 day year, 2 for a 354 day year, 3 for a 355 or 383 day year, 4 for a 384 day year and 5 for
		// a 385 day year
		return returnValue;
	}

	public String formatDafYomiBavli(Daf daf) {
		if (hebrewFormat) {
			return daf.getMasechta() + " " + formatHebrewNumber(daf.getDaf());
		} else {
			return daf.getMasechtaTransliterated() + " " + daf.getDaf();
		}
	}
	
	public String formatDafYomiYerushalmi(Daf daf) {
		if (hebrewFormat) {
			String dafName = daf.getDaf() == 0 ? "" : " " + formatHebrewNumber(daf.getDaf());
			return daf.getYerushalmiMasechta() + dafName;
		} else {
			String dafName = daf.getDaf() == 0 ? "" : " " + daf.getDaf();
			return daf.getYerushlmiMasechtaTransliterated() + dafName;
		}
	}

	/**
	 * Returns a Hebrew formatted string of a number. The method can calculate from 0 - 9999.
	 * <ul>
	 * <li>Single digit numbers such as 3, 30 and 100 will be returned with a &#x5F3; (<a
	 * href="http://en.wikipedia.org/wiki/Geresh">Geresh</a>) appended as at the end. For example &#x5D2;&#x5F3;,
	 * &#x5DC;&#x5F3; and &#x5E7;&#x5F3;</li>
	 * <li>multi digit numbers such as 21 and 769 will be returned with a &#x5F4; (<a
	 * href="http://en.wikipedia.org/wiki/Gershayim">Gershayim</a>) between the second to last and last letters. For
	 * example &#x5DB;&#x5F4;&#x5D0;, &#x5EA;&#x5E9;&#x5DB;&#x5F4;&#x5D8;</li>
	 * <li>15 and 16 will be returned as &#x5D8;&#x5F4;&#x5D5; and &#x5D8;&#x5F4;&#x5D6;</li>
	 * <li>Single digit numbers (years assumed) such as 6000 (%1000=0) will be returned as &#x5D5;&#x5F3;
	 * &#x5D0;&#x5DC;&#x5E4;&#x5D9;&#x5DD;</li>
	 * <li>0 will return &#x5D0;&#x5E4;&#x05E1;</li>
	 * </ul>
	 * 
	 * @param number
	 *            the number to be formatted. It will trow an IllegalArgumentException if the number is &lt; 0 or &gt; 9999.
	 * @return the Hebrew formatted number such as &#x5EA;&#x5E9;&#x5DB;&#x5F4;&#x5D8;
	 * @see #isUseFinalFormLetters()
	 * @see #isUseGershGershayim()
	 * @see #isHebrewFormat()
	 * 
	 */
	public String formatHebrewNumber(int number) {
		if (number < 0) {
			throw new IllegalArgumentException("negative numbers can't be formatted");
		} else if (number > 9999) {
			throw new IllegalArgumentException("numbers > 9999 can't be formatted");
		}

		String ALAFIM = "\u05D0\u05DC\u05E4\u05D9\u05DD";
		String EFES = "\u05D0\u05E4\u05E1";

		String[] jHundreds = new String[] { "", "\u05E7", "\u05E8", "\u05E9", "\u05EA", "\u05EA\u05E7", "\u05EA\u05E8",
				"\u05EA\u05E9", "\u05EA\u05EA", "\u05EA\u05EA\u05E7" };
		String[] jTens = new String[] { "", "\u05D9", "\u05DB", "\u05DC", "\u05DE", "\u05E0", "\u05E1", "\u05E2",
				"\u05E4", "\u05E6" };
		String[] jTenEnds = new String[] { "", "\u05D9", "\u05DA", "\u05DC", "\u05DD", "\u05DF", "\u05E1", "\u05E2",
				"\u05E3", "\u05E5" };
		String[] tavTaz = new String[] { "\u05D8\u05D5", "\u05D8\u05D6" };
		String[] jOnes = new String[] { "", "\u05D0", "\u05D1", "\u05D2", "\u05D3", "\u05D4", "\u05D5", "\u05D6",
				"\u05D7", "\u05D8" };

		if (number == 0) { // do we really need this? Should it be applicable to a date?
			return EFES;
		}
		int shortNumber = number % 1000; // discard thousands
		// next check for all possible single Hebrew digit years
		boolean singleDigitNumber = (shortNumber < 11 || (shortNumber < 100 && shortNumber % 10 == 0) || (shortNumber <= 400 && shortNumber % 100 == 0));
		int thousands = number / 1000; // get # thousands
		StringBuffer sb = new StringBuffer();
		// append thousands to String
		if (number % 1000 == 0) { // in year is 5000, 4000 etc
			sb.append(jOnes[thousands]);
			if (isUseGershGershayim()) {
				sb.append(GERESH);
			}
			sb.append(" ");
			sb.append(ALAFIM); // add # of thousands plus word thousand (overide alafim boolean)
			return sb.toString();
		} else if (useLonghebrewYears && number >= 1000) { // if alafim boolean display thousands
			sb.append(jOnes[thousands]);
			if (isUseGershGershayim()) {
				sb.append(GERESH); // append thousands quote
			}
			sb.append(" ");
		}
		number = number % 1000; // remove 1000s
		int hundreds = number / 100; // # of hundreds
		sb.append(jHundreds[hundreds]); // add hundreds to String
		number = number % 100; // remove 100s
		if (number == 15) { // special case 15
			sb.append(tavTaz[0]);
		} else if (number == 16) { // special case 16
			sb.append(tavTaz[1]);
		} else {
			int tens = number / 10;
			if (number % 10 == 0) { // if evenly divisable by 10
				if (!singleDigitNumber) {
					if(isUseFinalFormLetters()) {
						sb.append(jTenEnds[tens]); // years like 5780 will end with a final form &#x05E3;
					} else {
						sb.append(jTens[tens]); // years like 5780 will end with a regular &#x05E4;
					}
				} else {
					sb.append(jTens[tens]); // standard letters so years like 5050 will end with a regular nun
				}
			} else {
				sb.append(jTens[tens]);
				number = number % 10;
				sb.append(jOnes[number]);
			}
		}
		if (isUseGershGershayim()) {
			if (singleDigitNumber) {
				sb.append(GERESH); // append single quote
			} else { // append double quote before last digit
				sb.insert(sb.length() - 1, GERSHAYIM);
			}
		}
		return sb.toString();
	}	

	/**
	 * Retruns the list of transliterated parshiyos used by this formatter.
	 * 
	 * @return the list of transliterated Parshios
	 */
	public EnumMap<JewishCalendar.Parsha, String> getTransliteratedParshiosList() {
		return transliteratedParshaMap;
	}

	/**
	 * Setter method to allow overriding of the default list of parshiyos transliterated into into Latin chars. The
	 * default uses Ashkenazi American English transliteration.
	 * 
	 * @param transliteratedParshaMap
	 *            the transliterated Parshios as an EnumMap to set
	 * @see #getTransliteratedParshiosList()
	 */
	public void setTransliteratedParshiosList(EnumMap<JewishCalendar.Parsha, String> transliteratedParshaMap) {
		this.transliteratedParshaMap = transliteratedParshaMap;
	}
	
	/**
	 * Returns a String with the name of the current parsha(ios). If the formatter is set to format in Hebrew, returns
	 * a string of the current parsha(ios) in Hebrew for example &#x05D1;&#x05E8;&#x05D0;&#x05E9;&#x05D9;&#x05EA; or
	 * &#x05E0;&#x05D9;&#x05E6;&#x05D1;&#x05D9;&#x05DD; &#x05D5;&#x05D9;&#x05DC;&#x05DA; or an empty string if there
	 * are none. If not set to Hebrew, it returns a string of the parsha(ios) transliterated into Latin chars. The
	 * default uses Ashkenazi pronunciation in typical American English spelling, for example Bereshis or
	 * Nitzavim Vayeilech or an empty string if there are none.
	 * 
	 * @param jewishCalendar the JewishCalendar Object
	 * @return today's parsha(ios) in Hebrew for example, if the formatter is set to format in Hebrew, returns a string
	 *         of the current parsha(ios) in Hebrew for example &#x05D1;&#x05E8;&#x05D0;&#x05E9;&#x05D9;&#x05EA; or
	 *         &#x05E0;&#x05D9;&#x05E6;&#x05D1;&#x05D9;&#x05DD; &#x05D5;&#x05D9;&#x05DC;&#x05DA; or an empty string if
	 *         there are none. If not set to Hebrew, it returns a string of the parsha(ios) transliterated into Latin
	 *         chars. The default uses Ashkenazi pronunciation in typical American English spelling, for example
	 *         Bereshis or Nitzavim Vayeilech or an empty string if there are none.
	 */
	public String formatParsha(com.kosherjava.zmanim.hebrewcalendar.JewishCalendar jewishCalendar) {
		JewishCalendar.Parsha parsha =  jewishCalendar.getParshah();
		return hebrewFormat ? hebrewParshaMap.get(parsha) : transliteratedParshaMap.get(parsha);
	}
	
	/**
	 * Returns a String with the name of the current special parsha of Shekalim, Zachor, Parah or Hachodesh or an
	 * empty String for a non-special parsha. If the formatter is set to format in Hebrew, it returns a string of
	 * the current special parsha in Hebrew, for example &#x05E9;&#x05E7;&#x05DC;&#x05D9;&#x05DD;,
	 * &#x05D6;&#x05DB;&#x05D5;&#x05E8;, &#x05E4;&#x05E8;&#x05D4; or &#x05D4;&#x05D7;&#x05D3;&#x05E9;. An empty
	 * string if the date is not a special parsha. If not set to Hebrew, it returns a string of the special parsha
	 * transliterated into Latin chars. The default uses Ashkenazi pronunciation in typical American English spelling
	 * Shekalim, Zachor, Parah or Hachodesh.
	 * 
	 * @param jewishCalendar the JewishCalendar Object
	 * @return today's special parsha. If the formatter is set to format in Hebrew, returns a string
	 *         of the current special parsha  in Hebrew for in the format of &#x05E9;&#x05E7;&#x05DC;&#x05D9;&#x05DD;,
	 *         &#x05D6;&#x05DB;&#x05D5;&#x05E8;, &#x05E4;&#x05E8;&#x05D4; or &#x05D4;&#x05D7;&#x05D3;&#x05E9; or an empty
	 *         string if there are none. If not set to Hebrew, it returns a string of the special parsha transliterated
	 *         into Latin chars. The default uses Ashkenazi pronunciation in typical American English spelling of Shekalim,
	 *         Zachor, Parah or Hachodesh. An empty string if there are none.
	 */
	public String formatSpecialParsha(com.kosherjava.zmanim.hebrewcalendar.JewishCalendar jewishCalendar) {
		JewishCalendar.Parsha specialParsha =  jewishCalendar.getSpecialShabbos();
		return hebrewFormat ? hebrewParshaMap.get(specialParsha) : transliteratedParshaMap.get(specialParsha);
	}
}
