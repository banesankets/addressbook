// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Skillup/Java Skillup SanketB/Skillup/AddressBook/addressbook/conf/routes
// @DATE:Thu Aug 08 17:05:52 IST 2019

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package addressbook.controllers {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def exportCSV(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "home/export")
    }
  
    // @LINE:9
    def deleteContact(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "home/delete")
    }
  
    // @LINE:10
    def importCSV(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "home/import")
    }
  
    // @LINE:7
    def insertContact(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "home/insert")
    }
  
    // @LINE:8
    def updateContact(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "home/update")
    }
  
    // @LINE:6
    def getContactList(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "home")
    }
  
  }


}
