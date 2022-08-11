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
 
 insert into produto(categoria_id, nome, preco) values 
 (1, "prod1", 2.00), (1, "prod1", 2.00), (1, "prod1", 2.00), (1, "prod1", 2.00),
  (2, "prod1", 4.00), (2, "prod1", 4.00), (2, "prod1", 4.00), (2, "prod1", 4.00),
 (3, "prod1", 6.00), (3, "prod1", 6.00), (3, "prod1", 6.00), (3, "prod1", 6.00),
 (4, "prod1", 8.00), (4, "prod1", 8.00), (4, "prod1", 8.00), (4, "prod1", 8.00);

insert into nf (cliente_id) values (1), (2), (3), (4);

select * from nf;

insert into item_nf (nf_id, produto_id, qtd) values 
(1, 1, 5), (1, 2, 5), (1, 3, 5), (1, 4, 5), 
(2, 5, 5), (2, 6, 5), (2, 7, 5), (2, 8, 5), 
(3, 9, 5), (3, 10, 5), (3, 11, 5), (3, 12, 5), 
(4, 13, 5), (4, 14, 5), (4, 15, 5), (4, 16, 5);

select * from item_nf;






