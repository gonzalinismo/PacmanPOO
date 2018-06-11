import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//--------------definicion de clase--------------
public class ConfiguracionesEmulador extends JDialog implements ActionListener, KeyListener {
//--------------atributos--------------
private boolean auxarriba,auxabajo,auxizquierda,auxderecha,auxpausa;
private int[] datosConfig;		
Connection con=null;
String sql;
final String NOMBRE_BASEDATOS = "test.db";
Connection conn = null;
Statement stmt = null;
ResultSet rs = null;
PreparedStatement pstmt= null;


private JLabel labelNorthPrincipal;
private JPanel panelBotoneraCenterPrincipal;

private JButton botTeclas;
private JButton botGeneral;
private JPanel panelCardConfig;

private CardLayout cardLayoutConfig;
private JPanel panelConfigTeclas;

private JPanel panelConfigGral;

private BorderLayout borderPanelConfigTeclas;
private BorderLayout borderPanelConfigGral;
private JLabel labelPanelConfigTeclas;
private JLabel labelPanelConfigGral;
private JPanel panelGridTeclas;
private GridLayout gridLayoutConfigTeclas;
private JButton botGuardarCambios;
private JButton botPredTeclas;
private JLabel labelPausa;
private JLabel labelArriba;
private JLabel labelAbajo;
private JLabel labelDerecha;
private JLabel labelIzquierda;
private JButton botonArriba;
private JButton botonAbajo;
private JButton botonDerecha;
private JButton botonIzquierda;
private JButton botGuardarGral;
private JButton botReset;
private JButton botonPausa;
private JPanel panelGridGral;

private JLabel labelVentana;
private JLabel labelsonido;
private CheckboxGroup tamVentanas;
private CheckboxGroup sonido;
private JPanel panelChckVent;

private Checkbox checkboxFull;
private Checkbox checkboxMin;
private JPanel panelSonidoonoff;
private Checkbox checkboxSonOn;
private Checkbox checkboxSonOff;
private JPanel BotoneraSouth;

private FlowLayout LayBotSouth;
private JPanel BotoneraSouthTec;

private FlowLayout LayBotSouthTec;
private Boolean ventana= true;  /// 	Estos son los valores de los checkbox
private Boolean maximizado= false;
private Boolean sonidoOn=true;
private Boolean sonidoOff=false;
//--------------constructor--------------
public ConfiguracionesEmulador(){
	//---------------------crear objetos
	
	labelNorthPrincipal = new JLabel("CONFIGURACIONES",JLabel.CENTER);
	panelBotoneraCenterPrincipal = new JPanel();
	BotoneraSouth= new JPanel();
	BotoneraSouthTec= new JPanel();
	LayBotSouth= new FlowLayout();
	LayBotSouthTec= new FlowLayout();
	botTeclas = new JButton("Config. teclas");
	botGeneral = new JButton("Config general");
	panelCardConfig = new JPanel();
	cardLayoutConfig = new CardLayout();
	panelConfigTeclas = new JPanel();
	panelConfigGral = new JPanel();
	borderPanelConfigTeclas = new BorderLayout();
	borderPanelConfigGral = new BorderLayout();
	labelPanelConfigTeclas = new JLabel("Configuracion de teclado",JLabel.CENTER);
	labelPanelConfigGral = new JLabel("Configuraciones Generales",JLabel.CENTER);
	panelGridTeclas = new JPanel();
	gridLayoutConfigTeclas = new GridLayout(5,2);
	botGuardarCambios = new JButton("Guardar Cambios");
	botPredTeclas = new JButton("RESET");
	labelPausa=new JLabel("Pausar",JLabel.CENTER);
	labelArriba = new JLabel("Mover hacia arriba",JLabel.CENTER);
	labelAbajo = new JLabel("Mover hacia abajo",JLabel.CENTER);
	labelDerecha = new JLabel("Mover hacia la derecha",JLabel.CENTER);
	labelIzquierda = new JLabel("Mover hacia la izquierda",JLabel.CENTER);
	botonArriba = new JButton("Arriba");
	botonAbajo = new JButton("Abajo");
	botonDerecha = new JButton("Derecha");
	botonIzquierda = new JButton("Izquierda");
	botGuardarGral = new JButton("Guardar Cambios");
	botReset = new JButton("RESET");
	panelGridGral = new JPanel();
	GridLayout gridLayout2 = new GridLayout(3,2);
	labelVentana = new JLabel("Tamaño ventana",JLabel.LEFT);
	labelsonido = new JLabel("Sonido",JLabel.LEFT);
	panelChckVent = new JPanel();
	tamVentanas= new CheckboxGroup();
	sonido= new CheckboxGroup();
	datosConfig= new int[5];
	botonPausa= new JButton("P");
	/////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////
	
	
	
	
	
	checkboxFull = new Checkbox("Fullscreen",maximizado, tamVentanas);
	checkboxMin = new Checkbox("Ventana",ventana, tamVentanas);
	panelSonidoonoff = new JPanel();
	checkboxSonOn = new Checkbox("On",sonidoOn, sonido);
	checkboxSonOff = new Checkbox("Off",sonidoOff, sonido);
	////////////////////////////////////////////////////////////////
	

	
	
	
	
	//---------------------asignar layouts y agregar objetos a contenedores
	BotoneraSouth.setLayout(LayBotSouth);
	BotoneraSouthTec.setLayout(LayBotSouthTec);
	panelBotoneraCenterPrincipal.add(botGeneral);
	panelBotoneraCenterPrincipal.add(botTeclas);
	panelCardConfig.setLayout(cardLayoutConfig);
	panelCardConfig.add(panelConfigTeclas,"teclas");
	panelCardConfig.add(panelConfigGral,"generales");
	panelConfigTeclas.setLayout(borderPanelConfigTeclas);
	panelConfigTeclas.add(labelPanelConfigTeclas,BorderLayout.NORTH);
    panelConfigTeclas.add(panelGridTeclas,BorderLayout.CENTER);
    panelConfigTeclas.add(BotoneraSouthTec, BorderLayout.SOUTH);
	panelConfigGral.add(BotoneraSouth);
	BotoneraSouth.add(botGuardarGral);
	BotoneraSouth.add(botReset);
	BotoneraSouthTec.add(botGuardarCambios);
	BotoneraSouthTec.add(botPredTeclas);
	panelConfigGral.setLayout(borderPanelConfigGral);
	panelConfigGral.add(labelPanelConfigGral,BorderLayout.NORTH);
	panelConfigGral.add(panelGridGral,BorderLayout.CENTER);
	panelConfigGral.add(BotoneraSouth, BorderLayout.SOUTH);
	panelGridTeclas.setLayout(gridLayoutConfigTeclas);
	panelGridTeclas.add(labelArriba);
	panelGridTeclas.add(botonArriba);
	panelGridTeclas.add(labelAbajo);
	panelGridTeclas.add(botonAbajo);
	panelGridTeclas.add(labelDerecha);
	panelGridTeclas.add(botonDerecha);
	panelGridTeclas.add(labelIzquierda);
	panelGridTeclas.add(botonIzquierda);
	panelGridTeclas.add(labelPausa);
	panelGridTeclas.add(botonPausa);
	panelGridGral.setLayout(gridLayout2);
	panelGridGral.add(labelVentana);
	panelGridGral.add(panelChckVent);
	panelGridGral.add(labelsonido);
	panelGridGral.add(panelSonidoonoff);
	panelChckVent.add(checkboxFull);
	panelChckVent.add(checkboxMin);
	panelSonidoonoff.add(checkboxSonOn);
	panelSonidoonoff.add(checkboxSonOff);
	cardLayoutConfig.show(panelCardConfig,"teclas");
	////////////////////////////////////////////////////////////////SEÑALES///////////////////////////////////////////////	
	botReset.addActionListener(this);
	botonAbajo.addKeyListener(this);
	botonDerecha.addKeyListener(this);
	botonIzquierda.addKeyListener(this);
	botonAbajo.addActionListener(this);
	botonDerecha.addActionListener(this);
	botonIzquierda.addActionListener(this);
	botonPausa.addActionListener(this);
	botonPausa.addKeyListener(this);
	botonArriba.addKeyListener(this);
	botonArriba.addActionListener(this);
	botGeneral.addActionListener(this);
	botTeclas.addActionListener(this);
	botGuardarCambios.addActionListener(this);
	botPredTeclas.addActionListener(this);
	botGuardarGral.addActionListener(this);
	
	
	////////////////////////////////////////////////////////////////FINDESEÑALES	////////////////////////////////////////////////////////////////
	try {
		String url = "jdbc:sqlite:"+NOMBRE_BASEDATOS;
        conn = DriverManager.getConnection(url); // Si no existe crea el archivo de la base de datos
       	System.out.println("Conectado a  SQLite.");

       	stmt = conn.createStatement();
       	sql="SELECT * FROM teclado";
        rs = stmt.executeQuery(sql);
        int i=0;
        while(rs.next()){
             System.out.println(rs.getString("teclado") +" " + rs.getInt("valor") );
             datosConfig[i]=rs.getInt("valor");
             i++; 
         }
        
        sql="SELECT * FROM botonesname";
        rs = stmt.executeQuery(sql);
         i=0;
        while(rs.next()){
        	System.out.println(rs.getString("botones") +" " + rs.getString("valor") );
             if (i==0)
             	botonArriba.setText(rs.getString("valor"));
             else if (i==1)
              	botonAbajo.setText(rs.getString("valor"));
             else if (i==2)
              	botonIzquierda.setText(rs.getString("valor"));
             else if (i==3)
              	botonDerecha.setText(rs.getString("valor"));
             else if(i==4)
            	 botonPausa.setText(rs.getString("valor"));
             i++; 
         }
       
        
        
        sql="SELECT * FROM configuraciones";
        rs = stmt.executeQuery(sql);
         i=0;
        while(rs.next()){
        	System.out.println(rs.getString("conf") +" " + rs.getBoolean("valor") );
             if (i==0 && rs.getBoolean("valor")==true)
            	 tamVentanas.setSelectedCheckbox(checkboxFull);
             else if (rs.getBoolean("valor")==false)
            	 tamVentanas.setSelectedCheckbox(checkboxMin);
             if (i==1 && rs.getBoolean("valor")==true)
            	 sonido.setSelectedCheckbox(checkboxSonOn);
             else if(rs.getBoolean("valor")==false)
            	 sonido.setSelectedCheckbox(checkboxSonOff);
             i++; 
         }
       
        
        
        
        
        stmt.close();
     	
        
    } catch (SQLException ee) {
        System.out.println(ee.getMessage());
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }


	

	
	
		
	
	
	
	
	this.setResizable(false);
    this.setLocationRelativeTo(null);
	//---------------------asignar layout y agregar objetos a 'this'
	this.add(labelNorthPrincipal,BorderLayout.NORTH);
	this.add(panelBotoneraCenterPrincipal,BorderLayout.CENTER);
	this.add(panelCardConfig,BorderLayout.SOUTH);

	//set automatic size and show
	this.setModalityType(DEFAULT_MODALITY_TYPE);
	this.pack();
	this.setVisible(true);
	
}//end constructor















/////////////////////////////////////////////////////ACTION LISTENERR///////////////////////////////////////////////////


