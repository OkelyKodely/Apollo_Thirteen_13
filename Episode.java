
public class Episode {

    Designer designer = null;
    
    Runnable r = null;
    
    public boolean firstTime = true;
    
    public boolean episodeOver = false;
    
    public Episode(Runnable r) {
        this.r = r;
    }
    
    public void setDesigner(Designer designer) {
        this.designer = designer;
    }
    
    public void run() {
        Thread t = new Thread() {
            public void run() {
                while(!episodeOver) {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while(true) {
                                try {
                                    r.run();
                                    if(MySingleton.getInstance().getEpisodeOver()) {
                                        episodeOver = true;
                                        return;
                                    }
                                    Thread.sleep(100);
                                } catch(Exception e) {}
                            }
                        }
                    });

                    if(firstTime) {
                        firstTime = false;
                        thread.start();

                    }
                    try {
                        Thread.sleep(10000);
                    } catch(Exception e) {}
                }
            }
        };
        t.start();
        
        while(!episodeOver) {
            try {
                Thread.sleep(1000);
            } catch(Exception e) {}
        }
    }
}