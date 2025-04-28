package dto;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private Long id;
    private String name;
    private String email;
    private String position;
    private Double salary;
    private LocalDate hiringDate;

    public Employee() {
    }

    public Employee(Long id, String name, String email, String position, Double salary, LocalDate hiringDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.position = position;
        this.salary = salary;
        this.hiringDate = hiringDate;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    // Métodos para testar
    public boolean isValidEmail() {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return email.contains("@") && email.contains(".");
    }

    public Double calculateAnnualSalary() {
        if (salary == null) {
            return 0.0;
        }
        return salary * 12;
    }

    public int calculateYearsOfService() {
        if (hiringDate == null) {
            return 0;
        }
        return LocalDate.now().getYear() - hiringDate.getYear();
    }

    public boolean isEligibleForBonus() {
        int yearsOfService = calculateYearsOfService();
        return yearsOfService >= 2;
    }

    public Double calculateBonus() {
        if (!isEligibleForBonus() || salary == null) {
            return 0.0;
        }
        int yearsOfService = calculateYearsOfService();
        if (yearsOfService >= 5) {
            return salary * 0.2; // 20% para funcionários com 5+ anos
        } else {
            return salary * 0.1; // 10% para funcionários com 2-4 anos
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", hiringDate=" + hiringDate +
                '}';
    }
}
