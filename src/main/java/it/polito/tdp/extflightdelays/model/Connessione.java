package it.polito.tdp.extflightdelays.model;

public class Connessione {
	Integer aer1;
	Integer aer2;
	Double peso;
	
	
	public Connessione(Integer aer1, Integer aer2, Double peso) {
		super();
		this.aer1 = aer1;
		this.aer2 = aer2;
		this.peso = peso;
	}


	public Integer getAer1() {
		return aer1;
	}


	public void setAer1(Integer aer1) {
		this.aer1 = aer1;
	}


	public Integer getAer2() {
		return aer2;
	}


	public void setAer2(Integer aer2) {
		this.aer2 = aer2;
	}


	public Double getPeso() {
		return peso;
	}


	public void setPeso(Double peso) {
		this.peso = peso;
	}


	
	
}
