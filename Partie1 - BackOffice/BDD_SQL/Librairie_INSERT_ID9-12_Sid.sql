INSERT INTO COMMANDE VALUES ("00013CMD", "asid", "CB", "5.90", "22.12.2020", "00013FAC", "TVA1", "ST2", "00013ADR", "00013ADR", "13.92.225.288", NULL, "26.12.2020");
INSERT INTO COMMANDE VALUES ("00014CMD", "roxy", "CB", "5.90", "28.09.2019", "00014FAC", "TVA1", "ST5", "00014ADR", "00013ADR", "12.81.145.112", NULL, "02.10.2019");
INSERT INTO COMMANDE VALUES ("00015CMD", "gazel", "CB", "5.90", "11.10.2020", "00015FAC", "TVA1", "ST10", "00015ADR", "00015ADR", "12.92.235.319", NULL, "25.10.2020");
INSERT INTO COMMANDE VALUES ("00016CMD", "pinoch", "CB", "5.90", "02.11.2020", "00016FAC", "TVA1", "ST13", "00016ADR", "00015ADR", "14.91.325.243", NULL, "14.12.2020");

INSERT INTO EMPLOYE VALUES ("00013EMP", "DT3", "Touati", "Michel", "DAF", "mtouati", "employe");
INSERT INTO EMPLOYE VALUES ("00014EMP", "DT3", "Do", "Jeanne", "Assitante de direction", "jdo", "mploye");
INSERT INTO EMPLOYE VALUES ("00015EMP", "DT4", "Hilton", "Paris", "RH", "philton", "stagiaire");
INSERT INTO EMPLOYE VALUES ("00016EMP", "DT2", "Bacri", "Jean-Pierre", "Commercial", "jpbacri", "responsable");

INSERT INTO EVENEMENT VALUES("00004EVE", "Vos cadeaux de Noël", "20.11.2021", "24.12.2021", 5.00, "CHRISTMAS",NULL, NULL);  

