import java.util.Scanner;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Sistema {

    private static Scanner scanner = new Scanner(System.in);
    private static String filtro = "nenhum";
    private static String ordem = "matricula";

    public static List<Cargo> cargos = new ArrayList<Cargo>(Arrays.asList(
            new Cargo("Estagiário", 1200, Arrays.asList("Analista De Suporte")),
            new Cargo("Analista De Suporte", 1800,
                    Arrays.asList("Desenvolvedor", "Arquiteto De Software", "Analista De Qualidade",
                            "Analista De Dados", "Designer De Interface")),
            new Cargo("Analista De Dados", 2200, Arrays.asList("Gerente De Desenvolvimento")),
            new Cargo("Analista De Qualidade", 2600, Arrays.asList("Gerente De Desenvolvimento")),
            new Cargo("Arquiteto de Software", 2700, Arrays.asList("Gerente De Desenvolvimento")),
            new Cargo("Desenvolvedor", 2800, Arrays.asList("Gerente De Desenvolvimento")),
            new Cargo("Designer De Interface", 2700, Arrays.asList("Gerente De Desenvolvimento")),
            new Cargo("Gerente De Desenvolvimento", 4800, Arrays.asList("Gerente De Projetos")),
            new Cargo("Gerente De Projetos", 6800, Arrays.asList("Diretor de Tecnologia")),
            new Cargo("Diretor De Tecnologia", 10200, new ArrayList<>())));

    public static List<Funcionario> funcionarios = new ArrayList<Funcionario>(Arrays.asList(
            new Funcionario("Gabriel Marques", "87236491528"),
            new Funcionario("Mario Cathinip", "51968743210"),
            new Funcionario("Sabrina Rovia", "63428917506"),
            new Funcionario("Maria Boladona", "63428917506"),
            new Funcionario("Juliana Sabino", "96542173803"),
            new Funcionario("Albert Einstein", "14750382694")));

    public static List<Funcionario> lista = new ArrayList<Funcionario>();

    public static void main(String[] args) {
        int opc = 1;

        do {
            try {
                limparPainel();
                listarFuncionarios(ordenarFuncionarios(filtrarFuncionarios(funcionarios, filtro), ordem));

                opc = 1;
                System.out.print("\n");

                System.out.printf("%-2d) %-20s", opc++, "Novo");
                System.out.printf("%-2d) %-20s", opc++, "Editar");
                System.out.printf("%-2d) %-20s", opc++, "Contratar");
                System.out.printf("%-2d) %-20s", opc++, "Promover");
                System.out.print("\n");
                System.out.printf("%-2d) %-20s", opc++, "Ordenar ou fltrar");
                System.out.printf("%-2d) %-20s", opc++, "Emitir folha");
                System.out.printf("%-2d) %-20s", opc++, "Registrar horas");
                System.out.printf("%-2d) %-20s", opc++, "Debitar horas");
                System.out.print("\n");
                System.out.printf("%-2d) %-20s", opc++, "Registrar bonús");
                System.out.printf("%-2d) %-20s", opc++, "Demitir");
                System.out.printf("%-2d) %-20s", opc++, "Sair");

                System.out.print("\n\nDigite a opção desejada: ");

                opc = scanner.nextInt();
                scanner.nextLine();

                switch (opc) {
                    case 1:
                        novoFuncionario();
                        break;
                    case 2:
                        editarFuncionario();
                        break;
                    case 3:
                        contratarFuncionario();
                        break;
                    case 4:
                        promoverFuncionario();
                        break;
                    case 5:
                        ordenarLista();
                        break;
                    case 6:
                        emitirFolha();
                        break;
                    case 7:
                        creditarHoras();
                        break;
                    case 8:
                        debitarHoras();
                        break;
                    case 9:
                        registrarBonus();
                        break;
                    case 10:
                        demitirFuncionario();
                        break;
                    case 11:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (Exception ex) {
                System.out.printf("%n%s Tecle enter para continuar...", ex.getMessage());
                scanner.nextLine();
            }

        } while (opc != 11);

        scanner.close();
    }

    private static void listarFuncionarios(List<Funcionario> listaFuncionarios) {
        if (listaFuncionarios.isEmpty()) {
            System.out.println("Nenhum registro.");
        } else {
            String tabela = "%-6s %-20s %-13s %-28s %-10s %-7s %n";

            System.out.printf(tabela, "Matr", "Funcionário", "CPF", "Função",
                    "Salário", "Saldo (BH)");

            for (Funcionario funcionario : listaFuncionarios) {
                String matricula = funcionario.getMatricula();
                String nome = funcionario.getNome();
                String cpf = funcionario.getCpf();

                String cargo;
                String salario;
                String horas;

                if (funcionario.getCargo() == null) {
                    cargo = "-";
                    salario = "-";
                } else {
                    cargo = funcionario.getCargo().getTitulo();
                    salario = String.format("R$%.2f", funcionario.getCargo().getSalario());
                }

                if (funcionario.getBanco() == null) {
                    horas = "-";
                } else {
                    horas = String.format("%s (%s)", funcionario.getBanco().Horas(), funcionario.getBanco().getTipo());
                }

                System.out.printf(tabela, matricula, nome, cpf, cargo, salario, horas);
            }
        }
    }

    private static Funcionario selecionarFuncionario() {
        while (true) {
            System.out.print("Digite a matricula: ");
            String matricula = scanner.nextLine();

            for (Funcionario funcionario : funcionarios) {
                if (funcionario.getMatricula().equals(matricula)) {
                    return funcionario;
                }
            }

            System.out.print("Matricula não encontrada. ");
        }
    }

    private static void novoFuncionario() {
        String nome = indagarNome();
        String cpf = indagarCPF();

        funcionarios.add(new Funcionario(nome, cpf));
    }

    private static void editarFuncionario() {
        Funcionario funcionario = selecionarFuncionario();

        String nome = indagarNome();
        String cpf = indagarCPF();

        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
    }

    private static void contratarFuncionario() throws Exception {
        Funcionario funcionario = selecionarFuncionario();

        int opc;

        while (true) {
            System.out.print("\n");

            for (int i = 0; i < cargos.size(); i++) {
                String titulo = cargos.get(i).getTitulo();
                double salario = cargos.get(i).getSalario();
                System.out.printf("%-2d) %-28s R$%.2f%n", (i + 1), titulo, salario);
            }

            System.out.print("\nEscolha o cargo: ");
            opc = scanner.nextInt();
            scanner.nextLine();

            if (opc >= 1 && opc <= cargos.size()) {
                Cargo cargo = Sistema.cargos.get(opc - 1);
                funcionario.Contratar(cargo, indagarRegime());
                break;
            }

            System.out.println("Opção inválida. \n");
        }
    }

    private static void promoverFuncionario() throws Exception {
        Funcionario funcionario = selecionarFuncionario();

        if (funcionario.getCargo() == null)
            throw new NoSuchElementException("O funcionário não tem uma função.");

        while (true) {
            System.out.print("\n");

            List<Cargo> cargosDisp = funcionario.getCargo().getPromocoes();

            if (cargosDisp.size() == 0)
                throw new Exception("Sem promoções disponíveis.");

            for (int i = 0; i < cargosDisp.size(); i++) {
                String titulo = cargosDisp.get(i).getTitulo();
                double salario = cargosDisp.get(i).getSalario();
                System.out.printf("%d - %s (R$%.2f)%n", (i + 1), titulo, salario);
            }

            System.out.print("\nEscolha a promoção: ");
            int opc = scanner.nextInt();
            scanner.nextLine();

            if (opc >= 1 && opc <= cargosDisp.size()) {
                Cargo cargo = cargosDisp.get(opc - 1);
                funcionario.Promover(cargo);

                break;
            }
        }
    }

    private static void ordenarLista() {
        int opc = 1;

        System.out.print("\n");

        System.out.printf("%-2d) %-20s", opc++, "Apenas contratados");
        System.out.printf("%-2d) %-20s", opc++, "Ordenar por nome");
        System.out.printf("%-2d) %-20s", opc++, "Ordenar por salário");
        System.out.printf("%-2d) %-20s", opc++, "Resetar");

        while (true) {
            System.out.print("\n\nDigite a opção desejada: ");

            opc = scanner.nextInt();
            scanner.nextLine();

            switch (opc) {
                case 1:
                    Sistema.filtro = "funcao";
                    Sistema.ordem = "matricula";
                    return;
                case 2:
                    Sistema.filtro = "nenhum";
                    Sistema.ordem = "nome";
                    return;
                case 3:
                    Sistema.filtro = "nenhum";
                    Sistema.ordem = "salario";
                    return;
                case 4:
                    Sistema.filtro = "nenhum";
                    Sistema.ordem = "matricula";
                    return;
                default:
                    System.out.print("Opção inválida. ");
                    break;
            }
        }
    }

    private static void emitirFolha() {
        Funcionario funcionario = selecionarFuncionario();

        if (funcionario.getCargo() == null)
            throw new IllegalArgumentException("O funcionário não está devidamente " +
                    "contratado, então não é possível emitir uma folha de pagamento nesse registro. ");

        limparPainel();

        double salario = funcionario.getCargo().getSalario();
        String linhaHoraExtra = "";
        double totalExtra = 0;

        if (funcionario.getBanco().getTipo() == "horista") {
            double horasRegistradas = funcionario.getBanco().getHoras() / 60;
            double salarioHora = (salario / 21) / 8;
            String horaExtra = String.format("%.2f x R$%.2f", horasRegistradas, salarioHora);
            totalExtra = horasRegistradas * salarioHora;

            linhaHoraExtra = String.format("Horas extras        %-16s | R$%.2f%n", horaExtra, totalExtra);
        }

        System.out.printf("Nome: %-30s | Regime: %-10s%n", funcionario.getNome(), funcionario.getBanco().getTipo());
        System.out.printf("CPF:  %-30s | Horas:  %-10s%n", funcionario.getCpf(), funcionario.getBanco().Horas());
        System.out.printf("---------------------------------------------------------%n");
        System.out.printf("Salário             x21              | R$%.2f%n", salario);
        System.out.printf(linhaHoraExtra);
        System.out.printf("Bonificações        0,00             | R$0,00%n");
        System.out.printf("---------------------------------------------------------%n");
        System.out.printf("Total                                | R$%.2f%n", salario + totalExtra);

        if (funcionario.getBanco().getTipo() == "horista")
            System.out.printf("%nAo efetivar a emissão, o banco de horas será zerado. ");
        System.out.print("Confirmar a emissão? S ou N: ");

        do {
            String opc = scanner.nextLine();

            if (opc.equalsIgnoreCase("S")) {
                if (funcionario.getBanco().getTipo() == "horista")
                    funcionario.getBanco().setHoras(funcionario.getBanco().Horas(), "debitar");

                System.out.printf("A folha foi emitida. Tecle enter para continuar...");
                scanner.nextLine();
                break;
            } else if (opc.equalsIgnoreCase("N")) {
                System.out.printf("A folha não foi emitida. Tecle enter para continuar...");
                scanner.nextLine();
                break;
            } else {
                System.out.printf("Opção inválida. Confirme novamente com S ou rejeite com N: ");
            }
        } while (true);
    }

    private static void creditarHoras() {
        registrarHoras("creditar");
    }

    private static void debitarHoras() {
        registrarHoras("debitar");
    }

    private static void registrarHoras(String modo) {
        Funcionario funcionario = selecionarFuncionario();
        String horas = "";

        if (funcionario.getBanco() == null)
            throw new NoSuchElementException("O funcionário não foi devidamente " +
                    "contratado e, por tanto, não possui banco de horas ativo. Contrate-o pela " +
                    "primeira vez para poder registrar horas. ");

        if (modo == "debitar" && funcionario.getCargo() != null)
            if (funcionario.getBanco().getTipo() == "horista")
                throw new IllegalArgumentException("O funcionário é horista e, " +
                        "por tanto, só tem suas horas zeradas quando a folha de pagamento " +
                        "é emitida, adicionando o saldo de horas extras ao salário. ");

        do {
            System.out.print("Digite a quantidade de horas a ser registrada (no formato HH:MM): ");
            horas = scanner.nextLine();

            if (funcionario.getBanco().setHoras(horas, modo))
                break;

            System.out.print("Formato inválido. ");
        } while (true);
    }

    private static void registrarBonus() throws Exception {
        throw new Exception("Função ainda não implementada. ");
    }

    private static List<Funcionario> ordenarFuncionarios(List<Funcionario> lista, String ordem) {
        lista.sort(new Comparator<Funcionario>() {
            public int compare(Funcionario f1, Funcionario f2) {
                switch (ordem) {
                    case "matricula":
                        return f1.getMatricula().compareTo(f2.getMatricula());
                    case "nome":
                        return f1.getNome().compareTo(f2.getNome());
                    case "salario":
                        if (f1.getCargo() == null && f2.getCargo() == null)
                            return 0;
                        if (f1.getCargo() == null)
                            return 1;
                        if (f2.getCargo() == null)
                            return -1;
                        return Double.compare(f2.getCargo().getSalario(), f1.getCargo().getSalario());
                    default:
                        return 0;
                }
            }
        });

        return lista;
    }

    private static List<Funcionario> filtrarFuncionarios(List<Funcionario> lista, String filtro) {
        List<Funcionario> novaLista = new ArrayList<Funcionario>();

        for (Funcionario f : lista) {
            switch (filtro) {
                case "funcao":
                    if (f.getCargo() != null) {
                        novaLista.add(f);
                    }
                    break;
                default:
                    novaLista = Sistema.funcionarios;
                    break;
            }
        }

        return novaLista;
    }

    private static void demitirFuncionario() {
        Funcionario funcionario = selecionarFuncionario();
        funcionario.Demitir();

        System.out.print("\n" + funcionario.getNome() + " foi demitido(a). ");

        String horas = funcionario.getBanco().Horas();
        if (horas != "00:00")
            System.out.print("O funcionário ainda têm " + horas + " no banco de dados que devem ser acertadas. ");

        System.out.print("Tecle enter para continuar...");
        scanner.nextLine();
    }

    public static void limparPainel() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean verificarCPF(String cpf) {
        return cpf.length() == 11 && cpf.matches("\\d+");
    }

    public static String indagarNome() {
        String nome;

        do {
            System.out.print("Digite o nome: ");
            nome = scanner.nextLine();
        } while (nome.isEmpty());

        return nome;
    }

    public static String indagarCPF() {
        String cpf;

        while (true) {
            System.out.print("Digite o CPF: ");

            cpf = scanner.nextLine();

            if (verificarCPF(cpf))
                return cpf;

            System.out.print("CPF inválido. Use apenas 11 numeros. Sem pontos. ");
        }
    }

    public static int indagarRegime() {
        while (true) {
            int opc = 0;
            System.out.print("\n");

            System.out.printf("%-2d) %-20s%n", 1, "Mensalista (com banco de horas)");
            System.out.printf("%-2d) %-20s%n", 2, "Horista (com horas extras)");

            System.out.print("\nDigite a opção para regime de contratação: ");
            System.out.print(opc = scanner.nextInt());

            if (opc > 0 && opc < 3)
                return opc;

            System.out.print("Opção inválida. ");
        }
    }
}
