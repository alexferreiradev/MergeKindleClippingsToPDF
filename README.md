[![GitHub release](https://img.shields.io/badge/releases-v.0.0.1-yellow.svg)](https://github.com/alexferreiradev/MergeKindleClippingsToPDF/releases/tag/v0.0.1-alpha)

# Merge Kindle Clippings To PDF
A página do projeto pode ser visualizada através deste [link](https://alexferreiradev.github.io/MergeKindleClippingsToPDF/).

## Funções

* Criar arquivo PDF que está no seu Kindle com marcações no seu PC

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
    
    `# java -jar "<MergeJar>.jar;lib/*" main.MergeKindle <$PDF>`

Sendo que deve substituir `<MergeJar>` pelo nome da realease baixada e `$PDF` pelo nome do PDF, sem a extenção ".pdf", do qual deseja realizar o merge de anotações.

## Contribuições

O projeto ainda está no início. Caso tenha alguém interessado em ajudar, estamos aberto a conversa para colaborações. Para mais informações sobre o desenvolvimento, acesse [Link](/Documentation.md).

## Licença

O projeto ainda não está sob uma licença, mas está livre para qualquer coisa.
