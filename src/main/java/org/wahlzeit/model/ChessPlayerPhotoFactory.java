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

import java.util.logging.Logger;
import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.utils.DesignPattern;

@DesignPattern(name = "Factory", participants = {})
public class ChessPlayerPhotoFactory extends PhotoFactory {

	private static final Logger log = Logger.getLogger(ChessPlayerPhotoFactory.class.getName());

	private static ChessPlayerPhotoFactory instance = null;

	protected ChessPlayerPhotoFactory() {
		super();
	}

	public static void initialize() {
		getInstance();

	}

	public static synchronized ChessPlayerPhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting ChessPlayerPhotoFactory").toString());
			setInstance(new ChessPlayerPhotoFactory());
		}

		return instance;
	}

	protected static synchronized void setInstance(ChessPlayerPhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize ChessPlayerPhotoFactory twice");
		}

		instance = photoFactory;
	}

	public ChessPlayerPhoto createPhoto(String playerName, String place, int year) {
		ChessPlayerPhoto cp;
		try {
			cp = new ChessPlayerPhoto(playerName, place, year);
		} catch (IllegalArgumentException e) {
			log.warning("creation of ChessPlayerPhoto failed: " + e.getMessage());
			return null;
		}
		return cp;
	}

	public ChessPlayerPhoto createPhoto(PhotoId id, String playerName, String place, int year) {
		ChessPlayerPhoto cp;
		try {
			cp = new ChessPlayerPhoto(id, playerName, place, year);
		} catch (IllegalArgumentException e) {
			log.warning("creation of ChessPlayerPhoto failed: " + e.getMessage());
			return null;
		}
		return cp;
	}

//	@Override
//	public Photo loadPhoto(PhotoId id) {
//		// TODO Auto-generated method stub
//		return super.loadPhoto(id);
//	}

}
