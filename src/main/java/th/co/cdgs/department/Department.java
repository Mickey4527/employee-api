package th.co.cdgs.department;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;

@Entity
@Table(name = "department")
@NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d ORDER BY d.id", 
                hints = @jakarta.persistence.QueryHint(name = "org.hibernate.cacheable", value = "true"))

@Cacheable
public class Department {

    @Id
    @SequenceGenerator(name = "department_seq", sequenceName = "department_seq", 
                allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "department_seq")

    private Integer code;

    @Column(name = "name", length = 100)
    private String name;

    public Department() {
    }

    public Department(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode(){
        return code;
    }

    public void setCode(Integer code){
        this.code = code;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

}
