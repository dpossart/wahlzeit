package org.wahlzeit.model;

import java.util.concurrent.atomic.AtomicLong;

public class ChessPlayer {

	private static final AtomicLong counter = new AtomicLong();
	
	protected ChessPlayerType chessPlayerType;
	protected long id;
	
	public ChessPlayer(ChessPlayerType chessPlayerType) {
		this.chessPlayerType = chessPlayerType;
		this.id = counter.getAndIncrement();
	}
	
	public long getId() {
		return id;
	}
	
	public ChessPlayerType getChessPlayerType() {
		return chessPlayerType;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof ChessPlayer)) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		ChessPlayer cp = (ChessPlayer) obj;
		return chessPlayerType.equals(cp.chessPlayerType);
	}
	
	@Override
	public int hashCode() {
		return (int)id;
	}

}
