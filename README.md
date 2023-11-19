# LEIAME - Sistema de Gestão de Carreiras de Funcionários

Este é um sistema de gestão de funcionários em Java. Ele permite que você realize várias operações relacionadas à contratação, promoção, demissão e outras ações com funcionários e seus cargos. Abaixo estão as instruções para executar e utilizar o programa.

## Requisitos

Para executar este programa, você deve ter o Java Development Kit (JDK) instalado em seu computador. Você pode baixá-lo em [Java JDK](https://www.oracle.com/java/technologies/downloads/).

## Executando o Programa

Abra o terminal ou prompt de comando no diretório onde você salvou os arquivos Sistema.java, Funcionario.java e Cargo.java.

Compile o programa executando o seguinte comando:

```shell
javac Sistema.java
```

Após a compilação, execute o programa com o seguinte comando:

```shell
java Sistema
```

## Utilizando o Programa

Após executar o programa, você verá a lista de funcionários (ativos e inativos) cadastrados e um menu de opções no console. Aqui estão as opções disponíveis:

- Novo: Crie um novo funcionário e adicione-o à lista de funcionários.

- Editar: Edite as informações de um funcionário existente.

- Contratar: Contrate um funcionário existente para um cargo.

- Promover: Promova um funcionário existente para um cargo superior.

- Ordenar ou Filtrar: Escolha opções de ordenação e filtragem para visualizar a lista de funcionários.

- Emitir Folha: Emite a folha de pagamento de um funcionário.

- Registrar Horas: Registre as horas trabalhadas por um funcionário.

- Debitar Horas: Debita horas extras trabalhadas por um funcionário.

- Registrar Bônus: (Função ainda não implementada).

- Demitir: Demita um funcionário.

- Sair: Encerre o programa.

Siga as instruções no console para usar as diferentes opções do programa. O programa apresentará informações e solicitará entrada de dados conforme necessário.

## Diferença entre Mensalistas e Horistas

### Mensalistas (Banco de Horas):

- Regime de Contratação: Funcionários mensalistas têm um contrato em que recebem um salário fixo por mês, independentemente do número de horas trabalhadas.

- Banco de Horas: Os funcionários mensalistas têm a flexibilidade de registrar as horas extras trabalhadas além da jornada regular. Essas horas são registradas em um "banco de horas" e podem ser usadas posteriormente como tempo livre ou para compensação de horas.

- Compensação: O funcionário pode usar as horas acumuladas no banco de horas para tirar folga ou compensar por horas não trabalhadas.

- Folha de Pagamento: As horas extras trabalhadas não são pagas imediatamente, mas sim acumuladas no banco de horas e pagas em forma de tempo livre ou folga.

### Horistas (Horas Extras):

- Regime de Contratação: Funcionários horistas são pagos com base nas horas efetivamente trabalhadas. Eles recebem um valor fixo mais o proporcional às horas trabalhadas.

- Horas Extras: Os funcionários horistas podem receber horas extras quando trabalham além da jornada regular estabelecida. Essas horas extras são pagas a uma taxa superior (120%) à hora normal de trabalho.

- Pagamento Imediato: As horas extras são pagas imediatamente junto com o salário do período correspondente.

- Controle Estrito de Horas: O controle das horas é mais rigoroso para funcionários horistas, pois cada hora trabalhada além do horário regular deve ser registrada e paga.