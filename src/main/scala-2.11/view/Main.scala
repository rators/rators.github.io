package view

import view.Components._

import scalatags.Text.all._
import scalatags.Text.tags2.title

/**
  * The main index view
  */
object Main extends View {
  def mainLayout(navTitle: String, pageContent: Seq[Modifier] = Seq.empty, topics: Seq[Modifier] = Seq.empty) = {
    html(
      head(
        title(s"Rafael Torres | $navTitle"),
        link(href := "http://fonts.googleapis.com/icon?family=Material+Icons", rel := "stylesheet"),
        meta(name := "viewport", content := "width=device-width, initial-scale=1"),
        link(`type` := "text/css", rel := "stylesheet", href := "https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css",
          media := "screen,projection"),
        link(href := "prism.css", rel := "stylesheet")
      ),
      body(
        header(sideNav(navTitle)),
        main(
          div(cls := "row")(
            div(cls := "section col s12 m12 l12")(
              pageContent,
              topics
            )
          )
        ),
        pageFooter("Contact Info",
          Seq("Github" -> "https://github.com/rators",
            "Linkedin" -> "https://www.linkedin.com/in/rafael-torres-60846bb0")),
        script(`type` := "text/javascript", src := "http://cdn.jsdelivr.net/jquery/2.1.1/jquery.js"),
        script(src := "https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"),
        script(`type` := "text/javascript", src := "js/personal_site-fastopt.js"),
        script(src := "js/prism.js"),
        script(`type` := "text/javascript")(
          """
            |  $(document).ready(function(){
            |    $('.button-collapse').sideNav();
            |    $('.scrollspy').scrollSpy();
            |    $('.toc-wrapper').pushpin({
            |           top: $('.toc-wrapper').offset().top});
            |console.log($(document).height() / 2)
            |  });
          """.stripMargin
        )
      )
    )
  }

  val dataActivates = "data-activates".attr
  val nav = "nav".tag
  val main = "main".tag
  val code = "code".voidTag

  def sideNav(navTitle: String) = div(
    nav(cls := "top-bar white")(
      a(href := "#", dataActivates := "slide-out", cls := "button-collapse", style := "color: black;")(
        i(cls := "mdi-navigation-menu", style := "margin-left: 15px;")
      ),
      div(cls := "container")(
        div(cls := "nav-wrapper center-align")(
          a(cls := "brand-logo")(code(navTitle)),
          ul(id := "slide-out", cls := "side-nav fixed")(
            h1("RT")(color := "black", style := "; color:black;"),
            dividerNavLink,
            li(backgroundColor := (if (navTitle == "About") "#c2d2dc" else ""))(a(href := "aboutme.html")("About")),
            dividerNavLink,
            li(backgroundColor := (if (navTitle == "Raf's Blog") "#c2d2dc" else ""))(a(href := "work.html")("Blog")),
            dividerNavLink,
            li(backgroundColor := (if (navTitle == "") "#c2d2dc" else ""))(a(href := "#!")("Contact Info | Resume")),
            dividerNavLink
          )
        )
      )
    ),
    dividerTop
  )

  def pageFooter(footContent: String, links: Seq[(String, String)]) =
    footer(cls := "page-footer")(
      div(cls := "container")(
        div(cls := "row")(
          div(cls := "col l6 s12")(
            h5(cls := "black-text")(
              footContent
            ),
            ul(
              li(a(cls := "grey-text text-darken-1", href := """mailto:rafaeltrrs112@gmail.com?Subject=Web Contact""")("Email")))
          ),
          div(cls := "col l4 offset-l2 s12")(
            h5(cls := "black-text")("Connect"),
            for ((text, url) <- links) yield {
              li(a(cls := "grey-text text-darken-1", href := url)(text))
            }
          )
        )
      ),
      div(cls := "footer-copyright black-text")(
        div(cls := "container")(raw("2015 Copyright"))
      )
    )

  override def render: String = mainLayout("Rafael Torres").render
}


