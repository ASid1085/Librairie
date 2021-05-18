/*==============================================================*/
/* Nom de SGBD :  Sybase SQL Anywhere 11                        */
/* Date de création :  1/15/2021 4:00:39 PM                     */
/*==============================================================*/


if exists(select 1 from sys.sysforeignkey where role='FK_ASSOCIAT_ASSOCIATI_THEME') then
    alter table ASSOCIATION_25
       delete foreign key FK_ASSOCIAT_ASSOCIATI_THEME
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ASSOCIAT_ASSOCIATI_GENRE') then
    alter table ASSOCIATION_25
       delete foreign key FK_ASSOCIAT_ASSOCIATI_GENRE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ATTRIBUE_ATTRIBUER_LIVRE') then
    alter table ATTRIBUER
       delete foreign key FK_ATTRIBUE_ATTRIBUER_LIVRE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ATTRIBUE_ATTRIBUER_EVENEMEN') then
    alter table ATTRIBUER
       delete foreign key FK_ATTRIBUE_ATTRIBUER_EVENEMEN
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ATTRIBUE_ATTRIBUER_MOT_CLE') then
    alter table ATTRIBUER1
       delete foreign key FK_ATTRIBUE_ATTRIBUER_MOT_CLE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ATTRIBUE_ATTRIBUER_LIVRE') then
    alter table ATTRIBUER1
       delete foreign key FK_ATTRIBUE_ATTRIBUER_LIVRE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_AVOIR_AVOIR_THEME') then
    alter table AVOIR
       delete foreign key FK_AVOIR_AVOIR_THEME
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_AVOIR_AVOIR2_LIVRE') then
    alter table AVOIR
       delete foreign key FK_AVOIR_AVOIR2_LIVRE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_COMMANDE_APPLIQUER_TVA') then
    alter table COMMANDE
       delete foreign key FK_COMMANDE_APPLIQUER_TVA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_COMMANDE_ASSOCIATI_STATUT') then
    alter table COMMANDE
       delete foreign key FK_COMMANDE_ASSOCIATI_STATUT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_COMMANDE_FACTURER__ADRESSE') then
    alter table COMMANDE
       delete foreign key FK_COMMANDE_FACTURER__ADRESSE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_COMMANDE_LIVRER_A_ADRESSE') then
    alter table COMMANDE
       delete foreign key FK_COMMANDE_LIVRER_A_ADRESSE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_COMMANDE_PASSER_CLIENT') then
    alter table COMMANDE
       delete foreign key FK_COMMANDE_PASSER_CLIENT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_COMMENTA_ASSOCIATI_CLIENT') then
    alter table COMMENTAIRE
       delete foreign key FK_COMMENTA_ASSOCIATI_CLIENT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_COMMENTA_ASSOCIATI_LIVRE') then
    alter table COMMENTAIRE
       delete foreign key FK_COMMENTA_ASSOCIATI_LIVRE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_COMMENTA_ASSOCIATI_EMPLOYE') then
    alter table COMMENTAIRE
       delete foreign key FK_COMMENTA_ASSOCIATI_EMPLOYE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_COMMENTA_GENERER_LIGNE_CO') then
    alter table COMMENTAIRE
       delete foreign key FK_COMMENTA_GENERER_LIGNE_CO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ECRIRE_ECRIRE_AUTEUR') then
    alter table ECRIRE
       delete foreign key FK_ECRIRE_ECRIRE_AUTEUR
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ECRIRE_ECRIRE2_LIVRE') then
    alter table ECRIRE
       delete foreign key FK_ECRIRE_ECRIRE2_LIVRE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_EDITER_EDITER_LIVRE') then
    alter table EDITER
       delete foreign key FK_EDITER_EDITER_LIVRE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_EDITER_EDITER2_EDITEUR') then
    alter table EDITER
       delete foreign key FK_EDITER_EDITER2_EDITEUR
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_EMPLOYE_ASSOCIATI_DROITS_D') then
    alter table EMPLOYE
       delete foreign key FK_EMPLOYE_ASSOCIATI_DROITS_D
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_FACTURER_FACTURER_CLIENT') then
    alter table FACTURER
       delete foreign key FK_FACTURER_FACTURER_CLIENT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_FACTURER_FACTURER2_ADRESSE') then
    alter table FACTURER
       delete foreign key FK_FACTURER_FACTURER2_ADRESSE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_FAIRE_LI_FAIRE_LIV_CLIENT') then
    alter table FAIRE_LIVRER
       delete foreign key FK_FAIRE_LI_FAIRE_LIV_CLIENT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_FAIRE_LI_FAIRE_LIV_ADRESSE') then
    alter table FAIRE_LIVRER
       delete foreign key FK_FAIRE_LI_FAIRE_LIV_ADRESSE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_LIGNE_CO_AJOUTER_LIVRE') then
    alter table LIGNE_COMMANDE
       delete foreign key FK_LIGNE_CO_AJOUTER_LIVRE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_LIGNE_CO_ASSOCIATI_EVENEMEN') then
    alter table LIGNE_COMMANDE
       delete foreign key FK_LIGNE_CO_ASSOCIATI_EVENEMEN
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_LIGNE_CO_GENERER1_COMMANDE') then
    alter table LIGNE_COMMANDE
       delete foreign key FK_LIGNE_CO_GENERER1_COMMANDE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_LIGNE_CO_GENERER2_COMMENTA') then
    alter table LIGNE_COMMANDE
       delete foreign key FK_LIGNE_CO_GENERER2_COMMENTA
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_LIVRE_APPLIQUER_TVA') then
    alter table LIVRE
       delete foreign key FK_LIVRE_APPLIQUER_TVA
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ADRESSE_PK'
     and t.table_name='ADRESSE'
) then
   drop index ADRESSE.ADRESSE_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='ADRESSE'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table ADRESSE
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION_29_FK'
     and t.table_name='ASSOCIATION_25'
) then
   drop index ASSOCIATION_25.ASSOCIATION_29_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION_25_FK'
     and t.table_name='ASSOCIATION_25'
) then
   drop index ASSOCIATION_25.ASSOCIATION_25_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION_25_PK'
     and t.table_name='ASSOCIATION_25'
) then
   drop index ASSOCIATION_25.ASSOCIATION_25_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='ASSOCIATION_25'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table ASSOCIATION_25
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ATTRIBUER2_FK'
     and t.table_name='ATTRIBUER'
) then
   drop index ATTRIBUER.ATTRIBUER2_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ATTRIBUER_FK'
     and t.table_name='ATTRIBUER'
) then
   drop index ATTRIBUER.ATTRIBUER_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ATTRIBUER_PK'
     and t.table_name='ATTRIBUER'
) then
   drop index ATTRIBUER.ATTRIBUER_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='ATTRIBUER'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table ATTRIBUER
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ATTRIBUER3_FK'
     and t.table_name='ATTRIBUER1'
) then
   drop index ATTRIBUER1.ATTRIBUER3_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ATTRIBUER1_FK'
     and t.table_name='ATTRIBUER1'
) then
   drop index ATTRIBUER1.ATTRIBUER1_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ATTRIBUER1_PK'
     and t.table_name='ATTRIBUER1'
) then
   drop index ATTRIBUER1.ATTRIBUER1_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='ATTRIBUER1'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table ATTRIBUER1
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='AUTEUR_PK'
     and t.table_name='AUTEUR'
) then
   drop index AUTEUR.AUTEUR_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='AUTEUR'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table AUTEUR
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='AVOIR2_FK'
     and t.table_name='AVOIR'
) then
   drop index AVOIR.AVOIR2_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='AVOIR_FK'
     and t.table_name='AVOIR'
) then
   drop index AVOIR.AVOIR_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='AVOIR_PK'
     and t.table_name='AVOIR'
) then
   drop index AVOIR.AVOIR_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='AVOIR'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table AVOIR
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='CLIENT_PK'
     and t.table_name='CLIENT'
) then
   drop index CLIENT.CLIENT_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='CLIENT'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table CLIENT
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION_27_FK'
     and t.table_name='COMMANDE'
) then
   drop index COMMANDE.ASSOCIATION_27_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='APPLIQUER2_FK'
     and t.table_name='COMMANDE'
) then
   drop index COMMANDE.APPLIQUER2_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='PASSER_FK'
     and t.table_name='COMMANDE'
) then
   drop index COMMANDE.PASSER_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='LIVRER_A_FK'
     and t.table_name='COMMANDE'
) then
   drop index COMMANDE.LIVRER_A_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='FACTURER_A_FK'
     and t.table_name='COMMANDE'
) then
   drop index COMMANDE.FACTURER_A_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='COMMANDE_PK'
     and t.table_name='COMMANDE'
) then
   drop index COMMANDE.COMMANDE_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='COMMANDE'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table COMMANDE
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION_28_FK'
     and t.table_name='COMMENTAIRE'
) then
   drop index COMMENTAIRE.ASSOCIATION_28_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION_22_FK'
     and t.table_name='COMMENTAIRE'
) then
   drop index COMMENTAIRE.ASSOCIATION_22_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION_21_FK'
     and t.table_name='COMMENTAIRE'
) then
   drop index COMMENTAIRE.ASSOCIATION_21_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='GENERER_FK'
     and t.table_name='COMMENTAIRE'
) then
   drop index COMMENTAIRE.GENERER_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='COMMENTAIRE_PK'
     and t.table_name='COMMENTAIRE'
) then
   drop index COMMENTAIRE.COMMENTAIRE_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='COMMENTAIRE'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table COMMENTAIRE
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='DROITS_D_ACCES_PK'
     and t.table_name='DROITS_D_ACCES'
) then
   drop index DROITS_D_ACCES.DROITS_D_ACCES_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='DROITS_D_ACCES'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table DROITS_D_ACCES
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ECRIRE2_FK'
     and t.table_name='ECRIRE'
) then
   drop index ECRIRE.ECRIRE2_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ECRIRE_FK'
     and t.table_name='ECRIRE'
) then
   drop index ECRIRE.ECRIRE_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ECRIRE_PK'
     and t.table_name='ECRIRE'
) then
   drop index ECRIRE.ECRIRE_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='ECRIRE'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table ECRIRE
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='EDITER2_FK'
     and t.table_name='EDITER'
) then
   drop index EDITER.EDITER2_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='EDITER_FK'
     and t.table_name='EDITER'
) then
   drop index EDITER.EDITER_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='EDITER_PK'
     and t.table_name='EDITER'
) then
   drop index EDITER.EDITER_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='EDITER'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table EDITER
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='EDITEUR_PK'
     and t.table_name='EDITEUR'
) then
   drop index EDITEUR.EDITEUR_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='EDITEUR'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table EDITEUR
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION_24_FK'
     and t.table_name='EMPLOYE'
) then
   drop index EMPLOYE.ASSOCIATION_24_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='EMPLOYE_PK'
     and t.table_name='EMPLOYE'
) then
   drop index EMPLOYE.EMPLOYE_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='EMPLOYE'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table EMPLOYE
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='EVENEMENT_PK'
     and t.table_name='EVENEMENT'
) then
   drop index EVENEMENT.EVENEMENT_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='EVENEMENT'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table EVENEMENT
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='FACTURER2_FK'
     and t.table_name='FACTURER'
) then
   drop index FACTURER.FACTURER2_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='FACTURER_FK'
     and t.table_name='FACTURER'
) then
   drop index FACTURER.FACTURER_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='FACTURER_PK'
     and t.table_name='FACTURER'
) then
   drop index FACTURER.FACTURER_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='FACTURER'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table FACTURER
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='FAIRE_LIVRER2_FK'
     and t.table_name='FAIRE_LIVRER'
) then
   drop index FAIRE_LIVRER.FAIRE_LIVRER2_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='FAIRE_LIVRER_FK'
     and t.table_name='FAIRE_LIVRER'
) then
   drop index FAIRE_LIVRER.FAIRE_LIVRER_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='FAIRE_LIVRER_PK'
     and t.table_name='FAIRE_LIVRER'
) then
   drop index FAIRE_LIVRER.FAIRE_LIVRER_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='FAIRE_LIVRER'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table FAIRE_LIVRER
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='GENRE_PK'
     and t.table_name='GENRE'
) then
   drop index GENRE.GENRE_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='GENRE'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table GENRE
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION_26_FK'
     and t.table_name='LIGNE_COMMANDE'
) then
   drop index LIGNE_COMMANDE.ASSOCIATION_26_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='GENERER2_FK'
     and t.table_name='LIGNE_COMMANDE'
) then
   drop index LIGNE_COMMANDE.GENERER2_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='GENERER1_FK'
     and t.table_name='LIGNE_COMMANDE'
) then
   drop index LIGNE_COMMANDE.GENERER1_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='AJOUTER_FK'
     and t.table_name='LIGNE_COMMANDE'
) then
   drop index LIGNE_COMMANDE.AJOUTER_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='LIGNE_COMMANDE_PK'
     and t.table_name='LIGNE_COMMANDE'
) then
   drop index LIGNE_COMMANDE.LIGNE_COMMANDE_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='LIGNE_COMMANDE'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table LIGNE_COMMANDE
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='APPLIQUER1_FK'
     and t.table_name='LIVRE'
) then
   drop index LIVRE.APPLIQUER1_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='LIVRE_PK'
     and t.table_name='LIVRE'
) then
   drop index LIVRE.LIVRE_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='LIVRE'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table LIVRE
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='MOT_CLE_PK'
     and t.table_name='MOT_CLE'
) then
   drop index MOT_CLE.MOT_CLE_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='MOT_CLE'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table MOT_CLE
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='SOCIETE_PK'
     and t.table_name='SOCIETE'
) then
   drop index SOCIETE.SOCIETE_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='SOCIETE'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table SOCIETE
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='STATUT_PK'
     and t.table_name='STATUT'
) then
   drop index STATUT.STATUT_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='STATUT'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table STATUT
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='THEME_PK'
     and t.table_name='THEME'
) then
   drop index THEME.THEME_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='THEME'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table THEME
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='TVA_PK'
     and t.table_name='TVA'
) then
   drop index TVA.TVA_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='TVA'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table TVA
