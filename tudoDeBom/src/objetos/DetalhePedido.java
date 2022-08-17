package objetos;

public class DetalhePedido {

	int quantidade;
	double preco;
	double subTotal;
	double total;

	int idPedido, idCliente;
	String nomeProduto;
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	@Override
	public String toString() {
		return "DetalhePedido [quantidade=" + quantidade + ", preco=" + preco + ", subTotal=" + subTotal + ", total="
				+ total + ", idPedido=" + idPedido + ", idCliente=" + idCliente + ", nomeProduto=" + nomeProduto + "]";
	}
}
