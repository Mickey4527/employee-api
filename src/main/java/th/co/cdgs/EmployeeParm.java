package th.co.cdgs;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Query;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.persistence.EntityManager;

@Path("employee/searchByNativeSql")
@ApplicationScoped

public class EmployeeParm {
    @Inject
    EntityManager em;

    @GET
    public List<Employee> get(@QueryParam("firstName") String firstName, 
                              @QueryParam("lastName") String lastName){
        Query query = em.createNativeQuery("SELECT * FROM employee WHERE first_name = ? AND last_name = ?", Employee.class);
        query.setParameter(1, firstName);
        query.setParameter(2, lastName);

        if(query.getResultList().size() == 0){
            return null;
        }
        return query.getResultList();
    }
}
