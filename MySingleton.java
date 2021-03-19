
public class MySingleton {

    private static MySingleton instance = null;

    private static boolean episodeOver = false;

    private MySingleton() {}
    
    public static MySingleton getInstance() {
        
        if(null == MySingleton.instance) {
            instance = new MySingleton();
        }
        return instance;
    }
    
    public static boolean getEpisodeOver() {
        return instance.episodeOver;
    }

    public static void setEpisodeOver(boolean isit) {
        instance.episodeOver = isit;
    }
}