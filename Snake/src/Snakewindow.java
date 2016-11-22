/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jacoblin
 */
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snakewindow extends JFrame{

    JPanel scenedriver = new scenedriver();
    public Snakewindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(scenedriver);
        this.pack();
        this.setVisible(true);
    }
    public static void main(String[] args) {
        JFrame Snakewindow = new Snakewindow();
    // TODO code application logic here
    }
    
}
