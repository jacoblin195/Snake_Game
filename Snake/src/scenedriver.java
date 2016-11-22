/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jacoblin
 */


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class scenedriver extends JPanel implements KeyListener,ActionListener{
    public final int FRAME_WIDTH = 600;
    public final int FRAME_HEIGHT = 600;
    private Timer timer;
    private Random random = new Random();
    private int scorenum = 0;
    private boolean end = false;
    private JButton starter;
    private boolean beginn = false;
    private Snake thesnake = new Snake(5);
    private ArrayList<food> foodlist= new ArrayList<>();
    private int direction = 1;
    private boolean keyallowed;
    
    public scenedriver(){
        super();
        starter = new JButton("Start");
        starter.addActionListener(new ButtonListener());
        addKeyListener(this);
        setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));
        setFocusable(true);
        setBackground(Color.white);
        add(starter);   
        timer = new Timer(200, this);
        
        for(int i = 1;i<=5;++i){
            food nf = new food(this.random.nextInt(57)*10+20,this.random.nextInt(57)*10+20);
            for(;nf.getX() == 300;){
                nf = new food(this.random.nextInt(57)*10+20,this.random.nextInt(57)*10+20);
            }
            this.foodlist.add(nf);
        }
    }
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
      
         timer.start();
         beginn = true;
         remove(starter);
         repaint();
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(beginn){
            g.drawRect(15, 15, 570, 570);
            for(food f:foodlist){
                f.draw(g);
            }
            this.thesnake.draw(g);
            g.setColor(Color.black);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 18)); 
            g.drawString("Score:  " + scorenum,30,16);
        }
        if(end){
        JFrame frame = new JFrame("End");
        JOptionPane.showMessageDialog(frame,"Your score: " + scorenum + "!","End",JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(keyallowed){
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                this.direction = 4;
                this.thesnake.turnright();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT){
                this.direction = 3;
                this.thesnake.turnleft();
            } else if (e.getKeyCode() == KeyEvent.VK_UP){
                this.direction = 1;
                this.thesnake.turntop();
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN){
                this.direction = 2;
                this.thesnake.turndown();
            }
            this.keyallowed = false;
        }
    }
    @Override
     public void keyReleased(KeyEvent e) { }
  
    @Override
    public void keyTyped(KeyEvent e) { }
  
    @Override
    public void actionPerformed(ActionEvent e) {
            this.keyallowed=true;
            int size = this.thesnake.thesnake.size();
            boolean noOverLapSnake = true;
            for(int i = 1;i<=size;++i){
                for (int j = i;j<=size;++j){
                    if (i!=j){
                        noOverLapSnake = !(this.thesnake.thesnake.get(i-1).hitbox().contains(
                                this.thesnake.thesnake.get(j-1).hitbox())) && noOverLapSnake;
                    }   
                }       
            }
                boolean outofbounds = (this.thesnake.getHead().getX()<=14 ||
                        this.thesnake.getHead().getX()>=586||
                        this.thesnake.getHead().getY()<=14 ||
                        this.thesnake.getHead().getY()>=586);
            if(outofbounds){
                timer.stop();
                this.end = true;
                repaint();
            } else if(!noOverLapSnake){
                timer.stop();
                this.end = true;
                repaint();
            } else {
                boolean eat = false;
                for(Iterator<food> fiterator = this.foodlist.iterator();
                    fiterator.hasNext();){
                    food f = fiterator.next();
                    if(this.thesnake.getHead().hitbox().contains(f.hitbox())){
                        fiterator.remove();
                        eat = true;
                        ++this.scorenum;
                        int newdelay = (int) (this.timer.getDelay() * .96);
                        this.timer.setDelay(newdelay);
                        food nf = new food(this.random.nextInt(57)*10+20,
                                            this.random.nextInt(57)*10+20);
                        for (minisnake q:this.thesnake.thesnake){
                            if(q.hitbox().contains(nf.hitbox())){
                                nf = new food(this.random.nextInt(57)*10+20,
                                                this.random.nextInt(57)*10+20);
                            }
                        }
                        this.foodlist.add(nf);
                        break;
                    }
                }
                if(eat){
                    this.thesnake.grow();
                    this.thesnake.update(direction);
                } else {
                    this.thesnake.update(direction);
                }
                repaint();
            }
    }
}

