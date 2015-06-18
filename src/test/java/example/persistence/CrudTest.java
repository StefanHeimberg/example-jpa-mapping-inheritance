package example.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
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
        kartenVertragPeter.setKontoNr(2222);
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
    
//    @After
//    public void delete() {
//        em.getTransaction().begin();
//
//        em.remove(em.getReference(Employee.class, employeeId));
//        em.remove(em.getReference(Department.class, departmentId));
//
//        em.getTransaction().commit();
//            
//        // sicherstellen dass die entities neu geladen werden.
//        em.clear();
//        emf.getCache().evictAll();
//        
//        assertEquals(0l, em.createQuery("Select count(e) From Employee e").getSingleResult());
//        assertEquals(0l, em.createQuery("Select count(e) From Address e").getSingleResult());
//        assertEquals(0l, em.createQuery("Select count(e) From Department e").getSingleResult());
//    }

//    @Test
//    public void find() {
//        final Department department = em.find(Department.class, departmentId);
//        assertNotNull(department);
//        assertEquals("IT", department.getName());
//
//        final Address address = em.find(Address.class, addressId);
//        assertNotNull(address);
//        assertEquals("Blumenstrasse 13", address.getStreet());
//
//        final Employee employee = em.find(Employee.class, employeeId);
//        assertNotNull(employee);
//        assertSame(address, employee.getAddress());
//        assertSame(department, employee.getDepartment());
//    }

//    @Test
//    public void update() {
//        final Employee employee1 = em.find(Employee.class, employeeId);
//        assertFalse("Fritz Friedrich".equals(employee1.getName()));
//            
//        em.getTransaction().begin();
//
//        employee1.setName("Fritz Friedrich");
//        employee1.getAddress().setStreet("Blumenstrasse 99");
//
//        em.getTransaction().commit();
//            
//        em.clear();
//        emf.getCache().evictAll();
//
//        final Employee employee2 = em.find(Employee.class, employeeId);
//        assertNotSame(employee1, employee2);
//        assertNotSame(employee1.getAddress(), employee2.getAddress());
//
//        assertEquals("Fritz Friedrich", employee2.getName());
//        assertEquals("Blumenstrasse 99", employee2.getAddress().getStreet());
//    }
}
