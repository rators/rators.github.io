package view

import scalatags.Text.TypedTag
import scalatags.Text.all._

/**
  * The components for materialize css.
  */
object Components {

  def card(title: String, topics: Seq[TypedTag[String]], cardContent: Modifier, links: Seq[(String, String)]): Modifier =
    div(cls := "blog-card card white col s12 m12 l10")(
      div(cls := "valign-wrapper")(
        div(cls := "valign date-brick")("May 5th | 2016")),
      div(cls := "card-content black-text")(
        span(cls := "card-title")(title, topics),
        section(cardContent),
        div(cls := "card-action")(
          for ((text, url) <- links) yield {
            a(href := url)(text)
          }
        )
      )
    )

  def scalaCode(source: String): Modifier = pre(code(cls := "language-scala")(raw(source)))

  def section(sectionContent: Modifier): Modifier = Seq(divider, sectionContent, divider)

  def divider = div(cls := "divider")

  def dividerTop = div(cls := "divider", id := "top-divider")

  def dividerNavLink = div(cls := "divider nav-divider")

  def scalaChip = div(cls := "chip")(
    img(src := "images/scala.png", alt := "Scala Language", cls := "chip-img")("Scala")
  )

  def title3(iD: String, content: String) = h6(id := s"$iD", cls := "title3 section scrollspy")(content)

  def javaChip = div(cls := "chip")(
    img(src := "images/java-icon.png", alt := "Java Language", cls := "chip-img")("Java")
  )

  def tableOfContents(links: Seq[(String, String)]) =
    div(cls := "col hide-on-med-and-down m2 l2")(
      div(cls := "toc-wrapper")(
        ul(cls := "section table-of-contents")(
          for ((link, text) <- links) yield {
            li(a(href := s"#$link")(text))
          }
        )
      )
    )

  object BlogComponents {
    def par(content: String) =
      p(marginTop := "1em;", marginBottom := "1em;")(
        raw(content.stripMargin)
      )
  }


}

