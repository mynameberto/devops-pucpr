package sisrh.dto;

import dto.Employee;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;

public class EmployeeTest {

    private Employee employee;

    @Before
    public void setUp() {
        // Inicializa um funcionário para ser usado nos testes
        employee = new Employee(
                1L,
                "João Silva",
                "joao.silva@empresa.com",
                "Desenvolvedor",
                5000.0,
                LocalDate.of(2020, 1, 15)
        );
    }

    @Test
    public void testIsValidEmail() {
        // Teste para verificar se o email é válido
        assertTrue("O email deve ser válido", employee.isValidEmail());

        // Testando com email inválido
        employee.setEmail("email-invalido");
        assertFalse("O email sem @ ou . deve ser inválido", employee.isValidEmail());

        // Testando com email nulo
        employee.setEmail(null);
        assertFalse("O email nulo deve ser inválido", employee.isValidEmail());
    }

    @Test
    public void testCalculateAnnualSalary() {
        // Teste para calcular o salário anual
        assertEquals("O salário anual deve ser 12 vezes o salário mensal",
                60000.0, employee.calculateAnnualSalary(), 0.001);

        // Testando com salário nulo
        employee.setSalary(null);
        assertEquals("O salário anual deve ser 0 quando o salário mensal for nulo",
                0.0, employee.calculateAnnualSalary(), 0.001);
    }

    @Test
    public void testCalculateYearsOfService() {
        // Teste para calcular anos de serviço
        int expectedYears = LocalDate.now().getYear() - 2020;
        assertEquals("Os anos de serviço devem ser calculados corretamente",
                expectedYears, employee.calculateYearsOfService());

        // Testando com data de contratação nula
        employee.setHiringDate(null);
        assertEquals("Anos de serviço devem ser 0 quando a data de contratação for nula",
                0, employee.calculateYearsOfService());
    }

    @Test
    public void testIsEligibleForBonus() {
        // Verificando elegibilidade para bônus (funcionário com mais de 2 anos)
        assertTrue("Funcionário com mais de 2 anos deve ser elegível para bônus",
                employee.isEligibleForBonus());

        // Testando com funcionário recente (menos de 2 anos)
        employee.setHiringDate(LocalDate.now().minusYears(1));
        assertFalse("Funcionário com menos de 2 anos não deve ser elegível para bônus",
                employee.isEligibleForBonus());
    }

    @Test
    public void testCalculateBonus() {
        // Testando o cálculo de bônus para funcionário com mais de 5 anos
        employee.setHiringDate(LocalDate.now().minusYears(6));
        employee.setSalary(5000.0);
        assertEquals("Bônus para funcionário com 5+ anos deve ser 20% do salário",
                1000.0, employee.calculateBonus(), 0.001);

        // Testando para funcionário com 2-4 anos
        employee.setHiringDate(LocalDate.now().minusYears(3));
        assertEquals("Bônus para funcionário com 2-4 anos deve ser 10% do salário",
                500.0, employee.calculateBonus(), 0.001);

        // Testando para funcionário não elegível
        employee.setHiringDate(LocalDate.now().minusYears(1));
        assertEquals("Bônus deve ser 0 para funcionário não elegível",
                0.0, employee.calculateBonus(), 0.001);
    }
}