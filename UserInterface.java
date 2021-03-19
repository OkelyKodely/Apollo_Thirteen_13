import java.sql.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UserInterface {
  ResultSet rs0 = null;
Statement sta = null;        
    int setplayer = -2;
    public void setPlayer(int ii) {
        this .setplayer = ii;
    
    }
    ArrayList<Point> points = new ArrayList<Point>();
int insert_i = 0;
    boolean dont = false;
    int idd = -1;
    int idD = -1;
    int id = -1;

    private class Point {

        int x = -10;

        int y = -10;
    }
    
    Connection connection = null;
    Statement statement = null;
    boolean playPressed = false;
    boolean recPressed = false;
    Graphics2D g2 = null;
    int y_accel4 = 0;
    int x1 = 800;
    int x2 = 900;
    int x3 = 600;
    int x4 = 200;
    int y4 = 300;
    int xx1 = 0;
    int xx2 = 0;
    int xx3 = 0;
    int xx4 = 0;
    int shipx, shipy;
    int x_accel3 = 0;
    int y_accel3 = 0;
    int flag = 1;
    int acel = 0;
    int abc = 0;
    boolean launched = false;
    int thirty = 300;
    boolean lost = true;
    boolean lost1 = true;
    boolean lost3 = true;
    int accel1 = 6;
    int vk = 0;
    int tk = 0;
    int aad = -2;
    int aod = -10;
    boolean ff = true;
        
    JPanel p = null;
    JFrame f = null;
    
    Ship ship = new Ship();
    Ship ship2 = new Ship();
    Ground ground = new Ground();
    Sky sky = new Sky();
    
    ShipTrailerMgr stm = null;
    
    Designer d = new Designer(this);
    
    public UserInterface() {
        initProgram();
    }
    
    private void initProgram() {
        
        Designer d = new Designer(this);

        Ship ship = new Ship();
        Ship ship2 = new Ship();
        Sky sky = new Sky();
        Ground ground = new Ground();
    }
    int zero = -1;
    int ididid = -1;
    public void runScript() throws SQLException {

        try {
            
            Class.forName("com.mysql.jdbc.Driver");

            String hostName = "mysql3000.mochahost.com";
            
            String dbName = "yourztub_bemusedsanity";
            
            String userName = "yourztub_yoot";
            String passWord = "Asada428";
            
            String url = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?user=" + userName + "&password=" + passWord;

            connection = DriverManager.getConnection(url);

            statement = connection.createStatement();
            sta = connection.createStatement();
            //create table apollo13videos (idid int, shipx int, shipy int, lunarmoduleon int, episodenum int, goingtothemoonon int, goingtothemoon varchar(5), lunarmodulewillpickup int, lunarmodulewith30seconds int, lunarhoming int, inputdates datetime);

        } catch(Exception e) {
            e.printStackTrace();
        }

        d.g = (Graphics2D) getPanel().getGraphics();

        sky.y1 = 0;
        sky.y2 = 700;
        ground.y = 700;
        ship.x = 500;
        ship.y = ground.y-30;
        ship2.x = 500;
        ship2.y = ground.y;

        KeyListener k = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    launched = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
        
        f.addKeyListener(k);
    
        
        
        Runnable r = new Runnable() {
            
            @Override
            public void run() {
                
                try {
                    
                if (setplayer != -2 && zero == -1) {
                    
                    
                    zero = 1;
                                        ididid = points.get(setplayer).x;
                    
                    rs0 = sta.executeQuery ("select shipx, shipy from apollo13videos where i = " + ididid + " order by idid asc");
        
                }
                } catch(Exception e) {
                    e.printStackTrace();
                }
                
                
                f.setTitle("Launch");
                
                d.drawGround(ground);
                d.drawSun();
                d.drawSky(sky);
                d.drawShip(ship);
                d.drawLaunchpad(ship);
                while(!launched) {
                    try {
                        Thread.sleep(1000);
                    } catch(Exception e) {}
                }
                try {
                    
                if(rs0.next() &&setplayer != 10) {
                    ship.x = rs0.getInt("shipx");
                    ship.y = rs0.getInt("shipy");
                }
                
                } catch(SQLException sqle) {
                    
                }
                final int shipHeight = d.shipHeight;
                try {
                    stm.doi(
                            p, 
                            ship.x, 
                            ship.y+shipHeight);
                    
                } catch(Exception e) {
                    e.printStackTrace();
                }
                
                ship.y-=6;
                try {
                    Thread.sleep(100);
                } catch(Exception e) {}
                
                
                
                //added 3/16/2021.
                if(idd <= -2) {
                    idd = -1; //added 3/16/2021.
                }
                

                String sql = "SELECT max(idid) as idd FROM apollo13videos_table";
                ResultSet rs = null;
                
                try {
                    if(idd == -1)
                        rs = statement.executeQuery(sql);
                } catch(SQLException sqle) {
                    sqle.printStackTrace();
                }
                try {
                    if(idd == -1) {
                        if(rs.next()) {
                            idd = rs.getInt("idd") + 1;
                        } else {
                            idd = 1;
                        }
                    }
                } catch(SQLException sqle) {
                    sqle.printStackTrace();

                    idd = 1;

                }

               
                
                String sqlStr = "INSERT INTO apollo13videos_table (idid) values (" +idd+ ");";

                
                try {
                    if(dont == false) {
                        
                        insert_i = idd;
                        
                        statement.executeUpdate(sqlStr);
                        
                        idd = -2; //added 3/16/2021.
                        
                        dont = true;
                    }

                } catch(SQLException sqle) {
                    sqle.printStackTrace();
                    
                    idd = -2;
                }

               
                try {
                    sql = "SELECT max(idid) as id FROM apollo13videos";
                    rs = statement.executeQuery(sql);
                    if(rs.next()) {
                        id = rs.getInt("id") + 1;
                    } else {
                        id = 1;
                    }
                } catch(SQLException sqle) {
                    sqle.printStackTrace();
                    id = 1;
                }

                ////
                //3/16/2021PERIODEDinYUOfacE.~
                idD = id;

                
                
                //reate table apollo13videos (idid int, shipx int, shipy int, lunarmoduleon int, episodenum int, goingtothemoonon int, goingtothemoon varchar(5), lunarmodulewillpickup int, lunarmodulewith30seconds int, lunarhoming int, inputdates datetime);
                int episodenum = 1;
                int lunarmoduleon = 0;
                String sqlStr2 = "INSERT INTO apollo13videos (i, idid, shipx, shipy, lunarmoduleon, episodenum, goingtothemoonon, goingtothemoon, lunarmodulewillpickup, lunarmodulewith30seconds, lunarhoming, inputdates) values ("+insert_i+", " +idD+ ","+ship.x+","+ship.y+"," +lunarmoduleon+ "," +episodenum+ ", 0,0, 0, 0, 0, now());";

                try {

                   statement.executeUpdate(sqlStr2);

                } catch(SQLException sqle) {
                    System.out.println("here i am abram");//.
                    
                    sqle.printStackTrace();
                }

                if(ship.y < 0 || ship.y > 900) {

                    MySingleton.getInstance().setEpisodeOver(true);
                
                }
            }
        };
        Episode launchProgram = new Episode(r);
        launchProgram.setDesigner(d);
        launchProgram.run();

        ship.y = 650;
        idd = -1;
        dont = false;
                
        r = new Runnable() {
            @Override
            public void run() {
             
                f.setTitle("Staging");
                d.drawSpace();
                d.drawEarth(ground);
               
                
                while(!launched) {
                    try {
                        Thread.sleep(1000);
                    } catch(Exception e) {}
                }
                
                
                //added 3/16/2021.
                if(idd <= -2) {
                    idd = -1; //added 3/16/2021.
                }
                
                
                String sql = "SELECT max(idid) as idd FROM apollo13videos_table";
                ResultSet rs = null;
                try {
                    if(idd == -1) {
                        rs = statement.executeQuery(sql);
                        if(rs.next()) {
                            idd = rs.getInt("idd") + 1;
                        }
                    } else {
                        rs = statement.executeQuery(sql);
                        if(rs.next()) {
                            idd = rs.getInt("idd") + 1;
                        }
                    }
                } catch(SQLException sqle) {
                    sqle.printStackTrace();
                    idd = 1;
                }

              
               String sqlStr = "INSRT INTO apollo13videos_table (idid) values (" +idd+ ");";

               try {
                    if(dont == false) {
                        
                        statement.executeUpdate(sqlStr);
                        
                        idd = -2; //added 3/16/2021.
                        
                        dont = true;

                    }

                } catch(SQLException sqle) {
                    sqle.printStackTrace();
                    
                    
                    dont = true; ;;
                }
    
                
                
                try {
                    sql = "SELECT max(idid) as id FROM apollo13videos";
                    rs = statement.executeQuery(sql);
                    if(rs.next()) {
                        id = rs.getInt("id") + 1;
                    } else {
                        id = 1;
                    }
                } catch(SQLException sqle) {
                    sqle.printStackTrace();

                    id = 1;

                }
                ////
                //3/16/2021PERIODEDinYUOfacE.~
                idD = id;


                
                //create table apollo13videos (idid int, shipx int, shipy int, lunarmoduleon int, episodenum int, goingtothemoonon int, goingtothemoon varchar(5), lunarmodulewillpickup int, lunarmodulewith30seconds int, lunarhoming int, inputdates datetime);
                int episodenum = 2;
                int lunarmoduleon = 0;
                String sqlStr2 = "INSERT INTO apollo13videos (i, idid, shipx, shipy, lunarmoduleon, episodenum, goingtothemoonon, goingtothemoon, lunarmodulewillpickup, lunarmodulewith30seconds, lunarhoming, inputdates) values ("+insert_i+", " +idD+ ","+ship.x+","+ship.y+"," +lunarmoduleon+ "," +episodenum+ ", 0,0, 0, 0, 0, now());";

                try {

                   statement.executeUpdate(sqlStr2);
                } catch(SQLException sqle) {
                    
                    sqle.printStackTrace();
                }

                try {
                    
                if(rs0.next() &&setplayer != 10) {
                    ship.x = rs0.getInt("shipx");
                    ship.y = rs0.getInt("shipy");
                }
                
                } catch(SQLException sqle) {
                    
                }
                
                if(ship.y > 550) {
                    ship.y-=8;
                    ship2.y-=8;
                }
                else {
                    ship.y-=8;
                    if(ship2.y < 840)
                        d.drawSeparate(ship2);
                }
                d.drawShip(ship);
                if(ship.y <= 100)
                    MySingleton.getInstance().setEpisodeOver(true);
            }
        };
        MySingleton.getInstance().setEpisodeOver(false);
        Episode staging = new Episode(r);
        staging.setDesigner(d);
        staging.run();

        ship.y = 850;
        ship.x = 1150;
        Random rnd = new Random();
        k = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    acel -= 1;
                    aad = 0;
                    tk--;
                    if(aad == abc)
                        aad = -2;
                }
                else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    acel += 1;
                    aad = 1;
                    tk++;
                    if(aad == abc)
                        aad = -2;
                }
                d.drawSpace();
                d.drawShip(ship);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
        idd = -1;
        dont = false;
        f.addKeyListener(k);
        r = new Runnable() {
            @Override
            public void run() {
                f.setTitle("Going 2 Moon");
                d.drawSpace();
                d.drawShip(ship);
                ship.y-=1;
                ship.x+=acel;
                while(!launched) {
                    try {
                        Thread.sleep(1000);
                    } catch(Exception e) {}
                }

                //added 3/16/2021.
                if(idd <= -2) {
                    idd = -1; //added 3/16/2021.
                }
                
                
                String sql = "SELECT max(idid) as idd FROM apollo13videos_table";
                ResultSet rs = null;
                try {
                    if(idd == -1) {
                        rs = statement.executeQuery(sql);
                        if(rs.next()) {
                            idd = rs.getInt("idd") + 1;
                        }
                    } else {
                        rs = statement.executeQuery(sql);
                        if(rs.next()) {
                            idd = rs.getInt("idd") + 1;
                        }
                    }
                } catch(SQLException sqle) {
                    sqle.printStackTrace();
                    idd = 1;
                }

              
               String sqlStr = "INSRT INTO apollo13videos_table (idid) values (" +idd+ ");";

               try {
                    if(dont == false) {
                        
                        statement.executeUpdate(sqlStr);
                        
                        idd = -2; //added 3/16/2021.
                        
                        dont = true;

                    }

                } catch(SQLException sqle) {
                    sqle.printStackTrace();
                    dont = true;;
                }
    
                
                
                try {
                    sql = "SELECT max(idid) as id FROM apollo13videos";
                    rs = statement.executeQuery(sql);
                    if(rs.next()) {
                        id = rs.getInt("id") + 1;
                    } else {
                        id = 1;
                    }
                } catch(SQLException sqle) {
                    sqle.printStackTrace();

                    id = 1;

                }
                ////
                //3/16/2021PERIODEDinYUOfacE.~
                idD = id;


                
                //create table apollo13videos (idid int, shipx int, shipy int, lunarmoduleon int, episodenum int, goingtothemoonon int, goingtothemoon varchar(5), lunarmodulewillpickup int, lunarmodulewith30seconds int, lunarhoming int, inputdates datetime);
                int episodenum = 3;
                int lunarmoduleon = 0;
                String sqlStr2 = "INSERT INTO apollo13videos (i, idid, shipx, shipy, lunarmoduleon, episodenum, goingtothemoonon, goingtothemoon, lunarmodulewillpickup, lunarmodulewith30seconds, lunarhoming, inputdates) values ("+insert_i+", " +idD+ ","+ship.x+","+ship.y+"," +lunarmoduleon+ "," +episodenum+ ", 0,0, 0, 0, 0, now());";

                try {

                   statement.executeUpdate(sqlStr2);
                } catch(SQLException sqle) {
                    
                    sqle.printStackTrace();
                }

                Thread t = new Thread() {
                    public void run() {
                        while(true) {
                            try {
                                Thread.sleep(8000);
                                if(aad == -2) {
                                    if(vk > 0) {
                                        flag = -3;
                                        aod = rnd.nextInt(4);
                                    }
                                    else if(vk == 0) {
                                        aod = rnd.nextInt(2);
                                        flag = 1;
                                    }
                                    else if(vk < 0) {
                                        flag = 3;
                                        aod = rnd.nextInt(4);
                                    }

                                    if(flag == -3) {
                                        if(aod == 0 || aod == 2 || aod == 3) {
                                            vk--;
                                            abc = 0;
                                        }
                                        else if(aod == 1) {
                                            vk++;
                                            abc = 1;
                                        }
                                    }
                                    else if(flag == 3) {
                                        if(aod == 0) {
                                            vk--;
                                            abc = 0;
                                        }
                                        else if(aod == 1 || aod == 2 || aod == 3) {
                                            vk++;
                                            abc = 1;
                                        }
                                    }
                                    else if(flag == 1) {
                                        if(aod == 0 || aod == 1) {
                                            vk--;
                                            abc = 0;
                                        }
                                        else if(aod == 2 || aod == 3) {
                                            vk++;
                                            abc = 1;
                                        }
                                    }
                                    aad = -3;
                                }
                                if(abc == 0) {
                                    d.g.drawLine(100, 500, 200, 300);
                                    d.g.drawLine(100, 500, 200, 700);
                                    d.g.drawLine(100, 500, 450, 500);
                                } else if(abc == 1) {
                                    d.g.drawLine(450, 500, 250, 300);
                                    d.g.drawLine(450, 500, 250, 700);
                                    d.g.drawLine(450, 500, 150, 500);
                                }
                            } catch(Exception e) {}
                            if(MySingleton.getInstance().getEpisodeOver())
                                return;
                        }
                    }
                };
                //f.setTitle("attempted route:"+tk+",true route:"+vk);
                Thread a1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        d.g.setColor(Color.BLUE);
                        d.g.fillOval(800, 800, 200, 200);
                        d.g.setColor(Color.LIGHT_GRAY);
                        d.g.fillOval(670, 150, 50, 50);
                    }
                });
                a1.start();
                try {
                    
                if(rs0.next() &&setplayer != 10) {
                    ship.x = rs0.getInt("shipx");
                    ship.y = rs0.getInt("shipy");
                }
                
                } catch(SQLException sqle) {
                    
                }

                if(ff)
                    t.start();
                if(ff)
                    ff = false;
                if(ship.y <= 500) {
                    if(tk >= vk - 0 && tk <= vk + 0) {
                        MySingleton.getInstance().setEpisodeOver(true);
                        lost1 = false;
                        f.setTitle("You Won!");
                    }
                    else {
                        MySingleton.getInstance().setEpisodeOver(true);
                        lost1 = true;
                        f.setTitle("You Lost!");
                    }
                }
            }
        };
        MySingleton.getInstance().setEpisodeOver(false);
        Episode going2Moon = new Episode(r);
        going2Moon.setDesigner(d);
        while(lost1) {
            going2Moon.run();
            MySingleton.getInstance().setEpisodeOver(false);
            going2Moon.episodeOver = false;
            going2Moon.firstTime = true;
            ff = true;
            ship.y = 850;
            ship.x = 900;
            vk = 0;
            tk = 0;
            aad = -2;
            aod = -10;
        }
        
        f.removeKeyListener(k);
        ship.y = 270;
        ship.x = 55;
        idd = -1;
        dont = false;
        k = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    accel1 --;
                }
                else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    accel1 ++;
                }
                ship.y = ship.y + accel1;
                d.drawSpace();
                d.drawShip2(ship);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
        f.addKeyListener(k);
        r = new Runnable() {
            @Override
            public void run() {
                f.setTitle("Pickup Lunar Module");
                d.drawSpace();
                d.drawShip2(ship);
                d.drawLunarModuleStatic();
                ship.x+=5;
                ship.y = ship.y + accel1;
                try {
                    
                if(rs0.next() &&setplayer != 10) {
                    ship.x = rs0.getInt("shipx");
                    ship.y = rs0.getInt("shipy");
                }
                
                } catch(SQLException sqle) {
                    
                }
                while(!launched) {
                    try {
                        Thread.sleep(1000);
                    } catch(Exception e) {}
                }
                //added 3/16/2021.
                if(idd <= -2) {
                    idd = -1; //added 3/16/2021.
                }
                
                
                String sql = "SELECT max(idid) as idd FROM apollo13videos_table";
                ResultSet rs = null;
                try {
                    if(idd == -1) {
                        rs = statement.executeQuery(sql);
                        if(rs.next()) {
                            idd = rs.getInt("idd") + 1;
                        }
                    } else {
                        rs = statement.executeQuery(sql);
                        if(rs.next()) {
                            idd = rs.getInt("idd") + 1;
                        }
                    }
                } catch(SQLException sqle) {
                    sqle.printStackTrace();
                    idd = 1;
                }

              
               String sqlStr = "INSRT INTO apollo13videos_table (idid) values (" +idd+ ");";

               try {
                    if(dont == false) {
                        
                        statement.executeUpdate(sqlStr);
                        
                        idd = -2; //added 3/16/2021.
                        
                        dont = true;

                    }

                } catch(SQLException sqle) {
                    sqle.printStackTrace();
                    //added.
                    
                    dont = true;
                }
    
                
                
                try {
                    sql = "SELECT max(idid) as id FROM apollo13videos";
                    rs = statement.executeQuery(sql);
                    if(rs.next()) {
                        id = rs.getInt("id") + 1;
                    } else {
                        id = 1;
                    }
                } catch(SQLException sqle) {
                    sqle.printStackTrace();

                    id = 1;

                }
                ////
                //3/16/2021PERIODEDinYUOfacE.~
                idD = id;


                
                //create table apollo13videos (idid int, shipx int, shipy int, lunarmoduleon int, episodenum int, goingtothemoonon int, goingtothemoon varchar(5), lunarmodulewillpickup int, lunarmodulewith30seconds int, lunarhoming int, inputdates datetime);
                int episodenum = 4;
                int lunarmoduleon = 1;
                String sqlStr2 = "INSERT INTO apollo13videos (i, idid, shipx, shipy, lunarmoduleon, episodenum, goingtothemoonon, goingtothemoon, lunarmodulewillpickup, lunarmodulewith30seconds, lunarhoming, inputdates) values ("+insert_i+", " +idD+ ","+ship.x+","+ship.y+"," +lunarmoduleon+ "," +episodenum+ ", 0,0, 0, 0, 0, now());";

                try {

                   statement.executeUpdate(sqlStr2);
                } catch(SQLException sqle) {
                    
                    sqle.printStackTrace();
                }

                try {
                    
                if(rs0.next() &&setplayer != 10) {
                    ship.x = rs0.getInt("shipx");
                    ship.y = rs0.getInt("shipy");
                }
                
                } catch(SQLException sqle) {
                    
                }
                if(ship.x+90 >= 600-15)
                    MySingleton.getInstance().setEpisodeOver(true);
                if(ship.x+90 >= 600-15) {
                    if(ship.x+90 >= 600-15 && ship.y >= 433 && ship.y <= 437) {
                        f.setTitle("You picked up the Lunar Module, the LEM.  You Won!");
                        lost = false;
                    }
                    else
                        f.setTitle("You Lost!");
                }
            }
        };
        MySingleton.getInstance().setEpisodeOver(false);
        Episode pickup = new Episode(r);
        pickup.setDesigner(d);
        while(lost) {
            pickup.run();
            MySingleton.getInstance().setEpisodeOver(false);
            pickup.episodeOver = false;
            pickup.firstTime = true;
            ship.y = 270;
            ship.x = 55;
        }

        f.removeKeyListener(k);
        ship.y = 435;
        ship.x = 600-15-90;
        dont = false;
        idd = -1;
        k = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_X) {
                    thirty = 104;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
        r = new Runnable() {
            @Override
            public void run() {
                d.drawSpace();
                try {
                    
                if(rs0.next() &&setplayer != 10) {
                    ship.x = rs0.getInt("shipx");
                    ship.y = rs0.getInt("shipy");
                }
                
                } catch(SQLException sqle) {
                    
                }
                d.drawShip2(ship);
                d.drawLunarModuleStatic();
                while(!launched) {
                    try {
                        Thread.sleep(1000);
                    } catch(Exception e) {}
                }
                //added 3/16/2021.
                if(idd <= -2) {
                    idd = -1; //added 3/16/2021.
                }
                
                
                String sql = "SELECT max(idid) as idd FROM apollo13videos_table";
                ResultSet rs = null;
                try {
                    if(idd == -1) {
                        rs = statement.executeQuery(sql);
                        if(rs.next()) {
                            idd = rs.getInt("idd") + 1;
                        }
                    } else {
                        rs = statement.executeQuery(sql);
                        if(rs.next()) {
                            idd = rs.getInt("idd") + 1;
                        }
                    }
                } catch(SQLException sqle) {
                    sqle.printStackTrace();
                    idd = 1;
                }

              
               String sqlStr = "INSRT INTO apollo13videos_table (idid) values (" +idd+ ");";

               try {
                    if(dont == false) {
                        
                        statement.executeUpdate(sqlStr);
                        
                        idd = -2; //added 3/16/2021.
                        
                        dont = true;

                    }

                } catch(SQLException sqle) {
                    sqle.printStackTrace();
                    dont = true;
                }
    
                
                
                try {
                    sql = "SELECT max(idid) as id FROM apollo13videos";
                    rs = statement.executeQuery(sql);
                    if(rs.next()) {
                        id = rs.getInt("id") + 1;
                    } else {
                        id = 1;
                    }
                } catch(SQLException sqle) {
                    sqle.printStackTrace();

                    id = 1;

                }
                ////
                //3/16/2021PERIODEDinYUOfacE.~
                idD = id;


                
                //create table apollo13videos (idid int, shipx int, shipy int, lunarmoduleon int, episodenum int, goingtothemoonon int, goingtothemoon varchar(5), lunarmodulewillpickup int, lunarmodulewith30seconds int, lunarhoming int, inputdates datetime);
                int episodenum = 5;
                int lunarmoduleon = 0;
                
                String sqlStr2 = "INSERT INTO apollo13videos (i, idid, shipx, shipy, lunarmoduleon, episodenum, goingtothemoonon, goingtothemoon, lunarmodulewillpickup, lunarmodulewith30seconds, lunarhoming, inputdates) values ("+insert_i+", " +idD+ ","+ship.x+","+ship.y+"," +lunarmoduleon+ "," +episodenum+ ", 0,0, 0, 0, 0, now());";

                try {

                   statement.executeUpdate(sqlStr2);
                } catch(SQLException sqle) {
                    
                    sqle.printStackTrace();
                }
                f.setTitle((int)((double)thirty/(double)10)+" seconds");
                thirty --;
                if(thirty == 0) {
                    MySingleton.getInstance().setEpisodeOver(true);
                    f.setTitle("You won!");
                }
            }
        };
        MySingleton.getInstance().setEpisodeOver(false);
        Episode halfwayStory = new Episode(r);
        halfwayStory.setDesigner(d);
        halfwayStory.run();

        f.removeKeyListener(k);
        ship.y = 50;
        ship.x = 250;
        dont = false;
        idd = -1;
        k = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    x_accel3 -= 2;
                }
                else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    x_accel3 += 2;
                }

                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    y_accel3 -= 2;
                }
                else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    y_accel3 += 2;
                }

                d.drawSpace();
                try {
                    
                if(rs0.next() &&setplayer != 10) {
                    ship.x = rs0.getInt("shipx");
                    ship.y = rs0.getInt("shipy");
                }
                
                } catch(SQLException sqle) {
                    
                }
                d.drawShip2(ship);
                d.drawLunarModuleDynamic(ship);
                while(!launched) {
                    try {
                        Thread.sleep(1000);
                    } catch(Exception e3) {}
                }
                
                //added 3/16/2021.
                if(idd <= -2) {
                    idd = -1; //added 3/16/2021.
                }
                
                
                String sql = "SELECT max(idid) as idd FROM apollo13videos_table";
                ResultSet rs = null;
                try {
                    if(idd == -1) {
                        rs = statement.executeQuery(sql);
                        if(rs.next()) {
                            idd = rs.getInt("idd") + 1;
                        }
                    } else {
                        rs = statement.executeQuery(sql);
                        if(rs.next()) {
                            idd = rs.getInt("idd") + 1;
                        }
                    }
                } catch(SQLException sqle) {
                    sqle.printStackTrace();
                    idd = 1;
                }

              
               String sqlStr = "INSRT INTO apollo13videos_table (idid) values (" +idd+ ");";

               try {
                    if(dont == false) {
                        
                        statement.executeUpdate(sqlStr);
                        
                        idd = -2; //added 3/16/2021.
                        
                        dont = true;

                    }

                } catch(SQLException sqle) {
                    sqle.printStackTrace();
                    dont = true;
                }
    
                
                
                try {
                    sql = "SELECT max(idid) as id FROM apollo13videos";
                    rs = statement.executeQuery(sql);
                    if(rs.next()) {
                        id = rs.getInt("id") + 1;
                    } else {
                        id = 1;
                    }
                } catch(SQLException sqle) {
                    sqle.printStackTrace();

                    id = 1;

                }
                ////
                //3/16/2021PERIODEDinYUOfacE.~
                idD = id;


                
                //create table apollo13videos (idid int, shipx int, shipy int, lunarmoduleon int, episodenum int, goingtothemoonon int, goingtothemoon varchar(5), lunarmodulewillpickup int, lunarmodulewith30seconds int, lunarhoming int, inputdates datetime);
                int episodenum = 6;
                int lunarmoduleon = 0;
                String sqlStr2 = "INSERT INTO apollo13videos (i, idid, shipx, shipy, lunarmoduleon, episodenum, goingtothemoonon, goingtothemoon, lunarmodulewillpickup, lunarmodulewith30seconds, lunarhoming, inputdates) values ("+insert_i+", " +idD+ ","+ship.x+","+ship.y+"," +lunarmoduleon+ "," +episodenum+ ", 0,0, 0, 0, 0, now());";

                try {

                   statement.executeUpdate(sqlStr2);
                } catch(SQLException sqle) {
                    
                    sqle.printStackTrace();
                }

                d.g.setColor(Color.yellow);
                d.g.fillOval(400, 200, 300, 300);

                d.g.setColor(Color. PINK);
                d.g.drawLine(shipx, shipy, ship.x, ship.y);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
        f.addKeyListener(k);
        ArrayList<Integer> shipx_ = new ArrayList<>();
        ArrayList<Integer> shipy_ = new ArrayList<>();
        ArrayList<Integer> ship_x_ = new ArrayList<>();
        ArrayList<Integer> ship_y_ = new ArrayList<>();
        dont = false;
        idd = -1;
        r = new Runnable() {
            @Override
            public void run() {
                d.drawSpace();
                try {
                    
                if(rs0.next() &&setplayer != 10) {
                    ship.x = rs0.getInt("shipx");
                    ship.y = rs0.getInt("shipy");
                }
                
                } catch(SQLException sqle) {
                    
                }
                d.drawShip2(ship);
                d.drawLunarModuleDynamic(ship);
                while(!launched) {
                    try {
                        Thread.sleep(1000);
                    } catch(Exception e) {}
                }

                //added 3/16/2021.
                if(idd <= -2) {
                    idd = -1; //added 3/16/2021.
                }
                
                
                String sql = "SELECT max(idid) as idd FROM apollo13videos_table";
                ResultSet rs = null;
                try {
                    if(idd == -1) {
                        rs = statement.executeQuery(sql);
                        if(rs.next()) {
                            idd = rs.getInt("idd") + 1;
                        }
                    } else {
                        rs = statement.executeQuery(sql);
                        if(rs.next()) {
                            idd = rs.getInt("idd") + 1;
                        }
                    }
                } catch(SQLException sqle) {
                    sqle.printStackTrace();
                    idd = 1;
                }

              
               String sqlStr = "INSRT INTO apollo13videos_table (idid) values (" +idd+ ");";

               try {
                    if(dont == false) {
                        
                        statement.executeUpdate(sqlStr);
                        
                        idd = -2; //added 3/16/2021.
                        
                        dont = true;

                    }

                } catch(SQLException sqle) {
                    sqle.printStackTrace();
                    dont = true; //added
                }
    
                
                
                try {
                    sql = "SELECT max(idid) as id FROM apollo13videos";
                    rs = statement.executeQuery(sql);
                    if(rs.next()) {
                        id = rs.getInt("id") + 1;
                    } else {
                        id = 1;
                    }
                } catch(SQLException sqle) {
                    sqle.printStackTrace();

                    id = 1;

                }
                ////
                //3/16/2021PERIODEDinYUOfacE.~
                idD = id;


                
                //create table apollo13videos (idid int, shipx int, shipy int, lunarmoduleon int, episodenum int, goingtothemoonon int, goingtothemoon varchar(5), lunarmodulewillpickup int, lunarmodulewith30seconds int, lunarhoming int, inputdates datetime);
                int episodenum = 7;
                int lunarmoduleon = 0;
                String sqlStr2 = "INSERT INTO apollo13videos (i, idid, shipx, shipy, lunarmoduleon, episodenum, goingtothemoonon, goingtothemoon, lunarmodulewillpickup, lunarmodulewith30seconds, lunarhoming, inputdates) values ("+insert_i+", " +idD+ ","+ship.x+","+ship.y+"," +lunarmoduleon+ "," +episodenum+ ", 0,0, 0, 0, 0, now());";

                try {

                   statement.executeUpdate(sqlStr2);
                } catch(SQLException sqle) {
                    
                    sqle.printStackTrace();
                }

                Thread t = new Thread() {
                    public void run() {
                        d.g.setColor(Color.GRAY);
                        d.g.fillOval(400, 200, 300, 300);
                        while(!launched) {
                            try {
                                Thread.sleep(1000);
                            } catch(Exception e) {}
                        }

                        shipx = ship.x;
                        shipy = ship.y;

                        ship.x += x_accel3;
                        ship.y += y_accel3;

                        shipx_.add(shipx);
                        shipy_.add(shipy);
                        
                        ship_x_.add(ship.x);
                        ship_y_.add(ship.y);
                        
                        if(ship.x > x1)
                            xx1 = 1;

                        if(ship.x > x2)
                            xx2 = 1;

                        if(ship.x > x3)
                            xx3 = 1;

                        if(ship.x < x4 && ship.y < y4)
                            xx4 = 1;

                        if(xx1 == 1 && xx2 == 1 && xx3 == 1 && xx4 == 1) {
                            MySingleton.getInstance().setEpisodeOver(true);
                            f.setTitle("You won!");
                        }
                    }
                };
                t.start();

                Thread t1 = new Thread() {
                    public void run() {
                        for(int i=0; i<shipx_.size(); i++) {
                            d.g.setColor(Color.PINK);
                            d.g.drawLine(shipx_.get(i), shipy_.get(i), ship_x_.get(i), ship_y_.get(i));
                        }
                    }
                };
                t1.start();
            }
        };
        MySingleton.getInstance().setEpisodeOver(false);
        Episode orbit = new Episode(r);
        orbit.setDesigner(d);
        orbit.run();

        f.removeKeyListener(k);
        ship.y = 370;
        ship.x = 1050;
        dont = false;
        idd = -1;
        k = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    y_accel4 -= 2;
                }
                else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    y_accel4 += 2;
                }

                d.drawSpace();
                try {
                    
                if(rs0.next() &&setplayer != 10) {
                    ship.x = rs0.getInt("shipx");
                    ship.y = rs0.getInt("shipy");
                }
                
                } catch(SQLException sqle) {
                    
                }
                d.drawShip_2(ship);
                d.drawLunarModuleDynamic_2(ship);

                d.g.setColor(Color.BLUE);
                d.g.fillOval(-70, 140, 140, 280);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
        f.addKeyListener(k);
        r = new Runnable() {
            @Override
            public void run() {
                d.drawSpace();
                d.drawShip_2(ship);
                d.drawLunarModuleDynamic_2(ship);
                
                ship.y += y_accel4;
                ship.x--;
                while(!launched) {
                    try {
                        Thread.sleep(1000);
                    } catch(Exception e) {}
                }

                //added 3/16/2021.
                if(idd <= -2) {
                    idd = -1; //added 3/16/2021.
                }
                
                
                String sql = "SELECT max(idid) as idd FROM apollo13videos_table";
                ResultSet rs = null;
                try {
                    if(idd == -1) {
                        rs = statement.executeQuery(sql);
                        if(rs.next()) {
                            idd = rs.getInt("idd") + 1;
                        }
                    } else {
                        rs = statement.executeQuery(sql);
                        if(rs.next()) {
                            idd = rs.getInt("idd") + 1;
                        }
                    }
                } catch(SQLException sqle) {
                    sqle.printStackTrace();
                    idd = 1;
                }

              
               String sqlStr = "INSRT INTO apollo13videos_table (idid) values (" +idd+ ");";

               try {
                    if(dont == false) {
                        
                        statement.executeUpdate(sqlStr);
                        
                        idd = -2; //added 3/16/2021.
                        
                        dont = true;

                    }

                } catch(SQLException sqle) {
                    sqle.printStackTrace();
                    dont = true; //added 3/17/2021..
                }
    
                
                
                try {
                    sql = "SELECT max(idid) as id FROM apollo13videos";
                    rs = statement.executeQuery(sql);
                    if(rs.next()) {
                        id = rs.getInt("id") + 1;
                    } else {
                        id = 1;
                    }
                } catch(SQLException sqle) {
                    sqle.printStackTrace();

                    id = 1;

                }
                ////
                //3/16/2021PERIODEDinYUOfacE.~
                idD = id;


                
                //create table apollo13videos (idid int, shipx int, shipy int, lunarmoduleon int, episodenum int, goingtothemoonon int, goingtothemoon varchar(5), lunarmodulewillpickup int, lunarmodulewith30seconds int, lunarhoming int, inputdates datetime);
                int episodenum = 8;
                int lunarmoduleon = 0;
                String sqlStr2 = "INSERT INTO apollo13videos (i, idid, shipx, shipy, lunarmoduleon, episodenum, goingtothemoonon, goingtothemoon, lunarmodulewillpickup, lunarmodulewith30seconds, lunarhoming, inputdates) values ("+insert_i+", " +idD+ ","+ship.x+","+ship.y+"," +lunarmoduleon+ "," +episodenum+ ", 0,0, 0, 0, 0, now());";

                try {

                   statement.executeUpdate(sqlStr2);
                } catch(SQLException sqle) {
                    
                    sqle.printStackTrace();
                }

                Thread t = new Thread() {
                    public void run() {
                        d.g.setColor(Color.BLUE);
                        d.g.fillOval(-40, 140, 120, 280);
                    }
                };
                t.start();
            }
        };
        MySingleton.getInstance().setEpisodeOver(false);
        Episode direct2Home = new Episode(r);
        direct2Home.setDesigner(d);
        direct2Home.run();
    }
    JPanel p2 = null;
    public void setGUI() {
    
        this.f = new JFrame();
        this.p = new JPanel();
        this.p2 = new JPanel();
        f.setLayout(null);
        p.setLayout(null);
        p2.setLayout(null);
        f.setBounds(0, 0, 1300, 900);
        p.setLocation(0, 0);
        p.setSize(1280, 900);
        p2.setLocation(1100, 0);
        p2.setSize(180, 900);
        f.add(p);
        f.add(p2);
        this.stm = new ShipTrailerMgr(p);
        this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.f.setVisible(true);
        f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        
        g2 = (Graphics2D) p2.getGraphics();
        p2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(!recPressed)
                if(e.getX() >= 80 && e.getX() <= 140 &&
                        e.getY() >= 660 && e.getY() <= 686) {
                    launched = true;
                    recPressed = true;
                }

                if(!recPressed)
                if(e.getX() >= 10 && e.getX() <= 70 &&
                        e.getY() >= 660 && e.getY() <= 686) {
                    launched = true;
                    playPressed = !playPressed;
                }
                

                // addEd 3/18/2021
                if(e.getX() >= 10 && e.getX() <= 120 &&
                        e.getY() >= 10 && e.getY() <= 570) {
                    
                    int i = 0;
                    
                    for(; i<30; i++) {
                    
                        if(e.getX() >= 10 && e.getX() <= 120 &&
                                e.getY() >= 10 * i && e.getY() <= 10 * i + 20) {

                            setPlayer(i);
                            launched = true;
                            playPressed = true;
                        }

                    }
                }
                    
                if(e.getX() >= 10 && e.getX() <= 70 &&
                        e.getY() >= 760 && e.getY() <= 786) {
                    launched = true;
                }
                if(e.getX() >= 160 && e.getX() <= 180 &&
                        e.getY() >= 0 && e.getY() <= 20) {
                    launched = false;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        Thread t = new Thread() {
            public void run() {
                while(true) {
                    try {
                        
                        if(launched) {
                            g2.setColor(Color.WHITE);
                            g2.fillRect(160, 0, 20, 900);
                            g2.setColor(Color.lightGray);
                            g2.fillRect(160, 1, 16, 16);
                        }
                        
                        if(!launched) {
                            
                            g2.setColor(Color.WHITE);
                            g2.fillRect(0, 0, 180, 900);

                            g2.setColor(Color.RED);
                            g2.fillRect(10, 10, 160, 880-300);
                            
                            
                            try {
                            
                                Statement st = connection.createStatement();
                                String sql = "select b.i, b.shipx, b.shipy,b.inputdates, i.idid from apollo13videos_table i left outer join apollo13videos b on i.idid = b.i order by i.idid desc ; ";
                                int i = 0;
                                g2.setColor(Color.WHITE);
                                g2.setFont(new Font("arial", Font.BOLD, 22));
                                ResultSet rs = st.executeQuery(sql);
                                int ii = 1;
                                int cnt = 0;
                                while(rs.next()) {
                                    if(cnt++ > 0 && ii == rs.getInt("i")) {
                                        if(cnt > 0)
                                            continue;
                                        else
                                        {
                                            ii = rs.getInt("i");
                                            if(ii !=0 ) {
                                            g2.drawString(ii + " : " + rs.getString("inputdates"), 20, 40 + i);
                                            i+=20;
                                            Point p = new Point();
                                            p.x = ii;
                                            p.y = ii;
                                            points.add(p);
                                            }
                                        }
                                        
                                        
                                    } else if(cnt == 0) {
                                        
                                        ii = rs.getInt("i");
                                    if(ii !=0 ) {
                                    g2.drawString(ii + " : " + rs.getString("inputdates"), 20, 40 + i);
                                    i+=20;
                                     Point p = new Point();
                                     p.x = ii;
                                     p.y = ii;
                                     points.add(p);
                                    }
                                    
                                    } else {
                                        
                                        ii = rs.getInt("i");
                                            if(ii !=0 ) {
                                            g2.drawString(ii + " : " + rs.getString("inputdates"), 20, 40 + i);
                                            i+=20;
                                             Point p = new Point();
                                            p.x = ii;
                                            p.y = ii;
                                             points.add(p);
                                            }
                                    }
                                    ///
                                }
                                
                            } catch(SQLException sqle) {
                                sqle.printStackTrace();
                            }


                            g2.setFont(new Font("arial", Font.BOLD, 12));
                            g2.setColor(Color.BLACK);
                            g2.drawString("Choose an REPLAY VIDEO", 10, 620);

                            g2.setColor(Color.GRAY);
                            g2.fillRect(10, 660, 60, 26);
                            if(!playPressed)
                                g2.setColor(Color.black);
                            else
                                g2.setColor(Color.YELLOW);
                            g2.drawString("PLAY", 20, 677);

                            g2.setColor(Color.GRAY);
                            g2.fillRect(80, 660, 60, 26);
                            if(!recPressed)
                                g2.setColor(Color.red);
                            else
                                g2.setColor(Color.GREEN);
                            g2.drawString("REC", 90, 677);

                            g2.setColor(Color.GRAY);
                            g2.fillRect(10, 760, 60, 26);
                            g2.setColor(Color.black);
                            g2.drawString("CLOSE", 20, 777);
                        }

                        if(!launched)
                            Thread.sleep(1000);
                    } catch(Exception e) {}
                }
            }
        };
        
        t.start();
    }
    
    private void setFrameAndPanel(JFrame frame, JPanel panel) {
        initFrameAndPanel(frame, panel);
    }
    
    private void initFrameAndPanel(JFrame frame, JPanel panel) {
    }
    
    public JPanel getPanel() {return this.p;}
    
    public JFrame getFrame() {return this.f;}
}
