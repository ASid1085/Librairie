INSERT INTO COMMANDE VALUES ("00009CMD", "marine_cw", "CB", "20.12.2020", "00009FAC", "TVA1", "ST2", "00009ADR", "00009ADR", "12.91.224.287", NULL, "23.12.2020");
INSERT INTO COMMANDE VALUES ("00010CMD", "marine.c", "CB", "20.09.2019", "00010FAC", "TVA1", "ST13", "00010ADR", "00009ADR", "12.91.245.212", NULL, "30.09.2019");
INSERT INTO COMMANDE VALUES ("00011CMD", "joe.biden", "CB", "18.10.2020", "00011FAC", "TVA1", "ST12", "00011ADR", "00011ADR", "12.91.232.219", NULL, "23.10.2020");
INSERT INTO COMMANDE VALUES ("00012CMD", "emmanuel_macron", "CB", "03.11.2020", "00012FAC", "TVA1", "ST13", "00012ADR", "00011ADR", "12.91.320.211", NULL, "03.12.2020");

INSERT INTO EMPLOYE VALUES ("00008EMP", "DT2", "Dupont", "Jacques", "comptable", "jdupont", "employe");
INSERT INTO EMPLOYE VALUES ("00009EMP", "DT1", "Marie", "Jean", "informaticien", "jmarie", "admin");
INSERT INTO EMPLOYE VALUES ("00010EMP", "DT3", "Valois", "Paris", "marketing", "pvalois", "employe");
INSERT INTO EMPLOYE VALUES ("00012EMP", "DT4", "Marois", "Jacqueline", "RH", "jmarois", "employe");

INSERT INTO EVENEMENT VALUES("00003EVE", "La rentrée", "01.09.2021", "30.09.2021", 5.00, "BACK2SCHOOL",NULL, NULL);  

-- INSERT INTO COMMENTAIRE VALUES ("00008COM", "marine.c", "")

