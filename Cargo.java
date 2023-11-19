import java.util.ArrayList;
import java.util.List;

public class Cargo {
    private String titulo;
    private double salario;
    private List<String> promocoes;

    public Cargo(String titulo, double salario, List<String> promocoes) {
        this.titulo = titulo;
        this.salario = salario;
        this.promocoes = promocoes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public List<Cargo> getPromocoes() {
        List<Cargo> lista = new ArrayList<Cargo>();

        for (String promocao : promocoes) {
            for (Cargo cargo : Sistema.cargos) {
                if (promocao.equals(cargo.getTitulo())) {
                    lista.add(cargo);
                }
            }
        }

        return lista;
    }
}