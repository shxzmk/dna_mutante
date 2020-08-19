package com.meli.mutant.process;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meli.mutant.model.Dna;

public class DnaProcess {

	private static List<String[]> dna2 = Collections.synchronizedList(new ArrayList<String[]>());
	private static List<Integer[]> counterList = Collections.synchronizedList(new ArrayList<Integer[]>()); 
	private static List<String> previousList = Collections.synchronizedList(new ArrayList<String>()); 

	private final static int MAX_SECUENCE = 3;
	private final static int DIRECTIONS=6;
	private final static int NITROGENS=4;
	private final static int R=4;
	
	private static int mutantGenCounter = 0;
	private static Integer[] num = new Integer[]{0,0,0,0,0,0};
	private static boolean valid=true;
	private static Dna dna = new Dna();

	private static Logger logger = LoggerFactory.getLogger(DnaProcess.class);

	public static Dna executeProcess(DnaEntry dnaEntry, DnaData dnaData) {
		dna.setDnaString(dnaData.getDnaString());
		dna.setDnaHash(dnaData.getDnaHash());
		dna.setMutant(isMutant(dnaEntry.getDna()));

		if(!valid) return null;
		return dna;
	}

	public static boolean isMutant(String[] dna) {
		int N=dna.length;
		for(int i=0; i<N; i++) {
			if(dna[i].length() == N) {
				dna2.add(dna[i].split(""));
			} else {
				logger.error("La cadena de ADN no cumple con la estructura de NxN elementos");
				valid = false;
				clearData();
				return false;
			} 
		}
		
		for (String[] d : dna2) {
			for (String e: d) {
				System.out.print(e+" ");
			}
			System.out.println();
		}
		System.out.println();
		
		initializeList();
		//printMatrix(dna2);
		//printList();
		
		if(R>N) {
			logger.error("El numero de secuencia mutante supera el tamano de la secuencia del ADN");
			valid=false;
		}

		if(valid) {
			for(int i=0; i<dna.length; i++) {

				String horizontal="", vertical="";
				String diagonal="", inverse="";
				String mirrow="", inversemirrow="";

				for(int j=0; j<dna.length; j++) {
					if(!valid) break;
					//Horizontal Streams
					horizontal+=dna2.get(i)[j];
					analyzeNitrogenousBases("horizontal",dna2.get(i)[j]);
					
					//Vertical Streams
					vertical+=dna2.get(j)[i];
					analyzeNitrogenousBases("vertical",dna2.get(j)[i]);

					//Diagonal Streams
					if(j<N-i&&i<=N-R) {
						diagonal+=dna2.get(j)[j+i]; //diagonal
						analyzeNitrogenousBases("diagonal",dna2.get(j)[j+i]);
						inverse+=dna2.get(j+i)[N-j-1]; //diagonal inversa
						analyzeNitrogenousBases("inverse",dna2.get(j+i)[N-j-1]);

						if(i>0) { 
							mirrow+=dna2.get(j+i)[j]; //diagonal mirrow
							analyzeNitrogenousBases("mirrow",dna2.get(j+i)[j]);
							inversemirrow+=dna2.get(j)[N-i-j-1]; //diagonal mirrow inversa
							analyzeNitrogenousBases("inversemirrow",dna2.get(j)[N-i-j-1]);
						}

					}
				}
				initializeList();

				if(!valid) break;

				if (mutantGenCounter>=MAX_SECUENCE) {
					logger.info("El ADN analizado corresponde a un Mutante");
					logger.info("Numero de Genes mutantes: "+mutantGenCounter);
					System.out.println("Numero de Genes mutantes: "+mutantGenCounter);

					clearData();
					return true;
				}
			}
		}else {
			clearData();
			return false;
		}

		logger.info("Numero de Genes mutantes: "+mutantGenCounter);
		logger.info("Finalizacion del Analisis de ADN: No Mutante");
		clearData();
		return false;

	}

	public static void analyzeNitrogenousBases (String direction, String dnaNitrogen) {
		if(validateDnaNitrogenBase(dnaNitrogen)) {
			String previous = previousList.get(getDirectionValue(direction));
			int previousValue = getDNAValue(previous);
			int directionValue = getDirectionValue(direction);
			int dnaValue = getDNAValue(dnaNitrogen);

			if(previous != null && previous !="") {
				if(!previous.equalsIgnoreCase(dnaNitrogen)) {
					num = counterList.get(directionValue);
					num[previousValue] = 0;
					counterList.set(directionValue,num);
				}
			}

			num = counterList.get(directionValue);
			num[dnaValue] += 1 ;
			if(num[dnaValue]==R) {
				mutantGenCounter++;
				num[dnaValue]=0;
			}
			counterList.set(directionValue,num);
			previousList.set(directionValue, dnaNitrogen);
		}else {
			logger.error("Base Nitrogenada "+dnaNitrogen+" Incorrecta");
			logger.error("Analisis de ADN detenido");
			valid=false;
		}
	}

	public static boolean validateDnaNitrogenBase(String dnaNitrogen) {
		if(!dnaNitrogen.equalsIgnoreCase("A") && 
		   !dnaNitrogen.equalsIgnoreCase("C") &&
		   !dnaNitrogen.equalsIgnoreCase("G") &&
		   !dnaNitrogen.equalsIgnoreCase("T")) 
			return false;
		else return true;
	}

	public static void initializeList () {
		counterList.clear();
		previousList.clear();
		num = Collections.nCopies(DIRECTIONS, 0).toArray(new Integer[0]);
		//seis por la horizontal, vertical y cuatro diagonales

		for(int i=0; i<DIRECTIONS; i++) {
			counterList.add(Collections.nCopies(NITROGENS, 0).toArray(new Integer[0]));
			//cuatro por las bases nitrogenadas A C G T
			previousList.add("");
		}
	}
	
	public static void clearData () {
		dna2.clear();
		mutantGenCounter=0;
	}
	
	public static void printList () {
		for (Integer[] d : counterList) {
			for (Integer e: d) {
				System.out.print(e+" ");
			}
			System.out.println();
		}

		for (String d : previousList) {
			System.out.println(d+" .");
		}

	}

	public static int getDNAValue (String str) {
		str=str.toUpperCase();
		switch(str) { 
		case "A": 
			return 0;
		case "C": 
			return 1; 
		case "G": 
			return 2;
		case "T": 
			return 3; 
		default: 
			return -1; 
		}
	}

	public static int getDirectionValue (String str) {
		str=str.toLowerCase();
		switch(str) { 
		case "horizontal": 
			return 0;
		case "vertical": 
			return 1; 
		case "diagonal": 
			return 2;
		case "inverse": 
			return 3;
		case "mirrow": 
			return 4;
		case "inversemirrow": 
			return 5; 
		default: 
			return -1; 
		}
	}

}
