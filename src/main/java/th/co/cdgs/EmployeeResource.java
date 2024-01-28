package th.co.cdgs;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("employee")
@ApplicationScoped
public class EmployeeResource {
    @Inject
    EntityManager em;

    @GET
    public List<Employee> get(){
        return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }

    @GET
    @Path("{id}")
    public Employee getSingle(@PathParam("id") Integer id){
        //check database is found
        Employee entity = em.find(Employee.class, id);
        if(entity == null){
            throw new WebApplicationException("employee with id of " + id + " does not exist.", 
            Status.NOT_FOUND);
        }
        return entity;
    }

   
    @POST
    @Transactional
    public Response create(Employee employee){
       if(employee.getId() != null){
           throw new WebApplicationException("Id was invalidly set on request.", Status.BAD_REQUEST);
       }
         em.persist(employee);
         return Response.status(Status.CREATED).entity(employee).build();
    }


    @PUT
    @Path("{id}")
    @Transactional
    public Response update(Integer id, Employee employee){
       Employee entity = em.find(Employee.class, id);
       if(entity == null){
           throw new WebApplicationException("Employee with id of " + id + " does not exist.", 
           Status.NOT_FOUND);
       }
        entity.setDepartment(employee.getDepartment());
        entity.setFirstName(employee.getFirstName());
        entity.setLastName(employee.getLastName());
        entity.setGender(employee.getGender());

        return Response.status(Status.OK).entity(entity).build();
        
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(Integer id){
        Employee entity = em.find(Employee.class, id);
        if(entity == null){
            throw new WebApplicationException("employee with id of " + id + " does not exist.", 
            Status.NOT_FOUND);
        }
        em.remove(entity);
        return Response.status(Status.OK).build();
    }
}

