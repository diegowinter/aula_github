import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
  static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
  static Cliente clienteLogado = null;
	public static void main(String[] args) {
		Menu mainMenu =  new Menu("Menu Principal", Arrays.asList("Criar conta", "Logar conta", "Sair"));
    
    while(true) {
      switch (mainMenu.getSelection()) {
        case 1:
          criarConta();
          break;
        case 2:
          logarConta();
          break;
        case 4:
          return;
      }
    }
	}

  public static void criarConta() {
    Scanner s = new Scanner(System.in);
    
    System.out.println("Informe o nome:");
		String nome = s.nextLine();
    System.out.println("Informe o CPF:");
    String cpf = s.nextLine();

    Random r = new Random();
    long numeroConta = r.nextInt(99999999);
    double saldo = r.nextInt(9999);
    double valorFatura = r.nextInt(999);

    Cliente cliente = new Cliente(nome, numeroConta, cpf, saldo, valorFatura);
    clientes.add(cliente);
  }

  public static void logarConta() {
    Scanner s = new Scanner(System.in);
    System.out.println("Informe o CPF:");
    String cpf = s.nextLine();

    boolean encontrada = false;
    int index = 0;
    int indexConta = 0;
    for (Cliente cliente : clientes) {
      if (cliente.getCpf().equals(cpf)) {
        encontrada = true;
        indexConta = index;
      }
      index++;
    }
    System.out.println(index);

    if (encontrada == false) {
      System.out.println("Conta nao encontrada. Verifique o CPF e tente novamente.");
    } else {
      clienteLogado = clientes.get(indexConta);

      System.out.println("Cliente conectado: " + clienteLogado.getNome());
      
      Menu logadoMenu =  new Menu("Realizar operação", Arrays.asList("Sacar", "Transfericia TED","Pagar fatura","Gerar cartao virtual","Voltar"));
      switch(logadoMenu.getSelection()){
       case 1:
          sacar();
          break;
        case 2:
          trasferenciaTed();
          break;  
        case 3:
          pagarFatura();
          break;
        case 4:
          gerarCartao();
          break;  
      }
    }

  }
  public static void sacar(){

    Scanner s = new Scanner(System.in);
    System.out.println("Informe o valor do saque :");
    double valor = Double.parseDouble(s.nextLine());
    System.out.println(valor);

    if(clienteLogado.getSaldo() > valor ){
      double saldo = clienteLogado.getSaldo() - valor;
      clienteLogado.setSaldo(saldo);
      for (Cliente cliente : clientes){
        if (cliente.getCpf().equals(clienteLogado.getCpf())) {
          cliente.setSaldo(saldo); 
      }

    } 
      System.out.println("Saque Realizado");
      System.out.println("Cliente: " + clienteLogado.getNome()); 
      System.out.println("Novo Saldo : " + clienteLogado.getSaldo());

      return; 
    }
    System.out.println("Saldo insulficiente\n");
  }

  public static void trasferenciaTed(){
    Scanner s = new Scanner(System.in);
    System.out.println("Conta Destino :");
    String cpfDestino = s.nextLine();
    System.out.println("Valor da Transferencia:");
    double valor = Double.parseDouble(s.nextLine());
    if(clienteLogado.getCpf().equals(cpfDestino)){
      System.out.println("Impossivel realizar transferencia: CPF de destino igual o de origem");
      return;
    }

    if(clienteLogado.getSaldo() > valor ){
      double saldo = clienteLogado.getSaldo() - valor;
      clienteLogado.setSaldo(saldo);
      for (Cliente cliente : clientes){
        if (cliente.getCpf().equals(cpfDestino)) {
          cliente.setSaldo( cliente.getSaldo() + valor);
          System.out.println("Transferencia Realizada do cliente " + clienteLogado.getNome() + " para o cliente " + cliente.getNome());
          return;
      }   
        
    }
    System.out.println("Cliente destino com CPF" + cpfDestino + "nao localizado");
    return;
    }
    System.out.println("Saldo cliente" + clienteLogado.getNome() + "insulficiente");
  }
  public static void pagarFatura(){
    Scanner s = new Scanner(System.in);
    System.out.println("Valor do pagamento: ");
    double valor = Double.parseDouble(s.nextLine());
    clienteLogado.setValorFatura(clienteLogado.getValorFatura()-valor);

    for (Cliente cliente : clientes){
      if (clienteLogado.getCpf().equals(cliente.getCpf())) {
        cliente.setValorFatura(cliente.getValorFatura() - valor);
      }
    }   
    System.out.println("Fatura paga com sucesso.");
  }

  public static void gerarCartao(){
    Random random = new Random();
    String cartao = "";
    for(int i=0; i<4; i++){
      cartao += " "+String.valueOf(random.nextInt(9999));
    }
    System.out.println("Cartao: " + cartao);
  }
}

