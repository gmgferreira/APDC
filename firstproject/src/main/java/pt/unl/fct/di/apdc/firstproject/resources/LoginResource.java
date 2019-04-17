
package pt.unl.fct.di.apdc.firstproject.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

@Path("/login")
public class LoginResource {

	private static final DatastoreService dt = DatastoreServiceFactory.getDatastoreService();

	public LoginResource() {
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";chartset=utf-8")
	public Response register(pt.unl.fct.di.apdc.firstproject.util.RegisterData data) {

		//Transaction txn = dt.beginTransaction();

		try {
			Key userKey = KeyFactory.createKey("User", data.username);
			Entity user = dt.get(userKey);
			if(data.password.equals(user.getProperty("user_pwd")))
				return Response.ok().build() ;
			else 
				return Response.status(Status.BAD_REQUEST).entity("Wrong password").build();
		} catch(EntityNotFoundException e){

			return Response.status(Status.BAD_REQUEST).entity("User doesn't exist").build();

		}

	}


}