end if;

/*==============================================================*/
/* Table : ADRESSE                                              */
/*==============================================================*/
create table ADRESSE 
(
   ADRESSEID            long varchar                   not null,
   ADRESSECODE          long varchar                   null,
   ADRESSENOM           long varchar                   null,
   ADRESSEPRENOM        long varchar                   null,
   ADRESSENORUE         long varchar                   null,
   ADRESSERUE           long varchar                   null,
   ADRESSECOMPL         long varchar                   null,
   ADRESSECP            long varchar                   null,
   ADRESSEVILLE         long varchar                   null,
   ADRESSEPAYS          long varchar                   null,
   ADRESSETEL           long varchar                   null,
   ADRESSESOCIETE       long varchar                   null,
   constraint PK_ADRESSE primary key (ADRESSEID)
);

/*==============================================================*/
/* Index : ADRESSE_PK                                           */
/*==============================================================*/
create unique index ADRESSE_PK on ADRESSE (
ADRESSEID ASC
);

/*==============================================================*/
/* Table : ASSOCIATION_25                                       */
/*==============================================================*/
create table ASSOCIATION_25 
(
   THEMEID              long varchar                   not null,
   GENREID              long varchar                   not null,
   constraint PK_ASSOCIATION_25 primary key clustered (THEMEID, GENREID)
);

