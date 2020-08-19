package com.meli.mutant.process;

public class DnaData {

	private Long dnaId;
	private String dnaString;
	private String dnaHash;
	private boolean mutant;
	private String [] dna;
	
	public Long getDnaId() {
		return dnaId;
	}
	public void setDnaId(Long dnaId) {
		this.dnaId = dnaId;
	}
	public String getDnaString() {
		return dnaString;
	}
	public void setDnaString(String dnaString) {
		this.dnaString = dnaString;
	}
	public String getDnaHash() {
		return dnaHash;
	}
	public void setDnaHash(String dnaHash) {
		this.dnaHash = dnaHash;
	}
	public boolean isMutant() {
		return mutant;
	}
	public void setMutant(boolean mutant) {
		this.mutant = mutant;
	}
	public String[] getDna() {
		return dna;
	}
	public void setDna(String[] dna) {
		this.dna = dna;
	}
	
}
