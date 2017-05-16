/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package board;


import pieces.King;
import pieces.Normal;

/**
 *
 * @author himanshu
 */
public class Board {
    
    private static long[] masks = null;
    
    public static class X {
        public static final int A = 0;
        public static final int B = 1;
        public static final int C = 2;
        public static final int D = 3;
        public static final int E = 4;
        public static final int F = 5;
        public static final int G = 6;
        public static final int H = 7;
    }
    
    /* All of the important bitmaps used by the Bitboard. */
    public static class Bitmaps {
        
        /* Bitmaps for white pieces */
        private static final long[] WHITE_PIECES = {
                0xAA55AA0000000000L, /* NORMAL   */
                0x0000000000000000L, /* KING  */
        };
        
        /* Bitmaps for black pieces */
        private static final long[] RED_PIECES = {
                0x000000000055AA55L, /* NORMAL   */
                0x0000000000000000L, /* KING  */
                
        };
        
        /* Bitmaps for board Y */
        public static final long[] Y = {
                0xFF00000000000000L, /* 1 */
                0x00FF000000000000L, /* 2 */
                0x0000FF0000000000L, /* 3 */
                0x000000FF00000000L, /* 4 */
                0x00000000FF000000L, /* 5 */
                0x0000000000FF0000L, /* 6 */
                0x000000000000FF00L, /* 7 */
                0x00000000000000FFL, /* 8 */
        };
        
        /* Bitmaps for board X */
        private static final long[] X = {
                0x0101010101010101L, /* A */
                0x0202020202020202L, /* B */
                0x0404040404040404L, /* C */
                0x0808080808080808L, /* D */
                0x1010101010101010L, /* E */
                0x2020202020202020L, /* F */
                0x4040404040404040L, /* G */
                0x8080808080808080L, /* H */
        };
    }
    
    public Board(){
        masks = new long[64];
        
        for (int i = 0; i < masks.length; i++) {
            masks[i] = 1L << i;
        }
    }
    
    public static long[][] createBoard() {
        long[][] board = new long[2][2];
        
        for (int i = 0; i < board.length; i++) {
        	board[i][0] = Bitmaps.WHITE_PIECES[i];
        	board[i][1] = Bitmaps.RED_PIECES[i];
        }
        
        if (Board.masks == null) {
            Board.masks = new long[64];
            
            for (int i = 0; i < Board.masks.length; i++) {
                Board.masks[i] = 1L << i;
            }
        }
        
        return board;
    }
    
    public static long getBitmap(long[][] board) {
        long result = 0;
        
        for (int i = 0; i < board.length; i++) {
            result = result | board[i][0] | board[i][1];
        }
        
        return result;
    }
    
    public static long getBitmap(long[][] board, Piece piece) {
        return getBitmap(board, piece.getColor(), piece.getType());
    }
    
    public static long getBitmap(long[][] board, int color, int type) {
        return board[type][color];
    }
    
    public static long getBitmap(long[][] board, int color) {
        long result = 0;
        
        for (int i = 0; i < board.length; i++) {
            result = result | board[i][color];
        }
        
        return result;
    }
    
    public static long getBitmapAtPosition(long[][] board, Position position) {
        return Board.getBitmap(board) & Board.getMaskAtPosition(position);
    }
    
    public static long getBitmapAtPosition(long[][] board, int color, Position position) {
        long result = Board.getBitmap(board, color) & Board.getMaskAtPosition(position);
        
        return result;
    }
    
    public static Position getPositionFromBitmap(long bitmap) {
        Position result = new Position(0, 0);
        
        for (int i = 1; i < 65; i++) {
            if ((bitmap & 1) == 1) {
                result.setY((i - 1) % 8);
                
                break;
            }
            
            if ((i % 8) == 0) {
                result.setX(result.getX() + 1);
            }
            
            bitmap = bitmap >>> 1;
        }
        
        return result;
    }
    
    public static int getColorFromBitmap(long[][] board, long bitmap) {
        int result = -1;
        
        for (int i = 0; i < board.length; i++) {
            if ((board[i][0] & bitmap) != 0) {
                result = 0;
                
                break;
            } else if ((board[i][1] & bitmap) != 0) {
                result = 1;
                
                break;
            }
        }
        
        return result;
    }
    
    public static int getTypeFromBitmap(long[][] board, long bitmap) {
        int result = -1;
        
        for (int i = 0; i < board.length; i++) {
            if ((board[i][0] & bitmap) != 0) {
                result = i;
                
                break;
            } else if ((board[i][1] & bitmap) != 0) {
                result = i;
                
                break;
            }
        }
        
        return result;
    }
    
