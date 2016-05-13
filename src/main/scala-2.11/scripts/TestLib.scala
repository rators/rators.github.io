package scripts

import org.scalajs.dom
import org.scalajs.dom.{MouseEvent, document}
import org.scalajs.jquery.jQuery

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
/**
  * Created by rotor on 3/21/2016.
  */
@JSExport
object TestLib extends JSApp {
  var navActive: Boolean = false

  @JSExport
  def addNavButtonListener(): Unit = {
    val nav_button: dom.Element = document.getElementById("nav-button")
    nav_button.addEventListener("click", (e: MouseEvent) => e match {
      case _ =>
        println("Button clicked")
        e.preventDefault()
        toggleNav()
    }, useCapture = false)
  }

  @JSExport
  def addBodyListener(): Unit = {
    val body_box: dom.Element = document.getElementById("content-box")
    body_box.addEventListener("click", (e: MouseEvent) => e match {
      case _ =>
        e.preventDefault()
        bodyScope()
    }, useCapture = false)
  }

  val windowListener: scalajs.js.Function1[dom.MouseEvent, _] = (e: MouseEvent) => e match {
    case _ =>
      addNavButtonListener()
      addBodyListener()
  }

  @JSExport
  override def main(): Unit = {
    println("main")
    dom.window.addEventListener("load", windowListener, useCapture = false)
  }

  @JSExport
  def toggleNav(): Unit = {
    println("Toggling nav")
    val navBar = jQuery("#main-nav")
    if (navBar.hasClass("inactive")) {
      navBar.removeClass("inactive")
      navBar.addClass("active")
      navActive = true
      jQuery(document.body).toggleClass("no-scroll")
    }
    else if (navBar.hasClass("active")) {
      navBar.removeClass("active")
      navBar.addClass("inactive")
      navActive = false
      jQuery(document.body).toggleClass("no-scroll")
    }
    else {
      navBar.addClass("active")
      navActive = true
      jQuery(document.body).toggleClass("no-scroll")
    }
  }

  @JSExport
  def bodyScope() {
    if (navActive) {
      toggleNav()
    }
  }

}
