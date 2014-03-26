package nl.avans.festivalplanner.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.event.*;

import nl.avans.festivalplanner.model.FestivalHandler;
import nl.avans.festivalplanner.model.Stage;
import nl.avans.festivalplanner.model.simulator.Area;
import nl.avans.festivalplanner.model.simulator.Building;
import nl.avans.festivalplanner.model.simulator.Element;
import nl.avans.festivalplanner.model.simulator.Entrance;
import nl.avans.festivalplanner.model.simulator.Foodstand;
import nl.avans.festivalplanner.model.simulator.People;
import nl.avans.festivalplanner.model.simulator.Signpost;
import nl.avans.festivalplanner.model.simulator.Toilet;
import nl.avans.festivalplanner.model.simulator.Vector;
import nl.avans.festivalplanner.utils.AssetManager;
import nl.avans.festivalplanner.utils.Enums.States;
import nl.avans.festivalplanner.utils.Enums.Text;
import nl.avans.festivalplanner.utils.Utils;
import nl.avans.festivalplanner.utils.Utils.Recyclebin;
import nl.avans.festivalplanner.view.dialog.IntersectionOptions;

import com.javaswingcomponents.accordion.JSCAccordion;
import com.javaswingcomponents.accordion.TabOrientation;
import com.javaswingcomponents.accordion.listener.AccordionEvent;
import com.javaswingcomponents.accordion.listener.AccordionListener;
import com.javaswingcomponents.accordion.plaf.AccordionUI;
import com.javaswingcomponents.accordion.plaf.darksteel.DarkSteelAccordionUI;
import com.javaswingcomponents.accordion.plaf.darksteel.DarkSteelHorizontalTabRenderer;
import com.javaswingcomponents.framework.painters.configurationbound.GradientColorPainter;

public class SimulatorPanel extends Panel
{
	private static final long serialVersionUID = -3533223589206092760L;
	private static final boolean debug = true;
	
	private static final int PANEL_WIDTH = 900;
	private static final int PANEL_HEIGHT = 400;

	private Element elementDraggedFromToolbar;
	
	private Recyclebin recyclebin = new Recyclebin();

	private Toolbar toolbar;
	private Simulator simulator;
	MouseListener mouseListener = new MouseListener();
	MouseListenerToolbar mouseListenerToolbar = new MouseListenerToolbar();

	int selectedAccordionTab = 0;

	public SimulatorPanel()
	{

		AssetManager.Instance().loadAssets(); // loading all the images into the
												// memory.

		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
		toolbar = new Toolbar();
		simulator = new Simulator(new Dimension(PANEL_WIDTH, PANEL_HEIGHT)); // TODO change size
															// here.
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
		private JSpinner visitors;
		private JSpinner _speedSpinner;

		public Toolbar()
		{
			addTabs(accordion);
			listenForChanges(accordion);
			changeTabOrientation(accordion);
			// changeTheLookAndFeel(accordion);
			// customizeALookAndFeel(accordion);
			setLayout(new GridLayout(1, 1, 30, 30));
			// setLayout(null);
			add(accordion);

			if (debug)
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

			accordion.addTab(Text.Stalls.toString(), getStandTab());
			accordion.addTab(Text.Facilities.toString(), getFacilityTab());
			accordion.addTab(Text.Remaining.toString(), getRemainingTab());
		}

		private JPanel getControlTab()
		{
			JPanel result = new JPanel(new FlowLayout());
			JPanel controlPanel = new JPanel(new GridLayout(0, 2));

			final JButton buttonStart = new JButton(Text.Start.toString());
			final JButton buttonStop = new JButton(Text.Stop.toString());
			buttonStop.setEnabled(false);

			ActionListener listener = (new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if (e.getSource() == buttonStart)
					{
						buttonStop.setEnabled(true);
						buttonStart.setEnabled(false);
						FestivalHandler.Instance().getControls().setState(States.Running);
					}
					else if (e.getSource() == buttonStop)
					{
						buttonStop.setEnabled(false);
						buttonStart.setEnabled(true);
						FestivalHandler.Instance().getControls().setState(States.Stopped);
					}	
				}
			});

