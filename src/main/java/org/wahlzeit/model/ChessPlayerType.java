package org.wahlzeit.model;

import java.util.Objects;
import org.wahlzeit.services.DataObject;
import org.wahlzeit.utils.StringUtil;

public class ChessPlayerType extends DataObject {

	private String playerName;

	public ChessPlayerType(String playerName) {
		if (StringUtil.isNullOrEmptyString(playerName)) {
			throw new IllegalArgumentException("Chess player name is null or empty");
		}

		if (StringUtil.containsDigit(playerName)) {
			throw new IllegalArgumentException("Chess player names should not contain digits");
		}
		this.playerName = playerName;
	}

	public ChessPlayer createInstance() {
		return new ChessPlayer(this);
	}

	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Chessplayer do not have a hierarchy
	 * 
	 * @return false
	 */
	public boolean isSuptype() {
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof ChessPlayerType)) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		ChessPlayerType cp = (ChessPlayerType) obj;
		return playerName.equals(cp.playerName);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(playerName);
	}

}
