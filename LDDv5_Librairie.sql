create schema Librairie;
use Librairie;

/*==============================================================*/
/* Table : ADRESSE                                              */
/*==============================================================*/
create table ADRESSE 
(
   ADRESSEID            varchar (50)                  not null,
   ADRESSECODE          varchar (50)                  null,
   ADRESSENOM           varchar (50)                  not null,
   ADRESSEPRENOM        varchar (50)                  not null,
   ADRESSENORUE         varchar (50)                  null,
   ADRESSERUE           varchar (50)                  not null,
   ADRESSECOMPL         varchar (50)                  null,
   ADRESSECP            varchar (50)                  not null,
   ADRESSEVILLE         varchar (50)                  not null,
   ADRESSEPAYS          varchar (50)                  not null,
   ADRESSETEL           varchar (50)                  not null,
   ADRESSESOCIETE       varchar (50)                  null,
   constraint PK_ADRESSE primary key (ADRESSEID)
);

/*==============================================================*/
/* Table : POSSEDER                                             */
/*==============================================================*/
create table POSSEDER 
(
   THEMEID              varchar (50)                 not null,
   GENREID              varchar (50)                 not null,
   constraint PK_POSSEDER primary key clustered (THEMEID, GENREID),
   constraint FK_POSSEDER_THEME foreign key (THEMEID) references THEME (THEMEID),
   constraint FK_POSSEDER_GENRE foreign key (GENREID) references GENRE (GENREID)
);

/*==============================================================*/
/* Table : ATTRIBUER                                            */
/*==============================================================*/
create table ATTRIBUER 
(
   LIVREISBN            varchar (50)                 not null,
   EVENEMENTID          varchar (50)                 not null,
   constraint PK_ATTRIBUER primary key clustered (LIVREISBN, EVENEMENTID),
   constraint FK_ATTRIBUER_LIVRE foreign key (LIVREISBN) references LIVRE (LIVREISBN),
   constraint FK_ATTRIBUER_EVENEMENT foreign key (EVENEMENTID) references EVENEMENT (EVENEMENTID)
);

/*==============================================================*/
/* Table : ATTRIBUER1                                           */
/*==============================================================*/
create table ATTRIBUER1 
(
   MOTCLEID             varchar (50)                  not null,
   LIVREISBN            varchar (50)                  not null,
   constraint PK_ATTRIBUER1 primary key clustered (MOTCLEID, LIVREISBN),
   constraint FK_ATTRIBUER_MOTCLE foreign key (MOTCLEID) references MOT_CLE (MOTCLEID),
   constraint FK_ATTRIBUER_LIVRE foreign key (LIVREISBN) references LIVRE (LIVREISBN)
);

/*==============================================================*/
/* Table : AUTEUR                                               */
/*==============================================================*/
create table AUTEUR 
(
   AUTEURID             varchar  (50)                 not null,
   AUTEURNOM            varchar  (50)                 not null,
   AUTEURPRENOM         varchar  (50)                 not null,
   AUTEURPSEUDO         varchar  (50)                 null,
   constraint PK_AUTEUR primary key (AUTEURID)
);

/*==============================================================*/
/* Table : AVOIR                                                */
/*==============================================================*/
create table AVOIR 
(
   LIVREISBN            varchar (50)                 not null,
   THEMEID              varchar (50)                 not null,
   constraint PK_AVOIR primary key clustered (THEMEID, LIVREISBN),
   constraint FK_AVOIR_THEME foreign key (THEMEID) references THEME (THEMEID),
   constraint FK_AVOIR_LIVRE foreign key (LIVREISBN) references LIVRE (LIVREISBN)
);

/*==============================================================*/
/* Table : CLIENT                                               */
/*==============================================================*/
create table CLIENT 
(
   CLIENTLOGIN          varchar  (50)                 not null,
   CLIENTNOM            varchar  (50)                 not null,
   CLIENTPRENOM         varchar  (50)                 not null,
   CLIENTMDP            varchar  (50)                 not null,
   CLIENTEMAIL          varchar  (50)                 not null,
   CLIENTTEL            varchar  (50)                 null,
   CLIENTSTATUT         varchar  (50)                 null,
   CLIENTCOMMENT        varchar  (750)                null,
   constraint PK_CLIENT primary key (CLIENTLOGIN)
);

