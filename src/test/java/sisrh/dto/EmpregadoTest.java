package sisrh.dto;

import static org.junit.Assert.*;
import org.junit.Test;

public class EmpregadoTest {

    @Test
    public void testSetMatricula() {
        Empregado emp = new Empregado();
        emp.setMatricula("003");
        assertEquals("003", emp.getMatricula());
    }

    @Test
    public void testSetNome() {
        Empregado emp = new Empregado();
        emp.setNome("Carlos Souza");
        assertEquals("Carlos Souza", emp.getNome());
    }

    @Test
    public void testSetSalario() {
        Empregado emp = new Empregado();
        emp.setSalario(5000.0);
        assertEquals(5000.0, emp.getSalario(), 0.001);
    }

    @Test
    public void testDesligamentoNulo() {
        Empregado emp = new Empregado();
        emp.setDesligamento(null);
        assertNull(emp.getDesligamento());
    }

    @Test
    public void testAdmissaoValida() {
        Empregado emp = new Empregado();
        emp.setAdmissao("01/01/2023");
        assertEquals("01/01/2023", emp.getAdmissao());
    }
}