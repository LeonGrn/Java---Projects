package HW1_LeonGrinshpun;//316226679 leon grinshpun

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class HW1LeonGrinshpun extends Application
{	
	public static void main(String[] args)
    {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception
	{	
		AddressBookPane pane = new AddressBookPane();
		Scene scene = new Scene(pane);
		scene.getStylesheets().add("styles.css");
		primaryStage.setTitle("AddressBook");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setAlwaysOnTop(true);
	}
}
class AddressBookPane extends GridPane
{ 
	private RandomAccessFile raf;
  // Text fields
  private TextField jtfName = new TextField();
  private TextField jtfStreet = new TextField();
  private TextField jtfCity = new TextField();
  private TextField jtfState = new TextField();
  private TextField jtfZip = new TextField();
  
  // Buttons
  private AddButton jbtAdd;
  private FirstButton jbtFirst;
  private NextButton jbtNext;
  private PreviousButton jbtPrevious;
  private LastButton jbtLast;
  private Sort1Button jbtSort1;
  private Sort2Button jbtSort2;
  private IterButton jbtIter;
  
  public EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>()
  {	
	 public void handle(ActionEvent arg0)
    {
	  ((Command) arg0.getSource()).Execute();
	}
  };
  public AddressBookPane()
  { // Open or create a random access file
 	try
 	{ 
 		raf = new RandomAccessFile("address.dat", "rw");
	} 
 	catch (IOException ex)
 	{ 
 		System.out.print("Error: " + ex);
	  System.exit(0);
	}
 	
	jtfState.setAlignment(Pos.CENTER_LEFT);
	jtfState.setPrefWidth(25);
	jtfZip.setPrefWidth(60);
	jbtAdd = new AddButton(this, raf);
	jbtFirst = new FirstButton(this, raf);
	jbtNext = new NextButton(this, raf);
	jbtPrevious = new PreviousButton(this, raf);
	jbtLast = new LastButton(this, raf);
	jbtSort1 = new Sort1Button(this, raf);
	jbtSort2 = new Sort2Button(this , raf);
	jbtIter = new IterButton(this , raf);
	
	Label state = new Label("State");
	Label zp = new Label("Zip");
	Label name = new Label("Name");
	Label street = new Label("Street");
	Label city = new Label("City");		
	
	// Panel p1 for holding labels Name, Street, and City
	GridPane p1 = new GridPane();
	p1.add(name, 0, 0);
	p1.add(street, 0, 1);
	p1.add(city, 0, 2);
	p1.setAlignment(Pos.CENTER_LEFT);
	p1.setVgap(8);
	p1.setPadding(new Insets(0, 2, 0, 2));
	GridPane.setVgrow(name, Priority.ALWAYS);
	GridPane.setVgrow(street, Priority.ALWAYS);
	GridPane.setVgrow(city, Priority.ALWAYS);
	
	// City Row
	GridPane adP = new GridPane();
	adP.add(jtfCity, 0, 0);
	adP.add(state, 1, 0);
	adP.add(jtfState, 2, 0);
	adP.add(zp, 3, 0);
	adP.add(jtfZip, 4, 0);
	adP.setAlignment(Pos.CENTER_LEFT);
	GridPane.setHgrow(jtfCity, Priority.ALWAYS);
	GridPane.setVgrow(jtfCity, Priority.ALWAYS);
	GridPane.setHgrow(jtfState, Priority.ALWAYS);
	GridPane.setVgrow(jtfState, Priority.ALWAYS);
	GridPane.setVgrow(jtfZip, Priority.ALWAYS);
	GridPane.setVgrow(state, Priority.ALWAYS);
	GridPane.setVgrow(zp, Priority.ALWAYS);
	
	// Panel p4 for holding jtfName, jtfStreet, and p3
	GridPane p4 = new GridPane();
	p4.add(jtfName, 0, 0);
	p4.add(jtfStreet, 0, 1);
	p4.add(adP, 0, 2);
	p4.setVgap(1);
	GridPane.setHgrow(jtfName, Priority.ALWAYS);
	GridPane.setHgrow(jtfStreet, Priority.ALWAYS);
	GridPane.setHgrow(adP, Priority.ALWAYS);
	GridPane.setVgrow(jtfName, Priority.ALWAYS);
	GridPane.setVgrow(jtfStreet, Priority.ALWAYS);
	GridPane.setVgrow(adP, Priority.ALWAYS);
	
	// Place p1 and p4 into jpAddress
	GridPane jpAddress = new GridPane();
	jpAddress.add(p1, 0, 0);
	jpAddress.add(p4, 1, 0);
	GridPane.setHgrow(p1, Priority.NEVER);
	GridPane.setHgrow(p4, Priority.ALWAYS);
	GridPane.setVgrow(p1, Priority.ALWAYS);
	GridPane.setVgrow(p4, Priority.ALWAYS);
	
	// Set the panel with line border
	jpAddress.setStyle("-fx-border-color: grey;"
			+ " -fx-border-width: 1;"
			+ " -fx-border-style: solid outside ;");
	
	// Add buttons to a panel
	FlowPane jpButton = new FlowPane();
	jpButton.setHgap(6);
	jpButton.getChildren().addAll(jbtAdd, jbtFirst, 
		jbtNext, jbtPrevious, jbtLast , jbtSort1 , jbtSort2 , jbtIter);
	jpButton.setAlignment(Pos.CENTER);
	GridPane.setVgrow(jpButton, Priority.NEVER);
	GridPane.setVgrow(jpAddress, Priority.ALWAYS);
	GridPane.setHgrow(jpButton, Priority.ALWAYS);
	GridPane.setHgrow(jpAddress, Priority.ALWAYS);

	
	// Add jpAddress and jpButton to the stage
	this.setVgap(5);
	this.add(jpAddress, 0, 0);
	this.add(jpButton, 0, 1);
	jbtAdd.setOnAction(ae);
	jbtFirst.setOnAction(ae);
	jbtNext.setOnAction(ae);
	jbtPrevious.setOnAction(ae);
	jbtSort1.setOnAction(ae);
	jbtSort2.setOnAction(ae);
	jbtIter.setOnAction(ae);
	jbtLast.setOnAction(ae);
	jbtFirst.Execute();
  }
	public void actionHandled(ActionEvent e)
	{ 
		((Command) e.getSource()).Execute();
	}
	public void SetName(String text)
	{ 
		jtfName.setText(text);
	}
	public void SetStreet(String text)
	{ 
		jtfStreet.setText(text);
	}
	public void SetCity(String text)
	{ 
		jtfCity.setText(text);
	}
	public void SetState(String text)
	{ 
		jtfState.setText(text);
	}
	public void SetZip(String text)
	{ 
		jtfZip.setText(text);
	}
	public String GetName()
	{ 
		return jtfName.getText();
	}
	public String GetStreet()
	{ 
		return jtfStreet.getText();
	}
	public String GetCity()
	{ 
		return jtfCity.getText();
	}
	public String GetState()
	{ 
		return jtfState.getText();
	}
	public String GetZip()
	{ 
		return jtfZip.getText();
	}
}

interface Command
{
	public void Execute();
}

class CommandButton extends Button implements Command
{
  public final static int NAME_SIZE = 32;
  public final static int STREET_SIZE = 32;
  public final static int CITY_SIZE = 20;
  public final static int STATE_SIZE = 10;
  public final static int ZIP_SIZE = 5;
  public final static int RECORD_SIZE = (NAME_SIZE + STREET_SIZE + CITY_SIZE + STATE_SIZE + ZIP_SIZE) * 2;
  protected AddressBookPane p;
  protected RandomAccessFile raf;
  public CommandButton(AddressBookPane pane, RandomAccessFile r)
  {	
	super();
	p = pane;
	raf = r;
  }
  public void Execute()
  {
  }
  
  public void sort(Comparator<AddressInformation> comp) throws IOException
	{
		AddressInformation ai = new AddressInformation();
		AddressInformation ai2 = new AddressInformation();
		int count = 0;
		int count2 = 0;
		int step = RECORD_SIZE;
		while (readAddress(count,ai)) 
		{
			count2 = count + step;
			while (readAddress(count2,ai2)) 
			{
				if(comp.compare(ai, ai2) > 0)
				{
					writeAddress(count,ai2);
					writeAddress(count2,ai);
					readAddress(count,ai);
				}
				count2 += step;
			}
			count += step;				
		}
	}
  
  /** Write a record at the end of the file */
  public void writeAddress()
  {	
	  try
    { 
		  raf.seek(raf.length());
	  FixedLengthStringIO.writeFixedLengthString(p.GetName(), NAME_SIZE, raf);
	  FixedLengthStringIO.writeFixedLengthString(p.GetStreet(), STREET_SIZE, raf);
	  FixedLengthStringIO.writeFixedLengthString(p.GetCity(), CITY_SIZE, raf);
	  FixedLengthStringIO.writeFixedLengthString(p.GetState(), STATE_SIZE, raf);
	  FixedLengthStringIO.writeFixedLengthString(p.GetZip(), ZIP_SIZE, raf);
	 } 
     catch (IOException ex)
     { 
    	 ex.printStackTrace();
	 }
   }
  
  /** Write a record at the end of the file */
  public void writeAddress(long position, AddressInformation ai)
  {	
	  try
    { 
	  raf.seek(position);
	  FixedLengthStringIO.writeFixedLengthString(ai.getName(), NAME_SIZE, raf);
	  FixedLengthStringIO.writeFixedLengthString(ai.getStreet(), STREET_SIZE, raf);
	  FixedLengthStringIO.writeFixedLengthString(ai.getCity(), CITY_SIZE, raf);
	  FixedLengthStringIO.writeFixedLengthString(ai.getState(), STATE_SIZE, raf);
	  FixedLengthStringIO.writeFixedLengthString(ai.getZip(), ZIP_SIZE, raf);
	 } 
     catch (IOException ex)
     { 
    	 ex.printStackTrace();
	 }
   }
  
   /** Read a record at the specified position */
   public void readAddress(long position) throws IOException
   {
	   raf.seek(position);
	 String name = 
	   FixedLengthStringIO.readFixedLengthString(NAME_SIZE, raf);
	 String street = 
	   FixedLengthStringIO.readFixedLengthString(STREET_SIZE, raf);
  	 String city = 
  	   FixedLengthStringIO.readFixedLengthString(CITY_SIZE, raf);
	 String state = 
	   FixedLengthStringIO.readFixedLengthString(STATE_SIZE, raf);
  	 String zip = 
  	   FixedLengthStringIO.readFixedLengthString(ZIP_SIZE, raf);
	 p.SetName(name);
	 p.SetStreet(street);
	 p.SetCity(city);
	 p.SetState(state);
	 p.SetZip(zip);
	}
   
   public boolean readAddress(long position, AddressInformation ai) throws IOException
   {
	   if(position < raf.length())
	   {
		   raf.seek(position);
		   String name = FixedLengthStringIO.readFixedLengthString(NAME_SIZE, raf);
		   String street = FixedLengthStringIO.readFixedLengthString(STREET_SIZE, raf);
	  	 	String city = FixedLengthStringIO.readFixedLengthString(CITY_SIZE, raf);
	  	 	String state = FixedLengthStringIO.readFixedLengthString(STATE_SIZE, raf);
	  	 	String zip = FixedLengthStringIO.readFixedLengthString(ZIP_SIZE, raf);
	  	 	ai.setName(name);
	  	 	ai.setStreet(street);
	  	 	ai.setCity(city);
	  	 	ai.setState(state);
	  	 	ai.setZip(zip);
	  	 	return true;   
	   }
	   return false;
	}
}
class AddButton extends CommandButton
{ 
	public AddButton(AddressBookPane pane, RandomAccessFile r)
  {	
		super(pane, r);
	this.setText("Add");
  }
  @Override
  public void Execute()
  {	
	  writeAddress();
  }
}
class NextButton extends CommandButton
{ 
	public NextButton(AddressBookPane pane, RandomAccessFile r)
  {	
		super(pane, r);
	this.setText("Next");
  }
  @Override
  public void Execute()
  {
	  try
    { 
	  long currentPosition = raf.getFilePointer();
	  if (currentPosition < raf.length())
		readAddress(currentPosition);
	} 
    catch (IOException ex)
    { 
    	ex.printStackTrace();
	}
  }
}
class PreviousButton extends CommandButton
{ 
	public PreviousButton(AddressBookPane pane, RandomAccessFile r)
  {	
		super(pane, r);
	this.setText("Previous");
  }
  @Override
  public void Execute()
  {	
	  try
    { 
		  long currentPosition = raf.getFilePointer();
	  if (currentPosition - 2 * RECORD_SIZE >= 0)
	 	    readAddress(currentPosition - 2 * RECORD_SIZE);
	  else;
	  } 
      catch (IOException ex)
      {	
    	  ex.printStackTrace();
	  }
	}
  }
class LastButton extends CommandButton
{ 
	public LastButton(AddressBookPane pane, RandomAccessFile r)
  {	
		super(pane, r);
	this.setText("Last");
  }
  @Override
  public void Execute()
  {	
	 try
    { 
	 long lastPosition = raf.length();
	  if (lastPosition > 0)
		readAddress(lastPosition - RECORD_SIZE);
	} 
    catch (IOException ex)
    { 
    	ex.printStackTrace();
	}
   }
}
class FirstButton extends CommandButton
{ 
	public FirstButton(AddressBookPane pane, RandomAccessFile r)
  {	
	super(pane, r);
	this.setText("First");
  }
  @Override
  public void Execute()
  {
	  try
    { 
		  if (raf.length() > 0) readAddress(0);
	} 
    catch (IOException ex)
    { 
    	ex.printStackTrace();
	}
  }
}
class Sort1Button extends CommandButton //my button added need to check try and catch
{ 
	public Sort1Button(AddressBookPane pane, RandomAccessFile r)
	{	
		super(pane, r);
		this.setText("Sort1");
	}
	@Override
	public void Execute()
	{
		try 
		{
			sort(new NameComparator());
	    } 
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	    }
	}
	
}
class Sort2Button extends CommandButton 
{ 
	public Sort2Button(AddressBookPane pane, RandomAccessFile r)
  {	
	super(pane, r);
	this.setText("Sort2");
  }
  @Override
  public void Execute()
  {
	  try 
		{
			sort(new ZipComparator());
	    } 
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	    }
  }
}



