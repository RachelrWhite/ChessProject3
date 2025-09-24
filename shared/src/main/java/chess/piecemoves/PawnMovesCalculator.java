package chess.piecemoves;

import chess.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PawnMovesCalculator  implements MovesCalculator{
    public static boolean isBlocked(int row, int col, ChessBoard board) {
        ChessPiece testPiece = board.getPiece(new ChessPosition(row, col));

        if (testPiece == null) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValid(int row, int col) {
        return row <= 8 && row > 0 && col <= 8 && col > 0;
    }

    public static boolean isEnemyPiece (int row, int col, ChessBoard board, ChessPiece piece) {
        ChessPiece testPiece = board.getPiece(new ChessPosition(row, col));

        if (testPiece == null) {
            return false;
        } else if (!testPiece.getTeamColor().equals(piece.getTeamColor())) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public Collection<ChessMove> calculate(ChessBoard board, ChessPosition from, ChessPiece piece) {
        List<ChessMove> moves = new ArrayList<>();

        //this one is terrible and i hate it and i want to go to bed

        //this si going to be for the white pawns - i'm separating them by race becasue it is easier not becasue i am raciest
        if (piece.getTeamColor() == ChessGame.TeamColor.WHITE) {
            int r = from.getRow();
            int c = from.getColumn();

            //check to see if they can do the normal classic pawn move ahead 1 thing
            int r1 = r + 1;

            if (!isBlocked(r1, c, board) && !isEnemyPiece(r1, c, board, piece) && isValid(r1, c)) {
                //check to see if it is eligible for the promotion piece
                if (r1 == 8) {
                    moves.add(new ChessMove(from, new ChessPosition(r1, c), ChessPiece.PieceType.ROOK));
                    moves.add(new ChessMove(from, new ChessPosition(r1, c), ChessPiece.PieceType.QUEEN));
                    moves.add(new ChessMove(from, new ChessPosition(r1, c), ChessPiece.PieceType.KNIGHT));
                    moves.add(new ChessMove(from, new ChessPosition(r1, c), ChessPiece.PieceType.BISHOP));
                } else {
                    moves.add(new ChessMove(from, new ChessPosition(r1, c), null));

                    //check to see if it is on the 2nd line and if it is then see if it can move forward again
                    int r2 = r1 + 1;
                    if (r == 2 && !isBlocked(r2, c, board) && !isEnemyPiece(r2, c, board, piece)) {
                        moves.add(new ChessMove(from, new ChessPosition(r2, c), null));
                    }
                }
                //this is going to be for the diagonal boys to see if there is an enemy piece there and if there is then we can just hop skip and jump right over there
                int rDiag1 = r + 1;
                int cDiag1 = c + 1;
                int rDiag2 = r + 1;
                int cDiag2 = c - 1;

                if (isValid(rDiag1, cDiag1) && isEnemyPiece(rDiag1, cDiag1, board, piece)) {
                    if(rDiag1 == 8) {
                        moves.add(new ChessMove(from, new ChessPosition(rDiag1, cDiag1), ChessPiece.PieceType.ROOK));
                        moves.add(new ChessMove(from, new ChessPosition(rDiag1, cDiag1), ChessPiece.PieceType.BISHOP));
                        moves.add(new ChessMove(from, new ChessPosition(rDiag1, cDiag1), ChessPiece.PieceType.KNIGHT));
                        moves.add(new ChessMove(from, new ChessPosition(rDiag1, cDiag1), ChessPiece.PieceType.QUEEN));
                    } else {
                        moves.add(new ChessMove(from, new ChessPosition(rDiag1, cDiag1), null));
                    }
                }
                if (isValid(rDiag2, cDiag2) && isEnemyPiece(rDiag2, cDiag2, board, piece)) {
                    if(rDiag2 == 8) {
                        moves.add(new ChessMove(from, new ChessPosition(rDiag2, cDiag2), ChessPiece.PieceType.ROOK));
                        moves.add(new ChessMove(from, new ChessPosition(rDiag2, cDiag2), ChessPiece.PieceType.BISHOP));
                        moves.add(new ChessMove(from, new ChessPosition(rDiag2, cDiag2), ChessPiece.PieceType.KNIGHT));
                        moves.add(new ChessMove(from, new ChessPosition(rDiag2, cDiag2), ChessPiece.PieceType.QUEEN));
                    } else {
                        moves.add(new ChessMove(from, new ChessPosition(rDiag2, cDiag2), null));
                    }

                }
            }
        }


        //this is going to be for the black pawns
        if (piece.getTeamColor() == ChessGame.TeamColor.BLACK) {
            int r = from.getRow();
            int c = from.getColumn();

            //check to see if they can do the normal classic pawn move ahead 1 thing
            int r1 = r - 1;
//           if (!isBlocked(r1, c, board) && !isEnemyPiece(r1, c, board, piece) && isValid(r1, c)) {
            if (!isBlocked(r1, c, board) && isValid(r1, c)) {
                //check to see if it is eligible for the promotion piece
                if (r1 == 1) {
                    moves.add(new ChessMove(from, new ChessPosition(r1, c), ChessPiece.PieceType.ROOK));
                    moves.add(new ChessMove(from, new ChessPosition(r1, c), ChessPiece.PieceType.QUEEN));
                    moves.add(new ChessMove(from, new ChessPosition(r1, c), ChessPiece.PieceType.KNIGHT));
                    moves.add(new ChessMove(from, new ChessPosition(r1, c), ChessPiece.PieceType.BISHOP));
                } else {
                    moves.add(new ChessMove(from, new ChessPosition(r1, c), null));

                    //check to see if it is on the 2nd line and if it is then see if it can move forward again
                    int r2 = r1 - 1;
                    if (r == 7 && !isBlocked(r2, c, board) && !isEnemyPiece(r2, c, board, piece) && isValid(r2, c)) {
                        moves.add(new ChessMove(from, new ChessPosition(r2, c), null));
                    }
                }
            }

            //this is going to be for the diagonal boys to see if there is an enemy piece there and if there is then we can just hop skip and jump right over there
            int rDiag1 = r - 1;
            int cDiag1 = c + 1;
            int rDiag2 = r - 1;
            int cDiag2 = c - 1;

            if (isValid(rDiag1, cDiag1) && isEnemyPiece(rDiag1, cDiag1, board, piece)) {
                if(rDiag1 == 1) {
                    moves.add(new ChessMove(from, new ChessPosition(rDiag1, cDiag1), ChessPiece.PieceType.ROOK));
                    moves.add(new ChessMove(from, new ChessPosition(rDiag1, cDiag1), ChessPiece.PieceType.BISHOP));
                    moves.add(new ChessMove(from, new ChessPosition(rDiag1, cDiag1), ChessPiece.PieceType.KNIGHT));
                    moves.add(new ChessMove(from, new ChessPosition(rDiag1, cDiag1), ChessPiece.PieceType.QUEEN));
                } else {
                    moves.add(new ChessMove(from, new ChessPosition(rDiag1, cDiag1), null));
                }
            }
            if (isValid(rDiag2, cDiag2) && isEnemyPiece(rDiag2, cDiag2, board, piece)) {
                if (rDiag2 == 1) {
                    moves.add(new ChessMove(from, new ChessPosition(rDiag2, cDiag2), ChessPiece.PieceType.ROOK));
                    moves.add(new ChessMove(from, new ChessPosition(rDiag2, cDiag2), ChessPiece.PieceType.BISHOP));
                    moves.add(new ChessMove(from, new ChessPosition(rDiag2, cDiag2), ChessPiece.PieceType.KNIGHT));
                    moves.add(new ChessMove(from, new ChessPosition(rDiag2, cDiag2), ChessPiece.PieceType.QUEEN));
                } else {
                    moves.add(new ChessMove(from, new ChessPosition(rDiag2, cDiag2), null));
                }

            }
        }


        return moves;
    }
}
