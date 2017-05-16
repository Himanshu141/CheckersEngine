/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkersengine;

/**
 *
 * @author himanshu
 */
import board.Board;
import board.Move;
import board.Piece;
import board.Position;
import javax.swing.* ;
import java.applet.* ;
import java.awt.* ;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author himanshu
 */
public class CheckersGUI {
    public static int rows = 8;
	public static int columns = 8;
	public static Color col1 = Color.BLACK;
	public static Color col2 = Color.WHITE;
        public static JPanel[][] panels ;
        public static ImageIcon white_piece ;
        public static ImageIcon red_king ;
        public static ImageIcon red_piece ;
        public static ImageIcon white_king ;
        public static ImageIcon red_piece_temp ;
        public static ImageIcon red_king_temp ;
        public static ImageIcon white_piece_temp ;
        public static ImageIcon white_king_temp ;
        public static JFrame checkerBoard ;
        
        public CheckersGUI(){
                checkerBoard = new JFrame();
		checkerBoard.setSize(450, 480);
                panels = new JPanel[rows][columns] ;
                white_piece =new ImageIcon("white_circle.png") ;
                red_king =new ImageIcon("black_ring.png") ;
                red_piece =new ImageIcon("red_circle.png") ;
                white_king =new ImageIcon("white_ring.png") ;
                
		checkerBoard.setTitle("CheckerBoard");
                
                Image imgtemp = red_piece.getImage() ;
                Image newimgtemp = imgtemp.getScaledInstance(60, 50, java.awt.Image.SCALE_SMOOTH) ;
                red_piece_temp = new ImageIcon(newimgtemp) ;
                            
                imgtemp = white_piece.getImage() ;
                newimgtemp = imgtemp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH) ;
                white_piece_temp = new ImageIcon(newimgtemp) ;
               
                imgtemp = white_king.getImage() ;
                newimgtemp = imgtemp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH) ;
                white_king_temp = new ImageIcon(newimgtemp) ;
                
                imgtemp = red_king.getImage() ;
                newimgtemp = imgtemp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH) ;
                red_king_temp = new ImageIcon(newimgtemp) ;
                
		Container pane = checkerBoard.getContentPane();
		pane.setLayout(new GridLayout(rows, columns));
                
  
		Color temp;
                
		for (int i = 0; i < rows; i++)
		{
			if (i%2 == 0)
			{
				temp = col1;
			}
			else
			{
				temp = col2;
			}
			for (int j = 0; j < columns; j++)
			{
				panels[i][j] = new JPanel();
				panels[i][j].setBackground(temp);
				if (temp.equals(col1))
				{
					temp = col2;
				}
				else
				{
					temp = col1;
				}
				
			}
		}
                
                
                
                
                for(int i =0 ; i < 3 ; i++){
                    if(i%2==0){
                        for(int j =0; j < 8 ; j+=2){
                            
                            
                            JLabel templabelwp = new JLabel(red_piece_temp, JLabel.CENTER);
                            panels[i][j].add(templabelwp,JPanel.CENTER_ALIGNMENT) ;
                            
                        }
                    }else{
                        for(int j =1; j < 8 ; j+=2){
                         
                            
                            JLabel templabelwp = new JLabel(red_piece_temp, JLabel.CENTER);
                            panels[i][j].add(templabelwp,JPanel.CENTER_ALIGNMENT) ;
                            
                        }
                    }
                    
                }
                
                for(int i = 5 ; i < 8 ; i++){
                    if(i%2==0){
                        for(int j =0; j < 8 ; j+=2){
                            
                            
                            JLabel templabelbp = new JLabel(white_piece_temp, JLabel.CENTER);
                            panels[i][j].add(templabelbp,JPanel.CENTER_ALIGNMENT) ;
                            
                        }
                    }else{
                        for(int j =1; j < 8 ; j+=2){
                            
                            
                            JLabel templabelbp = new JLabel(white_piece_temp, JLabel.CENTER);
                            panels[i][j].add(templabelbp,JPanel.CENTER_ALIGNMENT) ;
                        }
                    }
                }
                
                for(int i =0; i < 8 ; i++){
                    for(int j =0; j < 8 ; j++){
                        pane.add(panels[i][j]);
                    }
                }
                panels[0][0].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //System.out.println("Mouse Clicked");
                        Position X = new Position(1,1) ;
                        Position Y = new Position(4,4) ;
                        Move move = new Move(X,Y) ;
                        //movePiece(move) ;
                    }
                }) ;
                checkerBoard.setVisible(true);
		
                
                System.out.println(panels[0][0].getSize());
        }
	
        
        public void movePiece(Move move, long[][] board){
            Position initial = move.getX() ;
            Position end = move.getY() ;
            int x1 = initial.getX() ;
            int y1 = initial.getY() ;
            int x2 = end.getX() ;
            int y2 = end.getY() ;
            Piece p = Board.getPiece(board,move.getX()) ;
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
            
            checkerBoard.setVisible(false);
            //panels[x1][y1].removeAll();
            
            panels[x2][y2].removeAll();
           
            if(p.getColor()==0 && p.getType()==0){
                JLabel templabelwp = new JLabel(white_piece_temp, JLabel.CENTER);
                panels[x2][y2].add(templabelwp) ;
            }else if(p.getColor()==0 && p.getType() == 1){
                JLabel templabelwp = new JLabel(white_king_temp, JLabel.CENTER);
                panels[x2][y2].add(templabelwp) ;
            }else if(p.getColor()==1 && p.getType() == 0){
                JLabel templabelwp = new JLabel(red_piece_temp, JLabel.CENTER);
                panels[x2][y2].add(templabelwp) ;
            }else{
                JLabel templabelwp = new JLabel(red_king_temp, JLabel.CENTER);
                panels[x2][y2].add(templabelwp) ;
            }
            
            
            //panels[2][2].removeAll();
            panels[x1][y1].removeAll();
            if(Math.abs(xd)==2 && Math.abs(yd)==2 ){
                 panels[x3][y3].removeAll();
            }
            checkerBoard.setVisible(true);
            
            
        }
        
}