/*==============================================================*/
/* Table : COMMANDE                                             */
/*==============================================================*/
create table COMMANDE 
(
   COMMANDENUM          varchar  (50)                 not null,
   CLIENTLOGIN          varchar  (50)                 not null,
   COMMANDEPAIEMENT     varchar  (50)                 null,
   COMMANDEFORFAITLIVRAISON varchar (50)              null,
   COMMANDEDATE         date                          not null,
   COMMANDENUMFACTURE   varchar  (50)                 null,
   TVAID                varchar  (50)                 not null,
   STATUTID             varchar  (50)                 not null,
   ADRESSEIDF            varchar  (50)                not null,
   ADRESSEIDL      		varchar  (50)                 not null,
   COMMANDEIP           varchar  (50)                 not null,
   COMMANDECOMMENT      varchar  (750)                null,
   DATESTATUT           date                          not null,
   constraint PK_COMMANDE primary key (COMMANDENUM),
   constraint FK_COMMANDE_TVA foreign key (TVAID) references TVA (TVAID),
   constraint FK_COMMANDE_STATUT foreign key (STATUTID) references STATUT (STATUTID),
   constraint FK_COMMANDE_FACT_ADR foreign key (ADRESSEIDF) references ADRESSE (ADRESSEID),
   constraint FK_COMMANDE_LIV_ADR foreign key (ADRESSEIDL) references ADRESSE (ADRESSEID),
   constraint FK_COMMANDE_CLIENT foreign key (CLIENTLOGIN) references CLIENT (CLIENTLOGIN)
);

/*==============================================================*/
/* Table : COMMENTAIRE                                          */
/*==============================================================*/
create table COMMENTAIRE 
(
   COMMENTAIREID        varchar (50)                  not null,
   CLIENTLOGIN          varchar (50)                  not null,
   LIVREISBN            varchar (50)                  not null,
   COMMENTAIRETEXTE     varchar (750)                 not null,
   COMMENTAIRENOTE      varchar (50)                  null,
   COMMENTAIREIP        varchar (50)                  not null,
   COMMENTAIRESTATUT    varchar (50)                  null,
   COMMENTAIREDATE      date                          not null,
   COMMANDENUM          varchar (50)                  not null,
   LIGNECOMMANDEID      varchar (50)                  not null,
   EMPLOYEID            varchar (50)                  null,
   DATEMODERATION       date                          null,
   constraint PK_COMMENTAIRE primary key (CLIENTLOGIN, LIVREISBN, COMMENTAIREID),
   constraint FK_COMMENTAIRE_CLIENT foreign key (CLIENTLOGIN) references CLIENT (CLIENTLOGIN),
   constraint FK_COMMENTAIRE_LIVRE foreign key (LIVREISBN) references LIVRE (LIVREISBN),
   constraint FK_COMMENTAIRE_EMPLOYE foreign key (EMPLOYEID) references EMPLOYE (EMPLOYEID),
   constraint FK_COMMENTAIRE_LIGCDE foreign key (LIVREISBN, COMMANDENUM, CLIENTLOGIN, COMMENTAIREID, LIGNECOMMANDEID)
      references LIGNE_COMMANDE (LIVREISBN, COMMANDENUM, CLIENTLOGIN, COMMENTAIREID, LIGNECOMMANDEID)
);

/*==============================================================*/
/* Table : DROITS_D_ACCES                                       */
/*==============================================================*/
create table DROITS_D_ACCES 
(
   DROITDACCESID        varchar (50)                  not null,
   DROITDACCESLIBELLE   varchar (50)                  not null,
   DROITDACCESTYPE      varchar (50)                  not null,
   constraint PK_DROITS_D_ACCES primary key (DROITDACCESID)
);

/*==============================================================*/
/* Table : ECRIRE                                               */
/*==============================================================*/
create table ECRIRE 
(
   LIVREISBN            varchar (50)                  not null,
   AUTEURID             varchar (50)                  not null,
   constraint PK_ECRIRE primary key clustered (AUTEURID, LIVREISBN),
   constraint FK_ECRIRE_AUTEUR foreign key (AUTEURID) references AUTEUR (AUTEURID),
   constraint FK_ECRIRE_LIVRE foreign key (LIVREISBN) references LIVRE (LIVREISBN)
);

