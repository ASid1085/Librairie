Use Librairie;

INSERT INTO DROITS_D_ACCES VALUES
	('DT10', 'Administrateur', 'Administrateur'),
	('DT20', 'Responsable', 'Responsable'),
	('DT30', 'Employé', 'Employé'),
    ('DT40', 'Stagiaire', 'Stagiaire');

INSERT INTO EMPLOYE VALUES 
	('00001EMP', 'DT10', 'Bouaziz', 'EDOUARD', 'CEO', 'eBou', 'cMoiLeBoss'),
	('00002EMP', 'DT20', 'Gaillard', 'Remi', 'responsable communication', 'gare', 'respecteMoi'),
	('00003EMP', 'DT40', 'Princesse', 'Sarah', 'maintenance', 'prinsa', 'travailleDur'),
	('00004EMP', 'DT30', 'Eboué', 'Fabrice', 'Commercial', 'fabeb', 'blablabla'),
	('00005EMP', 'DT30', 'Figo', 'Luis', 'commercial', 'figluis', 'employe'),
	('00006EMP', 'DT30', 'Denzey', 'Willy', 'commercial', 'willrnb', 'employe'),
	('00007EMP', 'DT30', 'Cabrel', 'Francis', 'commercial', 'cabris', 'employe'),
	('00008EMP', 'DT20', 'Chirac', 'Jacques', 'Qualité', 'jakchir', 'responsable'),
	('00009EMP', 'DT20', 'Dupont', 'Jacques', 'comptable', 'jdupont', 'employe'),
	('00010EMP', 'DT10', 'Marie', 'Jean', 'informaticien', 'jmarie', 'admin'),
	('00011EMP', 'DT30', 'Valois', 'Paris', 'marketing', 'pvalois', 'employe'),
	('00012EMP', 'DT40', 'Marois', 'Jacqueline', 'RH', 'jmarois', 'employe'),
	('00013EMP', 'DT30', 'Touati', 'Michel', 'DAF', 'mtouati', 'employe'),
	('00014EMP', 'DT30', 'Do', 'Jeanne', 'Assitante de direction', 'jdo', 'employe'),
	('00015EMP', 'DT40', 'Hilton', 'Paris', 'RH', 'philton', 'stagiaire');

INSERT INTO EMPLOYE VALUES ('00016EMP', 'DT20', 'Bacri', 'Jean-Pierre', 'Commercial', 'jpbacri', 'responsable');



INSERT INTO EVENEMENT VALUES
	('00001EVE', 'Les cerisiers en fleurs', '19.04.2019', '31.05.2019', 5.00, 'SAKURA',NULL, NULL),
	('00002EVE', 'Grandes vacances', '01.07.2021', '31.08.2021', 5.00, 'CHOLETE',NULL, NULL),
	('00003EVE', 'La rentrée', '01.09.2021', '30.09.2021', 5.00, 'BACK2SCHOOL',NULL, NULL),
	('00004EVE', 'Vos cadeaux de Noël', '20.11.2021', '24.12.2021', 5.00, 'CHRISTMAS',NULL, NULL);
-- UPDATE `librairie`.`evenement` SET `EVENEMENTDATEDEBUT` = '2021-09-01', `EVENEMENTDATEFIN` = '2020-09-30' WHERE (`EVENEMENTID` = '00003EVE');
-- UPDATE `librairie`.`evenement` SET `EVENEMENTDATEDEBUT` = '2021-07-20', `EVENEMENTDATEFIN` = '2021-08-31' WHERE (`EVENEMENTID` = '00002EVE');
-- UPDATE `librairie`.`evenement` SET `EVENEMENTDATEDEBUT` = '2021-04-20', `EVENEMENTDATEFIN` = '2021-05-31' WHERE (`EVENEMENTID` = '00001EVE');
-- UPDATE `librairie`.`evenement` SET `EVENEMENTDATEFIN` = '2021-12-24' WHERE (`EVENEMENTID` = '00004EVE');

select * from evenement;


INSERT INTO TVA VALUES 
	('TVA1', 2.1),
	('TVA2', 5.5),
    ('TVA3', 7),
    ('TVA4', 10),
	('TVA5', 20);

ALTER TABLE LIVRE
	MODIFY COLUMN LIVRERESUME TEXT;