	public void actionPerformed(ActionEvent e)
	{
		if(botGeneral.getActionCommand()==e.getActionCommand())
			cardLayoutConfig.show(panelCardConfig, "generales");
		if(botTeclas.getActionCommand()==e.getActionCommand())
			cardLayoutConfig.show(panelCardConfig, "teclas");
		if(botonArriba.getActionCommand()==e.getActionCommand() && auxarriba==false) {
			auxarriba=true;
			auxabajo=false;
			auxizquierda=false;
			auxderecha=false;
			auxpausa=false;
		}
		else if (botonArriba.getActionCommand()==e.getActionCommand() && auxarriba==true) 
			auxarriba=false;
			
		if(botonAbajo.getActionCommand()==e.getActionCommand() && auxabajo==false) {
			auxabajo=true;
			auxarriba=false;
			auxizquierda=false;
			auxderecha=false;
			auxpausa=false;
		}
		else if (botonAbajo.getActionCommand()==e.getActionCommand() && auxabajo==true)
			auxabajo=false;

		if(botonIzquierda.getActionCommand()==e.getActionCommand() && auxizquierda==false) {
			auxabajo=false;
			auxarriba=false;
			auxizquierda=true;
			auxderecha=false;
			auxpausa=false;
			
		}
		else if (botonIzquierda.getActionCommand()==e.getActionCommand() && auxizquierda==true)
			auxizquierda=false;

		if(botonDerecha.getActionCommand()==e.getActionCommand() && auxderecha==false) {
			auxabajo=false;
			auxarriba=false;
			auxizquierda=false;
			auxderecha=true;
			auxpausa=false;
			
		}
		else if (botonDerecha.getActionCommand()==e.getActionCommand() && auxderecha==true)
			auxderecha=false;
		
		if(botonPausa.getActionCommand()==e.getActionCommand() && auxpausa==false) {
			auxabajo=false;
			auxarriba=false;
			auxizquierda=false;
			auxderecha=false;
			auxpausa=true;
			
		}
		else if (botonPausa.getActionCommand()==e.getActionCommand() && auxpausa==true)
			auxpausa=false;

		
		
		////////////////////////////////////////////////////INTERACTUA CON LA BASE DE DATOS//////////////////////////////////////
		if(botGuardarCambios.getActionCommand()==e.getActionCommand() ||botGuardarGral.getActionCommand()==e.getActionCommand())
		{
			//Connection con=null;
			String sql;
			final String NOMBRE_BASEDATOS = "test.db";
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			PreparedStatement pstmt= null;
			 try {
				String url = "jdbc:sqlite:"+NOMBRE_BASEDATOS;
				int result;
	            conn = DriverManager.getConnection(url); // Si no existe crea el archivo de la base de datos
	           	System.out.println("Conectado a  SQLite.");
	
	           	stmt = conn.createStatement();
	           	//if (checkboxFull.getState()==true)
	           		//result=1;         	
	           	//else
	           		//result=0;
	           	
	           	//sql= "INSERT INTO configuraciones (conf,valor) values('pantallac',"+result +")";
	           	
	           	
	           //	stmt.executeUpdate(sql);
	           	
	           	
	           	//if (checkboxSonOn.getState()==true)
	           		//result=1;
	           	//else 
	           		//result=0;
	           		
	           
	           		//sql= "INSERT INTO configuraciones (conf,valor) values('sonido',"+result +")";
	           		
	           		
	           		if (checkboxSonOn.getState()==true) {
	           			result=1;
	           		}
		           	else 
		           		result=0;
	           		
	           		sql="UPDATE configuraciones SET valor=? where conf='sonido'";
		            pstmt=conn.prepareStatement(sql);
		            pstmt.setInt(1,result );
		            pstmt.executeUpdate();

	           		
	           		
	           		
	           		
	           		if (checkboxFull.getState()==true) {
	           		
	           			result=1;         	
	           		}
		           	else
		           		result=0;
	           		
	           		//stmt.executeUpdate(sql);
	           		sql="UPDATE configuraciones SET valor=? where conf='pantallac'";
		            pstmt=conn.prepareStatement(sql);
		            pstmt.setInt(1,result );
		            pstmt.executeUpdate();

	           		
	           	
	            sql="SELECT * FROM configuraciones";
	            rs = stmt.executeQuery(sql);
	            while(rs.next()){
                 System.out.println(rs.getString("conf") +" " + rs.getBoolean("valor") );

                  
             }
	            
	            sql="UPDATE teclado SET valor=? where teclado='arriba'";
	            pstmt=conn.prepareStatement(sql);
	            pstmt.setInt(1,datosConfig[0] );
	            pstmt.executeUpdate();
	            
	            sql="UPDATE teclado SET valor=? where teclado='abajo'";
	            pstmt=conn.prepareStatement(sql);
	            pstmt.setInt(1,datosConfig[1] );
	            pstmt.executeUpdate();
	            
	            sql="UPDATE teclado SET valor=? where teclado='izquierda'";
	            pstmt=conn.prepareStatement(sql);
	            pstmt.setInt(1,datosConfig[2] );
	            pstmt.executeUpdate();
	            
	            sql="UPDATE teclado SET valor=? where teclado='derecha'";
	            pstmt=conn.prepareStatement(sql);
	            pstmt.setInt(1,datosConfig[3] );
	            pstmt.executeUpdate();
	            
	            sql="UPDATE teclado SET valor=? where teclado='pausa'";
	            pstmt=conn.prepareStatement(sql);
	            pstmt.setInt(1,datosConfig[4] );
	            pstmt.executeUpdate();
	            
	            sql="SELECT * FROM teclado";
	            rs = stmt.executeQuery(sql);
	            while(rs.next()){
	                 System.out.println(rs.getString("teclado") +" " + rs.getInt("valor") );

	                  
	             }
	            //sql="INSERT INTO botonesname(botones,valor) values('arriba','"+ nombres[0] +"'),('abajo','"+ nombres[1] +"'),('izquierda','"+ nombres[2] +"'),('derecha','"+ nombres[3] +"')";
	            //stmt.executeUpdate(sql);
	            
	            sql="UPDATE botonesname SET valor=? where botones='arriba'";
	            pstmt=conn.prepareStatement(sql);
	            pstmt.setString(1,botonArriba.getActionCommand()/*nombres[0]*/ );
	            pstmt.executeUpdate();
	            
	            sql="UPDATE botonesname SET valor=? where botones='abajo'";
	            pstmt=conn.prepareStatement(sql);
	            pstmt.setString(1,botonAbajo.getActionCommand()/*nombres[1]*/ );
	            pstmt.executeUpdate();
	            
	            sql="UPDATE botonesname SET valor=? where botones='izquierda'";
	            pstmt=conn.prepareStatement(sql);
	            pstmt.setString(1,botonIzquierda.getActionCommand()/*nombres[2]*/ );
	            pstmt.executeUpdate();
	            
	            sql="UPDATE botonesname SET valor=? where botones='derecha'";
	            pstmt=conn.prepareStatement(sql);
	            pstmt.setString(1,botonDerecha.getActionCommand()/*nombres[3]*/ );
	            pstmt.executeUpdate();
	            
	            sql="UPDATE botonesname SET valor=? where botones='pausa'";
	            pstmt=conn.prepareStatement(sql);
	            pstmt.setString(1,botonPausa.getActionCommand()/*nombres[4]*/ );
	            pstmt.executeUpdate();
	            
	            sql="SELECT * FROM botonesname";
	            rs = stmt.executeQuery(sql);
	            while(rs.next()){
                 System.out.println(rs.getString("botones") +" " + rs.getString("valor") );

                  
             }

	            
	            
	            
	            stmt.close();
	         	
	            
	        } catch (SQLException ee) {
	            System.out.println(ee.getMessage());
	            try {
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (SQLException ex) {
	                System.out.println(ex.getMessage());
	            }
	        
	        }


			
			
		}
		
		if(botReset.getActionCommand()==e.getActionCommand() || botPredTeclas.getActionCommand()==e.getActionCommand())
		{
			tamVentanas.setSelectedCheckbox(checkboxMin);
			sonido.setSelectedCheckbox(checkboxSonOn);
			botonArriba.setText("Arriba");
			botonAbajo.setText("Abajo");
			botonDerecha.setText("Derecha");
			botonIzquierda.setText("Izquierda");
			botonPausa.setText("P");
			
			
		}
		
		
		
	}
	//////////////////////////////////////////////////////////FIN ACTION LISTENER///////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//////////////////////////////////////////////////////////KEY LISTENER///////////////////////////////////////////////
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent arg0)
	{
		
		if(auxarriba==true)
		{
			botonArriba.setText(KeyEvent.getKeyText(arg0.getKeyCode()));
			//nombres[0]=KeyEvent.getKeyText(arg0.getKeyCode());
			datosConfig[0]=arg0.getKeyCode();
			auxarriba=false;
		}
		else if(auxabajo==true)
		{
			botonAbajo.setText(KeyEvent.getKeyText(arg0.getKeyCode()));
			//nombres[1]=KeyEvent.getKeyText(arg0.getKeyCode());
			datosConfig[1]=arg0.getKeyCode();
			auxabajo=false;
		}
		else if(auxizquierda==true)
		{
			botonIzquierda.setText(KeyEvent.getKeyText(arg0.getKeyCode()));
			datosConfig[2]=arg0.getKeyCode();
			auxizquierda=false;
		}
		else if(auxderecha==true)
		{
			botonDerecha.setText(KeyEvent.getKeyText(arg0.getKeyCode()));
			datosConfig[3]=arg0.getKeyCode();
			auxderecha=false;
		}
		else if(auxpausa==true)
		{
			botonPausa.setText(KeyEvent.getKeyText(arg0.getKeyCode()));
			datosConfig[4]=arg0.getKeyCode();
			auxpausa=false;
		}
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub	
	}
	//////////////////////////////////////////////////FIN KEY LISTENER///////////////////////////////////////////////////////////////
	
}//end class