/*==============================================================*/
/* Index : ASSOCIATION_25_PK                                    */
/*==============================================================*/
create unique clustered index ASSOCIATION_25_PK on ASSOCIATION_25 (
THEMEID ASC,
GENREID ASC
);

/*==============================================================*/
/* Index : ASSOCIATION_25_FK                                    */
/*==============================================================*/
create index ASSOCIATION_25_FK on ASSOCIATION_25 (
THEMEID ASC
);

/*==============================================================*/
/* Index : ASSOCIATION_29_FK                                    */
/*==============================================================*/
create index ASSOCIATION_29_FK on ASSOCIATION_25 (
GENREID ASC
);

/*==============================================================*/
/* Table : ATTRIBUER                                            */
/*==============================================================*/
create table ATTRIBUER 
(
   LIVREISBN            long varchar                   not null,
   EVENEMENTID          varchar(30)                    not null,
   constraint PK_ATTRIBUER primary key clustered (LIVREISBN, EVENEMENTID)
);

/*==============================================================*/
/* Index : ATTRIBUER_PK                                         */
/*==============================================================*/
create unique clustered index ATTRIBUER_PK on ATTRIBUER (
LIVREISBN ASC,
EVENEMENTID ASC
);

/*==============================================================*/
/* Index : ATTRIBUER_FK                                         */
/*==============================================================*/
create index ATTRIBUER_FK on ATTRIBUER (
LIVREISBN ASC
);

