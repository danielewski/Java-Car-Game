import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JApplet;
import javax.swing.KeyStroke;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileInputStream;

public class WindowMain extends JApplet implements Runnable,
		MouseMotionListener, MouseListener {
	private Image image = null;
	private int x = 475;
	private int y = 850;
	private File theImage;
	private Thread t = null;
	private int h = 0;
	private int factor = 1;
	boolean pos = true;
	private int screen = 1;
	private String name;
	private String color;
	private boolean initialized = false;
	private boolean backgroundSet = false;
	private int choice;
	private int carX = 500;
	private int carY = 900;
	private int mouseX, mouseY; // the coordinates of the upper-left corner of
	int mousePos = 450; // the box
	private int mx;
	private int ObjectY = -400;
	private int ObjectX;// the most recently recorded mouse coordinates
	private boolean makeNew = true;
	private boolean newSet = true;
	boolean initgame = true;
	private int objectNum;
	private int lineY = 0;
	private int speed = 0;
	private int speed2 = 0;
	private int dodged;
	private Car selected;
	private int width;
	private int length;
	private String choiceName;
	boolean AI = false;

	public void start() {

		if (t == null) {
			t = new Thread(this);
			t.start();
		}

	}

	public void run() {
		while (true) {
			synchronized (this) {
				++factor;
				if (factor == 11) {
					factor = 1;
				}
			}
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}

	}

	public void init() {

		addMouseListener((MouseListener) this);
		addMouseMotionListener(this);

		// image = getImage(getDocumentBase(),
		// "LittleIttyBittyBabyKittyAww.jpg");
	}

	public void paint(Graphics g) {
		if (initialized == false) {
			try {
				screenSet(g);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			if (screen == 1) {
				screenOne(g);
			}
			if (screen == 2) {
				screenTwo(g);
			}

			if (screen == 3) {
				try {
					screenThree(g);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (screen == 4) {
				try {
					screenFour(g);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (screen == 5) {
				try {
					raceScreen(g);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (screen == 6) {
				loseScreen(g);
			}
		}
	}

	public void screenSet(Graphics g) throws IOException {
		if (backgroundSet == false) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getHeight(), getWidth());
			backgroundSet = true;
		}
		if (screen == 1) {
			screenOne(g);

		}
		if (screen == 2) {
			screenTwo(g);
		}

		if (screen == 3) {
			screenThree(g);
		}

		if (screen == 4) {
			screenFour(g);
		}

		if (screen == 5) {
			raceScreen(g);
		}
		if (screen == 6) {
			loseScreen(g);
		}
	}

	public void screenOne(Graphics g) {

		g.setFont(new Font("TimesRoman", Font.BOLD, 40));
		g.setColor(Color.RED);
		g.drawString("Hectic Highway", 368, 200);

		// play
		g.setColor(Color.WHITE);
		g.fillRect(400, 300, 200, 100);
		g.setColor(Color.RED);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		g.drawString("Play", 475, 360);

		// instructions
		g.setColor(Color.WHITE);
		g.fillRect(400, 500, 200, 100);
		g.setColor(Color.RED);
		g.drawString("Instructions", 440, 560);

		// quit
		g.setColor(Color.WHITE);
		g.fillRect(400, 700, 200, 100);
		g.setColor(Color.RED);
		g.drawString("Quit", 475, 760);

	}

	public void screenTwo(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.BOLD, 40));
		g.drawString("Select your vehicle class", 300, 200);

		// Car
		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		g.setColor(Color.WHITE);
		g.fillRect(200, 400, 200, 100);
		g.setColor(Color.BLUE);
		g.drawString("Car", 275, 460);

		// Motorcycle
		g.setColor(Color.WHITE);
		g.fillRect(600, 400, 200, 100);
		g.setColor(Color.BLUE);
		g.drawString("Motorcycle", 645, 460);

		// backButton
		g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		g.setColor(Color.WHITE);
		g.fillRect(450, 600, 100, 50);
		g.setColor(Color.RED);
		g.drawString("BACK", 480, 630);
	}

	public void screenThree(Graphics g) throws IOException {
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.BOLD, 40));
		g.drawString("Select your car", 380, 200);

		// STI

		File STI = new File("SubaruWRX.jpg");
		Image image = ImageIO.read(STI);
		g.drawImage(image, 125, 350, 200, 150, this);
		g.setColor(Color.GREEN);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("Subaru WRX STI", 125, 525);
		g.drawString("Speed: 155.0 mph", 125, 550);

		// M3
		File M3 = new File("BMWM3.jpg");
		image = ImageIO.read(M3);
		g.drawImage(image, 400, 350, 200, 150, this);
		g.setColor(Color.GREEN);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("BMW M3", 400, 525);
		g.drawString("Speed: 155.0 mph", 400, 550);

		// 918
		File Porsche = new File("Porsche918.jpg");
		image = ImageIO.read(Porsche);
		g.drawImage(image, 675, 350, 200, 150, this);
		g.setColor(Color.GREEN);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("Porsche 918", 675, 525);
		g.drawString("Speed: 211.0 mph", 675, 555);

		g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		g.setColor(Color.WHITE);
		g.fillRect(450, 600, 100, 50);
		g.setColor(Color.RED);
		g.drawString("Watch AI", 470, 630);

		// backButton
		g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		g.setColor(Color.WHITE);
		g.fillRect(450, 700, 100, 50);
		g.setColor(Color.RED);
		g.drawString("BACK", 480, 730);

	}

	public void screenFour(Graphics g) throws IOException {
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.BOLD, 40));
		g.drawString("Select your Motorcycle", 310, 200);

		// Honda

		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		File honda = new File("honda.jpg");
		Image image = ImageIO.read(honda);
		g.drawImage(image, 250, 350, 200, 150, this);
		g.setColor(Color.GREEN);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("Honda CBR600RR", 250, 525);
		g.drawString("Speed: 148.0 mph", 250, 550);

		// Hayabusa
		File Hayabusa = new File("Hayabusa.jpg");
		image = ImageIO.read(Hayabusa);
		g.drawImage(image, 550, 350, 200, 150, this);
		g.setColor(Color.GREEN);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("Suzuki Hayabusa", 550, 525);
		g.drawString("Speed: 194.0 mph", 550, 550);

		// backButton
		g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		g.setColor(Color.WHITE);
		g.fillRect(450, 600, 100, 50);
		g.setColor(Color.RED);
		g.drawString("BACK", 480, 630);
	}

	public void raceScreen(Graphics g) throws IOException {
		backgroundSet = false;
		if (initgame) {

			if (choice == 1) {
				selected = new Car("Subaru WRX STI", 155, 50, 100);
				theImage = new File("SubaruTop.jpg");

			}
			if (choice == 2) {
				selected = new Car("BMW M3", 180, 50, 100);
				theImage = new File("m3Top.jpg");

			}
			if (choice == 3) {
				selected = new Car("Porsche 918", 211, 50, 100);
				theImage = new File("PorscheTop.jpg");

			}
			if (choice == 4) {
				selected = new Car("Honda CBR600RR", 148, 25, 60);
				theImage = new File("HondaTop.jpg");

			}
			if (choice == 5) {
				selected = new Car("Suzuki Hayabusa", 194, 25, 60);
				theImage = new File("SuzukiTop.jpg");

			}

			name = selected.getName();
			speed = (int) (selected.getTopSpeed() / 3);
			speed2 = speed;
			width = selected.getWidth();
			length = selected.getLength();
			initgame = false;
		}

		if (AI) {
			int lineX = 100;
			int lineYSet = lineY - 55;
			while (lineX < 920) {
				g.setColor(Color.YELLOW);
				g.fillRect(lineX, lineY, 5, 30);
				lineY = lineY + 80;
				if (lineY > 1000) {
					lineY = lineYSet;
					lineX = lineX + 120;
				}
			}
			g.setColor(Color.WHITE);
			g.fillRect(100, 0, 5, 1000);
			g.fillRect(920, 0, 5, 1000);

			g.setColor(Color.BLUE);
			if (newSet) {
				ObjectX = (int) (Math.random() * 770);
				newSet = false;
			}
			g.fillRect(ObjectX, ObjectY, 400, 200);
			ObjectY = ObjectY + speed;

			g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
			g.setColor(Color.BLACK);
			g.fillRect(25, 5, 100, 55);
			g.setColor(Color.GREEN);
			g.drawString(selected.getName(), 25, 35);
			g.drawString(Integer.toString(objectNum), 25, 65);

			if (carX + (selected.getWidth()) >= ObjectX
					&& carX <= ObjectX + 400) {
				int nextRand = (int) Math.random() * 20;

				if (carX + 200 < 900 && carX - 200 > 100) {
					carX = carX + (int) (Math.random() * 500);
					carX = carX - 250;
				} else {
					if (carX >= 500) {
						carX = carX - 200;
					} else {
						carX = carX + 200;

					}
				}
			}

			if (dodged >= 5) {
				speed = (int) (speed * 1.3);
				dodged = 0;
			}

			if (ObjectY > 1000) {
				objectNum++;
				dodged++;
				ObjectY = -200;
				newSet = true;
			}
			Image image = ImageIO.read(theImage);
			g.drawImage(image, carX, carY - 25, width, length, this);
			g.setColor(Color.RED);

			if (carX + (selected.getWidth()) > ObjectX && carX < ObjectX + 400
					&& ObjectY + 200 > carY && ObjectY < carY + 200) {
				ObjectX = (int) (Math.random() * 770);
				ObjectY = -200;
				speed = speed2;
				screen = 6;
				AI = false;
				carX = 500;

			}
		} else {
			int lineX = 100;
			int lineYSet = lineY - 55;
			while (lineX < 920) {
				g.setColor(Color.YELLOW);
				g.fillRect(lineX, lineY, 5, 30);
				lineY = lineY + 80;
				if (lineY > 1000) {
					lineY = lineYSet;
					lineX = lineX + 120;
				}
			}
			g.setColor(Color.WHITE);
			g.fillRect(100, 0, 5, 1000);
			g.fillRect(920, 0, 5, 1000);

			g.setColor(Color.BLUE);
			if (newSet) {
				ObjectX = (int) (Math.random() * 770);
				newSet = false;
			}
			g.fillRect(ObjectX, ObjectY, 400, 200);
			ObjectY = ObjectY + speed;

			g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
			g.setColor(Color.BLACK);
			g.fillRect(25, 5, 100, 55);
			g.setColor(Color.GREEN);
			g.drawString(selected.getName(), 25, 35);
			g.drawString(Integer.toString(objectNum), 25, 65);

			if (dodged >= 5) {
				speed = (int) (speed * 1.3);
				dodged = 0;
			}

			if (ObjectY > 1000) {
				objectNum++;
				dodged++;
				ObjectY = -200;
				newSet = true;
			}
			Image image = ImageIO.read(theImage);
			g.drawImage(image, mousePos - (width / 2), carY - 25, width,
					length, this);
			g.setColor(Color.RED);

			if (mousePos + (selected.getWidth() / 2) > ObjectX
					&& mousePos - (selected.getWidth() / 2) < ObjectX + 400
					&& ObjectY + 200 > carY && ObjectY < carY + 200) {
				ObjectX = (int) (Math.random() * 770);
				ObjectY = -200;
				speed = speed2;
				screen = 6;
			}
		}
	}

	public void loseScreen(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.BOLD, 70));
		g.setColor(Color.RED);
		g.drawString("GAME OVER", 300, 330);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		g.setColor(Color.WHITE);
		g.drawString("Objects Dodged: " + objectNum, 420, 430);
		g.fillRect(450, 600, 140, 50);
		g.setColor(Color.RED);
		g.drawString("Main Menu", 460, 630);
	}

	public void quit() {
		System.exit(0);
	}

	public static void read() throws IOException {
		File tmpFile = new File("Instructions.txt");

		FileOutputStream fos = new FileOutputStream(tmpFile);
		PrintWriter pw = new PrintWriter(tmpFile);
		pw.println("To play the game:");
		pw.println("1) Click \"Play\"");
		pw.println("2) Select a vehicle class");
		pw.println("3) Select your car or motorcycle");
		pw.println("4) Moving your mouse left and right, try to dodge the");
		pw.println("objects that are coming towards your vehicle! For every");
		pw.println("object you dodge, you are awarded 1 point! Points are ");
		pw.println("displayed in the top left of the screen under your vehicle name");
		pw.println();
		pw.println("To watch the computer play:");
		pw.println("1) Click \"Play\"");
		pw.println("2) Select the \"Car\" option");
		pw.println("3) Select the \"Watch AI\" button");

		pw.close();
		if (Desktop.isDesktopSupported()) {
			Desktop.getDesktop().open(tmpFile);

		}

	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {
		mousePos = e.getX();

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int mx = e.getX();
		int my = e.getY();

		// e.consume();
		if (screen == 1) {
			if (mx >= 400 && mx <= 600 && my >= 300 && my <= 400) {
				backgroundSet = false;
				screen = 2;

			}
			if (mx >= 400 && mx <= 600 && my >= 500 && my <= 600) {
				try {
					read();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (mx >= 400 && mx <= 600 && my >= 700 && my <= 800) {
				quit();
			}
		} else {

			if (screen == 2) {
				if (mx >= 450 && mx <= 550 && my >= 600 && my <= 650) {
					backgroundSet = false;
					screen = 1;

				}
				if (mx >= 200 && mx <= 400 && my >= 400 && my <= 500) {
					backgroundSet = false;
					screen = 3;

				}
				if (mx >= 600 && mx <= 800 && my >= 400 && my <= 500) {
					backgroundSet = false;
					screen = 4;
				}
			} else {
				if (screen == 3) {
					if (mx >= 450 && mx <= 550 && my >= 600 && my <= 650) {
						AI = true;
						choice = 2;
						initgame = true;
						backgroundSet = false;
						screen = 5;
					}
					if (mx >= 450 && mx <= 550 && my >= 700 && my <= 750) {
						backgroundSet = false;
						screen = 2;

					}
					if (mx >= 125 && mx <= 325 && my >= 350 && my <= 500) {
						choice = 1;
						initgame = true;
						backgroundSet = false;
						screen = 5;

					}
					if (mx >= 400 && mx <= 600 && my >= 350 && my <= 500) {
						choice = 2;
						initgame = true;
						backgroundSet = false;
						screen = 5;

					}
					if (mx >= 675 && mx <= 870 && my >= 350 && my <= 500) {
						choice = 3;
						initgame = true;
						backgroundSet = false;
						screen = 5;

					}
				}

				else {
					if (screen == 4) {
						if (mx >= 450 && mx <= 550 && my >= 600 && my <= 650) {
							backgroundSet = false;
							screen = 2;

						}
						if (mx >= 250 && mx <= 450 && my >= 400 && my <= 500) {
							choice = 4;
							initgame = true;
							backgroundSet = false;
							screen = 5;

						}
						if (mx >= 550 && mx <= 750 && my >= 400 && my <= 500) {
							choice = 5;
							initgame = true;
							backgroundSet = false;
							screen = 5;

						}
					} else {
						if (screen == 6) {
							if (mx >= 450 && mx <= 600 && my >= 600
									&& my <= 650) {
								objectNum = 0;
								backgroundSet = false;
								screen = 1;

							}
							if (mx >= 200 && mx <= 400 && my >= 400
									&& my <= 500) {

							}
							if (mx >= 600 && mx <= 800 && my >= 400
									&& my <= 500) {

							}
						}

					}
				}
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}