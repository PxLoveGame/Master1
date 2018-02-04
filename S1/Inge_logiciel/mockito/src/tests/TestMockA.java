package tests;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import mockito.A;

@RunWith(MockitoJUnitRunner.class) 
public class TestMockA {
	A a  = new A();
	A spyA = spy(a);
	
	@Mock A mockA;
	
	@Before 
	public void setUp()
	{
		//when(mockA.m2(anyInt())).thenCallRealMethod();  utile si on travail avec un Mock et non un spy 
		when(spyA.m2(42)).thenReturn(0);		
	}
	
	@Test
	public void testCallMethode()
	{
		Assert.assertEquals(spyA.m1(), 42);
		Assert.assertEquals(spyA.m2(5), 25);
		verify(spyA,atLeastOnce()).m1();
		verify(spyA,atLeastOnce()).m2(5);
	}
	
	@Test
	public void testMockM2()
	{	
		Assert.assertEquals(9,spyA.m2(3));
		Assert.assertEquals(16,spyA.m2(4));
		Assert.assertEquals(0, spyA.m2(42));
		
		verify(spyA,times(3)).m2(anyInt());
	}
	
}
