
-- TABLE COMMANDE
INSERT INTO COMMANDE VALUES ('00001CMD', '1', 'CB', '5.90', '06/09/2016', '00001FAC', 'TVA2', 'ST2', '00001ADR', '00004ADR', '12.91.245.272',NULL,'20/09/2016' );
INSERT INTO COMMANDE VALUES ('00002CMD', '2', 'CB', '5.90', '02/07/1989', '00002FAC', 'TVA2', 'ST3', '00002ADR', '00001ADR', '12.91.245.282',NULL,'20/08/1989' );
INSERT INTO COMMANDE VALUES ('00003CMD', '3', 'CB', '5.90', '03/02/1986', '00003FAC', 'TVA2', 'ST4', '00003ADR', '00002ADR', '12.91.245.292',NULL,'15/03/1986' );
INSERT INTO COMMANDE VALUES ('00004CMD', '4', 'CB', '5.90', '09/11/2019', '00004FAC', 'TVA2', 'ST5', '00004ADR', '00003ADR', '12.91.245.372',NULL,'27/11/2019' );

--------------------------------------------------------------------------------------------------------------------
-- TABLE EMPLOYE
INSERT INTO EMPLOYE VALUES ("00001EMP", "DT1", "Bouaziz", "EDOUARD", "CEO", "eBou", "cMoiLeBoss");
INSERT INTO EMPLOYE VALUES ("00002EMP", "DT2", "Gaillard", "Remi", "responsable communication", "gare", "respecteMoi");
INSERT INTO EMPLOYE VALUES ("00003EMP", "DT4", "Princesse", "Sarah", "maintenance", "prinsa", "travailleDur");
INSERT INTO EMPLOYE VALUES ("00004EMP", "DT3", "Eboué", "Fabrice", "Commercial", "fabeb", "blablabla");

--------------------------------------------------------------------------------------------------------------------
--TABLE EVENEMENTS
INSERT INTO EVENEMENT VALUES("00001EVE", "LES CERISIERS EN FLEURS", "19.04.2021", "31.05.2021", 5.00, "SAKURA",NULL, NULL);

