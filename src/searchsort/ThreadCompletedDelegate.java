
package searchsort;

public interface ThreadCompletedDelegate {

    public void threadHasFinished(String whichThread, int status);

    public void updateStatus(int s);
}
