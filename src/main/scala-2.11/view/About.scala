package view

import scalatags.Text.all._
import Components._

/**
  * The about me page.
  */
object About extends View {
  def view: Modifier = Seq(
    div(style := "margin-left:10%;margin-right:10%;width:80%;padding:50px;", cls := "card container z-depth-1", id := "about-me-content")(
      img(src := "images/me.jpg", style := "margin-left:20%; margin-right:20%; width:60%;"),
      p(
        cls := "light-center")(raw(
        """ Hi all, My name is Rafael Torres and I am a Computer Science major at SUNY Oswego. <br><br>

        I am software developer, a hobby turned passion that has beautifully consumed countless of my life, not complaining  :). <br><br>
        My current focus/obsession is the Scala programming language and the Light-bend ecosystem build around it (Akka - Play! - Slick).<br><br>
        This little site serves as my programming  career journal, where I’ll share my thought process as I got about solving many different problems. <br><br>
        My posts will cover domains ranging from cloud computing to data structure implementation and even API development (I like making little libraries). <br><br>
        Stay tuned and I hope you enjoy your time in my mini web-hut. Thanks! """))))

  override def render: String = Main.mainLayout("About", Seq(view)).render
}
