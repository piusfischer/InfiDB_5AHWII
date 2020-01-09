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
        Person p = new Person(2,18, "Sepp","Huber");
        Person p = new Person();
        p.setAge(18);
        p.setFirstName("Seppl");
        p.setLastName("Huberer");
        EntityManagerFactory f = Persistence.createEntityManagerFactory("MyUnit");
        EntityManager manager = f.createEntityManager();
        manager.getTransaction().begin();
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
        p.setHobby2(h);
        manager.persist(p);
        manager.getTransaction().commit();
        
        
    }
}
