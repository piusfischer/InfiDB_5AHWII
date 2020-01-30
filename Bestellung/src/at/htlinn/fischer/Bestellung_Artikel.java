package at.htlinn.fischer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Bestellung_Artikel {
	
	@Setter
	@Getter
    private Bestellung bestell;
	@Setter
	@Getter
    private Artikel artikel;
	@Setter
	@Getter
    private int menge;
	
	public static int anzahl;

    public Bestellung_Artikel(Bestellung b, Artikel a, int menge){
        this.bestell = b;
        this.artikel = a;
        this.menge = menge;
        anzahl+=1;

    }
    public Bestellung getbestellartikel_bestell() {
		return bestell;
	}
	
	public void setbestellartikel_bestell(Bestellung b) {
		this.bestell=b;
	}
	
	public Artikel getbestellartikle_artikel() {
		return artikel;
	}
	public void setbestellartikel_artikel(Artikel a) {
		this.artikel=a;
	}
    
    
    public int getMenge()
    {
        return menge;
    }
    public void setMenge(int menge)
    {
        this.menge = menge;
    }
}
