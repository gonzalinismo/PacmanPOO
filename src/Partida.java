import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.imageio.ImageIO;

import com.sun.media.jfxmedia.events.PlayerEvent;

import java.net.*; //nuevo para sonido
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;


public class Partida 
{
	
	private int izquierda=37,derecha=39,arriba=38,abajo=40;
	BufferedImage img_fondo = null;
	 BufferedImage pacdot= null;
	 BufferedImage pildorita=null;
     Heroe heroe=new Heroe("pacman");
     Pacdot[] pacdots= new Pacdot[240];
     Personaje[] pildora=new Personaje[4];
     int celda=15,horizontal=0, vertical=0, inicioGridHorizontal=180, inicioGridVertical=15,cantpac=0,cantpaccom=0, cantpil=0, puntaje=0, nivel=1;
     int[][] matriz= new int[31][28]; //por defecto se inicializa con todo 0 por lo que las paredes serán representadas con un 0
     JFXPanel fxPanel = new JFXPanel();
     Media mediaSirena, mediaWaka;
     MediaPlayer sirena, waka;
     URL resource; 
     
  
	 Partida()
	 {
	    	Connection conn = null;
			   Statement stmt = null;
			   ResultSet rs = null;
			   PreparedStatement pstmt= null;
			   try{
				   conn = DriverManager.getConnection("jdbc:sqlite:test.db");
				   stmt = conn.createStatement();
				   
				   rs = stmt.executeQuery("Select valor From teclado ");
				   int indice=0;
				   while(rs.next())
				   {
	                  if(indice==0) {
	                	  arriba=rs.getInt("valor");
	                	  indice++;
	                	  }
	                  else if(indice==1) {
	                	  abajo=rs.getInt("valor");
	                	  indice++;
	                  }
	                  else if(indice==2) {
	                	  izquierda=rs.getInt("valor");
	                	  indice++;
	                  }
	                  else if(indice==3) {
	                	  derecha=rs.getInt("valor");
	                	  indice++;
	                  }
	               }

				   stmt.close();
				   
				   
			   }catch(Exception e) { 
				   System.out.println(e.getMessage());
	           try {
	               if (conn != null) {
	                   conn.close();
	               }
	           } catch (SQLException ex) {
	               System.out.println(ex.getMessage());
	           }
	       }
		 
		 
		   resource= getClass().getResource("/sonidos/PacmanWaka.mp3");
		   mediaWaka = new Media(resource.toString());
	       waka = new MediaPlayer(mediaWaka);
	       resource= getClass().getResource("/sonidos/PacmanSirena.mp3");
		   mediaSirena = new Media(resource.toString());
	       sirena = new MediaPlayer(mediaSirena);
	       sirena.setCycleCount(MediaPlayer.INDEFINITE);
	       sirena.play();
		       

	
		 for(int i=0; i<240; i++) 
	    		pacdots[i] = new Pacdot();
	        pildora[0]= new Personaje();
	        pildora[1]= new Personaje();
	        pildora[2]= new Personaje();
	        pildora[3]= new Personaje();
	        
	    	try {		
				pacdot=ImageIO.read(getClass().getResource("/imagenes/pac-dot.png"));
				for(int i=0; i<240; i++) 
			    	pacdots[i].setImagen(pacdot);   		
	    		}catch(Exception e) {}
	    	
	    	try {		
				pildorita=ImageIO.read(getClass().getResource("/imagenes/pildora.png"));
				for(int i=0; i<4; i++) 
			    	pildora[i].setImagen(pildorita);   		
	    		}catch(Exception e) {}    		    	
	    	try{
	            img_fondo= ImageIO.read(getClass().getResource("/imagenes/fondo.jpg"));
	            heroe.setImagen();
	        }
	        catch(Exception e){}
	    	
	    	String[] valores;
	    	try {
	    		 BufferedReader br = new BufferedReader(new FileReader("matrizpacprueba.csv"));
	    		for(int i=0;i<31; i++) {
	    			String line=null;
	    			line=br.readLine();
	    			valores= line.split(",");
	    			for(int j=0;j<28; j++){
	    				switch( Integer.parseInt(valores[j]) ) {
	    				case 2:
	    		            heroe.setPosicion(inicioGridHorizontal+celda*15,inicioGridVertical+celda*23.5);
	    		            heroe.posMat[0]=23;
	    		            heroe.posMat[1]=15;
	    					matriz[i][j]=3;//heroe
	    					break;
	    				case 3:
	    					pacdots[cantpac].setPosicion(inicioGridHorizontal+celda*j,inicioGridVertical+15+celda*i);
	    					pacdots[cantpac].posMat[0]=i;
	    					pacdots[cantpac].posMat[1]=j;
	    					cantpac++;
	    					matriz[i][j]=2;//pacdots
	    					break;
	    				case 0:	    					
	    					matriz[i][j]=1;//camino
	    					break;
	    				case 4:
	    					pildora[cantpil].setPosicion(inicioGridHorizontal+celda*j,inicioGridVertical+15+celda*i);
	    					pildora[cantpil].posMat[0]=i;
	    					pildora[cantpil].posMat[1]=j;
	    					cantpil++;
	    					matriz[i][j]=4;//pildora
	    					break;
	    				default:
	    					break;
	    				}
	    			}
	    		}
	    }
	    	
	    	catch(Exception e) {}

	 }
	 
	 
	 
	 
	 