---------------------------------------------------------------------------------------------------------------------
--TABLE LIVRES
INSERT INTO LIVRE VALUES ("ISBN0000059885", "Depuis l'au-Dela", NULL, 22, "TVA2" "04/10/2017", NULL, 
			"Je me nomme Gabriel Wells.Je suis écrivain de romans à suspens.Ma nouvelle enquête est un peu particulière
			car elle concerne le meurtre de quelqu’unque je connais personnellement :Moi-même.
			J’ai été tué dans la nuitet je me demande bien par qui.Pour résoudre cette énigme j’ai eu la chance de rencontrer Lucy Filipini.
			En tant que medium professionnelle elle parle tous les jours aux âmes des défunts.
			Et c’est ensemble, elle dans le monde matériel,moi dans le monde invisible que nous allons tenter de percer
			le mystère de ma mort.", 448, 56, NULL, NULL );
INSERT INTO LIVRE VALUES ("ISBN5601284766", "Un monde apres l'autre", NULL, 14.50, "TVA2" "08/02/2018", NULL, 
			"Derrière l’innocente façade de St Mary, le secret du voyage dans le temps a été découvert et reste bien gardé. 
			Les chercheurs en Histoire ont ainsi une méthode de travail tout à fait particulière : 
			ils « étudient ‘en temps réel’ les événements majeurs de l’Histoire ». 
			En se faisant passer pour d’inoffensifs excentriques, ils tentent de répondre à certaines questions qui n’ont jamais été résolues,
			sans jamais toucher au cours de l’Histoire… au risque d’en mourir. Madeleine Maxwell, une jeune et brillante historienne est contactée
			par son ancien professeur afin de rejoindre l’équipe de l’Institut St Mary.
			Au cours de son étrange entretien d’embauche, Maxwell comprend vite les possibilités qui s’offrent à elle… 
			De la disparition de Pompéi aux tranchées de la Première Guerre mondiale, du grand incendie de Londres à la destruction de la bibliothèque d’Alexandrie,
			la jeune historienne va revivre d’extraordinaires événements. Alors qu’au sein de l’institut naissent des enjeux de pouvoir…", 350, 30, NULL, NULL );
INSERT INTO LIVRE VALUES ("ISBN38564751514", "Les deux tours", Null, 12.90, "TVA2" "20 octobre 1955", NULL, 
			"La dernière partie du Seigneur des Anneaux voit la fin de la quête de Frodo en Terre du Milieu.
			Le Retour du Roi raconte la stratégie désespérée de Gandalf face au Seigneur des Anneaux, jusqu'à la catastrophe finale et au dénouement 
			de la grande Guerre où s'illustrent Aragorn et ses compagnons, Gimli le Nain, Legolas l'Elfe, les Hobbits Merry et Pippin,
			tandis que Gollum est appelé à jouer un rôle inattendu aux côtés de Frodo et de Sam au Mordor, 
			le seul lieu où l'Anneau de Sauron peut être détruit.", 416, 5, NULL, NULL );
INSERT INTO LIVRE VALUES ("ISBN0025684465", "Harry Potter", "et l'ordre du phoenix", 14.90, "TVA2" " 21 juin 2003", NULL, 
			"À quinze ans, Harry entre en cinquième année à Poudlard, mais il n'a jamais été si anxieux. L'adolescence, la perspective des examens et ces étranges cauchemars... 
			Car Celui-Dont-On-Ne-Doit-Pas-Prononcer-Le-Nom est de retour. Le ministère de la Magie semble ne pas prendre cette menace au sérieux, 
			contrairement à Dumbledore. La résistance s'organise alors autour de Harry qui va devoir compter sur le courage et la fidélité de ses amis de toujours...", 
			984, 26, NULL, NULL );

----------------------------------------------------------------------------------------------------------------------------
--TABLE AUTEUR
INSERT INTO AUTEUR VALUES ("00001AUT", "Bernard", "Werber", NULL);
INSERT INTO AUTEUR VALUES ("00002AUT", "John Ronald Reuel", "Tolkien", NULL);
INSERT INTO AUTEUR VALUES ("00003AUT", "Joanne K", "Rowling", NULL);
INSERT INTO AUTEUR VALUES ("OOO04AUT", "Jodi", "Taylor", NULL);

----------------------------------------------------------------------------------------------------------------------------
--TABLE MOT CLE
INSERT INTO MOT_CLE VALUES ("00001MOT", "Sorcier");
INSERT INTO MOT_CLE VALUES ("00002MOT", "Ame");
INSERT INTO MOT_CLE VALUES ("00003MOT", "Troll");
INSERT INTO MOT_CLE VALUES ("00004MOT", "Histoire");

------------------------------------------------------------------------------------------------------------------------------
--TABLE EDITEUR
INSERT INTO EDITEUR VALUES ("00001EDI", "HC editions");
INSERT INTO EDITEUR VALUES ("00002EDI", "Gallimard");

---------------------------------------------------------------------------------------------------------------------------
--TABLE GENRE
INSERT INTO GENRE VALUES ("00001GEN", "Mangas");
INSERT INTO GENRE VALUES ("00002GEN", "Comics");
INSERT INTO GENRE VALUES ("00003GEN", "Theatre");
INSERT INTO GENRE VALUES ("00004GEN", "Poesie"); 

-----------------------------------------------------------------------------------------------------------------------------
--TABLE THEME
INSERT INTO THEME VALUES ("00001THE", "Humour");
INSERT INTO THEME VALUES ("OOO02THE", "Langage");
INSERT INTO THEME VALUES ("OOO03THE", "Voyage");
INSERT INTO THEME VALUES ("OOO04THE", "Esprit");

--------------------------------------------------------------------------------------------------------------------------
--TABLE ADRESSE
INSERT INTO ADRESSE VALUES ("00001ADR", NULL, "Grey", "Meredith", "10", "Boulevard des scalpels", "93120", "La Courneuve", "FRANCE", NULL, NULL);
INSERT INTO ADRESSE VALUES ("00002ADR", NULL, "Scofield", "Michael", "5", " Chemin des Barreaux", "93420", "Villepinte", "FRANCE", NULL, NULL);
INSERT INTO ADRESSE VALUES ("00003ADR", NULL, "Uzumaki", "Naruto", "3", "allée des Ramens", "75013", "Paris", "FRANCE", NULL, NULL);
INSERT INTO ADRESSE VALUES ("00004ADR", NULL, "Choumicha", "Dubled", "20", "rue de Tanger", "75020", "Paris", "FRANCE", NULL, NULL);
