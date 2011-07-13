package sse
import scala.collection.mutable.HashMap

class SimpleSpreadSheet {

  var cells = new HashMap[(Char,Int),Cell]
  
  def setValue (point:(Char,Int),value:Cell) = {
    value.sse = this;
    this.cells += point -> value
  }
  
  def getValue (point:(Char,Int)) = {
    this.cells(point).output
  }
}

abstract class Cell {
  var sse:SimpleSpreadSheet = null
  def output:Int
}

class AbsoluteCell(var value:Int) extends Cell{
  def output = value
}

class AddCell(var c1:(Char,Int), var c2:(Char,Int)) extends Cell{
  def output = (sse.getValue(c1)+sse.getValue(c2))
}

class SubCell(var c1:(Char,Int), var c2:(Char,Int)) extends Cell{
	def output = (sse.getValue(c1)-sse.getValue(c2))
}

class MultCell(var c1:(Char,Int), var c2:(Char,Int)) extends Cell{
	def output = (sse.getValue(c1)*sse.getValue(c2))
}

class DivCell(var c1:(Char,Int), var c2:(Char,Int)) extends Cell{
	def output = (sse.getValue(c1)/sse.getValue(c2))
}