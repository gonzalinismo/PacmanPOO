import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Heroe extends Personaje{
	BufferedImage[][] images= new BufferedImage[4][3];
	public Heroe(String character) {
		 try {
		       images[0][0]=ImageIO.read(getClass().getResource("imagenes/"+character+"derecha.png"));
		       images[0][1]=ImageIO.read(getClass().getResource("imagenes/"+character+"derechaSemicerrado.png"));
		       images[0][2]=ImageIO.read(getClass().getResource("imagenes/"+character+"derechaCerrado.png"));
	        }catch(Exception e) {}
	       try {
	           images[1][0]=ImageIO.read(getClass().getResource("imagenes/"+character+"izquierda.png"));
	           images[1][1]=ImageIO.read(getClass().getResource("imagenes/"+character+"izquierdaSemicerrado.png"));
	           images[1][2]=ImageIO.read(getClass().getResource("imagenes/"+character+"izquierdaCerrado.png"));
	        }catch(Exception e) {}
	       try {
	           images[2][0]=ImageIO.read(getClass().getResource("imagenes/"+character+"arriba.png"));
	           images[2][1]=ImageIO.read(getClass().getResource("imagenes/"+character+"arribaSemicerrado.png"));
	           images[2][2]=ImageIO.read(getClass().getResource("imagenes/"+character+"arribaCerrado.png"));
	       }catch(Exception e) {}
	       try {
	           images[3][0]=ImageIO.read(getClass().getResource("imagenes/"+character+"abajo.png"));
	           images[3][1]=ImageIO.read(getClass().getResource("imagenes/"+character+"abajoSemicerrado.png"));
	           images[3][2]=ImageIO.read(getClass().getResource("imagenes/"+character+"abajoCerrado.png"));
	       }catch(Exception e) {}	       	      
	}
	
	public void setImagen(){
        this.imagen=images[fila][columna];
    }
	public void update(){
    	this.setImagen();
    	if(columna<2) {
    		columna++;
    	}else {columna=0;}
    }

}
