/*****************/
-- POSSEDER
/*****************/
insert into FACTURER values
	( '00010THE', '00003GEN'),
	( '00010THE', '00014GEN'),
	( '00010THE', '00013GEN'),
	( '00010THE', '00015GEN');
	
/*****************/
-- FAIRE_LIVRER
/*****************/
insert into FACTURER values
	( 'asid', '00013ADR'),
	( 'roxy', '00013ADR'),
	( 'gazel', '00015ADR'),
	( 'pinoch', '00015ADR');

/*****************/
-- FACTURER
/*****************/
insert into FACTURER values
	( 'asid', '00013ADR'),
	( 'roxy', '00014ADR'),
	( 'gazel', '00015ADR'),
	( 'pinoch', '00016ADR');

/*****************/
-- EDITER
/*****************/
insert into EDITER values
	( 'ISBN0000059013', '00001EDI'),
	( 'ISBN5601254014', '00003EDI'),
	( 'ISBN3856995015', '00005EDI'),
	( 'ISBN0025684016', '00007EDI'),
	( 'ISBN9568745017', '00001EDI');

/*****************/
-- AVOIR
/*****************/
insert into AVOIR values
	( 'ISBN0000059013', '00005THE'),
	( 'ISBN5601254014', '00015THE'),
	( 'ISBN3856995015', '00010THE'),
	( 'ISBN0025684016', '00014THE'),
	( 'ISBN9568745017', '00008THE');
	
/*****************/
-- ATTRIBUER1
/*****************/
insert into ATTRIBUER1 values
	('00013MOT', 'ISBN0000059013'),
	('00012MOT', 'ISBN0000059013'),
	('00014MOT', 'ISBN5601254014'),
	('00005MOT', 'ISBN5601254014'),
	('00015MOT', 'ISBN3856995015'),
	('00001MOT', 'ISBN3856995015'),
	('00012MOT', 'ISBN0025684016'),
	('00016MOT', 'ISBN9568745017'),
	('00002MOT', 'ISBN9568745017');

/*****************/
-- ATTRIBUER
/*****************/
insert into ATTRIBUER values
	( 'ISBN0000059013', '00004EVE'),
	( 'ISBN5601254014', '00004EVE'),
	( 'ISBN3856995015', '00004EVE'),
	( 'ISBN0025684016', '00004EVE'),
	( 'ISBN9568745017', '00004EVE');