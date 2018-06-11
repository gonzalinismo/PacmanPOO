import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public abstract class ObjetoGrafico {
	int fila=0, columna=0;
    BufferedImage imagen=null;
    Point2D.Double posicion  = new Point2D.Double();
    int[] posMat=new int[2];
    public ObjetoGrafico() {}
    abstract void setImagen();
    abstract void setPosicion(double x, double y);
    abstract void setX(double x); 
    abstract void setY(double y) ;
    abstract double getX();
    abstract double getY();
    abstract void update();
    abstract void draw(Graphics2D g);
}
