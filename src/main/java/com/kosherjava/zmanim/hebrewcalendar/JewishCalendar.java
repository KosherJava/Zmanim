/*
 * Zmanim Java API
 * Copyright (C) 2011 - 2021 Eliyahu Hershfeld
 * Copyright (C) September 2002 Avrom Finkelstien
 * Copyright (C) 2019 - 2021 Y Paritcher
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

import com.kosherjava.zmanim.util.GeoLocation;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * The JewishCalendar extends the JewishDate class and adds calendar methods.
 * 
 * This open source Java code was originally ported by <a href="http://www.facebook.com/avromf">Avrom Finkelstien</a>
 * from his C++ code. It was refactored to fit the KosherJava Zmanim API with simplification of the code, enhancements
 * and some bug fixing. The class allows setting whether the holiday and parsha scheme follows the Israel scheme or outside Israel
 * scheme. The default is the outside Israel scheme.
 * The parsha code was ported by Y. Paritcher from his <a href="https://github.com/yparitcher/libzmanim">libzmanim</a> code.
 * 
 * @todo Some do not belong in this class, but here is a partial list of what should still be implemented in some form:
 * <ol>
 * <li>Add Isru Chag</li>
 * <li>Mishna yomis etc</li>
 * </ol>
 * 
 * @see java.util.Date
 * @see java.util.Calendar
 * @author &copy; Y. Paritcher 2019 - 2021
 * @author &copy; Avrom Finkelstien 2002
 * @author &copy; Eliyahu Hershfeld 2011 - 2021
 */
public class JewishCalendar extends JewishDate {
	/** The 14th day of Nisan, the day before of Pesach (Passover).*/
	public static final int EREV_PESACH = 0;
	/** The holiday of Pesach (Passover) on the 15th (and 16th out of Israel) day of Nisan.*/
	public static final int PESACH = 1;
	/** Chol Hamoed (interim days) of Pesach (Passover)*/
	public static final int CHOL_HAMOED_PESACH = 2;
	/**Pesach Sheni, the 14th day of Iyar, a minor holiday.*/
	public static final int PESACH_SHENI = 3;
	/**Erev Shavuos (the day before Shavuos), the 5th of Sivan*/
	public static final int EREV_SHAVUOS = 4;
	/**Shavuos (Pentecost), the 6th of Sivan*/
	public static final int SHAVUOS = 5;
	/** The fast of the 17th day of Tamuz*/
	public static final int SEVENTEEN_OF_TAMMUZ = 6;
	/** The fast of the 9th of Av*/
	public static final int TISHA_BEAV = 7;
	/** The 15th day of Av, a minor holiday*/
	public static final int TU_BEAV = 8;
	/**Erev Rosh Hashana (the day before Rosh Hashana), the 29th of Elul*/
	public static final int EREV_ROSH_HASHANA = 9;
	/** Rosh Hashana, the first of Tishrei.*/
	public static final int ROSH_HASHANA = 10;
	/** The fast of Gedalyah, the 3rd of Tishrei.*/
	public static final int FAST_OF_GEDALYAH = 11;
	/** The 9th day of Tishrei, the day before of Yom Kippur.*/
	public static final int EREV_YOM_KIPPUR = 12;
	/** The holiday of Yom Kippur, the 10th day of Tishrei*/
	public static final int YOM_KIPPUR = 13;
	/** The 14th day of Tishrei, the day before of Succos/Sukkos (Tabernacles).*/
	public static final int EREV_SUCCOS = 14;
	/** The holiday of Succos/Sukkos (Tabernacles), the 15th (and 16th out of Israel) day of Tishrei */
	public static final int SUCCOS = 15;
	/** Chol Hamoed (interim days) of Succos/Sukkos (Tabernacles)*/
	public static final int CHOL_HAMOED_SUCCOS = 16;
	/** Hoshana Rabba, the 7th day of Succos/Sukkos that occurs on the 21st of Tishrei. */
	public static final int HOSHANA_RABBA = 17;
	/** Shmini Atzeres, the 8th day of Succos/Sukkos is an independent holiday that occurs on the 22nd of Tishrei. */
	public static final int SHEMINI_ATZERES = 18;
	/** Simchas Torah, the 9th day of Succos/Sukkos, or the second day of Shmini Atzeres that is celebrated
	 * {@link #getInIsrael() out of Israel} on the 23rd of Tishrei.
	 */
	public static final int SIMCHAS_TORAH = 19;
	// public static final int EREV_CHANUKAH = 20;// probably remove this
	/** The holiday of Chanukah. 8 days starting on the 25th day Kislev.*/
	public static final int CHANUKAH = 21;
	/** The fast of the 10th day of Teves.*/
	public static final int TENTH_OF_TEVES = 22;
	/** Tu Bishvat on the 15th day of Shevat, a minor holiday.*/
	public static final int TU_BESHVAT = 23;
	/** The fast of Esther, usually on the 13th day of Adar (or Adar II on leap years). It is earlier on some years.*/
	public static final int FAST_OF_ESTHER = 24;
	/** The holiday of Purim on the 14th day of Adar (or Adar II on leap years).*/
	public static final int PURIM = 25;
	/** The holiday of Shushan Purim on the 15th day of Adar (or Adar II on leap years).*/
	public static final int SHUSHAN_PURIM = 26;
	/** The holiday of Purim Katan on the 14th day of Adar I on a leap year when Purim is on Adar II, a minor holiday.*/
	public static final int PURIM_KATAN = 27;
	/**
	 * Rosh Chodesh, the new moon on the first day of the Jewish month, and the 30th day of the previous month in the
	 * case of a month with 30 days.
	 */
	public static final int ROSH_CHODESH = 28;
	/** Yom HaShoah, Holocaust Remembrance Day, usually held on the 27th of Nisan. If it falls on a Friday, it is moved
	 * to the 26th, and if it falls on a Sunday it is moved to the 28th. A {@link #isUseModernHolidays() modern holiday}.
	 */
	public static final int YOM_HASHOAH = 29;
	/**
	 * Yom HaZikaron, Israeli Memorial Day, held a day before Yom Ha'atzmaut.  A {@link #isUseModernHolidays() modern holiday}.
	 */
	public static final int YOM_HAZIKARON = 30;
	
	/** Yom Ha'atzmaut, Israel Independence Day, the 5th of Iyar, but if it occurs on a Friday or Saturday, the holiday is
	 * moved back to Thursday, the 3rd of 4th of Iyar, and if it falls on a Monday, it is moved forward to Tuesday the
	 * 6th of Iyar.  A {@link #isUseModernHolidays() modern holiday}.*/
	public static final int YOM_HAATZMAUT = 31;
	/**
	 * Yom Yerushalayim or Jerusalem Day, on 28 Iyar. A {@link #isUseModernHolidays() modern holiday}.
	 */
	public static final int YOM_YERUSHALAYIM = 32;
	
	/** The 33rd day of the Omer, the 18th of Iyar, a minor holiday.*/
	public static final int LAG_BAOMER = 33;
	
	/** The holiday of Purim Katan on the 15th day of Adar I on a leap year when Purim is on Adar II, a minor holiday.*/
	public static final int SHUSHAN_PURIM_KATAN = 34;

	/**
	 * Is the calendar set to Israel, where some holidays have different rules.
	 * @see #getInIsrael()
	 * @see #setInIsrael(boolean)
	 */
	private boolean inIsrael = false;
	
	/**
	 * Is the calendar set to use modern Israeli holidays such as Yom Haatzmaut.
	 * @see #isUseModernHolidays()
	 * @see #setUseModernHolidays(boolean)
	 */
	private boolean useModernHolidays = false;

	/**
	 * List of <em>parshiyos</em>. {@link #NONE} indicates a week without a <em>parsha</em>, while the enum for the <em>parsha</em> of
	 * {@link #VZOS_HABERACHA} exists for consistency, but is not currently used.
	 */
	public static enum Parsha {
		/**NONE A week without any <em>parsha</em> such as <em>Shabbos Chol Hamoed</em> */NONE,
		/**BERESHIS*/BERESHIS, /**NOACH*/NOACH, /**LECH_LECHA*/LECH_LECHA, /**VAYERA*/VAYERA, /**CHAYEI_SARA*/CHAYEI_SARA, /**TOLDOS*/TOLDOS, 
		/**VAYETZEI*/VAYETZEI, /**VAYISHLACH*/VAYISHLACH, /**VAYESHEV*/VAYESHEV, /**MIKETZ*/MIKETZ, /**VAYIGASH*/VAYIGASH, /**VAYECHI*/VAYECHI,
		/**SHEMOS*/SHEMOS, /**VAERA*/VAERA, /**BO*/BO, /**BESHALACH*/BESHALACH, /**YISRO*/YISRO, /**MISHPATIM*/MISHPATIM, /**TERUMAH*/TERUMAH,
		/**TETZAVEH*/TETZAVEH, /***KI_SISA*/KI_SISA, /**VAYAKHEL*/VAYAKHEL, /**PEKUDEI*/PEKUDEI, /**VAYIKRA*/VAYIKRA, /**TZAV*/TZAV, /**SHMINI*/SHMINI, 
		/**TAZRIA*/TAZRIA, /**METZORA*/METZORA, /**ACHREI_MOS*/ACHREI_MOS, /**KEDOSHIM*/KEDOSHIM, /**EMOR*/EMOR, /**BEHAR*/BEHAR, /**BECHUKOSAI*/BECHUKOSAI,
		/**BAMIDBAR*/BAMIDBAR, /**NASSO*/NASSO, /**BEHAALOSCHA*/BEHAALOSCHA, /**SHLACH*/SHLACH, /**KORACH*/KORACH, /**CHUKAS*/CHUKAS, /**BALAK*/BALAK,
		/**PINCHAS*/PINCHAS, /**MATOS*/MATOS, /**MASEI*/MASEI, /**DEVARIM*/DEVARIM, /**VAESCHANAN*/VAESCHANAN, /**EIKEV*/EIKEV, /**REEH*/REEH,
		/**SHOFTIM*/SHOFTIM, /**KI_SEITZEI*/KI_SEITZEI, /**KI_SAVO*/KI_SAVO, /**NITZAVIM*/NITZAVIM, /**VAYEILECH*/VAYEILECH, /**HAAZINU*/HAAZINU,
		/**VZOS_HABERACHA*/VZOS_HABERACHA, /**The double parsha of Vayakhel &amp; Peudei*/VAYAKHEL_PEKUDEI, /**The double <em>parsha</em> of Tazria
		 * &amp; Metzora*/TAZRIA_METZORA,/**The double <em>parsha</em> of Achrei Mos &amp; Kedoshim*/ACHREI_MOS_KEDOSHIM,/**The double <em>parsha</em>
		 * of Behar &amp; Bechukosai*/BEHAR_BECHUKOSAI,/**The double <em>parsha</em> of Chukas &amp; Balak*/CHUKAS_BALAK, /**The double
		 * <em>parsha</em> of Matos &amp; Masei*/MATOS_MASEI,/**The double <em>parsha</em> of Nitzavim &amp; Vayelech*/NITZAVIM_VAYEILECH,
		 /**The special <em>parsha</em> of Shekalim*/SHKALIM, /** The special <em>parsha</em> of Zachor*/ZACHOR, /**The special <em>parsha</em> of
		  * Para*/PARA, /** The special <em>parsha</em> of Hachodesh*/HACHODESH
	};
	