	 public void  update (Keyboard keyboard)
	 {	
		 
		 resource = getClass().getResource("/sonidos/PacmanSiren.mp3");
		 switch(matriz[heroe.posMat[0]+(vertical/15)][heroe.posMat[1]+(horizontal/15)]) {
			 case 1:
			 case 3:	 
				 heroe.setPosicion(heroe.getX()+(horizontal),heroe.getY()+(vertical) );//cambio de posicion por coordenadas
		         matriz[heroe.posMat[0]][heroe.posMat[1]]=1; //la posicion anterior del heroe vuelve a er 1
		         heroe.posMat[0]=heroe.posMat[0]+(vertical/15);  // se cambia el valor en la matriz y la posicion almacenada en el heroe
		         heroe.posMat[1]=heroe.posMat[1]+(horizontal/15);
		         matriz[heroe.posMat[0]][heroe.posMat[1]]=3;
		         heroe.update();
		     
		         
		         
				 break;
			 case 2:
				 heroe.setPosicion(heroe.getX()+(horizontal),heroe.getY()+(vertical) );//cambio de posicion por coordenadas
		         matriz[heroe.posMat[0]][heroe.posMat[1]]=1; //la posicion anterior del heroe vuelve a er 1
		         heroe.posMat[0]=heroe.posMat[0]+(vertical/15);  // se cambia el valor en la matriz y la posicion almacenada en el heroe
		         heroe.posMat[1]=heroe.posMat[1]+(horizontal/15);
		         matriz[heroe.posMat[0]][heroe.posMat[1]]=3;
		         heroe.update();
		         puntaje=puntaje+(10*nivel);
		         for(int i=0;i<240;i++) {
			         if(pacdots[i].posMat[0]==heroe.posMat[0] && pacdots[i].posMat[1]==heroe.posMat[1]) {
						 pacdots[i].update();
					 }
				 }
		    
		         waka.play();
				 break;
			 case 4:
				 heroe.setPosicion(heroe.getX()+(horizontal),heroe.getY()+(vertical) );//cambio de posicion por coordenadas
		         matriz[heroe.posMat[0]][heroe.posMat[1]]=1; //la posicion anterior del heroe vuelve a er 1
		         heroe.posMat[0]=heroe.posMat[0]+(vertical/15);  // se cambia el valor en la matriz y la posicion almacenada en el heroe
		         heroe.posMat[1]=heroe.posMat[1]+(horizontal/15);
		         matriz[heroe.posMat[0]][heroe.posMat[1]]=3;
		         heroe.update();
		         for(int i=0;i<4;i++) {
			         if(pildora[i].posMat[0]==heroe.posMat[0] && pildora[i].posMat[1]==heroe.posMat[1]) {
						 pildora[i].update("me borro");
					 }
				 }
		         puntaje=puntaje+(50*nivel);
				 break;	 	 
			  default:
					break;
			 }
			if((heroe.posMat[0]==14 && heroe.posMat[1]==1) && horizontal<0){
				 heroe.setPosicion(570, 232.5 );//cambio de posicion por coordenadas
		         matriz[heroe.posMat[0]][heroe.posMat[1]]=1; //la posicion anterior del heroe vuelve a er 1
		         heroe.posMat[0]=14;  // se cambia el valor en la matriz y la posicion almacenada en el heroe
		         heroe.posMat[1]=26;
		         matriz[heroe.posMat[0]][heroe.posMat[1]]=3;
		         heroe.update();
				 
			 }
			 if((heroe.posMat[0]==14 && heroe.posMat[1]==26) && horizontal>0){
				 heroe.setPosicion(195, 232.5 );//cambio de posicion por coordenadas
		         matriz[heroe.posMat[0]][heroe.posMat[1]]=1; //la posicion anterior del heroe vuelve a er 1
		         heroe.posMat[0]=14;  // se cambia el valor en la matriz y la posicion almacenada en el heroe
		         heroe.posMat[1]=1;
		         matriz[heroe.posMat[0]][heroe.posMat[1]]=3;
		         heroe.update();
				 
			 }
			 System.out.print("x: "+heroe.getX());
			 System.out.print("  y: "+heroe.getY());
			 System.out.print("x: "+heroe.posMat[0]);
			 System.out.println("x: "+heroe.posMat[1]);

	        // Procesar teclas de direccion
	        if (keyboard.isKeyPressed(arriba)){
	            
	            heroe.fila=2;
	            heroe.columna=0;
	            heroe.setImagen();
	        	horizontal=0;
	        	vertical=-celda;
	        }

	        if (keyboard.isKeyPressed(abajo)){
	            
	            heroe.fila=3;
	            heroe.columna=0;
	            heroe.setImagen();
	        	horizontal=0;
	        	vertical=celda;
	        }

	        if (keyboard.isKeyPressed(izquierda)){
	            
	            heroe.fila=1;
	            heroe.columna=0;
	            heroe.setImagen();	            
	        	horizontal=-celda;
	        	vertical=0;
	        }

	        if (keyboard.isKeyPressed(derecha)){
	            
	            heroe.fila=0;
	            heroe.columna=0;
	            heroe.setImagen();
	        	horizontal=celda;
	        	vertical=0;            
	        }      
	        if (keyboard.isKeyPressed(KeyEvent.VK_SPACE)){
	            horizontal=0;
	        	vertical=0;
	        }

	        // Esc fin del juego
	        LinkedList < KeyEvent > keyEvents = keyboard.getEvents();
	        for (KeyEvent event: keyEvents) {
	            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
	                (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
	            	
	            	for(int j=0; j<31;j++) {//avanzo por filas
	            		for(int i=0; i<28;i++) {//avanzo por columnas
	    	        		System.out.print(" | "+matriz[j][i]);
	    	        	}
	        		System.out.println( "|");
	            	}
	                System.exit(0);
	            }

	        }
	        
	        
	 }
	 public int[][] getMatriz()
	 {
		 return matriz;
	 }
	 public BufferedImage urlFondo()
	 {
		 return img_fondo;
	 }
	 public Pacdot[] arregloPacdot()
	 {
		 return pacdots;
	 }
	 public Personaje[] arregloPildora()
	 {
		 return pildora;
	 }
	 public Heroe getPersonaje()
	 {
		 return heroe;
	 }
	 public String getPuntaje() {
		 return(String.valueOf(puntaje));
	 }
}