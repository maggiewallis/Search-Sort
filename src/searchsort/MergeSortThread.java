package searchsort;

public class MergeSortThread extends AbstractSearchSortThread
{
    
	int numBars = mainArray.size();
    public MergeSortThread(BarArray barArrayToSort, StatsThread stats, ThreadCompletedDelegate del)
    {
        super("MergeSortThread",barArrayToSort, stats,del);
       

    }
    @Override
    public int executeAlgorithm() throws InterruptedException
    {

    	recursivething(0, mainArray.size()-1);
    	
    	
    	return STATUS_FINISHED;
    }
    
    public void Merge(BarArray x, BarArray y, int start, int end)
    {
    	int i = 0;
    	int j = 0;
    	for (int k = start; k < end + 1; k++)
    	{
    		if (j < y.size() && i < x.size() && x.get(i).compareTo(y.get(j)) < 0)
    		{
    			mainArray.set(k, x.get(i));
    			i = i +1;
    			continue;
    		}
    		if (i >= x.size())
			{
				while (k < end+1)
    			{
					mainArray.set(k, y.get(j));
					j++;
					k++;
    			}	
			}
    		else
    		{
    			if (j < y.size())
    			{
    				mainArray.set(k, y.get(j));
    				j = j +1;
    				continue;
    			}
    			else
    			{
    				while (k < end+1)
        			{
    					mainArray.set(k, x.get(i));
    					i++;
    					k++;
        			}    			
    			}
    		}
    	}
    }
    
    
    public void recursivething(int start, int end)
    {
    	if (start < end)
    	{
    		
    		int mid = (start + end)/2;
    		recursivething(start, mid);
        	recursivething(mid + 1, end);
    		BarArray x = mainArray.subarray(start, mid + 1);
    		BarArray y = mainArray.subarray(mid + 1, end + 1);
        	Merge(x, y, start, end);
    	}
    }

    

}