	/**
	 * An array of <em>parshiyos</em> in the 17 possible combinations.
	 */
	public static final Parsha[][] parshalist = {
		{Parsha.NONE, Parsha.VAYEILECH, Parsha.HAAZINU, Parsha.NONE, Parsha.BERESHIS, Parsha.NOACH, Parsha.LECH_LECHA, Parsha.VAYERA, Parsha.CHAYEI_SARA, Parsha.TOLDOS, Parsha.VAYETZEI, Parsha.VAYISHLACH, Parsha.VAYESHEV, Parsha.MIKETZ, Parsha.VAYIGASH, Parsha.VAYECHI, Parsha.SHEMOS, Parsha.VAERA, Parsha.BO, Parsha.BESHALACH, Parsha.YISRO, Parsha.MISHPATIM, Parsha.TERUMAH, Parsha.TETZAVEH, Parsha.KI_SISA, Parsha.VAYAKHEL_PEKUDEI, Parsha.VAYIKRA, Parsha.TZAV, Parsha.NONE, Parsha.SHMINI, Parsha.TAZRIA_METZORA, Parsha.ACHREI_MOS_KEDOSHIM, Parsha.EMOR, Parsha.BEHAR_BECHUKOSAI, Parsha.BAMIDBAR, Parsha.NASSO, Parsha.BEHAALOSCHA, Parsha.SHLACH, Parsha.KORACH, Parsha.CHUKAS, Parsha.BALAK, Parsha.PINCHAS, Parsha.MATOS_MASEI, Parsha.DEVARIM, Parsha.VAESCHANAN, Parsha.EIKEV, Parsha.REEH, Parsha.SHOFTIM, Parsha.KI_SEITZEI, Parsha.KI_SAVO, Parsha.NITZAVIM_VAYEILECH},
		{Parsha.NONE, Parsha.VAYEILECH, Parsha.HAAZINU, Parsha.NONE, Parsha.BERESHIS, Parsha.NOACH, Parsha.LECH_LECHA, Parsha.VAYERA, Parsha.CHAYEI_SARA, Parsha.TOLDOS, Parsha.VAYETZEI, Parsha.VAYISHLACH, Parsha.VAYESHEV, Parsha.MIKETZ, Parsha.VAYIGASH, Parsha.VAYECHI, Parsha.SHEMOS, Parsha.VAERA, Parsha.BO, Parsha.BESHALACH, Parsha.YISRO, Parsha.MISHPATIM, Parsha.TERUMAH, Parsha.TETZAVEH, Parsha.KI_SISA, Parsha.VAYAKHEL_PEKUDEI, Parsha.VAYIKRA, Parsha.TZAV, Parsha.NONE, Parsha.SHMINI, Parsha.TAZRIA_METZORA, Parsha.ACHREI_MOS_KEDOSHIM, Parsha.EMOR, Parsha.BEHAR_BECHUKOSAI, Parsha.BAMIDBAR, Parsha.NONE, Parsha.NASSO, Parsha.BEHAALOSCHA, Parsha.SHLACH, Parsha.KORACH, Parsha.CHUKAS_BALAK, Parsha.PINCHAS, Parsha.MATOS_MASEI, Parsha.DEVARIM, Parsha.VAESCHANAN, Parsha.EIKEV, Parsha.REEH, Parsha.SHOFTIM, Parsha.KI_SEITZEI, Parsha.KI_SAVO, Parsha.NITZAVIM_VAYEILECH},
		{Parsha.NONE, Parsha.HAAZINU, Parsha.NONE, Parsha.NONE, Parsha.BERESHIS, Parsha.NOACH, Parsha.LECH_LECHA, Parsha.VAYERA, Parsha.CHAYEI_SARA, Parsha.TOLDOS, Parsha.VAYETZEI, Parsha.VAYISHLACH, Parsha.VAYESHEV, Parsha.MIKETZ, Parsha.VAYIGASH, Parsha.VAYECHI, Parsha.SHEMOS, Parsha.VAERA, Parsha.BO, Parsha.BESHALACH, Parsha.YISRO, Parsha.MISHPATIM, Parsha.TERUMAH, Parsha.TETZAVEH, Parsha.KI_SISA, Parsha.VAYAKHEL_PEKUDEI, Parsha.VAYIKRA, Parsha.TZAV, Parsha.NONE, Parsha.NONE, Parsha.SHMINI, Parsha.TAZRIA_METZORA, Parsha.ACHREI_MOS_KEDOSHIM, Parsha.EMOR, Parsha.BEHAR_BECHUKOSAI, Parsha.BAMIDBAR, Parsha.NASSO, Parsha.BEHAALOSCHA, Parsha.SHLACH, Parsha.KORACH, Parsha.CHUKAS, Parsha.BALAK, Parsha.PINCHAS, Parsha.MATOS_MASEI, Parsha.DEVARIM, Parsha.VAESCHANAN, Parsha.EIKEV, Parsha.REEH, Parsha.SHOFTIM, Parsha.KI_SEITZEI, Parsha.KI_SAVO, Parsha.NITZAVIM},
		{Parsha.NONE, Parsha.HAAZINU, Parsha.NONE, Parsha.NONE, Parsha.BERESHIS, Parsha.NOACH, Parsha.LECH_LECHA, Parsha.VAYERA, Parsha.CHAYEI_SARA, Parsha.TOLDOS, Parsha.VAYETZEI, Parsha.VAYISHLACH, Parsha.VAYESHEV, Parsha.MIKETZ, Parsha.VAYIGASH, Parsha.VAYECHI, Parsha.SHEMOS, Parsha.VAERA, Parsha.BO, Parsha.BESHALACH, Parsha.YISRO, Parsha.MISHPATIM, Parsha.TERUMAH, Parsha.TETZAVEH, Parsha.KI_SISA, Parsha.VAYAKHEL, Parsha.PEKUDEI, Parsha.VAYIKRA, Parsha.TZAV, Parsha.NONE, Parsha.SHMINI, Parsha.TAZRIA_METZORA, Parsha.ACHREI_MOS_KEDOSHIM, Parsha.EMOR, Parsha.BEHAR_BECHUKOSAI, Parsha.BAMIDBAR, Parsha.NASSO, Parsha.BEHAALOSCHA, Parsha.SHLACH, Parsha.KORACH, Parsha.CHUKAS, Parsha.BALAK, Parsha.PINCHAS, Parsha.MATOS_MASEI, Parsha.DEVARIM, Parsha.VAESCHANAN, Parsha.EIKEV, Parsha.REEH, Parsha.SHOFTIM, Parsha.KI_SEITZEI, Parsha.KI_SAVO, Parsha.NITZAVIM},
		{Parsha.NONE, Parsha.NONE, Parsha.HAAZINU, Parsha.NONE, Parsha.NONE, Parsha.BERESHIS, Parsha.NOACH, Parsha.LECH_LECHA, Parsha.VAYERA, Parsha.CHAYEI_SARA, Parsha.TOLDOS, Parsha.VAYETZEI, Parsha.VAYISHLACH, Parsha.VAYESHEV, Parsha.MIKETZ, Parsha.VAYIGASH, Parsha.VAYECHI, Parsha.SHEMOS, Parsha.VAERA, Parsha.BO, Parsha.BESHALACH, Parsha.YISRO, Parsha.MISHPATIM, Parsha.TERUMAH, Parsha.TETZAVEH, Parsha.KI_SISA, Parsha.VAYAKHEL_PEKUDEI, Parsha.VAYIKRA, Parsha.TZAV, Parsha.NONE, Parsha.SHMINI, Parsha.TAZRIA_METZORA, Parsha.ACHREI_MOS_KEDOSHIM, Parsha.EMOR, Parsha.BEHAR_BECHUKOSAI, Parsha.BAMIDBAR, Parsha.NASSO, Parsha.BEHAALOSCHA, Parsha.SHLACH, Parsha.KORACH, Parsha.CHUKAS, Parsha.BALAK, Parsha.PINCHAS, Parsha.MATOS_MASEI, Parsha.DEVARIM, Parsha.VAESCHANAN, Parsha.EIKEV, Parsha.REEH, Parsha.SHOFTIM, Parsha.KI_SEITZEI, Parsha.KI_SAVO, Parsha.NITZAVIM},
		{Parsha.NONE, Parsha.NONE, Parsha.HAAZINU, Parsha.NONE, Parsha.NONE, Parsha.BERESHIS, Parsha.NOACH, Parsha.LECH_LECHA, Parsha.VAYERA, Parsha.CHAYEI_SARA, Parsha.TOLDOS, Parsha.VAYETZEI, Parsha.VAYISHLACH, Parsha.VAYESHEV, Parsha.MIKETZ, Parsha.VAYIGASH, Parsha.VAYECHI, Parsha.SHEMOS, Parsha.VAERA, Parsha.BO, Parsha.BESHALACH, Parsha.YISRO, Parsha.MISHPATIM, Parsha.TERUMAH, Parsha.TETZAVEH, Parsha.KI_SISA, Parsha.VAYAKHEL_PEKUDEI, Parsha.VAYIKRA, Parsha.TZAV, Parsha.NONE, Parsha.SHMINI, Parsha.TAZRIA_METZORA, Parsha.ACHREI_MOS_KEDOSHIM, Parsha.EMOR, Parsha.BEHAR_BECHUKOSAI, Parsha.BAMIDBAR, Parsha.NASSO, Parsha.BEHAALOSCHA, Parsha.SHLACH, Parsha.KORACH, Parsha.CHUKAS, Parsha.BALAK, Parsha.PINCHAS, Parsha.MATOS_MASEI, Parsha.DEVARIM, Parsha.VAESCHANAN, Parsha.EIKEV, Parsha.REEH, Parsha.SHOFTIM, Parsha.KI_SEITZEI, Parsha.KI_SAVO, Parsha.NITZAVIM_VAYEILECH},
		{Parsha.NONE, Parsha.VAYEILECH, Parsha.HAAZINU, Parsha.NONE, Parsha.BERESHIS, Parsha.NOACH, Parsha.LECH_LECHA, Parsha.VAYERA, Parsha.CHAYEI_SARA, Parsha.TOLDOS, Parsha.VAYETZEI, Parsha.VAYISHLACH, Parsha.VAYESHEV, Parsha.MIKETZ, Parsha.VAYIGASH, Parsha.VAYECHI, Parsha.SHEMOS, Parsha.VAERA, Parsha.BO, Parsha.BESHALACH, Parsha.YISRO, Parsha.MISHPATIM, Parsha.TERUMAH, Parsha.TETZAVEH, Parsha.KI_SISA, Parsha.VAYAKHEL, Parsha.PEKUDEI, Parsha.VAYIKRA, Parsha.TZAV, Parsha.SHMINI, Parsha.TAZRIA, Parsha.METZORA, Parsha.NONE, Parsha.ACHREI_MOS, Parsha.KEDOSHIM, Parsha.EMOR, Parsha.BEHAR, Parsha.BECHUKOSAI, Parsha.BAMIDBAR, Parsha.NONE, Parsha.NASSO, Parsha.BEHAALOSCHA, Parsha.SHLACH, Parsha.KORACH, Parsha.CHUKAS_BALAK, Parsha.PINCHAS, Parsha.MATOS_MASEI, Parsha.DEVARIM, Parsha.VAESCHANAN, Parsha.EIKEV, Parsha.REEH, Parsha.SHOFTIM, Parsha.KI_SEITZEI, Parsha.KI_SAVO, Parsha.NITZAVIM_VAYEILECH},
		{Parsha.NONE, Parsha.VAYEILECH, Parsha.HAAZINU, Parsha.NONE, Parsha.BERESHIS, Parsha.NOACH, Parsha.LECH_LECHA, Parsha.VAYERA, Parsha.CHAYEI_SARA, Parsha.TOLDOS, Parsha.VAYETZEI, Parsha.VAYISHLACH, Parsha.VAYESHEV, Parsha.MIKETZ, Parsha.VAYIGASH, Parsha.VAYECHI, Parsha.SHEMOS, Parsha.VAERA, Parsha.BO, Parsha.BESHALACH, Parsha.YISRO, Parsha.MISHPATIM, Parsha.TERUMAH, Parsha.TETZAVEH, Parsha.KI_SISA, Parsha.VAYAKHEL, Parsha.PEKUDEI, Parsha.VAYIKRA, Parsha.TZAV, Parsha.SHMINI, Parsha.TAZRIA, Parsha.METZORA, Parsha.NONE, Parsha.NONE, Parsha.ACHREI_MOS, Parsha.KEDOSHIM, Parsha.EMOR, Parsha.BEHAR, Parsha.BECHUKOSAI, Parsha.BAMIDBAR, Parsha.NASSO, Parsha.BEHAALOSCHA, Parsha.SHLACH, Parsha.KORACH, Parsha.CHUKAS, Parsha.BALAK, Parsha.PINCHAS, Parsha.MATOS_MASEI, Parsha.DEVARIM, Parsha.VAESCHANAN, Parsha.EIKEV, Parsha.REEH, Parsha.SHOFTIM, Parsha.KI_SEITZEI, Parsha.KI_SAVO, Parsha.NITZAVIM},
		{Parsha.NONE, Parsha.HAAZINU, Parsha.NONE, Parsha.NONE, Parsha.BERESHIS, Parsha.NOACH, Parsha.LECH_LECHA, Parsha.VAYERA, Parsha.CHAYEI_SARA, Parsha.TOLDOS, Parsha.VAYETZEI, Parsha.VAYISHLACH, Parsha.VAYESHEV, Parsha.MIKETZ, Parsha.VAYIGASH, Parsha.VAYECHI, Parsha.SHEMOS, Parsha.VAERA, Parsha.BO, Parsha.BESHALACH, Parsha.YISRO, Parsha.MISHPATIM, Parsha.TERUMAH, Parsha.TETZAVEH, Parsha.KI_SISA, Parsha.VAYAKHEL, Parsha.PEKUDEI, Parsha.VAYIKRA, Parsha.TZAV, Parsha.SHMINI, Parsha.TAZRIA, Parsha.METZORA, Parsha.ACHREI_MOS, Parsha.NONE, Parsha.KEDOSHIM, Parsha.EMOR, Parsha.BEHAR, Parsha.BECHUKOSAI, Parsha.BAMIDBAR, Parsha.NASSO, Parsha.BEHAALOSCHA, Parsha.SHLACH, Parsha.KORACH, Parsha.CHUKAS, Parsha.BALAK, Parsha.PINCHAS, Parsha.MATOS, Parsha.MASEI, Parsha.DEVARIM, Parsha.VAESCHANAN, Parsha.EIKEV, Parsha.REEH, Parsha.SHOFTIM, Parsha.KI_SEITZEI, Parsha.KI_SAVO, Parsha.NITZAVIM},
		{Parsha.NONE, Parsha.HAAZINU, Parsha.NONE, Parsha.NONE, Parsha.BERESHIS, Parsha.NOACH, Parsha.LECH_LECHA, Parsha.VAYERA, Parsha.CHAYEI_SARA, Parsha.TOLDOS, Parsha.VAYETZEI, Parsha.VAYISHLACH, Parsha.VAYESHEV, Parsha.MIKETZ, Parsha.VAYIGASH, Parsha.VAYECHI, Parsha.SHEMOS, Parsha.VAERA, Parsha.BO, Parsha.BESHALACH, Parsha.YISRO, Parsha.MISHPATIM, Parsha.TERUMAH, Parsha.TETZAVEH, Parsha.KI_SISA, Parsha.VAYAKHEL, Parsha.PEKUDEI, Parsha.VAYIKRA, Parsha.TZAV, Parsha.SHMINI, Parsha.TAZRIA, Parsha.METZORA, Parsha.ACHREI_MOS, Parsha.NONE, Parsha.KEDOSHIM, Parsha.EMOR, Parsha.BEHAR, Parsha.BECHUKOSAI, Parsha.BAMIDBAR, Parsha.NASSO, Parsha.BEHAALOSCHA, Parsha.SHLACH, Parsha.KORACH, Parsha.CHUKAS, Parsha.BALAK, Parsha.PINCHAS, Parsha.MATOS, Parsha.MASEI, Parsha.DEVARIM, Parsha.VAESCHANAN, Parsha.EIKEV, Parsha.REEH, Parsha.SHOFTIM, Parsha.KI_SEITZEI, Parsha.KI_SAVO, Parsha.NITZAVIM_VAYEILECH},
		{Parsha.NONE, Parsha.NONE, Parsha.HAAZINU, Parsha.NONE, Parsha.NONE, Parsha.BERESHIS, Parsha.NOACH, Parsha.LECH_LECHA, Parsha.VAYERA, Parsha.CHAYEI_SARA, Parsha.TOLDOS, Parsha.VAYETZEI, Parsha.VAYISHLACH, Parsha.VAYESHEV, Parsha.MIKETZ, Parsha.VAYIGASH, Parsha.VAYECHI, Parsha.SHEMOS, Parsha.VAERA, Parsha.BO, Parsha.BESHALACH, Parsha.YISRO, Parsha.MISHPATIM, Parsha.TERUMAH, Parsha.TETZAVEH, Parsha.KI_SISA, Parsha.VAYAKHEL, Parsha.PEKUDEI, Parsha.VAYIKRA, Parsha.TZAV, Parsha.SHMINI, Parsha.TAZRIA, Parsha.METZORA, Parsha.NONE, Parsha.ACHREI_MOS, Parsha.KEDOSHIM, Parsha.EMOR, Parsha.BEHAR, Parsha.BECHUKOSAI, Parsha.BAMIDBAR, Parsha.NASSO, Parsha.BEHAALOSCHA, Parsha.SHLACH, Parsha.KORACH, Parsha.CHUKAS, Parsha.BALAK, Parsha.PINCHAS, Parsha.MATOS_MASEI, Parsha.DEVARIM, Parsha.VAESCHANAN, Parsha.EIKEV, Parsha.REEH, Parsha.SHOFTIM, Parsha.KI_SEITZEI, Parsha.KI_SAVO, Parsha.NITZAVIM_VAYEILECH},
		{Parsha.NONE, Parsha.NONE, Parsha.HAAZINU, Parsha.NONE, Parsha.NONE, Parsha.BERESHIS, Parsha.NOACH, Parsha.LECH_LECHA, Parsha.VAYERA, Parsha.CHAYEI_SARA, Parsha.TOLDOS, Parsha.VAYETZEI, Parsha.VAYISHLACH, Parsha.VAYESHEV, Parsha.MIKETZ, Parsha.VAYIGASH, Parsha.VAYECHI, Parsha.SHEMOS, Parsha.VAERA, Parsha.BO, Parsha.BESHALACH, Parsha.YISRO, Parsha.MISHPATIM, Parsha.TERUMAH, Parsha.TETZAVEH, Parsha.KI_SISA, Parsha.VAYAKHEL, Parsha.PEKUDEI, Parsha.VAYIKRA, Parsha.TZAV, Parsha.SHMINI, Parsha.TAZRIA, Parsha.METZORA, Parsha.NONE, Parsha.ACHREI_MOS, Parsha.KEDOSHIM, Parsha.EMOR, Parsha.BEHAR, Parsha.BECHUKOSAI, Parsha.BAMIDBAR, Parsha.NONE, Parsha.NASSO, Parsha.BEHAALOSCHA, Parsha.SHLACH, Parsha.KORACH, Parsha.CHUKAS_BALAK, Parsha.PINCHAS, Parsha.MATOS_MASEI, Parsha.DEVARIM, Parsha.VAESCHANAN, Parsha.EIKEV, Parsha.REEH, Parsha.SHOFTIM, Parsha.KI_SEITZEI, Parsha.KI_SAVO, Parsha.NITZAVIM_VAYEILECH},
		{Parsha.NONE, Parsha.VAYEILECH, Parsha.HAAZINU, Parsha.NONE, Parsha.BERESHIS, Parsha.NOACH, Parsha.LECH_LECHA, Parsha.VAYERA, Parsha.CHAYEI_SARA, Parsha.TOLDOS, Parsha.VAYETZEI, Parsha.VAYISHLACH, Parsha.VAYESHEV, Parsha.MIKETZ, Parsha.VAYIGASH, Parsha.VAYECHI, Parsha.SHEMOS, Parsha.VAERA, Parsha.BO, Parsha.BESHALACH, Parsha.YISRO, Parsha.MISHPATIM, Parsha.TERUMAH, Parsha.TETZAVEH, Parsha.KI_SISA, Parsha.VAYAKHEL_PEKUDEI, Parsha.VAYIKRA, Parsha.TZAV, Parsha.NONE, Parsha.SHMINI, Parsha.TAZRIA_METZORA, Parsha.ACHREI_MOS_KEDOSHIM, Parsha.EMOR, Parsha.BEHAR_BECHUKOSAI, Parsha.BAMIDBAR, Parsha.NASSO, Parsha.BEHAALOSCHA, Parsha.SHLACH, Parsha.KORACH, Parsha.CHUKAS, Parsha.BALAK, Parsha.PINCHAS, Parsha.MATOS_MASEI, Parsha.DEVARIM, Parsha.VAESCHANAN, Parsha.EIKEV, Parsha.REEH, Parsha.SHOFTIM, Parsha.KI_SEITZEI, Parsha.KI_SAVO, Parsha.NITZAVIM_VAYEILECH},
		{Parsha.NONE, Parsha.HAAZINU, Parsha.NONE, Parsha.NONE, Parsha.BERESHIS, Parsha.NOACH, Parsha.LECH_LECHA, Parsha.VAYERA, Parsha.CHAYEI_SARA, Parsha.TOLDOS, Parsha.VAYETZEI, Parsha.VAYISHLACH, Parsha.VAYESHEV, Parsha.MIKETZ, Parsha.VAYIGASH, Parsha.VAYECHI, Parsha.SHEMOS, Parsha.VAERA, Parsha.BO, Parsha.BESHALACH, Parsha.YISRO, Parsha.MISHPATIM, Parsha.TERUMAH, Parsha.TETZAVEH, Parsha.KI_SISA, Parsha.VAYAKHEL_PEKUDEI, Parsha.VAYIKRA, Parsha.TZAV, Parsha.NONE, Parsha.SHMINI, Parsha.TAZRIA_METZORA, Parsha.ACHREI_MOS_KEDOSHIM, Parsha.EMOR, Parsha.BEHAR, Parsha.BECHUKOSAI, Parsha.BAMIDBAR, Parsha.NASSO, Parsha.BEHAALOSCHA, Parsha.SHLACH, Parsha.KORACH, Parsha.CHUKAS, Parsha.BALAK, Parsha.PINCHAS, Parsha.MATOS_MASEI, Parsha.DEVARIM, Parsha.VAESCHANAN, Parsha.EIKEV, Parsha.REEH, Parsha.SHOFTIM, Parsha.KI_SEITZEI, Parsha.KI_SAVO, Parsha.NITZAVIM},
		{Parsha.NONE, Parsha.VAYEILECH, Parsha.HAAZINU, Parsha.NONE, Parsha.BERESHIS, Parsha.NOACH, Parsha.LECH_LECHA, Parsha.VAYERA, Parsha.CHAYEI_SARA, Parsha.TOLDOS, Parsha.VAYETZEI, Parsha.VAYISHLACH, Parsha.VAYESHEV, Parsha.MIKETZ, Parsha.VAYIGASH, Parsha.VAYECHI, Parsha.SHEMOS, Parsha.VAERA, Parsha.BO, Parsha.BESHALACH, Parsha.YISRO, Parsha.MISHPATIM, Parsha.TERUMAH, Parsha.TETZAVEH, Parsha.KI_SISA, Parsha.VAYAKHEL, Parsha.PEKUDEI, Parsha.VAYIKRA, Parsha.TZAV, Parsha.SHMINI, Parsha.TAZRIA, Parsha.METZORA, Parsha.NONE, Parsha.ACHREI_MOS, Parsha.KEDOSHIM, Parsha.EMOR, Parsha.BEHAR, Parsha.BECHUKOSAI, Parsha.BAMIDBAR, Parsha.NASSO, Parsha.BEHAALOSCHA, Parsha.SHLACH, Parsha.KORACH, Parsha.CHUKAS, Parsha.BALAK, Parsha.PINCHAS, Parsha.MATOS_MASEI, Parsha.DEVARIM, Parsha.VAESCHANAN, Parsha.EIKEV, Parsha.REEH, Parsha.SHOFTIM, Parsha.KI_SEITZEI, Parsha.KI_SAVO, Parsha.NITZAVIM_VAYEILECH},
		{Parsha.NONE, Parsha.VAYEILECH, Parsha.HAAZINU, Parsha.NONE, Parsha.BERESHIS, Parsha.NOACH, Parsha.LECH_LECHA, Parsha.VAYERA, Parsha.CHAYEI_SARA, Parsha.TOLDOS, Parsha.VAYETZEI, Parsha.VAYISHLACH, Parsha.VAYESHEV, Parsha.MIKETZ, Parsha.VAYIGASH, Parsha.VAYECHI, Parsha.SHEMOS, Parsha.VAERA, Parsha.BO, Parsha.BESHALACH, Parsha.YISRO, Parsha.MISHPATIM, Parsha.TERUMAH, Parsha.TETZAVEH, Parsha.KI_SISA, Parsha.VAYAKHEL, Parsha.PEKUDEI, Parsha.VAYIKRA, Parsha.TZAV, Parsha.SHMINI, Parsha.TAZRIA, Parsha.METZORA, Parsha.NONE, Parsha.ACHREI_MOS, Parsha.KEDOSHIM, Parsha.EMOR, Parsha.BEHAR, Parsha.BECHUKOSAI, Parsha.BAMIDBAR, Parsha.NASSO, Parsha.BEHAALOSCHA, Parsha.SHLACH, Parsha.KORACH, Parsha.CHUKAS, Parsha.BALAK, Parsha.PINCHAS, Parsha.MATOS, Parsha.MASEI, Parsha.DEVARIM, Parsha.VAESCHANAN, Parsha.EIKEV, Parsha.REEH, Parsha.SHOFTIM, Parsha.KI_SEITZEI, Parsha.KI_SAVO, Parsha.NITZAVIM},
		{Parsha.NONE, Parsha.NONE, Parsha.HAAZINU, Parsha.NONE, Parsha.NONE, Parsha.BERESHIS, Parsha.NOACH, Parsha.LECH_LECHA, Parsha.VAYERA, Parsha.CHAYEI_SARA, Parsha.TOLDOS, Parsha.VAYETZEI, Parsha.VAYISHLACH, Parsha.VAYESHEV, Parsha.MIKETZ, Parsha.VAYIGASH, Parsha.VAYECHI, Parsha.SHEMOS, Parsha.VAERA, Parsha.BO, Parsha.BESHALACH, Parsha.YISRO, Parsha.MISHPATIM, Parsha.TERUMAH, Parsha.TETZAVEH, Parsha.KI_SISA, Parsha.VAYAKHEL, Parsha.PEKUDEI, Parsha.VAYIKRA, Parsha.TZAV, Parsha.SHMINI, Parsha.TAZRIA, Parsha.METZORA, Parsha.NONE, Parsha.ACHREI_MOS, Parsha.KEDOSHIM, Parsha.EMOR, Parsha.BEHAR, Parsha.BECHUKOSAI, Parsha.BAMIDBAR, Parsha.NASSO, Parsha.BEHAALOSCHA, Parsha.SHLACH, Parsha.KORACH, Parsha.CHUKAS, Parsha.BALAK, Parsha.PINCHAS, Parsha.MATOS_MASEI, Parsha.DEVARIM, Parsha.VAESCHANAN, Parsha.EIKEV, Parsha.REEH, Parsha.SHOFTIM, Parsha.KI_SEITZEI, Parsha.KI_SAVO, Parsha.NITZAVIM_VAYEILECH}
	};

