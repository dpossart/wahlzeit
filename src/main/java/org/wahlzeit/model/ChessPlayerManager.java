package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Map;

import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.utils.StringUtil;

public class ChessPlayerManager extends ObjectManager {

	private final Map<Long, ChessPlayer> chessPlayers;
	private final Map<String, ChessPlayerType> chessPlayerTypes;

	public static final ChessPlayerManager instance = new ChessPlayerManager();

	private ChessPlayerManager() {
		chessPlayers = new HashMap<Long, ChessPlayer>();
		chessPlayerTypes = new HashMap<String, ChessPlayerType>();
	}
	
	/**
	 * 
	 * @return 
	 */
	public static ChessPlayerManager getInstance() {
		return instance;
	}

	/**
	 * 
	 * @param typeName
	 * @return null if the creation failed else chessplayer object
	 */
	public ChessPlayer createChessPlayer(String typeName) {
		if (StringUtil.containsDigit(typeName) || StringUtil.isNullOrEmptyString(typeName)) {
			return null;
		}
		ChessPlayerType cpt = getChessPlayerType(typeName);
		ChessPlayer result = cpt.createInstance();
		chessPlayers.put(result.getId(), result);
		return result;
	}

	private ChessPlayerType getChessPlayerType(String typeName) {
		ChessPlayerType res = chessPlayerTypes.get(typeName);
		if (res == null) {
			synchronized (chessPlayerTypes) {
				res = chessPlayerTypes.get(typeName);
				if (res == null) {
					res = new ChessPlayerType(typeName);
					chessPlayerTypes.put(typeName, res);
				}
			}
		}
		return res;
	}
}
