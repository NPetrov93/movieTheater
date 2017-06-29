package javaEEproject.movieTheater.services;

import java.net.HttpURLConnection;

import javaEEproject.movieTheater.dao.UserDAO;
import javaEEproject.movieTheater.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("user")
public class UserManager {

	private static final Response RESPONSE_OK = Response.ok().build();
	
	@Inject
	private UserDAO userDAO;
	
	@Inject
    private UserContext context;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void RegisterUser(User user)
	{
		userDAO.addUser(user);
		context.setCurrentUser(user);
	}
	
	@Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(User user) {
        User thisUser = userDAO.validateUserCredentials(user.getUserName(), user.getPassword());
        if (thisUser == null) {
            return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).build();
        }
        
        context.setCurrentUser(thisUser);
        return RESPONSE_OK;
    }
    
    @Path("authenticated")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response isAuthenticated() {
        if (context.getCurrentUser() == null) {
            return Response.status(HttpURLConnection.HTTP_NOT_FOUND).build();
        }
        return RESPONSE_OK;
    }

    @Path("current")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    public String getUser() {
        if (context.getCurrentUser() == null) {
            return null;
        }
        return context.getCurrentUser().getUserName();
    }

	@Path("logout")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	public void logoutUser() {
		context.setCurrentUser(null);
	}
	
}
