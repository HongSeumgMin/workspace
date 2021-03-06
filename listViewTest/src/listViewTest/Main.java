package listViewTest;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {
	private JScrollPane sc;
	private JScrollPane sc1;
	private JPanel mainPn;
	private JPanel mainPanel;
	private JPanel mainPanel1;
	private JPanel mainPanel2;
	private Mytitle titlePanel;
	private JPanel listPanel;
	private JPanel contentsPanel;
	private listPn myl1;
	private listPn myl2;

	private Point pP;
	private int width;
	private int height;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		mainPn = new JPanel();

		setUndecorated(true);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		setMinimumSize(new Dimension(450, 300));

		mainPn.setLayout(new BorderLayout());
		mainPn.setBorder(new EmptyBorder(0, 0, 3, 3));
		mainPn.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

				pP = e.getPoint();
				width = (int) getSize().getWidth();
				height = (int) getSize().getHeight();
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		
		mainPn.addMouseMotionListener(new MouseMotionListener() {
			boolean flag = false;

			@Override
			public void mouseMoved(MouseEvent e) {

				System.out.println(e.getX() + " " + getWidth());
				if (e.getX() < (int) getWidth() - 2)
					if (flag) {
						setCursor(Cursor.DEFAULT_CURSOR);
						flag = false;
					}
				if (e.getX() < getWidth() && e.getX() > getWidth() - 2) {
					setCursor(Cursor.NW_RESIZE_CURSOR);
					flag = true;
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				Point p = e.getPoint();
				setSize(width + (p.x - pP.x), height + (p.y - pP.y));
			}
		});

		mainPanel2 = new JPanel(new BorderLayout());
		mainPanel2.setPreferredSize(new Dimension(400, 120));
		mainPanel2.setMinimumSize(new Dimension(400, 120));
		mainPanel2.setMaximumSize(new Dimension(4000, 120));
		mainPanel2.setBackground(new Color(82, 55, 56));

		titlePanel = new Mytitle();
		// titlePanel.setBounds(0, 0, 400, 20);
		// titlePanel.setPreferredSize(new Dimension(400, 20));
		titlePanel.setMinimumSize(new Dimension(400, 20));
		titlePanel.setMaximumSize(new Dimension(4000, 20));
		titlePanel.setBackground(new Color(82, 55, 56));

		titlePanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				pP = e.getPoint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		titlePanel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				Point dP = e.getLocationOnScreen();
				Point p = titlePanel.getLocation();
				setLocation(p.x + dP.x - pP.x, p.y + dP.y - pP.y);
			}
		});

		listPanel = new JPanel();
		// listPanel.setBounds(0, 20, 400, 100);
		listPanel.setPreferredSize(new Dimension(400, 100));
		listPanel.setMinimumSize(new Dimension(400, 100));
		listPanel.setMaximumSize(new Dimension(400, 100));

		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.X_AXIS));
		listPanel.setBackground(new Color(82, 55, 56));

		mainPanel2.add(titlePanel, BorderLayout.NORTH);
		mainPanel2.add(listPanel, BorderLayout.SOUTH);

		myl1 = new listPn("friends");
		myl1.addMouseListener(new MouseListener() {

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
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				myl1.setOpaque();
				myl2.setTranslucent();
				repaint();
				if (sc1.isVisible())
					sc1.setVisible(false);
				if (!sc.isVisible())
					sc.setVisible(true);
			}
		});
		myl1.setBackground(new Color(82, 55, 56));

		myl2 = new listPn("chat");
		myl2.addMouseListener(new MouseListener() {

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
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				myl2.setOpaque();
				myl1.setTranslucent();
				repaint();
				if (sc.isVisible())
					sc.setVisible(false);
				if (!sc1.isVisible())
					sc1.setVisible(true);
			}
		});
		myl2.setBackground(new Color(82, 55, 56));

		myl1.setMinimumSize(new Dimension(200, 80));
		myl1.setMaximumSize(new Dimension(200, 80));
		myl2.setMinimumSize(new Dimension(200, 80));
		myl2.setMaximumSize(new Dimension(200, 80));

		listPanel.add(myl1);
		listPanel.add(myl2);

		mainPn.add(mainPanel2, BorderLayout.NORTH);

		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setOpaque(true);

		mainPanel1 = new JPanel();
		mainPanel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel1.setBackground(Color.WHITE);
		mainPanel1.setOpaque(true);

		sc1 = new JScrollPane(mainPanel1);
		sc = new JScrollPane(mainPanel);

		sc1.getVerticalScrollBar().setUnitIncrement(10); // 胶农费 10px 究 捞悼
		sc.getVerticalScrollBar().setUnitIncrement(10); // 胶农费 10px 究 捞悼

		mainPanel1.setLayout(new BoxLayout(mainPanel1, BoxLayout.Y_AXIS));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		sc1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		for (int i = 0; i < 50; i++) {
			ListFriends listItem = new ListFriends();
			// listItem.setBounds(5, 5+(i*50), 400, 50);
			listItem.setPreferredSize(new Dimension(400, 80));
			listItem.setMaximumSize(new Dimension(4000, 80));
			mainPanel1.add(listItem);
		}

		for (int i = 0; i < 500; i++) {
			ListItem listItem = new ListItem();
			// listItem.setBounds(5, 5+(i*50), 400, 50);
			listItem.setPreferredSize(new Dimension(400, 80));
			listItem.setMaximumSize(new Dimension(4000, 80));
			mainPanel.add(listItem);
		}
		contentsPanel = new JPanel();
		contentsPanel.setLayout(new CardLayout());

		contentsPanel.add(sc);
		contentsPanel.add(sc1);

		mainPn.add(contentsPanel, BorderLayout.CENTER);

		add(mainPn);
	}
}
