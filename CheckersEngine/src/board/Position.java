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
public class Position {
    private int X , Y ;
    
    public Position(){
        this.X = 0 ;
        this.Y = 0 ;
    }
    
    public Position(int X,int Y){
        this.X = X ;
        this.Y = Y ;
    }
    
    public int getX(){
        return this.X ;
    }
    
    public int getY(){
        return this.Y ;
    }
    
    public void setX(int x){
        this.X =  x;
    }
    
    public void setY(int y){
        this.Y =  y;
    }
    
    public boolean equals(Object object){
        if (object == this) {
            return true;
        }
        
        if (object == null) {
            return false;
        }
        
        if (object instanceof Position) {
            Position position = (Position)object;
            
            return (position.getX() == this.X) && (position.getY() == this.Y);
        }
        
        return false;
    }
    
}
