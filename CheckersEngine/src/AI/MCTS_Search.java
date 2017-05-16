/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import board.Move;
import java.util.Random;

/**
 *
 * @author himanshu
 */
public class MCTS_Search {
        private Random random;
        private Node rootNode;
        private double explorationConstant = Math.sqrt(2.0);
	private int runs ;
	public MCTS_Search() {
            this.nodes = 0;
            random = new Random();
            this.runs = 10 ;
	}
    
        private long nodes;
    

    
        public MCTS_Search(int runs) {
            this.nodes = 0;
            random = new Random();
         //   boardNodepair = new HashMap<long[][],Node>() ;
            this.runs = runs ;
        }
    
        public Move MCTS_searchForMove(long[][] board , int color){
            rootNode = new Node(board,color) ;
            rootNode.createChildren(board, color);
            for(int i =0 ; i < runs ; i++){
                System.out.println("Run: "+i);
                Node n = rootNode.select() ;
                if(n==null){
                    continue ;
                }
                n.simulate(0);
            }
            Node bestnode = rootNode.bestNode(color) ;
            System.out.println(bestnode.score[color]+" ");
            return bestnode.move ;
            
        }
        
        
       
    
        private static int invertColor(int color) {
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
        
        
	
    
}

