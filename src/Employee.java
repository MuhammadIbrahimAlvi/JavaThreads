import java.util.Date;

public class Employee {
    private String name;
    private Long age;
    private String department;
    private Long salary;
    private Date date;

    public static class Builder {
        private final String name;
        private final Long age;
        private final String department;
        private final Long salary;
        private final Date date;

        public Builder(String name, Long age, String department, Long salary, Date date) {
            this.name = name;
            this.age = age;
            this.department = department;
            this.salary = salary;
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public Long getAge() {
            return age;
        }

        public String getDepartment() {
            return department;
        }

        public Long getSalary() {
            return salary;
        }

        public Date getDate() {
            return date;
        }
    }
    private Employee(Builder builder) {
        name = builder.name;
        age = builder.age;
        salary = builder.salary;
        department = builder.department;
        date = builder.date;
    }
}
