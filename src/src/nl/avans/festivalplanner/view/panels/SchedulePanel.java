package nl.avans.festivalplanner.view.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import nl.avans.festivalplanner.model.FestivalHandler;
import nl.avans.festivalplanner.model.Stage;
import nl.avans.festivalplanner.utils.Utils;
import nl.avans.festivalplanner.view.ApplicationView;
import nl.avans.festivalplanner.view.GUIHelper;
import nl.avans.festivalplanner.view.Panel;

public class SchedulePanel extends Panel implements MouseMotionListener, MouseListener
{
	private GUIHelper _guiHelper;
	
	private final int startX = GUIHelper.XOFFSET;
	private final int startY = GUIHelper.YOFFSET;
	
	Color defaultColor = new Color(0x09000000 ,true); //color black with alpha 09 and alpha=true

//	ArrayList<Stage> stageList = FestivalHandler.Instance().getStages() // TODO UNCOMMENT
	ArrayList<Stage> stageList = FestivalHandler.Instance().getStagesTest(); // TODO COMMENT
	
	private final int ROWS = stageList.size(); // rows in schedule to show depends on stages in festival
	private final int COLS = 16;
	
	Shape rectangle[][] = new Shape[ROWS][COLS]; // button array of 12 * 16
	Color rectColor[][] = new Color[ROWS][COLS];
	
	boolean plusSign[][] = new boolean[ROWS][COLS]; // tracks weather a plus sign should be displayed or not
	
	public SchedulePanel()
	{
		super();
		_guiHelper = new GUIHelper();
		this.addMouseMotionListener(this);
		
		int width = ApplicationView.WIDTH;
		int height = ApplicationView.HEIGHT;
		
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
			
				for(int x = 0; x < ROWS; x++)
				{
					for(int y = 0; y < COLS; y++)
					{				
						if(rectangle[x][y].contains(e.getPoint()))
						{
							System.out.println("ROW: " + x + " COLUMN: " + y); // TODO CHANGE
						}						
					}
				}
			}
		});
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		int titleWidth = 180; // the width of the column that shows the stage name
		
		Calendar timeValue = new GregorianCalendar();
		timeValue.set(Calendar.HOUR_OF_DAY, 12);
		timeValue.set(Calendar.MINUTE, 0);
		
		String timeString = "";
		for(int i = 0; i < 17; i++) // constructs the string that displays the time line in the top
		{
			String time = "";
			
			if(i > 0)
				time += " - ";
			
			time += Utils.getTimeString(timeValue);
			//TODO CHECK THIS IMPROVE READABILITY
			timeString += time;
			
			timeValue.add(Calendar.MINUTE, 60);
		}
		
		g2.drawString(timeString, startX + titleWidth + 20, startY);
		
		int curX = startX;
		int curY = startY;
		
		curY += 40;
		curX += 20;
		
		int lineHeight = 50;
		
		//stageList defined in constructor
		if(stageList.size() > 10)
		{
			lineHeight += - (stageList.size() - 10) * 4.5; // decrease the lineHeight for every stage > 8 that is added.
		}
		
		int x = 0;  // DON'T JUDGE ME!
		for(Stage s : stageList)
		{
			if(lineHeight < 10)
				System.out.println("lineHeight too small!");
			
			//draws the String in front of the line and the line itself (without blocks)
			g2.drawString(s.getName(), curX, curY);
			
			g2.setColor(Color.lightGray);
			g2.drawLine(curX + titleWidth + 20 -5, curY - 5 -1, ApplicationView.WIDTH - 30, curY - 5 -1);
			g2.drawLine(curX + titleWidth + 20 -5, curY - 5 +0, ApplicationView.WIDTH - 30, curY - 5 +0);
			g2.drawLine(curX + titleWidth + 20 -5, curY - 5 +1, ApplicationView.WIDTH - 30, curY - 5 +1);
			
			//code that draws the rectangles
			int boxWidth = 40;
			g2.setColor(defaultColor);
			for(int y = 0; y < 16; y++)
			{
				g2.setColor(rectColor[x][y]);
				int rectX = curX + titleWidth + 20 + (y * 48);
				int rectY = curY - lineHeight / 2;
				rectangle[x][y] = new Rectangle2D.Double(rectX, rectY, boxWidth, lineHeight - 10);
				g2.fill(rectangle[x][y]);
//				g2.fillRect(curX + titleWidth + 20 + (i * 48), curY - lineHeight / 2, boxWidth, lineHeight - 10);
			
				if(plusSign[x][y] == true)
				{
					g2.setColor(Color.gray);
					int plusLength = 20;
					int plusX = rectX + boxWidth / 2 - plusLength /2;
					int plusY = rectY + lineHeight /2 - 8;
					Shape plusShapeX = new Rectangle2D.Double(plusX, plusY, plusLength, 6);
					Shape plusShapeY = new Rectangle2D.Double(plusX + (plusLength/2 - 3), plusY - (plusLength/2 -3), 6, plusLength);
					g2.fill(plusShapeX);
					g2.fill(plusShapeY);
				}
			}
			g2.setColor(Color.black);
			
			curY += lineHeight;
			x++;
		}
	}
	
	public void mouseMoved(MouseEvent e)
	{
		for(int i = 0; i < ROWS; i++)
		{
			for(int j = 0; j < COLS; j++) // TODO CHANGE VALUE OF 16 TO USE GLOBAL VALUE
			{
				if(rectangle[i][j].contains(e.getPoint()))
				{
					rectColor[i][j] = Color.lightGray;
					plusSign[i][j] = true;
					
					repaint();
				}
				else
				{
					rectColor[i][j] = defaultColor;
					plusSign[i][j] = false;
					
					repaint();
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Panel getPanel()
	{
		return this;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		}
	}