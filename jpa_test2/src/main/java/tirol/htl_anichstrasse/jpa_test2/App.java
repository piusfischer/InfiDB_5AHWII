package tirol.htl_anichstrasse.jpa_test2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Hobby2 motocross= new Hobby2("motocross", 12, 500);
    	Person p = new Person(2,18, "Sepp","Huber", motocross);
    	
        Person p1 = new Person();
        p1.setAge(18);
        p1.setFirstName("Seppl");
        p1.setLastName("Huberer");
        EntityManagerFactory f = Persistence.createEntityManagerFactory("MyUnit");
        EntityManager manager = f.createEntityManager();
        
        
        manager.getTransaction().begin();
        manager.persist(p1);
        manager.persist(p);
        manager.getTransaction().commit();
        
        // read person
   //     Person p = manager.find(Person.class, 1L);
   //     System.out.println(p);
   //     
        //update person
   //     p.setFirstName("Ingeborg");
   //     manager.getTransaction().begin();
   //     manager.getTransaction().commit();
        
        //delete person
   //     manager.getTransaction().begin();
   //     manager.remove(p);
   //     manager.getTransaction().commit();
        
        Hobby2 h = new Hobby2("Schwimmen", 10, 0);
        manager.getTransaction().begin();
        manager.persist(h);
        p1.setHobby2(h);
        manager.persist(p1);
        manager.getTransaction().commit();
        
        
    }
}
