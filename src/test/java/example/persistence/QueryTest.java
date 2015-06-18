package example.persistence;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
public class QueryTest {
    
    private static final String PU = "examplePU";
    private static final String TESTDATA = "data/testdata.xml";

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private static IDatabaseConnection dbConnection;
    private static FlatXmlDataSet dataSet;

    @BeforeClass
    public static void setUpClass() throws DatabaseUnitException {
        emf = Persistence.createEntityManagerFactory(PU);
        em = emf.createEntityManager();
        
        em.getTransaction().begin();
        final Connection jdbcConnection = em.unwrap(Connection.class);
        em.getTransaction().rollback();
        
        dbConnection = new DatabaseConnection(jdbcConnection);
        
        final InputStream is = QueryTest.class.getClassLoader().getResourceAsStream(TESTDATA);
        final FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        dataSet = builder.build(is);
    }

    @AfterClass
    public static void tearDownClass() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    @Before
    public void setUp() throws DatabaseUnitException, SQLException {
        em.clear();
        emf.getCache().evictAll();

        DatabaseOperation.CLEAN_INSERT.execute(dbConnection, dataSet);
    }

    @After
    public void tearDown() throws DatabaseUnitException, SQLException {
        DatabaseOperation.DELETE_ALL.execute(dbConnection, dataSet);
    }
    
    @Test
    public void testFindOnlyKartenverteaege() {
        final TypedQuery q = em.createQuery("Select e From KartenVertrag e", KartenVertrag.class);
        
        final List<Object[]> result = q.getResultList();
        assertEquals(2, result.size());
    }
    
    @Test
    public void testFindOnlyPFMobileVertraege() {
        final TypedQuery q = em.createQuery("Select e From PFMobileVertrag e", PFMobileVertrag.class);
        
        final List<Object[]> result = q.getResultList();
        assertEquals(3, result.size());
    }
    
    @Test
    public void testFindAlleVertraege() {
        final TypedQuery q = em.createQuery("Select e From Vertrag e", Vertrag.class);
        
        final List<Object[]> result = q.getResultList();
        assertEquals(5, result.size());
    }
    
}
