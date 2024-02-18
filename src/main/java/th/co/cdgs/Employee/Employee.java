package th.co.cdgs.Employee;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.QueryHint;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import th.co.cdgs.department.Department;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "employee")
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e ORDER BY e.id", 
                hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
public class Employee {

    @Id
    @SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq", 
                allocationSize = 1, initialValue = 15)
    @GeneratedValue(generator = "employee_seq")
    private Integer id;

    @Column(length = 40, name = "first_name")
    private String firstname;

    @Column(length = 100)
    private String last_name;

    private String fullname;

    @ManyToOne(fetch = FetchType.EAGER) // ใช้ LAZY จะไม่ดึงข้อมูลมาทั้งหมด
    @JoinColumn(name = "department", referencedColumnName = "code")
    private Department department;

    @Column(length = 1)
    private String gender;

    @Version
    private Integer version;


    public Employee() {
    }

    public Employee(String firstname, String last_name, Department department,String gender) {
        this.firstname = firstname;
        this.last_name = last_name;
        this.department = department;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getFullName() {
        return fullname;
    }

    public void setFullName(String fullname) {
        this.fullname = fullname;
    }

    public Department getDepartment(){
        return department;
    }

    public void setDepartment(Department department){
        this.department = department;
    }
    
    public String getGender(){
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getVersion(){
        return version;
    }

    public void setVersion(Integer version){
        this.version = version;
    }
}