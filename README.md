[![GitHub release](https://img.shields.io/badge/releases-v.0.0.1-yellow.svg)](https://github.com/alexferreiradev/MergeKindleClippingsToPDF/releases/tag/v0.0.1-alpha)

# Merge Kindle Clippings To PDF
A página do projeto pode ser visualizada através deste [link](https://alexferreiradev.github.io/MergeKindleClippingsToPDF/).

## Funcionalidade

* Criar arquivo PDF que está no seu Kindle, com marcações, para o seu PC

## Modo de utilizaçao:

O Merge requisita um ambiente com o JAVA instalado, o arquivo .txt criado pelo Kindle e uma biblioteca que poderá ser baixada. Portanto, siga estes passos para execução:

1. Confira a instalação do Java com os passos descritos através deste [link](https://www.java.com/pt_BR/download/help/ie_online_install.xml#test) ou digite no terminal: `java -version`.
1. Fazer cópia do arquivo MyClipping.txt, o qual está dentro do seu Kindle, para o seu PC e colocar em um diretório $dir.
2. Copiar o PDF que deseja adicionar as marcações no diretório $dir.
2. Fazer download de JAR do [PDFCLown](https://sourceforge.net/projects/clown/) e colocar na pasta lib que deve estar dentro do diretório $dir.
2. Fazer download do [JAR](https://github.com/alexferreiradev/MergeKindleClippingsToPDF/releases/tag/v0.0.1-alpha) do projeto.
1. Abrir um terminal (Linux e MAC) ou CMD (Windows) e entrar no diretório $dir:
    1. `# cd "$dir"`, sendo que deve substituir $dir pelo caminho para o seu diretório $dir.
    1. Testar o Java com: `# java -version`, deve mostrar a versão instalada do seu JAVA
    1. Rodar comando:
    
    `# java -cp "<MergeJar>.jar;lib/*" main.MergeKindle <$PDF>`

Sendo que deve substituir `<MergeJar>` pelo nome da realease baixada e `$PDF` pelo nome do PDF, sem a extenção ".pdf", do qual deseja realizar o merge de anotações.

## Limitações

1. **Marcações duplicadadas**
 - O Merge ainda não está trabalhando com a informação de posição, somente faz a busca pelo texto marcado. Portanto, um texto marcado em uma página, será procurado em todas as páginas do PDF, ou seja, terá mais de uma marcação, caso seja encontrado o mesmo texto em páginas diferentes.
1. **Erro com alguns caracteres UNICODE**
 - O Merge utiliza a biblioteca PDFClown, a qual está com um bug aberto a respeito deste erro. Enquanto não resolverem o problema ou não ser feita a troca para outra Lib, ele terá este erro.
1. **Trabalha com um PDF por vez**
 - Será feito marcação em um PDF por execução. O Merge não realiza a busca para todos PDFs listados no arquivo .txt.

## Contribuições

O projeto ainda está no início. Caso tenha alguém interessado em ajudar, estamos aberto a conversa para colaborações. Para mais informações sobre o desenvolvimento, acesse [Link](/Documentation.md).

## Licença

O projeto está sob a licença do MIT, veja neste [Link](/License.txt).
