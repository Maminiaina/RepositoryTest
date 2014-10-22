package Interface;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import org.apache.lucene.queryparser.classic.ParseException;

import Traitement.Lucene;
import Traitement.RDFManager;
import Traitement.Triplet;

/**
 *
 * @author weswes1991
 */
public class MainForm extends javax.swing.JFrame {

	private DefaultTableModel model = new DefaultTableModel(); 
	private Lucene lucene;
	public MainForm() {
        initComponents();
        
        lucene = new Lucene();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        jPanel1      = new javax.swing.JPanel();
        chemin_lab   = new javax.swing.JLabel();
        chemin_txt   = new javax.swing.JTextField();
        launch_bt    = new javax.swing.JButton();
        search_txt   = new javax.swing.JTextField();
        search_bt    = new javax.swing.JButton(new ImageIcon(getClass().getResource("search.png")));
        jScrollPane1 = new javax.swing.JScrollPane();
        jMenuBar1    = new javax.swing.JMenuBar();
        jMenu1       = new javax.swing.JMenu();
        jMenu2       = new javax.swing.JMenu();
        maTable		 = new javax.swing.JTable(model);
        
        model.addColumn("Ressource");
        model.addColumn("Prédicat");
        model.addColumn("Objet");
maTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        jPanel1.setLayout(null);
        
        chemin_lab.setText("RDF Path : ");
        chemin_lab.setBounds(5,10,70,25);
        
        chemin_txt.setBounds(75, 10, 470, 25);

        launch_bt.setText("Launch");
        launch_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	launch_btActionPerformed(evt);
            }
        });
        
        launch_bt.setBounds(545, 10, 100, 25);
        
        search_txt.setBounds(660, 10, 200, 25);
       
        search_bt.setBounds(860, 10, 28, 25);
        search_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	search_btActionPerformed(evt);
            }
        });
        
        jScrollPane1.setViewportView(maTable);
                
        jScrollPane1.setBounds(5, 40, 885, 505);
      
        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);
        
        jPanel1.add(chemin_lab);     
        jPanel1.add(chemin_txt);
        jPanel1.add(search_txt);
        jPanel1.add(search_bt);
        jPanel1.add(launch_bt);
        jPanel1.add(jScrollPane1);
        
        setJMenuBar(jMenuBar1);

        this.setContentPane(jPanel1);
     //  this.add(jPanel1,BorderLayout.CENTER);
        this.setBounds(0, 0, 900, 600);
       // this.setPreferredSize(new Dimension(900,600));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    
    }// </editor-fold>                        

    //Evennement du bouton
    private void launch_btActionPerformed(java.awt.event.ActionEvent evt)  {                                           
    	String file = chemin_txt.getText();
    	if (!file.isEmpty()) 
    	{
    		RDFManager rdf= new RDFManager();
    		
    		int result = rdf.readFileRDF(file);
    		if(result == 1 )
    		{
    			//succès
    			try {
					lucene.index(rdf.getTriplets());
				} catch (IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    			for(Triplet triplet : rdf.getTriplets())
    			{
    				model.addRow(new Object[]{triplet.getRessource().toString(), triplet.getPredicate().toString(),triplet.getObject().toString()});
    			}
    			
    		}
    		else {}//échec
    	}	
    }                                          
    
    private void search_btActionPerformed(java.awt.event.ActionEvent evt)  
    {
    	try {
    		while (model.getRowCount()!=0)
    			model.removeRow(0);
    		lucene.Search(search_txt.getText());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JTextField chemin_txt;
    private javax.swing.JButton launch_bt;
    private javax.swing.JTextField search_txt;
    private javax.swing.JButton search_bt;
    private javax.swing.JLabel chemin_lab;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable maTable;
    // End of variables declaration                   
	
	public static void main(String[] args) {
		MainForm interf = new MainForm();
		interf.setVisible(true);
	}
}
