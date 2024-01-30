package th.co.cdgs.department;


import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.PathParam;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import th.co.cdgs.Employee;
import th.co.cdgs.EmployeeService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.WebApplicationException;

@Path("department")
@ApplicationScoped
public class DepartmentResource {

    @Inject
    EntityManager em;

    @Inject
    EmployeeService employeeService;

    @GET
    @Path("{id}")
    public Department get(@PathParam("id") Integer id) {
        Department entity = em.find(Department.class, id);
        if (entity == null) {
            throw new WebApplicationException("Department with id of " + id + " does not exist.",
                    Status.NOT_FOUND);
        }
        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional // คือการบอกว่า method นี้จะเป็นการทำ transaction ซึ่งจะทำให้ method นี้ทำงานเสร็จสิ้น หรือไม่ทำงานเลย ถ้าเกิด error ขึ้น
    // โดยจะทำการ rollback ทั้งหมด ทำให้ข้อมูลใน database ไม่เปลี่ยนแปลง
    // transaction คือการทำงานหลายๆอย่างใน database ในรูปแบบที่ต้องการให้ทำงานเสร็จสิ้นหมด หรือไม่ทำงานเลย ถ้าเกิด error ขึ้น
    public Response delete(@PathParam("id") Integer id){
       List<Employee> emps = employeeService.findEmployeesByDepartment(id);
       boolean hasError = false;

       for(Employee emp: emps){
              try{
                employeeService.deleteEmployee(emp.getId());
              }catch(Exception e){
                hasError = true;
              }
       }

       
       if (!hasError) {
        Department entity = em.find(Department.class, id);
        em.remove(entity);
       }
       else{
           return Response.status(Status.INTERNAL_SERVER_ERROR).build();
       }
       
       return Response.status(Status.OK).build();
    }
}