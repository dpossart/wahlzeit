/*
 * Copyright (c) 2019 by Dennis Possart
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ChessPlayerPhoto extends Photo {

	protected String playerName;
	protected String place;
	protected int year;

	public ChessPlayerPhoto(String playerName, String place, int year) {
		super();
		validateString(playerName);
		validateString(place);
		validateYear(year);
		this.playerName = playerName;
		this.place = place;
		this.year = year;
	}

	public ChessPlayerPhoto(PhotoId myId, String playerName, String place, int year) {
		super(myId);
		validateString(playerName);
		validateString(place);
		validateYear(year);
		this.playerName = playerName;
		this.place = place;
		this.year = year;
	}

	public String getPlayerName() {
		return playerName;
	}

	public String getPlace() {
		return place;
	}

	public int getYear() {
		return year;
	}

	protected void validateString(String str) {
		boolean isValid = true;
		for(char c : str.toCharArray()) {
			if(Character.isDigit(c)&& (c!=" ".charAt(0))) {
				isValid = false;
			}
		}
		if (!isValid) {
			throw new IllegalArgumentException("invalid String contains digits=" + str);
		}

	}

	protected void validateYear(int year) {
		Date curTime = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(curTime);
		int curYear = calendar.get(Calendar.YEAR);

		if (year >= curYear || year < 0) {
			throw new IllegalArgumentException("invalid year=" + String.valueOf(year));
		}

	}

}
