# Farmácio Tudo de Bom
## Desafio Parte 1: Programa if black, then code - IBM & Gama Academy
#### A loja fictícia Tudo de Bom está construindo uma nova plataforma e precisa de uma API para gerenciar as transações. O sistema deve permitir cadastro de novos clientes, incluindo dados pessoais, dados para contato e controle de estoque.

#### Tech's utilizadas: JAVA (JFrame) / MySQL
####
#### Requisitos Mínimos do Software:
> #### Cadastro de novos clientes na plataforma ✅
> > Endpoint: TudoDeBom-IBM/tudoDeBom/src/connection/ClienteDAO.java 
> > - Método: public void cadastrarCliente(Object)
> > - O método recebe o objeto cliente como um parâmetro, e a inserção (POST) de uma tupla desse objeto é feita e alocada no banco de dados. 

> ####  Listagem de clientes na plataforma ✅ 
> > Endpoint: TudoDeBom-IBM/tudoDeBom/src/connection/ClienteDAO.java 
> > - Método: public ArrayList<Cliente> listarCliente()
> > - O método efetua a busca (GET) das tuplas da relação Cliente no banco de dados e aloca na tabela JFrame na janela MainFrame.

> ####  Atualização de clientes na plataforma ✅ 
> > Endpoint: TudoDeBom-IBM/tudoDeBom/src/connection/ClienteDAO.java 
> > - Método: public void atualizarCliente(Object)
> > - O método recebe o objeto cliente, com atributos já preenchidos, como um parâmetro, e a atualização (PUT) da tupla desse objeto é feita e realocada no banco de dados.

> ####  Deletar clientes de plataforma ✅ 
> > Endpoint: TudoDeBom-IBM/tudoDeBom/src/connection/ClienteDAO.java 
> > - Método: public void deletarCliente(Object)
> > - O método recebe o objeto cliente como um parâmetro, e a remoção (DELETE) da tupla desse objeto é feita e reajustada no banco de dados.

> ####  Historico de transações entre contas ✅ 
> > Endpoint: TudoDeBom-IBM/tudoDeBom/src/connection/PedidoDAO.java 
> > - Método: public ArrayList<Pedido> listarPedidoEspCliente()
> > - o método efetua a busca (GET) de um ID especifico da relação Cliente e, através de uma chave estrangeira, tem acesso a relação Pedido, com os pedidos desse cliente. Após isso, o método lista as informações do pedido na table JFrame na janela Histórioco de Transições. 

> ####  Controle de estoque de produtos ✅ 
> > Endpoint: TudoDeBom-IBM/tudoDeBom/src/connection/ProdutoDAO.java
> > - Métodos:
> > > - public void cadastrarProduto(Objeto)
> > > - public ArrayList<Produto> listarProduto()
> > > - public void atualizarProduto(Objeto)
> > > - public void removerProduto(Objeto)
> > - os métodos efetuam o CRUD da entidade Produto o qual representa o controle de estoque.

> ####  Medicamentos genéricos contêm 20% de desconto ❔ 
> > Endpoint: TudoDeBom-IBM/tudoDeBom/src/connection/ProdutoDAO.java
> > - Métodos:
> > > - public void cadastrarProduto(Objeto)
> > > - public ArrayList<Produto> listarProduto()
> > - assim que um objeto for cadastradado, se o objeto tiver Remedio=true && Generico=true, o desconto de 20% é aplicado ao preço desse objeto. O Desconto foi implementado manualmente no banco de dados.

#### Recursos Extras do Software:
> #### Utilização de uma Relação ItemPedido como intermédio entre Pedido e Produto
> > Endpoint: TudoDeBom-IBM/tudoDeBom/src/connection/ItemPedidoDAO.java 

> #### Detalhamento do pedido
> > Endpoint: TudoDeBom-IBM/tudoDeBom/src/connection/DetalhamentoDAO.java

