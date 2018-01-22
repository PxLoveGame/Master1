package tests;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import mockito.I;

@RunWith(MockitoJUnitRunner.class) 
public class TestMockI{
	I mockI = mock(I.class);
	
	@Before 
	public void setUp() throws Exception{
		when(mockI.methodeParam(3)).thenReturn(5);
		when(mockI.methodeParam(5)).thenReturn(10);
		when(mockI.methodeInt()).thenReturn(1,2,3,4);
		doThrow(new Exception()).when(mockI).methodeVoid();
	}
	
	@Test 
	public void testDefaultValue() throws Exception{
		Assert.assertEquals(mockI.methodeParam(0), 0);
	}
	
	@Test
	public void multipleReturns() throws Exception{
		Assert.assertEquals(mockI.methodeInt(), 1);
		Assert.assertEquals(mockI.methodeInt(), 2);
		Assert.assertEquals(mockI.methodeInt(), 3);
		Assert.assertEquals(mockI.methodeInt(), 4);
		
		verify(mockI,times(4)).methodeInt();
	}
	
	@Test(expected = Exception.class)
	public void verifyException() throws Exception{
		mockI.methodeVoid();
	}
	
	@Test
	public void testMatcherParameter() throws Exception{
		Assert.assertEquals(0, mockI.methodeParam(0));
		Assert.assertEquals(5, mockI.methodeParam(3));
		Assert.assertEquals(10, mockI.methodeParam(5));
	}
	
	
	
}
