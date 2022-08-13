create database tudodebom;

use tudodebom;

create table cliente(
id int auto_increment primary key,
nome varchar(50) not null,
cpf varchar(50) not null,
endereco varchar(100) not null,
cidade varchar(50) not null,
estado varchar(50) not null,
cep varchar(50) not null,
telefone varchar(50) not null
);

create table categoria(
id int auto_increment primary key,
nome varchar(50) not null
);

create table produto(
id int auto_increment primary key,
categoria_id int not null,
nome varchar(100) not null,
preco decimal(12,2) not null,
desconto boolean not null,
qtd int not null,
foreign key(categoria_id) references categoria(id) 
);

create table nf(
id int auto_increment primary key,
cliente_id int not null,
-- item_nf_id int not null,

foreign key(cliente_id) references cliente(id)
-- foreign key(item_nf_id) references item_nf(id)
);

create table item_nf(
nf_id int not null,
produto_id int not null,
qtd int not null,

foreign key(nf_id) references nf(id),
foreign key(produto_id) references produto(id)
);

 insert into cliente (nome, cpf, endereco, cidade, estado, cep, telefone) values ("nome1", "123.123.123-123", "Rua Perdizes", "São Nunca", "SN", "00000-00", "9999-9999"), 
 ("nome2", "123.123.123-123", "Rua Perdizes", "São Nunca", "SN", "00000-00", "9999-9999"), 
 ("nome3", "123.123.123-123", "Rua Perdizes", "São Nunca", "SN", "00000-00", "9999-9999"), 
 ("nome4", "123.123.123-123", "Rua Perdizes", "São Nunca", "SN", "00000-00", "9999-9999");
 
insert into categoria(nome) values ("tipo 1"), ("tipo 2"), ("tipo 3"), ("tipo 4");
 
insert into produto(nome, preco, estoque, desconto, categoria_id) values 
("prod1", 2.00, 50, true, 1), ("prod2", 4.00, 50, false, 1), ("prod3", 6.00, 50,true, 1), ("prod4", 8.00, 50, false, 1),
("prod5", 10.00, 50, true, 2), ("prod6", 12.00, 50, false, 2), ("prod7", 14.00, 50, true, 2), ("prod8", 16.00, 50, false, 2),
("prod9", 18.00, 50, true, 3), ("prod10", 20.00, 50, false, 3), ("prod11", 22.00, 50, true, 3), ("prod12", 24.00, 50, false, 3),
("prod13", 26.00, 50, true, 4), ("prod14", 28.00, 50, false, 4), ("prod15", 30.00, 50, true, 4), ("prod16", 32.00, 50, false, 4);

insert into nf (cliente_id) values (1), (2), (3), (4);

select * from nf;

insert into item_nf (nf_id, produto_id, qtd) values 
(1, 1, 5), (1, 2, 5), (1, 3, 5), (1, 4, 5), 
(2, 5, 5), (2, 6, 5), (2, 7, 5), (2, 8, 5), 
(3, 9, 5), (3, 10, 5), (3, 11, 5), (3, 12, 5), 
(4, 13, 5), (4, 14, 5), (4, 15, 5), (4, 16, 5);

select * from item_nf;

-- calcular apenas produtos que estiverem com descontos true
select  produto.nome, (preco + (preco * 0.2)) as valor_desconto from produto where desconto = true;

-- select c.nome'Nome Cliente', inf.nf_id 'Cod NF', inf.produto_id
select p.id 'CODIGO', p.nome 'PRODUTO', (p.preco+(p.preco*0.2))'PRECO C/DESCONTO', c.nome 'CATEGORIA' from produto p inner join categoria c on c.id = p.categoria_id where p.preco < 8 and p.preco >= 2 and p.desconto = true;

select (p.qtd - inf.qtd) 'DESCONTADO DO BANCO' from item_nf inf inner join produto p on p.id =  inf.produto_id where inf.nf_id = 1;

select * from produto;

select  (select sum(qtd) from produto)-(select sum(qtd) from item_nf); 

-- update produto set p.qtd = (p.qtd - inf.qtd) from item_nf inf inner join produto p on p.id = inf.produto_id;


  