/*==============================================================*/
/* Index : ATTRIBUER2_FK                                        */
/*==============================================================*/
create index ATTRIBUER2_FK on ATTRIBUER (
EVENEMENTID ASC
);

/*==============================================================*/
/* Table : ATTRIBUER1                                           */
/*==============================================================*/
create table ATTRIBUER1 
(
   MOTCLEID             long varchar                   not null,
   LIVREISBN            long varchar                   not null,
   constraint PK_ATTRIBUER1 primary key clustered (MOTCLEID, LIVREISBN)
);

/*==============================================================*/
/* Index : ATTRIBUER1_PK                                        */
/*==============================================================*/
create unique clustered index ATTRIBUER1_PK on ATTRIBUER1 (
MOTCLEID ASC,
LIVREISBN ASC
);

/*==============================================================*/
/* Index : ATTRIBUER1_FK                                        */
/*==============================================================*/
create index ATTRIBUER1_FK on ATTRIBUER1 (
MOTCLEID ASC
);

/*==============================================================*/
/* Index : ATTRIBUER3_FK                                        */
/*==============================================================*/
create index ATTRIBUER3_FK on ATTRIBUER1 (
LIVREISBN ASC
);

/*==============================================================*/
/* Table : AUTEUR                                               */
/*==============================================================*/
create table AUTEUR 
(
   AUTEURID             long varchar                   not null,
   AUTEURNOM            long varchar                   null,
   AUTEURPRENOM         long varchar                   null,
   AUTEURPSEUDO         long varchar                   null,
   constraint PK_AUTEUR primary key (AUTEURID)
);

/*==============================================================*/
/* Index : AUTEUR_PK                                            */
/*==============================================================*/
create unique index AUTEUR_PK on AUTEUR (
AUTEURID ASC
);

/*==============================================================*/
/* Table : AVOIR                                                */
/*==============================================================*/
create table AVOIR 
(
   THEMEID              long varchar                   not null,
   LIVREISBN            long varchar                   not null,
   constraint PK_AVOIR primary key clustered (THEMEID, LIVREISBN)
);

/*==============================================================*/
/* Index : AVOIR_PK                                             */
/*==============================================================*/
create unique clustered index AVOIR_PK on AVOIR (
THEMEID ASC,
LIVREISBN ASC
);

/*==============================================================*/
/* Index : AVOIR_FK                                             */
/*==============================================================*/
create index AVOIR_FK on AVOIR (
THEMEID ASC
);

/*==============================================================*/
/* Index : AVOIR2_FK                                            */
/*==============================================================*/
create index AVOIR2_FK on AVOIR (
LIVREISBN ASC
);

/*==============================================================*/
/* Table : CLIENT                                               */
/*==============================================================*/
create table CLIENT 
(
   CLIENTLOGIN          long varchar                   not null,
   CLIENTMDP            long varchar                   null,
   CLIENTEMAIL          long varchar                   null,
   CLIENTNOM            long varchar                   null,
   CLIENTPRENOM         long varchar                   null,
   CLIENTTEL            long varchar                   null,
   CLIENTSTATUT         long varchar                   null,
   CLIENTCOMMENT        long varchar                   null,
   constraint PK_CLIENT primary key (CLIENTLOGIN)
);

/*==============================================================*/
/* Index : CLIENT_PK                                            */
/*==============================================================*/
create unique index CLIENT_PK on CLIENT (
CLIENTLOGIN ASC
);

/*==============================================================*/
/* Table : COMMANDE                                             */
/*==============================================================*/
create table COMMANDE 
(
   COMMANDENUM          long varchar                   not null,
   STATUTID             long varchar                   not null,
   CLIENTLOGIN          long varchar                   not null,
   ADRESSEID            long varchar                   not null,
   TVAID                long varchar                   not null,
   ADR_ADRESSEID        long varchar                   not null,
   COMMANDEPAIEMENT     long varchar                   null,
   COMMANDEFORFAITLIVRAISON long varchar                   null,
   COMMANDEDATE         date                           null,
   COMMANDENUMFACTURE   long varchar                   null,
   COMMANDEIP           long varchar                   null,
   COMMANDECOMMENT      long varchar                   null,
   DATESTATUT           date                           null,
   constraint PK_COMMANDE primary key (COMMANDENUM)
);

/*==============================================================*/
/* Index : COMMANDE_PK                                          */
/*==============================================================*/
create unique index COMMANDE_PK on COMMANDE (
COMMANDENUM ASC
);

/*==============================================================*/
/* Index : FACTURER_A_FK                                        */
/*==============================================================*/
create index FACTURER_A_FK on COMMANDE (
ADR_ADRESSEID ASC
);

/*==============================================================*/
/* Index : LIVRER_A_FK                                          */
/*==============================================================*/
create index LIVRER_A_FK on COMMANDE (
ADRESSEID ASC
);

/*==============================================================*/
/* Index : PASSER_FK                                            */
/*==============================================================*/
create index PASSER_FK on COMMANDE (
CLIENTLOGIN ASC
);

/*==============================================================*/
/* Index : APPLIQUER2_FK                                        */
/*==============================================================*/
create index APPLIQUER2_FK on COMMANDE (
TVAID ASC
);

/*==============================================================*/
/* Index : ASSOCIATION_27_FK                                    */
/*==============================================================*/
create index ASSOCIATION_27_FK on COMMANDE (
STATUTID ASC
);