INSERT INTO LIVRE VALUES ("ISBN0000059874", "L'univers Élégant", NULL, 14.90, "TVA2" "Septembre 2000", NULL, 
			"Les physiciens aiment l'élégance, l'économie, la simplicité. Ils rêvent de théories permettant d'expliquer
            d'un seul coup le plus de phénomènes possibles.", 656, 75, NULL, NULL );
INSERT INTO LIVRE VALUES ("ISBN5601254766", "Pierre Vernimmen", "Finance d'entreprise 2020", 62.95, "TVA2" "Août 2019", NULL, 
			"Finance d'entreprise 2020 intègre les préoccupations environnementales et sociales qui changent la finance d’entreprise.
			Écrit par deux anciens banquiers d'affaires, actuellement business angels ou senior banker et professeurs à HEC Paris, 
            « Le Vernimmen » est le manuel de gestion couvrant l'ensemble des domaines de la finance d'entreprise en partant à la 
            fois de l'analyse des données comptables et des techniques de marché pour conduire à une analyse rigoureuse de toute
            décision financière sous ses aspects théoriques et pratiques.
			Véritable outil pédagogique, chaque chapitre de l'ouvrage est suivi d'un résumé, de questions et d'exercices actualisés
            avec leurs corrigés, et d'une bibliographie sélective.", 1220, 30, NULL, NULL );
INSERT INTO LIVRE VALUES ("ISBN38569954214", "Trois sœurcières", NULL, 8.90, "TVA2" "Avril 1995", NULL, 
			"À Lancre, le roi Vérence meurt assassiné par son cousin le duc Kasqueth, qui prend sa place sur le trône afin de
            satisfaire sa femme ambitieuse. Vérence se retrouve à hanter son propre château.
			Son fils (ainsi que sa couronne) est caché dans une troupe de théâtre itinérante, grâce aux trois sorcières 
            Mémé Ciredutemps, Nounou Ogg et Magrat Goussedail, pour qu'il échappe au même sort. Le duc Kasqueth tente alors de
            forcer les sorcières du pays à lui obéir, sans grand succès.", 320, 12, NULL, NULL );
INSERT INTO LIVRE VALUES ("ISBN0025684465", "Carbone & Silicium", NULL, 22.90, "TVA2" "Août 2020", NULL, 
			"Carbone est un personnage qui s'investit dans le monde, qui veut être une actrice de son évolution. 
            Silicium, lui, s'en détache et cherche à en découvrir toute la beauté. Le plus important, au-delà de leur 
            trajectoire personnelle et parfois éloignée, c'est le lien qui les unit.", 272, 26, NULL, NULL );
INSERT INTO LIVRE VALUES ("ISBN9568745352", "Les 4 accords toltèques", "La voie de la liberté personnelle", 
			8.40, "TVA2" "Janvier 2016", NULL, 
			"Castaneda a fait découvrir au grand public les enseignements des chamans mexicains qui ont pour origine la tradition
            toltèque, gardienne des connaissances de Quetzacoatl, le serpent à plumes. Dans ce livre, Don Miguel révèle la
            source des croyances limi-tatrices qui nous privent de joie et créent des souffrances inutiles. br/ br/ Il montre
            en des termes très simples comment on peut se libérer du conditionnement collectif - le rêve de la planète, basé sur
            la peur - afin de retrouver la dimension d'amour inconditionnel qui est à notre origine et constitue le fondement
            des enseignements toltèques. br/ br/ Les quatre accords proposent un puissant code de conduite capable de transformer
            rapidement notre vie en une expérience de liberté, de vrai bonheur et d'amour. Le monde fascinant de la Connaissance
            véritable et incarnée est enfin à la portée de chacun.", 141, 18, NULL, NULL );


INSERT INTO AUTEUR VALUES ("00013AUT", "Greene", "Brian", NULL);
INSERT INTO AUTEUR VALUES ("00014AUT", "Quiry", "Pascal", NULL);
INSERT INTO AUTEUR VALUES ("00015AUT", "le Fur", "Yann", NULL);
INSERT INTO AUTEUR VALUES ("OOO16AUT", "Pratchett", "Terry", NULL);
INSERT INTO AUTEUR VALUES ("OOO17AUT", "Bablet", "Mathieu", NULL);
INSERT INTO AUTEUR VALUES ("OOO18AUT", "Ruiz", "Don Miguel", NULL);

INSERT INTO MOT_CLE VALUES ("00013MOT", "Vulgarisation");
INSERT INTO MOT_CLE VALUES ("00014MOT", "Finance");
INSERT INTO MOT_CLE VALUES ("00015MOT", "Fantasy");
INSERT INTO MOT_CLE VALUES ("00016MOT", "Developpement personnel");

INSERT INTO EDITEUR VALUES ("00006EDI", "Vérone");
INSERT INTO EDITEUR VALUES ("00008EDI", "Les éditeurs Parisiens");

INSERT INTO GENRE VALUES ("00013GEN", "Non-fiction");
INSERT INTO GENRE VALUES ("00014GEN", "Bande-dessinée");
INSERT INTO GENRE VALUES ("00015GEN", "Science-fiction");
INSERT INTO GENRE VALUES ("00016GEN", "Mythe");

INSERT INTO THEME VALUES ("00013THE", "Romance");
INSERT INTO THEME VALUES ("OOO14THE", "Futuriste");
INSERT INTO THEME VALUES ("OOO15THE", "Entreprise");
INSERT INTO THEME VALUES ("OOO16THE", "Renaissance");

INSERT INTO ADRESSE VALUES ("00013ADR", NULL, "Creissant", "Robert", "89", "avenue Ferdinand de Lesseps", "59760", "Grande-Synthe", "FRANCE", NULL, NULL);
INSERT INTO ADRESSE VALUES ("00014ADR", NULL, "Flantier", "Noël", "53", " Chemin Du Lavarin Sud", "62100", "Calais", "FRANCE", NULL, NULL);
INSERT INTO ADRESSE VALUES ("00015ADR", NULL, "Bouvier", "Corinne", "9", "rue Clement Marot", "93380", "Pierrefitte sur Seine", "FRANCE", NULL, "MAISON BLANCHE");
INSERT INTO ADRESSE VALUES ("00016ADR", NULL, "Patenaude", "Christelle", "72", "rue Sadi Carnot", "32000", "Auch", "FRANCE", NULL, NULL);

INSERT INTO CLIENT VALUES ("asid", "Fernandes", "Sidonie", "sisi1", "princesse.sisi@gmail.org", NULL, NULL);
INSERT INTO CLIENT VALUES ("roxy", "Flantier", "Noël", "nono1", "bouledenoel.flantier@gmail.org", NULL, NULL);
INSERT INTO CLIENT VALUES ("gazel", "Robert", "Joe", "jojo1", "chasseur2.gazel@aol.org", NULL, NULL);
INSERT INTO CLIENT VALUES ("pinoch", "Bond", "James", "jaja1", "bond.jamesbond.007@gmail.org", NULL, NULL);
