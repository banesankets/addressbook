
package views.html.error

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import play.data._
import play.core.j.PlayFormsMagicForJava._

object error extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[String,Integer,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(uri : String, statusCode : Integer, message : String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Error</title>
		<link href=""""),_display_(/*8.16*/routes/*8.22*/.Assets.at("/public/stylesheets", "home.css")),format.raw/*8.67*/("""" rel="stylesheet" type="text/css">
		<link href="https://fonts.googleapis.com/css?family=ABeeZee" rel="stylesheet">
		<script src=""""),_display_(/*10.17*/routes/*10.23*/.Assets.versioned("javascripts/jquery-3.0.0.min.js")),format.raw/*10.75*/(""""></script>
		<script src=""""),_display_(/*11.17*/routes/*11.23*/.Assets.versioned("javascripts/error.js")),format.raw/*11.64*/(""""></script>
		<script type="text/javascript">
			var context = "/addressbook/";
		</script>
	</head>
	<body>
		<div class="container">
			<div class="title">
				<span id="titleTest">Address Book</span>
			</div>
			<div class="content">
				<center>
					"""),_display_(/*23.7*/if(statusCode == 404)/*23.28*/ {_display_(Seq[Any](format.raw/*23.30*/("""
						"""),format.raw/*24.7*/("""<div id="error">
							404<br>Oops... Page Not Found. 
						</div>
						<img id = "errImg" src= """"),_display_(/*27.33*/routes/*27.39*/.Assets.at("/public/images", "error404.jpg")),format.raw/*27.83*/("""">
					""")))}/*28.8*/else/*28.13*/{_display_(Seq[Any](format.raw/*28.14*/("""
						"""),format.raw/*29.7*/("""<div id="error">
							500<br>Malfunctioning... Internal Server Error.
						</div>
						<img id = "errImg" src= """"),_display_(/*32.33*/routes/*32.39*/.Assets.at("/public/images", "error500.png")),format.raw/*32.83*/("""">
					""")))}),format.raw/*33.7*/("""
					"""),format.raw/*34.6*/("""<div id="errorMsg">
						<br>Requested URL: """),_display_(/*35.27*/uri),format.raw/*35.30*/("""
						"""),format.raw/*36.7*/("""<br>"""),_display_(/*36.12*/message),format.raw/*36.19*/("""
					"""),format.raw/*37.6*/("""</div>
					<div>
						<button onClick="backToHome()" id="goToHome">Home</button>
					</div>
				</center>
			</div>
		</div>
	</body>
</html>"""))
      }
    }
  }

  def render(uri:String,statusCode:Integer,message:String): play.twirl.api.HtmlFormat.Appendable = apply(uri,statusCode,message)

  def f:((String,Integer,String) => play.twirl.api.HtmlFormat.Appendable) = (uri,statusCode,message) => apply(uri,statusCode,message)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Aug 08 17:05:52 IST 2019
                  SOURCE: D:/Skillup/Java Skillup SanketB/Skillup/AddressBook/addressbook/app/views/error/error.scala.html
                  HASH: 49371a3d845931e442de4253beaa8d921b954059
                  MATRIX: 969->1|1117->56|1144->57|1264->151|1278->157|1343->202|1503->335|1518->341|1591->393|1646->421|1661->427|1723->468|2006->725|2036->746|2076->748|2110->755|2238->856|2253->862|2318->906|2345->916|2358->921|2397->922|2431->929|2575->1046|2590->1052|2655->1096|2694->1105|2727->1111|2800->1157|2824->1160|2858->1167|2890->1172|2918->1179|2951->1185
                  LINES: 28->1|33->2|34->3|39->8|39->8|39->8|41->10|41->10|41->10|42->11|42->11|42->11|54->23|54->23|54->23|55->24|58->27|58->27|58->27|59->28|59->28|59->28|60->29|63->32|63->32|63->32|64->33|65->34|66->35|66->35|67->36|67->36|67->36|68->37
                  -- GENERATED --
              */
          