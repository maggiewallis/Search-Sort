package searchsort;

import java.util.*;
import java.awt.*;

public class SortableBar implements Comparable, Constants{

    
	private static int Current_Comparison_Type = BAR_COMPARE_BY_TOP;
	private static double Bar_Width;
	private static int Max_Bar_Height;
	private static boolean Invert_Order;
        private static int compareCount;
        private static int compareDelay;
	


	private double myTop;
	private double myBottom;
	private double myRed;
	private double myGreen;
	private double myBlue;
	
 
    public SortableBar()
    {
		myTop = normalize(Math.random());
		myBottom = normalize(Math.random());
		if (myTop<myBottom)
		{   
			double temp = myBottom;
			myBottom = myTop;
			myTop = temp;
		}
		myRed = normalize(Math.random());
		myGreen = normalize(Math.random());
		myBlue = normalize(Math.random());
    }

    public SortableBar(String sourceString)
    {
		StringTokenizer st = new StringTokenizer(sourceString,BAR_FILE_DELIM);
		if (st.countTokens() != 5)
			throw new RuntimeException("Wrong number of tokens in String: "+sourceString);
			
		myBottom =		Double.parseDouble(st.nextToken());
		myTop =      	Double.parseDouble(st.nextToken());
		myRed =			Double.parseDouble(st.nextToken());
		myGreen =       	Double.parseDouble(st.nextToken());
		myBlue =			Double.parseDouble(st.nextToken());
    }
   
    public SortableBar(SortableBar source)
    {
		myBottom = 	source.getBottom();
		myTop = 		source.getTop();
		myRed = 		source.getRed();
		myGreen = 	source.getGreen();
		myBlue = 	source.getBlue();
    }
    
    
    public SortableBar(double inBottom, double inTop, double inRed, double inGreen, double inBlue)
    {
		if ((inTop>=0)&&(inTop<1000))
			myTop = inTop;
		else
			myTop = 999;
		if ((inBottom>=0)&&(inBottom<1000))
			myBottom = inBottom;
		else
			myBottom = 0;
		if (myBottom>myTop)
		{
			double temp = myTop;
			myTop = myBottom;
			myBottom = temp;
		}
		if ((inRed>=0)&&(inRed<1000))
			myRed = inRed;
		else
			myRed = 0;
		if ((inGreen>=0)&&(inGreen<1000))
			myGreen = inGreen;
		else
			myGreen = 0;
		if ((inBlue>=0)&&(inBlue<1000))
			myBlue = inBlue;
		else
			myBlue = 0;
    }
  
    public static SortableBar makeTargetSortableBar(double searchValue)
     {
	 	searchValue*=10;
	 	if (getCurrentComparisonType() == SortableBar.BAR_COMPARE_BY_SIZE)
	 	    return new SortableBar(0.0,searchValue,0.0,0.0,0.0);
	 	return new SortableBar(searchValue,searchValue,searchValue,searchValue, searchValue);
     }

   
    public double normalize(double source)
    {
    		return ((int)(source*1000));	
    }
    
    public static void setCurrentComparisonType(int newType)
    {
		if ((newType>-1)&&(newType<7))
			Current_Comparison_Type = newType;
    }
    
    public static int getCurrentComparisonType()
    {   return Current_Comparison_Type; }
    
    public static void setBarWidth(double inWidth)
    {   if (inWidth>0)
			Bar_Width = inWidth;
		else
			Bar_Width = 1;
    }
    
    public static double getBarWidth()
    {   return Bar_Width;}
    
    public static void setMaxBarHeight(int inMaxHeight)
    {   if (inMaxHeight>0)
			Max_Bar_Height = inMaxHeight;
		else
			Max_Bar_Height = 1;
    }
    
    public static void setInvertOrder(boolean inOrder)
    {   Invert_Order = inOrder; }
    
    // Accessors
    public double getSize()
    {   return myTop-myBottom;}
    
    public double getTop()
    {   return myTop;}
    
    public double getBottom()
    {   return myBottom;}
    
    public double getRed()
    {   return myRed;}
    
    public double getGreen()
    {   return myGreen;}
    