	/**
	 * Is this calendar set to return modern Israeli national holidays. By default this value is false. The holidays
	 * are: <em>Yom HaShoah</em>, <em>Yom Hazikaron</em>, <em>Yom Ha'atzmaut</em> and <em>Yom Yerushalayim</em>.
	 * 
	 * @return the useModernHolidays true if set to return modern Israeli national holidays
	 * 
	 * @see #setUseModernHolidays(boolean)
	 */
	public boolean isUseModernHolidays() {
		return useModernHolidays;
	}

	/**
	 * Sets the calendar to return modern Israeli national holidays. By default this value is false. The holidays are:
	 * <em>Yom HaShoah</em>, <em>Yom Hazikaron</em>, <em>Yom Ha'atzmaut</em> and <em>Yom Yerushalayim</em>.
	 * 
	 * @param useModernHolidays
	 *            the useModernHolidays to set
	 * 
	 * @see #isUseModernHolidays()
	 */
	public void setUseModernHolidays(boolean useModernHolidays) {
		this.useModernHolidays = useModernHolidays;
	}

	/**
	 * Default constructor will set a default date to the current system date.
	 */
	public JewishCalendar() {
		super();
	}

	/**
	 * A constructor that initializes the date to the {@link java.util.Date Date} parameter.
	 * 
	 * @param date
	 *            the <code>Date</code> to set the calendar to
	 */
	public JewishCalendar(Date date) {
		super(date);
	}

