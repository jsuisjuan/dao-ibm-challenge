create database tudodebombd;
use tudodebombd;

create table cliente(
id int auto_increment primary key,
nome varchar(50) not null,
cpf varchar(50) not null
);

create table produto (
	id int auto_increment primary key,
    nome varchar(100) not null,
    estoque int not null,
    preco decimal(5,2) not null,
    categoria varchar(50) not null,
    disponibilidade boolean not null,
    generico boolean not null,
    remedio boolean not null,
    preco_descontado decimal(5,2)
);

select * from pedido;
create table pedido(
pedido_id int auto_increment primary key,
endereco_entrega varchar(100) not null,
data_pedido date,
total_pedido decimal(5,2),
cliente_id int not null,

foreign key(cliente_id) references cliente(id) ON DELETE CASCADE ON UPDATE CASCADE
);
select nome from pedido, cliente where pedido.cliente_id=cliente.id;
select * from pedido, cliente where pedido.cliente_id=cliente.id and cliente.id=1;

select * from pedido;
create table item_pedido(
id int auto_increment primary key,
quantidade int not null,
preco decimal(5,2),
sub_total decimal(5,2),
pedido_id int not null,
produto_id int not null,

foreign key(pedido_id) references pedido(pedido_id) ON DELETE CASCADE ON UPDATE CASCADE,
foreign key(produto_id) references produto(id) ON DELETE CASCADE ON UPDATE CASCADE
);
select * from item_pedido, pedido where item_pedido.pedido_id=pedido.pedido_id and pedido.pedido_id=1;
insert into cliente (nome, cpf) values ("Gerson", "123.123.123-123"), ("Juan", "123.123.123-123"), ("Jessica", "123.123.123-123"), ("Silvio", "123.123.123-123");
 
 
insert into produto(nome, estoque, preco, categoria, disponibilidade, generico, remedio, preco_descontado) values 
("Advil", 45, 5.00, "Medicamento", true, false, true, 4.00), ("Shampoo", 46, 30.00, "Higienico", true, false, false, 0), ("Probiótica", 47, 100.00, "Suplementos", true, false, false, 0), ("Própolis", 49, 33.00, "Natural", false, false, false, 0),
("Buscopan", 45, 32.00, "Medicamento", false, true, true, 25.60), ("Escova de Dente", 46, 8.00, "Higienico", false, false, false, 0), ("Growth Supplements", 48, 75.00, "Suplementos", false, true, false, 0), ("Chá de Boldo", 47, 15.00, "Natural", true, false, false, 0),
("Dipirona", 46, 18.00, "Medicamento", true, true, true, 14.40), ("Sabonete", 47, 4.00,"Higienico", true, false, false, 0), ("Adaptogen", 45, 85.00, "Suplementos", true, true, false,0), ("Castanha-da-India", 49, 77.00, "Natural", false, false, false, 0),
("Xarope", 48, 40.00, "Medicamento", false, true, true, 32.00),("Pasta de Dente", 46, 18.00, "Higiênico", false, false, false, 0), ("Athletica Nutrition", 47, 250.00, "Suplementos", false, false, false, 0), ("Óleo de Côco", 49, 55.00, "Natural", true, false, false, 0);

insert into pedido (endereco_entrega,data_pedido,total_pedido, cliente_id) values ("Rua Santa", '2023-04-08' ,150.00 , 1), ("Rua Getulio Vargas", '2022-12-18',150.00 , 2), ("Rua Da Vala", '2022-09-29' ,150.00 , 3), ("Rua Pedro Fonseca", '2022-11-16' ,150.00 , 4);

select total_pedido from pedido where id = 1;

insert into item_pedido (pedido_id, produto_id, quantidade, preco, sub_total) values 
(1, 1, 5, 5.0, 25.00), (2, 5, 5, 32.0, 160.00), (3, 9, 4, 18.0, 72.00), (4, 13, 2, 40.0, 80.00), 
(1, 2, 4, 33.0, 132.00), (2, 6, 4, 8.0, 32.00), (3, 10, 3, 4.0, 12.00), (4, 14, 4, 18.0, 72.00), 
(1, 3, 3, 100.0, 300.00), (2, 7, 2, 75.0, 150.00), (3, 11, 5, 85.0, 425.00), (4, 15, 3, 250.0, 750.00), 
(1, 4, 1, 5.0, 5.00), (2, 8, 3, 15.0, 45.00), (3, 12, 1, 77.0, 77.00), (4, 16, 1, 55.0, 55.00);

select * from produto;
select * from cliente;
select * from pedido;
select * from item_pedido;

-- calcular apenas produtos que estiverem com descontos true
select  produto.nome, (preco + (preco * 0.2)) as valor_desconto from produto where desconto = true;

-- select c.nome'Nome Cliente', inf.nf_id 'Cod NF', inf.produto_id
select p.id 'CODIGO', p.nome 'PRODUTO', (p.preco+(p.preco*0.2))'PRECO C/DESCONTO', c.nome 'CATEGORIA' from produto p inner join categoria c on c.id = p.categoria_id where p.preco < 8 and p.preco >= 2 and p.desconto = true;

select (p.estoque - ip.quantidade) 'DESCONTADO DO BANCO' from item_pedido ip inner join produto p on p.id =  ip.produto_id where ip.pedido_id = 1;

select * from produto;

select  (select sum(estoque) from produto)-(select sum(quantidade) from item_pedido); 

-- update produto set p.qtd = (p.qtd - inf.qtd) from item_nf inf inner join produto p on p.id = inf.produto_id;


select c.tipo_categoria 'CATEGORIA', p.nome, 'PRODUTO', p.estoque 'ESTOQUE'  from categoria c inner join produto p on p.categoria_id = c.id where c.tipo_categoria like '%TIPO 1%';

select * from produto inner join categoria on produto.categoria_id = categoria.id where produto.id = 1; 

select * from item_pedido where id = 1; 

select pd.id 'Nº PEDIDO', ip.sub_total 'SUB-TOTAL' from pedido pd  inner join item_pedido ip on ip.pedido_id = pd.id where pd.id = 1;

select cl.nome 'CLIENTE', pd.id 'Nº PEDIDO', ip.quantidade 'QUANTIDADE', pr.preco 'PREÇO UNITARIO', pr.desconto_flag 'DESCONTO FLAG 20%', pr.remedio_flag 'GENERICO FLAG 20%', ip.sub_total 'SUB-TOTAL', pd.total_pedido 'TOTAL PEDIDO', pd.endereco_entrega 'ENDEREÇO ENTREGA', pr.id 'COD PROD', pr.nome 'PRODUTO',pr.estoque 'ESTOQUE', c.tipo_categoria 'CATEGORIA' from pedido pd inner join item_pedido ip on pd.id = ip.pedido_id
 inner join produto pr on pr.id = ip.produto_id 
 inner join cliente cl on cl.id = pd.cliente_id
 inner join categoria c on c.id = pr.categoria_id where cl.id = 1; 
 
 select * from item_pedido where pedido_id = 2;
 
 select * from item_pedido;
 
 select * from produto where disponibilidade = 1;
 select * from pedido;
 
 select * from pedido, cliente where pedido.cliente_id=cliente.id and pedido.cliente_id=1;
 
 select * from pedido inner join item_pedido on  pedido.pedido_id  = item_pedido.pedido_id 
 inner join produto on produto.id = item_pedido.produto_id
 inner join cliente on cliente.id = pedido.cliente_id;