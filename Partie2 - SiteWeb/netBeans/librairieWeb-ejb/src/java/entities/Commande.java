package entities;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Commande implements Serializable {

    /*
    ** Getter et Setter de l'entity
    */
    @Column(length = 10, nullable = false)
    @Id
    private String numCommande;
    @Column(nullable = false, unique = true)
    private String numFacture;
    @Column(nullable = false)
    private Float forfaitLivraison;
    @Column(nullable = false)
    private Date dateCommande;
    @Column(nullable = false)
    private String ip;
    private String blabla;
    @Column(length = 4, nullable = false, precision = 2)
    private Float tvaLivraison;
    @OneToOne
    @JoinColumn(name = "adr_facture_fk", nullable = false)
    private Adresse adresseFact;
    @OneToOne
    @JoinColumn(name = "adr_livraison_pk", nullable = false)
    private Adresse adresseLiv;
    @OneToOne
    @JoinColumn(name = "paiement_fk", nullable = false)
    private Paiement paiement;
    @OneToOne
    @JoinColumn(name = "client_fk", nullable = false)
    private Client client;
    @OneToMany
    private Collection<Tva> listTvaCommande;
    @OneToMany
    @JoinTable( name= "DETAIL_DE_COMMANDE",
                joinColumns = @JoinColumn(name = "idCommande_fk"),
                inverseJoinColumns = @JoinColumn( name = "idLigCommande_fk"))
    private Collection<LigneCommande> detailCommande;
    @OneToMany
    @JoinTable( name= "STATUT_DE_COMMANDE",
                joinColumns = @JoinColumn(name = "listStatutCommande_fk"),
                inverseJoinColumns = @JoinColumn( name = "commande_fk"))
    private Collection<StatutCommande> listStatutCommande;

    /*
    ** Getter et Setter de l'entity
    */
    public void setNumCommande(String numCommande) {
        this.numCommande = numCommande;
    }

    public String getNumCommande() {
        return this.numCommande;
    }

    public void setNumFacture(String numFacture) {
        this.numFacture = numFacture;
    }

    public String getNumFacture() {
        return this.numFacture;
    }

    public void setForfaitLivraison(Float forfaitLivraison) {
        this.forfaitLivraison = forfaitLivraison;
    }

    public Float getForfaitLivraison() {
        return this.forfaitLivraison;
    }
    
    public String getForfaitLivAff() {
        return String.format("%.2f", this.forfaitLivraison);
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Collection<Tva> getListTvaCommande() {
        return listTvaCommande;
    }

    public void setListTvaCommande(Collection<Tva> listTvaCommande) {
        this.listTvaCommande = listTvaCommande;
    }

    public Collection<StatutCommande> getListStatutCommande() {
        return listStatutCommande;
    }

    public void setListStatutCommande(Collection<StatutCommande> listStatutCommande) {
        this.listStatutCommande = listStatutCommande;
    }

    
    public void setDate(Date date) {
        this.dateCommande = date;
    }

    public Date getDate() {
        return this.dateCommande;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return this.ip;
    }

    public void setBlabla(String blabla) {
        this.blabla = blabla;
    }

    public String getBlabla() {
        return this.blabla;
    }

    public void setTvaLivraison(Float tvaLivraison) {
        this.tvaLivraison = tvaLivraison;
    }

    public Float getTvaLivraison() {
        return this.tvaLivraison;
    }

    public void setAdresseFact(Adresse adresseFact) {
        this.adresseFact = adresseFact;
    }

    public Adresse getAdresseFact() {
        return this.adresseFact;
    }

    public void setAdresseLiv(Adresse adresseLiv) {
        this.adresseLiv = adresseLiv;
    }

    public Adresse getAdresseLiv() {
        return this.adresseLiv;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }

    public Paiement getPaiement() {
        return this.paiement;
    }

    public void setlistTvaCommande(Collection<Tva> listTvaCommande) {
        this.listTvaCommande = listTvaCommande;
    }

    public Collection<Tva> getlistTvaCommande() {
        return this.listTvaCommande;
    }

    public void setDetailCommande(Collection<LigneCommande> detailCommande) {
        this.detailCommande = detailCommande;
    }
    
    public Collection<LigneCommande> getDetailCommande() {
        return this.detailCommande;
    }

    public void setlistStatutCommande(Collection<StatutCommande> listStatutCommande) {
        this.listStatutCommande = listStatutCommande;
    }

    public Collection<StatutCommande> getlistStatutCommande() {
        return this.listStatutCommande;
    }
    
    /*
    ** Méthodes non natives de l'entity
    */
    public String recupAdresseIp() {
        String adrIp = "";
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
            adrIp = ip.getHostAddress();
        } catch (UnknownHostException ex) {
            System.out.println( "oops: UnknownHostException: " + ex.getMessage());
        }
        return adrIp;
    }

    public String getDateCde() {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
       return df.format(this.dateCommande);
    }
    
    public float getMtnPaye(){
        float mtnPaye = 0;
        Collection<LigneCommande> contenuCde = getDetailCommande();
        for (LigneCommande lc : contenuCde) {
            mtnPaye += lc.getMntTtc() * lc.getQuantite() * (1- lc.remisePourcentage());
        }
        mtnPaye += getForfaitLivraison();
        return mtnPaye;
    }
    
    public String getMtnPayeAffichage(){
        float mtnPaye = getMtnPaye();
        return String.format("%.2f", mtnPaye);
    }
    
    public float getMntTotalCdeHt(){
       float mntTotalCdeHt = 0;
        Collection<LigneCommande> listLigCdes = getDetailCommande();
        for (LigneCommande lc : listLigCdes) {
            mntTotalCdeHt += lc.getPrixHT() * (1 - lc.getRemise()/100) * lc.getQuantite();
        }
        return mntTotalCdeHt;
    }
    
    public String getMntTotalCdeHtAff(){
        float mntTotalCdeHt = getMntTotalCdeHt();
        return String.format("%.2f", mntTotalCdeHt);
    }
    
    public float getMntTotalCdeTva(){
       float mntTotalCdeTva = 0;
        Collection<LigneCommande> listLigCdes = getDetailCommande();
        for (LigneCommande lc : listLigCdes) {
            mntTotalCdeTva += lc.getPrixHT() * (1 - lc.getRemise()/100) * lc.getQuantite() * lc.getTvaAppliqueePourcentage();
        }
        return mntTotalCdeTva;
    }
    
    public String getMntTotalCdeTvaAff(){
        float mntTotalCdeTva = getMntTotalCdeTva();
        return String.format("%.2f", mntTotalCdeTva);
    }

    public float getMntTotalCdeTtc(){
       float mntTotalCdeTtc = 0;
        Collection<LigneCommande> listLigCdes = getDetailCommande();
        for (LigneCommande lc : listLigCdes) {
            mntTotalCdeTtc +=  lc.getPrixHT() * (1 - lc.getRemise()/100) * lc.getQuantite() * (1 + lc.getTvaAppliqueePourcentage());
        }
        return mntTotalCdeTtc;
    }
    
    public String getMntTotalCdeTtcAff(){
        float mntTotalCdeTtc = getMntTotalCdeTtc();
        return String.format("%.2f", mntTotalCdeTtc);
    }
    
    public float getMntTotalCdePaye(){
        return getMntTotalCdeTtc() + getForfaitLivraison();
    }
    
     public String getMntTotalCdePayeAff(){
        float mntTotalCdePaye = getMntTotalCdePaye();
        return String.format("%.2f", mntTotalCdePaye);
    }
}
