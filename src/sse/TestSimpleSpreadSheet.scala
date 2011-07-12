package sse

import org.junit.Test
import org.junit.Assert._

class TestSimpleSpreadSheet{
	
  @Test
  def should_set_a_value_in_a_cell: Unit ={
    var sheet = new SimpleSpreadSheet
    sheet.setValue(('B',15),10)
    assertEquals(10,sheet.getValue(('B',15)))
  }
}