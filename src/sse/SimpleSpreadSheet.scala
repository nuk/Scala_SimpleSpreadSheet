package sse
import scala.collection.mutable.HashMap

class SimpleSpreadSheet {
  
  var cells = new HashMap[(Char,Int),Cell]
  
  def setValue (point:(Char,Int),value:Cell) = {
    value.sse = this;
    this.cells += point -> value
    this
  }
  
  def getValue (point:(Char,Int)) = {
    this.cells(point).output
  }
  
  def getIntValue (point:(Char,Int)) = {
    this.cells(point).output.asInstanceOf[Int]
  }
}

abstract class Cell {
  var sse:SimpleSpreadSheet = null
  def output:AnyRef
}

class AbsoluteCell(var value:Int) extends Cell{
  def output = value.asInstanceOf[AnyRef]
}

class AddCell(var c1:(Char,Int), var c2:(Char,Int)) extends Cell{
  def output = (sse.getIntValue(c1)+sse.getIntValue(c2)).asInstanceOf[AnyRef]
}

class SubCell(var c1:(Char,Int), var c2:(Char,Int)) extends Cell{
	def output = (sse.getIntValue(c1)-sse.getIntValue(c2)).asInstanceOf[AnyRef]
}

class MultCell(var c1:(Char,Int), var c2:(Char,Int)) extends Cell{
	def output = (sse.getIntValue(c1)*sse.getIntValue(c2)).asInstanceOf[AnyRef]
}

class DivCell(var c1:(Char,Int), var c2:(Char,Int)) extends Cell{
	def output = (sse.getIntValue(c1)/sse.getIntValue(c2)).asInstanceOf[AnyRef]
}

class RefCell(var ref:(Char,Int)) extends Cell{
	def output = sse.getValue(ref)
}

class TextCell(var value:String) extends Cell{
	def output = value
}