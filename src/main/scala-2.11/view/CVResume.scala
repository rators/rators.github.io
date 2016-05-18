package view

import scalatags.Text.all._
import Components._
import Components.BlogComponents._

import scalatags.Text.TypedTag
/**
  * Created by rotor on 5/18/2016.
  */
object CVResume extends View {
  def view: Modifier = div(cls := "container")(
    cvResumeCard("Work Experience")
  )

  override def render: String = Main.mainLayout("CV | Resume", Seq(view)).render
}
