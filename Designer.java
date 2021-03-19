
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Designer {

    UserInterface ui = null;
    
    Graphics2D g = null;
    
    public int shipHeight = 90;
    
    public int shipWidth = 40;
    
    public Designer(UserInterface ui) {

        this.ui = ui;
    }
    
    private void setGraphics(UserInterface ui) {

        this.g = (Graphics2D) ui.getPanel().getGraphics();
    }

    public void drawSun() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                g.setColor(Color.YELLOW);
                g.fillOval(30, 160, 150, 150);
            }
        });
        
        t.start();
    }

    public void drawSeparate(Ship ship2) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                g.setColor(Color.YELLOW);
                g.fillRect(ship2.x, ship2.y, 50, 120);
                if(ship2.y < 850)
                    ship2.y+=2;
            }
        });
        
        t.start();
    }
    
    public void drawEarth(Ground ground) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                g.setColor(Color.BLUE);
                g.fillOval(-1300, ground.y, 2600, 1000);

                g.setColor(Color.WHITE);
                g.fillRect(10, 720, 150, 150);
                g.setColor(Color.BLUE);
                g.fillRect(10, 720, 65, 65);
                
                for(int i=1; i<=3; i++) {
                    if(i  > 1) {
                        g.setColor(Color.RED);
                        g.fillRect(75, 720+i*18 - 9, 85, 10);
                    }
                    else {
                        g.setColor(Color.RED);
                        g.fillRect(75, 720+i*9, 85, 10);
                    }
                }
                
                for(int i=4; i<=8; i++) {
                    g.setColor(Color.RED);
                    g.fillRect(10, 720+i*18 - 9, 150, 10);
                }

                g.setColor(Color.RED);
                g.drawRect(10, 720, 150, 150);

                for(int i=0; i<5; i++) {
                    for(int j=0; j<6; j++) {
                        g.setColor(Color.WHITE);
                        g.fillOval(20+j*8, 735+i*12 - 6, 4, 4);
                    }
                }

                for(int i=0; i<4; i++) {
                    for(int j=0; j<5; j++) {
                        g.setColor(Color.WHITE);
                        g.fillOval(20+j*8+4, 735+i*12 - 6 + 6, 4, 4);
                    }
                }
            }
        });
        t.start();
    }
                
    public void drawGround(Ground ground) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                g.setColor(Color.GRAY);
                g.fillRect(0, ground.y, 1300, 900-ground.y);

                g.setColor(Color.BLACK);
                g.drawLine(0, 800, 1300, 800);
                g.setColor(Color.BLACK);
                g.drawLine(0, 840, 1300, 840);
                g.setColor(Color.BLACK);
                g.drawLine(0, 880, 1300, 880);
            }
        });
        t.start();
    }

    public void drawSky(Sky sky) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                g.setColor(Color.BLUE);
                g.fillRect(0, sky.y1, 1300, sky.y2);

                g.setColor(Color.GRAY);
                g.fillOval(100, 100, 90, 100);
                g.setColor(Color.GRAY);
                g.fillOval(140, 100, 90, 100);
                g.setColor(Color.GRAY);
                g.fillOval(180, 100, 90, 100);

                g.setColor(Color.GRAY);
                g.fillOval(300, 100, 90, 100);
                g.setColor(Color.GRAY);
                g.fillOval(340, 100, 90, 100);
                g.setColor(Color.GRAY);
                g.fillOval(380, 100, 90, 100);

                g.setColor(Color.GRAY);
                g.fillOval(500, 100, 90, 100);
                g.setColor(Color.GRAY);
                g.fillOval(540, 100, 90, 100);
                g.setColor(Color.GRAY);
                g.fillOval(580, 100, 90, 100);

                g.setColor(Color.GRAY);
                g.fillOval(700, 100, 90, 100);
                g.setColor(Color.GRAY);
                g.fillOval(740, 100, 90, 100);
                g.setColor(Color.GRAY);
                g.fillOval(780, 100, 90, 100);
                g.setColor(Color.GRAY);

                g.setColor(Color.GRAY);
                g.fillOval(900, 100, 90, 100);
                g.setColor(Color.GRAY);
                g.fillOval(940, 100, 90, 100);
                g.setColor(Color.GRAY);
                g.fillOval(980, 100, 90, 100);

                g.setColor(Color.GRAY);
                g.fillOval(1100, 100, 90, 100);
                g.setColor(Color.GRAY);
                g.fillOval(1140, 100, 90, 100);
                g.setColor(Color.GRAY);
                g.fillOval(1180, 100, 90, 100);
            }
        });
        t.start();
    }
    
    public void drawSpace() {
        Random random = new Random();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                g.setColor(new Color(40, 40, 40));
                g.fillRect(0, 0, 1300, 900);
                for(int x=0; x<100; x++) {
                    g.setColor(Color.WHITE);
                    int xx = random.nextInt(1300);
                    int yy = random.nextInt(700);
                    g.drawOval(xx, yy, 3, 3);
                }
            }
        });
        t.start();
    }

    public void drawShip(Ship ship) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                g.setColor(Color.MAGENTA);
                g.fillRect(ship.x-29, ship.y, shipWidth, shipHeight);
            }
        });
        t.start();
    }

    public void drawLunarModuleStatic() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(600, 350, 100, 200);
                g.setColor(Color.WHITE);
                g.fillOval(600-15, 435, 30, 30);
            }
        });
        t.start();
    }
    
    public void drawLunarModuleDynamic(Ship ship) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(ship.x+90, ship.y-90, 100, 200);
                g.setColor(Color.WHITE);
                g.fillOval(ship.x+90-15, ship.y - 90 + 85, 30, 30);
            }
        });
        t.start();
    }

    public void drawLunarModuleDynamic_2(Ship ship) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(ship.x-100, ship.y-90, 100, 200);
                g.setColor(Color.WHITE);
                g.fillOval(ship.x-100+15+75, ship.y - 90 + 75, 30, 30);
            }
        });
        t.start();
    }

    public void drawShip2(Ship ship) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                g.setColor(Color.YELLOW);
                g.fillRect(ship.x, ship.y, 90, 30);
            }
        });
        t.start();
    }

    public void drawShip_2(Ship ship) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                g.setColor(Color.YELLOW);
                g.fillRect(ship.x, ship.y, 90, 30);
            }
        });
        t.start();
    }

    public void drawLaunchpad(Ship ship) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                g.setColor(Color.white);
                g.fillRect(500-70, 620, 30+30, 90+50);
            }
        });
        t.start();
    }
}