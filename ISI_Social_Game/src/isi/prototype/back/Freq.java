 
/**
 * Abstract class Freq - sed for Links and Jammers
 * 
 * @author Aaron
 * @version 0.1
 */
public abstract class Freq
{
    private double freq;
    
    public Freq()
    {
        this((int) (Math.random() * World.MAX_FREQ));
    }
    
    public Freq(int num)
    {
        freq = num;
    }
    
    /**
     * @param  num   the number you want to change the link's frequency to.
     * @return       what the frequency has now been set as. 
     */
    public double setFreq(int num)
    {
        freq = num % World.MAX_FREQ;
        return freq;
    }
    
    public double getFreq() {
        return freq;
    }

}
