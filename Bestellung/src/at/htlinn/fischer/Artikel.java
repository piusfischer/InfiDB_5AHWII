package at.htlinn.fischer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Artikel
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Setter
	@Getter
    private int id;
	@Setter
	@Getter
    private String name;
	@Setter
	@Getter
    private double preis;
	
	public static int anzahl;

    public Artikel(int id, String name, double preis)
    {
        this.id = id;
        this.name = name;
        this.preis = preis;
        anzahl+=1;
    }
    public Artikel() {
    	
    }

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public double getPreis()
    {
        return preis;
    }
    public void setPreis(double preis)
    {
        this.preis = preis;
    }
}