			buttonStart.addActionListener(listener);
			buttonStop.addActionListener(listener);

			JLabel visitorLabel = new JLabel(Text.People.toString());

			visitors = new JSpinner();
			visitors.setModel(new SpinnerNumberModel(1000, 0, 1000000, 1000));

			JLabel speedLabel = new JLabel(Text.Speed.toString());

			_speedSpinner = new JSpinner();
			_speedSpinner.setModel(new SpinnerNumberModel(1,1,100,1));

			_speedSpinner.addChangeListener(new ChangeListener()
					{
						public void stateChanged(ChangeEvent e)
						{
							JSpinner s = (JSpinner)e.getSource();
							FestivalHandler.Instance().getControls().setSpeed((int)s.getValue());
							System.out.println("Value was upated");
						}		
					});

			controlPanel.add(buttonStart);
			controlPanel.add(buttonStop);
			controlPanel.add(visitorLabel);
			controlPanel.add(visitors);
			controlPanel.add(speedLabel);
			controlPanel.add(_speedSpinner);

			result.add(controlPanel);
			return result;
		}

		private JScrollPane getFacilityTab()
		{
			final List<Element> facilities = FestivalHandler.Instance()
					.getFacilities();
			FestivalHandler.Instance().addFacilities(
					new Toilet(new Dimension(100, 100), new Vector(0, 0)));
			FestivalHandler.Instance().addFacilities(
					new Entrance(new Dimension(100, 100), new Vector(0, 0)));

			JPanel pane = new JPanel()
			{
				public void paintComponent(Graphics g)
				{
					super.paintComponent(g);

					Graphics2D g2 = (Graphics2D) g;
					int shapeWidth = 100;
					int shapeHeight = 100;

					int horOffset = 10 + (shapeWidth / 2); // horizontal offset
															// + 50 because
															// element is drawn
															// in the center of
															// the object not in
															// left corner.
					int curX = 0 + horOffset;
					int curY = 0 + horOffset;

					int counter = 0;

					for (Element e : facilities)
					{
						int x = curX;
						int y = curY;

						e.setSize(new Dimension(shapeWidth, shapeHeight));
						e.setPosition(new Vector(curX, curY));
						e.draw(g2);
						curX += (int) (shapeWidth / 1.5) + horOffset;

						if ((counter != 0) && (counter % 2 == 1))
						{
							curY += (int) (shapeHeight / 2) + horOffset;
							curX = 0 + horOffset;
						}

						counter++;

					}
				}
			};

			pane.addMouseListener(mouseListenerToolbar);
			pane.addMouseMotionListener(mouseListenerToolbar);

			return new JScrollPane(pane);
		}

		private JScrollPane getStandTab()
		{
			final List<Element> stands = FestivalHandler.Instance().getStands();
			FestivalHandler.Instance().addStands(
					new Foodstand(new Dimension(100, 100), new Vector(0, 0)));

			Panel pane = new Panel()
			{
				@Override
				protected void paintComponent(Graphics g)
				{
					super.paintComponent(g);
					Graphics2D g2 = (Graphics2D) g;
					int shapeWidth = 100;
					int shapeHeight = 100;

					int horOffset = 10 + (shapeWidth / 2); // horizontal offset
															// + 50 because
															// element is drawn
															// in the center of
															// the object not in
															// left corner.
					int curX = 0 + horOffset;
					int curY = 0 + horOffset;

					int counter = 0;

					for (Element e : stands)
					{
						int x = curX;
						int y = curY;

						e.setSize(new Dimension(shapeWidth, shapeHeight));
						e.setPosition(new Vector(curX, curY));
						e.draw(g2);
						curX += (int) (shapeWidth / 1.5) + horOffset;

						if ((counter != 0) && (counter % 2 == 1))
						{
							curY += (int) (shapeHeight / 2) + horOffset;
							curX = 0 + horOffset;
						}

						counter++;
					}
				}

				public void actionPerformed(ActionEvent e)
				{

				}

				public Panel getPanel()
				{
					return this;
				}
			};

			pane.addMouseListener(mouseListenerToolbar);
			pane.addMouseMotionListener(mouseListenerToolbar);

			return new JScrollPane(pane);
		}

		private JScrollPane getStageTab()
		{
			Panel pane = new Panel()
			{

				@Override
				protected void paintComponent(Graphics g)
				{
					super.paintComponent(g);
					Graphics2D g2 = (Graphics2D) g;

					Rectangle2D rectangle = new Rectangle2D.Double(1, 1, 50, 50);

					int shapeWidth = 100;
					int shapeHeight = 100;

					int horOffset = 10 + (shapeWidth / 2); // horizontal offset
															// + 50 because
															// element is drawn
															// in the center of
															// the object not in
															// left corner.
					int curX = 0 + horOffset;
					int curY = 0 + horOffset;

					int counter = 0;

					for (Stage s : FestivalHandler.Instance().getStages())
					{
						if (!FestivalHandler.Instance().getElementsOnTerrain()
								.contains(s))
						{
							int x = curX;
							int y = curY;

							// g2.fill(new Rectangle2D.Double(curX, curY,
							// shapeWidth, shapeHeight));
							s.setSize(new Dimension(shapeWidth, shapeHeight));
							s.setPosition(new Vector(curX, curY));
							s.draw(g2);

							curX += (int) (shapeWidth / 1.5) + horOffset;

							if ((counter != 0) && (counter % 2 == 1))
							{
								curY += (int) (shapeHeight / 2) + horOffset;
								curX = 0 + horOffset;
							}

							counter++;
						}
					}

				}

				@Override
				public void actionPerformed(ActionEvent e)
				{
					repaint();
				}

				@Override
				public Panel getPanel()
				{
					// TODO Auto-generated method stub
					return null;
				}
			};

			pane.addMouseListener(mouseListenerToolbar);
			pane.addMouseMotionListener(mouseListenerToolbar);
			pane.startTimer(20);

			// TODO Fix the scrollpane to actually do something.
			JScrollPane scrollPane = new JScrollPane(pane);
			return scrollPane;
		}

		private JScrollPane getRemainingTab()
		{
			final List<Element> remaining = FestivalHandler.Instance()
					.getRemaining();
			FestivalHandler.Instance().addRemaining(
					new Signpost(new Dimension(100, 100), new Vector(0, 0)));

			Panel pane = new Panel()
			{
				@Override
				protected void paintComponent(Graphics g)
				{
					super.paintComponent(g);
					Graphics2D g2 = (Graphics2D) g;
					int shapeWidth = 100;
					int shapeHeight = 100;

					int horOffset = 10 + (shapeWidth / 2); // horizontal offset
															// + 50 because
															// element is drawn
															// in the center of
															// the object not in
															// left corner.
					int curX = 0 + horOffset;
					int curY = 0 + horOffset;

					int counter = 0;

					for (Element e : remaining)
					{
						int x = curX;
						int y = curY;

						e.setSize(new Dimension(shapeWidth, shapeHeight));
						e.setPosition(new Vector(curX, curY));
						e.draw(g2);
						curX += (int) (shapeWidth / 1.5) + horOffset;

						if ((counter != 0) && (counter % 2 == 1))
						{
							curY += (int) (shapeHeight / 2) + horOffset;
							curX = 0 + horOffset;
						}

						counter++;
					}
				}

				public void actionPerformed(ActionEvent e)
				{

				}

				public Panel getPanel()
				{
					return this;
				}
			};

			pane.addMouseListener(mouseListenerToolbar);
			pane.addMouseMotionListener(mouseListenerToolbar);

			return new JScrollPane(pane);
		}

		private void listenForChanges(JSCAccordion accordion)
		{
			accordion.addAccordionListener(new AccordionListener()
			{
				@Override
				public void accordionChanged(AccordionEvent accordionEvent)
				{
					switch (accordionEvent.getEventType()) {
					case TAB_ADDED:
						// add your logic here to react to a tab being added.
						break;
					case TAB_REMOVED:
						// add your logic here to react to a tab being removed.
						break;
					case TAB_SELECTED:
						// add your logic here to react to a tab being selected.
						selectedAccordionTab = accordionEvent.getTabIndex();
						System.out
								.println("SimulatorPanel.l373: selectedAccordionTab: "
										+ selectedAccordionTab);
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
		 * The easiest way to customize a AccordionUI is to change the default
		 * Background Painter, AccordionTabRenderers or tweak values on the
		 * currently installed Background Painter, AccordionTabRenderers and UI.
		 * 
		 * @param accordion
		 */
		private void customizeALookAndFeel(JSCAccordion accordion)
		{
			// example of changing a value on the ui.
			DarkSteelAccordionUI ui = (DarkSteelAccordionUI) accordion.getUI();
			ui.setHorizontalBackgroundPadding(10);

			// example of changing the AccordionTabRenderer
			DarkSteelHorizontalTabRenderer tabRenderer = new DarkSteelHorizontalTabRenderer(
					accordion);
			// tabRenderer.setFontColor(Color.RED);
			accordion.setHorizontalAccordionTabRenderer(tabRenderer);

			// example of changing the background painter.
			GradientColorPainter backgroundPainter = (GradientColorPainter) accordion
					.getBackgroundPainter();
			backgroundPainter = (GradientColorPainter) accordion
					.getBackgroundPainter();
			backgroundPainter.setStartColor(Color.BLACK);
			backgroundPainter.setEndColor(Color.WHITE);
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub

		}
	}

	public class Simulator extends JPanel implements ActionListener
	{
		private Image _grassTexture;
		private Dimension _size;

		public Dimension getSize()
		{
			return this._size;
		}

		public Simulator(Dimension dim)
		{
			setPreferredSize(dim);
			this._size = dim;
			addMouseMotionListener(mouseListener);
			addMouseListener(mouseListener);			
			try
			{
				_grassTexture = new ImageIcon("bin/grass.png").getImage();

				System.out.println(_grassTexture);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			Timer timer = new Timer(30, this);
			timer.start();

		}

		private boolean peopleOnField()
		{
			for (Element p : FestivalHandler.Instance().getElementsOnTerrain())
			{
				if (p instanceof People)
					return true;
			}
			return false;
		}

		@Override
		public void actionPerformed(ActionEvent event)
		{
			FestivalHandler.Instance().updateSimulator();
			repaint();
		}

		/**
		 * draws all the elements to the simulator screen
		 * 
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
			for (int y = 0; y <= (this.getHeight() / imageHeight); y++)
			{
				int curX = 0;
				for (int x = 0; x <= (this.getWidth() / imageWidth); x++)
				{
					g2.drawImage(_grassTexture, curX, curY, imageWidth,
							imageHeight, null);
					curX += imageWidth;
				}
				curY += imageHeight;
			}

			// end drawing background
			// Draw all the elements to the screen.
			for (Element e : FestivalHandler.Instance().getElementsOnTerrain())
			{
				e.draw(g2);
			}
			// end drawing allthe elements to the screen.
			
			// begin draw recycle bin
			recyclebin.draw(g2);
			// end draw recycle bin
		}
	}

	public class MouseListener extends MouseAdapter implements
			MouseMotionListener
	{
		/**
		 * Override the mouseDragged event to check either the elements on the
		 * simulatorTerrain
		 * 
		 * @Author Michiel & Jack
		 */
		@Override
		public void mouseDragged(MouseEvent e)
		{
			boolean debugMethod = false;

			if (debugMethod)
				System.out.println("Dragged: X: " + e.getPoint().getX()
						+ " Y: " + e.getPoint().getY());

			boolean hasDragged = false;
			for (Element element : FestivalHandler.Instance()
					.getElementsOnTerrain())
			{
				if (element instanceof Area && element.contains(e.getPoint()))
				{
					if (debugMethod)
						System.out.println("Element was dragged!");

					if (!hasDragged) // stops the multiple item drag bug.
						element.drag(e.getPoint());

					recyclebin.setElementInHand(element); // save a reference of the element in hand
					recyclebin.beingTouched(recyclebin.contains(e.getPoint())); // Tells the recyclebin weather is being touched or not
					recyclebin.display();
					
					hasDragged = true;
				}
				else
				{
					recyclebin.setElementInHand(null);
				}
			}

		}

		/**
		 * Override the MouMoved event to check either the elements on the
		 * simulatorTerrain
		 * 
		 * @Author Michiel & Jack
		 */
		@Override
		public void mouseMoved(MouseEvent e)
		{
			boolean debugMethod = false;

			if (debugMethod)
				System.out.println("X: " + e.getPoint().getX() + " Y: "
						+ e.getPoint().getY());

			// for all the elements on the terrain check if it is hovered by the
			// mouse.
			for (Element element : FestivalHandler.Instance()
					.getElementsOnTerrain())
			{
				if (element.contains(e.getPoint()))
				{
					if (debugMethod)
						System.out.println("Element was hovered!");

					if (element instanceof Building)
						((Building) element).setHovered(true);
				}
				else
				{
					if (element instanceof Building)
						((Building) element).setHovered(false);
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e)
		{
			boolean debugMethod = true;
			if (debugMethod)
				System.out.println("CLICKED");

			for (Element element : FestivalHandler.Instance()
					.getElementsOnTerrain())
			{
				if (element instanceof Signpost & element.contains(e.getPoint()))
				{
					if (debugMethod)
						System.out.println("Signpost have been clicked! -"  + element.toString());

					new IntersectionOptions(element);
				}

				if (element instanceof Building
						&& ((Building) element).getRotationBox().contains(
								e.getPoint()))
				{
					element.rotate();
				}
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e)
		{
			if(recyclebin.contains(e.getPoint()) )
			{
				FestivalHandler.Instance().removeElementFromTerrain(recyclebin.getElementInHand());
			}
			recyclebin.setElementInHand(null);
			recyclebin.hide();
		}
	}

	public class MouseListenerToolbar extends MouseAdapter implements
			MouseMotionListener
	{

		@Override
		public void mouseDragged(MouseEvent e)
		{

			switch (selectedAccordionTab) {

			case 1: // stage tab!
				dragStageTab(e);
				break;

			case 2: // Stand/stalls Tab!
				dragStandTab(e);
				break;

			case 3: // Facilities tab!
				dragFacilityTab(e);
				break;

			case 4: // Remaining tab!
				dragRemainingTab(e);
				break;
			}
		}

		@Override
		public void mouseMoved(MouseEvent e)
		{
			boolean debugMethod = false;

			for (Stage s : FestivalHandler.Instance().getStages())
			{
				Point point = e.getPoint();

				if (s.contains(point))
				{
					if (debugMethod)
						System.out.println("ShapeHovered!!!!!");
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			boolean debugMethod = false;

			int xOffset = 740;
			int yOffset = 100 + 20 * selectedAccordionTab; // calculates the
															// yOffset with use
															// of the
															// selectedaccordiontab

			if (debugMethod && elementDraggedFromToolbar != null)
				System.out.println("MouseReleased! and Element :"
						+ ((Stage) elementDraggedFromToolbar).getName()
						+ "xAndY" + e.getX() + " " + e.getY());

			if (!FestivalHandler.Instance().getElementsOnTerrain()
					.contains(elementDraggedFromToolbar)
					&& elementDraggedFromToolbar != null
					&& e.getPoint().getX() < 0)
			{
				if (elementDraggedFromToolbar instanceof Stage)
				{
					elementDraggedFromToolbar.setPosition(new Vector(xOffset
							+ e.getX(), yOffset + e.getY()));
					FestivalHandler.Instance().addElementToTerrain(
							elementDraggedFromToolbar);
					elementDraggedFromToolbar = null;
				}
				else
				{
					elementDraggedFromToolbar.setPosition(new Vector(xOffset
							+ e.getX(), yOffset + e.getY()));
					try
					{
						FestivalHandler.Instance().addElementToTerrain(
								(Element) elementDraggedFromToolbar.clone());
					}
					catch (CloneNotSupportedException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					elementDraggedFromToolbar = null;
				}
			}
		}

		private void dragStageTab(MouseEvent e)
		{
			boolean debugMethod = false;

			boolean hasDragged = false;
			for (Stage s : FestivalHandler.Instance().getStages())
			{
				Point point = e.getPoint();

				if (s.contains(point))
				{
					if (debugMethod)
						System.out.println("Stage in toolbar was dragged!");

					if (!hasDragged) // stops the multiple item drag bug.
					{
						if (debugMethod)
							System.out.println("HAI" + s.getName());

						elementDraggedFromToolbar = s;
					}

					hasDragged = true;
				}
			}
		}

		private void dragStandTab(MouseEvent e)
		{
			boolean debugMethod = false;

			boolean hasDragged = false;
			for (Stage s : FestivalHandler.Instance().getStages())

				if (debugMethod)
				{
					System.out.println("dragStandTab method has been called");
					System.out.println("SimPanL699: " + e.getPoint());
				}

			for (Element s : FestivalHandler.Instance().getStands())
			{
				Point point = e.getPoint();

				if (s.contains(point))
				{
					if (debugMethod)
						System.out.println("ShapeHovered!!!!!");

					System.out.println("Element in toolbar was dragged!");

					if (!hasDragged) // stops the multiple item drag bug.
					{
						if (debugMethod)
							System.out.println("Element on toolbar what?");

						elementDraggedFromToolbar = s;
					}
					hasDragged = true;

				}
			}
		}

		private void dragFacilityTab(MouseEvent e)
		{
			boolean debugMethod = true;

			if (debugMethod)
			{
				System.out.println("dragFacility method has been called");
				System.out.println("SimPanL732: " + e.getPoint());
			}

			boolean hasDragged = false;
			for (Element s : FestivalHandler.Instance().getFacilities())
			{
				Point point = e.getPoint();

				if (s.contains(point))
				{
					if (debugMethod)
						System.out.println("Element in toolbar was dragged!");

					if (!hasDragged) // stops the multiple item drag bug.
					{
						if (debugMethod)
							System.out.println("Element on toolbar what?");

						elementDraggedFromToolbar = s;
					}

					hasDragged = true;
				}
			}
		}
		
		private void dragRemainingTab(MouseEvent e)
		{
			boolean debugMethod = true;

			if (debugMethod)
			{
				System.out.println("dragRemaining method has been called");
				System.out.println("SimPanL753: " + e.getPoint());
			}

			boolean hasDragged = false;
			for (Element s : FestivalHandler.Instance().getRemaining())
			{
				Point point = e.getPoint();

				if (s.contains(point))
				{
					if (debugMethod)
						System.out.println("Element in toolbar was dragged!");

					if (!hasDragged) // stops the multiple item drag bug.
					{
						if (debugMethod)
							System.out.println("Element on toolbar what?");

						elementDraggedFromToolbar = s;
					}

					hasDragged = true;
				}
			}
		}

	}
}
