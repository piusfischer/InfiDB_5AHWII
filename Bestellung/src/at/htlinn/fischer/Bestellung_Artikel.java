package at.htlinn.fischer;

public class Bestellung_Artikel {

    private int bestell_id;
    private int artikel_id;
    private int menge;

    public Bestellung_Artikel(int id1, int id2, int menge){
        this.bestell_id = id1;
        this.artikel_id = id2;
        this.menge = menge;

    }
    public int getbestellartikel_bestellid()
    {
        return bestell_id;
    }
    public void setbestellartikel_bestellid(int bestellartikel_bestellid)
    {
        this.bestell_id = bestellartikel_bestellid;
    }

    public int getbestellartikel_artikelid()
    {
        return artikel_id;
    }
    public void setbestellartikel_artikelid(int bestellartikel_artikelid)
    {
        this.artikel_id = bestellartikel_artikelid;
    }

    public int getMenge()
    {
        return bestell_id;
    }
    public void setMenge(int menge)
    {
        this.menge = menge;
    }
}
