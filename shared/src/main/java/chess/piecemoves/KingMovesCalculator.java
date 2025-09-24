package chess.piecemoves;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.*;

public class KingMovesCalculator  implements MovesCalculator{
    private static final int[][] KING_OFFSET = {
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1},
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public static boolean inBounds(int row, int col) {
        return row <=8 && row > 0 && col <=8 && col > 0;
    }

    public static boolean notBlocked(int row, int col, ChessPiece piece, ChessBoard board) {
        ChessPiece target = board.getPiece(new ChessPosition(row, col));
        if (target == null) {
            return true;
        } else if (!target.getTeamColor().equals(piece.getTeamColor())) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Collection<ChessMove> calculate(ChessBoard board, ChessPosition from, ChessPiece piece) {
        List<ChessMove> moves = new ArrayList<>();
        for (int[] i : KING_OFFSET) {
            int r1 = from.getRow() + i[0];
            int c1 = from.getColumn() + i[1];
            if (inBounds(r1, c1) && notBlocked(r1, c1, piece, board)) {
                moves.add(new ChessMove(from, new ChessPosition(r1, c1), null));
            }
        }

        return moves;

    }
}