/*==============================================================*/
/* Table : EDITER                                               */
/*==============================================================*/
create table EDITER 
(
   LIVREISBN            varchar (50)                  not null,
   EDITEURID            varchar (50)                  not null,
   constraint PK_EDITER primary key clustered (LIVREISBN, EDITEURID),
   constraint FK_EDITER_LIVRE foreign key (LIVREISBN) references LIVRE (LIVREISBN),
   constraint FK_EDITER_EDITEUR foreign key (EDITEURID) references EDITEUR (EDITEURID)
);

/*==============================================================*/
/* Table : EDITEUR                                              */
/*==============================================================*/
create table EDITEUR 
(
   EDITEURID            varchar  (50)                 not null,
   EDITEURNOM           varchar  (50)                 not null,
   EDITEURADRESSE       varchar  (50)                 null,
   EDITEURTEL           varchar  (50)                 null,
   EDITEURMAIL          varchar  (50)                 null,
   EDITEURCONTACT       varchar  (50)                 null,
   EDITEURCOMMENT       varchar  (750)                null,
   constraint PK_EDITEUR primary key (EDITEURID)
);

/*==============================================================*/
/* Table : EMPLOYE                                              */
/*==============================================================*/
create table EMPLOYE 
(
   EMPLOYEID            varchar (50)                  not null,
   DROITDACCESID        varchar (50)                  not null,
   EMPLOYENOM           varchar (50)                  not null,
   EMPLOYEPRENOM        varchar (50)                  not null,
   EMPLOYEPOSTE         varchar (50)                  null,
   EMPLOYELOG           varchar (50)                  not null,
   EMPLOYEMDP           varchar (50)                  not null,
   constraint PK_EMPLOYE primary key (EMPLOYEID),
   constraint FK_EMPLOYE_DRTACCES foreign key (DROITDACCESID) references DROITS_D_ACCES (DROITDACCESID)
);

/*==============================================================*/
/* Table : EVENEMENT                                            */
/*==============================================================*/
create table EVENEMENT 
(
   EVENEMENTID          varchar (30)                  not null,
   EVENEMENTNOM         varchar (50)                  not null,
   EVENEMENTDATEDEBUT   date                          not null,
   EVENEMENTDATEFIN     date                          not null,
   EVENEMENTPOURCENTAGE numeric(8,2)                  null,
   EVENMENTCODEPROMO    varchar (50)                  null,
   EVENEMENTIMAGE       varchar (50)                  null,
   EVENEMENTCOMMENT     varchar (750)                 null,
   constraint PK_EVENEMENT primary key (EVENEMENTID)
);

/*==============================================================*/
/* Table : FACTURER                                             */
/*==============================================================*/
create table FACTURER 
(
   CLIENTLOGIN          varchar (50)                  not null,
   ADRESSEID            varchar (50)                  not null,
   constraint PK_FACTURER primary key clustered (CLIENTLOGIN, ADRESSEID),
   constraint FK_FACTURER_CLIENT foreign key (CLIENTLOGIN) references CLIENT (CLIENTLOGIN),
   constraint FK_FACTURER_ADRESSE foreign key (ADRESSEID) references ADRESSE (ADRESSEID)
);

/*==============================================================*/
/* Table : FAIRE_LIVRER                                         */
/*==============================================================*/
create table FAIRE_LIVRER 
(
   CLIENTLOGIN          varchar (50)                  not null,
   ADRESSEID            varchar (50)                  not null,
   constraint PK_FAIRE_LIVRER primary key clustered (CLIENTLOGIN, ADRESSEID),
   constraint FK_FAIRE_LIV_CLIENT foreign key (CLIENTLOGIN) references CLIENT (CLIENTLOGIN),
   constraint FK_FAIRE_LIV_ADRESSE foreign key (ADRESSEID) references ADRESSE (ADRESSEID)
);

/*==============================================================*/
/* Table : GENRE                                                */
/*==============================================================*/
create table GENRE 
(
   GENREID              varchar (50)                  not null,
   GENRENOM             varchar (50)                  not null,
   constraint PK_GENRE primary key (GENREID)
);

