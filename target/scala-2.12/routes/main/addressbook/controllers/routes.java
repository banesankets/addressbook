// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Skillup/Java Skillup SanketB/Skillup/AddressBook/addressbook/conf/routes
// @DATE:Thu Aug 08 17:05:52 IST 2019

package addressbook.controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final addressbook.controllers.ReverseHomeController HomeController = new addressbook.controllers.ReverseHomeController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final addressbook.controllers.javascript.ReverseHomeController HomeController = new addressbook.controllers.javascript.ReverseHomeController(RoutesPrefix.byNamePrefix());
  }

}
