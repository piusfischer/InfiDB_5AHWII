package at.htlinn.fischer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Kunde
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Setter
	@Getter
    private int id;
	@Setter
	@Getter
    private String titelv;
	@Setter
	@Getter
    private String vorname;
	@Setter
	@Getter
    private String nachname;
	@Setter
	@Getter
    private String titeln;
	
	public static int anzahl;



    public Kunde(int id, String titelv, String vorname, String nachname, String titeln)
    {
        super();
        this.id = id;
        this.titelv = titelv;
        this.vorname = vorname;
        this.nachname = nachname;
        this.titeln = titeln;
        anzahl+=1;
    }
    public Kunde() {
    	
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