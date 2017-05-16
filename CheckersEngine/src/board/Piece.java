/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package board;

/**
 *
 * @author himanshu
 */
public class Piece {
    
    private Position position ;
    private int color  ;
    private int type ;
    
    public static class Type{
        public static final int PLAIN = 0 ;
        public static final int KING = 1 ;
    }
    
    public static class Color{
        public static final int WHITE = 0 ;
        public static final int RED = 1 ;
    }
    
    public Piece(){
        this.position = null ;
        this.color = -1 ;
        this.type = -1 ;
    }
    
    public Piece(Position p){
        this.position = p ;
        this.color = -1 ;
        this.type = -1 ;
    }
    
    public Piece(int c, int t){
        this.position = null ;
        this.color = c ;
        this.type = t ;
    }
    
    public Position getP(){
        return this.position ;
    }
    
    public void setP(Position p){
        this.position = p ;
    }
    
    public int getColor(){
        return this.color ;
    }
    
    public void setColor(int c){
        this.color = c;
    }
    
    public int getType(){
        return this.type ;
    }
    
    public void setType(int t){
        this.type = t ;
    }
    
    
}
