package view

import scalatags.Text.all._
import Components.BlogComponents._
import Components._

/**
  * The about me page.
  */
object About extends View {
  def view: Modifier = Seq(
    div(style := "margin-left:10%;margin-right:10%;width:80%;padding:50px;", cls := "card container z-depth-1", id := "about-me-content")(
      img(src := "images/me.jpg", style := "margin-left:20%; margin-right:20%; width:60%;"),
      title3("leOrigins", "Le Origins [Hacking at Pokemon]"),
      par(
        """
          |Hi all, My name is Rafael Torres, I'm' from Brooklyn and I'm currently a Computer Science major at SUNY Oswego.
          |
          |I started tinkering with software hacking away at NDS rom ASM data and altering Pokemon stats in game in middle school.
          |I loved McFlurry's from Mc'Donalds so I would sell pokemon to my friends with goofy stats, like a Water type Charizard
          |with psychic attacks :) to make money to buy some ice cream after school."""),
      title3("collegeDays", "University [SUNY Oswego]"),
      par(
        """
          |Worry not, I am not pursuing a career in illegal Pokemon Dealing thanks to SUNY Oswego's CS Department. It's at Oswego that
          |I have been able completely surround my self with other students who share the same passion for software development that I do. I've probably
          |logged more hours in Shineman than the janitors, that building is a magnet for great ideas, I'll surely miss that place after graduating this December.
        """),
      title3("beyond", "Post-college Days"),
      par(
        """
          |I've decided to start blogging so I have a place to log my thoughts as I code, and who knows, maybe one of my posts might actually be useful to someone o.0.
          |
          |My posts will cover domains ranging from cloud computing (PaaS, IaaS, etc...) to data structure implementation and even API development (
          |I like making little libraries). The majority of my code is written Scala so expect to see a lot of <code>underscores</code>"""),
      title3("scalaTimes", "Being a Scala Type"),
      par("""
          |I'm a Scala Type, which means I primarily code in Scala. I've recently attended Scala Days 2016 and the future is looking extremely bright for the entire Scala ecosystem.
          |This site was developed using an end-to-end Scala stack and a huge smile on my face the entire time.
          | """
      )))

  override def render: String = Main.mainLayout("About", Seq(view)).render
}
