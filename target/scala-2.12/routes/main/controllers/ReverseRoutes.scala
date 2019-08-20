// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Skillup/Java Skillup SanketB/Skillup/AddressBook/addressbook/conf/routes
// @DATE:Thu Aug 08 17:05:52 IST 2019

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:14
package controllers {

  // @LINE:14
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def at(path:String, file:String): Call = {
    
      (path: @unchecked, file: @unchecked) match {
      
        // @LINE:15
        case (path, file) if path == "/public/stylesheets" =>
          implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public/stylesheets"))); _rrc
          Call("GET", _prefix + { _defaultPrefix } + "stylesheets/" + implicitly[play.api.mvc.PathBindable[String]].unbind("file", file))
      
        // @LINE:16
        case (path, file) if path == "/public/javascripts" =>
          implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public/javascripts"))); _rrc
          Call("GET", _prefix + { _defaultPrefix } + "javascripts/" + implicitly[play.api.mvc.PathBindable[String]].unbind("file", file))
      
        // @LINE:17
        case (path, file) if path == "/public/images" =>
          implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public/images"))); _rrc
          Call("GET", _prefix + { _defaultPrefix } + "images/" + implicitly[play.api.mvc.PathBindable[String]].unbind("file", file))
      
      }
    
    }
  
    // @LINE:14
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
