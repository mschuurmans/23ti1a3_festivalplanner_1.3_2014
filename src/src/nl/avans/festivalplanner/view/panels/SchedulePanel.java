package nl.avans.festivalplanner.view.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import nl.avans.festivalplanner.model.Act;
import nl.avans.festivalplanner.model.Artist;
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

	Color defaultBoxColor = new Color(0x09_00_00_00, true); // color black with alpha  09 and alpha=true
	private ArrayList<Integer> _timeList = new ArrayList<Integer>();
	private int _curAct = 0;


//	ArrayList<Stage> stageList = FestivalHandler.Instance().getStages(); // TODO UNCOMMENT
	ArrayList<Stage> _stageList = FestivalHandler.Instance().getStagesTest(); // debugging purposes // TODO COMMENT
	List<Artist> _artistList = FestivalHandler.Instance().getArtists(); // TODO COMMENT
	ArrayList<Act> _actList = FestivalHandler.Instance().getActsTest();
	ArrayList<Shape> _actShapeList = new ArrayList<Shape>();

	private final int ROWS = _stageList.size(); // rows in schedule to show depends on stages in festival
	private final int COLS = 16;

	int stageHeight[] = new int[ROWS];
	int lineHeight;

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

		addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				for(Shape actShape : _actShapeList)
				{
					if(actShape.contains(e.getPoint()))
					{
						// actShapes are being created with a for each loop that goes trough the _actList
						// the first shape in the _actShapeList is a visual representation of the first act in the _actList
						int actShapeNumber = _actShapeList.indexOf(actShape);
						
						System.out.println("Act: " + _actList.get(actShapeNumber).getName() + " has been clicked."); //TODO REPLACE
						// TODO replace body of code
						
						return; //necessary to return operation in order to ignore clicks on rectangles when actShapes have been clicked
					}
				}

				for (int x = 0; x < ROWS; x++)
				{
					for (int y = 0; y < COLS; y++)
					{
						if (rectangle[x][y].contains(e.getPoint()))
						{
							System.out.println("ROW: " + x + " COLUMN: " + y); // TODO CHANGE
							showDialog(x, y);
						}
					}
				}
			}
		});
		for (int hours = 12; hours != 4; hours++)
		{
			if (hours == 24)
			{
				hours = 0;
			}
			for (int minutes = 0; minutes < 4; minutes++)
			{
				_timeList.add(hours * 100 + minutes * 15);
			}
		}
	}

	private void showDialog(int stage, int time)
	{
		JPanel _dialogPanel = createDialogBox();
		_artistList = FestivalHandler.Instance().getArtists();
		List stageList = FestivalHandler.Instance().getStagesTest(); // TODO change to actual list instead of test list
		GregorianCalendar _startTime = new GregorianCalendar();
		GregorianCalendar _endTime = new GregorianCalendar();
		
		Object[] _artists = _artistList.toArray(new Object[_artistList.size()]);
		Object[] _stages = stageList.toArray(new Object[stageList.size()]);

		Object[] _times = _timeList.toArray(new Object[_timeList.size()]);
		if (_artists.length != 0)
		{
			Object _selectedArtist = JOptionPane.showInputDialog(null,
					"Choose artist", "Create new act",
					JOptionPane.INFORMATION_MESSAGE, null, _artists,
					_artists[0]);
			Object _selectedStage = JOptionPane.showInputDialog(null,
					"Choose stage", "Create new act",
					JOptionPane.INFORMATION_MESSAGE, null, _stages,
					_stages[stage]);
			Object _selectedStartTime = JOptionPane.showInputDialog(null,
					"Choose start time", "Create new act",
					JOptionPane.INFORMATION_MESSAGE, null, _times,
					_times[time*4]);
			Object _selectedEndTime = JOptionPane.showInputDialog(null,
					"Choose end time", "Create new act",
					JOptionPane.INFORMATION_MESSAGE, null, _times,
					_times[(time+1)*4]);

			_startTime.set(2014, 2, 1, (int)_selectedStartTime/100, (int)_selectedStartTime%100);
			_endTime.set(2014, 2, 1, (int)_selectedEndTime/100, (int)_selectedEndTime%100);
			
			FestivalHandler.Instance().addAct(new Act("", (Stage)_selectedStage, (Artist)_selectedArtist, _startTime,
							_endTime));
			System.out.println(FestivalHandler.Instance().getFestival().getSchedule().getActs().get(_curAct).toString());
			_curAct++;
		}
		else
		{
			JOptionPane.showMessageDialog(this,
					"No artists have been registered");
		}
	}
	
	private JPanel createDialogBox()
	{
		JPanel panel = new JPanel();
		
		return panel;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		int titleWidth = 180; // the width of the column that shows the stage name *timeline starts to the right

		// draws the timesString in the top
		String timeString = getTimeString(12, 17, 60);
		g2.drawString(timeString, startX + titleWidth + 20, startY);

		int curX = startX;
		int curY = startY;

		// Drawing the timelines start here!
		curY += 40;
		curX += 20;

		//determining the height of each timeline
		lineHeight = 50;
		if (_stageList.size() > 10) // stageList defined in constructor
		{
			lineHeight += -(_stageList.size() - 10) * 4.5; // decrease the lineHeight for every stage > 8 that is added.
		}

		int x = 0; // DON'T JUDGE ME!
		for (Stage s : _stageList) //for each stage in the stageList !
		{
			if (lineHeight < 10)
				System.out.println("lineHeight too small!");

			// draws the String in front of the line and the line itself (without blocks)
			g2.drawString(s.getName(), curX, curY);

			g2.setColor(Color.lightGray);
			g2.drawLine(curX + titleWidth + 20 - 5, curY - 5 - 1, ApplicationView.WIDTH - 30, curY - 5 - 1);
			stageHeight[x] = curY - 5 + 0; // fills an array with the height of each timeline for later reference
			g2.drawLine(curX + titleWidth + 20 - 5, stageHeight[x], ApplicationView.WIDTH - 30, curY - 5 + 0);
			g2.drawLine(curX + titleWidth + 20 - 5, curY - 5 + 1, ApplicationView.WIDTH - 30, curY - 5 + 1);
			// debugging purposes
			// for(int k = 0; k < stageHeight.length; k++)
			// {
			// System.out.println("row: " + k + "height: " + stageHeight[k]);
			// }

			// code that draws the rectangles
			int boxWidth = 40;
			g2.setColor(defaultBoxColor);
			for (int y = 0; y < 16; y++)
			{
				g2.setColor(rectColor[x][y]);
				int rectX = curX + titleWidth + 20 + (y * 48);
				int rectY = curY - lineHeight / 2;
				rectangle[x][y] = new Rectangle2D.Double(rectX, rectY, boxWidth, lineHeight - 10);
				g2.fill(rectangle[x][y]);
				// g2.fillRect(curX + titleWidth + 20 + (i * 48), curY - lineHeight / 2, boxWidth, lineHeight - 10);

				if (plusSign[x][y] == true)
				{
					g2.setColor(Color.gray);
					int plusLength = 20;
					int plusX = rectX + boxWidth / 2 - plusLength / 2;
					int plusY = rectY + lineHeight / 2 - 8;
					Shape plusShapeX = new Rectangle2D.Double(plusX, plusY, plusLength, 6);
					Shape plusShapeY = new Rectangle2D.Double(plusX + (plusLength / 2 - 3), plusY - (plusLength / 2 - 3), 6, plusLength);
					g2.fill(plusShapeX);
					g2.fill(plusShapeY);
				}
			}
			g2.setColor(Color.black);

			curY += lineHeight;
			x++;
		}
		
		// DISPLAY ACTS
		// code here
		for (Act act : _actList)
		{
			Shape shape = createActShape(act);
			_actShapeList.add(shape);
			g2.setColor(Color.gray);
			g2.fill(shape);
			g2.setColor(Color.lightGray);
			String actName = act.getName();
			actName = Utils.cropString(actName, (int)shape.getBounds().getWidth() - 6); //cropString on the string and maxwidth
			int stringWidth = Utils.getWidth(actName);
			g2.drawString(actName,(int)( (shape.getBounds().x) + (shape.getBounds().getWidth() /2) - (stringWidth /2) -3 ), shape.getBounds().y + 4 + 20);
			g2.setColor(Color.black);
		}
	}

	private Shape createActShape(Act act)
	{
		double timeStart = ( (act.getStartTime().get(Calendar.HOUR_OF_DAY) * 60) + act.getStartTime().get(Calendar.MINUTE) ) / 60.0;
		double timeEnd = ( (act.getEndTime().get(Calendar.HOUR_OF_DAY) * 60) + act.getEndTime().get(Calendar.MINUTE) ) / 60.0;
//		System.out.println("act.timeEnd: " + timeEnd); // DEBUGGING PURPOSES
//		System.out.println(act.getName() + act.getEndTime().get(Calendar.MINUTE)); // DEBUGGING PURPOSES
		Stage stage = act.getStage();
		int stageIndex = _stageList.indexOf(stage);
		int ppMinute = 48; // pixels per minute
		if(timeEnd < 12) //when the end time is after midnight its between 0 and 4 (or higher)
		{
			timeEnd += 24; // add 12 so the calculated shapeWidth value makes sense
		}
		int timeOffset = -12; // time offset. the amount to add to the time in order for 12pm to be the origin
		int shapeHeight = lineHeight -10; //value used to be 40, lineheight is 50* - *when stagelist.size <= 8
		int x = (int) (227 + ( (timeStart + timeOffset) * ppMinute));
		int y = stageHeight[stageIndex] - shapeHeight / 2;
		int shapeWidth = (int) ((timeEnd - timeStart) * ppMinute);
		
		Shape shape = new Rectangle2D.Double(x, y, shapeWidth, shapeHeight);

		return shape;
	}
	
	/**
	 * getTimeString generates a timeString, used for the schedulePanel
	 * @param startTime the time from wich to start counting in hours
	 * @param entries the amount of timeStrings to show. 2 means ¨12:00 - 13:00" with start time 12 and interval 60
	 * @param interval the amount the second consecutive time gets increased with in minutes
	 * @return a generated timeString
	 * @author jack
	 */
	private String getTimeString(int startTime, int entries, int interval)
	{
		// used to get formatted time for the timeline scale in the top
		Calendar timeValue = new GregorianCalendar();
		timeValue.set(Calendar.HOUR_OF_DAY, startTime);
		timeValue.set(Calendar.MINUTE, 0);

		// constructs the string that displays the time line in the top
		String timeString = "";
		for (int i = 0; i < entries; i++)
		{
			if (i > 0)
			{
				timeString += " - ";
			}

			timeString += Utils.getTimeString(timeValue);

			timeValue.add(Calendar.MINUTE, interval);
		}
		
		return timeString;
	}
	
	public void mouseMoved(MouseEvent e)
	{
		for (int i = 0; i < ROWS; i++)
		{
			for (int j = 0; j < COLS; j++) // TODO CHANGE VALUE OF 16 TO USE GLOBAL VALUE
			{
				if (rectangle[i][j].contains(e.getPoint()))
				{
					rectColor[i][j] = Color.lightGray;
					plusSign[i][j] = true;

					repaint();
				} else
				{
					rectColor[i][j] = defaultBoxColor;
					plusSign[i][j] = false;

					repaint();
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Panel getPanel()
	{
		return this;
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
	}
}
