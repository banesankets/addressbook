package addressbook.controllers.error;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import com.google.inject.Inject;

import addressbook.controllers.routes;
import addressbook.utility.Definitions;
import play.http.HttpErrorHandler;
import play.mvc.Action;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.mvc.Results;
import views.html.error.error;

import com.typesafe.config.Config;

/**
 * ErrorHandler class
 */
public class ErrorHandler implements HttpErrorHandler
{
	/**
	 * configuration object
	 */
	private final Config _config;

	/**
	 * constructor
	 *
	 * @param config - configuration object
	 */
	@Inject
	public ErrorHandler(Config config)
	{
		_config = config;
	}

	/**
	 * handle client side error
	 *
	 * @param request - Request Obj
	 * @param statusCode - stautus code
	 * @param message - message
	 */
	@Override
	public CompletionStage<Result> onClientError(RequestHeader request, int statusCode, String message)
	{
		CompletionStage<Result> stage = null;
		String reqUri = request.uri();
		String context = _config.getString("play.http.context");
		if(!reqUri.contains(context))
		{
			reqUri = context;
			stage =  CompletableFuture.completedFuture(Results.redirect(reqUri));
		}
		else if(reqUri.startsWith(context) && reqUri.endsWith(context))
		{
			stage = CompletableFuture.completedFuture(Results.redirect(routes.HomeController.getContactList()));
		}
		else
		{
			if(message.isEmpty())
			{
				message = Definitions.CLIENT_SIDE_ERROR; 
			}
			stage = CompletableFuture.completedFuture(Results.notFound(error.render(reqUri, statusCode, message)));
		}
		return stage;
	}

	/**
	 * handle server side error
	 *
	 * @param request - request
	 *  @param ex - throwable obj 
	 */
	@Override
	public CompletionStage<Result> onServerError(RequestHeader request, Throwable ex)
	{
		int statusCode = 500;
		String reqUri = request.uri();
		String message =Definitions.SERVER_SIDE_ERROR;
		return CompletableFuture.completedFuture(Results.notFound(error.render(reqUri, statusCode, message)));
	}
}