package at.htlinn.fischer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Adresse
{	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Setter
	@Getter
    private int id;
	@Setter
	@Getter
    private String stadt;
	@Setter
	@Getter
    private String strasse;
	@Setter
	@Getter
    private int plz;
	@Setter
	@Getter
    private int hnr;
	
	public static int anzahl;

    public Adresse(int id, String stadt, String strasse, int plz, int hnr)
    {
        this.id = id;
        this.stadt = stadt;
        this.strasse = strasse;
        this.plz = plz;
        this.hnr = hnr;
        anzahl+=1;
    }
    public Adresse() {
    	
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