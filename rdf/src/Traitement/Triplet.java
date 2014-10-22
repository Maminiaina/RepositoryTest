package Traitement;

import com.hp.hpl.jena.rdf.model.*;

public class Triplet {
	
	private int id;
	private Resource ressource;
	private Property  predicate ;
	private RDFNode   object  ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Resource getRessource() {
		return ressource;
	}
	public void setRessource(Resource ressource) {
		this.ressource = ressource;
	}
	
	public Property getPredicate() {
		return predicate;
	}
	public void setPredicate(Property predicate) {
		this.predicate = predicate;
	}
	
	public RDFNode getObject() {
		return object;
	}
	public void setObject(RDFNode object) {

		this.object = object;
	}
	
	public Triplet(int id, Resource ressource, Property predicate, RDFNode object) {
		this.id = id;
		this.ressource = ressource;
		this.predicate = predicate;
		this.object = object;
	}
	
	
}
