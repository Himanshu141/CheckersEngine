/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import board.Board;
import board.Piece;
import board.Position;

/**
 *
 * @author himanshu
 */
public class Normal {

    public static long getMoves(long[][] board, Piece piece) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        long bitmap = Board.getBitmapAtPosition(board, piece.getP()) ;
        long result = 0;
        int color = piece.getColor() ;
        int x = piece.getP().getX() + 1;
        int y = piece.getP().getY() + 1;
        int temp  = (8 - piece.getP().getY()) ;
        if(color==1){
            if(temp>1){
                result = result | (bitmap << (9));
                
                if (Board.occupied(board, new Position(x, y))) {
                    
                }
            
                x++;
                y++;
            }
            /* Squares on the lower-right diagonal of the Bishop. */
        
        
            x = piece.getP().getX() + 1;
            y = piece.getP().getY() - 1;
        
            /* Squares on the lower-left diagonal of the Bishop. */
            temp = (piece.getP().getY() + 1) ;
            if(temp>1){
                result = result | (bitmap << (7));
            
                if (Board.occupied(board, new Position(x, y))) {
                
                }
            
                x++;
                y--;
            }
        
            
        }else{
            x = piece.getP().getX() - 1;
            y = piece.getP().getY() + 1;
        
            /* Squares on the upper-right diagonal of the Bishop. */
            temp = (8 - piece.getP().getY());
            if(temp>1){
                result = result | (bitmap >>> (7));
            
                if (Board.occupied(board, new Position(x, y))) {
               
                }
            
                x--;
                y++;
            }
        
        
            x = piece.getP().getX() - 1;
            y = piece.getP().getY() - 1;
        
            /* Squares on the upper-left diagonal of the Bishop. */
            temp = (piece.getP().getY() + 1) ;
            if(temp>1){
                result = result | (bitmap >>> (9));
            
                if (Board.occupied(board, new Position(x, y))) {
                    
                }
            
                x--;
                y--;
            }
            
        }
        
        
        
        
        return result;
    }
    
}
