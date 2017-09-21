package searchsort;

public class BozoSortThread extends AbstractSearchSortThread
{
    

    public BozoSortThread(BarArray barArrayToSort, StatsThread stats, ThreadCompletedDelegate del)
    {
        super("BozoSortThread",barArrayToSort, stats,del);
       

    }
    @Override
    public int executeAlgorithm() throws InterruptedException
    {
        int numBars = mainArray.size();
        
        while (true)
        {
        	int num1 = (int)(Math.random()*numBars);
        	int num2 = (int)(Math.random()*numBars);
        	
        	ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("i", num1);
        	ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("i", num2);
        	
        	mainArray.swap(num1,  num2);
        	checkIn();
        	if (mainArray.isSorted())
        		break;
        }
        
        return STATUS_FINISHED;   
        
    }
}
