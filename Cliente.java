public class Cliente {
  private String nome;
  private long numeroConta;
  private String cpf;
  private double saldo;
  private double valorFatura;

  public Cliente(String nome, long numeroConta, String cpf, double saldo, double valorFatura) {
    this.nome = nome;
    this.numeroConta = numeroConta;
    this.cpf = cpf;
    this.saldo = saldo;
    this.valorFatura = valorFatura;
  }

  public String getNome() {
    return this.nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  public long getNumeroConta() {
  	return this.numeroConta;
  }
  public void setNumeroConta(long numeroConta) {
  	this.numeroConta = numeroConta;
  }

  public String getCpf() {
  	return this.cpf;
  }
  public void setCpf(String cpf) {
  	this.cpf = cpf;
  }

  public double getSaldo() {
  	return this.saldo;
  }
  public void setSaldo(double saldo) {
  	this.saldo = saldo;
  }

  public double getValorFatura() {
  	return this.valorFatura;
  }
  public void setValorFatura(double valorFatura) {
  	this.valorFatura = valorFatura;
  }
}
