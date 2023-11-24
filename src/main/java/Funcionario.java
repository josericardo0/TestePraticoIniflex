import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;

class Funcionario extends Pessoa {

    //classe Funcionario estendendo Pessoa com os atributos salário (BigDecimal) e função (String)
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    @Override
    public String toString() {
        return super.toString() + ", Salário: " + formatarSalario(salario) +
                ", Função: " + funcao;
    }


    //formatacao do salario para atender ao requisito de separador de milhar com DecimalFormat
    private String formatarSalario(BigDecimal salario) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        DecimalFormat format = new DecimalFormat("#,##0.00", symbols);
        return format.format(salario);
    }

}
