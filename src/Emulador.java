import java.awt.*;
import java.awt.event.*;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.*;




public class Emulador extends JFrame implements ActionListener,KeyListener,ListSelectionListener,MouseListener ////////////////////////inicio de la clase////////////////////////////////////
{
	private Boolean aux=false;
	private CardLayout cl;
	private JPanel panelizq;
	private JPanel panelder,pcan1,pcan2,pcan4,pcan3;
	private Can can1,can2,can3,can4;
	private JMenuBar menub;
	private JMenu file, options;
	private JMenuItem config; 
	private  JList<String> jl;
	Connection con=null;
	private static final String NOMBRE_BASEDATOS = "test.db";
    private static final String SQL_TABLA_CONFIG ="CREATE TABLE IF NOT EXISTS 	configuraciones \n"+
								"(id INTEGER PRIMARY KEY, tamanio TEXT, sonido TEXT, cancion TEXT)";
   Connection conn = null;
   Statement stmt = null;
   ResultSet rs = null;
   PreparedStatement pstmt= null;
	Emulador()///////////////////////constructor cl /////////////////////////////////////////
	{
		
		////////////////////////////inicializo variables//////////////////////////////
		super("MAME 32");
		cl=new CardLayout();
		panelizq=new JPanel();
		panelder=new JPanel();
		jl=new JList<String>();
		can1=new Can("Pac-Man.JPG");
		can2=new Can("Battle City.PNG");
		can3=new Can("Metal slug.JPG");
		can4=new Can("Mario bros.PNG");
		String[] arreglo={"Pac-Man","Battle City","Metal Slug 3","Mario Bros"};
		jl.setListData(arreglo);
		this.setLayout(new BorderLayout());
		pcan1=new JPanel();
		pcan2= new JPanel();
		pcan3=new JPanel();
		pcan4=new JPanel();
		menub= new JMenuBar();
		file=new JMenu("File");
		options=new JMenu("Options");
		this.add(menub, BorderLayout.NORTH);
		config=new JMenuItem("Configuraciones");
		
		//////////////////////////////envian señales//////////////////////////////////////
		
		config.addActionListener(this);
		jl.addKeyListener(this);
		jl.addMouseListener(this);
		jl.getSelectionModel().addListSelectionListener(this);
		
		/////////////////////////////////////////////////////////////////////////////////////
		
		
		
		
		
		
		
		
		
		
		//////////////////////////////agrego a distintos paneles/////////////////////////////////////////
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		panelizq.add(jl);
		this.add(panelizq,BorderLayout.WEST);
		panelder.setLayout(cl);
		pcan1.add(can1);
		pcan2.add(can2);
		pcan3.add(can3);
		pcan4.add(can4);
		panelder.add(pcan1,"1");
		panelder.add(pcan2,"2");
		panelder.add(pcan3,"3");
		panelder.add(pcan4,"4");
		this.add(panelder, BorderLayout.EAST);
		menub.add(file);
		menub.add(options);
		options.add(config);
		
		/////////////////////////////////////realizo listselectionlistener//////////////////////////////////////////
		/*jl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e)
			{
				Vector<String> lista=new Vector<String>();
				lista.add("Battle City");
				lista.add("Pac-Man");
				lista.add("Metal Slug 3");
				lista.add("Mario Bros");
				//System.out.println("entra: "+jl.getSelectedValue() );
				switch(lista.indexOf(jl.getSelectedValue()))
				{
					case  0:
						cl.show(panelder, "2");
						break;
					case 1:
						cl.show(panelder, "1");
						
						System.out.println("sssssssssss");
						break;
					case 2:
						cl.show(panelder, "3");
						break;
					case 3:
						cl.show(panelder, "4");
						break;
					default:
						break;
				}
			}
			
			
		}

		);/////////////////fin de listselection///////////////////////////////////*/
		
		
		
		///////////////////////////realizo el cierre de ventana////////////////////////////////////
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});//////////////////////////fin de cierre//////////////////////////////////////////7
		
		
		this.setVisible(true);
		this.setResizable(false);
		this.pack();
		////////////////////////////////////BASE DE DATOS POR DEFECTO/////////////////////////////////
		    

		
		
	}/////////////////////////////////////////finconstructor///////////////////////////////////////

	
	public void valueChanged(ListSelectionEvent e)
	{
		Vector<String> lista=new Vector<String>();
		lista.add("Battle City");
		lista.add("Pac-Man");
		lista.add("Metal Slug 3");
		lista.add("Mario Bros");
		//System.out.println("entra: "+jl.getSelectedValue() );
		switch(lista.indexOf(jl.getSelectedValue()))
		{
			case  0:
				cl.show(panelder, "2");
				this.aux=false;
				break;
			case 1:
				cl.show(panelder, "1");
				this.aux=true;
				break;
			case 2:
				cl.show(panelder, "3");
				this.aux=false;
				break;
			case 3:
				cl.show(panelder, "4");
				this.aux=false;
				break;
			default:
				break;
		}
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{
		
		if (e.getActionCommand()==config.getActionCommand())
		{
			new ConfiguracionesEmulador();
		}
	}
	
	
	
	
	
	///////////////////ACA LO EJECUTO//////////////////////////////////////////////
	
	
	
	@Override
	public void keyPressed(KeyEvent e) {
	
		if(e.getKeyCode()==KeyEvent.VK_ENTER  && aux==true)
		{
			VideojuegoPacman game = new VideojuegoPacman();
	        Thread t= new Thread() { 
	        public void run()
	        {
	        	game.run(1.0 / 15.0);
	        }};
	        t.start();
	        this.setVisible(false);
	        
		}
			
	}


	@Override
	public void keyReleased(KeyEvent arg0)
	{
			// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		if (arg0.getClickCount()==2 && aux==true)
		{
			System.out.println("el juego");
		}
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub	
	}


	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	

}/////////////////////////////fin de la clase Emulador //////////////////////////7	




class Can extends Canvas /////////////////////inicio de clase can para cargar imagenes//////////////////////////////////////
{
	private Image icono;
	
	Can(String etiqueta)//////////////////////constructor////////////////////////////////////
	{
		this.setBackground(Color.BLACK);
		icono = new ImageIcon("src\\..\\src\\imagenes\\"+etiqueta).getImage();
		this.repaint();
		this.setMaximumSize(null);
	}//////////////////////////////fin del constructor///////////////////////////

	
	public void paint(Graphics gr)/////////////////////inicio de paint////////////////////
	{
		gr.drawImage(icono, 0, 0, this.getWidth(),this.getHeight(),this);

		
	}///////////////////////fin del paint///////////////////////
	
	
	
	public Dimension preferredSize()
	{
        return new Dimension(320,240);
    }
}////////////////////////////////fin de clase can//////////////////////////


