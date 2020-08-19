-- Table: dna

-- DROP TABLE dna;

CREATE TABLE dna
(
  dna_id bigserial NOT NULL,
  dna_string text NOT NULL,
  dna_hash text NOT NULL,
  mutant boolean NOT NULL,
  CONSTRAINT dna_pkey PRIMARY KEY (dna_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE dna
  OWNER TO postgres;