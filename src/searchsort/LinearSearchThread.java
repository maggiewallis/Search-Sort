
package searchsort;


public class LinearSearchThread extends AbstractSearchThread
{
   
    public LinearSearchThread(BarArray array, StatsThread stats, ThreadCompletedDelegate del, double searchValue)
    {
        super("Linear Search",array, stats,del,searchValue);
 
    }

    public int executeAlgorithm() throws InterruptedException
    {
        int len = mainArray.size();
        for (int i=0; i<len; i++)
        {
            ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("i", i);
            if (searchTarget.compareTo(mainArray.get(i))==0)
            {
                announceFoundBar(mainArray.get(i),i);
                return AbstractSearchSortThread.STATUS_FINISHED_FOUND;
            }
            checkIn();
        }
        announceMissedBar();
        return AbstractSearchSortThread.STATUS_FINISHED_FAILED;

    }
}