-- ALTER COLUMN LIVRERESUME TEXT;
	
    
INSERT INTO LIVRE  VALUES ('ISBN0000059001', 'Depuis l\'au-Dela', NULL, 22, 'TVA2', 'Avril 2017', NULL, 
			'Je me nomme Gabriel Wells.Je suis écrivain de romans à suspens. Ma nouvelle enquête est un peu particulière
			car elle concerne le meurtre de quelqu\’un que je connais personnellement : Moi-même.
			J\’ai été tué dans la nuit et je me demande bien par qui. Pour résoudre cette énigme j’ai eu la chance de rencontrer Lucy Filipini.
			En tant que medium professionnelle elle parle tous les jours aux âmes des défunts.
			Et c’est ensemble, elle dans le monde matériel, moi dans le monde invisible que nous allons tenter de percer
			le mystère de ma mort.', 448, '56', NULL, NULL);
INSERT INTO LIVRE VALUES ('ISBN5601284002', 'Un monde apres l\'autre', NULL, 14.50, 'TVA2', '08/02/2018', NULL, 
			'Derrière l\'innocente façade de St Mary, le secret du voyage dans le temps a été découvert et reste bien gardé. 
			Les chercheurs en Histoire ont ainsi une méthode de travail tout à fait particulière : 
			ils « étudient en temps réel les événements majeurs de l’Histoire ». 
			En se faisant passer pour d\’inoffensifs excentriques, ils tentent de répondre à certaines questions qui n\'ont jamais été résolues,
			sans jamais toucher au cours de l\’Histoire… au risque d\’en mourir. Madeleine Maxwell, une jeune et brillante historienne est contactée
			par son ancien professeur afin de rejoindre l\'équipe de l\'Institut St Mary.
			Au cours de son étrange entretien d\'embauche, Maxwell comprend vite les possibilités qui s\’offrent à elle… 
			De la disparition de Pompéi aux tranchées de la Première Guerre mondiale, du grand incendie de Londres à la destruction de la bibliothèque d\'Alexandrie,
			la jeune historienne va revivre d\'extraordinaires événements. Alors qu\’au sein de l institut naissent des enjeux de pouvoir…', 350, 30, NULL, NULL );
INSERT INTO LIVRE VALUES ('ISBN8564751003', 'Les deux tours', Null, 12.90, 'TVA2', '20 octobre 1955', NULL, 
			'La dernière partie du Seigneur des Anneaux voit la fin de la quête de Frodo en Terre du Milieu.
			Le Retour du Roi raconte la stratégie désespérée de Gandalf face au Seigneur des Anneaux, jusqu\'à la catastrophe finale et au dénouement 
			de la grande Guerre où s\'illustrent Aragorn et ses compagnons, Gimli le Nain, Legolas l\'Elfe, les Hobbits Merry et Pippin,
			tandis que Gollum est appelé à jouer un rôle inattendu aux côtés de Frodo et de Sam au Mordor, 
			le seul lieu où l\'Anneau de Sauron peut être détruit.', 416, 5, NULL, NULL );
INSERT INTO LIVRE VALUES ('ISBN0025684004', 'Harry Potter', 'et l\'ordre du phoenix', 14.90, 'TVA2', 'juin 2003', NULL, 
			'À quinze ans, Harry entre en cinquième année à Poudlard, mais il n\'a jamais été si anxieux. L\'adolescence, la perspective des examens et ces étranges cauchemars... 
			Car Celui-Dont-On-Ne-Doit-Pas-Prononcer-Le-Nom est de retour. Le ministère de la Magie semble ne pas prendre cette menace au sérieux, 
			contrairement à Dumbledore. La résistance s\'organise alors autour de Harry qui va devoir compter sur le courage et la fidélité de ses amis de toujours...', 
			984, 26, NULL, NULL );
INSERT INTO LIVRE VALUES ('ISBN4524700005', 'Hypnose', NULL, 22.30, 'TVA2', 'Décembre 2020', NULL, 
			'La définition classique de l\’hypnose évoque un état modifié de conscience. Dans cet état, 
			l\’hypnotiseur semble pouvoir produire de nombreux effets sur la personne hypnotisée, 
			certains bénéfiques, d\’autres spectaculaires ou inattendus. Comment fait-il ? 
			Utilise-t-il son charisme ? Une forme d\’énergie ? En vérité, c’est bien plus simple que 
			cela : l\’hypnotiseur utilise simplement des techniques de suggestion qui peuvent être apprises
			et améliorées, comme dans n\’importe quel domaine. Êtes-vous prêt à les découvrir ?', 150, 22, NULL, NULL );
INSERT INTO LIVRE VALUES ('ISBN7889685006', 'Minimalism', NULL, 5.50, 'TVA2', 'Avril 2000', NULL, 
			'Courez-vous après le temps ? Êtes-vous stressé ? Pensez-vous manquer d\’argent ? Si la réponse
			à ces questions est oui, le problème n\’est peut-être pas celui que vous imaginez. En effet, si
			vous subissez votre vie plus que vous ne la vivez, c’est sans doute que vous possédez trop de 
			choses. La société de consommation postule que plus vous avez de biens, plus vous êtes heureux.
			À l\’inverse, le minimalisme apprend à remettre de l\’ordre dans votre vie et dans vos priorités.
			En devenant minimaliste, vous changerez votre existence à tout jamais
			! Prêt à faire le vide ?', 20, 7, NULL, NULL );
INSERT INTO LIVRE VALUES ('ISBN7244905007', 'Tous musclés', NULL, 15.20, 'TVA2', 'Mars 2012', NULL, 
			'Le bodybuilding, dans son acception moderne, est une pratique apparue en Europe à la fin du XIXe 
			siècle. Son introduction a été impulsée par des pionniers tels que Eugen Sandow ou Edmond
			Desbonnet. Ces athlètes font alors démonstration, lors de spectacles ou expositions, de leurs
			corps sculptés. En opposition à la conception antique du muscle, il est ici plus question d’une 
			recherche esthétique que d\’une démonstration de force brute. Aujourd\’hui, il existe 5 500 salles
			de fitness en Europe, 60 millions de pratiquants et cela correspond à 27 milliards de chiffre 
			d\’affaires par an dans le monde. Le bodybuilding est-il devenu une obsession 
			à grande échelle ?', 2000, 44, NULL, NULL );
INSERT INTO LIVRE VALUES ('ISBN0478891008', 'Génération Slasheur', NULL, 14.80, 'TVA2', 'Mai 2017', NULL, 
			'À une époque où la sécurité de l\’emploi est de plus en plus incertaine, travailler toute sa 
			vie pour une même entreprise ou simplement exercer un seul métier semble impossible. Pour 
			faire face à ces difficultés mais aussi pour s\’épanouir personnellement et professionnellement,
			de plus en plus de jeunes cumulent plusieurs professions. Ces slasheurs exploitent les 
			opportunités offertes par les nouveaux statuts professionnels et les possibilités des nouveaux
			médias. Découvrez le portrait de ces jeunes qui vivent leur vie professionnelle 
			à cent à l\’heure !', 100, 19, NULL, NULL );
INSERT INTO LIVRE VALUES ('ISBN0192834009', 'Les Quatre Coins du Coeur', NULL, 12.90, 'TVA2', 'Septembre 2019', NULL, 
			'Les Quatre Coins du coeur est le dernier roman de Françoise Sagan. Subtil, résolument libre, 
			empreint de son immense maîtrise, irrigué par sa passion des sentiments et de leur altérité. 
            L\'intelligence, le cocasse, cette élégance qui lui permet de passer sur les drames de manière 
            si vive et si concise, tout se rencontre et nous permet de revisiter une vie de Sagan à laquelle 
            rien ne manque dans ce roman inachevé, brut et bouleversant.', 117, 100, NULL, NULL );
INSERT INTO LIVRE VALUES ('ISBN3454327010', 'Les Misérables', NULL, 4.95, 'TVA2', 'Aout 2014', NULL, 
			'Le destin de Jean Valjean, forçat échappé du bagne, est bouleversé par sa rencontre avec Fantine. 
            Mourante et sans le sou, celle-ci lui demande de prendre soin de Cosette, sa fille confiée aux 
            Thénardier. Ce couple d\’aubergistes, malhonnête et sans scrupules, exploitent la fillette jusqu\’à 
            ce que Jean Valjean tienne sa promesse et l\’adopte. Cosette devient alors sa raison de vivre. 
            Mais son passé le rattrape et l\’inspecteur Javert le traque…', 352, 104, NULL, NULL );
INSERT INTO LIVRE VALUES ('ISBN3456456011', 'Les Particules Elémentaires', NULL, 7.99, 'TVA2', 'Aout 1998', NULL, 
			'Les Particules élémentaires est un roman de Michel Houellebecq publié en 1998 chez Flammarion. 
            Ce deuxième roman de l\'auteur a reçu l\'ultime prix Novembre et a été élu par la rédaction 
            de la revue Lire « meilleur livre de l année 1998 ».', 317, 14, NULL, NULL );
INSERT INTO LIVRE VALUES ('ISBN3456980012', 'Orgueil et Préjugés', NULL, 6.90, 'TVA2', 'Juillet 1813', NULL, 
			'Orgueil et Préjugés est un roman de la femme de lettres anglaise Jane Austen paru en 1813. 
            Il est considéré comme l\'une de ses œuvres les plus significatives et est aussi la plus connue 
            du grand public. ', 236, 29, NULL, NULL );
INSERT INTO LIVRE VALUES ('ISBN0000059013', 'L\'univers Élégant', NULL, 14.90, 'TVA2', 'Septembre 2000', NULL, 
			'Les physiciens aiment l\'élégance, l\'économie, la simplicité. Ils rêvent de théories permettant d\'expliquer
            d\'un seul coup le plus de phénomènes possibles.', 656, 75, NULL, NULL );
INSERT INTO LIVRE VALUES ('ISBN5601254014', 'Pierre Vernimmen', 'Finance d\'entreprise 2020', 62.95, 'TVA2', 'Août 2019', NULL, 
			'Finance d\'entreprise 2020 intègre les préoccupations environnementales et sociales qui changent la finance d’entreprise.
			Écrit par deux anciens banquiers d\'affaires, actuellement business angels ou senior banker et professeurs à HEC Paris, 
            « Le Vernimmen » est le manuel de gestion couvrant l\'ensemble des domaines de la finance d\'entreprise en partant à la 
            fois de l\'analyse des données comptables et des techniques de marché pour conduire à une analyse rigoureuse de toute
            décision financière sous ses aspects théoriques et pratiques.
			Véritable outil pédagogique, chaque chapitre de l\'ouvrage est suivi d\'un résumé, de questions et d\'exercices actualisés
            avec leurs corrigés, et d\'une bibliographie sélective.', 1220, 30, NULL, NULL );
INSERT INTO LIVRE VALUES ('ISBN3856995015', 'Trois sœurcières', NULL, 8.90, 'TVA2', 'Avril 1995', NULL, 
			'À Lancre, le roi Vérence meurt assassiné par son cousin le duc Kasqueth, qui prend sa place sur le trône afin de
            satisfaire sa femme ambitieuse. Vérence se retrouve à hanter son propre château.
			Son fils (ainsi que sa couronne) est caché dans une troupe de théâtre itinérante, grâce aux trois sorcières 
            Mémé Ciredutemps, Nounou Ogg et Magrat Goussedail, pour qu\'il échappe au même sort. Le duc Kasqueth tente alors de
            forcer les sorcières du pays à lui obéir, sans grand succès.', 320, 12, NULL, NULL );
INSERT INTO LIVRE VALUES ('ISBN0025684016', 'Carbone & Silicium', NULL, 22.90, 'TVA2', 'Août 2020', NULL, 
			'Carbone est un personnage qui s\'investit dans le monde, qui veut être une actrice de son évolution. 
            Silicium, lui, s\'en détache et cherche à en découvrir toute la beauté. Le plus important, au-delà de leur 
            trajectoire personnelle et parfois éloignée, c\'est le lien qui les unit.', 272, 26, NULL, NULL );
INSERT INTO LIVRE VALUES ('ISBN9568745017', 'Les 4 accords toltèques', 'La voie de la liberté personnelle', 
			8.40, 'TVA2', 'Janvier 2016', NULL, 
			'Castaneda a fait découvrir au grand public les enseignements des chamans mexicains qui ont pour origine la tradition
            toltèque, gardienne des connaissances de Quetzacoatl, le serpent à plumes. Dans ce livre, Don Miguel révèle la
            source des croyances limitatrices qui nous privent de joie et créent des souffrances inutiles. Il montre
            en des termes très simples comment on peut se libérer du conditionnement collectif - le rêve de la planète, basé sur
            la peur - afin de retrouver la dimension d\'amour inconditionnel qui est à notre origine et constitue le fondement
            des enseignements toltèques. Les quatre accords proposent un puissant code de conduite capable de transformer
            rapidement notre vie en une expérience de liberté, de vrai bonheur et d\'amour. Le monde fascinant de la Connaissance
            véritable et incarnée est enfin à la portée de chacun.', 141, 18, NULL, NULL );
            
		
select * from LIVRE;

INSERT INTO ATTRIBUER VALUES 
	('ISBN5601284002', '00001EVE'),
	('ISBN8564751003', '00001EVE'),
	('ISBN4524700005', '00003EVE'),
	('ISBN7889685006', '00004EVE'),
	('ISBN7244905007', '00002EVE'),
	('ISBN0478891008', '00001EVE'),
	('ISBN0192834009', '00002EVE'),
	('ISBN3454327010', '00003EVE'),
	('ISBN0000059013', '00004EVE'),
	('ISBN5601254014', '00004EVE'),
	('ISBN3856995015', '00004EVE'),
	('ISBN0025684016', '00004EVE'),
	('ISBN9568745017', '00004EVE');

INSERT INTO AUTEUR VALUES 
	('00001AUT', 'Bernard', 'Werber', NULL),
	('00002AUT', 'John Ronald Reuel', 'Tolkien', NULL),
	('00003AUT', 'Joanne K', 'Rowling', NULL),
	('00004AUT', 'Jodi', 'Taylor', NULL),
	('00005AUT', 'Lockert', 'Olivier', NULL),
	('00006AUT', 'D Avella', 'Matt', NULL),
	('00007AUT', 'Juza', 'Camille', NULL),
	('00008AUT', 'Beguin',  'Isabelle', NULL),
	('00009AUT', 'Sagan', 'Francoise', NULL),
	('00010AUT', 'Hugo', 'Victor', NULL),
	('00011AUT', 'Houellebecq', 'Michel', NULL),
	('00012AUT', 'Jane',  'Austen', NULL),
	('00013AUT', 'Greene', 'Brian', NULL),
	('00014AUT', 'Quiry', 'Pascal', NULL),
	('00015AUT', 'le Fur', 'Yann', NULL),
	('00016AUT', 'Pratchett', 'Terry', NULL),
	('00017AUT', 'Bablet', 'Mathieu', NULL),
	('00018AUT', 'Ruiz', 'Don Miguel', NULL);


INSERT INTO ECRIRE VALUES 
	('ISBN0000059001', '00001AUT'),
	('ISBN8564751003', '00002AUT'),
	('ISBN0025684004', '00003AUT'),
	('ISBN5601284002', '00004AUT'),
	('ISBN4524700005', '00005AUT'),
	('ISBN7889685006', '00006AUT'),
	('ISBN7244905007', '00007AUT'),
	('ISBN0478891008', '00008AUT'),
	('ISBN0192834009', '00009AUT'),
	('ISBN3454327010', '00010AUT'),
	('ISBN3456456011', '00011AUT'),
	('ISBN3456980012', '00012AUT'),
	('ISBN0000059013', '00013AUT'),
	('ISBN5601254014', '00014AUT'),
	('ISBN5601254014', '00015AUT'),
	('ISBN3856995015', '00016AUT'),
	('ISBN0025684016', '00017AUT'),
	('ISBN9568745017', '00018AUT');
--  select * from ecrire


INSERT INTO MOT_CLE VALUES 
	('00001MOT', 'Sorcier'),
	('00002MOT', 'Ame'),
	('00003MOT', 'Troll'),
	('00004MOT', 'Histoire'),
	('00005MOT', 'Moderne'),
	('00006MOT', 'Forme'),
	('00007MOT', 'Conscience'),
	('00008MOT', 'Question'),
	('00009MOT', 'Politique'),
	('00010MOT', 'Roman'),
	('00011MOT', 'Paris'),
	('00012MOT', 'Amour'),
	('00013MOT', 'Vulgarisation'),
	('00014MOT', 'Finance'),
	('00015MOT', 'Fantasy'),
	('00016MOT', 'Developpement personnel');

INSERT INTO ATTRIBUER1 VALUES 
	('00002MOT', 'ISBN0000059001'),
	('00004MOT', 'ISBN5601284002'),
	('00003MOT', 'ISBN8564751003'),
	('00001MOT', 'ISBN0025684004'),
	('00001MOT', 'ISBN4524700005'),
	('00005MOT', 'ISBN7889685006'),
	('00006MOT', 'ISBN7244905007'),
	('00005MOT', 'ISBN0478891008'), 
	('00010MOT', 'ISBN0192834009'),
	('00012MOT', 'ISBN0192834009'),
	('00004MOT', 'ISBN3454327010'),
	('00009MOT', 'ISBN3454327010'),
	('00005MOT', 'ISBN3456456011'),
	('00010MOT', 'ISBN3456456011'),
	('00004MOT', 'ISBN3456980012'),
	('00012MOT', 'ISBN3456980012'),
	('00013MOT', 'ISBN0000059013'),
	('00012MOT', 'ISBN0000059013'),
	('00014MOT', 'ISBN5601254014'),
	('00005MOT', 'ISBN5601254014'),
	('00015MOT', 'ISBN3856995015'),
	('00001MOT', 'ISBN3856995015'),
	('00012MOT', 'ISBN0025684016'),
	('00016MOT', 'ISBN9568745017'),
	('00002MOT', 'ISBN9568745017');



INSERT INTO EDITEUR VALUES 
	('00001EDI', 'HC editions', NULL, NULL, NULL, NULL, NULL),
	('00002EDI', 'Gallimard', NULL, NULL, NULL, NULL, NULL),
	('00003EDI', 'Eni', NULL, NULL, NULL, NULL, NULL),
	('00004EDI', 'Plon', NULL, NULL, NULL, NULL, NULL),
	('00005EDI', 'Flammarion', NULL, NULL, NULL, NULL, NULL),
	('00006EDI', 'Poche', NULL, NULL, NULL, NULL, NULL),
	('00007EDI', 'Vérone', NULL, NULL, NULL, NULL, NULL),
	('00008EDI', 'Les éditeurs Parisiens', NULL, NULL, NULL, NULL, NULL);


INSERT INTO EDITER VALUES 
	('ISBN0000059001', '00001EDI'),
	('ISBN5601284002', '00002EDI'),
	('ISBN8564751003', '00001EDI'),
	('ISBN0025684004', '00002EDI'),
	('ISBN4524700005', '00004EDI'),
	('ISBN7889685006', '00005EDI'),
	('ISBN7244905007', '00002EDI'),
	('ISBN0478891008', '00003EDI'),
	('ISBN0192834009', '00002EDI'),
	('ISBN3454327010', '00002EDI'),
	('ISBN3456456011', '00006EDI'),
	('ISBN3456980012', '00002EDI'),
	('ISBN0000059013', '00001EDI'),
	('ISBN5601254014', '00003EDI'),
	('ISBN3856995015', '00005EDI'),
	('ISBN0025684016', '00007EDI'),
	('ISBN9568745017', '00001EDI');

INSERT INTO GENRE VALUES 
	('00001GEN', 'Manga'),
	('00002GEN', 'Comics'),
	('00003GEN', 'Theatre'),
	('00004GEN', 'Poesie'),
	('00005GEN', 'Biographie'),
	('00006GEN', 'Politique'),
	('00007GEN', 'Narratif'),
	('00008GEN', 'Documentaire'),
	('00009GEN', 'Roman'),
	('00010GEN', 'Epistolaire'),
	('00011GEN', 'Conte'),
	('00012GEN', 'Historique'),
	('00013GEN', 'Non-fiction'),
	('00014GEN', 'Bande-dessinée'),
	('00015GEN', 'Science-fiction'),
	('00016GEN', 'Mythe');


INSERT INTO THEME VALUES 
	('00001THE', 'Humour'),
	('00002THE', 'Langage'),
	('00003THE', 'Voyage'),
	('00004THE', 'Esprit'),
	('00005THE', 'Nature'),
	('00006THE', 'Sport'),
	('00007THE', 'Histoire'),
	('00008THE', 'Bien-être'),
	('00009THE', 'Zombie'),
	('00010THE', 'Fantastique'),
	('00011THE', 'Mer'),
	('00012THE', 'Moyen Age'),
	('00013THE', 'Romance'),
	('00014THE', 'Futuriste'),
	('00015THE', 'Entreprise'),
	('00016THE', 'Renaissance');


INSERT INTO AVOIR VALUES
	('ISBN0000059001', '00004THE'),
	('ISBN5601284002', '00003THE'),
	('ISBN8564751003', '00003THE'),
	('ISBN0025684004', '00002THE'),
	('ISBN4524700005', '00004THE'),
	('ISBN7889685006', '00008THE'),
	('ISBN7244905007', '00006THE'),
	('ISBN0478891008', '00015THE'),
	('ISBN0192834009', '00013THE'),
	('ISBN3454327010', '00014THE'),
	('ISBN3456456011', '00007THE'),
	('ISBN3456980012', '00013THE'),
	('ISBN0000059013', '00005THE'),
	('ISBN5601254014', '00015THE'),
	('ISBN3856995015', '00010THE'),
	('ISBN0025684016', '00014THE'),
	('ISBN9568745017', '00008THE');


INSERT INTO POSSEDER VALUES 
	('00001THE', '00001GEN'),
	('00002THE', '00002GEN'),
	('00003THE', '00003GEN'),
	('00004THE', '00004GEN'),
	('00005THE', '00008GEN'),
	('00006THE', '00005GEN'),
	('00007THE', '00007GEN'),
	('00008THE', '00007GEN'),
	('00009THE', '00015GEN'),
	('00010THE', '00015GEN'),
	('00011THE', '00016GEN'),
	('00012THE', '00012GEN'),
	('00010THE', '00003GEN'),
	('00010THE', '00014GEN'),
	('00010THE', '00013GEN');


INSERT INTO CLIENT VALUES 
	('philou', 'Risoli', 'Philippe', 'phiri', 'juste.prix@gmail.org',NULL, NULL, NULL),
	('juju', 'Courbet', 'Julien', 'jucou', 'sans.aucuns.doutes@gmail.org', NULL, NULL, NULL),
	('guyguy', 'Lux', 'Guy', 'gulu', 'interville@aol.org', NULL, NULL, NULL),
	('patoch', 'Poivre d\'Arvor', 'Patrick', 'ppda', 'le.JT@gmail.org',NULL, NULL, NULL),
	('sampete', 'Sampras', 'Pete', 'balle','pistolPete@atp.org', NULL, NULL, NULL),
	('willS', 'Smith', 'Will', 'onclephil','fresh@prince.com',NULL, NULL, NULL),
	('jackychoun', 'Chan', 'Jacky', 'kungfu','jtedefonce@free.fr', NULL, NULL, NULL),
	('bernie', 'Pivot', 'Bernard', 'ageanivo','antenne2@aol.com', NULL, NULL, NULL),
	('marine_cw', 'Charrier', 'Marine', 'coucou', 'marine.charrier1@gmail.com', NULL, NULL, NULL),
	('marine.c', 'Chart', 'Marine', 'coucou', 'mchart@gmail.com', NULL, NULL, NULL),
	('joe.biden', 'Biden', 'Joe', 'whitehouse', 'joe@whitehouse.usa', NULL, NULL, NULL),
	('emmanuel_macron', 'Macron', 'Emmanuel', 'cestnotreprojet', 'manu@elysee.com', NULL, NULL, NULL),
	('asid', 'Fernandes', 'Sidonie', 'sisi1', 'princesse.sisi@gmail.org', NULL, NULL, NULL),
	('roxy', 'Flantier', 'Noël', 'nono1', 'bouledenoel.flantier@gmail.org', NULL, NULL, NULL),
	('gazel', 'Robert', 'Joe', 'jojo1', 'chasseur.gazel@aol.org', NULL, NULL, NULL),
	('pinoch', 'Bond', 'James', 'jaja1', 'bond.jamesbond.007@gmail.org', NULL, NULL, NULL);


INSERT INTO STATUT VALUES
	('ST1', 'Validée en attente de traitement'),
    ('ST2', 'En cours de préparation'),
    ('ST3', 'Commande remis au transporteur'),
    ('ST4', 'Commande en cours de livraison'),
    ('ST5', 'Commande réceptionnée'),
    ('ST6', 'Commande retournée pour échange'),
    ('ST7', 'Echange remis au transporteur'),
    ('ST8', 'Echange en cours de livraison'),
    ('ST9', 'Echange réceptionné'),
    ('ST10', 'Commande retournée pour remboursement'),
    ('ST11', 'Remboursement effectuée'),
    ('ST12', 'Commande annulée'),
    ('ST13', 'Commande terminée');


ALTER TABLE ADRESSE
	MODIFY COLUMN ADRESSETEL VARCHAR(50) NULL;
-- ALTER COLUMN ADRESSETEL VARCHAR(50) NULL;


INSERT INTO ADRESSE VALUES 
	('00001ADR', NULL, 'Grey', 'Meredith', '10', 'Boulevard des scalpels', NULL, '93120', 'La Courneuve', 'FRANCE', NULL, NULL),
	('00002ADR', NULL, 'Scofield', 'Michael', '5', ' Chemin des Barreaux', NULL,  '93420', 'Villepinte', 'FRANCE', NULL, NULL),
	('00003ADR', NULL, 'Uzumaki', 'Naruto', '3', 'allée des Ramens',NULL,  '75013', 'Paris', 'FRANCE', NULL, NULL),
	('00004ADR', NULL, 'Choumicha', 'Dubled', '20', 'rue de Tanger', NULL, '75020', 'Paris', 'FRANCE', NULL, NULL),
	('00005ADR', NULL, 'Sampras', 'Pete', '1500', 'rue des aces', NULL, '89000', 'Auxerre', 'FRANCE', NULL, NULL),
	('00006ADR', NULL, 'Smith', 'Will', '20', 'boulevard de bel-air', NULL, '66400', 'Céret', 'FRANCE', NULL, NULL),
	('00007ADR', NULL, 'Chan', 'Jacky', '44', 'Route de la soie', NULL, '69100', 'Villeurbanne', 'FRANCE', NULL,NULL),
	('00008ADR', NULL, 'Pivot', 'Bernard', '123', 'rue Apostrophe',NULL,  '34000', 'Montpellier', 'FRANCE', NULL, NULL),
	('00009ADR', NULL, 'Charrier', 'Marine', '13', 'boulevard du centre', NULL, '13008', 'Marseille', 'FRANCE', NULL, NULL),
	('00010ADR', NULL, 'Chart', 'Marine', '12', 'boulevard de la liberation', NULL, '94300', 'Vincennes', 'FRANCE', NULL, NULL),
	('00011ADR', NULL, 'Biden', 'Joe', '13', 'RUE DES ETATS-UNIS', NULL, '75008', 'PARIS', 'FRANCE', NULL, 'MAISON BLANCHE'),
	('00012ADR', NULL, 'Macron', 'Emmanuel', '01', 'rue de l\'Elysée', NULL,  '75008', 'PARIS', 'FRANCE', NULL, NULL),
	('00013ADR', NULL, 'Creissant', 'Robert', '89', 'avenue Ferdinand de Lesseps', NULL,  '59760', 'Grande-Synthe', 'FRANCE', NULL, NULL),
	('00014ADR', NULL, 'Flantier', 'Noël', '53', ' Chemin Du Lavarin Sud', NULL, '62100', 'Calais', 'FRANCE', NULL, NULL),
	('00015ADR', NULL, 'Bouvier', 'Corinne', '9', 'rue Clement Marot',NULL,  '93380', 'Pierrefitte sur Seine', 'FRANCE', NULL, 'MAISON BLANCHE'),
	('00016ADR', NULL, 'Patenaude', 'Christelle', '72', 'rue Sadi Carnot',NULL,  '32000', 'Auch', 'FRANCE', NULL, NULL);


INSERT INTO COMMANDE VALUES 
	('00001CMD', 'philou', 'CB', '5.90', '06.09.2016', '00001FAC', 'TVA2', 'ST2', '00001ADR', '00004ADR', '12.91.245.272',NULL,'20.09.2016'),
	('00002CMD', 'juju', 'CB', '5.90', '02.07.2009', '00002FAC', 'TVA2', 'ST3', '00002ADR', '00001ADR', '12.91.245.282',NULL,'20.08.2009' ),
	('00003CMD', 'guyguy', 'CB', '5.90', '03.02.2006', '00003FAC', 'TVA2', 'ST4', '00003ADR', '00002ADR', '12.91.245.292',NULL,'15.03.2006' ),
	('00004CMD', 'patoch', 'CB', '5.90', '09.11.2019', '00004FAC', 'TVA2', 'ST5', '00004ADR', '00003ADR', '12.91.245.372',NULL,'27.11.2019' ),
	('00005CMD', 'sampete', 'CB', '5.90', '14.10.2020', '00005FAC', 'TVA1', 'ST2', '00005ADR', '00006ADR', '18.97.284.276', NULL, '24.10.2019'),
	('00006CMD', 'willS', 'CB', '5.90', '22.11.2020', '00006FAC', 'TVA1', 'ST13', '00006ADR', '00008ADR', '11.67.265.149', NULL, '22.08.2017'),
	('00007CMD', 'jackychoun', '5.90', 'CB', '19.12.2018', '00007FAC', 'TVA1', 'ST12', '00007ADR', '00007ADR', '17.78.134.222', NULL, '17.10.2020'),
	('00008CMD', 'bernie', 'CB', '5.90', '09.08.2020', '00008FAC', 'TVA1', 'ST13', '00008ADR', '00005ADR', '19.89.120.277', NULL, '12.03.2018'),
	('00009CMD', 'marine_cw', 'CB', '5.90', '20.12.2020', '00009FAC', 'TVA1', 'ST2', '00009ADR', '00009ADR', '12.91.224.287', NULL, '23.12.2020'),
	('00010CMD', 'marine.c', 'CB', '5.90','20.09.2019', '00010FAC', 'TVA1', 'ST13', '00010ADR', '00009ADR', '12.91.245.212', NULL, '30.09.2019'),
	('00011CMD', 'joe.biden', 'CB', '5.90', '18.10.2020', '00011FAC', 'TVA1', 'ST12', '00011ADR', '00011ADR', '12.91.232.219', NULL, '23.10.2020'),
	('00012CMD', 'emmanuel_macron', 'CB', '5.90', '03.11.2020', '00012FAC', 'TVA1', 'ST13', '00012ADR', '00011ADR', '12.91.320.211', NULL, '03.12.2020'),
	('00013CMD', 'asid', 'CB', '5.90', '22.12.2020', '00013FAC', 'TVA1', 'ST2', '00013ADR', '00013ADR', '13.92.225.288', NULL, '26.12.2020'),
	('00014CMD', 'roxy', 'CB', '5.90', '28.09.2019', '00014FAC', 'TVA1', 'ST5', '00014ADR', '00013ADR', '12.81.145.112', NULL, '02.10.2019'),
	('00015CMD', 'gazel', 'CB', '5.90', '11.10.2020', '00015FAC', 'TVA1', 'ST10', '00015ADR', '00015ADR', '12.92.235.319', NULL, '25.10.2020'),
	('00016CMD', 'pinoch', 'CB', '5.90', '02.11.2020', '00016FAC', 'TVA1', 'ST13', '00016ADR', '00015ADR', '14.91.325.243', NULL, '14.12.2020');

select * from COMMANDE;

delete from COMMANDE
	where COMMANDENUM = '00007CMD';
    
insert into COMMANDE Values
	('00007CMD', 'jackychoun', 'CB', '5.90', '19.12.2018', '00007FAC', 'TVA1', 'ST12', '00007ADR', '00007ADR', '17.78.134.222', NULL, '17.10.2020');

INSERT INTO FACTURER VALUES 
	('philou','00001ADR'),
	('juju','00002ADR'),
	('guyguy','00003ADR'),
	('patoch','00004ADR'),
	('sampete','00005ADR'),
	('willS','00006ADR'),
	('jackychoun','00007ADR'),
	('bernie','00008ADR'),
	('marine_cw', '00009ADR'),
	('marine.c', '00010ADR'),
	('joe.biden', '00011ADR'),
	('emmanuel_macron', '00012ADR'),
	( 'asid', '00013ADR'),
	( 'roxy', '00014ADR'),
	( 'gazel', '00015ADR'),
	( 'pinoch', '00016ADR');


INSERT INTO FAIRE_LIVRER VALUES 
	('philou', '00004ADR'),
	('juju', '00001ADR'),
	('guyguy', '00002ADR'),
	('patoch', '00003ADR'),
	('sampete', '00006ADR'),
	('willS', '00008ADR'),
	('jackychoun', '00007ADR'),
	('bernie', '00005ADR'),
	('marine_cw', '00009ADR'),
	('marine.c', '00009ADR'),
	('joe.biden', '00011ADR'),
	('emmanuel_macron', '00011ADR'),
	('asid', '00013ADR'),
	('roxy', '00013ADR'),
	('pinoch', '00015ADR');


INSERT INTO SOCIETE VALUES
	('156.787.564.00001', 'Ma librairie à moi', '8, place de la Madeleine - 75008 PARIS', '--CHEMIN VERS LA SOURCE DE L\'IMAGE--', '01.45.67.89.44', 'www.malibrairieamoi.io', 'RCS Paris : 156.787.564', '10.000 €', 'Stagiaire & co SARL');