# Cuidar.me - Sistema de Gerenciamento para Psicólogos  

## Sobre o Projeto  
Bem vindo(a)! Este repositório contém a versão POO do **Cuidar.me**, um sistema desenvolvido com o objetivo de auxiliar psicólogos na gestão de suas atividades administrativas e clínicas.  
Este projeto foi desenvolvido como parte da disciplina **Análise e Projeto de Sistemas (APS)** do curso de **Análise e Desenvolvimento de Sistemas** do **IFPB - Campus Esperança**.  

O repositório contém:  
- **Código-fonte em Java (terminal)**  
- **Documento de Visão**  
- **Diagramas UML**  
- **Checklist de Consistência**  

## Objetivo  
Oferecer uma solução gratuita que permita aos psicólogos:  
- Cadastrar pacientes  
- Agendar consultas  
- Registrar pagamentos  
- Gerenciar documentos clínicos  
- Garantir segurança e conformidade 

## Funcionalidades  

| Categoria               | Funcionalidades |
|--------------------------|-----------------|
| **Pacientes**            | Cadastro, edição, pesquisa e inativação |
| **Consultas**            | Agendamento, remarcação, cancelamento, integração com prontuários |
| **Financeiro**           | Registro de pagamentos (Pix, boleto, cartão) |
| **Documentos** *(futuro)* | Modelos pré-definidos de prontuários e declarações |
| **Segurança**            | Login e senha |

## Estrutura do Repositório

```plaintext
cuidarme-poo/
  └── docs
      └── Diagramas
          ├── DiagramaAtividade.pdf
          ├── DiagramaCasosDeUso.pdf
          ├── DiagramaClasses.pdf
          ├── DiagramaObjetos.pdf
          ├── DiagramaSequencia.pdf
      ├── ConsistenciaDiagramas.pdf
      ├── DocumentoVisao.pdf
  └── src
      └── main
          └── java
              └── br.edu.ifpb.es.cuidarme
                  └── controller
                      ├── AgendamentoController.java
                      ├── PacienteController.java
                      ├── PagamentoController.java
                      ├── ProntuarioController.java
                      ├── PsicologoController.java
                  └── model
                      ├── Agendamento.java
                      ├── ContatoEmergencia.java
                      ├── Endereco.java
                      ├── Paciente.java
                      ├── Pagamento.java
                      ├── Prontuario.java
                      ├── Psicologo.java
                  └── service
                      ├── MenuLogadoView.java
                      ├── MenuView.java
                  ├── CuidarmeApp.java
```

## Como Executar

1. Certifique-se de ter o **Java 17+** instalado
2. Clone este repositório
```bash
git clone https://github.com/JhenniferK/cuidarme-poo.git
```
3. Vá até a raiz do projeto
```bash
cd cuidarme-poo
```
4. Compile todos os arquivos
```bash
javac -d out $(find src/main/java -name "*.java")
```
5. Execute a classe principal
```bash
java -cp out br.edu.ifpb.es.cuidarme.CuidarmeApp
```

## Documentação

Na pasta docs/ você encontrará:

- Documento de Visão – detalhamento de escopo, objetivos e funcionalidades
- Diagramas UML – representações estruturais e de casos de uso
- Checklist de Consistência – garantia de alinhamento entre requisitos e implementação

## Equipe

- Jhennifer Kelly Nicolau da Cunha - Graduanda em Análise em Desenvolvimento de Sistemas
- Joyce Gregório da Silva - Graduanda em Análise em Desenvolvimento de Sistemas

Orientador: Prof. Artur Luiz Torres de Oliveira

## Licença

Este projeto foi desenvolvido com fins acadêmicos no IFPB - Campus Esperança.
Você pode reutilizá-lo e adaptá-lo para estudos, desde que cite os autores.

---

🖤 **Obrigada por visitar este repositório!**
   

