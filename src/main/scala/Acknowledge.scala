/*
  In this piece of code trying to get acknowlegment from reciever i.e MessageMonitor
  Setting 10 seconds to set the wait time
  If it does not return any message exception will be thrown for timeout
 */
import akka.actor.{Actor, ActorSystem, Props}
import akka.util.Timeout
import scala.concurrent.duration._
import akka.pattern.ask

import scala.concurrent.Await
class MessageMonitor extends  Actor {
  override def receive: Receive = {
    case msg : String => println("Message<===>"+msg)
      sender() ! "Hello I got your message.Thank you"
  }
}

object Acknowledge extends  App {
  var actorSystem =  ActorSystem("MessageMonitor")
  var actor = actorSystem.actorOf(Props[MessageMonitor],"MessageMonitor")
  implicit val timeout = Timeout(10 seconds)
  val future = actor.ask("Hello")
  val result = Await.result(future,timeout.duration)
  println(result)

}
