
package searchsort;


public class BubbleSortThread extends AbstractSearchSortThread
{
    

    public BubbleSortThread(BarArray barArrayToSort, StatsThread stats, ThreadCompletedDelegate del)
    {
        super("BubbleSortThread",barArrayToSort, stats,del);
       

    }
    @Override
    public int executeAlgorithm() throws InterruptedException
    {
        int numBars = mainArray.size();
        for (int i=numBars-1; i>0; i--)
        {
            ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("i", i); 
            for (int j=0; j<i; j++)
            {
                ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("j", j); 

                if (mainArray.get(j).compareTo(mainArray.get(j+1))>0)
                {
                    mainArray.swap(j,j+1);
                }
                checkIn(); 
            }
        }
        return STATUS_FINISHED;
    }

}