/*==============================================================*/
/* Table : COMMENTAIRE                                          */
/*==============================================================*/
create table COMMENTAIRE 
(
   CLIENTLOGIN          long varchar                   not null,
   LIVREISBN            long varchar                   not null,
   COMMENTAIREID        long varchar                   not null,
   COMMANDENUM          long varchar                   null,
   LIGNECOMMANDEID      varchar(50)                    null,
   EMPLOYEID            long varchar                   null,
   COMMENTAIRETEXTE     long varchar                   null,
   COMMENTAIRENOTE      long varchar                   null,
   COMMENTAIREIP        long varchar                   null,
   COMMENTAIRESTATUT    long varchar                   null,
   COMMENTAIRE_DATE     date                           null,
   DATEMODERATION       date                           null,
   constraint PK_COMMENTAIRE primary key (CLIENTLOGIN, LIVREISBN, COMMENTAIREID)
);

/*==============================================================*/
/* Index : COMMENTAIRE_PK                                       */
/*==============================================================*/
create unique index COMMENTAIRE_PK on COMMENTAIRE (
CLIENTLOGIN ASC,
LIVREISBN ASC,
COMMENTAIREID ASC
);

/*==============================================================*/
/* Index : GENERER_FK                                           */
/*==============================================================*/
create index GENERER_FK on COMMENTAIRE (
LIG_LIVREISBN ASC,
COMMANDENUM ASC,
LIG_CLIENTLOGIN ASC,
COM_LIVREISBN ASC,
LIG_COMMENTAIREID ASC,
LIGNECOMMANDEID ASC
);

/*==============================================================*/
/* Index : ASSOCIATION_21_FK                                    */
/*==============================================================*/
create index ASSOCIATION_21_FK on COMMENTAIRE (
CLIENTLOGIN ASC
);

/*==============================================================*/
/* Index : ASSOCIATION_22_FK                                    */
/*==============================================================*/
create index ASSOCIATION_22_FK on COMMENTAIRE (
LIVREISBN ASC
);

/*==============================================================*/
/* Index : ASSOCIATION_28_FK                                    */
/*==============================================================*/
create index ASSOCIATION_28_FK on COMMENTAIRE (
EMPLOYEID ASC
);

/*==============================================================*/
/* Table : DROITS_D_ACCES                                       */
/*==============================================================*/
create table DROITS_D_ACCES 
(
   DROITDACCESID        long varchar                   not null,
   DROITDACCESLIBELLE   long varchar                   null,
   DROITDACCESTYPE      long varchar                   null,
   constraint PK_DROITS_D_ACCES primary key (DROITDACCESID)
);

/*==============================================================*/
/* Index : DROITS_D_ACCES_PK                                    */
/*==============================================================*/
create unique index DROITS_D_ACCES_PK on DROITS_D_ACCES (
DROITDACCESID ASC
);

/*==============================================================*/
/* Table : ECRIRE                                               */
/*==============================================================*/
create table ECRIRE 
(
   AUTEURID             long varchar                   not null,
   LIVREISBN            long varchar                   not null,
   constraint PK_ECRIRE primary key clustered (AUTEURID, LIVREISBN)
);

/*==============================================================*/
/* Index : ECRIRE_PK                                            */
/*==============================================================*/
create unique clustered index ECRIRE_PK on ECRIRE (
AUTEURID ASC,
LIVREISBN ASC
);

/*==============================================================*/
/* Index : ECRIRE_FK                                            */
/*==============================================================*/
create index ECRIRE_FK on ECRIRE (
AUTEURID ASC
);

/*==============================================================*/
/* Index : ECRIRE2_FK                                           */
/*==============================================================*/
create index ECRIRE2_FK on ECRIRE (
LIVREISBN ASC
);

/*==============================================================*/
/* Table : EDITER                                               */
/*==============================================================*/
create table EDITER 
(
   LIVREISBN            long varchar                   not null,
   EDITEURID            long varchar                   not null,
   constraint PK_EDITER primary key clustered (LIVREISBN, EDITEURID)
);

/*==============================================================*/
/* Index : EDITER_PK                                            */
/*==============================================================*/
create unique clustered index EDITER_PK on EDITER (
LIVREISBN ASC,
EDITEURID ASC
);

/*==============================================================*/
/* Index : EDITER_FK                                            */
/*==============================================================*/
create index EDITER_FK on EDITER (
LIVREISBN ASC
);

/*==============================================================*/
/* Index : EDITER2_FK                                           */
/*==============================================================*/
create index EDITER2_FK on EDITER (
EDITEURID ASC
);

/*==============================================================*/
/* Table : EDITEUR                                              */
/*==============================================================*/
create table EDITEUR 
(
   EDITEURID            long varchar                   not null,
   EDITEURNOM           long varchar                   null,
   EDITEURADRESSE       long varchar                   null,
   EDITEURTEL           long varchar                   null,
   EDITEURMAIL          long varchar                   null,
   EDITEURCONTACT       long varchar                   null,
   EDITEURCOMMENT       long varchar                   null,
   constraint PK_EDITEUR primary key (EDITEURID)
);

/*==============================================================*/
/* Index : EDITEUR_PK                                           */
/*==============================================================*/
create unique index EDITEUR_PK on EDITEUR (
EDITEURID ASC
);

/*==============================================================*/
/* Table : EMPLOYE                                              */
/*==============================================================*/
create table EMPLOYE 
(
   EMPLOYEID            long varchar                   not null,
   DROITDACCESID        long varchar                   not null,
   EMPLOYENOM           long varchar                   null,
   EMPLOYEPRENOM        long varchar                   null,
   EMPLOYEPOSTE         long varchar                   null,
   EMPLOYELOG           long varchar                   null,
   EMPLOYEMDP           long varchar                   null,
   constraint PK_EMPLOYE primary key (EMPLOYEID)
);

