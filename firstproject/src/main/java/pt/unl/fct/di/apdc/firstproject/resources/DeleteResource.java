package pt.unl.fct.di.apdc.firstproject.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

@Path("/delete")
public class DeleteResource {

	public DeleteResource() {
	}

	private static final DatastoreService dt = DatastoreServiceFactory.getDatastoreService();

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";chartset=utf-8")
	public Response Delete(pt.unl.fct.di.apdc.firstproject.util.UpdateData data) {

		try {
			Key userKey = KeyFactory.createKey("User", data.username);
			Entity user = dt.get(userKey);
			dt.delete(userKey);
			return Response.ok().build();
		} catch(EntityNotFoundException e){

			return Response.status(Status.BAD_REQUEST).entity("User doesn't exist").build();

		}
	}



}
