package at.htlinn.fischer;
import java.sql.*;
import java.util.ArrayList;


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
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Kunde(id,titelv, vorname, nachname, titeln) VALUES(?,?,?,?,?)");
        ps.setInt(1, Kundenid);
        ps.setString(2, titelv);
        ps.setString(3, vorname);
        ps.setString(4, nachname);
        ps.setString(5,titeln);
        ps.executeQuery();
        ps.close();
    }

    public void deleteKunde(int id) throws SQLException
    {

        PreparedStatement ps = conn.prepareStatement("DELETE FROM Kunde WHERE id=?");
        ps.setInt(1,id);
        ps.executeUpdate();
        ps.close();
    }
    public ArrayList<Kunde> getKunden()throws SQLException
    {
        ArrayList<Kunde> kunden = new ArrayList<>();


        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Kunde");
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
        return kunden;
    }


    public int Adresseid= 0;
    public void setAdresse(String stadt, String strasse, int plz, String hnr) throws SQLException{
        Adresseid= Adresseid+1;
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Adresse(id, stadt, strasse, plz, hnr) VALUES(?,?,?,?,?)");
        ps.setInt(1, Adresseid);
        ps.setString(2, stadt);
        ps.setString(3, strasse);
        ps.setInt(4, plz);
        ps.setString(5, hnr);
        ps.executeQuery();
        ps.close();
    }

    public void deleteAdresse(int id) throws SQLException
    {

        PreparedStatement ps = conn.prepareStatement("DELETE FROM Adresse WHERE id=?");
        ps.setInt(1,id);
        ps.executeUpdate();
        ps.close();
    }
    public ArrayList<Adresse> getAdressen()throws SQLException
    {
        ArrayList<Adresse> adressen = new ArrayList<>();


        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Adresse");
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
        return adressen;
    }


    public int Artikelid=0;
    public void setArtikel(String name, double preis) throws SQLException{
        Artikelid=Artikelid+1;
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Artikel(id, name, preis) VALUES(?,?,?)");
        ps.setInt(1, Artikelid);
        ps.setString(2, name);
        ps.setDouble(3, preis);
        ps.executeQuery();
        ps.close();
    }

    public void deleteArtikel(int id) throws SQLException
    {

        PreparedStatement ps = conn.prepareStatement("DELETE FROM Artikel WHERE id=?");
        ps.setInt(1,id);
        ps.executeUpdate();
        ps.close();
    }
    public ArrayList<Artikel> getArtikel()throws SQLException
    {
        ArrayList<Artikel> artikel = new ArrayList<>();


        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Artikel");
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
        return artikel;
    }

    public int Bestellungid=0;
    public void setBestellung(int kid, int arid, int alid) throws SQLException {
        Bestellungid=Bestellungid+1;
        PreparedStatement ps = conn.prepareStatement("INSERT INTO bestellung(id, kunde_id, adresse_rechnung_id, adresse_liefer_id) VALUES(?,?,?,?)");
        ps.setInt(1, Bestellungid);
        ps.setInt(2, kid);
        ps.setInt(3, arid);
        ps.setInt(4, alid);
        ps.executeUpdate();
        ps.close();

    }

    public ArrayList<Bestellung> getBestellung() throws SQLException
    {
        ArrayList<Bestellung> bestellungen = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM bestellung");
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
        return bestellungen;
    }

    public void deleteBestellung(int id) throws SQLException
    {

        PreparedStatement ps = conn.prepareStatement("DELETE FROM Bestellung WHERE id=?");
        ps.setInt(1,id);
        ps.executeUpdate();
        ps.close();
    }

    public void addArtikeltoBestellung(int bestell_id, int artikel_id, int menge) throws SQLException
    {

        PreparedStatement ps = conn.prepareStatement("INSERT INTO bestellung_artikel(bestell_id, artikel_id, menge) VALUES(?,?,?)");
        ps.setInt(1,bestell_id);
        ps.setInt(2,artikel_id);
        ps.setInt(3,menge);
        ps.executeUpdate();
        ps.close();
    }

    public void deleteArtikelfromBestellung(int bestell_id, int artikel_id) throws SQLException
    {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM bestellung_artikel WHERE bestell_id=? AND artikel_id=?");
        ps.setInt(1,bestell_id);
        ps.setInt(2,artikel_id);
        ps.executeUpdate();
        ps.close();
    }


    public ArrayList<Bestellung_Artikel> getArtikelBestellung()throws SQLException
    {
        ArrayList<Bestellung_Artikel> artikelbestellungen = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Bestellung_Artikel");
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
        return artikelbestellungen;
    }



    public void closeProgramm() throws SQLException {
        this.conn.close();
    }





}