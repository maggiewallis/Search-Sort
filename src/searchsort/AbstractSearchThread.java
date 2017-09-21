
package searchsort;

import javax.swing.JOptionPane;

public abstract class AbstractSearchThread extends AbstractSearchSortThread {

    protected SortableBar searchTarget;

    public AbstractSearchThread(String name, BarArray bars, StatsThread stats, ThreadCompletedDelegate del, double searchValue)
    {
        super(name, bars, stats,del);
        System.out.println("Searching for: "+SortableBar.makeTargetSortableBar(searchValue));
        searchTarget = SortableBar.makeTargetSortableBar(searchValue);
    }

    
    public void announceFoundBar(SortableBar bar, int index)
    {
        statsThread.stopCheckingStats();

        JOptionPane.showMessageDialog(null, "Found bar:\n"+bar.getDescription()+"\nat index:\n"+index);
    }

    public void announceMissedBar()
    {
        statsThread.stopCheckingStats();
                
        JOptionPane.showMessageDialog(null, "Could not find bar.");
    }
}
