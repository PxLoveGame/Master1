package tests;
 
 
import static org.mockito.ArgumentMatchers.anyInt;
 
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import mockito.C;
 
 
@RunWith(PowerMockRunner.class)
@PrepareForTest({C.class})
public class TestMockC {
 
    @Test
    public void test1() {
        PowerMockito.mockStatic(C.class);
        int res = 666;
        Mockito.when(C.m1()).thenReturn(res);
        Assert.assertEquals(C.m1(), res);
    }
 
   
    @Test
    public void test2() {
        C mock = PowerMockito.mock(C.class);
        final int res = 666;
        Mockito.when(mock.m2(anyInt())).thenReturn(res);
        Assert.assertEquals(mock.m2(anyInt()), res);
    }
}
