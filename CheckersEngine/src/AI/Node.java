/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import board.Board;
import board.Move;
import board.Piece;
import board.Position;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author himanshu
 */
public class Node {
        public long[] score;
	public double games;
	public Move move;
	public LinkedList<Node> children ;
	//public Set<Integer> rVisited;
	public Node parent;
	public int player;
        public long tempgames ;
	long[][] board ;
        double weight ;
	/**
	 * This creates the root node
	 * 
	 * @param b
	 */
        
        
	public Node(long[][] b,int color,Move move) {
		children = new LinkedList<Node>() ;
                board = b ;
		player = color;
		this.move = move ;
                games= 0 ;
                tempgames = 0 ;
                score = new long[2] ;
                weight = 100000.00 ;
	}
        
        public Node(long[][] b, int color){
            children = new LinkedList<Node>() ;
            board = b ;
            player = color ;
            games = 0 ;
            tempgames = 0 ;
            score = new long[2] ;
            weight = 100000.00 ;
        }
        
        public void addChild(Node n){
            children.add(n );
        }
        
        

        /*public Node search(long[][] board,int color){
            long[][] currboard = duplicate(board) ;
            LinkedList<Move> availableMoves = MoveGenerator.generateMovesForColor(board, color);
            
        }*/
        public void createChildren(long[][] board, int color) {
            LinkedList<Move> availableMoves = MoveGenerator.generateMovesForColor(board, color);
            //Move result = null;
            
        
            for (Move move : availableMoves) {
                Piece current;
                Piece capture = null;
            
                current = Board.getPiece(board, move.getX());
                Piece endP = Board.getPiece(board, move.getY()) ;
                if(current == null){
                    continue ;
                }
                if(endP != null){
                    continue ;
                }
                
                board = Board.removePiece(board, current, move.getX());
                Position start = move.getX() ;
                Position end = move.getY() ;
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
                Piece endPiece = Board.getPiece(board, end) ;
                if(endPiece != null){
                    continue ;
                }
                if(Math.abs(xd)>2 || Math.abs(yd)>2){
                    continue ;
                }
                Position temp = null;
                if(Math.abs(xd)>1){
                        temp = new Position(x3,y3) ;
                        capture = Board.getPiece(board,temp);
                        if(capture != null){
                            board = Board.removePiece(board,capture,temp) ;
                        }
                        
                }
                Piece tempKing = new Piece(end) ;
                boolean tempValue = false;
                if(color==0){
                    long bitsEnd = Board.getMaskAtPosition(end) ;
                    long bitsY7 = Board.Bitmaps.Y[7] ;
                    if((bitsEnd & bitsY7) > 0){
                        tempKing.setColor(0);
                        tempKing.setType(1);
                        tempValue = true;
                        board = Board.movePiece(board, tempKing, end);
                    }else{
                        board = Board.movePiece(board, current, end);
                    }
                }else{
                    long bitsEnd = Board.getMaskAtPosition(end) ;
                    long bitsY1 = Board.Bitmaps.Y[0] ;
                    if((bitsEnd & bitsY1) > 0){
                        tempKing.setColor(1);
                        tempKing.setType(1);
                        tempValue = true;
                        board = Board.movePiece(board, tempKing, end);
                    }else{
                        board = Board.movePiece(board, current, end);
                    }
                }
        
                //board = Board.movePiece(board, current, end);
        
            
                
                
                
                Node child = new Node(duplicate(board),Node.invertColor(color),move) ;
                child.parent =this ;
                addChild(child);
               // MCTS_Search.boardNodepair.put(duplicate(board),child) ;
                //long score = -this.search(board, Search.invertColor(color), this.depth, -100000, 100000);
                if(tempValue){
                    board = Board.removePiece(board, tempKing, move.getY());
                }else{
                    board = Board.removePiece(board, current, move.getY());
                }
                
                board = Board.movePiece(board, current, move.getX());
            /*  test capture, make sure to put the piece back. */
                if(capture != null) {
                    board = Board.movePiece(board, capture, new Position(x3,y3));
                }
            
                
            
                
            }
        }
        
