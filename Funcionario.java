import java.util.Random;

public class Funcionario {

    private String nome;
    private String cpf;
    private String matricula;
    private Cargo cargo;
    private BancoDeHoras banco;

    public Funcionario(String nome, String cpf) {
        setNome(nome);
        setCpf(cpf);
        setMatricula(GerarMatricula());
    }

    public Funcionario(String nome, String cpf, Cargo cargo) {
        setNome(nome);
        setCpf(cpf);
        setMatricula(GerarMatricula());
        setCargo(cargo);
    }

    // ------------------------

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public BancoDeHoras getBanco() {
        return banco;
    }

    public void setBanco(BancoDeHoras banco) {
        this.banco = banco;
    }

    // ------------------------------

    public void Contratar(Cargo cargo, int regime) throws Exception {
        setCargo(cargo);
        if (getBanco() == null)
            setBanco(new BancoDeHoras(regime));
    }

    public void Demitir() {
        setCargo(null);
    }

    public void Promover(Cargo cargo) throws Exception {
        if (getCargo() == null)
            throw new IllegalArgumentException("O funcionário não está contratado. Contrate-o para poder promove-lo.");
        else
            Contratar(cargo, Sistema.indagarRegime());
    }

    public double CalcularSalario() throws Exception {
        if (getCargo() == null)
            throw new IllegalArgumentException("O funcionário não está contratado. Contrate-o para poder promove-lo.");

        double salario = getCargo().getSalario();
        salario = salario + 0;

        if (getBanco().getTipo() == "horista") {
            salario = salario + (getBanco().getHoras());
        }

        return salario;
    }

    public String SomarHorasBanco() {
        return getBanco().Horas();
    }

    // ----------------------------

    private String GerarMatricula() {
        Random random = new Random();
        String letras = "abcd";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1; i++) {
            sb.append(letras.charAt(random.nextInt(letras.length())));
        }
        for (int i = 0; i < 2; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    // ----------------------
}
