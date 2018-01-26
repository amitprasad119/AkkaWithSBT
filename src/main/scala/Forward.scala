import akka.actor.{Actor, ActorSystem, Props}
class MainMonitor extends Actor{
  override def receive: Receive = {
    case message :String => println("Message Came into MainMonitor:::"+ message + ":::"+ self.path.name)
    val childActor = context.actorOf(Props[LastRecieverMessage])
    childActor ! message
    case _ => println("Unknown message")
  }
}

class LastRecieverMessage extends Actor{
  override def receive: Receive = {
    case forwardedMsg : String => println("Got forwarded Msg:::"+forwardedMsg)
  }
}
object Forward extends App{
  val actorSystem = ActorSystem("MainActorSystem")
  val actor = actorSystem.actorOf(Props[MainMonitor],"MainMonitor")
  actor ! "Test for Forwarded Message!!"

}
