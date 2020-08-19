package com.meli.mutant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meli.mutant.model.Dna;


@Repository("dnaRepository")
public interface DnaRepository extends JpaRepository<Dna, Long>{
	Dna findByDnaId(int dnaId);
	Dna findByDnaString(String dnaString);
	Dna findByDnaHash(String dnaHash);
	List<Dna> findAll();
	List<Dna> findByMutant(boolean mutant);
	long count ();
	long countByMutant (boolean mutant);

}