/*==============================================================*/
/* Table : LIGNE_COMMANDE                                       */
/*==============================================================*/
create table LIGNE_COMMANDE 
(
   LIGNECOMMANDEID      	varchar(50)                   not null,
   COMMANDENUM          	varchar(50)                   not null,
   LIVREISBN            	varchar(50)                   not null,
   LIGNECOMMANDEQUANTITE 	numeric(8,2)                  not null,
   LIGNECOMMANDEPRIXHT  	numeric(8,2)                  not null,
   LIGNECOMMANDETVAAPPLIQUEE numeric(8,2)                 not null,
   LIGNECOMMANDEREMISE  	numeric(8,2)                  null,
   CLIENTLOGIN          	varchar(50)                   not null,
   COMMENTAIREID        	varchar(50)                   not null,
   EVENEMENTID          	varchar(50)                   null,
   
   constraint PK_LIGNE_COMMANDE primary key (LIVREISBN, COMMANDENUM, CLIENTLOGIN, COMMENTAIREID, LIGNECOMMANDEID),
   constraint FK_LIGCDE_LIVRE foreign key (LIVREISBN) references LIVRE (LIVREISBN),
   constraint FK_LIGCDE_EVENEMENT foreign key (EVENEMENTID) references EVENEMENT (EVENEMENTID),
   constraint FK_LIGCDE_COMMANDE foreign key (COMMANDENUM) references COMMANDE (COMMANDENUM),
   constraint FK_LIGCDE_COMMENTAIRE foreign key (CLIENTLOGIN, LIVREISBN, COMMENTAIREID) references COMMENTAIRE (CLIENTLOGIN, LIVREISBN, COMMENTAIREID)
);

/*==============================================================*/
/* Table : LIVRE                                                */
/*==============================================================*/
create table LIVRE 
(
   LIVREISBN            varchar (50)                not null,
   LIVRETITRE           varchar (50)                not null,
   LIVRESOUSTITRE       varchar (50)                null,
   LIVREPRIXHT          numeric (8,2)               not null,
   TVAID                varchar (50)                not null,
   LIVREDATEEDITION     varchar (50)                not null,
   LIVREIMAGE           varchar (50)                null,
   LIVRERESUME          varchar (50)                null,
   LIVRENBREPAGE        numeric (8,2)               not null,
   LIVRESTOCK           varchar (50)                not null,
   LIVRECOMMENT         varchar (750)               null,
   LIVRESTATUT          varchar (50)                null,
   constraint PK_LIVRE primary key (LIVREISBN),
   constraint FK_LIVRE_TVA foreign key (TVAID) references TVA (TVAID)
);
      
/*==============================================================*/
/* Table : MOT_CLE                                              */
/*==============================================================*/
create table MOT_CLE 
(
   MOTCLEID             varchar(50)                   not null,
   MOTCLELIBELLE        varchar(50)                   not null,
   constraint PK_MOT_CLE primary key (MOTCLEID)
);

/*==============================================================*/
/* Table : SOCIETE                                              */
/*==============================================================*/
create table SOCIETE 
(
   SOCIETESIRET         varchar (50)                  not null,
   SOCIETENOM           varchar (50)                  not null,
   SOCIETESIEGESOCIAL   varchar (50)                  not null,
   SOCIETELOGO          varchar (150)                  not null,
   SOCIETETEL           varchar (50)                  null,
   SOCIETESITEWEB       varchar (50)                  null,
   SOCIETERCS           varchar (50)                  not null,
   SOCIETECAPITAL       varchar (50)                  not null,
   SOCIETERAISONSOCIALE varchar (50)                  not null,
   constraint PK_SOCIETE primary key (SOCIETESIRET)
);

/*==============================================================*/
/* Table : STATUT                                               */
/*==============================================================*/
create table STATUT 
(
   STATUTID             varchar (50)                  not null,
   STATUTLIBELLE        varchar (50)                  not null,
   constraint PK_STATUT primary key (STATUTID)
);

/*==============================================================*/
/* Table : THEME                                                */
/*==============================================================*/
create table THEME 
(
   THEMEID              varchar (50)                  not null,
   THEMENOM             varchar (50)                  not null,
   constraint PK_THEME primary key (THEMEID)
);

/*==============================================================*/
/* Table : TVA                                                  */
/*==============================================================*/
create table TVA 
(
   TVAID                varchar (50)                   not null,
   TVATAUX              numeric (8,2)                  not null,
   constraint PK_TVA primary key (TVAID)
);