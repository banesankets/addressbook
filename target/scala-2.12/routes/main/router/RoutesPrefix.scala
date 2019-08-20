// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Skillup/Java Skillup SanketB/Skillup/AddressBook/addressbook/conf/routes
// @DATE:Thu Aug 08 17:05:52 IST 2019


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
