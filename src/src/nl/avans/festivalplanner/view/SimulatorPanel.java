package nl.avans.festivalplanner.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTree;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;

import nl.avans.festivalplanner.model.FestivalHandler;
import nl.avans.festivalplanner.model.Stage;
import nl.avans.festivalplanner.model.simulator.Area;
import nl.avans.festivalplanner.model.simulator.Element;
import nl.avans.festivalplanner.model.simulator.Vector;
import nl.avans.festivalplanner.utils.Enums.Text;
import nl.avans.festivalplanner.utils.*;

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
	MouseListenerToolbar mouseListenerToolbar = new MouseListenerToolbar();

	public SimulatorPanel()
	{
		AssetManager.Instance().loadAssets(); //loading all the images into the memory.

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
			addTabs(accordion);
			listenForChanges(accordion);
			changeTabOrientation(accordion);
			//changeTheLookAndFeel(accordion);
			//customizeALookAndFeel(accordion);
			setLayout(new GridLayout(1,1,30,30));
			//setLayout(null);
			add(accordion);

			if(debug)
				System.out.println("Added accordion");
		}

		private void addTabs(JSCAccordion accordion)
	       	{
			JPanel transparentPanel = new JPanel();
			transparentPanel.setOpaque(false);
			transparentPanel.setBackground(Color.GRAY);

			JPanel opaquePanel = new JPanel();
			opaquePanel.setOpaque(true);
			opaquePanel.setBackground(Color.GRAY);

			accordion.addTab(Text.Controls.toString(), getControlTab());
			accordion.addTab(Text.Stages.toString(), getStageTab());

			accordion.addTab(Text.Stalls.toString(), new JScrollPane(new JTree()));
			accordion.addTab(Text.Facilities.toString(), opaquePanel);
			accordion.addTab(Text.Remaining.toString(), transparentPanel);
		}

		private JPanel getControlTab()
		{
			JPanel result = new JPanel(new FlowLayout());
			JPanel controlPanel = new JPanel(new GridLayout(0,2));

			final JButton buttonStart = new JButton(Text.Start.toString());
			final JButton buttonStop = new JButton(Text.Stop.toString());
			buttonStop.setEnabled(false);


			ActionListener listener = (new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(e.getSource() == buttonStart)
					{
						buttonStop.setEnabled(true);
						buttonStart.setEnabled(false);
					}
					else if(e.getSource() == buttonStop)
			{
				buttonStop.setEnabled(false);
				buttonStart.setEnabled(true);
			}	
				}
			});


			buttonStart.addActionListener(listener);
			buttonStop.addActionListener(listener);

			JLabel visitorLabel = new JLabel(Text.People.toString());

			JSpinner visitors = new JSpinner();
			visitors.setModel(new SpinnerNumberModel(1000,0,1000000, 1000));

			controlPanel.add(buttonStart);
			controlPanel.add(buttonStop);
			controlPanel.add(visitorLabel);
			controlPanel.add(visitors);
			
			result.add(controlPanel);
			return result;
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
					
					int shapeWidth = 100;
					int shapeHeight = 100;
					
					int horOffset = 10 + (shapeWidth / 2); // horizontal offset + 50 because element is drawn in the center of the object not in left corner.
					int curX = 0 + horOffset;
					int curY = 0 + horOffset;


					int counter = 0;

					for(Stage s : FestivalHandler.Instance().getStages())
					{
						int x = curX;
						int y = curY;

						//g2.fill(new Rectangle2D.Double(curX, curY, shapeWidth, shapeHeight));
						s.setSize(new Dimension(shapeWidth, shapeHeight));
						s.setPosition(new Vector(curX, curY));
						s.draw(g2);

						curX += (int)(shapeWidth / 1.5) +horOffset;
						
						if((counter != 0) && (counter % 2 == 1))
						{
							curY += (int)(shapeHeight / 2) + horOffset;
							curX = 0 + horOffset;
						}	

						counter++;
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
			
			pane.addMouseMotionListener(mouseListenerToolbar);

			// TODO Fix the scrollpane to actually do something.		
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
			
			Element test = new Area(new Dimension(100,100), new Vector(100,100));
			FestivalHandler.Instance().addElementToTerrain(test);

			Timer timer = new Timer(30, this);
			timer.start();
		}

		@Override
		public void actionPerformed(ActionEvent event)
		{
			repaint();
		}

		/** 
		 * drawes all the elements to the simulator screen
		 * @Author Michiel & Kasper
		 */
		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			
			int imageWidth = 400;
			int imageHeight = 400;
			
			// begin drawing background	
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
			
			
			// end drawing background
			//Draw all the elements to the screen.
			for(Element e : FestivalHandler.Instance().getElementsOnTerrain())
			{
				e.draw(g2);
			}
			// end drawing allthe elements to the screen.	
		}	
	}

	
	
	public class MouseListener implements MouseMotionListener
	{
		/**
		 * Override the mouseDragged event to check either the elements on the simulatorTerrain 
		 * @Author Michiel & Jack
		 */
		@Override
		public void mouseDragged(MouseEvent e)
		{
			boolean debugMethod = false;
			
			if(debugMethod)			
				System.out.println("Dragged: X: " + e.getPoint().getX() + " Y: " + e.getPoint().getY());	
			boolean hasDragged = false;
			for(Element element : FestivalHandler.Instance().getElementsOnTerrain())
			{
				if(element.contains(e.getPoint()))
				{
					if(debugMethod)
						System.out.println("Element was dragged!");

					if(!hasDragged) // stops the multiple item drag bug.
						element.drag(e.getPoint());

					hasDragged = true;
				}
			}
			
		}

		
		/**
		 * Override the MouMoved event to check either the elements on the simulatorTerrain 
		 * @Author Michiel & Jack
		 */
		@Override
		public void mouseMoved(MouseEvent e)
		{
			boolean debugMethod = false;

			if(debugMethod)
				System.out.println("X: " + e.getPoint().getX() + " Y: " + e.getPoint().getY());

			// for all the elements on the terrain check if it is hovered by the mouse.
			for(Element element : FestivalHandler.Instance().getElementsOnTerrain())
			{
				if(element.contains(e.getPoint()))
				{
					if(debugMethod)
						System.out.println("Element was hovered!");
				}
			}
		}
		
	}

	public class MouseListenerToolbar implements MouseMotionListener
	{
		@Override
		public void mouseDragged(MouseEvent e)
		{

			boolean debugMethod = false;
			
			if(debugMethod)			
				System.out.println("Dragged: X: " + e.getPoint().getX() + " Y: " + e.getPoint().getY());
			
			for(Stage s : FestivalHandler.Instance().getStages())
			{
				Point point = e.getPoint();
				
				if(s.contains(point))
				{
					if(debugMethod)
						System.out.println("Stage in toolbar was dragged!");
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e)
		{
					
			boolean debugMethod = false;

			if(debugMethod)
				System.out.println("X: " + e.getPoint().getX() + " Y: " + e.getPoint().getY());
	
			for(Stage s : FestivalHandler.Instance().getStages())
			{
				Point point = e.getPoint();
				
				if(s.contains(point))
				{
					if(debugMethod)
						System.out.println("ShapeHovered!!!!!");
				}
			}
		}
	}

}