/*==============================================================*/
/* Index : EMPLOYE_PK                                           */
/*==============================================================*/
create unique index EMPLOYE_PK on EMPLOYE (
EMPLOYEID ASC
);

/*==============================================================*/
/* Index : ASSOCIATION_24_FK                                    */
/*==============================================================*/
create index ASSOCIATION_24_FK on EMPLOYE (
DROITDACCESID ASC
);

/*==============================================================*/
/* Table : EVENEMENT                                            */
/*==============================================================*/
create table EVENEMENT 
(
   EVENEMENTID          varchar(30)                    not null,
   EVENEMENTNOM         long varchar                   null,
   EVENEMENTDATEDEBUT   date                           null,
   EVENEMENTDATEFIN     date                           null,
   EVENEMENTPOURCENTAGE numeric(8,2)                   null,
   EVENMENTCODEPROMO    varchar                        null,
   EVENEMENTIMAGE       varchar                        null,
   EVENEMENTCOMMENT     long varchar                   null,
   constraint PK_EVENEMENT primary key (EVENEMENTID)
);

/*==============================================================*/
/* Index : EVENEMENT_PK                                         */
/*==============================================================*/
create unique index EVENEMENT_PK on EVENEMENT (
EVENEMENTID ASC
);

/*==============================================================*/
/* Table : FACTURER                                             */
/*==============================================================*/
create table FACTURER 
(
   CLIENTLOGIN          long varchar                   not null,
   ADRESSEID            long varchar                   not null,
   constraint PK_FACTURER primary key clustered (CLIENTLOGIN, ADRESSEID)
);

/*==============================================================*/
/* Index : FACTURER_PK                                          */
/*==============================================================*/
create unique clustered index FACTURER_PK on FACTURER (
CLIENTLOGIN ASC,
ADRESSEID ASC
);

/*==============================================================*/
/* Index : FACTURER_FK                                          */
/*==============================================================*/
create index FACTURER_FK on FACTURER (
CLIENTLOGIN ASC
);

/*==============================================================*/
/* Index : FACTURER2_FK                                         */
/*==============================================================*/
create index FACTURER2_FK on FACTURER (
ADRESSEID ASC
);

/*==============================================================*/
/* Table : FAIRE_LIVRER                                         */
/*==============================================================*/
create table FAIRE_LIVRER 
(
   CLIENTLOGIN          long varchar                   not null,
   ADRESSEID            long varchar                   not null,
   constraint PK_FAIRE_LIVRER primary key clustered (CLIENTLOGIN, ADRESSEID)
);

/*==============================================================*/
/* Index : FAIRE_LIVRER_PK                                      */
/*==============================================================*/
create unique clustered index FAIRE_LIVRER_PK on FAIRE_LIVRER (
CLIENTLOGIN ASC,
ADRESSEID ASC
);

/*==============================================================*/
/* Index : FAIRE_LIVRER_FK                                      */
/*==============================================================*/
create index FAIRE_LIVRER_FK on FAIRE_LIVRER (
CLIENTLOGIN ASC
);

/*==============================================================*/
/* Index : FAIRE_LIVRER2_FK                                     */
/*==============================================================*/
create index FAIRE_LIVRER2_FK on FAIRE_LIVRER (
ADRESSEID ASC
);

/*==============================================================*/
/* Table : GENRE                                                */
/*==============================================================*/
create table GENRE 
(
   GENREID              long varchar                   not null,
   GENRENOM             long varchar                   null,
   constraint PK_GENRE primary key (GENREID)
);

/*==============================================================*/
/* Index : GENRE_PK                                             */
/*==============================================================*/
create unique index GENRE_PK on GENRE (
GENREID ASC
);

/*==============================================================*/
/* Table : LIGNE_COMMANDE                                       */
/*==============================================================*/
create table LIGNE_COMMANDE 
(
   LIVREISBN            long varchar                   not null,
   COMMANDENUM          long varchar                   not null,
   CLIENTLOGIN          long varchar                   not null,
   COMMENTAIREID        long varchar                   not null,
   LIGNECOMMANDEID      varchar(50)                    not null,
   EVENEMENTID          varchar(30)                    null,
   LIGNECOMMANDEQUANTITE numeric(8,2)                   null,
   LIGNECOMMANDEPRIXHT  numeric(8,2)                   null,
   LIGNECOMMANDEREMISE  numeric(8,2)                   null,
   LIGNECOMMANDETVAAPPLIQUEE numeric(8,2)                   null,
   constraint PK_LIGNE_COMMANDE primary key (LIVREISBN, COMMANDENUM, CLIENTLOGIN, COM_LIVREISBN, COMMENTAIREID, LIGNECOMMANDEID)
);

/*==============================================================*/
/* Index : LIGNE_COMMANDE_PK                                    */
/*==============================================================*/
create unique index LIGNE_COMMANDE_PK on LIGNE_COMMANDE (
LIVREISBN ASC,
COMMANDENUM ASC,
CLIENTLOGIN ASC,
COM_LIVREISBN ASC,
COMMENTAIREID ASC,
LIGNECOMMANDEID ASC
);

/*==============================================================*/
/* Index : AJOUTER_FK                                           */
/*==============================================================*/
create index AJOUTER_FK on LIGNE_COMMANDE (
LIVREISBN ASC
);

/*==============================================================*/
/* Index : GENERER1_FK                                          */
/*==============================================================*/
create index GENERER1_FK on LIGNE_COMMANDE (
COMMANDENUM ASC
);

/*==============================================================*/
/* Index : GENERER2_FK                                          */
/*==============================================================*/
create index GENERER2_FK on LIGNE_COMMANDE (
CLIENTLOGIN ASC,
COM_LIVREISBN ASC,
COMMENTAIREID ASC
);

