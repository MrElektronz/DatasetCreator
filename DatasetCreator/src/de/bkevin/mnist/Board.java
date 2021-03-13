package de.bkevin.mnist;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Board extends JPanel {
	
	int x,y;
	boolean startpaint = true;
	private BufferedImage paintImage;
	public boolean mouseDown = false;
	String storedPath = "";
	public Board(){
	
		addMouseMotionListener(new MList());
		addMouseListener(new MouseListen());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(new Color(50,50,50));
		g2.fillRect(0, 0, 600, 600);
		g2.setColor(Color.BLACK);
		g2.fillRect(300-112, 300-112, 112, 112);
		g2.setColor(Color.GREEN);
		g2.drawRect(300-113, 300-113, 113, 113);

		
		if(startpaint){
			startpaint= false;
			paintImage = new BufferedImage(112, 112, BufferedImage.TYPE_BYTE_GRAY);
		}
		
		g.drawImage(paintImage, 300-112, 300-112, this);
		
		
	}
	
	
	public void updatePaint() {
		if(mouseDown){
		  Graphics g = paintImage.createGraphics();

		  g.setColor(Color.WHITE);
			g.fillRect(x, y, 4, 4);

	        g.dispose();
	        // repaint panel with new modified paint
	        repaint();
		}
	}
	
	
	class MouseListen implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			
		}
			

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1){
				mouseDown = true;
				x = e.getX()-(300-112);
				y = e.getY()-(300-112);
				initThread();
				}else if(e.getButton() == MouseEvent.BUTTON3){
					String path = CreatorWindow.export.getText();
					
					if(!path.endsWith("\\")){
						path= path+"\\";
					}
					
					if(storedPath != CreatorWindow.export.getText()){
						storedPath = CreatorWindow.export.getText();
						//Re-evaluate Counter
						
						File folder = new File(path);
						int i = 0;
						String toCompare = CreatorWindow.name.getText();
						for(File file: folder.listFiles()){
							String filename = file.getName().split("_")[0];
							if(filename.equals(toCompare)){
								i++;
							}
						}
						CreatorWindow.counter=i;
					}
					
					
					//Export
		
					path = path +CreatorWindow.name.getText()+"_"+CreatorWindow.counter;
					CreatorWindow.counter++;
					try {
						ImageIO.write(paintImage, "jpg", new File(path+".jpg"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					startpaint=true;
					repaint();
					//right
				}else if(e.getButton() == MouseEvent.BUTTON2){
					startpaint=true;
					repaint();
					//middle
				}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1){
				mouseDown = false;
				}
		} 
		
	}
	
	
	
	
	
	class MList extends MouseMotionAdapter{
		@Override
		public void mouseDragged(MouseEvent e) {
			if(mouseDown){
				x = e.getX()-(300-112);
				y = e.getY()-(300-112);
			}
			
			//System.out.println("repaint");
		}
	}
	
	volatile private boolean isRunning = false;
	private synchronized boolean checkAndMark() {
	    if (isRunning) return false;
	    isRunning = true;
	    return true;
	}
	private void initThread() {
	    if (checkAndMark()) {
	        new Thread() {
	            public void run() {
	                do {

	        			updatePaint();
	                
	                } while (mouseDown);
	                isRunning = false;
	            }
	        }.start();
	    }
	}
	
}
