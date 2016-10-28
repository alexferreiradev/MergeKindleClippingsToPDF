[![GitHub release](https://img.shields.io/badge/releases-v.0.0.1-yellow.svg)](https://github.com/alexferreiradev/MergeKindleClippingsToPDF/releases/tag/v0.0.1-alpha)

# Merge Kindle Clippings To PDF
A página do projeto pode ser visualizada através deste [link](https://alexferreiradev.github.io/MergeKindleClippingsToPDF/).

## Funções

* Criar arquivo PDF que está no seu Kindle com marcações no seu PC

## Modo de utilizaçao:

1. Fazer download de arquivo MyClipping.txt para o seu PC e colocar em uma diretório $dir.
2. Copiar o PDF que deseja fazer o merge na diretório $dir.
2. Fazer download de JAR do [PDFCLown](https://sourceforge.net/projects/clown/) e colocar na pasta lib que deve estar dentro do diretório $dir.
2. Fazer download do [JAR](https://github.com/alexferreiradev/MergeKindleClippingsToPDF/releases/tag/v0.0.1-alpha) do projeto.
2. Rodar comando java de dentro do diretório $dir:

    `java -jar "<MergeJar>.jar;lib/*" main.MergeKindle <$PDF>`

Sendo que deve substituir `<MergeJar>` pelo nome da realease baixada e `$PDF` pelo nome do PDF, sem a extenção ".pdf", do qual deseja realizar o merge de anotações.

## Contribuições

O projeto ainda está no início. Caso tenha alguém interessado em ajudar, estamos aberto a conversa para colaborações. Para mais informações sobre o desenvolvimento, acesse [Link](/Documentation.md).

## Licença

O projeto ainda não está sob uma licença, mas está livre para qualquer coisa.
