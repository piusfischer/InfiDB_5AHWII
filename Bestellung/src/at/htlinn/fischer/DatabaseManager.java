package at.htlinn.fischer;
import java.sql.*;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import tirol.htl_anichstrasse.jpa_test2.Hobby2;



public class DatabaseManager {

    private String db_host = "localhost";
    private String db_port = "3306";
    private String db_user = "root";
    private String db_pass = "root";
    private String db_base = "BestellungDb";
    
    

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private Connection conn = null;


    public void createConnection() throws SQLException, ClassNotFoundException {

        Class.forName(DRIVER);
        String mySqlUrl = "jdbc:mysql://" + db_host + ":" + db_port + "/"
                + db_base;
        conn = DriverManager.getConnection(mySqlUrl, db_user, db_pass);
        
        
    }
    EntityManagerFactory f = Persistence.createEntityManagerFactory("MyUnit");
    EntityManager manager = f.createEntityManager();


    public void showTable(String nameOfTable) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM ?");
        ps.setString(1, nameOfTable);
        ResultSet rs = ps.executeQuery();

        int columns = rs.getMetaData().getColumnCount();
        System.out.println("Alle Einträge zu der Tabelle "+nameOfTable);
        System.out.println();

        for (int i = 1; i <= columns; i++)
            System.out.print(rs.getMetaData().getColumnLabel(i) + "\t\t");
        System.out.println();
        System.out.println();

        while (rs.next()) {
            for (int i = 1; i <= columns; i++) {
                System.out.print(rs.getString(i) + "\t\t");
            }
            System.out.println();
        }

