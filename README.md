# README 


API MUTANT

Para verificar el funcionamiento del API se puede emplear un cliente de Testing para Rest API como Postman o JMeter.

****************************************************************************
1) POST /mutant/

URL: http://3.136.69.74:8080/dna/mutant/

Descripcion: 

Servicio que permite verificar si una cadena de ADN de un humano es mutante o no.


Body:

{
"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCATA","TCACTG"]
}

Response:
200 OK -> en caso de que las condiciones del ADN se cumpla para ser mutante.

403 FORBIDDEN -> en caso de no cumplir las condiciones de ADN para ser mutante.

***Condicion para ser mutante: 
Un humano es mutante, si se encuentra más de una secuencia de cuatro letras
iguales, de forma oblicua, horizontal o vertical.***

Las Bases Nitrogenadas del ADN se representan con las letras A, C, G, T.

La Matriz de la cadena de ADN debe ser de tamaño NxN.


********************************************************************************
2) GET /stats/

URL:  http://3.136.69.74:8080/dna/stats/

Descripcion:

Servicio que permite obtener las estadisticas de los siguientes datos:

- Conteo de ADN Mutante (count_mutant_dna): el total de cadenas de ADN validadas como mutantes.
- Conteo de ADN Humano (count_human_dna): el total de cadenas de ADN que han sido procesadas por el API y tienen un formato de ADN correcto.
- Proporcion (ratio): Es la relacion entre el numero de mutantes y la cantidad total de humanos.


Response:
{
    "count_mutant_dna": 5,
    "count_human_dna": 5,
    "ratio": 0.5
}


*******************************************************************************
Testing Automatico

Se incluye un archivo de testing de POSTMAN, que compila quince (15) casos que testean el funcionamiento del API
Nombre del Archivo: MeliMutanTest.postman_collection.json

