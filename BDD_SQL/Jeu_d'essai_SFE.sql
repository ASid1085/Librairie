INSERT INTO TVA VALUES 
	('TVA1', 2.1),
	('TVA2', 5.5),
    ('TVA3', 7),
    ('TVA4', 10),
	('TVA5', 20);
    
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
    
INSERT INTO DROITS_D_ACCES VALUES
	('DT1', 'Administrateur'),
	('DT2', 'Responsable'),
	('DT3', 'Employé'),
    ('DT4', 'Stagiaire');

INSERT INTO SOCIETE VALUES
	('Siret', '156.787.564.00001'),
    ('Nom', 'Ma librairie à moi'),
    ('Siège', '8, place de la Madeleine - 75008 PARIS'),
    ('Logo', '--CHEMIN VERS LA SOURCE DE L\'IMAGE--'),
    ('Tel', '01.45.67.89.44'),
    ('Site', 'www.malibrairieamoi.io'),
    ('RCS', 'RCS Paris : 156.787.564'),
    ('Capital', '10.000 €'),
    ('RS' , 'Stagiaire & co SARL');