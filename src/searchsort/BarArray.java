
package searchsort;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class BarArray implements Constants
{
    private static int setCount; // counts the number of "set/add/put" operations
    private static int getCount; // counts the number of "get" operations

    private static int setDelay; // the number of milliseconds delay per set operation
    private static int getDelay; // the number of milliseconds delay per get operation

    
    private SortableBar[] theBars;
    private boolean locked;
    
    public BarArray()
    {
        super();
        initialize(DEFAULT_BAR_ARRAY_SIZE);
    }

    
    public BarArray(int size)
    {
        super();
        initialize(size);
    }

    
    public BarArray(int size, boolean isEmpty)
    {
        super();
        if (isEmpty)
        {
            initializeEmpty(size);
        }
        else
        {
            initialize(size);
        }
    }

    
    public BarArray(String[] inData)
    {
        int length = inData.length;
        theBars = new SortableBar[length];
        for (int i=0; i<length; i++)
        {
            if (inData[i].equals("null"))
                theBars[i] = null;  // not really necessary to say this, but 
                                    // handy to remember.
            else
                theBars[i] = new SortableBar(inData[i]);
        }
    }
    
   
    
    public int size()
    {   return theBars.length;}

    
    public void set(int index, SortableBar value)
    {
        try
        {
            Thread.sleep(setDelay);
        }
        catch(InterruptedException ie)
        {
            System.out.println(ie.toString());
        }
        modify(index,value);
        ActionIndicatorQueue.sharedActionIndicatorQueue().addPutArrow(index);
    }


    
    public SortableBar get(int index)
    {
        try
        {
            Thread.sleep(getDelay);
        }
        catch(InterruptedException ie)
        {
            System.out.println(ie.toString());
        }
        ActionIndicatorQueue.sharedActionIndicatorQueue().addGetArrow(index);
        return this.acquire(index);
    }

    
    public void swap (int i, int j)
    {
        if (i<0||j<0||i>size()-1||j>size()-1)
            throw new RuntimeException("Index out of bounds. Attempted to swap items "+
                                        i+" and "+j+" in list of length "+size()+".");
        SortableBar temp = get(i);
        set(i,get(j));
        set(j,temp);

        ActionIndicatorQueue.sharedActionIndicatorQueue().addSwapArrow(i,j);
    }

    
    public static int requestGetDelay()
    {
        return getDelay;
    }

    @Override
    public synchronized String toString()
    {
        while (locked)
        try
            {
                wait(1);
            }
            catch (InterruptedException ie)
            {
                System.out.println(ie.toString());
            }
        locked = true;
        String result = ""+size()+"\n";
        for (int i=0; i<size(); i++)
        {
            if (theBars[i]!=null)
                result+=theBars[i].toString()+"\n";
            else
                result+="null\n";
        }
        locked = false;
        return result;
    }

   
    public synchronized BarArray subarray(int start, int end)
    {
        if (end<start)
            throw new RuntimeException("Attempted to get a sublist with length < 0. Start = "+start+" End = "+end);
        if (end<0||start<0||end>size() || start>=size())
            throw new RuntimeException("Attempted to get a sublist with bounds out of range. BarList Length = "+
                    size()+ " start = "+start+" end = "+end);
        
        BarArray result = new BarArray(end-start,true);
        for (int i=start; i<end; i++)
            result.set(i-start,this.get(i));
        return result;
    }

   
    public synchronized BarArray subarray(int start)
    {
        return subarray(start,size()-1);
    }

    
    public synchronized void replaceSubArray(BarArray given, int start)
    {
        if (start+given.size()>this.size())
            throw new RuntimeException("attempted to replaceSubArray in a position that would extend past the end of this BarArray. This BarArray's size() = "+
                    this.size()+". Replacement location: "+start+
                    ". Replacement length = "+given.size()+".");
        for (int i=0; i<given.size(); i++)
            this.set(start+i,given.get(i));
    }

  
    public static void resetCounts()
    {
        setCount = 0;
        getCount = 0;
    }

    
    public static int getCount()
    {
        return getCount;
    }
    
    public static int setCount()
    {
        return setCount;
    }

    
    public static void updateSetDelay(int delay)
    {
        if (delay>=0)
             setDelay = delay;
    }
    
    public static void updateGetDelay(int delay)
    {
        if (delay>=0)
             getDelay = delay;
    }
   
    public String getSelectedBarInfo(int which)
    {
        if (which<0||which>size()-1)
            return "";
        return theBars[which].getDescription();
    }

    
    public synchronized void drawSelf(BufferedImage bi)
    {
        Graphics g = bi.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        int len = size();
        SortableBar.setBarWidth((bi.getWidth()-20.0)/len);
        SortableBar.setMaxBarHeight((int)bi.getHeight()-50);
        int topOfBars = bi.getHeight()-30;
        BarArray barCopy = new BarArray(len,true);
        while (locked)
        try
            {
                wait(1);
            }
            catch (InterruptedException ie)
            {
                System.out.println(ie.toString());
            }
        locked = true;
          
            System.arraycopy(this.theBars, 0, barCopy.theBars, 0, len);
        locked = false;

        locked = false;
        for (int i=0; i<len; i++)
        {
            theBars[i].drawSelfAt(g, 
                                  (int)(10.0+i*SortableBar.getBarWidth()),
                                  topOfBars);
        }

        ActionIndicatorQueue.sharedActionIndicatorQueue().drawIndicators(g, 10, bi.getHeight()-30, SortableBar.getBarWidth());

    }
 
    public synchronized boolean isSorted()
    {
        boolean sorted = true;
        while (locked)
        try
            {
                wait(1);
            }
            catch (InterruptedException ie)
            {
                System.out.println(ie.toString());
            }
        locked = true;
        int len = size();
        for (int i=0; i<len-1; i++)
            if (theBars[i].compareTo(theBars[i+1])>0)
            {
                sorted = false;
                break;
            }
        locked = false;
        return sorted;
    }

   
    public synchronized boolean hasDuplicates()
    {
        boolean dupes = false;
        while (locked)
        try
            {
                wait(1);
            }
            catch (InterruptedException ie)
            {
                System.out.println(ie.toString());
            }
        locked = true;
        int len = size();
        for (int i=0; i<len-1; i++)
            if (theBars[i].deepEquals(theBars[i+1]))
            {
                dupes = true;
                break;
            }
        locked = false;
        return dupes;
    }

    private synchronized void modify (int index, SortableBar value)
    {
        if (index<0 || index>size()-1)
            throw new RuntimeException("Attempted to set SortableBar in a BarArray, but index was out of bounds. BarArray size = "+
                    size()+". Index = "+index+".");
        while (locked)
        try
            {
                wait(1);
            }
            catch (InterruptedException ie)
            {
                System.out.println(ie.toString());
            }
        locked = true;
        theBars[index] = value;
        setCount ++;
        locked = false;
    }
    private synchronized SortableBar acquire(int index)
    {
        while (locked)
        try
            {
                wait(1);
            }
            catch (InterruptedException ie)
            {
                System.out.println(ie.toString());
            }
        locked = true;
        if (index<0||index>size()-1)
            throw new RuntimeException("Attempted to access index "+index+ 
                    " of a BarArray of length "+size()+".");
        SortableBar temp = theBars[index];
        locked = false;
        getCount++;
        return temp;
    }
    private synchronized void initializeEmpty(int size)
    {
        while (locked)
        try
            {
                wait(1);
            }
            catch (InterruptedException ie)
            {
                System.out.println(ie.toString());
            }
        locked = true;
        theBars = new SortableBar[size];
        locked = false;
    }
    private synchronized void initialize(int size)
    {
        System.out.println("initializing the bars "+size);
        while (locked)
        try
            {
                wait(1);
            }
            catch (InterruptedException ie)
            {
                System.out.println(ie.toString());
            }
        locked = true;
        theBars = new SortableBar[size];
        for (int i=0;i<size; i++)
            theBars[i] = new SortableBar();
        locked = false;
    }
    
    
}
