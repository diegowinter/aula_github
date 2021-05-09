import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
  static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public static void main(String[] args) {
		Menu mainMenu =  new Menu("Menu Principal", Arrays.asList("Criar conta"));

    switch (mainMenu.getSelection()) {
      case 1:
        criarConta();
        break;
    }

		System.out.println(mainMenu.getSelection() + "foi selecionada");
		System.out.println("Fim");
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

}
