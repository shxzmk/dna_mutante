package com.meli.mutant.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.meli.mutant.model.Dna;
import com.meli.mutant.process.DnaData;
import com.meli.mutant.process.DnaEntry;
import com.meli.mutant.process.DnaProcess;
import com.meli.mutant.process.DnaStatistics;
import com.meli.mutant.service.DnaService;

@RestController
@RequestMapping("/dna")
public class DnaController {
	
	@Autowired
	private DnaService dnaService;
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    		

    @RequestMapping(value = "/mutant", method = RequestMethod.POST)
    public ResponseEntity<?> isMutant(@RequestBody String jsonString) {
        
        //Data transformation from JSON to String[]
        Gson gson = new Gson();
        DnaEntry dnaEntry = gson.fromJson(jsonString, DnaEntry.class);
        DnaData dnaData = new DnaData();
        String sha256hex = DigestUtils.sha256Hex(jsonString);
        boolean mutant = false;

        dnaData.setDnaString(jsonString);
        dnaData.setDna(dnaEntry.getDna());
        dnaData.setDnaHash(sha256hex);

        //Verificacion en Base de datos si ya existe la cadena de ADN
        //con el empleo del HASH: SHA256HEX (64 chars hexadecimales)se pueden generar mas de 21 mil billones de hashes. 
        Dna dna = dnaService.findByDnaHash(sha256hex);

        //Proceso para el analisis del ADN
        if(dna==null) {
        	dna=DnaProcess.executeProcess(dnaEntry,dnaData);
        	if(dna!=null) {
        		dnaService.saveDna(dna);
        		mutant=dna.isMutant();
        		logger.info("Inserto de registro exitoso en la Base de Datos");

        	}else {
        		mutant=false;
        	}
        }else {
        	mutant=dna.isMutant();
        }
        if(mutant) return new ResponseEntity<DnaData>(new HttpHeaders(), HttpStatus.OK);
 
        return new ResponseEntity<DnaData>(new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
    
  
    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public ResponseEntity<?> getStatistics() {
    	DnaStatistics dnaStats = new DnaStatistics ();
    	Gson gson = new Gson();
    	
    	long humans = dnaService.count();
    	long mutants = dnaService.countByMutant(true);
    	float ratio = 0;

    	if(humans>0) {
    		ratio = (float) mutants / (float) humans;
    	}
    	dnaStats.setCount_human_dna(humans);
    	dnaStats.setCount_mutant_dna(mutants);
    	dnaStats.setRatio(ratio);
    	String output = gson.toJson(dnaStats);
    	
        return new ResponseEntity<String>(output, HttpStatus.OK);
    }
}