class IterButton extends CommandButton //my button added need to check try and catch
{ 
	private boolean hasClicked = false;
	private Map<AddressInformation, AddressInformation> iterMap = new LinkedHashMap<>();
	private MyListIterator lit = null;
	
	public IterButton(AddressBookPane pane, RandomAccessFile r)
	{	
		super(pane, r);
		this.setText("Iter");
		lit = new MyListIterator(r);
	}
	@Override
	public void Execute()
	{
		if(!hasClicked)
		{
			updateMap();
		}
		else
		{
			try 
			{
				mapToTreeSet(lit, iterMap);
			} 
			catch (IOException e)
			{
			e.printStackTrace();
			}		 
		}
	}
  
	public void updateMap()
	{
		while(lit.hasNext())
		{
			AddressInformation a1 = lit.next();
			iterMap.put(a1, a1);
		}
		lit.clear();
		for(AddressInformation temp : iterMap.values())
		{
			lit.add(temp);
		}
		hasClicked = true;
	}
  

	private void mapToTreeSet(MyListIterator lit, Map<AddressInformation, AddressInformation> iterMap) throws IOException 
	{
		updateMap();
		Set<AddressInformation> treeSet = new TreeSet<>(new StreetComparator());
		treeSet.addAll(iterMap.values());
		lit.clear();
		for (AddressInformation addressTemp : treeSet)
			lit.add(addressTemp);
	}
  
