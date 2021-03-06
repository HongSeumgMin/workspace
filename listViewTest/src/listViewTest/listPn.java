package listViewTest;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class listPn extends JPanel {
	private BufferedImage img = null;
	private AlphaComposite alpha = null;
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setComposite(alpha);
		g2.drawImage(img, 68, 8, null);
	}

	public listPn(String name) {		
		this.setMinimumSize(new Dimension(64, 64));
		try {
			if (name.equals("chat")) {
				img = ImageIO.read(new File("image\\chat.png"));

				alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f);
			} else {
				img = ImageIO.read(new File("image\\friends.png"));

				alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.f);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setTranslucent(){
		alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f);
	}
	
	public void setOpaque(){
		alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.f);
	}
}
