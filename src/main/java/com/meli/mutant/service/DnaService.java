package com.meli.mutant.service;

import java.util.List;
import com.meli.mutant.model.Dna;

public interface DnaService {
	public Dna findByDnaId(int dnaId);
	public Dna findByDnaString(String dnaString);
	public Dna findByDnaHash(String dnaHash);
	public List<Dna> findAll();
	public List<Dna> findByMutant(boolean mutant);
	public long count ();
	public long countByMutant (boolean mutant);
	public void saveDna (Dna dna);
	public void updateDna (Dna dna);
	public void deleteDna (Dna dna);
	
}
