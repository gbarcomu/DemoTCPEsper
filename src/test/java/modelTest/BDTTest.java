package modelTest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.BDT;

public class BDTTest {

	@Test
	public void shouldGetAllInputValuesInClassAttributes() {
		
		BDT bdt = new BDT("5#3#2#0.5");
		
		assertTrue(bdt.getDataSourceId() == 5);
		assertTrue(bdt.getKindOfSource() == 3);
		assertTrue(bdt.getAlertValue() == 2);
		assertTrue(bdt.getSensorValue() == 0.5);
	}
	
	@Test
	public void shouldObtainNullIfValueIsMissing() {
		
		BDT bdt = new BDT("5#3##0.5");
		assertTrue(bdt.getAlertValue() == null);
		BDT bdt2 = new BDT("5#3#2#");
		assertTrue(bdt2.getSensorValue() == null);
	}
}
