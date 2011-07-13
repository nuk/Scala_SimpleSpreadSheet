package sse

import org.junit.Test
import org.junit.Assert._

class TestSimpleSpreadSheet{

  implicit def int2AbsoluteCell(t:Int) = new AbsoluteCell(t)
  
  @Test
	def should_set_a_value_in_a_cell: Unit ={
    	var sheet = new SimpleSpreadSheet
    	sheet.setValue(('B',15),10)
    	assertEquals(10,sheet.getValue(('B',15)))
    }
  
	@Test
	def should_set_a_different_value_in_a_cell: Unit ={
    	var sheet = new SimpleSpreadSheet
    	sheet.setValue(('B',15),9)
    	assertEquals(9,sheet.getValue(('B',15)))
    }
	
	@Test
	def should_return_all_setted_cells_values: Unit ={
		var sheet = new SimpleSpreadSheet
		sheet.setValue(('B',15),9)
		sheet.setValue(('Z',10),10)
		assertEquals(9,sheet.getValue(('B',15)))
		assertEquals(10,sheet.getValue(('Z',10)))
	}
	
	@Test
	def should_compute_a_simple_sum: Unit ={
		var sheet = new SimpleSpreadSheet
		sheet.setValue(('B',15),9)
		sheet.setValue(('Z',10),10)
		sheet.setValue(('A',1),new AddCell(('B',15),('Z',10)))
		assertEquals(9+10,sheet.getValue(('A',1)))
	}
	
	@Test
	def should_compute_a_simple_subtraction: Unit ={
		var sheet = new SimpleSpreadSheet
		sheet.setValue(('B',15),9)
		sheet.setValue(('Z',10),10)
		sheet.setValue(('A',1),new SubCell(('B',15),('Z',10)))
		assertEquals(9-10,sheet.getValue(('A',1)))
	}
	
	@Test
	def should_compute_a_simple_multiplication: Unit ={
		var sheet = new SimpleSpreadSheet
		sheet.setValue(('B',15),9)
		sheet.setValue(('Z',10),10)
		sheet.setValue(('A',1),new MultCell(('B',15),('Z',10)))
		assertEquals(9*10,sheet.getValue(('A',1)))
	}
	
	@Test
	def should_compute_a_simple_division: Unit ={
		var sheet = new SimpleSpreadSheet
		sheet.setValue(('B',15),8)
		sheet.setValue(('Z',10),2)
		sheet.setValue(('A',1),new DivCell(('B',15),('Z',10)))
		assertEquals(8/2,sheet.getValue(('A',1)))
	}
	
	@Test
	def should_compute_a_complex_calculation: Unit ={
		var sheet = new SimpleSpreadSheet
		sheet.setValue(('A',1),5)
		sheet.setValue(('B',1),2)
		sheet.setValue(('C',1),new AddCell(('A',1),('B',1)))
		sheet.setValue(('D',1),3)
		sheet.setValue(('E',1),new SubCell(('C',1),('D',1)))
		sheet.setValue(('F',1),4)
		sheet.setValue(('G',1),new MultCell(('E',1),('F',1)))
		sheet.setValue(('H',1),2)
		sheet.setValue(('I',1),new DivCell(('G',1),('H',1)))
		assertEquals((((5+2)-3)*4)/2,sheet.getValue(('I',1)))
	}
	
	@Test
	def should_compute_a_complex_calculation_changing_values: Unit ={
		var sheet = new SimpleSpreadSheet
		sheet.setValue(('A',1),5)
		sheet.setValue(('B',1),2)
		sheet.setValue(('C',1),new AddCell(('A',1),('B',1)))
		sheet.setValue(('D',1),3)
		sheet.setValue(('E',1),new SubCell(('C',1),('D',1)))
		sheet.setValue(('F',1),4)
		sheet.setValue(('G',1),new MultCell(('E',1),('F',1)))
		sheet.setValue(('H',1),2)
		sheet.setValue(('I',1),new DivCell(('G',1),('H',1)))
		assertEquals((((5+2)-3)*4)/2,sheet.getValue(('I',1)))
		sheet.setValue(('D',1),7)
		assertEquals((((5+2)-7)*4)/2,sheet.getValue(('I',1)))
	}
}