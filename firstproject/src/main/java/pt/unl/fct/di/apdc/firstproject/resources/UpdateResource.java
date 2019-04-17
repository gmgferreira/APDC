package pt.unl.fct.di.apdc.firstproject.resources;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
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

@Path("/update")
public class UpdateResource {
	
	private static final DatastoreService dt = DatastoreServiceFactory.getDatastoreService();
	
	public UpdateResource() {
	}
	

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";chartset=utf-8")
	public Response Update(pt.unl.fct.di.apdc.firstproject.util.UpdateData data) {

		try {
			Key userKey = KeyFactory.createKey("User", data.username);
			Entity user = dt.get(userKey);
			user.setProperty("user_email", data.mail);
			user.setProperty("user_address", data.address);
			user.setProperty("user_local", data.localidade);
			user.setProperty("user_codPost", data.codPostal);
			user.setProperty("user_phone", data.phone);
			user.setProperty("user_mobile", data.mobile);
			dt.put(user);
			return Response.ok().build();
		} catch(EntityNotFoundException e){

			return Response.status(Status.BAD_REQUEST).entity("User doesn't exists").build();
			
		}
	}


}
