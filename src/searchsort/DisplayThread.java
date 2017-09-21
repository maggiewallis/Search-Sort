

package searchsort;

import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class DisplayThread extends Thread
{
    private BarArray mainArray;
    private JPanel targetPanel;
    private BufferedImage doubleBuffer;
    private JLabel barDescriptionLabel;

    public DisplayThread(BarArray ba, JPanel jp, JLabel jl)
    {
        mainArray = ba;
        targetPanel = jp;
        barDescriptionLabel = jl;
        doubleBuffer = null;
    }

    public void setNewBarArray(BarArray ba)
    {
        mainArray = ba;
    }

    @Override
    public void run()
    {
        while(true)
        {
            if (null == doubleBuffer||
                    doubleBuffer.getWidth()!=targetPanel.getWidth()||
                    doubleBuffer.getHeight()!=targetPanel.getHeight())
                makeNewDoubleBuffer();

            else
            {
                mainArray.drawSelf(doubleBuffer);
                targetPanel.getGraphics().drawImage(doubleBuffer, 0, 0, null);
            }
            try
            {
                sleep(5);
            }
            catch (InterruptedException ie)
            {
                System.out.println(ie.toString());
            }
        }

    }
   
    public void makeNewDoubleBuffer()
    {
        if (targetPanel.getWidth()>0 && targetPanel.getHeight()>0)
        {
            doubleBuffer = new BufferedImage(targetPanel.getWidth(), targetPanel.getHeight(), BufferedImage.TYPE_INT_RGB);


        }
    }


}