/*==============================================================*/
/* Index : ASSOCIATION_26_FK                                    */
/*==============================================================*/
create index ASSOCIATION_26_FK on LIGNE_COMMANDE (
EVENEMENTID ASC
);

/*==============================================================*/
/* Table : LIVRE                                                */
/*==============================================================*/
create table LIVRE 
(
   LIVREISBN            long varchar                   not null,
   TVAID                long varchar                   not null,
   LIVRETITRE           long varchar                   not null,
   LIVRESOUSTITRE       long varchar                   null,
   LIVREPRIXHT          numeric(8,2)                   not null,
   LIVREIMAGE           long varchar                   null,
   LIVRERESUME          long varchar                   null,
   LIVRENBREPAGE        numeric(8,2)                   null,
   LIVRESTOCK           long varchar                   null,
   LIVREDATEEDITION     long varchar                   null,
   LIVRECOMMENT         long varchar                   null,
   LIVRESTATUT          long varchar                   null,
   constraint PK_LIVRE primary key (LIVREISBN)
);

/*==============================================================*/
/* Index : LIVRE_PK                                             */
/*==============================================================*/
create unique index LIVRE_PK on LIVRE (
LIVREISBN ASC
);

/*==============================================================*/
/* Index : APPLIQUER1_FK                                        */
/*==============================================================*/
create index APPLIQUER1_FK on LIVRE (
TVAID ASC
);

/*==============================================================*/
/* Table : MOT_CLE                                              */
/*==============================================================*/
create table MOT_CLE 
(
   MOTCLEID             long varchar                   not null,
   MOTCLELIBELLE        long varchar                   null,
   constraint PK_MOT_CLE primary key (MOTCLEID)
);

/*==============================================================*/
/* Index : MOT_CLE_PK                                           */
/*==============================================================*/
create unique index MOT_CLE_PK on MOT_CLE (
MOTCLEID ASC
);

/*==============================================================*/
/* Table : SOCIETE                                              */
/*==============================================================*/
create table SOCIETE 
(
   SOCIETESIRET         long varchar                   not null,
   SOCIETENOM           long varchar                   null,
   SOCIETESIEGESOCIAL   long varchar                   null,
   SOCIETELOGO          long varchar                   null,
   SOCIETETEL           long varchar                   null,
   SOCIETESITEWEB       long varchar                   null,
   SOCIETERCS           long varchar                   null,
   SOCIETECAPITAL       long varchar                   null,
   SOCIETERAISONSOCIALE long varchar                   null,
   constraint PK_SOCIETE primary key (SOCIETESIRET)
);

/*==============================================================*/
/* Index : SOCIETE_PK                                           */
/*==============================================================*/
create unique index SOCIETE_PK on SOCIETE (
SOCIETESIRET ASC
);

/*==============================================================*/
/* Table : STATUT                                               */
/*==============================================================*/
create table STATUT 
(
   STATUTID             long varchar                   not null,
   STATUTLIBELLE        long varchar                   null,
   constraint PK_STATUT primary key (STATUTID)
);

/*==============================================================*/
/* Index : STATUT_PK                                            */
/*==============================================================*/
create unique index STATUT_PK on STATUT (
STATUTID ASC
);

/*==============================================================*/
/* Table : THEME                                                */
/*==============================================================*/
create table THEME 
(
   THEMEID              long varchar                   not null,
   THEMENOM             long varchar                   null,
   constraint PK_THEME primary key (THEMEID)
);

/*==============================================================*/
/* Index : THEME_PK                                             */
/*==============================================================*/
create unique index THEME_PK on THEME (
THEMEID ASC
);

/*==============================================================*/
/* Table : TVA                                                  */
/*==============================================================*/
create table TVA 
(
   TVAID                long varchar                   not null,
   TVATAUX              numeric(8,2)                   null,
   constraint PK_TVA primary key (TVAID)
);

/*==============================================================*/
/* Index : TVA_PK                                               */
/*==============================================================*/
create unique index TVA_PK on TVA (
TVAID ASC
);

alter table ASSOCIATION_25
   add constraint FK_ASSOCIAT_ASSOCIATI_THEME foreign key (THEMEID)
      references THEME (THEMEID)
      on update restrict
      on delete restrict;

alter table ASSOCIATION_25
   add constraint FK_ASSOCIAT_ASSOCIATI_GENRE foreign key (GENREID)
      references GENRE (GENREID)
      on update restrict
      on delete restrict;

alter table ATTRIBUER
   add constraint FK_ATTRIBUE_ATTRIBUER_LIVRE foreign key (LIVREISBN)
      references LIVRE (LIVREISBN)
      on update restrict
      on delete restrict;

alter table ATTRIBUER
   add constraint FK_ATTRIBUE_ATTRIBUER_EVENEMEN foreign key (EVENEMENTID)
      references EVENEMENT (EVENEMENTID)
      on update restrict
      on delete restrict;

alter table ATTRIBUER1
   add constraint FK_ATTRIBUE_ATTRIBUER_MOT_CLE foreign key (MOTCLEID)
      references MOT_CLE (MOTCLEID)
      on update restrict
      on delete restrict;

alter table ATTRIBUER1
   add constraint FK_ATTRIBUE_ATTRIBUER_LIVRE foreign key (LIVREISBN)
      references LIVRE (LIVREISBN)
      on update restrict
      on delete restrict;

alter table AVOIR
   add constraint FK_AVOIR_AVOIR_THEME foreign key (THEMEID)
      references THEME (THEMEID)
      on update restrict
      on delete restrict;

