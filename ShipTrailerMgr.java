import java.util.Random;
import javax.swing.JPanel;

public class ShipTrailerMgr {

    public Random rand = new Random();
    
    public enum tipe {
        LINEX,
        LINEY
    }
    
    public tipe Taipe = tipe.LINEY;    
    
    public TriMaker tm = null;
    
    public ShipTrailerMgr(JPanel panel) {
        
        this.tm = new TriMaker( panel);
    }
    
    public void doi(JPanel panel, int x, int y) {
        
        for(int xx=0; xx<4; ++xx) {
            
            int vv = rand.nextInt(40) - rand.nextInt(41);
            
            int ww = rand.nextInt(30) - rand.nextInt(32);
            
            tm.draw(panel, x+vv, y+ww);
        }
    }
}