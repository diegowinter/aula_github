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
    for (Cliente cliente : clientes) {
      if (cliente.getCpf().equals(cpf)) {
        encontrada = true; 
      }
      index++;
    }
    System.out.println(index);

    if (encontrada == false) {
      System.out.println("Conta nao encontrada. Verifique o CPF e tente novamente.");
    } else {
      clienteLogado = clientes.get(index-1);

      System.out.println("Cliente conectado: " + clienteLogado.getNome());
      
      Menu logadoMenu =  new Menu("Realizar operação", Arrays.asList("Sacar", "Transfericia TED","Voltar"));
      switch(logadoMenu.getSelection()){
       case 1:
          sacar();
          break;
        case 2:
          return;  
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


  
  
}