    public static Piece getPiece(long[][] board, Position position) {
        Piece result = null;
        
        if (Board.occupied(board, position)) {
            long bitmap = Board.getBitmapAtPosition(board, position);
            
            result = new Piece(Board.getColorFromBitmap(board, bitmap), Board.getTypeFromBitmap(board, bitmap));
            
            result.setP(position);
        }
        
        return result;
    }
    
    public static long getMoves(long[][] board, Piece piece) {
        long result = 0;
        
        if (piece == null) {
            return result;
        }
        
        /* What piece are we looking for? */
        switch (piece.getType()) {
            case 1:
                result = King.getMoves(board, piece);
                
                break;
            case 0:
                result = Normal.getMoves(board, piece);
                
                break;
            default:
                break;
        }
        
        /* Blocks squares held by a piece of the same color. */
        result = result & ~Board.getBitmap(board, piece.getColor());
        
        return result;
    }
    
    public static long getY(int y) {
        return Bitmaps.Y[y];
    }
    
    public static long getX(int x) {
        return Bitmaps.X[x];
    }
    
    public static long getMaskAtPosition(Position position) {
        long result = 0;
        
        if ((getIndexAtPosition(position) < 64) && (getIndexAtPosition(position) > -1)) {
            result = Board.masks[getIndexAtPosition(position)];
        }
        
        return result;
    }
    public static long[][] makeMove(long[][] board, Move move){
        Position start = move.getX() ;
        Position end = move.getY() ;
        Piece startPiece = Board.getPiece(board,start) ;
        int color = startPiece.getColor() ;
        if(color==0){
            long bitsEnd = Board.getMaskAtPosition(end) ;
            long bitsY7 = Bitmaps.Y[7] ;
        }else{
            long bitsEnd = Board.getMaskAtPosition(end) ;
            long bitsY1 = Bitmaps.Y[0] ;
        }
        int x1 = start.getX() ;
        int y1 = start.getY() ;
        int x2 = end.getX() ;
        int y2 = end.getY() ;
        int xd = x2-x1 ;
        int yd = y2-y1 ;
        int x3 = 0 ;
        int y3 = 0 ;
        if(xd < 0){
            x3 = x1-1 ;
        }else{
            x3 = x1+1 ;
        }
            
        if(yd < 0){
            y3 = y1-1 ;
        }else{
            y3 = y1+1 ;
        }
        
        if(Math.abs(xd)>1){
                 Position temp = new Position(x3,y3) ;
                 Piece current2 = Board.getPiece(board,temp);
                 board = Board.removePiece(board,current2,temp) ;
        }
        
        Piece current = Board.getPiece(board,start);
        board = Board.removePiece(board, current, start);
        if(color==0){
            long bitsEnd = Board.getMaskAtPosition(end) ;
            long bitsY7 = Bitmaps.Y[7] ;
            if((bitsEnd & bitsY7) > 0){
                Piece tempKing = new Piece(end) ;
                tempKing.setColor(0);
                tempKing.setType(1);
                board = Board.movePiece(board, tempKing, end);
            }else{
                board = Board.movePiece(board, current, end);
            }
        }else{
            long bitsEnd = Board.getMaskAtPosition(end) ;
            long bitsY1 = Bitmaps.Y[0] ;
            if((bitsEnd & bitsY1) > 0){
                Piece tempKing = new Piece(end) ;
                tempKing.setColor(1);
                tempKing.setType(1);
                board = Board.movePiece(board, tempKing, end);
            }else{
                board = Board.movePiece(board, current, end);
            }
        }
        
        
        
        return board ;
    }
    
    public static long[][] movePiece(long[][] board, Piece piece, Position position) {
        board[piece.getType()][piece.getColor()] = Board.getBitmap(board, piece) | Board.getMaskAtPosition(position);
        
        return board;
    }
    
    public static long[][] removePiece(long[][] board, Piece piece, Position position) {
        board[piece.getType()][piece.getColor()] = Board.getBitmap(board, piece) & ~Board.getMaskAtPosition(position);
        
        return board;
    }
    
    public static boolean occupied(long[][] board, Position position) {
        return (Board.getBitmap(board) & Board.getMaskAtPosition(position)) != 0;
    }
    
    public static boolean isPositionAttacked(long bitmap, Position position) {
        return (bitmap >>> (((position.getX() * 8) + position.getY())) & 1) == 1;
    }
    
    public static int getIndexAtPosition(Position position) {
        return (position.getX() << 3) + position.getY();
    }
    
    public static String getString(long bitmap) {
        String result = "";
        
        for (int i = 1; i < 65; i++) {
            if ((bitmap & 1) == 1) {
                result = result + "1";
            } else {
                result = result + "0";
            }
            
            if ((i % 8) == 0) {
                result = result + "\n";
            }
            
            bitmap = bitmap >>> 1;
        }
        
        return result;
    }
    
}