alter table AVOIR
   add constraint FK_AVOIR_AVOIR2_LIVRE foreign key (LIVREISBN)
      references LIVRE (LIVREISBN)
      on update restrict
      on delete restrict;

alter table COMMANDE
   add constraint FK_COMMANDE_APPLIQUER_TVA foreign key (TVAID)
      references TVA (TVAID)
      on update restrict
      on delete restrict;

alter table COMMANDE
   add constraint FK_COMMANDE_ASSOCIATI_STATUT foreign key (STATUTID)
      references STATUT (STATUTID)
      on update restrict
      on delete restrict;

alter table COMMANDE
   add constraint FK_COMMANDE_FACTURER__ADRESSE foreign key (ADR_ADRESSEID)
      references ADRESSE (ADRESSEID)
      on update restrict
      on delete restrict;

alter table COMMANDE
   add constraint FK_COMMANDE_LIVRER_A_ADRESSE foreign key (ADRESSEID)
      references ADRESSE (ADRESSEID)
      on update restrict
      on delete restrict;

alter table COMMANDE
   add constraint FK_COMMANDE_PASSER_CLIENT foreign key (CLIENTLOGIN)
      references CLIENT (CLIENTLOGIN)
      on update restrict
      on delete restrict;

alter table COMMENTAIRE
   add constraint FK_COMMENTA_ASSOCIATI_CLIENT foreign key (CLIENTLOGIN)
      references CLIENT (CLIENTLOGIN)
      on update restrict
      on delete restrict;

alter table COMMENTAIRE
   add constraint FK_COMMENTA_ASSOCIATI_LIVRE foreign key (LIVREISBN)
      references LIVRE (LIVREISBN)
      on update restrict
      on delete restrict;

alter table COMMENTAIRE
   add constraint FK_COMMENTA_ASSOCIATI_EMPLOYE foreign key (EMPLOYEID)
      references EMPLOYE (EMPLOYEID)
      on update restrict
      on delete restrict;

alter table COMMENTAIRE
   add constraint FK_COMMENTA_GENERER_LIGNE_CO foreign key (LIG_LIVREISBN, COMMANDENUM, LIG_CLIENTLOGIN, COM_LIVREISBN, LIG_COMMENTAIREID, LIGNECOMMANDEID)
      references LIGNE_COMMANDE (LIVREISBN, COMMANDENUM, CLIENTLOGIN, COM_LIVREISBN, COMMENTAIREID, LIGNECOMMANDEID)
      on update restrict
      on delete restrict;

alter table ECRIRE
   add constraint FK_ECRIRE_ECRIRE_AUTEUR foreign key (AUTEURID)
      references AUTEUR (AUTEURID)
      on update restrict
      on delete restrict;

alter table ECRIRE
   add constraint FK_ECRIRE_ECRIRE2_LIVRE foreign key (LIVREISBN)
      references LIVRE (LIVREISBN)
      on update restrict
      on delete restrict;

alter table EDITER
   add constraint FK_EDITER_EDITER_LIVRE foreign key (LIVREISBN)
      references LIVRE (LIVREISBN)
      on update restrict
      on delete restrict;

alter table EDITER
   add constraint FK_EDITER_EDITER2_EDITEUR foreign key (EDITEURID)
      references EDITEUR (EDITEURID)
      on update restrict
      on delete restrict;

alter table EMPLOYE
   add constraint FK_EMPLOYE_ASSOCIATI_DROITS_D foreign key (DROITDACCESID)
      references DROITS_D_ACCES (DROITDACCESID)
      on update restrict
      on delete restrict;

alter table FACTURER
   add constraint FK_FACTURER_FACTURER_CLIENT foreign key (CLIENTLOGIN)
      references CLIENT (CLIENTLOGIN)
      on update restrict
      on delete restrict;

alter table FACTURER
   add constraint FK_FACTURER_FACTURER2_ADRESSE foreign key (ADRESSEID)
      references ADRESSE (ADRESSEID)
      on update restrict
      on delete restrict;

alter table FAIRE_LIVRER
   add constraint FK_FAIRE_LI_FAIRE_LIV_CLIENT foreign key (CLIENTLOGIN)
      references CLIENT (CLIENTLOGIN)
      on update restrict
      on delete restrict;

alter table FAIRE_LIVRER
   add constraint FK_FAIRE_LI_FAIRE_LIV_ADRESSE foreign key (ADRESSEID)
      references ADRESSE (ADRESSEID)
      on update restrict
      on delete restrict;

alter table LIGNE_COMMANDE
   add constraint FK_LIGNE_CO_AJOUTER_LIVRE foreign key (LIVREISBN)
      references LIVRE (LIVREISBN)
      on update restrict
      on delete restrict;

alter table LIGNE_COMMANDE
   add constraint FK_LIGNE_CO_ASSOCIATI_EVENEMEN foreign key (EVENEMENTID)
      references EVENEMENT (EVENEMENTID)
      on update restrict
      on delete restrict;

alter table LIGNE_COMMANDE
   add constraint FK_LIGNE_CO_GENERER1_COMMANDE foreign key (COMMANDENUM)
      references COMMANDE (COMMANDENUM)
      on update restrict
      on delete restrict;

alter table LIGNE_COMMANDE
   add constraint FK_LIGNE_CO_GENERER2_COMMENTA foreign key (CLIENTLOGIN, COM_LIVREISBN, COMMENTAIREID)
      references COMMENTAIRE (CLIENTLOGIN, LIVREISBN, COMMENTAIREID)
      on update restrict
      on delete restrict;

alter table LIVRE
   add constraint FK_LIVRE_APPLIQUER_TVA foreign key (TVAID)
      references TVA (TVAID)
      on update restrict
      on delete restrict;

