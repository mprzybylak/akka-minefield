package pl.mprzybylak.minefields.akka.random.messages 

object MeanMessage {
  case class GenerateNumberMessage()
  case class RandomNumberMessage(number:Int)
  case class CalculateMeanMessage(numbers:List[Int])
  case class MeanResultMessage(mean:Double)
  case class PrintResultMessage(value:Double)
  case class EndProgramMessage()
}