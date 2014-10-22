package Traitement;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;

public class RDFManager {

	private ArrayList<Triplet> triplets;
	
	public ArrayList<Triplet> getTriplets() {
		return triplets;
	}
	public void setTriplets(ArrayList<Triplet> triplets) {
		this.triplets = triplets;
	}

	public RDFManager()
	{
		triplets = new ArrayList<Triplet>();
	}
	
	//Fonction permettant de charger un fichier RDF
	private InputStream loadFileRDF(String _filepath)
	{
		try
		{
			File f = new File(_filepath);
			if(f.exists())
			{
				InputStream input = FileManager.get().open(_filepath);
				return input;	
			}
			else 
				{
					System.out.println("Erreur: Fichier: "+_filepath+"  Introuvable");
					return null;
				}
		}
		catch(IllegalArgumentException   io)
		{
			System.out.println("Error:"+io.getMessage());
			return null;
		}
	}
	
	//Fonction permettant de lire un fichier rdf
	public int readFileRDF(String _filepath)
	{
		Model model = ModelFactory.createDefaultModel();
		System.out.println("ato");
		InputStream input = loadFileRDF(_filepath);
		
		if(input!=null)
		{
			model.read(input,"","N-TRIPLE");
			System.out.println("ato ko");
			StmtIterator iter = model.listStatements();
			int i=1;
			while (iter.hasNext()) {
				Statement stmt      = iter.nextStatement(); // get next statement
                Triplet tr = new Triplet(i,stmt.getSubject(), stmt.getPredicate(), stmt.getObject());
                i++;
                triplets.add(tr);	
                 
        }
			return 1;
		}
		else return 0;
	}
}
