package nl.avans.festivalplanner.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.Timer;

import nl.avans.festivalplanner.model.FestivalHandler;
import nl.avans.festivalplanner.model.Stage;

import com.javaswingcomponents.accordion.JSCAccordion;
import com.javaswingcomponents.accordion.TabOrientation;
import com.javaswingcomponents.accordion.listener.AccordionEvent;
import com.javaswingcomponents.accordion.listener.AccordionListener;
import com.javaswingcomponents.accordion.plaf.AccordionUI;
import com.javaswingcomponents.accordion.plaf.basic.BasicHorizontalTabRenderer;
import com.javaswingcomponents.accordion.plaf.darksteel.DarkSteelAccordionUI;
import com.javaswingcomponents.framework.painters.configurationbound.GradientColorPainter;

public class SimulatorPanel extends Panel
{
	private static final long serialVersionUID = -3533223589206092760L;
	
	private static final boolean debug = true;
	
	private int pXOffset = 750;
	private int pYOffset = 110;
	
	private Toolbar toolbar;
	private Simulator simulator;
	MouseListener mouseListener = new MouseListener();
	ArrayList<Shape> stageShapeList = new ArrayList<Shape>();

	public SimulatorPanel()
	{
		this.addMouseMotionListener(mouseListener);
		toolbar = new Toolbar();
		simulator = new Simulator(new Dimension(900,400)); // TODO change size here.
	}

	public Toolbar getToolbar()
	{
		return toolbar;
	}

