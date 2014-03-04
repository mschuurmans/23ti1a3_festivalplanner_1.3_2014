package nl.avans.festivalplanner.view;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import com.javaswingcomponents.accordion.JSCAccordion;
import com.javaswingcomponents.accordion.TabOrientation;
import com.javaswingcomponents.accordion.listener.AccordionEvent;
import com.javaswingcomponents.accordion.listener.AccordionListener;
import com.javaswingcomponents.accordion.plaf.AccordionUI;
import com.javaswingcomponents.accordion.plaf.basic.BasicHorizontalTabRenderer;
import com.javaswingcomponents.accordion.plaf.darksteel.DarkSteelAccordionUI;
import com.javaswingcomponents.framework.painters.configurationbound.GradientColorPainter;

import nl.avans.festivalplanner.model.*;
import nl.avans.festivalplanner.model.simulator.*;
import nl.avans.festivalplanner.utils.Enums.Text;

public class SimulatorPanel extends Panel
{
	private static final long serialVersionUID = -3533223589206092760L;
	private static final boolean debug = true;
	
	private Toolbar toolbar;
	private Simulator simulator;
	
	ArrayList<Shape> stageShapeList = new ArrayList<Shape>();

	public SimulatorPanel()
	{
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
			//accordion.setSize(200,200);
			//accordion.setBounds(1200, 0, 200, 200);
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

		private void addTabs(JSCAccordion accordion) {
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
					System.out.println("stageName of stages to display in StagesTab!: " + stage.getName());
				
				//create shapes for acts
				int x = curX;
				int y = curY;
				stageShapeList.add(new Rectangle2D.Double(curX, curY, shapeWidth, shapeHeight));
				
				curX += shapeWidth + horOffset;
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
			Element test = new Area(new Dimension(100,100), new Vector(100,100));
			test.draw(g2);
			//Draw all the elements to the screen.
			for(Element e : FestivalHandler.Instance().getElementsOnTerrain())
			{
				e.draw(g2);
			}
			// end drawing allthe elements to the screen.	
		}	
	}

}
