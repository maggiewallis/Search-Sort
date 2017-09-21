package searchsort;

public class QuickSortThread extends AbstractSearchSortThread
{
    
	int numBars = mainArray.size();
    public QuickSortThread(BarArray barArrayToSort, StatsThread stats, ThreadCompletedDelegate del)
    {
        super("QuickSortThread",barArrayToSort, stats,del);
       

    }
    @Override
    public int executeAlgorithm() throws InterruptedException
    {

    	recursion(0, mainArray.size()-1);
    	
    	
    	return STATUS_FINISHED;
    } 
 
    public void recursion(int low, int high)
    {
    	if (low >= high)
    	{
    		return;
    	}
    	int i = low;
    	int pivot = high;
    	for (int j = low; j < pivot; j++)
    	{
    		if (mainArray.get(j).compareTo(mainArray.get(pivot)) < 0)
    		{
    			mainArray.swap(i, j);
        		i++;	
    		}
    	}
    	mainArray.swap(pivot, i);
    	recursion (low, i - 1);
    	recursion (i +1, high);
    	
    }
    

}
