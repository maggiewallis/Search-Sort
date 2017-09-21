package searchsort;

public class BinarySearchThread extends AbstractSearchThread
{
    

    public BinarySearchThread(BarArray array, StatsThread stats, ThreadCompletedDelegate del, double searchValue)
    {
        super("Binary Search",array, stats,del,searchValue);
 
    }

   
    
    @Override
    
    public int executeAlgorithm() throws InterruptedException
    {
    	int start = 0;
    	int end = mainArray.size() - 1;
    	search(start, end);
    	return STATUS_FINISHED;   
    }
    
    public void search(int start, int end) {
        int midpt = (start + end)/2;
        
        if (mainArray.get(midpt).compareTo(searchTarget)<0)
        {
        	search (midpt, end);
        }
        
        if (mainArray.get(midpt).compareTo(searchTarget)>0)
        {
        	search(start, midpt);
        }
        if (mainArray.get(midpt).compareTo(searchTarget)==0)
        {
            announceFoundBar(mainArray.get(midpt),midpt);
            
        }

     
        return;
    }
        
    
}