	/**
	 * A constructor that initializes the date to the {@link java.util.Calendar Calendar} parameter.
	 * 
	 * @param calendar
	 *            the <code>Calendar</code> to set the calendar to
	 */
	public JewishCalendar(Calendar calendar) {
		super(calendar);
	}

	/**
	 * A constructor that initializes the date to the {@link java.time.LocalDate LocalDate} parameter.
	 * 
	 * @param localDate
	 *            the <code>LocalDate</code> to set the calendar to
	 */
	public JewishCalendar(LocalDate localDate) {
		super(localDate);
	}

	/**
	 * Creates a Jewish date based on a Jewish year, month and day of month.
	 * 
	 * @param jewishYear
	 *            the Jewish year
	 * @param jewishMonth
	 *            the Jewish month. The method expects a 1 for Nissan ... 12 for Adar and 13 for Adar II. Use the
	 *            constants {@link #NISSAN} ... {@link #ADAR} (or {@link #ADAR_II} for a leap year Adar II) to avoid any
	 *            confusion.
	 * @param jewishDayOfMonth
	 *            the Jewish day of month. If 30 is passed in for a month with only 29 days (for example {@link #IYAR},
	 *            or {@link #KISLEV} in a year that {@link #isKislevShort()}), the 29th (last valid date of the month)
	 *            will be set
	 * @throws IllegalArgumentException
	 *             if the day of month is &lt; 1 or &gt; 30, or a year of &lt; 0 is passed in.
	 */
	public JewishCalendar(int jewishYear, int jewishMonth, int jewishDayOfMonth) {
		super(jewishYear, jewishMonth, jewishDayOfMonth);
	}

	/**
	 * Creates a Jewish date based on a Jewish date and whether in Israel
	 * 
	 * @param jewishYear
	 *            the Jewish year
	 * @param jewishMonth
	 *            the Jewish month. The method expects a 1 for <em>Nissan</em> ... 12 for <em>Adar</em> and 13 for
	 *            <em>Adar II</em>. Use the constants {@link #NISSAN} ... {@link #ADAR} (or {@link #ADAR_II} for a
	 *            leap year Adar II) to avoid any confusion.
	 * @param jewishDayOfMonth
	 *            the Jewish day of month. If 30 is passed in for a month with only 29 days (for example {@link #IYAR},
	 *            or {@link #KISLEV} in a year that {@link #isKislevShort()}), the 29th (last valid date of the month)
	 *            will be set.
	 * @param inIsrael
	 *            whether in Israel. This affects <em>Yom Tov</em> calculations
	 */
	public JewishCalendar(int jewishYear, int jewishMonth, int jewishDayOfMonth, boolean inIsrael) {
		super(jewishYear, jewishMonth, jewishDayOfMonth);
		setInIsrael(inIsrael);
	}

	/**
	 * Sets whether to use Israel holiday scheme or not. Default is false.
	 * 
	 * @param inIsrael
	 *            set to true for calculations for Israel
	 * 
	 * @see #getInIsrael()
	 */
	public void setInIsrael(boolean inIsrael) {
		this.inIsrael = inIsrael;
	}

	/**
	 * Gets whether Israel holiday scheme is used or not. The default (if not set) is false.
	 * 
	 * @return if the if the calendar is set to Israel
	 * 
	 * @see #setInIsrael(boolean)
	 */
	public boolean getInIsrael() {
		return inIsrael;
	}
	
	/**
	 * <a href="https://en.wikipedia.org/wiki/Birkat_Hachama">Birkas Hachamah</a> is recited every 28 years based on
	 * Tekufas Shmulel (Julian years) that a year is 365.25 days. The <a href="https://en.wikipedia.org/wiki/Maimonides">Rambam</a>
	 * in <a href="http://hebrewbooks.org/pdfpager.aspx?req=14278&amp;st=&amp;pgnum=323">Hilchos Kiddush Hachodesh 9:3</a> states that
	 * tekufas Nisan of year 1 was 7 days + 9 hours before molad Nisan. This is calculated as every 10,227 days (28 * 365.25).  
	 * @return true for a day that Birkas Hachamah is recited.
	 */
	public boolean isBirkasHachamah() {
		int elapsedDays = getJewishCalendarElapsedDays(getJewishYear()); //elapsed days since molad ToHu
		elapsedDays = elapsedDays + getDaysSinceStartOfJewishYear(); //elapsed days to the current calendar date
		
		/* Molad Nisan year 1 was 177 days after molad tohu of Tishrei. We multiply 29.5 day months * 6 months from Tishrei
		 * to Nisan = 177. Subtract 7 days since tekufas Nisan was 7 days and 9 hours before the molad as stated in the Rambam
		 * and we are now at 170 days. Because getJewishCalendarElapsedDays and getDaysSinceStartOfJewishYear use the value for
		 * Rosh Hashana as 1, we have to add 1 day days for a total of 171. To this add a day since the tekufah is on a Tuesday
		 * night and we push off the bracha to Wednesday AM resulting in the 172 used in the calculation.
		 */
		if (elapsedDays % (28 * 365.25) == 172) { // 28 years of 365.25 days + the offset from molad tohu mentioned above
			return true;
		}
		return false;
	}

