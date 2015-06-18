package example.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
public class CrudTest {
    
    private static final String PU = "examplePU";
    
    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory(PU);
        em = emf.createEntityManager();
    }
    
    @AfterClass
    public static void tearDownClass() {
        if(em != null && em.isOpen()) {
            em.close();
        }
        if(emf != null && emf.isOpen()) {
            emf.close();
        }
    }
    
    private Long kartenVertragFritzId;
    private Long kartenVertragPeterId;
    
    @Before
    public void create() {
        em.getTransaction().begin();

        final KartenVertrag kartenVertragFritz = new KartenVertrag();
        kartenVertragFritz.setKarteId(1234);
        kartenVertragFritz.setKontoNr(1111);
        em.persist(kartenVertragFritz);
        em.flush();
        em.refresh(kartenVertragFritz);
        kartenVertragFritzId = kartenVertragFritz.getId();
        
        final KartenVertrag kartenVertragPeter = new KartenVertrag();
        kartenVertragPeter.setKarteId(4321);
        kartenVertragPeter.setKontoNr(4444);
        em.persist(kartenVertragPeter);
        em.flush();
        em.refresh(kartenVertragPeter);
        kartenVertragPeterId = kartenVertragPeter.getId();

        em.getTransaction().commit();
        
        em.clear();
        emf.getCache().evictAll();
    }

    @Test
    public void test() {
    }
    
    @After
    public void delete() {
        em.getTransaction().begin();

        em.remove(em.getReference(KartenVertrag.class, kartenVertragFritzId));
        em.remove(em.getReference(KartenVertrag.class, kartenVertragPeterId));

        em.getTransaction().commit();
            
        // sicherstellen dass die entities neu geladen werden.
        em.clear();
        emf.getCache().evictAll();
        
        assertEquals(0l, em.createQuery("Select count(e) From KartenVertrag e").getSingleResult());
        assertEquals(0l, em.createQuery("Select count(e) From Vertrag e").getSingleResult());
        assertEquals(0l, em.createQuery("Select count(e) From PFMobileVertrag e").getSingleResult());
    }

    @Test
    public void find() {
        final KartenVertrag kartenVertragFritz = em.find(KartenVertrag.class, kartenVertragFritzId);
        assertNotNull(kartenVertragFritz);
        assertEquals(Integer.valueOf(1234), kartenVertragFritz.getKarteId());
        
        final KartenVertrag kartenVertragPater = em.find(KartenVertrag.class, kartenVertragPeterId);
        assertNotNull(kartenVertragPater);
        assertEquals(Integer.valueOf(4321), kartenVertragPater.getKarteId());
    }

    @Test
    public void update() {
        final KartenVertrag kartenVertragFritz1 = em.find(KartenVertrag.class, kartenVertragFritzId);
        assertEquals(Integer.valueOf(1111), kartenVertragFritz1.getKontoNr());
            
        em.getTransaction().begin();

        kartenVertragFritz1.setKontoNr(2222);

        em.getTransaction().commit();
            
        em.clear();
        emf.getCache().evictAll();

        final KartenVertrag kartenVertragFritz2 = em.find(KartenVertrag.class, kartenVertragFritzId);
        assertNotSame(kartenVertragFritz1, kartenVertragFritz2);
        assertEquals(Integer.valueOf(2222), kartenVertragFritz2.getKontoNr());
    }
}
