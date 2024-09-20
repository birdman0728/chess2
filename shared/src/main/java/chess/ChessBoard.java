package chess;

import java.util.Arrays;
import java.util.Objects;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    int maxRow = 8;
    int maxCol = 8;

    ChessPiece[][] board = new ChessPiece[maxCol][maxRow];

    public ChessBoard() {

    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        ChessPiece newPiece = new ChessPiece(piece.getTeamColor(), piece.getPieceType());
        board[position.getColumn()-1][position.getRow()-1] = newPiece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return board[position.getColumn()-1][position.getRow()-1];
    }

    public void removePiece(ChessPosition position) {
        board[position.getColumn()-1][position.getRow()-1] = null;
    }


    public void clearBoard(){
        for(int i = 1; i <=8; i++){
            for(int j = 1; j<=8; j++){
                removePiece(new ChessPosition(i, j));
            }
        }
    }


    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {


        //pawns
        for(int i = 1; i<=8; i++) {
            addPiece(new ChessPosition(2, i), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
            addPiece(new ChessPosition(7, i), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
        }
        //White rooks
        addPiece(new ChessPosition(1,1),new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK ));
        addPiece(new ChessPosition(1,8),new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK ));
        //Black rooks
        addPiece(new ChessPosition(8,1),new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK ));
        addPiece(new ChessPosition(8,8),new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK ));

        //White knights
        addPiece(new ChessPosition(1,2),new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT ));
        addPiece(new ChessPosition(1,7),new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT ));
        //Black Knights
        addPiece(new ChessPosition(8,2),new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT ));
        addPiece(new ChessPosition(8,7),new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT ));

        //White bishops
        addPiece(new ChessPosition(1,3),new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP ));
        addPiece(new ChessPosition(1,6),new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP ));
        //Black bishops
        addPiece(new ChessPosition(8,3),new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP ));
        addPiece(new ChessPosition(8,6),new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP ));

        //White Queen
        addPiece(new ChessPosition(1,4),new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN ));
        //Black Queen
        addPiece(new ChessPosition(8,4),new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN ));

        //White King
        addPiece(new ChessPosition(1,5),new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING ));
        //Black King
        addPiece(new ChessPosition(8,5),new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING ));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoard that = (ChessBoard) o;
        return maxRow == that.maxRow && maxCol == that.maxCol && Arrays.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(maxRow, maxCol);
        result = 31 * result + Arrays.deepHashCode(board);
        return result;
    }

    @Override
    public String toString() {
        return "ChessBoard{" +
                "maxRow=" + maxRow +
                ", maxCol=" + maxCol +
                ", board=" + Arrays.deepToString(board) +
                '}';
    }
}
