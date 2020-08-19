package com.meli.mutant.service;

import java.util.List;
import com.meli.mutant.model.Dna;
import com.meli.mutant.repository.DnaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dnaService")
public class DnaServiceImpl implements DnaService{
	
	@Autowired
    private DnaRepository dnaRepository; 

	@Override
	public Dna findByDnaId(int dnaId) {
		return dnaRepository.findByDnaId(dnaId);
	}

	@Override
	public Dna findByDnaString(String dnaString) {
		return dnaRepository.findByDnaString(dnaString);
	}

	@Override
	public Dna findByDnaHash(String dnaHash) {
		return dnaRepository.findByDnaHash(dnaHash);
	}

	@Override
	public List<Dna> findAll() {
		return dnaRepository.findAll();
	}

	@Override
	public List<Dna> findByMutant(boolean mutant) {
		return dnaRepository.findByMutant(mutant);
	}

	@Override
	public long count() {
		return dnaRepository.count();
	}

	@Override
	public long countByMutant(boolean mutant) {
		return dnaRepository.countByMutant(mutant);
	}

	@Override
	public void saveDna(Dna dna) {
		dnaRepository.save(dna);
		
	}

	@Override
	public void updateDna(Dna dna) {
		dnaRepository.saveAndFlush(dna);		
	}

	@Override
	public void deleteDna(Dna dna) {
		dnaRepository.delete(dna);
	}

	
}
