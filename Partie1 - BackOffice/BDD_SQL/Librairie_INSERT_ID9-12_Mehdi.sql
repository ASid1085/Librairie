
INSERT INTO EVENEMENT VALUES("00002EVE", "Grandes vacances", "01.07.2021", "31.08.2021", 5.00, "CHOLETE",NULL, NULL);  

ICISTATUS???????????????????

INSERT INTO COMMANDE VALUES ("00005CMD", "sampete", "CB", "14.10.2020", "00005FAC", "TVA1", "ST2", "00005ADR", "00006ADR", "18.97.284.276", NULL, "24.10.2019");
INSERT INTO COMMANDE VALUES ("00006CMD", "willS", "CB", "22.11.2020", "00006FAC", "TVA1", "ST13", "00006ADR", "00008ADR", "11.67.265.149", NULL, "22.08.2017");
INSERT INTO COMMANDE VALUES ("00007CMD", "jackychoun", "CB", "19.12.2018", "00007FAC", "TVA1", "ST12", "00007ADR", "00007ADR", "17.78.134.222", NULL, "17.10.2020");
INSERT INTO COMMANDE VALUES ("00008CMD", "bernie", "CB", "09.08.2020", "00008FAC", "TVA1", "ST13", "00008ADR", "00005ADR", "19.89.120.277", NULL, "12.03.2018");



