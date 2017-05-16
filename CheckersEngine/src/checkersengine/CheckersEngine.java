/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkersengine;

import AI.MCTS_Search;
import board.Move;
import board.Position;
import board.Board ;
import AI.MoveGenerator ;
import AI.Node;
import board.Piece;
import java.util.LinkedList;
/**
 *
 * @author himanshu
 */
public class CheckersEngine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
       CheckersGUI gui = new CheckersGUI() ;
       MCTS_Search search = new MCTS_Search(300) ;
       /*long[][] board = Board.createBoard() ;
       LinkedList<Move> moves = MoveGenerator.generateMovesForColor(board, 1) ;
       gui.movePiece(moves.get(1),board);
       board = Board.makeMove(board, moves.get(1)) ;
       
       moves = MoveGenerator.generateMovesForColor(board, 0) ;
       gui.movePiece(moves.get(2),board);
       board = Board.makeMove(board, moves.get(2)) ;
       
       moves = MoveGenerator.generateMovesForColor(board, 1) ;
       Move bestMove = search.MCTS_searchForMove(board ,1) ;
       gui.movePiece(moves.get(7),board);
       board = Board.makeMove(board,moves.get(7)) ;
       
       moves = MoveGenerator.generateMovesForColor(board, 0) ;
       gui.movePiece(moves.get(3),board);
       board = Board.makeMove(board,moves.get(3)) ;
       
       moves = MoveGenerator.generateMovesForColor(board, 0) ;
       gui.movePiece(moves.get(7),board);
       board = Board.makeMove(board,moves.get(7)) ;
       
       moves = MoveGenerator.generateMovesForColor(board, 0) ;
       gui.movePiece(moves.get(7),board);
       board = Board.makeMove(board,moves.get(7)) ;
       
       moves = MoveGenerator.generateMovesForColor(board, 1) ;
       gui.movePiece(moves.get(8),board);
       board = Board.makeMove(board,moves.get(8)) ;
       
       moves = MoveGenerator.generateMovesForColor(board, 1) ;
       
       for(Move m : moves){
           Position A = m.getX() ;
           Position B = m.getY() ;
           System.out.println("Initial: "+A.getX()+"-"+A.getY()+" Final: "+B.getX()+"-"+B.getY());
       }*/
       long[][] board = Board.createBoard() ;
       long bitWhite = Board.getBitmap(board, 0 );
       int color = 0 ;
       long bitRed = Board.getBitmap(board, 1) ;
       while((bitRed >0) || (bitWhite >0)){
            Move bestMove = search.MCTS_searchForMove(board ,color) ;
            Position start = bestMove.getX() ;
            Position end = bestMove.getY() ;
            Piece startP = Board.getPiece(board, start) ;
            if(startP == null){
                continue ;
            }
            Piece endP = Board.getPiece(board, end) ;
            if(endP != null){
                continue ;
            }
            int x1 = start.getX() ;
            int y1 = start.getY() ;
            int x2 = end.getX() ;
            int y2 = end.getY() ;
            int xd = x2-x1 ;
            int yd = y2-y1 ;
            int x3 = 0 ;
            int y3 = 0 ;
            if(xd == -2){
                x3 = x1-1 ;
            }else if(xd == 2){
                 x3 = x1+1 ;
            }
            
            if(yd == -2){
                y3 = y1-1 ;
            }else if(yd == 2){
                    y3 = y1+1 ;
            }
            
            if(Math.abs(xd)==2){
                Position temp = new Position(x3,y3) ;
                Piece capture = Board.getPiece(board,temp);
                if(capture == null){
                    continue ;
                }
            }else{
                color = Node.invertColor(color) ;
            }
           // Piece capture = 
            gui.movePiece(bestMove,board);
            board = Board.makeMove(board,bestMove) ;
            bitWhite = Board.getBitmap(board, 0 );
            bitRed = Board.getBitmap(board, 1) ;
            //Thread.sleep(1000);
       }
       
       if(bitRed == 0){
           System.out.println("White Won");
       }else{
           System.out.println("Red Won");
       }
       /*System.out.println("Best Node:--");
       Position A = bestMove.getX() ;
       Position B = bestMove.getY() ;
       System.out.println("Initial: "+A.getX()+"-"+A.getY()+" Final: "+B.getX()+"-"+B.getY());*/
      // Position X = new Position(1,1) ;
      // Position Y = new Position(4,4) ;
      // Move move = new Move(X,Y) ;
      // gui.movePiece(move);
    }
    
}
