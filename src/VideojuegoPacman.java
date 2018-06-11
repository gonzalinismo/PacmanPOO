 
import java.awt.*;
import java.awt.event.*; //eventos
import java.awt.image.*;  //imagenes
import javax.imageio.*; //imagenes
import java.awt.Graphics2D;
import java.awt.geom.*; //Point2d
import java.util.LinkedList;
import java.io.*;



public class VideojuegoPacman extends JGame  {
   Partida partida1, partida2;
   boolean estoyEnMenu=true;
   BufferedImage img_fondo,img_fondo_menu,img_menu_jugador1,img_menu_jugador2,img_menu_ranking,img_menu_config,cursor;
   Personaje pildora[];
   Pacdot  pacdot[];
   Heroe heroe;
   int posCursor[],indiceCursor;
  
	


    public VideojuegoPacman() {

        super("VideojuegoPacman", 800, 600);
        try {
        	
        }catch(Exception e) {	System.out.println("s");}
        
        
       
        pacdot=new Pacdot[240];
        pildora=new Personaje[4];
        heroe=new Heroe("pacman");
        posCursor=new int[4];
        indiceCursor=0;
        posCursor[0]=400; posCursor[1]=447; posCursor[2]=493;posCursor[3]=537;
        try{
        	img_fondo_menu=ImageIO.read(getClass().getResource("/imagenes/fondomenu.png"));
        	img_menu_jugador1=ImageIO.read(getClass().getResource("/imagenes/jugador1.png"));
        	img_menu_jugador2=ImageIO.read(getClass().getResource("/imagenes/jugadores2.png"));
        	img_menu_ranking=ImageIO.read(getClass().getResource("/imagenes/ranking.png"));
        	img_menu_config=ImageIO.read(getClass().getResource("/imagenes/configuracion.png"));
        	cursor=ImageIO.read(getClass().getResource("/imagenes/cursor.png"));
        	
        	
        }catch(Exception e) {}
    }

    public void gameStartup() 
    {
    	
    	
    }

    public void gameUpdate(double delta)
    {
        Keyboard keyboard = this.getKeyboard();
        
        if(estoyEnMenu) {
        	if (keyboard.isKeyPressed(KeyEvent.VK_ENTER) && indiceCursor==0 )
        	{
        		estoyEnMenu=false;
        		partida1=new Partida();
        		img_fondo=partida1.urlFondo();
        		pacdot=partida1.arregloPacdot();
        		heroe=partida1.getPersonaje();
        		pildora=partida1.arregloPildora();
        	}
        	else if(keyboard.isKeyPressed(KeyEvent.VK_DOWN))
        	{
        		indiceCursor=(indiceCursor+1) % 4;
        	}
        	else if(keyboard.isKeyPressed(KeyEvent.VK_UP) && (indiceCursor-1)!=-1 )
        	{
        		indiceCursor=(indiceCursor-1) % 4;
        	}
        	else if(keyboard.isKeyPressed(KeyEvent.VK_UP) && (indiceCursor-1)==-1 )
        	{
        		indiceCursor=3;
        	}
        }else
        {
        	partida1.update(keyboard);
        }
        	
        
        	
        	

    }

        


    

    public void gameDraw(Graphics2D g) {
    	
		g.drawImage(img_fondo,0,10,null);// imagen de fondo        
        g.drawString("Tecla ESC = Fin del Juego ",592,42);
        if (estoyEnMenu==true)                                       /////////////verifico si estoy en menu para mostrar el menu
        {
        	g.drawImage(img_fondo_menu,0,10,800,600,null);
        	g.drawImage(img_menu_jugador1,250,400,300,30,null);
        	g.drawImage(img_menu_jugador2,253,450,300,30,null);
        	g.drawImage(img_menu_ranking,233,490,300,50,null);
        	g.drawImage(img_menu_config,250,530,300,50,null);
        	g.drawImage(cursor,225,posCursor[indiceCursor],30,35,null);// apunta a 1jugador
        	
        	
        		
        }else {
        
        
        
    	g.setColor(Color.white);
    	for(int i=0; i<240; i++)
    		pacdot[i].draw(g);
    	for(int i=0; i<4; i++)
    		pildora[i].draw(g);
    	heroe.draw(g);
    	g.setFont(new Font ("Monospaced",Font.BOLD,30));
    	g.drawString(partida1.getPuntaje(),180+15*8,19+15*33);
        }
    }


    public void gameShutdown() {
       Log.info(getClass().getSimpleName(), "Shutting down game");
       System.exit(0);
    }
}

