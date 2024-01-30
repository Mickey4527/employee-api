package th.co.cdgs;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Query;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.persistence.EntityManager;

@Path("employee/search")
@ApplicationScoped

public class EmployeeParm {
    @Inject
    EntityManager em;

    @GET
    public List<Employee> searchByNativeSql(@QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName, @QueryParam("departmentId") Integer departmentId) {
        Query query = em.createNativeQuery("SELECT * FROM employee WHERE first_name = ? AND last_name = ? AND department = ?",
                Employee.class);
        query.setParameter(1, firstName);
        query.setParameter(2, lastName);
        query.setParameter(3, departmentId);
        return query.getResultList();
}
}
