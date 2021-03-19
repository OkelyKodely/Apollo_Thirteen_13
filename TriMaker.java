import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class TriMaker {
    
    public Graphics g = null;
    
    public Line firstLine;
    public Line secondLine;
    public Line thirdLine;
    
    public Color color = Color.BLACK;
    
    public TriMaker(JPanel panel) {

        g = panel.getGraphics();
        
        firstLine = new Line();
        secondLine = new Line();
        thirdLine = new Line();
    }
    
    public void draw(JPanel panel, int thex, int they) {

        Graphics g = panel.getGraphics();
        g.setColor(this.color);
        
        g.drawLine(thex, they, thex+10, they+0);
        g.drawLine(thex+10, they+0, thex+5, they+8);
        g.drawLine(thex+5, they+8, thex, they);
    }
}