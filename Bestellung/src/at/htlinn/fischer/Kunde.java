package at.htlinn.fischer;

public class Kunde
{
    private int id;
    private String titelv;
    private String vorname;
    private String nachname;
    private String titeln;



    public Kunde(int id, String titelv, String vorname, String nachname, String titeln)
    {
        super();
        this.id = id;
        this.titelv = titelv;
        this.vorname = vorname;
        this.nachname = nachname;
        this.titeln = titeln;
    }
    public int getKunde_id()
    {
        return id;
    }
    public void setKunde_id(int kunde_id)
    {
        this.id = kunde_id;
    }

    public String getTitelv()
    {
        return titelv;
    }
    public void setTitelv(String titelv)
    {
        this.titelv = titelv;
    }

    public String getVorname()
    {
        return vorname;
    }
    public void setVorname(String vorname)
    {
        this.vorname = vorname;
    }

    public String getNachname()
    {
        return nachname;
    }
    public void setNachname(String nachname)
    {
        this.nachname = nachname;
    }

    public String getTiteln()
    {
        return titeln;
    }
    public void setTiteln(String titeln)
    {
        this.titeln = titeln;
    }


}