/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jacoblin
 */

import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Dimension;
public class minisnake extends dots{
    public minisnake(int x,int y)  {
        super(x,y);
        this.fillColor = Color.blue;
    }
    public minisnake createup(){
        return new minisnake(this.getX(),this.getY()-this.getD());
    }
    public minisnake createdown(){
        return new minisnake(this.getX(),this.getY()+this.getD());
    }
    public minisnake createleft(){
        return new minisnake(this.getX()-this.getD(),this.getY());
    }
    public minisnake createright(){
        return new minisnake(this.getX()+this.getD(),this.getY());
    }
    public void bluefill(){
        this.fillColor = Color.BLUE;
    }
    public void redfill(){
        this.fillColor = Color.RED;
    }
}
