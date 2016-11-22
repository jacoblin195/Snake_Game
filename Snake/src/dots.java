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
abstract class dots {
    public Color fillColor = Color.blue;
    private final Color borderColor = Color.BLACK;
    private final Point stdCoordinate;
    private final Point ctrCoordinate;
    private final int radius;
    private final int diameter;
    private final Rectangle rect;
    private final int x;
    private final int y;
    public dots(int x,int y)  {
        this.x = x;
        this.y = y;
        radius = 5;
        diameter = 10;
        this.ctrCoordinate = new Point(x,y);
        this.stdCoordinate = new Point(x - radius,y - radius);
        this.rect = new Rectangle(new Point(this.stdCoordinate.x-1,this.stdCoordinate.y-1),new Dimension(this.diameter-2,this.diameter-2));
    }
    public void draw(Graphics g){
        g.setColor(this.fillColor);
        g.fillOval((int)this.stdCoordinate.getX(),(int)this.stdCoordinate.getY(),this.diameter,this.diameter);
        g.setColor(this.borderColor);
        g.drawOval((int)this.stdCoordinate.getX(),(int)this.stdCoordinate.getY(),this.diameter,this.diameter);
    }
    public Rectangle hitbox(){
        return rect;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getD(){
        return this.diameter;
    }
}
