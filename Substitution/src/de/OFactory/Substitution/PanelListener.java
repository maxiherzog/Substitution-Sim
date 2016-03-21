package de.OFactory.Substitution;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.event.MouseInputListener;

public class PanelListener implements MouseMotionListener, MouseListener{

	@Override
	public void mouseDragged(MouseEvent e) {
		Panel.mausx = e.getX();
		Panel.mausy = e.getY();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Panel.mausx = e.getX();
		Panel.mausy = e.getY();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		Panel.leftmaus = true;
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Panel.leftmaus = true;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Panel.leftmaus = false;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



}
