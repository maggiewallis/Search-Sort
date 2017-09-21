
package searchsort;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;


public class ActionIndicatorQueue implements Constants {
    private ArrayList<HashMap<String,Integer>> myQueue;
    private HashMap<String,Integer> variableLocations;
    private boolean locked;
    static private ActionIndicatorQueue sharedAIQ;



    private ActionIndicatorQueue()
    {
        super();
        myQueue = new ArrayList<HashMap<String,Integer>>();
        variableLocations = new HashMap<String,Integer>();
        locked = false;

    }

   
    public static ActionIndicatorQueue sharedActionIndicatorQueue()
    {
        if (null == sharedAIQ)
            sharedAIQ = new ActionIndicatorQueue();
        return sharedAIQ;
    }

  
    public synchronized boolean addVariable(String name, int index)
    {
        while (locked)
        {
            try
            {
                wait();
            }
            catch (InterruptedException ie)
            {
                System.out.println(ie);
            }
        }
        boolean response;
        locked = true;
        if (variableLocations.containsKey(name))
        {
            response = false;
        }
        else
            response = true;
        variableLocations.put(name,index);
        locked = false;
        return response;
    }

    private synchronized boolean addMap(HashMap<String, Integer> theMap)
    {
        while (locked)
        {
            try
            {
                wait();
            }
            catch (InterruptedException ie)
            {
                System.out.println(ie);
            }
        }
        locked = true;
        for (int i = 0; i<myQueue.size()-1; i++)
        {
            HashMap<String, Integer> temp = myQueue.get(i+1);
            temp.put(RED_KEY, Math.min(255,temp.get(RED_KEY)+255/MAX_NUM_INDICATORS));
            temp.put(GREEN_KEY, Math.min(255,temp.get(GREEN_KEY)+255/MAX_NUM_INDICATORS));
            temp.put(BLUE_KEY, Math.min(255,temp.get(BLUE_KEY)+255/MAX_NUM_INDICATORS));
            myQueue.set(i,temp);
        }
        boolean result;
        if (myQueue.size()<MAX_NUM_INDICATORS)
            result =  myQueue.add(theMap);
        else
        {
            myQueue.set(MAX_NUM_INDICATORS-1,theMap);
            result = true;
        }
        locked = false;
        return result;
    }

   
    public void clear()
    {
        myQueue.clear();
        variableLocations.clear();
    }
    
    public boolean addGetArrow(int i)
    {
        HashMap<String,Integer> temp = new HashMap<String,Integer>();
        temp.put(TYPE_KEY, ACTION_GET_TYPE);
        temp.put(LOCATION_KEY, i);
        temp.put(RED_KEY, 0);
        temp.put(GREEN_KEY, 128);
        temp.put(BLUE_KEY, 0);

        return addMap(temp);
    }
   
    public boolean addPutArrow(int i)
     {
        HashMap<String,Integer> temp = new HashMap<String,Integer>();
        temp.put(TYPE_KEY, ACTION_PUT_TYPE);
        temp.put(LOCATION_KEY, i);
        temp.put(RED_KEY, 128);
        temp.put(GREEN_KEY, 0);
        temp.put(BLUE_KEY, 0);

        return addMap(temp);
    }
   
    public boolean addSwapArrow(int i, int j)
    {
        if (Math.min(i,j)==Math.max(i, j))
            System.out.println("Recording non-swap event...");
        HashMap<String,Integer> temp = new HashMap<String, Integer>();
        temp.put(TYPE_KEY,ACTION_SWAP_TYPE);
        temp.put(LOCATION_KEY, Math.min(i,j));
        temp.put(LOCATION2_KEY,Math.max(i,j));
        temp.put(RED_KEY, 0);
        temp.put(GREEN_KEY, 0);
        temp.put(BLUE_KEY, 128);
        return addMap(temp);
     }

    public synchronized void drawIndicators(Graphics g, int left, int top, double barWidth)
    {
        while (locked)
        {
            try
            {
                wait();
            }
            catch (InterruptedException ie)
            {
                System.out.println(ie);
            }
        }
        locked = true;
        for (HashMap<String,Integer> temp:myQueue)
        {
            g.setColor(new Color(temp.get(RED_KEY),
                                 temp.get(GREEN_KEY),
                                 temp.get(BLUE_KEY)));
            int x = (int)(left+temp.get(LOCATION_KEY)*barWidth);

            switch(temp.get(TYPE_KEY))
            {
                case ACTION_PUT_TYPE:
                    g.drawLine(x, top, x, top+ACTION_ARROW_HEIGHT);
                    g.drawLine(x-ACTION_ARROWHEAD_SIZE, top+ACTION_ARROWHEAD_SIZE, x, top);
                    g.drawLine(x+ACTION_ARROWHEAD_SIZE, top+ACTION_ARROWHEAD_SIZE, x, top);
                    break;
                case ACTION_GET_TYPE:
                    g.drawLine(x, top, x, top+ACTION_ARROW_HEIGHT);
                    g.drawLine(x-ACTION_ARROWHEAD_SIZE,
                                top+ACTION_ARROW_HEIGHT-ACTION_ARROWHEAD_SIZE,
                                x,
                                top+ACTION_ARROW_HEIGHT);
                    g.drawLine(x+ACTION_ARROWHEAD_SIZE,
                                top+ACTION_ARROW_HEIGHT-ACTION_ARROWHEAD_SIZE,
                                x,
                                top+ACTION_ARROW_HEIGHT);
                    break;
                case ACTION_SWAP_TYPE:
                    int x2 = (int)(left+temp.get(LOCATION2_KEY)*barWidth);
//                    if (x2==x)
//                        System.out.println("Swapping identical points? ("+x+","+x2+")"+temp);
                    g.drawLine(x, top+ACTION_ARROWHEAD_SIZE, x2, top+ACTION_ARROWHEAD_SIZE);
                    g.drawLine(x+ACTION_ARROWHEAD_SIZE, top, x, top+ACTION_ARROWHEAD_SIZE);
                    g.drawLine(x+ACTION_ARROWHEAD_SIZE,
                                top+2*ACTION_ARROWHEAD_SIZE,
                                x,
                                top+ACTION_ARROWHEAD_SIZE);
                    g.drawLine(x2-ACTION_ARROWHEAD_SIZE,
                                top,
                                x2,
                                top+ACTION_ARROWHEAD_SIZE);
                    g.drawLine(x2-ACTION_ARROWHEAD_SIZE, top+2*ACTION_ARROWHEAD_SIZE, x2, top+ACTION_ARROWHEAD_SIZE);
            }


        }
        g.setColor(Color.BLACK);
        for (String s: variableLocations.keySet())
        {
            int x3 = (int)(left+variableLocations.get(s)*barWidth);
            g.drawString(s, x3-2, top+ACTION_ARROW_HEIGHT+10);
        }
        locked = false;

    }

}
