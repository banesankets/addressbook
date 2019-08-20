
package views.html

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

object home extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[List[addressbook.domain.Person],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(people : List[addressbook.domain.Person]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.44*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>AddressBook</title>
		<link href=""""),_display_(/*7.16*/routes/*7.22*/.Assets.at("/public/stylesheets", "home.css")),format.raw/*7.67*/("""" rel="stylesheet" type="text/css">
		<link href="https://fonts.googleapis.com/css?family=ABeeZee" rel="stylesheet">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<script src=""""),_display_(/*10.17*/routes/*10.23*/.Assets.versioned("javascripts/jquery-3.0.0.min.js")),format.raw/*10.75*/(""""></script>
		<script src=""""),_display_(/*11.17*/routes/*11.23*/.Assets.versioned("javascripts/home.js")),format.raw/*11.63*/(""""></script>
		<script>
			var persons = [];
			"""),_display_(/*14.5*/for(person <- people) yield /*14.26*/{_display_(Seq[Any](format.raw/*14.27*/("""	
				"""),format.raw/*15.5*/("""var person = """),format.raw/*15.18*/("""{"""),format.raw/*15.19*/("""
					"""),format.raw/*16.6*/("""id : """"),_display_(/*16.13*/person/*16.19*/.getId()),format.raw/*16.27*/("""",
					fname: """"),_display_(/*17.15*/person/*17.21*/.getFname()),format.raw/*17.32*/("""",
					lname : """"),_display_(/*18.16*/person/*18.22*/.getLname()),format.raw/*18.33*/("""",
					mobile : """"),_display_(/*19.17*/person/*19.23*/.getMobile()),format.raw/*19.35*/("""",
					telephone : """"),_display_(/*20.20*/person/*20.26*/.getTelephone()),format.raw/*20.41*/("""",
					email : """"),_display_(/*21.16*/person/*21.22*/.getEmail()),format.raw/*21.33*/("""",
					address : """"),_display_(/*22.18*/person/*22.24*/.getAddress),format.raw/*22.35*/(""""
				"""),format.raw/*23.5*/("""}"""),format.raw/*23.6*/(""";
				persons.push(person);
			""")))}),format.raw/*25.5*/("""
			"""),format.raw/*26.4*/("""var context = "/addressbook/";
		</script>
	</head>
	<body onload = "loadNames()" ondragstart="return false;" ondrop="return false;">
		<div class="container">
			<div class="title">
				<span id="titleText"><i class="fa fa-address-book-o"></i>  Address Book</span>
			</div>
			<div class="content">
				<div class="contactSearch">
					<input type="text" autocomplete="off" id="search" oninput="search()" placeholder="Search..">
					<ul id="contactList"></ul>
				</div>
				<div class="displayDetails">
					<input type=hidden id="id">
					<div class="lable" id="name"></div>
					<div class="details">
						<table class="infoTable">
							<tr>
								<td><span class="lable"><b>Mobile</b></span></td>
								<td><span class="value" id="mobileValue"></span></td>
							</tr>
							<tr>
								<td><span class="lable"><b>Telephone</b></span></td>
								<td><span class="value" id="telephoneValue"></span></td>
							</tr>
							<tr>
								<td><span class="lable"><b>Email</b></span></td>
								<td><span class="value" id="emailValue"></span></td>
							</tr>
							<tr>
								<td><span class="lable"><b>Address</b></span></td>
								<td><span class="value" id="addressValue"></span></td>
							</tr>
						</table>
					</div>
				</div>
				<div class="operations">
					<h2>Operations</h2>
					<ul id="dbOperations">
						<li><button onclick="displayPopup('add')" id="addContact"><i class="fa fa-plus"></i>      Add</button></li>
						<li><button onclick="displayPopup('edit')" id="editContact"><i class="fa fa-pencil"></i>       Edit</button></li>
						<li><button onclick="deleteContact()" id="deleteContact"><i class="fa fa-times"></i> Delete</button></li>
					</ul>
				</div>
				<div class="options">
					<h2>Options</h2>
					<ul id="csvOptions">
						<li>
								<input id="importFile" type="file" accept=".csv" onchange="readCSVFile(event);"/>
								<button onclick="importCSV()"><i class="fa fa-upload"></i> Import CSV</button>
						</li>
						<li>
							<button id="exportFile" onclick="exportCSV()"><i class="fa fa-download"></i> Export CSV</button>
						</li>
					</ul>
				</div>
				<div class="popup">
					<div class="popupWindow animate">
						<div class="popupTitle"><span id="popupTitleText">Contact details</span></div>
						<div>
							<table >
								<tr>
									<td><input type="hidden" id="formId"></td>
								</tr>
								<tr>
									<td><span class="lable">First Name<span class="required">*</span></span></td>
									<td><input type="text" autocomplete="off" placeholder="Enter First Name" id="fname" oninput="isValueChanged()"></td>
								</tr>
								<tr>
									<td></td>
									<td><span class="error" id="errfname"></span><td>
								</tr>
								<tr>
									<td><span class="lable">Last Name<span class="required">*</span></span></td>
									<td><input type="text" autocomplete="off" placeholder="Enter Last Name" id="lname" oninput="isValueChanged()"></td>
								</tr>
								<tr>
									<td></td>
									<td><span class="error" id="errlname"></span><td>
								</tr>
								<tr>
									<td><span class="lable">Mobile<span class="required">*</span></span></td>
									<td><input type="text" autocomplete="off" placeholder="Enter Mobile No." id="mobile" oninput="isValueChanged()"></td>
								</tr>
								<tr>
									<td></td>
									<td><span class="error" id="errmobile"></span><td>
								</tr>
								<tr>
									<td><span class="lable">Telephone</span></td>
									<td><input type="text" autocomplete="off" placeholder="Enter Telephone No." id="telephone" oninput="isValueChanged()"></td>
								</tr>
								<tr>
									<td></td>
									<td><span class="error" id="errtelephone"></span><td>
								</tr>
								<tr>
									<td><span class="lable">Email</span></td>
									<td><input type="email" autocomplete="off" placeholder="Enter Email" id="email" oninput="isValueChanged()"></td>
								</tr>
								<tr>
									<td></td>
									<td><span class="error" id="erremail"></span><td>
								</tr>
								<tr>						
									<td><span class="lable">Address<span class="required">*</span></span></td>
									<td><textarea rows="4" placeholder="Enter Address" cols="50" id="address" oninput="isValueChanged()"></textarea></td>
								</tr>
								<tr>
									<td></td>
									<td><span class="error" id="erraddress"></span><td>
								</tr>
					</table>
							<div id=modalButtons>
								<button id="modify"onclick="return modifyContact()">Save</button>
								<button id="undo" onclick="undoChanges()">Undo</button>
								<button id="cancel" onclick="closePopup()">Cancel</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>"""))
      }
    }
  }

  def render(people:List[addressbook.domain.Person]): play.twirl.api.HtmlFormat.Appendable = apply(people)

  def f:((List[addressbook.domain.Person]) => play.twirl.api.HtmlFormat.Appendable) = (people) => apply(people)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Aug 08 17:05:52 IST 2019
                  SOURCE: D:/Skillup/Java Skillup SanketB/Skillup/AddressBook/addressbook/app/views/home.scala.html
                  HASH: 8533c690ed87924678cae94a0e840234041a9e5d
                  MATRIX: 972->1|1109->43|1137->45|1268->150|1282->156|1347->201|1626->453|1641->459|1714->511|1770->540|1785->546|1846->586|1923->637|1960->658|1999->659|2033->666|2074->679|2103->680|2137->687|2171->694|2186->700|2215->708|2260->726|2275->732|2307->743|2353->762|2368->768|2400->779|2447->799|2462->805|2495->817|2545->840|2560->846|2596->861|2642->880|2657->886|2689->897|2737->918|2752->924|2784->935|2818->942|2846->943|2910->977|2942->982
                  LINES: 28->1|33->1|34->2|39->7|39->7|39->7|42->10|42->10|42->10|43->11|43->11|43->11|46->14|46->14|46->14|47->15|47->15|47->15|48->16|48->16|48->16|48->16|49->17|49->17|49->17|50->18|50->18|50->18|51->19|51->19|51->19|52->20|52->20|52->20|53->21|53->21|53->21|54->22|54->22|54->22|55->23|55->23|57->25|58->26
                  -- GENERATED --
              */
          