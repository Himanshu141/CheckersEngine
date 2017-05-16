import javax.swing.* ;
import java.applet.* ;
import java.awt.* ;
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
        public static ImageIcon black_piece ;
        public static ImageIcon black_king ;
        public static ImageIcon white_piece ;
        public static ImageIcon white_king ;
        
        public CheckersGUI(){
            JFrame checkerBoard = new JFrame();
		checkerBoard.setSize(450, 480);
                panels = new JPanel[rows][columns] ;
                black_piece =new ImageIcon("black_circle.png") ;
                black_king =new ImageIcon("black_ring.png") ;
                white_piece =new ImageIcon("white_circle.png") ;
                white_king =new ImageIcon("white_ring.png") ;
                
		checkerBoard.setTitle("CheckerBoard");
               
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
                            
                            Image imgtemp = white_piece.getImage() ;
                            Image newimgtemp = imgtemp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH) ;
                            ImageIcon white_piece_temp = new ImageIcon(newimgtemp) ;
                            
                            JLabel templabelwp = new JLabel(white_piece_temp, JLabel.CENTER);
                            panels[i][j].add(templabelwp,JPanel.CENTER_ALIGNMENT) ;
                            
                        }
                    }else{
                        for(int j =1; j < 8 ; j+=2){
                            Image imgtemp = white_piece.getImage() ;
                            Image newimgtemp = imgtemp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH) ;
                            ImageIcon white_piece_temp = new ImageIcon(newimgtemp) ;
                            
                            JLabel templabelwp = new JLabel(white_piece_temp, JLabel.CENTER);
                            panels[i][j].add(templabelwp,JPanel.CENTER_ALIGNMENT) ;
                            
                        }
                    }
                    
                }
                
                for(int i = 5 ; i < 8 ; i++){
                    if(i%2==1){
                        for(int j =0; j < 8 ; j+=2){
                            Image imgtemp = black_piece.getImage() ;
                            Image newimgtemp = imgtemp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH) ;
                            ImageIcon black_piece_temp = new ImageIcon(newimgtemp) ;
                            
                            JLabel templabelbp = new JLabel(black_piece_temp, JLabel.CENTER);
                            panels[i][j].add(templabelbp,JPanel.CENTER_ALIGNMENT) ;
                            
                        }
                    }else{
                        for(int j =1; j < 8 ; j+=2){
                            Image imgtemp = black_piece.getImage() ;
                            Image newimgtemp = imgtemp.getScaledInstance(50,50, java.awt.Image.SCALE_SMOOTH) ;
                            ImageIcon black_piece_temp = new ImageIcon(newimgtemp) ;
                            
                            JLabel templabelbp = new JLabel(black_piece_temp, JLabel.CENTER);
                            panels[i][j].add(templabelbp,JPanel.CENTER_ALIGNMENT) ;
                        }
                    }
                }
                
                for(int i =0; i < 8 ; i++){
                    for(int j =0; j < 8 ; j++){
                        pane.add(panels[i][j]);
                    }
                }
                checkerBoard.setVisible(true);
		
                System.out.println(panels[0][0].getSize());
        }
	public static void main(String[] args) 
	{
		JFrame checkerBoard = new JFrame();
		checkerBoard.setSize(450, 480);
                panels = new JPanel[rows][columns] ;
                black_piece =new ImageIcon("black_circle.png") ;
                black_king =new ImageIcon("black_ring.png") ;
                white_piece =new ImageIcon("white_circle.png") ;
                white_king =new ImageIcon("white_ring.png") ;
                
		checkerBoard.setTitle("CheckerBoard");
               
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
                            
                            Image imgtemp = white_piece.getImage() ;
                            Image newimgtemp = imgtemp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH) ;
                            ImageIcon white_piece_temp = new ImageIcon(newimgtemp) ;
                            
                            JLabel templabelwp = new JLabel(white_piece_temp, JLabel.CENTER);
                            panels[i][j].add(templabelwp,JPanel.CENTER_ALIGNMENT) ;
                            
                        }
                    }else{
                        for(int j =1; j < 8 ; j+=2){
                            Image imgtemp = white_piece.getImage() ;
                            Image newimgtemp = imgtemp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH) ;
                            ImageIcon white_piece_temp = new ImageIcon(newimgtemp) ;
                            
                            JLabel templabelwp = new JLabel(white_piece_temp, JLabel.CENTER);
                            panels[i][j].add(templabelwp,JPanel.CENTER_ALIGNMENT) ;
                            
                        }
                    }
                    
                }
                
                for(int i = 5 ; i < 8 ; i++){
                    if(i%2==1){
                        for(int j =0; j < 8 ; j+=2){
                            Image imgtemp = black_piece.getImage() ;
                            Image newimgtemp = imgtemp.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH) ;
                            ImageIcon black_piece_temp = new ImageIcon(newimgtemp) ;
                            
                            JLabel templabelbp = new JLabel(black_piece_temp, JLabel.CENTER);
                            panels[i][j].add(templabelbp,JPanel.CENTER_ALIGNMENT) ;
                            
                        }
                    }else{
                        for(int j =1; j < 8 ; j+=2){
                            Image imgtemp = black_piece.getImage() ;
                            Image newimgtemp = imgtemp.getScaledInstance(50,50, java.awt.Image.SCALE_SMOOTH) ;
                            ImageIcon black_piece_temp = new ImageIcon(newimgtemp) ;
                            
                            JLabel templabelbp = new JLabel(black_piece_temp, JLabel.CENTER);
                            panels[i][j].add(templabelbp,JPanel.CENTER_ALIGNMENT) ;
                        }
                    }
                }
                
                for(int i =0; i < 8 ; i++){
                    for(int j =0; j < 8 ; j++){
                        pane.add(panels[i][j]);
                    }
                }
                checkerBoard.setVisible(true);
		
                System.out.println(panels[0][0].getSize());
	}
}