	/**
	 * Return the type of year for parsha calculations. The algorithm follows the
	 * <a href="http://hebrewbooks.org/pdfpager.aspx?req=14268&amp;st=&amp;pgnum=222">Luach Arba'ah Shearim</a> in the Tur Ohr Hachaim.
	 * @return the type of year for parsha calculations.
	 */
	private int getParshaYearType() {
		int roshHashanaDayOfWeek = (getJewishCalendarElapsedDays(getJewishYear()) + 1) % 7; // plus one to the original Rosh Hashana of year 1 to get a week starting on Sunday
		if(roshHashanaDayOfWeek == 0) {
			roshHashanaDayOfWeek = 7; // convert 0 to 7 for Shabbos for readability
		}
		if (isJewishLeapYear()) {
			switch (roshHashanaDayOfWeek) {
			case Calendar.MONDAY:
				if (isKislevShort()) { //BaCh
					if (getInIsrael()) {
						return 14;
					}
					return 6;
				}
				if (isCheshvanLong()) { //BaSh
					if (getInIsrael()) {
						return 15;
					}
					return 7;
				}
				break;
			case Calendar.TUESDAY: //Gak
				if (getInIsrael()) {
					return 15;
				}
				return 7;
			case Calendar.THURSDAY:
				if (isKislevShort()) { //HaCh
					return 8;
				}
				if (isCheshvanLong()) { //HaSh
					return 9;
				}
				break;
			case Calendar.SATURDAY:
				if (isKislevShort()) { //ZaCh
					return 10;
				}
				if (isCheshvanLong()) { //ZaSh
					if (getInIsrael()) {
						return 16;
					}
					return 11;
				}
				break;
			}
		} else { //not a leap year
			switch (roshHashanaDayOfWeek) {
			case Calendar.MONDAY:
				if (isKislevShort()) { //BaCh
					return 0;
				}
				if (isCheshvanLong()) { //BaSh
					if (getInIsrael()) {
						return 12;
					}
					return 1;
				}
				break;
			case Calendar.TUESDAY: //GaK
				if (getInIsrael()) {
					return 12;
				}
				return 1;
			case Calendar.THURSDAY:
				if (isCheshvanLong()) { //HaSh
					return 3;
				}
				if (!isKislevShort()) { //Hak
					if (getInIsrael()) {
						return 13;
					}
					return 2;
				}
				break;
			case Calendar.SATURDAY:
				if (isKislevShort()) { //ZaCh
					return 4;
				}
				if (isCheshvanLong()) { //ZaSh
					return 5;
				}
				break;
			}
		}
		return -1; //keep the compiler happy
	}

	/**
	 * Returns this week's {@link Parsha} if it is <em>Shabbos</em>.
	 * returns Parsha.NONE if a weekday or if there is no <em>parsha</em> that week (for example <em>Yomtov</em> is on <em>Shabbos</em>).
	 * 
	 * @return the current <em>parsha</em>.
	 */
	public Parsha getParshah() {
		if (getDayOfWeek() != Calendar.SATURDAY) {
			return Parsha.NONE;
		}
		
		int yearType = getParshaYearType();
		int roshHashanaDayOfWeek = getJewishCalendarElapsedDays(getJewishYear()) % 7;
		int day = roshHashanaDayOfWeek + getDaysSinceStartOfJewishYear();
		
		if (yearType >= 0) { // negative year should be impossible, but lets cover all bases
			return parshalist[yearType][day/7];
		}
		return Parsha.NONE; //keep the compiler happy
	}

	/**
	 * Returns a <em>parsha</em> enum if the <em>Shabbos</em> is one of the four <em>parshiyos</em> of Parsha.SHKALIM, Parsha.ZACHOR,
	 * Parsha.PARA, Parsha.HACHODESH or Parsha.NONE for a regular <em>Shabbos</em> (or any weekday).
	 * 
	 * @return one of the four <em>parshiyos</em> of Parsha.SHKALIM, Parsha.ZACHOR, Parsha.PARA, Parsha.HACHODESH or Parsha.NONE.
	 */
	public Parsha getSpecialShabbos() {
		if(getDayOfWeek() == Calendar.SATURDAY)	{
			if((getJewishMonth() == SHEVAT && !isJewishLeapYear()) || (getJewishMonth() == ADAR && isJewishLeapYear())) {
				if(getJewishDayOfMonth() == 25 || getJewishDayOfMonth() == 27 || getJewishDayOfMonth() == 29) {
					return Parsha.SHKALIM;
				}
			}
			if((getJewishMonth() == ADAR && !isJewishLeapYear()) || getJewishMonth() == ADAR_II) {
				if(getJewishDayOfMonth() == 1) {
					return Parsha.SHKALIM;
				}
				if(getJewishDayOfMonth() == 8 || getJewishDayOfMonth() == 9 || getJewishDayOfMonth() == 11 || getJewishDayOfMonth() == 13) {
					return Parsha.ZACHOR;
				}
				if(getJewishDayOfMonth() == 18 || getJewishDayOfMonth() == 20 || getJewishDayOfMonth() == 22 || getJewishDayOfMonth() == 23) {
					return Parsha.PARA;
				}
				if(getJewishDayOfMonth() == 25 || getJewishDayOfMonth() == 27 || getJewishDayOfMonth() == 29) {
					return Parsha.HACHODESH;
				}
			}
			if(getJewishMonth() == NISSAN && getJewishDayOfMonth() == 1) {
				return Parsha.HACHODESH;
			}
		}
		return Parsha.NONE;
	}

	/**
	 * Returns an index of the Jewish holiday or fast day for the current day, or a -1 if there is no holiday for this day.
	 * There are constants in this class representing each <em>Yom Tov</em>. Formatting of the <em>Yomim tovim</em> is done
	 * in the {@link HebrewDateFormatter#formatYomTov(JewishCalendar)}.
	 * 
	 * @todo Consider using enums instead of the constant ints.
	 * 
	 * @return the index of the holiday such as the constant {@link #LAG_BAOMER} or {@link #YOM_KIPPUR} or a -1 if it is not a holiday.
	 * 
	 * @see HebrewDateFormatter#formatYomTov(JewishCalendar)
	 */
	public int getYomTovIndex() {
		final int day = getJewishDayOfMonth();
		final int dayOfWeek = getDayOfWeek();

		// check by month (starting from Nissan)
		switch (getJewishMonth()) {
		case NISSAN:
			if (day == 14) {
				return EREV_PESACH;
			}
			if (day == 15 || day == 21
					|| (!inIsrael && (day == 16 || day == 22))) {
				return PESACH;
			}
			if (day >= 17 && day <= 20
					|| (day == 16 && inIsrael)) {
				return CHOL_HAMOED_PESACH;
			}
			if (isUseModernHolidays()
					&& ((day == 26 && dayOfWeek == Calendar.THURSDAY)
							|| (day == 28 && dayOfWeek == Calendar.MONDAY)
							|| (day == 27 && dayOfWeek != Calendar.SUNDAY && dayOfWeek != Calendar.FRIDAY))) {
				return YOM_HASHOAH;
			}
			break;
		case IYAR:
			if (isUseModernHolidays()
					&& ((day == 4 && dayOfWeek == Calendar.TUESDAY)
							|| ((day == 3 || day == 2) && dayOfWeek == Calendar.WEDNESDAY) || (day == 5 && dayOfWeek == Calendar.MONDAY))) {
				return YOM_HAZIKARON;
			}
			// if 5 Iyar falls on Wed Yom Haatzmaut is that day. If it fal1s on Friday or Shabbos it is moved back to
			// Thursday. If it falls on Monday it is moved to Tuesday
			if (isUseModernHolidays()
					&& ((day == 5 && dayOfWeek == Calendar.WEDNESDAY)
							|| ((day == 4 || day == 3) && dayOfWeek == Calendar.THURSDAY) || (day == 6 && dayOfWeek == Calendar.TUESDAY))) {
				return YOM_HAATZMAUT;
			}
			if (day == 14) {
				return PESACH_SHENI;
			}
			if (day == 18) {
				return LAG_BAOMER;
			}
			if (isUseModernHolidays() && day == 28) {
				return YOM_YERUSHALAYIM;
			}
			break;
		case SIVAN:
			if (day == 5) {
				return EREV_SHAVUOS;
			}
			if (day == 6 || (day == 7 && !inIsrael)) {
				return SHAVUOS;
			}
			break;
		case TAMMUZ:
			// push off the fast day if it falls on Shabbos
			if ((day == 17 && dayOfWeek != Calendar.SATURDAY)
					|| (day == 18 && dayOfWeek == Calendar.SUNDAY)) {
				return SEVENTEEN_OF_TAMMUZ;
			}
			break;
		case AV:
			// if Tisha B'av falls on Shabbos, push off until Sunday
			if ((dayOfWeek == Calendar.SUNDAY && day == 10)
					|| (dayOfWeek != Calendar.SATURDAY && day == 9)) {
				return TISHA_BEAV;
			}
			if (day == 15) {
				return TU_BEAV;
			}
			break;
		case ELUL:
			if (day == 29) {
				return EREV_ROSH_HASHANA;
			}
			break;
		case TISHREI:
			if (day == 1 || day == 2) {
				return ROSH_HASHANA;
			}
			if ((day == 3 && dayOfWeek != Calendar.SATURDAY) || (day == 4 && dayOfWeek == Calendar.SUNDAY)) {
				// push off Tzom Gedalia if it falls on Shabbos
				return FAST_OF_GEDALYAH;
			}
			if (day == 9) {
				return EREV_YOM_KIPPUR;
			}
			if (day == 10) {
				return YOM_KIPPUR;
			}
			if (day == 14) {
				return EREV_SUCCOS;
			}
			if (day == 15 || (day == 16 && !inIsrael)) {
				return SUCCOS;
			}
			if (day >= 17 && day <= 20 || (day == 16 && inIsrael)) {
				return CHOL_HAMOED_SUCCOS;
			}
			if (day == 21) {
				return HOSHANA_RABBA;
			}
			if (day == 22) {
				return SHEMINI_ATZERES;
			}
			if (day == 23 && !inIsrael) {
				return SIMCHAS_TORAH;
			}
			break;
		case KISLEV: // no yomtov in CHESHVAN
			// if (day == 24) {
			// return EREV_CHANUKAH;
			// } else
			if (day >= 25) {
				return CHANUKAH;
			}
			break;
		case TEVES:
			if (day == 1 || day == 2
					|| (day == 3 && isKislevShort())) {
				return CHANUKAH;
			}
			if (day == 10) {
				return TENTH_OF_TEVES;
			}
			break;
		case SHEVAT:
			if (day == 15) {
				return TU_BESHVAT;
			}
			break;
		case ADAR:
			if (!isJewishLeapYear()) {
				// if 13th Adar falls on Friday or Shabbos, push back to Thursday
				if (((day == 11 || day == 12) && dayOfWeek == Calendar.THURSDAY)
						|| (day == 13 && !(dayOfWeek == Calendar.FRIDAY || dayOfWeek == Calendar.SATURDAY))) {
					return FAST_OF_ESTHER;
				}
				if (day == 14) {
					return PURIM;
				}
				if (day == 15) {
					return SHUSHAN_PURIM;
				}
			} else { // else if a leap year
				if (day == 14) {
					return PURIM_KATAN;
				}
				if (day == 15) {
					return SHUSHAN_PURIM_KATAN;
				}
			}
			break;
		case ADAR_II:
			// if 13th Adar falls on Friday or Shabbos, push back to Thursday
			if (((day == 11 || day == 12) && dayOfWeek == Calendar.THURSDAY)
					|| (day == 13 && !(dayOfWeek == Calendar.FRIDAY || dayOfWeek == Calendar.SATURDAY))) {
				return FAST_OF_ESTHER;
			}
			if (day == 14) {
				return PURIM;
			}
			if (day == 15) {
				return SHUSHAN_PURIM;
			}
			break;
		}
		// if we get to this stage, then there are no holidays for the given date return -1
		return -1;
	}

	/**
	 * Returns true if the current day is <em>Yom Tov</em>. The method returns true even for holidays such as {@link #CHANUKAH}
	 * and minor ones such as {@link #TU_BEAV} and {@link #PESACH_SHENI}. <em>Erev Yom Tov</em> (with the exception of
	 * {@link #HOSHANA_RABBA}, <em>erev</em> the second days of <em>Pesach</em>) returns false, as do {@link #isTaanis() fast
	 * days} besides {@link #YOM_KIPPUR}. Use {@link #isAssurBemelacha()} to find the days that have a prohibition of work. 
	 * 
	 * @return true if the current day is a Yom Tov
	 * 
	 * @see #getYomTovIndex()
	 * @see #isErevYomTov()
	 * @see #isErevYomTovSheni()
	 * @see #isTaanis()
	 * @see #isAssurBemelacha()
	 * @see #isCholHamoed()
	 */
	public boolean isYomTov() {
		int holidayIndex = getYomTovIndex();
		if ((isErevYomTov() && (holidayIndex != HOSHANA_RABBA && (holidayIndex == CHOL_HAMOED_PESACH && getJewishDayOfMonth() != 20)))
				|| (isTaanis() && holidayIndex != YOM_KIPPUR)) {
			return false;
		}
		return getYomTovIndex() != -1;
	}

