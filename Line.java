public class Line {
    
    class Point {
        int x, y;
    }    

    Point firstPoint;
    Point secondPoint;
    pelim plim;
    delim dlim;

    public enum delim {
        FIRSTPOINT,
        SECONDPOINT
    }
        
    public enum pelim {
        FIRSTCOORD,
        SECONDCOORD
    }
    
    public Line() {

        dlim = delim.FIRSTPOINT;
        
        firstPoint = new Point();
        firstPoint.x = 0;
        firstPoint.y = 0;

        secondPoint = new Point();
        secondPoint.x = 0;
        secondPoint.y = 0;
    }
    
    public void setPoint(delim dOne, pelim pOne, int value) {
        switch(dOne) {
            case FIRSTPOINT:
                switch(pOne) {
                    case FIRSTCOORD:
                        firstPoint.x = value;
                    case SECONDCOORD:
                        firstPoint.y = value;
                    default:break;
                }
            case SECONDPOINT:
                switch(pOne) {
                    case FIRSTCOORD:
                        firstPoint.y = value;
                    case SECONDCOORD:
                        firstPoint.y = value;
                    default:break;
                }
            default:break;
        }
    }
}