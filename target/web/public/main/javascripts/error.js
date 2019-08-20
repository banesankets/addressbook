/**
 * redirect to home page
 */
function backToHome() {
	var url = location.protocol + "//" + location.host + context + "home";
	window.location.assign(url);
}