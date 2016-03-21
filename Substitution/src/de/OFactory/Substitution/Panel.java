package de.OFactory.Substitution;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.OFactory.Substitution.Objects.Element;

public class Panel extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static int SCREEN_WIDTH = 1200;
	private static int SCREEN_HEIGHT = 700;

	private JFrame frame;
	
	//Schriftarten
	public static Font ultra, big, small, head, norm; 
	
	//Mausposition
	public static int mausx, mausy;
	public static boolean leftmaus; 
	
	PanelListener pl = new PanelListener();
	
	
	
	private long delta, last, fps;
	
	
	public static Button start;
	
	public static void main(String[] args){
		new Panel(SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	
	
	public Panel(int w, int h){
		this.setPreferredSize(new Dimension(w, h));
		this.setBackground(new Color(230, 230, 230));
		
		// Fenster wird erstellt
		frame = new JFrame("Substitution - Eine typische Reaktion der Alkane | OFactory© ");
		//frame.setResizable(false);
		frame.setLocation(100,100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				SCREEN_WIDTH = frame.getWidth();
				SCREEN_HEIGHT = frame.getHeight();
				resizeFonts(SCREEN_WIDTH, SCREEN_HEIGHT);
				
				System.out.println("[Dimensions] " + frame.getWidth() + " | " + frame.getHeight());
				
			}
			@Override
			public void componentMoved(ComponentEvent e) {}

			@Override
			public void componentShown(ComponentEvent e) {}
			
			@Override
			public void componentHidden(ComponentEvent e) {}
		});
		
		frame.addMouseMotionListener(pl);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);

		
		doInitializations();
		
		Thread th = new Thread(this);
		th.start();
	}

	private void doInitializations() {
		
		System.out.println(Element.WASSERSTOFF.get(1));
		
		
		//Sachen für "einmalige Gelegenheiten"
		last = System.nanoTime();
		
		start = new Button(0, (int) (SCREEN_WIDTH*0.7 - SCREEN_WIDTH/7), (int) (SCREEN_HEIGHT*0.8 - SCREEN_HEIGHT/14), SCREEN_WIDTH/3, SCREEN_HEIGHT/7, "Start");
		
	}

	private void computeDelta() {
		//Zeit für jeweils vorherigen Schleifendurchlauf errechnen
		delta = System.nanoTime() - last;
		last = System.nanoTime();
		fps = ((long) 1e9)/delta;
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.setFont(Panel.norm);
		
		g.setColor(Color.RED);
		g.drawString("FPS: " + this.fps, 10, 15);
		g.drawString("NanoTime: " + this.last, 10, 30);
		g.drawString("delta: " + this.delta, 10, 45);
		g.drawString("Maus: " + Panel.mausx + " | " + Panel.mausy, 10, 60);
		g.drawString("MausPressed: " + Panel.leftmaus, 10, 75);
		
		g.setColor(Color.black);
		drawCenteredString(g, "Substitution", new Rectangle(0, SCREEN_HEIGHT/8, SCREEN_WIDTH, SCREEN_HEIGHT), Panel.head);
		g.setColor(new Color(100, 100, 100));
		drawCenteredString(g, "Eine typische Reaktion der Alkane", new Rectangle(0, SCREEN_HEIGHT/2, SCREEN_WIDTH, SCREEN_HEIGHT/4), Panel.ultra);
		drawCenteredString(g, "OFactory", new Rectangle((int) (SCREEN_WIDTH*0.75), 0, SCREEN_WIDTH/4, SCREEN_HEIGHT/6), Panel.big);
		
		start.draw(g);
		
		
		g.dispose();
	}


	@Override
	public void run(){
		
		while(frame.isVisible()){
			
			doLogic();
			
			computeDelta();
			repaint();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}	

			
		}
		
	}
	
	
	private void doLogic() {
		
		
		start.update(Panel.mausx, Panel.mausy, Panel.leftmaus);
		start.setX(   (int) (SCREEN_WIDTH*0.7 - SCREEN_WIDTH/7)    );
		start.setY(   (int) (SCREEN_HEIGHT*0.8 - SCREEN_HEIGHT/14) );
		start.setWidth(      SCREEN_WIDTH/3                        );
		start.setHeight(     SCREEN_HEIGHT/7                       );
		
	}



	public void resizeFonts(int width, int height){
		
		int delta = ((width+height)/2)/80;
		
		Panel.ultra = new Font("Arial", Font.BOLD, 20 + delta);
		Panel.big   = new Font("Arial", Font.BOLD, 10 + delta);
		Panel.small = new Font("Arial", Font.BOLD,      delta);
		Panel.norm  = new Font("Arial", Font.BOLD, 3 + delta);
		Panel.head  = new Font("Arial", Font.BOLD, 80 + delta);
	}
	
	/**
	 * Draw a String centered in the middle of a Rectangle.
	 *
	 * @param g Die Graphik-Instanz
	 * @param text Den zu zeichnenden String
	 * @param rect Das Rechteck in dem der String gezeichnet werden soll
	 */
	public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
	    // FontMetrics gönnen #GönnungMussSein
	    FontMetrics metrics = g.getFontMetrics(font);

	    int x = (rect.width - metrics.stringWidth(text)) / 2;
	    int y = ((rect.height - metrics.getHeight()) / 2) - metrics.getAscent();

	    
	    g.setFont(font);
	    g.drawString(text, rect.x + x,  rect.y + y);
	}
	
}
