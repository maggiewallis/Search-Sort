package searchsort;

public class InsertionSortThread extends AbstractSearchSortThread
{
    

    public InsertionSortThread(BarArray barArrayToSort, StatsThread stats, ThreadCompletedDelegate del)
    {
        super("InsertionSortThread",barArrayToSort, stats,del);
       

    }
    @Override
    public int executeAlgorithm() throws InterruptedException
    {
    	int numBars = mainArray.size();
        for (int i= 1; i < numBars; i++)
        {
        	
        	ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("i", i); 
        	SortableBar replace;
        	SortableBar hold;
        	replace = mainArray.get(i);
        	for (int j=0; j < i ; j++)
            {
        		
        		if (mainArray.get(j).compareTo(mainArray.get(i))>0)
        		{
            	hold = mainArray.get(j);
            	mainArray.set(j, replace);
            	replace = hold;
        		}
        		ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("j", j); 
            
                checkIn(); 
            }
        	mainArray.set(i, replace);
        }
        return STATUS_FINISHED;
    }
    

}
