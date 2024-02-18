package th.co.cdgs;

import java.util.Date;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Consumes;

@Path("dateTime")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class dateTime {

    @GET
    @Path("currentDate")
    public Date currentDate() {
        return new Date();
    }

    @POST
    @Path("add45Date")
    @Transactional
    public Date add45Date(Date inputDate) {
        return new Date(inputDate.getTime() + 45 * 24 * 60 * 60 * 1000);
    }
}
