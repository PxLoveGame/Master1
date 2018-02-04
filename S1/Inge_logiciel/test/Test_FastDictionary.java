import Dico.FastDictionary;
import org.junit.*;

public class Test_FastDictionary {

    private FastDictionary fd;

    @Before
    public void setUp()
    {
        fd = new FastDictionary();
    }


    @Test
    public void testAddOneElementToEmptyDico()
    {
        fd.put(0,1);
        Assert.assertEquals(fd.getSize(), 1);
        Assert.assertTrue(fd.containsKey(0));
    }

    @Test
    public void testMultipleAddToEmptyDico()
    {
        fd.put(0,1);
        fd.put(1,2);
        fd.put(2,3);
        Assert.assertEquals(fd.getSize(), 3);
        Assert.assertTrue(fd.containsKey(0));
        Assert.assertTrue(fd.containsKey(1));
        Assert.assertTrue(fd.containsKey(2));

    }
}
