package com.meli.mutant.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name="dna")
public class Dna implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="dna_id")
	private Long dnaId;

	@Column(name="dna_string", nullable=true)
	private String dnaString;
	
	@Column(name="dna_hash", nullable=true)
	private String dnaHash;
	
	@Column(name="mutant", nullable=true)
	private boolean mutant;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
