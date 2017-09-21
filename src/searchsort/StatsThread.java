
package searchsort;

import java.util.Date;
import javax.swing.JLabel;


public class StatsThread extends Thread
{
    private JLabel getLabel;
    private JLabel setLabel;
    private JLabel compareLabel;
    private JLabel timeLabel;
    
    private Date lastTimeCheck;
    
    private boolean timerIsRunning;
    private long timeCount;
    private long timeOffset;

    private int oldGet, oldSet, oldCompare;
    private long oldTime;


    public StatsThread(JLabel gL, JLabel sL, JLabel cL, JLabel tL)
    {
        super();
        getLabel = gL;
        setLabel = sL;
        compareLabel = cL;
        timeLabel = tL;
        timerIsRunning = false;
        timeCount = 0;
        timeOffset = 0;
        lastTimeCheck = new Date();
    }

    public void run()
    {
        int pause;
       while (true)
       {
           if (timerIsRunning)
           {
               timeCount = timeOffset + (new Date()).getTime()-lastTimeCheck.getTime();
               updateLabels();
           }
           
           try
           {
               sleep(1);
           }
           catch (InterruptedException ie)
           {
               System.out.println(ie.toString());
           }
       }
    }

   
    private void updateLabels()
    {
       if (oldGet!=BarArray.getCount())
       {
           oldGet= BarArray.getCount();
           getLabel.setText(String.format("%010d",oldGet));

       }
       if (oldSet!=BarArray.setCount())
       {
           oldSet = BarArray.setCount();
           setLabel.setText(String.format("%010d",oldSet));
       }
       if (oldCompare!= SortableBar.compareCount())
       {
           oldCompare = SortableBar.compareCount();
           compareLabel.setText(String.format("%010d",SortableBar.compareCount()));
       }
       String result;
       if (oldTime==timeCount)
           return;
       oldTime = timeCount;
       if (timeCount>3600000)
           result = String.format("%d:%02d:%02d.%03d",
                                  timeCount/3600000,
                                  (timeCount/60000)%60,
                                  (timeCount/1000)%60,
                                  (timeCount%1000));
       else
           result = String.format("%02d:%02d.%03d",
                                  (timeCount/60000)%60,
                                  (timeCount/1000)%60,
                                  (timeCount%1000));
       timeLabel.setText(result);

    }

   
    public void beginCheckingStats(boolean shouldReset)
    {
        System.out.println("Beginning to check stats. Resetting counters: "+shouldReset);
        if (shouldReset)
            reset();
        lastTimeCheck = new Date();
        timerIsRunning = true;

    }

   
    public void reset()
    {
        System.out.println("Resetting.");
        BarArray.resetCounts();
        SortableBar.resetCompareCount();
        timeCount = 0;
        lastTimeCheck = new Date();
        timeOffset = 0;
        updateLabels();
    }

    
    public void stopCheckingStats()
    {
        System.out.println("Stopping stats check.");
        timerIsRunning = false;
        timeOffset = timeCount;
    }
}