	public Simulator getSimulator()
	{
		return simulator;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{

	}

	@Override
	public Panel getPanel()
	{
		return this;
	}

	public class Toolbar extends JPanel implements ActionListener
	{
		JSCAccordion accordion = new JSCAccordion();
		public Toolbar()
		{
			addMouseMotionListener(mouseListener);
			//accordion.setBounds(200, 200, 200, 200);
			addTabs(accordion);
			listenForChanges(accordion);
			changeTabOrientation(accordion);
			//changeTheLookAndFeel(accordion);
			//customizeALookAndFeel(accordion);
			setLayout(new GridLayout(1,1,30,30));
			add(accordion);

			System.out.println("Added accordion");
		}

		private void addTabs(JSCAccordion accordion) {
			JPanel transparentPanel = new JPanel();
			transparentPanel.setOpaque(false);
			transparentPanel.setBackground(Color.GRAY);

			JPanel opaquePanel = new JPanel();
			opaquePanel.setOpaque(true);
			opaquePanel.setBackground(Color.GRAY);

			accordion.addTab("Tab 1", new JButton("Button"));
			accordion.addTab("Stages", getStageTab());
			accordion.addTab("Tab 3", new JScrollPane(new JTree()));
			accordion.addTab("Tab 4", opaquePanel);
			accordion.addTab("Tab 5", transparentPanel);
		}

		private JScrollPane getStageTab()
		{
			Panel pane = new Panel(){
				@Override
				protected void paintComponent(Graphics g)
				{
					super.paintComponent(g);
					Graphics2D g2 = (Graphics2D) g;
					
					Rectangle2D rectangle = new Rectangle2D.Double(1, 1, 50, 50);
					
					for(Shape s : stageShapeList)
					{
						g2.fill(s);
					}
				}

				@Override
				public void actionPerformed(ActionEvent e)
				{
					// TODO Auto-generated method stub
					
				}

				@Override
				public Panel getPanel()
				{
					// TODO Auto-generated method stub
					return null;
				}
			};
			
			ArrayList<Stage> stageList = FestivalHandler.Instance().getStagesTest();
						
			int horOffset = 10; // horizontal distance offset
			
			int curX = 0 + horOffset;
			int curY = 0 + horOffset;
			
			int shapeWidth = 100;
			int shapeHeight = 100;
			
			int counter = 0;
			
			for(Stage stage : stageList)
			{
				if(debug)
				{
					System.out.println("stageName of stages to display in StagesTab!: " + stage.getName());
				}
				
				//create shapes for acts
				int x = curX;
				int y = curY;
				stageShapeList.add(new Rectangle2D.Double(curX, curY, shapeWidth, shapeHeight));
				
				//increases curX
				curX += shapeWidth + horOffset;
				//increases curY and resets curX: when the value of counter is a multiple of 2
				if((counter != 0) && (counter % 2 == 1))
				{
					curY += shapeHeight + horOffset;
					curX = 0 + horOffset;
				}
				
				counter++;
			}
					
			JScrollPane scrollPane = new JScrollPane(pane);
			return scrollPane;
		}

		private void listenForChanges(JSCAccordion accordion) 
		{
			accordion.addAccordionListener(new AccordionListener() 
			{
				@Override
				public void accordionChanged(AccordionEvent accordionEvent)
				{
					switch (accordionEvent.getEventType()) 
					{
					case TAB_ADDED: 
						//add your logic here to react to a tab being added.
						break;
					case TAB_REMOVED: 
						//add your logic here to react to a tab being removed.
						break;	
					case TAB_SELECTED: 
						//add your logic here to react to a tab being selected.
						break;
					}
				}
			});
		}
		private void changeTabOrientation(JSCAccordion accordion) 
		{
			accordion.setTabOrientation(TabOrientation.VERTICAL);
		}

		private void changeTheLookAndFeel(JSCAccordion accordion) 
		{
			AccordionUI newUI = DarkSteelAccordionUI.createUI(accordion);
			accordion.setUI(newUI);
		}

		/**
		 * The easiest way to customize a AccordionUI is to change the 
		 * default Background Painter, AccordionTabRenderers or tweak values
		 * on the currently installed Background Painter, AccordionTabRenderers and UI.
		 * @param accordion
		 */
		private void customizeALookAndFeel(JSCAccordion accordion) {
			//example of changing a value on the ui.
			DarkSteelAccordionUI ui = (DarkSteelAccordionUI) accordion.getUI();
			ui.setHorizontalBackgroundPadding(10);

			//example of changing the AccordionTabRenderer
			BasicHorizontalTabRenderer tabRenderer = new BasicHorizontalTabRenderer(accordion);
			tabRenderer.setFontColor(Color.RED);
			accordion.setHorizontalAccordionTabRenderer(tabRenderer);

			//example of changing the background painter.
			GradientColorPainter backgroundPainter = (GradientColorPainter) accordion.getBackgroundPainter();
			backgroundPainter = (GradientColorPainter) accordion.getBackgroundPainter();
			backgroundPainter.setStartColor(Color.BLACK);
			backgroundPainter.setEndColor(Color.WHITE);

			//the outcome of this customization is not the most visually appealing result
			//but it just serves to illustrate how to customize the accordion's look and feel.
			//The UI is darkSteel.
			//The backgroundPainter is a gradient running from Black to White
			//The accordionTabRenderer belongs to the BasicAccordionUI
			//And finally the text of the tab is red!
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
	}

	public class Simulator extends JPanel implements ActionListener
	{
		private Image _grassTexture;
		private Dimension _size;
		

		public Simulator(Dimension dim)
		{
			setPreferredSize(dim);
			this._size = dim;
			addMouseMotionListener(mouseListener);
			try
			{
				 _grassTexture = new ImageIcon("bin/grass.png").getImage();
				 
				 System.out.println(_grassTexture);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			Timer timer = new Timer(30, this);
			timer.start();
		}

		@Override
		public void actionPerformed(ActionEvent event)
		{
			repaint();
		}

		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			
			int imageWidth = 400;
			int imageHeight = 400;
			
			int curY = 0;
			for(int y = 0; y<=(this._size.getHeight() / imageHeight); y++)
			{
				int curX = 0;
				for(int x = 0; x<=(this._size.getWidth() / imageWidth); x++)
				{
					g2.drawImage(_grassTexture, curX, curY, imageWidth, imageHeight, null);
					curX += imageWidth;
				}
				curY+= imageHeight;
			}
			/*
			for(int y =0; currentHeight<=this._size.getHeight(); y++)
			{
				for(int x=0; currentWidth<=this._size.getWidth(); x++)
				{
					g2.drawImage(_grassTexture, currentWidth, currentHeight, 400, 400, null);
					currentWidth = x*400;
				}
				currentHeight = y*400;
			}
			*/
			//System.out.println("repaint");
		}
	}

	
	
	public class MouseListener implements MouseMotionListener
	{
		@Override
		public void mouseDragged(MouseEvent e)
		{
			for(Shape s : stageShapeList)
			{
				Point point = e.getPoint();
				point.translate(-pXOffset, -pYOffset);
				
				if(s.contains(point))
				{
					if(debug)
						System.out.println("ShapeDragged!!!!");
				}
			}
			
		}

		@Override
		public void mouseMoved(MouseEvent e)
		{
			for(Shape s : stageShapeList)
			{
				Point point = e.getPoint();
				point.translate(-pXOffset, -pYOffset);
				
				if(s.contains(point))
				{
					if(debug)
						System.out.println("ShapeHovered!!!!!");
				}
			}
		}
		
	}

}

