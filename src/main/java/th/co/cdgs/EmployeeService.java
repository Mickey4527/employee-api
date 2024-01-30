package th.co.cdgs;

import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional.TxType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EmployeeService {
    @Inject
    private EntityManager em;

    @Transactional(TxType.NOT_SUPPORTED)
    public List<Employee> findEmployeesByDepartment(Integer departmentId){
        String jpql = "SELECT e FROM Employee e WHERE e.department.id = :departmentId";
        Query query = em.createQuery(jpql, Employee.class);
        query.setParameter("departmentId", departmentId);
        
        return query.getResultList();
    }

    @Transactional(TxType.REQUIRES_NEW)
    public void deleteEmployee(Integer employeeId){
        try {
            Employee entity = em.getReference(Employee.class, employeeId);
            em.remove(entity);
        } catch (Exception e) {
            // Handle or log the exception properly
            e.printStackTrace();
        }
    }
}
