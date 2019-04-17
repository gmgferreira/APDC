package pt.unl.fct.di.apdc.firstproject.resources;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;


@Path("/register")
public class RegisterResource {

	private static final DatastoreService dt = DatastoreServiceFactory.getDatastoreService();
	
	public RegisterResource() {
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
			return Response.status(Status.BAD_REQUEST).entity("User already exists").build();
		} catch(EntityNotFoundException e){
			Entity user = new Entity("User", data.username);
			user.setProperty("user_name", data.username);
			user.setProperty("user_pwd", data.password);
			user.setProperty("user_email", data.mail);
			user.setProperty("user_address", data.address);
			user.setProperty("user_local", data.localidade);
			user.setProperty("user_codPost", data.codPostal);
			user.setProperty("user_phone", data.phone);
			user.setProperty("user_mobile", data.mobile);
			user.setUnindexedProperty("user_creation_time", new Date());
			dt.put(user);
			//LOG.info("User registered" + data.username);
			return Response.ok().build();
			
		}

	}

}