        ps.close();

    }
    public int Kundenid=0;
    public void setKunde(String titelv, String vorname, String nachname, String titeln) throws SQLException{
        Kundenid=Kundenid+1;
        Kunde k = new Kunde(Kundenid, titelv, vorname, nachname, titeln);
        manager.persist(k);
        manager.getTransaction().commit();
        /*PreparedStatement ps = conn.prepareStatement("INSERT INTO Kunde(id,titelv, vorname, nachname, titeln) VALUES(?,?,?,?,?)");
        ps.setInt(1, Kundenid);
        ps.setString(2, titelv);
        ps.setString(3, vorname);
        ps.setString(4, nachname);
        ps.setString(5,titeln);
        ps.executeQuery();
        ps.close();*/
    }

    public void deleteKunde(int id) throws SQLException
    {
    	Kunde k = manager.find(Kunde.class, id);
    	
    	manager.getTransaction().begin();
    	manager.remove(k);
    	manager.persist(k);
    	manager.getTransaction().commit();
    	
        /*PreparedStatement ps = conn.prepareStatement("DELETE FROM Kunde WHERE id=?");
        ps.setInt(1,id);
        ps.executeUpdate();
        ps.close();
        */
    }
    public ArrayList<Kunde> getKunden()throws SQLException
    {
        ArrayList<Kunde> kunden = new ArrayList<>();
        Kunde k;

        for(int i=0;i<=Kunde.anzahl; i++)
        {
        	k= manager.find(Kunde.class, i);
        	Kunde ku = new Kunde(k.getKunde_id(), k.getTitelv(), k.getVorname(), k.getNachname(), k.getTiteln());
        	System.out.printf(" %2s , %20s , %20s , %20s ,\n",k.getKunde_id(), k.getTitelv(), k.getVorname(), k.getNachname(), k.getTiteln());
        	kunden.add(ku);
        	manager.getTransaction().begin();
            manager.persist(ku);
            manager.getTransaction().commit();
        }
        
        /*PreparedStatement ps = conn.prepareStatement("SELECT * FROM Kunde");
        ResultSet rs = ps.executeQuery();

        while(rs.next())
        {
            int id = rs.getInt("id");
            String titelv = rs.getString("titelv");
            String vorname = rs.getString("vorname");
            String nachname = rs.getString("nachname");
            String titeln = rs.getString("titeln");
            System.out.printf(" %2s , %20s , %20s , %20s ,\n",id,titelv,vorname,nachname,titeln);

            Kunde kunde = new Kunde(id, titelv, titeln, vorname, nachname);
            kunden.add(kunde);
        }

        rs.close();
        ps.close();
        */
        return kunden;
    }


    public int Adresseid= 0;
    public void setAdresse(String stadt, String strasse, int plz, int hnr) throws SQLException{
        Adresseid= Adresseid+1;
        Adresse a = new Adresse(Adresseid,stadt,strasse, plz, hnr);
        manager.getTransaction().begin();
        manager.persist(a);
        manager.getTransaction().commit();
        
        /*PreparedStatement ps = conn.prepareStatement("INSERT INTO Adresse(id, stadt, strasse, plz, hnr) VALUES(?,?,?,?,?)");
        ps.setInt(1, Adresseid);
        ps.setString(2, stadt);
        ps.setString(3, strasse);
        ps.setInt(4, plz);
        ps.setString(5, hnr);
        ps.executeQuery();
        ps.close();
        */
    }

    public void deleteAdresse(int id) throws SQLException
    {

    	Adresse a = manager.find(Adresse.class, id);
    	
    	manager.getTransaction().begin();
    	manager.remove(a);
    	manager.persist(a);
    	manager.getTransaction().commit();
    	
        /*PreparedStatement ps = conn.prepareStatement("DELETE FROM Adresse WHERE id=?");
        ps.setInt(1,id);
        ps.executeUpdate();
        ps.close();
        */
    }
    public ArrayList<Adresse> getAdressen()throws SQLException
    {
        ArrayList<Adresse> adressen = new ArrayList<>();

        
        Adresse a;

        for(int i=0;i<=Adresse.anzahl; i++)
        {
        	a= manager.find(Adresse.class, i);
        	Adresse ad = new Adresse(a.getAdresse_id(), a.getStadt(), a.getStrasse(), a.getPlz(), a.getHnr());
        	System.out.printf(" %2s , %20s , %20s , %20s ,\n",a.getAdresse_id(), a.getStadt(), a.getStrasse(), a.getPlz(), a.getHnr());
        	adressen.add(ad);
        	manager.getTransaction().begin();
            manager.persist(ad);
            manager.getTransaction().commit();
        }
        /*PreparedStatement ps = conn.prepareStatement("SELECT * FROM Adresse");
        ResultSet rs = ps.executeQuery();

        while(rs.next())
        {
            int id = rs.getInt("id");
            String stadt = rs.getString("stadt");
            String strasse = rs.getString("strasse");
            int plz = rs.getInt("plz");
            int hnr = rs.getInt("hnr");
            System.out.printf(" %2s , %20s , %20s , %20s ,\n",id,stadt,strasse,plz,hnr);

            Adresse adresse = new Adresse(id,stadt,strasse,plz,hnr);
            adressen.add(adresse);
        }

        rs.close();
        ps.close();
        */
        return adressen;
    }


    public int Artikelid=0;
    public void setArtikel(String name, double preis) throws SQLException{
        Artikelid=Artikelid+1;
       
        Artikel a = new Artikel(Artikelid, name, 10);
        manager.getTransaction().begin();
        manager.persist(a);
        manager.getTransaction().commit();
        
        /*PreparedStatement ps = conn.prepareStatement("INSERT INTO Artikel(id, name, preis) VALUES(?,?,?)");
        ps.setInt(1, Artikelid);
        ps.setString(2, name);
        ps.setDouble(3, preis);
        ps.executeQuery();
        ps.close();
        */
    }

    public void deleteArtikel(int id) throws SQLException
    {

    	Artikel a = manager.find(Artikel.class, id);
    	
    	manager.getTransaction().begin();
    	manager.remove(a);
    	manager.persist(a);
    	manager.getTransaction().commit();
    	
        /*PreparedStatement ps = conn.prepareStatement("DELETE FROM Artikel WHERE id=?");
        ps.setInt(1,id);
        ps.executeUpdate();
        ps.close();
        */
    }
    public ArrayList<Artikel> getArtikel()throws SQLException
    {
        ArrayList<Artikel> artikel = new ArrayList<>();
        
        Artikel a;

        for(int i=0;i<=Artikel.anzahl; i++)
        {
        	a= manager.find(Artikel.class, i);
        	Artikel ar = new Artikel(a.getId(), a.getName(), a.getPreis());
        	System.out.printf(" %2s , %20s , %20s ,\n",a.getId(), a.getName(), a.getPreis());
        	artikel.add(ar);
        	manager.getTransaction().begin();
            manager.persist(ar);
            manager.getTransaction().commit();
        }

        /*PreparedStatement ps = conn.prepareStatement("SELECT * FROM Artikel");
        ResultSet rs = ps.executeQuery();

        while(rs.next())
        {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int preis = rs.getInt("preis");
            System.out.printf(" %2s , %20s , %20s ,\n",id, name, preis);

            Artikel artikel1 = new Artikel(id,name,preis);
            artikel.add(artikel1);
        }

        rs.close();
        ps.close();
        */
        return artikel;
    }

    public int Bestellungid=0;
    public void setBestellung(int kid, int arid, int alid) throws SQLException {
        Bestellungid=Bestellungid+1;
        Bestellung b = new Bestellung(Bestellungid, mangaer.find(Kunde.class,kid), manager.find(Adresse.class,arid), manager.find(Adresse.class,alid));
        manager.getTransaction().begin();
        manager.persist(b);
        manager.getTransaction().commit();
        
        /*PreparedStatement ps = conn.prepareStatement("INSERT INTO bestellung(id, kunde_id, adresse_rechnung_id, adresse_liefer_id) VALUES(?,?,?,?)");
        ps.setInt(1, Bestellungid);
        ps.setInt(2, kid);
        ps.setInt(3, arid);
        ps.setInt(4, alid);
        ps.executeUpdate();
        ps.close();
        */

    }

    public ArrayList<Bestellung> getBestellung() throws SQLException
    {
        ArrayList<Bestellung> bestellungen = new ArrayList<>();
        
        Bestellung b;

        for(int i=0;i<=Bestellung.anzahl; i++)
        {
        	b= manager.find(Bestellung.class, i);
        	Bestellung be = new Bestellung(b.getId(), b.getKunde(), b.getARadresse(),b.getALadresse());
        	System.out.printf(" %20s , %20s , %20s , %20s ,\n",b.getId(), b.getKunde(), b.getARadresse(),b.getALadresse());
        	bestellungen.add(be);
        	manager.getTransaction().begin();
            manager.persist(be);
            manager.getTransaction().commit();
        }
        
        /*PreparedStatement ps = conn.prepareStatement("SELECT * FROM bestellung");
        ResultSet rs = ps.executeQuery();


        System.out.printf(", %20s , %20s , %20s , %20s ,\n","id", "kunde_id", "adresse_rechnung_id", "adresse_liefer_id");

        while(rs.next())
        {
            int id = rs.getInt("id");
            int kunde_id = rs.getInt("kunde_id");
            int adresse_rechnung_id = rs.getInt("adresse_rechnung_id");
            int adresse_liefer_id = rs.getInt("adresse_liefer_id");
            System.out.printf(" %20s , %20s , %20s , %20s ,\n",id, kunde_id, adresse_rechnung_id, adresse_liefer_id);
            Bestellung bestellung = new Bestellung(id,kunde_id,adresse_rechnung_id,adresse_liefer_id);
            bestellungen.add(bestellung);
        }

        rs.close();
        ps.close();
        
       */
        return bestellungen;
    }

    public void deleteBestellung(int id) throws SQLException
    {
    	Bestellung b = manager.find(Bestellung.class, id);
    	
    	manager.getTransaction().begin();
    	manager.remove(b);
    	manager.persist(b);
    	manager.getTransaction().commit();
        
    	/*PreparedStatement ps = conn.prepareStatement("DELETE FROM Bestellung WHERE id=?");
        ps.setInt(1,id);
        ps.executeUpdate();
        ps.close();
        */
    }

    public void addArtikeltoBestellung(int bestell_id, int artikel_id, int menge) throws SQLException
    {

    	Bestellung_Artikel ab = new Bestellung_Artikel(manager.find(Bestellung.class,bestell_id), manager.find(Artikel.class,artikel_id), menge);
        manager.getTransaction().begin();
        manager.persist(ab);
        manager.getTransaction().commit();
    	
        
        
        /*PreparedStatement ps = conn.prepareStatement("INSERT INTO bestellung_artikel(bestell_id, artikel_id, menge) VALUES(?,?,?)");
        ps.setInt(1,bestell_id);
        ps.setInt(2,artikel_id);
        ps.setInt(3,menge);
        ps.executeUpdate();
        ps.close();
        */
    }

    public void deleteArtikelfromBestellung(int bestell_id, int artikel_id) throws SQLException
    {
    	Bestellung_Artikel ab = manager.find(Bestellung_Artikel.class, bestell_id);
    	
    	manager.getTransaction().begin();
    	
    	manager.persist(b);
    	manager.getTransaction().commit();
    	
        PreparedStatement ps = conn.prepareStatement("DELETE FROM bestellung_artikel WHERE bestell_id=? AND artikel_id=?");
        ps.setInt(1,bestell_id);
        ps.setInt(2,artikel_id);
        ps.executeUpdate();
        ps.close();
    }


    public ArrayList<Bestellung_Artikel> getArtikelBestellung()throws SQLException
    {
        ArrayList<Bestellung_Artikel> artikelbestellungen = new ArrayList<>();
        
        Bestellung_Artikel ba;

        for(int i=0;i<=Bestellung_Artikel.anzahl; i++)
        {
        	ba= manager.find(Bestellung_Artikel.class, i);
        	Bestellung_Artikel be = new Bestellung_Artikel(ba.getbestellartikel_bestell(), ba.getbestellartikle_artikel(), ba.getMenge());
        	System.out.printf(" %20s , %20s , %20s ,\n",ba.getbestellartikel_bestell(), ba.getbestellartikle_artikel(), ba.getMenge());
        	artikelbestellungen.add(be);
        	manager.getTransaction().begin();
            manager.persist(be);
            manager.getTransaction().commit();
        }
        
        
        /*PreparedStatement ps = conn.prepareStatement("SELECT * FROM Bestellung_Artikel");
        ResultSet rs = ps.executeQuery();
        System.out.printf(" %20s , %20s , %20s ,\n","bestell_id", "artikel_id", "menge");

        while(rs.next())
        {
            int bestell_id = rs.getInt("bestell_id");
            int artikel_id = rs.getInt("artikel_id");
            int menge = rs.getInt("menge");
            System.out.printf(" %20s , %20s , %20s ,\n",bestell_id, artikel_id, menge);

            Bestellung_Artikel bestellArtikel = new Bestellung_Artikel(bestell_id,artikel_id,menge);
            artikelbestellungen.add(bestellArtikel);
        }

        rs.close();
        ps.close();
        */
        return artikelbestellungen;
    }



    public void closeProgramm() throws SQLException {
        this.conn.close();
    }





}