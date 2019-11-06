package at.htlinn.fischer;


public class Adresse
{
    private int id;
    private String stadt;
    private String strasse;
    private int plz;
    private int hnr;

    public Adresse(int id, String stadt, String strasse, int plz, int hnr)
    {
        this.id = id;
        this.stadt = stadt;
        this.strasse = strasse;
        this.plz = plz;
        this.hnr = hnr;
    }

    public int getAdresse_id()
    {
        return id;
    }
    public void setAdresse_id(int adresse_id)
    {
        this.id = id;
    }

    public String getStadt()
    {
        return stadt;
    }
    public void setStadt(String stadt)
    {
        this.stadt = stadt;
    }

    public String getStrasse()
    {
        return strasse;
    }
    public void setStrasse(String strasse)
    {
        this.strasse = strasse;
    }

    public int getPlz()
    {
        return plz;
    }
    public void setPlz(int plz)
    {
        this.plz = plz;
    }

    public int getHnr()
    {
        return hnr;
    }
    public void setHnr(int hnr)
    {
        this.hnr = hnr;
    }
}