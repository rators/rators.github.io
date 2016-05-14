package view

import scalatags.Text.TypedTag

/**
  * Created by rotor on 5/5/2016.
  */
trait View {
  def render: String

  def contents: Option[TypedTag[String]] = None
}
