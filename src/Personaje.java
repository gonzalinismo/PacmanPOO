import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Personaje extends ObjetoGrafico{
	int fila=0, columna=0;
    BufferedImage imagen=null;
    private Point2D.Double posicion  = new Point2D.Double();
    int[] posMat=new int[2];
    public Personaje(){
    }
    public void setImagen() {};
    public void setImagen(BufferedImage img) {
    	imagen=img;
    }
    public void setPosicion(double x, double y){
        posicion.setLocation(x, y);
    }
    public void setX(double x){
   
    	posicion.x=x;
    	
    }
    public void setY(double y){
    	
        posicion.y=y;
    	
    }
    public double getX(){
        return posicion.getX(); 
    }
    public double getY(){
    	
        return posicion.getY(); 
    	
    }
    public void update(String a){
    	imagen= null;
    }
    public void update() {}
    public void draw(Graphics2D g){
        g.drawImage(imagen,(int)posicion.getX(),(int)posicion.getY(),null);

    }
    }
