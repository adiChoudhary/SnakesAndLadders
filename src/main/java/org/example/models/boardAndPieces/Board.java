package org.example.models.boardAndPieces;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Board {
    private final int size;
    private final Map<Integer, Piece> pieceMap;

    public Board(int size, List<Piece> pieces) {
        this.size = size;
        this.pieceMap = new HashMap<>();
        setPieceMap(pieces);
    }

    private void setPieceMap(List<Piece> pieces) {
        pieces.forEach(e -> pieceMap.put(e.getStart(), e));
    }
}
