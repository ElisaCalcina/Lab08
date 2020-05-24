package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	public Graph<Airport, DefaultWeightedEdge> grafo;
	private Map<Integer, Airport> idMap;
	ExtFlightDelaysDAO dao ;
	//List<Connessione> connessioni;
	
	public Model() {
		idMap= new HashMap<Integer, Airport>();
		//connessioni= new LinkedList<Connessione>();
	}
	
	public void creaGrafo(Integer x) {
		this.grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		dao = new ExtFlightDelaysDAO();
		dao.loadAllAirports(idMap);
		//connessioni= dao.getConnessioni();
		
		//aggiungo i vertici
		Graphs.addAllVertices(this.grafo, idMap.values() );
		
		//aggiungo archi
		for(Connessione c: dao.getConnessioni(idMap, x)) {
			//if(c.getPeso()>x) {	
				if(!grafo.containsEdge(idMap.get(c.getAer1()), idMap.get(c.getAer2()))){
					Graphs.addEdge(grafo, idMap.get(c.getAer1()), idMap.get(c.getAer2()), c.getPeso());
				}
				else {
					DefaultWeightedEdge e= this.grafo.getEdge(idMap.get(c.getAer1()), idMap.get(c.getAer2()));
					this.grafo.setEdgeWeight(e, (c.getPeso()+this.grafo.getEdgeWeight(e))/2);
				/*DefaultWeightedEdge e= this.grafo.getEdge(idMap.get(c.getAer1()), idMap.get(c.getAer2()));
				if(e==null) {
					Graphs.addEdge(grafo, idMap.get(c.getAer1()), idMap.get(c.getAer2()), c.getPeso());
				}
				else {
					double pesoVecchio= this.grafo.getEdgeWeight(e);
					double pesoNuovo= (pesoVecchio+ c.getPeso())/2;
					this.grafo.setEdgeWeight(e, pesoNuovo);
				}*/
			//}
			}
		}
		System.out.println(String.format("Grafo stampato con %d vertici e %d archi", this.grafo.vertexSet().size(), this.grafo.edgeSet().size()));
		
	}
	
	public List<Connessione> getConn(){
		List<Connessione> conn= new ArrayList<Connessione>();
		for(DefaultWeightedEdge e: this.grafo.edgeSet()) {
			conn.add(new Connessione(this.grafo.getEdgeSource(e).getId(), this.grafo.getEdgeTarget(e).getId(), this.grafo.getEdgeWeight(e)));
		}
		return conn;
	}
	
	public Map<Integer, Airport> getIdMap(){
		return idMap;
	}
	
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
}


