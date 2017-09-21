package searchsort;

public class SelectionSortThread extends AbstractSearchSortThread
{
    

    public SelectionSortThread(BarArray barArrayToSort, StatsThread stats, ThreadCompletedDelegate del)
    {
        super("SelectionSortThread",barArrayToSort, stats,del);
       

    }
    @Override
    public int executeAlgorithm() throws InterruptedException
    {
        int numBars = mainArray.size();
          
        for (int i = 0;i < numBars; i++)
        {
            ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("i", i); 
        	int min = 99;
        	for (int j = i; j < numBars; j++)
        	{
                ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("j", j); 
        		if (mainArray.get(j).compareTo(mainArray.get(min)) < 0)
        			min = j;        		
        		checkIn();
        	}
        	mainArray.swap(i, min);
        }
        
        return STATUS_FINISHED;   
        
    }
}