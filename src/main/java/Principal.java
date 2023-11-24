import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        //Adicionando cada funcionario, um por um, e suas respectivas informacoes de acordo com a tabela para a lista Funcionarios
        //Instanciando LocalDate com "LocalDate.of" e passando o ano, o mês e o dia, para trabalhar com a data de nascimento de cada funcionario
        //Usando o enum Month para representar cada mês e facilitar a tratativa com LocalDate;
        //BigDecimal para o salario
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, Month.OCTOBER, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, Month.MAY, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, Month.MAY, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, Month.OCTOBER, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, Month.JANUARY, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, Month.NOVEMBER, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, Month.MARCH, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, Month.JULY, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, Month.MAY, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, Month.JUNE, 2), new BigDecimal("2799.93"), "Gerente"));

        //Removendo João da lista de funcionários
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        //Imprimindo todos os funcionários atuais, após a remoção de João
        System.out.println("\n --- Todos os funcionários atuais ---");
        funcionarios.forEach(System.out::println);

        //Atualizando a lista de funcionários com aumento de 10% nos seus salários
        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.10"));
            funcionario.setSalario(novoSalario);
        }

        //Imprimindo os funcionários com seus salários atualizados após o aumento
        System.out.println("\n --- Relação de funcionários atualizada após aumento de 10% --- ");
        funcionarios.forEach(System.out::println);

        // Agrupando os funcionários por suas funções em uma estrutura de Map
        //Stream da lista de funcionarios, sequenciando os elementos
        //collect.Collectors para agrupar os funcionarios de acordo com a funcao (Funcionario::getFuncao)
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // Imprimindo o agrupamento de funcionários e funções
        System.out.println("\n--- Funcionários agrupados por função--- ");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("\nFunção: " + funcao);
            lista.forEach(System.out::println);
        });

        // Imprimindo os funcionários que fazem aniversário em outubro (mês 10) e dezembro (mês 12)
        //Não há funcionário que nasceu em dezembro, então decidi imprimir apenas os de outubro
        System.out.println("\n--- Funcionários aniversariantes de outubro ---");
        funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonth() == Month.OCTOBER)
                .forEach(System.out::println);

        // Encontrando o funcionário com a maior idade, utilizando Collections e Comparator
        //Cálculo baseado na data atual (LocalDate.now()) e a quantidade de anos do nascimento até agora (getYears)
        //Exibindo os atributos nome e idade do funcionario mais velho
        System.out.println("\n ---Funcionário com maior idade ---");
        Funcionario maisVelho = Collections.max(funcionarios, Comparator.comparingInt(f ->
                f.getDataNascimento().until(LocalDate.now()).getYears()));
        System.out.println("Nome: " + maisVelho.getNome() +
                ", Idade: " + maisVelho.getDataNascimento().until(LocalDate.now()).getYears());

        //Lista de funcionários em ordem alfabética, trabalhando com a API Stream
        //Criando uma stream a partir da lista de funcionarios : funcionarios.stream()
        //sorted para ordenar os elementos com base no comparador Funcionario::getNome
        //getNome - metodo da classe Funcionario
        //forEach(System.out::println) para iterar sobre cada elemento da stream e imprimir no console após ordenar em ordem alfabetica
        System.out.println("\n --- Funcionários agrupados por ordem alfabética --- ");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);

        //Stream da lista de funcionarios (funcionarios.stream)
        //map(Funcionario::getSalario) para criar uma stream contendo apenas os salarios dos funcionarios
        //formatacao do resultado com setScale para duas casas decimais : setScale(2, BigDecimal.ROUND_HALF_UP)
        //replace(".", ",") para trocar o ponto decimal por vírgula
        //toString() para fazer a conversao do resultado BigDecimal para String
        // .reduce(BigDecimal.ZERO, BigDecimal::add: reduzir a stream para apenas um resultado
        // BigDecimal.ZERO - primeiro valor
        // BigDecimal::add - para obter o total somando todos os elementos da stream
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nNúmero total dos salários de todos os funcionários: " +
                totalSalarios.setScale(2, RoundingMode.HALF_UP).toString().replace(".", ","));

        //Imprimindo a quantidade de salários mínimos que cada funcionário ganha
        //Iterando na lista funcionarios, aplicando o calculo com BigDecimal qtdSalariosMinimos
        //Aplicando a formatacao de decimal com DecimalFormat, criando uma variavel string salarioMinimoFormatado
        //A string salarioMinimoFormatado recebe o BigDecimal qtdSalariosMinimos e faz a devida formatação
        //Por fim, imprime-se cada funcionáro e a quantidade de salários mínimos que ele recebe.
        System.out.println("\n --- Quantidade de salários mínimos que cada funcionário ganha (valor do salário mínimo considerado:R$1212.00) ---");
        DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.getDefault()));
        funcionarios.forEach(funcionario -> {
            BigDecimal qtdSalariosMinimos = funcionario.getSalario().divide(new BigDecimal("1212.00"), 2, RoundingMode.HALF_UP);
            String salarioMinimoFormatado = df.format(qtdSalariosMinimos).replace(".", ",");
            System.out.println(funcionario.getNome() + ": " + salarioMinimoFormatado);
        });
    }
}