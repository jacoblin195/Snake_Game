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
import java.awt.Color;
import java.awt.Graphics;

public class Snake {
    public minisnake invisiblehead;
    public ArrayList<minisnake> thesnake;
    public Snake(int num){
        this.thesnake = new ArrayList<>();
        minisnake h = new minisnake(300,300);
        h.redfill();
        thesnake.add(h);
        this.invisiblehead = h.createup();
        for (int i = 1;i<=num;++i){
            thesnake.add(new minisnake(300,300+i*10));
        }
        
    }
    public minisnake getHead(){
        return this.thesnake.get(0);
    }
    public void turntop(){
        this.invisiblehead=new minisnake(this.getHead().getX(),this.getHead().getY()-10);
    }
    public void turndown(){
        this.invisiblehead=new minisnake(this.getHead().getX(),this.getHead().getY()+10);
    }
    public void turnleft(){
        this.invisiblehead=new minisnake(this.getHead().getX()-10,this.getHead().getY());
    }
    public void turnright(){
        this.invisiblehead=new minisnake(this.getHead().getX()+10,this.getHead().getY());
    }
    public void update(int direction){
        if(direction == 1||direction == 2 || direction == 3 || direction == 4){
            
            int last = thesnake.size();
            
            this.thesnake.remove(last-1);
            minisnake newhead = this.invisiblehead;
            newhead.redfill();
            this.thesnake.add(0, newhead);
            minisnake second = this.thesnake.get(1);
            second.bluefill();
            this.thesnake.set(1, second);
            
            if(direction == 1){
                this.invisiblehead = thesnake.get(0).createup();
            } else if (direction == 2){
                this.invisiblehead = thesnake.get(0).createdown();
            } else if (direction == 3){
                this.invisiblehead = thesnake.get(0).createleft();
            } else if (direction == 4){
                this.invisiblehead = thesnake.get(0).createright();
            }
        } else {
        }
    }
    public void grow(){
        int last = this.thesnake.size()-1;
        minisnake lastone = this.thesnake.get(last);
        int lastx = lastone.getX();
        int lasty = lastone.getY();
        int slastx = this.thesnake.get(last-1).getX();
        int slasty = this.thesnake.get(last-1).getY();
        if(slasty == lasty && slastx == lastx+10){
            this.thesnake.add(lastone.createleft());
        } else if (slasty == lasty && slastx == lastx-10){
            this.thesnake.add(lastone.createright());
        } else if (lastx == slastx && slasty == lasty-10){
            this.thesnake.add(lastone.createdown());
        } else if (lastx == slastx && slasty == lasty+10){
            this.thesnake.add(lastone.createup());
        }
    }
    public void draw(Graphics g){
        for(minisnake s:this.thesnake){
            s.draw(g);
        }
        this.getHead().draw(g);
    }
}
