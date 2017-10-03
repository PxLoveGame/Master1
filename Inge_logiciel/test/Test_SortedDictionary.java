import Dico.SortedDictionary;
import org.junit.*;

public class Test_SortedDictionary {

    private SortedDictionary sd;

    @Before
    public void setUp()
    {
        sd = new SortedDictionary();
    }


    @Test
    public void testAddOneElementToEmptyDico()
    {
        sd.put(0,1);
        Assert.assertEquals(sd.getSize(), 1);
        Assert.assertTrue(sd.containsKey(0));
    }

    @Test
    public void testMultipleAddToEmptyDico()
    {
        sd.put(0,1);
        sd.put(1,2);
        sd.put(2,3);
        Assert.assertEquals(sd.getSize(), 3);
        Assert.assertTrue(sd.containsKey(0));
        Assert.assertTrue(sd.containsKey(1));
        Assert.assertTrue(sd.containsKey(2));

    }
}
