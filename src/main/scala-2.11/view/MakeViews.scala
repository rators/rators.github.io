package view

import java.io.{File, PrintWriter}
import Blog.links
/**
  * The strings of the views.
  */
object MakeViews extends App {
  println(links)

  def makeView(fileName: String, fileContent: String) = {
    val f = new File(fileName)
    val writer = new PrintWriter(f)
    writer.write(fileContent)
    writer.close()
  }

  makeView("index.html", Main)
  makeView("aboutme.html", About)
  makeView("work.html", Blog)
  makeView("cvresume.html", CVResume)

  implicit def viewStr(view: View): String = view.render
}
