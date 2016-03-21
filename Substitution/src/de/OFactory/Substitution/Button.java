package de.OFactory.Substitution;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Button implements Drawable {
	
	protected int ID;
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	protected Rectangle shape;
	
	protected Color hovered_color;
	protected Color normal_color;
	protected Color clicked_color;
	
	protected String content;
	
	boolean hovered = false;
	boolean clicked = false;
	
	
	
	public Button(int ID, int x, int y, int width, int height, String content) {
		
		this.ID = ID;
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.content = content;
		
		this.clicked_color = new Color(100, 100, 100);
		this.hovered_color = new Color(150, 150, 255);
		this.normal_color  = new Color(150, 150, 150);
		
	}
	
	public Button(int ID, int x, int y, int width, int height, String content, Color normal, Color hovered, Color clicked){
		
		this.ID = ID;
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.content = content;
		
		this.clicked_color = clicked;
		this.normal_color  = normal;
		this.hovered_color = hovered;
		
	}
	
	//-------------------------------------------------------------------------
	
	public void update(int mousex, int mousey, boolean left){

		
		if(new Rectangle(this.x, this.y, this.width, this.height).contains(mousex, mousey)) {
			this.hovered = true;
		} else {
			this.hovered = false;
		}
		
		if(this.hovered){
			if(left){
				this.clicked = true;
			}
		}
		
		
		
		
	}
	
	@Override
	public void draw(Graphics g){
		if(this.clicked) {
			g.setColor(clicked_color);
			
			if(this.hovered){ // Durschnittsfarbe
				g.setColor(new Color((clicked_color.getRed()+hovered_color.getRed())/2, (clicked_color.getGreen()+hovered_color.getGreen())/2, (clicked_color.getBlue()+hovered_color.getBlue())/2));
			}	
		} else if(this.hovered)
			g.setColor(hovered_color);
		else
			g.setColor(normal_color);
		
		
		g.fillRect(this.x, this.y, this.width, this.height);
		
		g.setColor(Color.black);
		g.drawRect(this.x, this.y, this.width, this.height);
		
		if(this.content != "") {
			Panel.drawCenteredString(g, this.content, new Rectangle(this.x, this.y + this.height/2, this.width, this.height), Panel.big);
		}
		
	}
	
	
	
	
	//-------------------------------------------------------------------------
	
	//Setters & Getters
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getID() {
		return this.ID;
	}
	
	
}
