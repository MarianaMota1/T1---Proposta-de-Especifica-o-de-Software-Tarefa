/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Mary
 * Created: 23/01/2017
 */

create table funcionario(
    codigo int not null auto_increment primary key,
    cpf varchar(250) not null,
    rg varchar(250) not null,
    nome varchar(250) not null,
    funcao varchar(250) not null,
    senha varchar(250) not null
);

create table cliente(
    codigo int not null auto_increment primary key,
    cpf varchar(250) not null,
    rg varchar(250) not null,
    endereco varchar(250) not null,
    telefone varchar(250) not null,
    nome varchar(250) not null
);

create table fornecedor(
    codigo int not null auto_increment primary key,
    cnpj varchar(250) not null,
    nome varchar(250) not null,
    telefone varchar(250) not null,
    email varchar(250) not null
);

create table produto(
    codigo int not null auto_increment primary key,
    nome varchar(250) not null,
    descricao text not null,
    precounitario decimal(9,2) not null
);

create table venda(
    codigo int not null auto_increment primary key,
    cliente int not null,
    data date not null,
    valortotal decimal(9,2) not null,
    foreign key(cliente) references cliente(codigo)
);

create table vendaitem(
    codigo int not null auto_increment primary key,
    venda int not null,
    produto int not null,
    quantidade int not null,
    foreign key(venda) references venda(codigo),
    foreign key(produto) references produto(codigo)
);

create table meiopagamento(
    codigo int not null auto_increment primary key,
    meiopagamento varchar(250) not null
);

create table itemnotafiscal(
    codigo int not null auto_increment primary key,
    valorunitario decimal(9,2) not null,
    quantidade int not null
);

create table notafiscal(
    codigo int not null auto_increment primary key,
    data date not null,
    valortotal decimal(9,2) not null,
    descricao text not null
);

create table formapagamento(
    codigo int not null auto_increment primary key,
    tipopagamento varchar(250) not null
);