	/**
	 * Returns true if the <em>Yom Tov</em> day has a <em>melacha</em> (work)  prohibition. This method will return false for a
	 * non-<em>Yom Tov</em> day, even if it is <em>Shabbos</em>.
	 *
	 * @return if the <em>Yom Tov</em> day has a <em>melacha</em> (work)  prohibition.
	 */
	public boolean isYomTovAssurBemelacha() {
		int holidayIndex = getYomTovIndex();
		return holidayIndex == PESACH || holidayIndex == SHAVUOS || holidayIndex == SUCCOS || holidayIndex == SHEMINI_ATZERES ||
				holidayIndex == SIMCHAS_TORAH || holidayIndex == ROSH_HASHANA  || holidayIndex == YOM_KIPPUR;
	}
	
	/**
	 * Returns true if it is <em>Shabbos</em> or if it is a <em>Yom Tov</em> day that has a <em>melacha</em> (work)  prohibition.
	 * 
	 * @return if the day is a <em>Yom Tov</em> that is <em>assur bemlacha</em> or <em>Shabbos</em>
	 */
	public boolean isAssurBemelacha() {
		return getDayOfWeek() == Calendar.SATURDAY || isYomTovAssurBemelacha();
	}
	
	/**
	 * Returns true if the day has candle lighting. This will return true on erev <em>Shabbos</em>, erev <em>Yom Tov</em>, the
	 * first day of <em>Rosh Hashana</em> and the first days of <em>Yom Tov</em> out of Israel. It is identical
	 * to calling {@link #isTomorrowShabbosOrYomTov()}.
	 * 
	 * @return if the day has candle lighting.
	 * 
	 * @see #isTomorrowShabbosOrYomTov()
	 */
	public boolean hasCandleLighting() {
		return isTomorrowShabbosOrYomTov();
	}
	
	/**
	 * Returns true if tomorrow is <em>Shabbos</em> or <em>Yom Tov</em>. This will return true on erev <em>Shabbos</em>,
	 * <em>erev Yom Tov</em>, the first day of <em>Rosh Hashana</em> and <em>erev</em> the first days of <em>Yom Tov</em>
	 * out of Israel. It is identical to calling {@link #hasCandleLighting()}.
	 * 
	 * @return will return if the next day is <em>Shabbos</em> or <em>Yom Tov</em>.
	 * 
	 * @see #hasCandleLighting()
	 */
	public boolean isTomorrowShabbosOrYomTov() {
		return getDayOfWeek() == Calendar.FRIDAY || isErevYomTov() || isErevYomTovSheni();
	}
	
	/**
	 * Returns true if the day is the second day of <em>Yom Tov</em>. This impacts the second day of <em>Rosh Hashana</em> everywhere and
	 * the second days of Yom Tov in <em>chutz laaretz</em> (out of Israel).
	 * 
	 * @return  if the day is the second day of <em>Yom Tov</em>.
	 */
	public boolean isErevYomTovSheni() {
		return (getJewishMonth() == TISHREI && (getJewishDayOfMonth() == 1))
		|| (! getInIsrael()
				&& ((getJewishMonth() == NISSAN && (getJewishDayOfMonth() == 15 || getJewishDayOfMonth() == 21))
				|| (getJewishMonth() == TISHREI && (getJewishDayOfMonth() == 15 || getJewishDayOfMonth() == 22))
				|| (getJewishMonth() == SIVAN && getJewishDayOfMonth() == 6 )));
	}

	/**
	 * Returns true if the current day is <em>Aseret Yemei Teshuva</em>.
	 * 
	 * @return if the current day is <em>Aseret Yemei Teshuvah</em>
	 */
	public boolean isAseresYemeiTeshuva(){
		return getJewishMonth() == TISHREI && getJewishDayOfMonth() <= 10;
	}

	/**
	 * Returns true if the current day is <em>Chol Hamoed</em> of <em>Pesach</em> or <em>Succos</em>.
	 * 
	 * @return true if the current day is <em>Chol Hamoed</em> of <em>Pesach</em> or <em>Succos</em>
	 * @see #isYomTov()
	 * @see #CHOL_HAMOED_PESACH
	 * @see #CHOL_HAMOED_SUCCOS
	 */
	public boolean isCholHamoed() {
		return isCholHamoedPesach() || isCholHamoedSuccos();
	}

	/**
	 * Returns true if the current day is <em>Chol Hamoed</em> of <em>Pesach</em>.
	 *
	 * @return true if the current day is <em>Chol Hamoed</em> of <em>Pesach</em>
	 * @see #isYomTov()
	 * @see #CHOL_HAMOED_PESACH
	 */
	public boolean isCholHamoedPesach() {
		int holidayIndex = getYomTovIndex();
		return holidayIndex == CHOL_HAMOED_PESACH;
	}

	/**
	 * Returns true if the current day is <em>Chol Hamoed</em> of <em>Succos</em>.
	 *
	 * @return true if the current day is <em>Chol Hamoed</em> of <em>Succos</em>
	 * @see #isYomTov()
	 * @see #CHOL_HAMOED_SUCCOS
	 */
	public boolean isCholHamoedSuccos() {
		int holidayIndex = getYomTovIndex();
		return holidayIndex == CHOL_HAMOED_SUCCOS;
	}

	/**
	 * Returns true if the current day is erev Yom Tov. The method returns true for <em>Erev</em> - <em>Pesach</em> (first and
	 * last days), <em>Shavuos</em>, <em>Rosh Hashana</em>, <em>Yom Kippur</em>, <em>Succos</em> and <em>Hoshana Rabba</em>.
	 * 
	 * @return true if the current day is <em>Erev</em> - <em>Pesach</em>, <em>Shavuos</em>, <em>Rosh Hashana</em>, <em>Yom
	 * Kippur</em>, <em>Succos</em> and <em>Hoshana Rabba</em>.
	 * @see #isYomTov()
	 * @see #isErevYomTovSheni()
	 */
	public boolean isErevYomTov() {
		int holidayIndex = getYomTovIndex();
		return holidayIndex == EREV_PESACH || holidayIndex == EREV_SHAVUOS || holidayIndex == EREV_ROSH_HASHANA
				|| holidayIndex == EREV_YOM_KIPPUR || holidayIndex == EREV_SUCCOS || holidayIndex == HOSHANA_RABBA
				|| (holidayIndex == CHOL_HAMOED_PESACH && getJewishDayOfMonth() == 20);
	}

	/**
	 * Returns true if the current day is Erev Rosh Chodesh. Returns false for Erev Rosh Hashana
	 * 
	 * @return true if the current day is Erev Rosh Chodesh. Returns false for Erev Rosh Hashana
	 * @see #isRoshChodesh()
	 */
	public boolean isErevRoshChodesh() {
		// Erev Rosh Hashana is not Erev Rosh Chodesh.
		return (getJewishDayOfMonth() == 29 && getJewishMonth() != ELUL);
	}

	/**
	 * Return true if the day is a Taanis (fast day). Return true for <em>17 of Tammuz</em>, <em>Tisha B'Av</em>,
	 * <em>Yom Kippur</em>, <em>Fast of Gedalyah</em>, <em>10 of Teves</em> and the <em>Fast of Esther</em>.
	 * 
	 * @return true if today is a fast day
	 */
	public boolean isTaanis() {
		int holidayIndex = getYomTovIndex();
		return holidayIndex == SEVENTEEN_OF_TAMMUZ || holidayIndex == TISHA_BEAV || holidayIndex == YOM_KIPPUR
				|| holidayIndex == FAST_OF_GEDALYAH || holidayIndex == TENTH_OF_TEVES || holidayIndex == FAST_OF_ESTHER;
	}
	
	/**
	 * Return true if the day is <em>Taanis Bechoros</em> (on <em>erev Pesach</em>). It will return true for the 14th
	 * of <em>Nissan</em> if it is not on <em>Shabbos</em>, or if the 12th of <em>Nissan</em> occurs on a Thursday.
	 * 
	 * @return true if today is <em>Taanis Bechoros</em>.
	 */
	public boolean isTaanisBechoros() {
	    final int day = getJewishDayOfMonth();
	    final int dayOfWeek = getDayOfWeek();
	    // on 14 Nisan unless that is Shabbos where the fast is moved back to Thursday
	    return getJewishMonth() == NISSAN && ((day == 14 && dayOfWeek != Calendar.SATURDAY) ||
	    		(day == 12 && dayOfWeek == Calendar.THURSDAY ));
	}

	/**
	 * Returns the day of <em>Chanukah</em> or -1 if it is not <em>Chanukah</em>.
	 * 
	 * @return the day of <em>Chanukah</em> or -1 if it is not <em>Chanukah</em>.
	 * @see #isChanukah()
	 */
	public int getDayOfChanukah() {
		final int day = getJewishDayOfMonth();
		if (isChanukah()) {
			if (getJewishMonth() == KISLEV) {
				return day - 24;
			} else { // teves
				return isKislevShort() ? day + 5 : day + 6;
			}
		} else {
			return -1;
		}
	}

	/**
	 * Returns true if the current day is one of the 8 days of <em>Chanukah</em>.
	 * 
	 * @return if the current day is one of the 8 days of <em>Chanukah</em>.
	 * 
	 * @see #getDayOfChanukah()
	 */
	public boolean isChanukah() {
		return getYomTovIndex() == CHANUKAH;
	}

	/**
	 * Returns if the day is Rosh Chodesh. Rosh Hashana will return false
	 * 
	 * @return true if it is Rosh Chodesh. Rosh Hashana will return false
	 */
	public boolean isRoshChodesh() {
		// Rosh Hashana is not rosh chodesh. Elul never has 30 days
		return (getJewishDayOfMonth() == 1 && getJewishMonth() != TISHREI) || getJewishDayOfMonth() == 30;
	}

	/**
	 * Returns if the day is <em>Shabbos</em> and Sunday is <em>Rosh Chodesh</em>.
	 *
	 * @return true if it is <em>Shabbos</em> and Sunday is <em>Rosh Chodesh</em>.
	 * @todo There is more to tweak in this method (it does not cover all cases and opinions), and it may be removed.
	 */
	public boolean isMacharChodesh() {
		return (getDayOfWeek() == Calendar.SATURDAY && (getJewishDayOfMonth() == 30 || getJewishDayOfMonth() == 29));
	}

	/**
	 * Returns if the day is <em>Shabbos Mevorchim</em>.
	 *
	 * @return true if it is <em>Shabbos Mevorchim</em>.
	 */
	public boolean isShabbosMevorchim() {
		return (getDayOfWeek() == Calendar.SATURDAY && getJewishDayOfMonth() >= 23 && getJewishDayOfMonth() <= 29 && getJewishMonth() != ELUL);
	}

	/**
	 * Returns the int value of the <em>Omer</em> day or -1 if the day is not in the <em>Omer</em>.
	 * 
	 * @return The <em>Omer</em> count as an int or -1 if it is not a day of the <em>Omer</em>.
	 */
	public int getDayOfOmer() {
		int omer = -1; // not a day of the Omer
		int month = getJewishMonth();
		int day = getJewishDayOfMonth();

		// if Nissan and second day of Pesach and on
		if (month == NISSAN && day >= 16) {
			omer = day - 15;
			// if Iyar
		} else if (month == IYAR) {
			omer = day + 15;
			// if Sivan and before Shavuos
		} else if (month == SIVAN && day < 6) {
			omer = day + 44;
		}
		return omer;
	}

