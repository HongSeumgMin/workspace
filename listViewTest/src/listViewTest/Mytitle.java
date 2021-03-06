package listViewTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mytitle extends JPanel{
	private JLabel titleName;
	private ClosePn close;
	
	public Mytitle(){
		super(new BorderLayout());
		
		titleName = new JLabel("   talk talk");
		titleName.setForeground(new Color(0,0,0));
		
		close = new ClosePn();
		close.setPreferredSize(new Dimension(32, 32));
		close.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				close.setTranslucent();
				repaint();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				close.setOpaque();
				repaint();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		add(titleName,BorderLayout.WEST);
		add(close,BorderLayout.EAST);
	}
}
