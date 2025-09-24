package chess.piecemoves;
import chess.*;
import java.util.*;


public interface MovesCalculator {


    Collection<ChessMove> calculate(ChessBoard board, ChessPosition from, ChessPiece piece);
    public static boolean inBounds(int row, int col) {
        return row <= 8 && row > 0 && col <= 8 && col > 0;
    }


    static Collection<ChessMove> contInThisDir(int rowDir, int colDir, int rowPos, int colPos, ChessBoard board, ChessPiece piece) {
        List<ChessMove> moves = new ArrayList<>();

        int r1 = rowPos + rowDir;
        int c1 = colPos + colDir;

        while (inBounds(r1, c1)) {
            ChessPiece target = board.getPiece(new ChessPosition(r1, c1));
            //check if the spot is null - return move and keep going
            if (target == null) {
                moves.add(new ChessMove(new ChessPosition(rowPos, colPos), new ChessPosition(r1, c1), null));
            } else if (!target.getTeamColor().equals(piece.getTeamColor())) {
                moves.add(new ChessMove(new ChessPosition(rowPos, colPos), new ChessPosition(r1, c1), null));
                break;
            } else {
                break;
            }
            r1 += rowDir;
            c1 += colDir;
        }
        return moves;
    }


}
