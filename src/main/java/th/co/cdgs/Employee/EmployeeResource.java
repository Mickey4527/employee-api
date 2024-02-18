package th.co.cdgs.Employee;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.persistence.Query;
import th.co.cdgs.department.Department;


@Path("employee")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @Inject
    EntityManager em;
    @Inject
    EmployeeService employeeService;

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
    public Employee update(Integer id, Employee employee) {

            Employee entity = em.find(Employee.class, id);

            if (entity == null) {
                throw new WebApplicationException("Employee with id of " + id + " does not exist.", 404);
            }
    
            entity.setFirstName(employee.getFirstName());
            entity.setLastName(employee.getLastName());
            entity.setGender(employee.getGender());
            employeeService.changeDepartment(entity, employee);
        
    
            return entity;

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

    @GET
    @Path("search")
    public List<Employee> searchByNativeSql(Employee condition, @QueryParam("departmentId") Integer departmentId){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, first_name, last_name, concat(first_name,' ',last_name) fullname, department, gender ");
        sql.append("FROM employee ");
        sql.append("WHERE 1=1 ");

        if(condition.getId() != null){
            sql.append("AND id = " + condition.getId() + " ");
        }
        if(condition.getFirstName() != null){
            sql.append("AND first_name = '" + condition.getFirstName() + "' ");
        }
        if(condition.getLastName() != null){
            sql.append("AND last_name = '" + condition.getLastName() + "' ");
        }
        if(departmentId != null){
            sql.append("AND department = '" + departmentId + "' ");
        }

        if(condition.getGender() != null){
            sql.append("AND gender = '" + condition.getGender() + "' ");
        }

        Query query = em.createNativeQuery(sql.toString());
        Employee emp = new Employee();
        List<Employee> result = new ArrayList<>();
        List<Object[]> resultList = query.getResultList();
        for (Object[] row : resultList) {
            emp.setId((Integer) row[0]);
            emp.setFirstName((String) row[1]);
            emp.setLastName((String) row[2]);
            emp.setFullName((String) row[3]);
            emp.setDepartment((Department) em.find(Department.class, row[4]));
            emp.setGender((String) row[5]);
            result.add(emp);
        }

        return result;
    }

    @PATCH
    @Path("{id}")
    public Response changeDepartment(Integer id, Employee employee){
        Employee entity = em.find(Employee.class, id);
        employeeService.changeDepartment(entity, employee);
        return Response.ok(entity).build();
    }
}

