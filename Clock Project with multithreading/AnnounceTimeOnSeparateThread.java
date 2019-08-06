import java.applet.*;//Leon Grinshpun 316226679

public class AnnounceTimeOnSeparateThread implements Runnable
{ 
  protected AudioClip[] hourAudio = new AudioClip[12];
  protected AudioClip[] minuteAudio = new AudioClip[60];
  protected AudioClip amAudio = Applet.newAudioClip(this.getClass().getResource("/audio/am.au"));
  protected AudioClip pmAudio = Applet.newAudioClip(this.getClass().getResource("/audio/pm.au"));
  private ClockPane clockPane;

  public AnnounceTimeOnSeparateThread(ClockPane clockpane)
  {
	  this.clockPane = clockpane;
	  init();
  }
  
  /** Initialize the applet */
  public void init()
  { 
	// Create audio clips for pronouncing hours
    for (int i = 0; i < 12; i++)
      hourAudio[i] = Applet.newAudioClip(this.getClass().getResource("/audio/hour" + i + ".au"));
    
    // Create audio clips for pronouncing minutes
    for (int i = 0; i < 60; i++)
      minuteAudio[i] = Applet.newAudioClip(this.getClass().getResource("/audio/minute" + i + ".au"));
    
  }
 
	public void run()
	{
		while (true) 
		{
			if (clockPane.getSecond() == 0) 
			{
				try 
				{

					hourAudio[clockPane.getHour() % 12].play();
					Thread.sleep(1500);
					minuteAudio[clockPane.getMinute()].play();
					Thread.sleep(1500);
				}
				catch (InterruptedException e)
				{

					e.printStackTrace();
				}
				if (clockPane.getHour() < 12)
					amAudio.play();
				else
					pmAudio.play();
			}
			hourAudio[clockPane.getHour() % 12].stop();
			minuteAudio[clockPane.getMinute()].stop();
		}
	} 
}