INSERT INTO LIVRE VALUES ("ISBN4524700091", "Hypnose", NULL, 22.30, "TVA2" "D�cembre 2020", NULL, 
			"La d�finition classique de l�hypnose �voque un �tat modifi� de conscience. Dans cet �tat, 
			l�hypnotiseur semble pouvoir produire de nombreux effets sur la personne hypnotis�e, 
			certains b�n�fiques, d�autres spectaculaires ou inattendus. Comment fait-il ? 
			Utilise-t-il son charisme ? Une forme d��nergie ? En v�rit�, c�est bien plus simple que 
			cela : l�hypnotiseur utilise simplement des techniques de suggestion qui peuvent �tre apprises
			et am�lior�es, comme dans n�importe quel domaine. �tes-vous pr�t � les d�couvrir ?", 150, 22, NULL, NULL );
INSERT INTO LIVRE VALUES ("ISBN7889685400", "Minimalism", NULL, 5.50, "TVA2" "Avril 2000", NULL, 
			"Courez-vous apr�s le temps ? �tes-vous stress� ? Pensez-vous manquer d�argent ? Si la r�ponse
			� ces questions est oui, le probl�me n�est peut-�tre pas celui que vous imaginez. En effet, si
			vous subissez votre vie plus que vous ne la vivez, c�est sans doute que vous poss�dez trop de 
			choses. La soci�t� de consommation postule que plus vous avez de biens, plus vous �tes heureux.
			� l�inverse, le minimalisme apprend � remettre de l�ordre dans votre vie et dans vos priorit�s.
			En devenant minimaliste, vous changerez votre existence � tout jamais
			! Pr�t � faire le vide ?", 20, 7, NULL, NULL );
INSERT INTO LIVRE VALUES ("ISBN7244905432", "Tous muscl�s", NULL, 15.20, "TVA2" "Mars 2012", NULL, 
			"Le bodybuilding, dans son acception moderne, est une pratique apparue en Europe � la fin du XIXe 
			si�cle. Son introduction a �t� impuls�e par des pionniers tels que Eugen Sandow ou Edmond
			Desbonnet. Ces athl�tes font alors d�monstration, lors de spectacles ou expositions, de leurs
			corps sculpt�s. En opposition � la conception antique du muscle, il est ici plus question d�une 
			recherche esth�tique que d�une d�monstration de force brute. Aujourd�hui, il existe 5 500 salles
			de fitness en Europe, 60 millions de pratiquants et cela correspond � 27 milliards de chiffre 
			d�affaires par an dans le monde. Le bodybuilding est-il devenu une obsession 
			� grande �chelle ?", 2000, 44, NULL, NULL );
INSERT INTO LIVRE VALUES ("ISBN0478891635", "G�n�ration Slasheur", NULL, 14.80, "TVA2" "Mai 2017", NULL, 
			"� une �poque o� la s�curit� de l�emploi est de plus en plus incertaine, travailler toute sa 
			vie pour une m�me entreprise ou simplement exercer un seul m�tier semble impossible. Pour 
			faire face � ces difficult�s mais aussi pour s��panouir personnellement et professionnellement,
			de plus en plus de jeunes cumulent plusieurs professions. Ces slasheurs exploitent les 
			opportunit�s offertes par les nouveaux statuts professionnels et les possibilit�s des nouveaux
			m�dias. D�couvrez le portrait de ces jeunes qui vivent leur vie professionnelle 
			� cent � l�heure !", 100, 19, NULL, NULL );


INSERT INTO AUTEUR VALUES ("00005AUT", "Inshape", "Tibo", NULL);
INSERT INTO AUTEUR VALUES ("00006AUT", "Sotomayor", "Javier", NULL);
INSERT INTO AUTEUR VALUES ("00007AUT", "Vandamme", "Jean-Claude", NULL);
INSERT INTO AUTEUR VALUES ("00008AUT", "Agassi",  "Andr�", NULL);


INSERT INTO MOT_CLE VALUES ("00005MOT", "Moderne");
INSERT INTO MOT_CLE VALUES ("00006MOT", "Forme");
INSERT INTO MOT_CLE VALUES ("00007MOT", "Conscience");
INSERT INTO MOT_CLE VALUES ("00008MOT", "Question");

INSERT INTO EDITEUR VALUES ("00003EDI", "Eni");
INSERT INTO EDITEUR VALUES ("00004EDI", "Plon");

INSERT INTO GENRE VALUES ("00005GEN", "Biographique");
INSERT INTO GENRE VALUES ("00006GEN", "Po�tique");
INSERT INTO GENRE VALUES ("00007GEN", "Narratif");
INSERT INTO GENRE VALUES ("00008GEN", "Documentaire");

INSERT INTO THEME VALUES ("00005THE", "Nature");
INSERT INTO THEME VALUES ("00006THE", "Sport");
INSERT INTO THEME VALUES ("00007THE", "Voyage");
INSERT INTO THEME VALUES ("00008THE", "Bien-�tre");

INSERT INTO ADRESSE VALUES ("00005ADR", NULL, "Sampras", "Pete", "1500", "rue des aces", "89000", "Auxerre", "FRANCE", NULL, NULL);
INSERT INTO ADRESSE VALUES ("00006ADR", NULL, "Smith", "Will", "20", "boulevard de bel-air", "66400", "C�ret", "FRANCE", NULL, NULL);
INSERT INTO ADRESSE VALUES ("00007ADR", NULL, "Chan", "Jacky", "44", "Route de la soie", "69100", "Villeurbanne", "FRANCE", NULL,NULL);
INSERT INTO ADRESSE VALUES ("00008ADR", NULL, "Pivot", "Bernard", "123", "rue Apostrophe", "34000", "Montpellier", "FRANCE", NULL, NULL);


INSERT INTO CLIENT VALUES ("sampete", "Sampras", "Pete", "balle","pistolPete@atp.org", NULL, NULL, NULL);
INSERT INTO CLIENT VALUES ("willS", "Smith", "Will", "onclephil","fresh@prince.com",NULL, NULL, NULL);
INSERT INTO CLIENT VALUES ("jackychoun", "Chan", "Jacky", "kungfu","jtedefonce@free.fr", NULL, NULL, NULL);
INSERT INTO CLIENT VALUES ("bernie", "Pivot", "Bernard", "ageanivo","antenne2@aol.com", NULL, NULL, NULL);

INSERT INTO EMPLOYE VALUES ("00005EMP", "DT3", "Figo", "Luis", "commercial", "figluis", "employe");
INSERT INTO EMPLOYE VALUES ("00006EMP", "DT3", "Denzey", "Willy", "commercial", "willrnb", "employe");
INSERT INTO EMPLOYE VALUES ("00007EMP", "DT3", "Cabrel", "Francis", "commercial", "cabris", "employe");
INSERT INTO EMPLOYE VALUES ("00008EMP", "DT2", "Chirac", "Jacques", "Qualit�", "jakchir", "responsable");