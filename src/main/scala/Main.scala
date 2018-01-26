
import akka.actor.{Actor, ActorSystem, Props}

class HelloAkka extends Actor{

  override def receive: Receive = {
    case msg:String => println("Actor[Parent]::"+ msg + self.path.name)
      println("SENDER:::"+sender())
      var child = context.actorOf(Props[Actor2],name="childActor")
      child ! "Hello"
  }
}

class Actor2 extends Actor{
  override def receive: Receive = {
    case msg:String => println("Actor[Child]:::"+ msg + self.path.name)
    println("SENDER:::"+sender())
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    var actorSystem = ActorSystem("ActorSystem")
    var actor = actorSystem.actorOf(Props[HelloAkka],"HelloAkka")
    actor ! "Hello from Amit"
  }
}
