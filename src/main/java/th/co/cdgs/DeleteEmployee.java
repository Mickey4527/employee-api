package th.co.cdgs;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.core.Response.Status;


@Path("department")
@ApplicationScoped // คือการบอกว่า class นี้จะถูกสร้างเป็น object ครั้งเดียว และจะถูกใช้งานในทุกๆที่ โดยที่ไม่ต้องสร้าง object ใหม่ แต่จะใช้ object เดิม ที่ถูกสร้างไว้แล้ว และถูกเก็บไว้ใน memory ของ application
public class DeleteEmployee {
    
    @Inject // คือการบอกว่าตัวแปรนี้จะถูก inject ค่าเข้ามา โดยที่ไม่ต้องสร้าง object ใหม่ แต่จะใช้ object เดิม ที่ถูกสร้างไว้แล้ว และถูกเก็บไว้ใน memory ของ application
    EntityManager em;

    @DELETE
    @Path("{id}")
    @Transactional // คือการบอกว่า method นี้จะเป็นการทำ transaction ซึ่งจะทำให้ method นี้ทำงานเสร็จสิ้น หรือไม่ทำงานเลย ถ้าเกิด error ขึ้น
    // โดยจะทำการ rollback ทั้งหมด ทำให้ข้อมูลใน database ไม่เปลี่ยนแปลง
    // transaction คือการทำงานหลายๆอย่างใน database ในรูปแบบที่ต้องการให้ทำงานเสร็จสิ้นหมด หรือไม่ทำงานเลย ถ้าเกิด error ขึ้น

    public void deleteEmployee(@PathParam("id") Integer id){
        Employee entity = em.find(Employee.class, id);

        if(entity == null){
            throw new WebApplicationException("Employee with id of " + id + " does not exist.", 
            Status.NOT_FOUND);
        }
        em.remove(entity);

       
    }

}
