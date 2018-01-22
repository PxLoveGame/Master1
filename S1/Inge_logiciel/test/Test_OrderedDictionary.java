import Dico.OrderedDictionary;
import org.junit.*;


public class Test_OrderedDictionary {

    private OrderedDictionary od;

    @Before
    public void setUp()
    {
       od = new OrderedDictionary();
    }

    @Test
    public void testAddOneElementToEmptyDico()
    {
        od.put(0,1);
        Assert.assertEquals(od.getSize(), 1);
        Assert.assertTrue(od.containsKey(0));
    }

    @Test
    public void testMultipleAddToEmptyDico()
    {
        od.put(0,1);
        od.put(1,2);
        od.put(2,3);
        Assert.assertEquals(od.getSize(), 3);
        Assert.assertTrue(od.containsKey(0));
        Assert.assertTrue(od.containsKey(1));
        Assert.assertTrue(od.containsKey(2));

    }
}
