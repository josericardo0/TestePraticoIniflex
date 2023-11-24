import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
class Pessoa {

    //Classe Pessoa com os atributos nome (String) e data de nascimento (LocalDate)
    private String nome;
    private LocalDate dataNascimento;

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Data de Nascimento: " + dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
