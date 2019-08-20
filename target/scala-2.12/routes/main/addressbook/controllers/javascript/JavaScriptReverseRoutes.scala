// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Skillup/Java Skillup SanketB/Skillup/AddressBook/addressbook/conf/routes
// @DATE:Thu Aug 08 17:05:52 IST 2019

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package addressbook.controllers.javascript {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def exportCSV: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "addressbook.controllers.HomeController.exportCSV",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "home/export"})
        }
      """
    )
  
    // @LINE:9
    def deleteContact: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "addressbook.controllers.HomeController.deleteContact",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "home/delete"})
        }
      """
    )
  
    // @LINE:10
    def importCSV: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "addressbook.controllers.HomeController.importCSV",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "home/import"})
        }
      """
    )
  
    // @LINE:7
    def insertContact: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "addressbook.controllers.HomeController.insertContact",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "home/insert"})
        }
      """
    )
  
    // @LINE:8
    def updateContact: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "addressbook.controllers.HomeController.updateContact",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "home/update"})
        }
      """
    )
  
    // @LINE:6
    def getContactList: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "addressbook.controllers.HomeController.getContactList",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "home"})
        }
      """
    )
  
  }


}