	/**
	 * Returns the molad in Standard Time in Yerushalayim as a {@link Date}. The traditional calculation uses local time. This
	 * method subtracts 20.94 minutes (20 minutes and 56.496 seconds) from the local time (Har Habayis with a longitude
	 * of 35.2354&deg; is 5.2354&deg; away from the %15 timezone longitude) to get to standard time. This method
	 * intentionally uses standard time and not dailight savings time. Java will implicitly format the time to the
	 * default (or set) Timezone.
	 * 
	 * @return the Date representing the moment of the molad in Yerushalayim standard time (GMT + 2)
	 */
	public Date getMoladAsDate() {
		JewishDate molad = getMolad();
		String locationName = "Jerusalem, Israel";

		double latitude = 31.778; // Har Habayis latitude
		double longitude = 35.2354; // Har Habayis longitude

		// The raw molad Date (point in time) must be generated using standard time. Using "Asia/Jerusalem" timezone will result in the time
		// being incorrectly off by an hour in the summer due to DST. Proper adjustment for the actual time in DST will be done by the date
		// formatter class used to display the Date.
		TimeZone yerushalayimStandardTZ = TimeZone.getTimeZone("GMT+2");
		GeoLocation geo = new GeoLocation(locationName, latitude, longitude, yerushalayimStandardTZ);
		Calendar cal = Calendar.getInstance(geo.getTimeZone());
		cal.clear();
		double moladSeconds = molad.getMoladChalakim() * 10 / (double) 3;
		cal.set(molad.getGregorianYear(), molad.getGregorianMonth(), molad.getGregorianDayOfMonth(),
				molad.getMoladHours(), molad.getMoladMinutes(), (int) moladSeconds);
		cal.set(Calendar.MILLISECOND, (int) (1000 * (moladSeconds - (int) moladSeconds)));
		// subtract local time difference of 20.94 minutes (20 minutes and 56.496 seconds) to get to Standard time
		cal.add(Calendar.MILLISECOND, -1 * (int) geo.getLocalMeanTimeOffset());
		return cal.getTime();
	}

	/**
	 * Returns the tekufa in Standard Time in Yerushalayim as a {@link Date}. There are 4 tekufas every solar year
	 * (Tishrei, Tevet, Nissan, and Tammuz). This method will give you a {@link Date} object if one of these tekufas
	 * falls on the current day, otherwise, it will return null. Note that the tekufa is based on the hebrew date,
	 * not the gregorian date. This method intentionally uses standard time and not daylight savings time. Java will
	 * implicitly format the time to the default (or set) Timezone.
	 *
	 * @return the Date representing the moment of the tekufa in Yerushalayim standard time (GMT +2) or null if the tekufa is not today.
	 * @see #getTekufaHours()
	 * @see #getTekufaType()
	 */
	public Date getTekufaAsDate() {
		if (getTekufaHours() == null) {
			return null;
		}
		double hours = getTekufaHours() - 6;// take away 6 hours since the getTekufa method gives the time based on the start of the jewish day (6:00pm)
		int minutes = (int) ((hours - (int) hours) * 60);// take away the hours and get the minutes by multiplying the decimal by 60

		// The tekufa Date (point in time) must be generated using standard time. Using "Asia/Jerusalem" timezone will result in the time
		// being incorrectly off by an hour in the summer due to DST. Proper adjustment for the actual time in DST will be done by the date
		// formatter class used to display the Date.
		TimeZone yerushalayimStandardTZ = TimeZone.getTimeZone("GMT+2");
		Calendar cal = Calendar.getInstance(yerushalayimStandardTZ);
		cal.clear();
		cal.set(getGregorianYear(), getGregorianMonth(), getGregorianDayOfMonth(), 0, 0, 0);
		cal.add(Calendar.HOUR_OF_DAY, (int) hours);
		cal.add(Calendar.MINUTE, minutes);

		return cal.getTime();
	}

	/**
	 * Returns the hours of the tekufa if it falls on the current day, otherwise returns null. The tekufa is based on the
	 * hebrew date, not the gregorian date. Therefore, these hours are based on the start of the jewish day (6:00pm),
	 * which could be a day before the set gregorian date.
	 * @return the hours of the tekufa if it falls on the current day, otherwise returns null.
	 * @see #getTekufaAsDate()
	 * @see #getTekufaType()
	 */
	public Double getTekufaHours() {
		double INITIAL_TEKUFA_OFFSET = 12.625;// the number of days Tekufas Tishrei occurs before JEWISH_EPOCH
		double days = getJewishCalendarElapsedDays(getJewishYear()) + getDaysSinceStartOfJewishYear() + INITIAL_TEKUFA_OFFSET - 1;// total days since first Tekufas Tishrei event

		double solarDaysElapsed = days % 365.25;// total days elapsed since start of solar year
		double tekufaDaysElapsed = solarDaysElapsed % 91.3125;// the number of days that have passed since a tekufa event
		if (tekufaDaysElapsed > 0 && tekufaDaysElapsed <= 1){// if the tekufa happens in the upcoming 24 hours
			return ((1.0 - tekufaDaysElapsed) * 24.0) % 24;// rationalize the tekufa event to number of hours since start of jewish day
		} else {
			return null;
		}
	}

	/**
	 * Returns the type of the tekufa if it falls on the current day, otherwise returns -1. The tekufa is based on the
	 * hebrew date, not the gregorian date. There are 4 tekufas every solar year (Tishrei, Tevet, Nissan, and Tammuz).
	 * This method will return 0 for Tishrei, 1 for Tevet, 2 for Nissan, and 3 for Tammuz.
	 * @return the type of the tekufa if it falls on the current day, otherwise returns -1.
	 * @see #getTekufaAsDate()
	 * @see #getTekufaHours()
	 */
	public int getTekufaType() {
		double INITIAL_TEKUFA_OFFSET = 12.625;// the number of days Tekufas Tishrei occurs before JEWISH_EPOCH
		double days = getJewishCalendarElapsedDays(getJewishYear()) + getDaysSinceStartOfJewishYear() + INITIAL_TEKUFA_OFFSET - 1;// total days since first Tekufas Tishrei event

		double solarDaysElapsed = days % 365.25;// total days elapsed since start of solar year
		int currentTekufaNumber = (int) (solarDaysElapsed / 91.3125);// the current quarter of the solar year
		double tekufaDaysElapsed = solarDaysElapsed % 91.3125;// the number of days that have passed since a tekufa event
		if (tekufaDaysElapsed > 0 && tekufaDaysElapsed <= 1){// if the tekufa happens in the upcoming 24 hours
			return currentTekufaNumber;//0 for Tishrei, 1 for Tevet, 2, for Nissan, 3 for Tammuz
		} else {
			return -1;
		}
	}

	/**
	 * Returns the earliest time of <em>Kiddush Levana</em> calculated as 3 days after the molad. This method returns the time
	 * even if it is during the day when <em>Kiddush Levana</em> can't be said. Callers of this method should consider
	 * displaying the next <em>tzais</em> if the zman is between <em>alos</em> and <em>tzais</em>.
	 * 
	 * @return the Date representing the moment 3 days after the molad.
	 * 
	 * @see com.kosherjava.zmanim.ComplexZmanimCalendar#getTchilasZmanKidushLevana3Days()
	 * @see com.kosherjava.zmanim.ComplexZmanimCalendar#getTchilasZmanKidushLevana3Days(Date, Date)
	 */
	public Date getTchilasZmanKidushLevana3Days() {
		Date molad = getMoladAsDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(molad);
		cal.add(Calendar.HOUR, 72); // 3 days after the molad
		return cal.getTime();
	}

	/**
	 * Returns the earliest time of Kiddush Levana calculated as 7 days after the molad as mentioned by the <a
	 * href="http://en.wikipedia.org/wiki/Yosef_Karo">Mechaber</a>. See the <a
	 * href="http://en.wikipedia.org/wiki/Yoel_Sirkis">Bach's</a> opinion on this time. This method returns the time
	 * even if it is during the day when <em>Kiddush Levana</em> can't be said. Callers of this method should consider
	 * displaying the next <em>tzais</em> if the zman is between <em>alos</em> and <em>tzais</em>.
	 * 
	 * @return the Date representing the moment 7 days after the molad.
	 * 
	 * @see com.kosherjava.zmanim.ComplexZmanimCalendar#getTchilasZmanKidushLevana7Days()
	 * @see com.kosherjava.zmanim.ComplexZmanimCalendar#getTchilasZmanKidushLevana7Days(Date, Date)
	 */
	public Date getTchilasZmanKidushLevana7Days() {
		Date molad = getMoladAsDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(molad);
		cal.add(Calendar.HOUR, 168); // 7 days after the molad
		return cal.getTime();
	}

	/**
	 * Returns the latest time of Kiddush Levana according to the <a
	 * href="http://en.wikipedia.org/wiki/Yaakov_ben_Moshe_Levi_Moelin">Maharil's</a> opinion that it is calculated as
	 * halfway between molad and molad. This adds half the 29 days, 12 hours and 793 chalakim time between molad and
	 * molad (14 days, 18 hours, 22 minutes and 666 milliseconds) to the month's molad. This method returns the time
	 * even if it is during the day when <em>Kiddush Levana</em> can't be said. Callers of this method should consider
	 * displaying <em>alos</em> before this time if the zman is between <em>alos</em> and <em>tzais</em>.
	 * 
	 * @return the Date representing the moment halfway between molad and molad.
	 * 
	 * @see #getSofZmanKidushLevana15Days()
	 * @see com.kosherjava.zmanim.ComplexZmanimCalendar#getSofZmanKidushLevanaBetweenMoldos()
	 * @see com.kosherjava.zmanim.ComplexZmanimCalendar#getSofZmanKidushLevanaBetweenMoldos(Date, Date)
	 */
	public Date getSofZmanKidushLevanaBetweenMoldos() {
		Date molad = getMoladAsDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(molad);
		// add half the time between molad and molad (half of 29 days, 12 hours and 793 chalakim (44 minutes, 3.3
		// seconds), or 14 days, 18 hours, 22 minutes and 666 milliseconds). Add it as hours, not days, to avoid
		// DST/ST crossover issues.
		cal.add(Calendar.HOUR, (24 * 14) + 18);
		cal.add(Calendar.MINUTE, 22);
		cal.add(Calendar.SECOND, 1);
		cal.add(Calendar.MILLISECOND, 666);
		return cal.getTime();
	}

	/**
	 * Returns the latest time of Kiddush Levana calculated as 15 days after the molad. This is the opinion brought down
	 * in the Shulchan Aruch (Orach Chaim 426). It should be noted that some opinions hold that the
	 * <a href="http://en.wikipedia.org/wiki/Moses_Isserles">Rema</a> who brings down the opinion of the <a
	 * href="http://en.wikipedia.org/wiki/Yaakov_ben_Moshe_Levi_Moelin">Maharil's</a> of calculating
	 * {@link #getSofZmanKidushLevanaBetweenMoldos() half way between molad and mold} is of the opinion that Mechaber
	 * agrees to his opinion. Also see the Aruch Hashulchan. For additional details on the subject, See Rabbi Dovid
	 * Heber's very detailed writeup in Siman Daled (chapter 4) of <a
	 * href="http://www.worldcat.org/oclc/461326125">Shaarei Zmanim</a>. This method returns the time even if it is during
	 * the day when <em>Kiddush Levana</em> can't be said. Callers of this method should consider displaying <em>alos</em>
	 * before this time if the zman is between <em>alos</em> and <em>tzais</em>.
	 * 
	 * @return the Date representing the moment 15 days after the molad.
	 * @see #getSofZmanKidushLevanaBetweenMoldos()
	 * @see com.kosherjava.zmanim.ComplexZmanimCalendar#getSofZmanKidushLevana15Days()
	 * @see com.kosherjava.zmanim.ComplexZmanimCalendar#getSofZmanKidushLevana15Days(Date, Date)
	 */
	public Date getSofZmanKidushLevana15Days() {
		Date molad = getMoladAsDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(molad);
		cal.add(Calendar.HOUR, 24 * 15); //15 days after the molad. Add it as hours, not days, to avoid DST/ST crossover issues.
		return cal.getTime();
	}

