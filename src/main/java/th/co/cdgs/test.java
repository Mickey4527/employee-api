package th.co.cdgs;

public class test {
    public static void main(String[] args) {
        Test01 test01 = new Test01();
        test01.setFirstName("Tony");
        test01.setLastName("Stark");
        System.out.println(test01.getFirstName());
        System.out.println(test01.getLastName());
        Test01 test02 = new Test01();
        test02.setFirstName("Michael");
        test02.setLastName("Jackson");
        System.out.println(test02.getFirstName());
        System.out.println(test02.getLastName());
        test01.setFirstName(test02.getFirstName());
        test01.setLastName(test02.getLastName());
        System.out.println(test01.getFirstName());
        System.out.println(test01.getLastName());
    }

}
class Test01{
    private String first_name;
    private String last_name;

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

}