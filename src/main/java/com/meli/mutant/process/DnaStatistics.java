package com.meli.mutant.process;

public class DnaStatistics {
	private long count_mutant_dna;
	private long count_human_dna;
	private float ratio;
	
	public long getCount_mutant_dna() {
		return count_mutant_dna;
	}
	
	public void setCount_mutant_dna(long count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}
	
	public long getCount_human_dna() {
		return count_human_dna;
	}
	
	public void setCount_human_dna(long count_human_dna) {
		this.count_human_dna = count_human_dna;
	}
	
	public float getRatio() {
		return ratio;
	}
	
	public void setRatio(float ratio) {
		this.ratio = ratio;
	}

}