	/**
	 * Returns the <em>Daf Yomi (Bavli)</em> for the date that the calendar is set to. See the
	 * {@link HebrewDateFormatter#formatDafYomiBavli(Daf)} for the ability to format the <em>daf</em> in
	 * Hebrew or transliterated <em>masechta</em> names.
	 * 
	 * @return the daf as a {@link Daf}
	 */
	public Daf getDafYomiBavli() {
		return YomiCalculator.getDafYomiBavli(this);
	}
	/**
	 * Returns the <em>Daf Yomi (Yerushalmi)</em> for the date that the calendar is set to. See the
	 * {@link HebrewDateFormatter#formatDafYomiYerushalmi(Daf)} for the ability to format the <em>daf</em>
	 * in Hebrew or transliterated <em>masechta</em> names.
	 *
	 * @return the daf as a {@link Daf}
	 */
	public Daf getDafYomiYerushalmi() {
		return YerushalmiYomiCalculator.getDafYomiYerushalmi(this);
	}
	
	/**
	 * Returns the elapsed days since <em>Tekufas Tishrei</em>. This uses <em>Tekufas Shmuel</em> (identical to the <a href=
	 * "https://en.wikipedia.org/wiki/Julian_year_(astronomy)">Julian Year</a> with a solar year length of 365.25 days.
	 * The notation used below is D = days, H = hours and C = chalakim. <em><a href="https://en.wikipedia.org/wiki/Molad"
	 * >Molad</a> BaHaRad</em> was 2D,5H,204C or 5H,204C from the start of <em>Rosh Hashana</em> year 1. For <em>molad
	 * Nissan</em> add 177D, 4H and 438C (6 * 29D, 12H and 793C), or 177D,9H,642C after <em>Rosh Hashana</em> year 1.
	 * <em>Tekufas Nissan</em> was 7D, 9H and 642C before <em>molad Nissan</em> according to the Rambam, or 170D, 0H and
	 * 0C after <em>Rosh Hashana</em> year 1. <em>Tekufas Tishrei</em> was 182D and 3H (365.25 / 2) before <em>tekufas
	 * Nissan</em>, or 12D and 15H before <em>Rosh Hashana</em> of year 1. Outside of Israel we start reciting <em>Tal
	 * Umatar</em> in <em>Birkas Hashanim</em> from 60 days after <em>tekufas Tishrei</em>. The 60 days include the day of
	 * the <em>tekufah</em> and the day we start reciting <em>Tal Umatar</em>. 60 days from the tekufah == 47D and 9H
	 * from <em>Rosh Hashana</em> year 1.
	 * 
	 * @return the number of elapsed days since <em>tekufas Tishrei</em>.
	 * 
	 * @see #isVeseinTalUmatarStartDate()
	 * @see #isVeseinTalUmatarStartingTonight()
	 * @see #isVeseinTalUmatarRecited()
	 */
	public int getTekufasTishreiElapsedDays() {
		// Days since Rosh Hashana year 1. Add 1/2 day as the first tekufas tishrei was 9 hours into the day. This allows all
		// 4 years of the secular leap year cycle to share 47 days. Truncate 47D and 9H to 47D for simplicity.
		double days = getJewishCalendarElapsedDays(getJewishYear()) + (getDaysSinceStartOfJewishYear()-1) + 0.5;
		// days of completed solar years
		double solar = (getJewishYear() - 1) * 365.25;
		return (int) Math.floor(days - solar);
	}

	/**
	 * Returns if it is the Jewish day (starting the evening before) to start reciting <em>Vesein Tal Umatar
	 * Livracha</em> (<em>Sheailas Geshamim</em>). In Israel this is the 7th day of <em>Marcheshvan</em>. Outside
	 * Israel recitation starts on the evening of December 4th (or 5th if it is the year before a civil leap year)
	 * in the 21st century and shifts a day forward every century not evenly divisible by 400. This method will
	 * return true if <em>vesein tal umatar</em> on the current Jewish date that starts on the previous night, so
	 * Dec 5/6 will be returned by this method in the 21st century. <em>vesein tal umatar</em> is not recited on
	 * <em>Shabbos</em> and the start date will be delayed a day when the start day is on a <em>Shabbos</em> (this
	 * can only occur out of Israel).
	 * 
	 * @return true if it is the first Jewish day (starting the prior evening of reciting <em>Vesein Tal Umatar
	 * Livracha</em> (<em>Sheailas Geshamim</em>).
	 * 
	 * @see #isVeseinTalUmatarStartingTonight()
	 * @see #isVeseinTalUmatarRecited()
	 */
	public boolean isVeseinTalUmatarStartDate() {
		if (inIsrael) {
			 // The 7th Cheshvan can't occur on Shabbos, so always return true for 7 Cheshvan
			if (getJewishMonth() == CHESHVAN && getJewishDayOfMonth() == 7) {
				return true;
			}
		} else {
			if (getDayOfWeek() == Calendar.SATURDAY) { //Not recited on Friday night
				return false;
			}
			if(getDayOfWeek() == Calendar.SUNDAY) { // When starting on Sunday, it can be the start date or delayed from Shabbos
				return getTekufasTishreiElapsedDays() == 48 || getTekufasTishreiElapsedDays() == 47;
			} else {
				return getTekufasTishreiElapsedDays() == 47;
			}
		}
		return false; // keep the compiler happy
	}
	
	/**
	 * Returns if true if tonight is the first night to start reciting <em>Vesein Tal Umatar Livracha</em> (
	 * <em>Sheailas Geshamim</em>). In Israel this is the 7th day of <em>Marcheshvan</em> (so the 6th will return
	 * true). Outside Israel recitation starts on the evening of December 4th (or 5th if it is the year before a
	 * civil leap year) in the 21st century and shifts a day forward every century not evenly divisible by 400.
	 * <em>Vesein tal umatar</em> is not recited on <em>Shabbos</em> and the start date will be delayed a day when
	 * the start day is on a <em>Shabbos</em> (this can only occur out of Israel).
	 * 
	 * @return true if it is the first Jewish day (starting the prior evening of reciting <em>Vesein Tal Umatar
	 * Livracha</em> (<em>Sheailas Geshamim</em>).
	 * 
	 * @see #isVeseinTalUmatarStartDate()
	 * @see #isVeseinTalUmatarRecited()
	 */
	public boolean isVeseinTalUmatarStartingTonight() {
		if (inIsrael) {
			// The 7th Cheshvan can't occur on Shabbos, so always return true for 6 Cheshvan
			if (getJewishMonth() == CHESHVAN && getJewishDayOfMonth() == 6) {
					return true;
			}
		} else {
			if (getDayOfWeek() == Calendar.FRIDAY) { //Not recited on Friday night
				return false;
			}
			if(getDayOfWeek() == Calendar.SATURDAY) { // When starting on motzai Shabbos, it can be the start date or delayed from Friday night
				return getTekufasTishreiElapsedDays() == 47 || getTekufasTishreiElapsedDays() == 46;
			} else {
				return getTekufasTishreiElapsedDays() == 46;
			}
		}
		return false;
	}

	/**
	 * Returns if <em>Vesein Tal Umatar Livracha</em> (<em>Sheailas Geshamim</em>) is recited. This will return
	 * true for the entire season, even on <em>Shabbos</em> when it is not recited.
	 * @return true if <em>Vesein Tal Umatar Livracha</em> (<em>Sheailas Geshamim</em>) is recited.
	 * 
	 * @see #isVeseinTalUmatarStartDate()
	 * @see #isVeseinTalUmatarStartingTonight()
	 */
	public boolean isVeseinTalUmatarRecited() {
		if (getJewishMonth() == NISSAN && getJewishDayOfMonth() < 15) {
			return true;
		}
		if (getJewishMonth() < CHESHVAN) {
			return false;
		}
		if (inIsrael) {
			return getJewishMonth() != CHESHVAN || getJewishDayOfMonth() >= 7;
		} else {
			return getTekufasTishreiElapsedDays() >= 47;
		}
	}
	
	/**
	 * Returns if <em>Vesein Beracha</em> is recited. It is recited from 15 <em>Nissan</em> to the point that {@link
	 * #isVeseinTalUmatarRecited() <em>vesein tal umatar</em> is recited}.
	 * 
	 * @return true if <em>Vesein Beracha</em> is recited.
	 * 
	 * @see #isVeseinTalUmatarRecited()
	 */
	public boolean isVeseinBerachaRecited() {
		return !isVeseinTalUmatarRecited();
	}

	/**
	 * Returns if the date is the start date for reciting <em>Mashiv Haruach Umorid Hageshem</em>. The date is 22 <em>Tishrei</em>.
	 * 
	 * @return true if the date is the start date for reciting <em>Mashiv Haruach Umorid Hageshem</em>.
	 * 
	 * @see #isMashivHaruachEndDate()
	 * @see #isMashivHaruachRecited()
	 */
	public boolean isMashivHaruachStartDate() {
		return getJewishMonth() == TISHREI && getJewishDayOfMonth() == 22;
	}

	/**
	 * Returns if the date is the end date for reciting <em>Mashiv Haruach Umorid Hageshem</em>. The date is 15 <em>Nissan</em>.
	 * 
	 * @return true if the date is the end date for reciting <em>Mashiv Haruach Umorid Hageshem</em>.
	 * 
	 * @see #isMashivHaruachStartDate()
	 * @see #isMashivHaruachRecited()
	 */
	public boolean isMashivHaruachEndDate() {
		return getJewishMonth() == NISSAN && getJewishDayOfMonth() == 15;
	}

	/**
	 * Returns if <em>Mashiv Haruach Umorid Hageshem</em> is recited. This period starts on 22 <em>Tishrei</em> and ends
	 * on the 15th day of <em>Nissan</em>.
	 * <em>Marcheshvan</em>. Outside of Israel recitation starts on December 4/5.
	 * 
	 * @return true if <em>Mashiv Haruach Umorid Hageshem</em> is recited.
	 * 
	 * @see #isMashivHaruachStartDate()
	 * @see #isMashivHaruachEndDate()
	 */
	public boolean isMashivHaruachRecited() {
		JewishDate startDate = new JewishDate(getJewishYear(), TISHREI, 22);
		JewishDate endDate = new JewishDate(getJewishYear(), NISSAN, 15);
		return compareTo(startDate) > 0 && compareTo(endDate) < 0;
	}

	/**
	 * Returns if <em>Morid Hatal</em> (or the lack of reciting <em>Mashiv Haruach</em> following <em>nussach Ashkenaz</em>) is recited.
	 * This period starts on 22 <em>Tishrei</em> and ends on the 15th day of
	 * <em>Nissan</em>.
	 * 
	 * @return true if <em>Morid Hatal</em> (or the lack of reciting <em>Mashiv Haruach</em> following <em>nussach Ashkenaz</em>) is recited.
	 */
	public boolean isMoridHatalRecited() {
		return !isMashivHaruachRecited() || isMashivHaruachStartDate() || isMashivHaruachEndDate();
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 * @see Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof JewishCalendar)) {
			return false;
		}
		JewishCalendar jewishCalendar = (JewishCalendar) object;
		return getAbsDate() == jewishCalendar.getAbsDate() && getInIsrael() == jewishCalendar.getInIsrael();
	}

	/**
	 * Overrides {@link Object#hashCode()}.
	 * @see Object#hashCode()
	 */
	public int hashCode() {
		int result = 17;
		result = 37 * result + getClass().hashCode(); // needed or this and subclasses will return identical hash
		result += 37 * result + getAbsDate() + (getInIsrael() ? 1 : 3);
		return result;
	}
}
