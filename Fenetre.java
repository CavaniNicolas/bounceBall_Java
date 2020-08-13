
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fenetre extends JFrame {

	private Panneau pan = new Panneau();
	private Bouton bouton1 = new Bouton("Go !");
	private Bouton bouton2 = new Bouton("Stop");
	private JPanel container = new JPanel();
	private JLabel label = new JLabel("Le JLabel");
	
	private int compteur = 0;
	
	// Attributs lies a la boule
	// Thread pour executer la methode go()
	private Thread t;
	// Coordonnees
	private int x;
	private int y;
	// Pour savoir si on avance ou on recule
	private boolean backX = false;
	private boolean backY = false;
	// Pour savoir si on Stop ou on Go l'animation
	private boolean animated = true;


	public Fenetre() {
		this.setTitle("BounceBall Ver 2");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setForeground(Color.blue);
		label.setHorizontalAlignment(JLabel.CENTER);


		JPanel southPan = new JPanel();
		southPan.add(bouton1);
		southPan.add(bouton2);

		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		container.add(pan, BorderLayout.CENTER);
		container.add(southPan, BorderLayout.SOUTH);
		container.add(label, BorderLayout.NORTH);

		bouton1.setPreferredSize(new Dimension(70, 40));
		bouton1.setMyEnabled(false);
		bouton1.addActionListener(new BoutonGoListener());
		bouton2.addActionListener(new BoutonStopListener());

		this.setContentPane(container);
		// this.setResizable(false);
		this.setVisible(true);
		go();
	}

	private void go() {
		// Les coords et taille de depart du rond
		x = pan.getPosX();
		y = pan.getPosY();
		int cote = pan.getCote();

		while (this.animated) {

			// Pour savoir si on avance ou on recule selon les x
			if (x < 1) {
				backX = false;
			}
			if (x > pan.getWidth()-cote) {
				backX = true;
			}
			// Pour savoir si on avance ou on recule selon les y
			if (y < 1) {
				backY = false;
			}
			if (y > pan.getHeight()-cote) {
				backY = true;
			}

			// On deplace le rond
			if (!backX) {
				pan.setPosX(++x);
			} else {
				pan.setPosX(--x);
			}

			if (!backY) {
				pan.setPosY(++y);
			} else {
				pan.setPosY(--y);
			}

			// On redessine notre panneau
			pan.repaint();

			// On attend 3 millisecondes
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public class BoutonGoListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			compteur++;
			animated = true;
			label.setText(compteur + " clics (1)");

			t = new Thread(new PlayAnimation());
			t.start();

			bouton1.setMyEnabled(false);
			bouton2.setMyEnabled(true);
		}
	}

	public class BoutonStopListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			compteur++;
			animated = false;
			label.setText(compteur + " clics (2)");
			bouton2.setMyEnabled(false);
			bouton1.setMyEnabled(true);
		}
	}

	public class PlayAnimation implements Runnable {
		public void run() {
			go();
		}
	}
}