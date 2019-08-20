// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Skillup/Java Skillup SanketB/Skillup/AddressBook/addressbook/conf/routes
// @DATE:Thu Aug 08 17:05:52 IST 2019

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_1: addressbook.controllers.HomeController,
  // @LINE:14
  Assets_0: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_1: addressbook.controllers.HomeController,
    // @LINE:14
    Assets_0: controllers.Assets
  ) = this(errorHandler, HomeController_1, Assets_0, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_1, Assets_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """home""", """addressbook.controllers.HomeController.getContactList"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """home/insert""", """addressbook.controllers.HomeController.insertContact"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """home/update""", """addressbook.controllers.HomeController.updateContact"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """home/delete""", """addressbook.controllers.HomeController.deleteContact"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """home/import""", """addressbook.controllers.HomeController.importCSV"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """home/export""", """addressbook.controllers.HomeController.exportCSV"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """stylesheets/""" + "$" + """file<.+>""", """controllers.Assets.at(path:String = "/public/stylesheets", file:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """javascripts/""" + "$" + """file<.+>""", """controllers.Assets.at(path:String = "/public/javascripts", file:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """images/""" + "$" + """file<.+>""", """controllers.Assets.at(path:String = "/public/images", file:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val addressbook_controllers_HomeController_getContactList0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("home")))
  )
  private[this] lazy val addressbook_controllers_HomeController_getContactList0_invoker = createInvoker(
    HomeController_1.getContactList,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "addressbook.controllers.HomeController",
      "getContactList",
      Nil,
      "GET",
      this.prefix + """home""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val addressbook_controllers_HomeController_insertContact1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("home/insert")))
  )
  private[this] lazy val addressbook_controllers_HomeController_insertContact1_invoker = createInvoker(
    HomeController_1.insertContact,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "addressbook.controllers.HomeController",
      "insertContact",
      Nil,
      "POST",
      this.prefix + """home/insert""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val addressbook_controllers_HomeController_updateContact2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("home/update")))
  )
  private[this] lazy val addressbook_controllers_HomeController_updateContact2_invoker = createInvoker(
    HomeController_1.updateContact,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "addressbook.controllers.HomeController",
      "updateContact",
      Nil,
      "POST",
      this.prefix + """home/update""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val addressbook_controllers_HomeController_deleteContact3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("home/delete")))
  )
  private[this] lazy val addressbook_controllers_HomeController_deleteContact3_invoker = createInvoker(
    HomeController_1.deleteContact,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "addressbook.controllers.HomeController",
      "deleteContact",
      Nil,
      "POST",
      this.prefix + """home/delete""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val addressbook_controllers_HomeController_importCSV4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("home/import")))
  )
  private[this] lazy val addressbook_controllers_HomeController_importCSV4_invoker = createInvoker(
    HomeController_1.importCSV,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "addressbook.controllers.HomeController",
      "importCSV",
      Nil,
      "POST",
      this.prefix + """home/import""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val addressbook_controllers_HomeController_exportCSV5_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("home/export")))
  )
  private[this] lazy val addressbook_controllers_HomeController_exportCSV5_invoker = createInvoker(
    HomeController_1.exportCSV,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "addressbook.controllers.HomeController",
      "exportCSV",
      Nil,
      "POST",
      this.prefix + """home/export""",
      """""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_Assets_versioned6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned6_invoker = createInvoker(
    Assets_0.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:15
  private[this] lazy val controllers_Assets_at7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("stylesheets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_at7_invoker = createInvoker(
    Assets_0.at(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "at",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """stylesheets/""" + "$" + """file<.+>""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_Assets_at8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("javascripts/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_at8_invoker = createInvoker(
    Assets_0.at(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "at",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """javascripts/""" + "$" + """file<.+>""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_Assets_at9_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("images/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_at9_invoker = createInvoker(
    Assets_0.at(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "at",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """images/""" + "$" + """file<.+>""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case addressbook_controllers_HomeController_getContactList0_route(params@_) =>
      call { 
        addressbook_controllers_HomeController_getContactList0_invoker.call(HomeController_1.getContactList)
      }
  
    // @LINE:7
    case addressbook_controllers_HomeController_insertContact1_route(params@_) =>
      call { 
        addressbook_controllers_HomeController_insertContact1_invoker.call(HomeController_1.insertContact)
      }
  
    // @LINE:8
    case addressbook_controllers_HomeController_updateContact2_route(params@_) =>
      call { 
        addressbook_controllers_HomeController_updateContact2_invoker.call(HomeController_1.updateContact)
      }
  
    // @LINE:9
    case addressbook_controllers_HomeController_deleteContact3_route(params@_) =>
      call { 
        addressbook_controllers_HomeController_deleteContact3_invoker.call(HomeController_1.deleteContact)
      }
  
    // @LINE:10
    case addressbook_controllers_HomeController_importCSV4_route(params@_) =>
      call { 
        addressbook_controllers_HomeController_importCSV4_invoker.call(HomeController_1.importCSV)
      }
  
    // @LINE:11
    case addressbook_controllers_HomeController_exportCSV5_route(params@_) =>
      call { 
        addressbook_controllers_HomeController_exportCSV5_invoker.call(HomeController_1.exportCSV)
      }
  
    // @LINE:14
    case controllers_Assets_versioned6_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned6_invoker.call(Assets_0.versioned(path, file))
      }
  
    // @LINE:15
    case controllers_Assets_at7_route(params@_) =>
      call(Param[String]("path", Right("/public/stylesheets")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at7_invoker.call(Assets_0.at(path, file))
      }
  
    // @LINE:16
    case controllers_Assets_at8_route(params@_) =>
      call(Param[String]("path", Right("/public/javascripts")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at8_invoker.call(Assets_0.at(path, file))
      }
  
    // @LINE:17
    case controllers_Assets_at9_route(params@_) =>
      call(Param[String]("path", Right("/public/images")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at9_invoker.call(Assets_0.at(path, file))
      }
  }
}
