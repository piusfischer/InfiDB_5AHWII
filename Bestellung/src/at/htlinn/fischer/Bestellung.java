package at.htlinn.fischer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import tirol.htl_anichstrasse.jpa_test2.Hobby2;
@Entity
public class Bestellung {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Setter
	@Getter
    private int id;
	@Setter
	@Getter
    private Kunde kunde;
	@Setter
	@Getter
    private Adresse arechnung;
	@Setter
	@Getter
    private Adresse aliefer;
    
	public static int anzahl;

    public Bestellung(int id, Kunde kd, Adresse ar, Adresse al){
        this.id = id;
        this.kunde = kd;
        this.arechnung = ar;
        this.aliefer= al;
        anzahl+=1;
    }
    public Bestellung() {
    	
    }
    
    public Kunde getKunde() {
		return kunde;
	}
	
	public void setKunde(Kunde kunde) {
		this.kunde=kunde;
	}
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }

    public Adresse getARadresse() {
		return arechnung;
	}
	
	public void setARadresse(Adresse arechnung) {
		this.arechnung=arechnung;
	}
	
	public Adresse getALadresse() {
		return aliefer;
	}
	
	public void setALadresse(Adresse aliefer) {
		this.aliefer=aliefer;
	}

    
}
