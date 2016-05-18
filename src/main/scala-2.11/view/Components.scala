package view

import scalatags.Text.TypedTag
import scalatags.Text.all._

/**
  * The components for materialize css.
  */
object Components {

  def blogCard(title: String, topics: Seq[TypedTag[String]], cardContent: Modifier, links: Seq[(String, String)]): Modifier =
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

  def cvResumeCard(title: String) = div(cls := "row")(
    div(cls := "col s12 m12 l12")(
      div(cls := "card")(
        div(cls := "card-content black-text row", style := "padding-bottom:0;")(
          div(i(cls := "material-icons col s12 m12 l1 center-align", style := "font-size:3rem; color:#757575;")("business_center")),
          div(cls := "col s12 m12 l11")(
            div(cls := "card-title cv-header", style := "color: #757575;")(title),
            div(cls := "card-content black-text col s12 m12 l12", style := "padding-bottom:0;")(
              div(cls := "row", style := "font-weight:300;")(
                div(cls := "col s12 m12 l12", style := "color:#757575; font-size:1.05em; font-weight: 400;")("Electrical and Computer Engineering Research Assistant@SUNY OSWEGO"),
                a(cls := "col s12 m12 l12")("SUMMER 2015"),
                div(cls := "col s12 m12 l12", style := "padding-top:5px")(
                  """Re-designed a pixel array detector for future use in X-ray imaging research projects in SUNY Oswego and Cornell University. """
                ),
                div(cls := "col s12 m12 l12", style := "padding-top: 5px;")(
                  """Actively sought out the necessary components needed to improve the current PCB design of PAD, all while maintaining backwards compatibility with the existing FPGA data buffer."""
                )
              )
            ),
            dividerNavLink,
            div(cls := "card-content black-text col s12 m12 l12", style := "padding-bottom:0;")(
              div(cls := "row", style := "font-weight:300;")(
                div(cls := "col s12 m12 l12", style := "color:#757575; font-size:1.05em; font-weight: 400;")("Software Engineer @Prista Technologies LLC"),
                div(cls := "cv-position-block")(
                  a(cls := "col s12 m12 l12")("SEPT 2015 - APRIL 2016"),
                  div(cls := "col s12 m12 l12", style := "padding-top: 5px; padding-bottom: 5px;")(
                    """Desktop actor-based (Akka) radar application development for the United States Marines."""
                  ),
                  div(cls := "col s12 m12 l12", style := "padding-top: 5px")(
                    raw("""Javascript / HTML / CSS : Implemented new features and maintained the database back-end for a non-profit organizations's senior assistance web portal.""")
                  )
                )
              )
            ),
            dividerNavLink,
            div(cls := "card-content black-text col s12 m12 l12")(
              div(cls := "row", style := "font-weight:300;")(
                div(cls := "col s12 m12 l12", style := "color:#757575; font-size:1.05em; font-weight: 400;")("Electrical and Computer Engineering Research Assistant@SUNY OSWEGO"),
                a(cls := "col s12 m12 l12")("SUMMER 2015"),
                div(cls := "col s12 m12 l12", style := "padding-bottom: 5px; padding-top:5px")(
                  """Re-designed a pixel array detector for future use in X-ray imaging research projects in SUNY Oswego and Cornell University. """
                ),
                div(cls := "col s12 m12 l12", style := "padding-top: 5px; padding-bottom: 5px;")(
                  """Actively sought out the necessary components needed to improve the current PCB design of PAD, all while maintaining backwards compatibility with the existing FPGA data buffer."""
                )
              )
            )
          )
        )
      )
    ),
    div(cls := "col s12 m12 l12")(
      div(cls := "card")(
        div(cls := "card-content black-text row")(
          div(i(cls := "material-icons col s12 m12 l1 center-align", style := "font-size:3rem; color:#757575;")("lightbulb_outline")),
          div(cls := "col s12 m11 l11")(
            div(cls := "card-title cv-header col s12 m12", style := "color: #757575;")("Projects"),
            div(cls := "card-content black-text col s12 m12 l12", style := "padding-bottom:0;")(
              div(cls := "row", style := "font-weight:300;")(
                div(cls := "col s12 m12 l12", style := "color:#757575; font-size:1.05em; font-weight: 400;")("Lead Developer@Team Null : Crashed Martian for Android and PC;"),
                div(cls := "cv-position-block")(
                  a(cls := "col s12 m12 l12")("FALL 2015 SEMESTER"),
                  div(cls := "col s12 m12 l12", style := "padding-top: 5px; padding-bottom: 5px;")(
                    """Designed the underlying Entity Component System architecture for a 2d sidescroller game as part of a semester long project. """
                  ),
                  div(cls := "col s12 m12 l12", style := "padding-top: 5px")(
                    raw("""Created the architecture and web stack necessary to keep the code clean and in-line with modern game development standards and best practic""")
                  )
                )
              )
            ),
            dividerNavLink,
            div(cls := "card-content black-text col s12 m12 l12")(
              div(cls := "row", style := "font-weight:300;")(
                div(cls := "col s12 m12 l12", style := "color:#757575; font-size:1.05em; font-weight: 400;")("RAX-SIMULATE SYSTEMS SIMULATION FRAMEWORK"),
                a(cls := "col s12 m12 l12")("FALL 2015 SEMESTER"),
                div(cls := "col s12 m12 l12", style := "padding-bottom: 5px; padding-top:5px")(
                  """Discrete time event simulation framework. Proof of concept for the use of algebraic (case class) data types and functional-modular hybrid concepts in simulation framework programming. """
                )
              )
            )
          )
        )
      )
    ), div(cls := "col s12 m12 l12")(
      div(cls := "card")(
        div(cls := "card-content black-text row")(
          div(i(cls := "material-icons col s12 m2 l1 center-align", style := "font-size:3rem; color:#757575;")("school")),
          div(cls := "col s12 m12 l11")(
            div(cls := "card-title cv-header", style := "color: #757575;")("Education"),
            div(cls := "card-content black-text col s12 m12 l12", style := "padding-bottom:0;")(
              div(cls := "row", style := "font-weight:300;")(
                div(cls := "col s12 m12 l12", style := "color:#757575; font-size:1.05em; font-weight: 400;")("Computer Science Major@STATE UNIVERSITY OF NEW YORK AT OSWEGO"),
                div(cls := "cv-position-block")(
                  a(cls := "col s12 m12 l12")("GRADUATION DATE: DEC 2016"),
                  div(cls := "col s12 m12 l12", style := "padding-bottom: 5px; padding-top:5px")(
                    """GPA: 3.65"""
                  )
                )
              )
            )
          )
        )
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

