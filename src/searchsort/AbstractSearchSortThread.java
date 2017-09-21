
package searchsort;


public abstract class AbstractSearchSortThread extends Thread implements Constants{

    protected boolean isRunning;
    protected boolean isPaused;
    protected boolean startStepping;
    protected StatsThread statsThread;
    private static boolean shouldResetStats;
    private int status;
    private ThreadCompletedDelegate delegate;
    protected BarArray mainArray;

    public AbstractSearchSortThread(String name, BarArray barArray, StatsThread stats, ThreadCompletedDelegate del)
    {
        super(name);
        statsThread = stats;
        delegate = del;
        mainArray = barArray;
    }

   
    public void stepProcess()
    {
          startStepping = true;

    }

    public void cancelProcess()
    {
        isRunning = false;
        delegate.updateStatus(STATUS_CANCELLED);
    }

    
    public void pauseProcess()
    {
        startStepping = false;
        isPaused = true;
        if (null!=statsThread)
        {
            statsThread.stopCheckingStats();
        }
        delegate.updateStatus(STATUS_PAUSED);
    }

   
    public void resumeProcess()
    {
       isPaused = false;
        if (null!=statsThread)
            statsThread.beginCheckingStats(false);
        delegate.updateStatus(STATUS_RUNNING);
    }



    @Override
    
    public void run()
    {
        if (null!= statsThread)
            statsThread.beginCheckingStats(shouldResetStats);
        startStepping = false;
        isPaused = false;
        isRunning = true;
        
        ActionIndicatorQueue.sharedActionIndicatorQueue().clear();
        try
        {
            delegate.updateStatus(STATUS_RUNNING);
            status = executeAlgorithm();
        }
        catch (InterruptedException ie)
        {
            status = STATUS_CANCELLED;
            delegate.threadHasFinished(this.getName(), status);
            return;
        }
        delegate.threadHasFinished(this.getName(), status);
        if (AbstractSearchSortThread.STATUS_FINISHED==status)
        {
            if (mainArray.isSorted())
            {
                delegate.updateStatus(STATUS_SORTED);
                if (mainArray.hasDuplicates())
                    delegate.updateStatus(STATUS_DUPLICATES);
            }
            else
                delegate.updateStatus(STATUS_UNSORTED);
        }

    }

   
    
    public abstract int executeAlgorithm() throws InterruptedException;

    

    public static void setAutoReset(boolean autoReset)
    {
        shouldResetStats = autoReset;
    }

    
    public synchronized void checkIn() throws InterruptedException
    {
       // System.out.println("Checking in.");
        if (!isRunning)
            throw new InterruptedException("Did not complete process.");

        if (isPaused && null!=statsThread)
        {
            statsThread.stopCheckingStats();
        }
        while(isPaused)
        {
            status = STATUS_PAUSED;
            if (startStepping)
            {
                if (null!=statsThread)
                    statsThread.beginCheckingStats(false);
                startStepping = false;
                break;
            }
            
            
            wait(1);
           
            if (!isRunning)
            throw new InterruptedException("Did not complete process.");
        }
        status = STATUS_RUNNING;
       
    }

    public int getStatus()
    {
        return status;
    }
}
