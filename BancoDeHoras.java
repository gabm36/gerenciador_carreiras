import java.util.NoSuchElementException;

public class BancoDeHoras {
    private int tipo;
    private double horas;

    public BancoDeHoras(int tipo) {
        setTipo(tipo);
        setHoras("00:00", "creditar");
    }

    public String getTipo() {
        if (this.tipo == 1)
            return "mensalista";
        else if (this.tipo == 2)
            return "horista";

        throw new NoSuchElementException("Tipo de banco não encontrado.");
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getHoras() {
        return horas;
    }

    public boolean setHoras(String horario, String modo) {
        try {
            String[] partes = horario.split(":");
            if (partes.length != 2)
                throw new IllegalArgumentException("Formato de horário inválido. ");

            int horas = Integer.parseInt(partes[0]);
            int minutos = Integer.parseInt(partes[1]);

            if (modo == "creditar") {
                this.horas += (horas * 60 + minutos);
            } else if (modo == "debitar") {
                this.horas -= (horas * 60 + minutos);
            } else {
                throw new IllegalArgumentException("Argumento ilegal para modo de banco de horas. ");
            }

            return true;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato de hora inválido. ", e);
        }
    }

    public String Horas() {
        int totalMinutos = (int) this.horas;

        int horas = totalMinutos / 60;
        int minutosRestantes = totalMinutos % 60;

        return String.format("%02d:%02d", horas, minutosRestantes);
    }
}