    public double getBlue()
    {   return myBlue;}
    
    public double getBrightness()
    {   int temp = (int)( (myBlue+myGreen+myRed)); 
		temp = temp/3;
		return (double)(temp);
    }
    
    public int compareTo(Object o)
    {
	try
        {
            Thread.sleep(compareDelay);
        }
        catch(InterruptedException ie)
        {
            System.out.println(ie.toString());
        }

        int inversionFactor=1;
        if (Invert_Order)
                inversionFactor=-1;
        if (o == null)
                throw new RuntimeException("Attempted to make a comparison between Sortable Bars, but second parameter was null.");
        compareCount++;
        if (o instanceof SortableBar)
        {
        		SortableBar otherBar = (SortableBar)o;
        		double difference = 0;
                
        		switch (Current_Comparison_Type)
            {
            case BAR_COMPARE_BY_SIZE:
                    difference = (this.getSize()-otherBar.getSize());
                    break;
            case BAR_COMPARE_BY_RED:
                    difference =(this.myRed-otherBar.myRed);
                    break;
            case BAR_COMPARE_BY_GREEN:
                    difference=(this.myGreen-otherBar.myGreen);
                    break;
            case BAR_COMPARE_BY_BLUE:
                    difference = (this.myBlue-otherBar.myBlue);
                    break;
            case BAR_COMPARE_BY_BRIGHTNESS: 
            			difference = (this.getBrightness()-otherBar.getBrightness());
            			break;
            case BAR_COMPARE_BY_TOP:
                		difference=(this.myTop-otherBar.myTop);
                		break;
            case BAR_COMPARE_BY_BOTTOM:
            			difference=(this.myBottom-otherBar.myBottom);
            			break;
            default:
                    difference=(this.getSize()-otherBar.getSize());
                    break;
            }
            return (int)(inversionFactor*difference);
        }
        else
            throw new RuntimeException("Error! Attempted to compare a sortable bar with another class.");

    }
    
    public boolean equals(Object o)
    {
	return (this.compareTo(o) == 0);
    }

    
    public boolean deepEquals(SortableBar other)
    {
        return (this.myTop == other.myTop)&&
               (this.myBottom == other.myBottom) &&
               (this.myRed == other.myRed)&&
               (this.myGreen == other.myGreen)&&
               (this.myBlue == other.myBlue);

    }
    
    public void drawSelfAt(Graphics g, int x, int y)
    {
		Color barColor = new Color((float)(myRed/1000.0),
								(float)(myGreen/1000.0),
								(float)(myBlue/1000.0));
		g.setColor(barColor);
		g.fillRect(x,(int)(y-Max_Bar_Height*myTop/1000.0),
				(int)Bar_Width,(int)(Max_Bar_Height*(getSize())/1000.0));
    }
   
    @Override
    public String toString()
    {
		return myBottom+BAR_FILE_DELIM+myTop+BAR_FILE_DELIM+myRed+BAR_FILE_DELIM+myGreen+BAR_FILE_DELIM+myBlue;
    }
    
    
    private String getPercentage(double source)
    {
        return String.format("%04.1f",source/10.0);
    }
    
    
    public String getDescription()
    {
        String output = "Size: "+getPercentage(getSize())+"%\t";
        output+= " Top: "+getPercentage(myTop)+"%\t";
        output+= " Bottom: "+getPercentage(myBottom)+"%\t";
        output+= " Brightness: "+getPercentage((myRed+myBlue+myGreen)/3)+"%\t";
        output+= " Red: "+getPercentage(myRed)+"%\t";
        output+= " Green: "+getPercentage(myGreen)+"%\t";
        output+= " Blue: "+getPercentage(myBlue)+"%";
        return output;
    }

    
    public static int compareCount()
    {   return compareCount;}

    
    public static void resetCompareCount()
    {   compareCount = 0; }

    
    public static void updateCompareDelay(int del)
    {
        if (del>=0)
            compareDelay=del;
    }

    
    public static String getCompareTypeString()
    {
        return BAR_COMPARE_TYPE_STRINGS[Current_Comparison_Type];
    }
}
