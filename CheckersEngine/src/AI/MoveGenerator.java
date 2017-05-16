/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

/**
 *
 * @author himanshu
 */
import java.util.LinkedList;

import board.Board;
import board.Move;
import board.Piece;
import board.Position;

public class MoveGenerator {
    
    public static LinkedList<Move> generateMovesForPiece(long[][] board, Piece piece) {
        LinkedList<Move> result = new LinkedList<Move>();
        long bitmap;
        
        bitmap = Board.getMoves(board, piece);
        
        while (bitmap != 0) {
            Position destination = Board.getPositionFromBitmap(bitmap);
            Move move = new Move();
            Piece p = Board.getPiece(board, destination) ;
            move.setX(piece.getP());
            if(p != null){
                if(p.getColor() != piece.getColor()){
                    int x1 = piece.getP().getX() ;
                    int y1 = piece.getP().getY() ;
                    int x2 = p.getP().getX() ;
                    int y2 = p.getP().getY() ;
                    int xd = x2-x1 ;
                    int yd = y2-y1 ;
                    if(xd>0 && yd>0){
                        if(((x2+1)<8)&&((y2+1)<8)){
                            Position temp = new Position(x2+1,y2+1) ;
                            Piece tempP = Board.getPiece(board, temp) ;
                            if(tempP == null){
                                move.setY(temp);
                            }
                        }
                    }else if(xd<0 && yd>0){
                        if(((x2-1)>-1)&&((y2+1)<8)){
                            Position temp = new Position(x2-1,y2+1) ;
                            Piece tempP = Board.getPiece(board, temp) ;
                            if(tempP == null){
                                move.setY(temp);
                            }
                        }
                    }else if(xd>0 && yd<0){
                        if(((x2+1)<8)&&((y2-1)>-1)){
                            Position temp = new Position(x2+1,y2-1) ;
                            Piece tempP = Board.getPiece(board, temp) ;
                            if(tempP == null){
                                move.setY(temp);
                            }
                        }
                    }else{
                        if(((x2-1)>-1)&&((y2-1)>-1)){
                            Position temp = new Position(x2-1,y2-1) ;
                            Piece tempP = Board.getPiece(board, temp) ;
                            if(tempP == null){
                                move.setY(temp);
                            }
                        }
                    }
                }else{
                    move.setY(destination);
                }
                
                
            }else{
                move.setY(destination);
            }
            
            
            result.add(move);
            
            bitmap = bitmap & ~Board.getMaskAtPosition(destination);
        }
        
        return result;
    }
    
    public static LinkedList<Move> generateMovesForColor(long[][] board, int color) {
        LinkedList<Move> result = new LinkedList<Move>();
        
        for (int i = 0; i < 2; i++) {
            long bitmap = Board.getBitmap(board, color, i);
            
            while (bitmap != 0) {
                Piece origin = new Piece(color, i);
                
                origin.setP(Board.getPositionFromBitmap(bitmap));
                
                result.addAll(MoveGenerator.generateMovesForPiece(board, origin));
                
                bitmap = bitmap & ~Board.getMaskAtPosition(origin.getP());
            }
        }
        
        return result;
    }
}