INSERT INTO LIVRE VALUES ("ISBN0192834756", "Les 4 coins du coeur", NULL, 12.90, "TVA2" "Septembre 2019", NULL, 
			"Les Quatre Coins du coeur est le dernier roman de Françoise Sagan. Subtil, résolument libre, 
			empreint de son immense maîtrise, irrigué par sa passion des sentiments et de leur altérité. 
            L'intelligence, le cocasse, cette élégance qui lui permet de passer sur les drames de manière 
            si vive et si concise, tout se rencontre et nous permet de revisiter une vie de Sagan à laquelle 
            rien ne manque dans ce roman inachevé, brut et bouleversant.", 117, 100, NULL, NULL );
INSERT INTO LIVRE VALUES ("ISBN3454327890", "Les Misérables", NULL, 4.95, "TVA2" "Aout 2014", NULL, 
			"Le destin de Jean Valjean, forçat échappé du bagne, est bouleversé par sa rencontre avec Fantine. 
            Mourante et sans le sou, celle-ci lui demande de prendre soin de Cosette, sa fille confiée aux 
            Thénardier. Ce couple d’aubergistes, malhonnête et sans scrupules, exploitent la fillette jusqu’à 
            ce que Jean Valjean tienne sa promesse et l’adopte. Cosette devient alors sa raison de vivre. 
            Mais son passé le rattrape et l’inspecteur Javert le traque…", 352, 104, NULL, NULL );
INSERT INTO LIVRE VALUES ("ISBN3456456489", "Les Particules Elémentaires", NULL, 7.99, "TVA2" "Aout 1998", NULL, 
			"Les Particules élémentaires est un roman de Michel Houellebecq publié en 1998 chez Flammarion. 
            Ce deuxième roman de l'auteur a reçu l'ultime prix Novembre et a été élu par la rédaction 
            de la revue Lire « meilleur livre de l'année 1998 ».", 317, 14, NULL, NULL );
INSERT INTO LIVRE VALUES ("ISBN3456456489", "Le Pouvoir du Moment Présent", NULL, 7.49, "TVA2" "Juillet 1998", NULL, 
			"Le Pouvoir du moment présent, guide d'éveil spirituel devenu depuis sa parution un best-seller 
            incontournable, n'a d'autre ambition que de rendre heureux celles et ceux qui désirent se prêter 
            au jeu du bonheur. Et si tout le monde peut participer, faut-il encore le vouloir ! Eckart Tolle 
            donne des trucs et des astuces pour prendre la vie du bon côté, même quand le malheur nous accable. 
            Ses conseils bienveillants prennent en compte les forces et les faiblesses de chacun. 
            Le bonheur est une énergie inépuisable et gratuite !", 236, 29, NULL, NULL );
INSERT INTO LIVRE VALUES ("ISBN3456980093", "Orgueil et Préjugés", NULL, 6.90, "TVA2" "Juillet 1813", NULL, 
			"Orgueil et Préjugés est un roman de la femme de lettres anglaise Jane Austen paru en 1813. 
            Il est considéré comme l'une de ses œuvres les plus significatives et est aussi la plus connue 
            du grand public. ", 236, 29, NULL, NULL );


INSERT INTO AUTEUR VALUES ("00009AUT", "Sagan", "Francoise", NULL);
INSERT INTO AUTEUR VALUES ("00010AUT", "Hugo", "Victor", NULL);
INSERT INTO AUTEUR VALUES ("00011AUT", "Houellebecq", "Michel", NULL);
INSERT INTO AUTEUR VALUES ("OOO12AUT", "Jane",  "Austen", NULL);

INSERT INTO MOT_CLE VALUES ("00009MOT", "Historique");
INSERT INTO MOT_CLE VALUES ("00010MOT", "Roman");
INSERT INTO MOT_CLE VALUES ("00011MOT", "Paris");
INSERT INTO MOT_CLE VALUES ("00012MOT", "Amour");

INSERT INTO EDITEUR VALUES ("00005EDI", "Flammarion");
INSERT INTO EDITEUR VALUES ("00006EDI", "Poche");

INSERT INTO GENRE VALUES ("00009GEN", "Roman");
INSERT INTO GENRE VALUES ("00010GEN", "Epistolaire");
INSERT INTO GENRE VALUES ("00011GEN", "Théâtre");
INSERT INTO GENRE VALUES ("00012GEN", "Historique");

INSERT INTO THEME VALUES ("00009THE", "Zombie");
INSERT INTO THEME VALUES ("OOO10THE", "Fantastique");
INSERT INTO THEME VALUES ("OOO11THE", "Mer");
INSERT INTO THEME VALUES ("OOO12THE", "Moyen Age");

INSERT INTO ADRESSE VALUES ("00009ADR", NULL, "Charrier", "Marine", "13", "boulevard du centre", "13008", "Marseille", "FRANCE", NULL, NULL);
INSERT INTO ADRESSE VALUES ("00010ADR", NULL, "Chart", "Marine", "12", "boulevard de la liberation", "94300", "Vincennes", "FRANCE", NULL, NULL);
INSERT INTO ADRESSE VALUES ("00011ADR", NULL, "Biden", "Joe", "13", "RUE DES ETATS-UNIS", "75008", "PARIS", "FRANCE", NULL, "MAISON BLANCHE");
INSERT INTO ADRESSE VALUES ("00012ADR", NULL, "Macron", "Emmanuel", "01", "rue de l'Elysée", "75008", "PARIS", "FRANCE", NULL, NULL);

INSERT INTO CLIENT VALUES ("marine_cw", "Charrier", "Marine", "coucou", NULL, NULL, NULL);
INSERT INTO CLIENT VALUES ("marine.c", "Chart", "Marine", "coucou", NULL, NULL, NULL);
INSERT INTO CLIENT VALUES ("joe.biden", "Biden", "Joe", "whitehouse", NULL, NULL, NULL);
INSERT INTO CLIENT VALUES ("emmanuel_macron", "Macron", "Emmanuel", "cestnotreprojet", NULL, NULL, NULL);