        public boolean arecreatedChildren(){
            if(children.size()!=0){
                return true ;
            }else{
                return false ;
            }
        }
        
        public void backpropagateScore(long[] score){
            if(score[0] != 0){
                this.tempgames++ ;
            }
            this.score[0] = this.score[0] + score[0] ;
            this.score[1] = this.score[1] + score[1] ;
            this.games++ ;
            if(this.parent != null){
                this.parent.backpropagateScore(score);
                this.weight = upperConfidenceBound(1.0) ;
            }
        }
        
        public Node select(){
            if(!arecreatedChildren()){
                createChildren(this.board, this.player) ;
            }
            double totalweight = 0.0;
            for(Node child : children){
                totalweight += child.weight ;
            }
            Random r = new Random() ;
            double randomValue = totalweight*(r.nextDouble()) ;
            int i =0; 
            double temp = 0.0 ;
            for(Node child : children){
                temp += child.weight ;
                if(temp>randomValue){
                    return children.get(i) ;
                }else{
                    i++ ;
                }
            }
            if(children.isEmpty()){
                return null ;
            }
            return children.getLast() ;
            
        }
        
        public void expand(){
            if(!arecreatedChildren()){
                createChildren(this.board, this.player) ;
            }
        }
        
        public void simulate(int depth){
            System.out.println(depth+" is the depth");
            if(Board.getBitmap(this.board, this.player) == 0){
                long[] tempscore = new long[2] ;
                tempscore[this.player] = -1 ;
                tempscore[Node.invertColor(this.player)] = 1 ;
                backpropagateScore(tempscore);
                return ;
            }else if(Board.getBitmap(this.board, Node.invertColor(this.player)) == 0){
                long[] tempscore = new long[2] ;
                tempscore[this.player] = 1 ;
                tempscore[Node.invertColor(this.player)] = -1 ;
                backpropagateScore(tempscore);
                return ;
            }
            
            if(!arecreatedChildren()){
                createChildren(this.board, this.player) ;
            }
            Node n = select() ;
            if(n==null){
                long[] tempscore = new long[2] ;
                tempscore[this.player] = 0 ;
                tempscore[Node.invertColor(this.player)] = 0 ;
                backpropagateScore(tempscore);
                return ;
            }
            if(depth>1000){
                long[] tempscore = new long[2] ;
                tempscore[this.player] = 0 ;
                tempscore[Node.invertColor(this.player)] = 0 ;
                backpropagateScore(tempscore);
                return ;
            }
            depth++;
            n.simulate(depth) ;
        }
        
        
	

	
	public double upperConfidenceBound(double c) {
		return (double)(score[0])/(2*games) +
				c*Math.sqrt(Math.log(parent.games + 1) / games);
	}
        
        public Node bestNode(int color){
            Node bestNode = null ;
            long bestValue = -100000000 ;
            int i =0;
            for(Node child : children){
                System.out.println("Node "+i+": Value1: "+child.score[color]+": Value2: "+child.score[Node.invertColor(color)]+ " TempGames: "+child.tempgames) ;
                i++ ;
                if(child.score[color] > bestValue){
                    bestValue = child.score[color] ;
                    bestNode = child ;
                }
            }
            return bestNode ;
        }

	/**
	 * Update the tree with the new score.
	 * @param scr
	 */
	public static int invertColor(int color) {
            if (color != 0) {
                return 0;
            } else {
                return 1;
            }
        }

	
        
        private long[][] duplicate(long[][] board){
            long[][] newboard = new long[2][2];
            for(int i =0; i < 2 ; i++){
                for(int j =0 ; j < 2;  j++){
                    newboard[i][j] = board[i][j] ;
                }
            }
            return newboard ;
        }
	/**
	 * Select a child node at random and return it.
	 * @param board
	 * @return
	 */
	
    
}

