package chess.piecemoves;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.*;

public class QueenMovesCalculator  implements MovesCalculator{
    @Override
    public Collection<ChessMove> calculate(ChessBoard board, ChessPosition from, ChessPiece piece) {
        List<ChessMove> moves = new ArrayList<>();
        int r = from.getRow();
        int c = from.getColumn();

        moves.addAll(MovesCalculator.contInThisDir(+1, +1, r, c, board, piece));
        moves.addAll(MovesCalculator.contInThisDir(+1, -1, r, c, board, piece));
        moves.addAll(MovesCalculator.contInThisDir(+1, +0, r, c, board, piece));
        moves.addAll(MovesCalculator.contInThisDir(-1, +1, r, c, board, piece));
        moves.addAll(MovesCalculator.contInThisDir(-1, -1, r, c, board, piece));
        moves.addAll(MovesCalculator.contInThisDir(-1, +0, r, c, board, piece));
        moves.addAll(MovesCalculator.contInThisDir(+0, +1, r, c, board, piece));
        moves.addAll(MovesCalculator.contInThisDir(+0, -1, r, c, board, piece));


        return moves;
    }
}