	public class MyListIterator implements ListIterator<AddressInformation>
	{
  	RandomAccessFile raf = null;
  	int m_currentIndex = 0;
  	int getout = 0;
    
  	public MyListIterator(RandomAccessFile raf)
  	{
  		this.raf = raf;
    }
  	
  	public MyListIterator()
  	{
  		m_currentIndex = 0;
  	}
  	  	
  	@Override
  	public void add(AddressInformation e) 
  	{
  		{
  	  		int temp = m_currentIndex;
			ArrayList<AddressInformation> addrestList = new ArrayList<>();
			boolean nextAd = true;
			try {
				while (nextAd)
				{
					AddressInformation ai = new AddressInformation();
					nextAd = readAddress(temp * RECORD_SIZE, ai);
					if(nextAd == true)
						addrestList.add(ai);
					temp++;
				}
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
				
			
	  		if(m_currentIndex < 0)
	  			throw new IndexOutOfBoundsException();
	  		
	  		writeAddress(m_currentIndex * RECORD_SIZE, e);
			for (int i = 0; i < addrestList.size(); i++)
			{
				writeAddress((m_currentIndex + i + 1) * RECORD_SIZE, addrestList.get(i));
			}
			
			m_currentIndex++;
		}
  	}

  	@Override
  	public boolean hasNext() 
  	{
  		try 
  		{
  			return ((m_currentIndex + 1) * RECORD_SIZE < raf.length());
  		} 
  		catch (IOException e) 
  		{
  			e.printStackTrace();
  		}
  		return false;
  	}

  	@Override
  	public boolean hasPrevious() 
  	{
  		return (m_currentIndex > 0);
  	}

  	@Override
  	public AddressInformation next()
  	{
  		if(hasNext() == false)
  			throw new NoSuchElementException();
		AddressInformation element = new AddressInformation();
  		m_currentIndex++;
  		try {
  			readAddress(m_currentIndex * RECORD_SIZE, element);
  			getout = 1;
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		return element;
  	}

  	@Override
  	public int nextIndex()
  	{
  		try {
  			if((m_currentIndex + 1) * RECORD_SIZE >= raf.length())
  				return (int)(raf.length()/RECORD_SIZE - 1);
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}

  		return m_currentIndex + 1;
  	}

  	@Override
  	public AddressInformation previous() 
  	{
  		if(!hasPrevious())
  			throw new NoSuchElementException();
		  	AddressInformation element = new AddressInformation();
  			m_currentIndex--;
  			try {
  				readAddress(m_currentIndex * RECORD_SIZE, element);
  				getout = m_currentIndex ;
  			} catch (IOException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  			return element;
  	}

  	@Override
  	public int previousIndex()
  	{
  		if(hasPrevious() == true)
  			return (m_currentIndex - 1);
  		return -1;
  	}

  	@Override
  	public void remove()
  	{
  		if(getout == -1)
  			throw new IllegalStateException();
  		else 
  		{
	  		try
	  		{
	  			if(m_currentIndex*RECORD_SIZE != raf.length())
	  			{
	  				ArrayList<AddressInformation> temp = new ArrayList<AddressInformation>();
	  				AddressInformation ai = new AddressInformation();
	  				for (int i = 0; i * RECORD_SIZE < raf.length(); i++) {
	  					if(i != m_currentIndex)
	  					{
	  						readAddress(i*RECORD_SIZE, ai);
	  						temp.add(ai);
	  					}					
	  				}
	  				
	  				raf.setLength(0);
	  				for (AddressInformation addressInformation : temp) 
	  				{
	  					add(addressInformation);
	  				}
	  				
	  				if(temp.size() > 0 && m_currentIndex >= temp.size())
	  					m_currentIndex = temp.size() - 1;
	  			}
	  			getout = -1;
	  		} 
	  		catch (IOException e) 
	  		{
	  			e.printStackTrace();
	  		}	
  		}
  	} 	

  	public void clear() 
  	{
		m_currentIndex = 0;
		try {
			raf.setLength(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	}
  	@Override
  	public void set(AddressInformation e) 
  	{
  		if(getout == -1)
  			throw new IllegalStateException();
  		writeAddress(m_currentIndex * RECORD_SIZE, e);
  	}
  }
}
