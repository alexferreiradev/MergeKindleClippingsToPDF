# Merge Kindle Clippings To PDF

##Funções

* Criar arquivo PDF que está no seu Kindle com marcações no seu PC

##Modo de utilizaçao:

1. Fazer download de arquivo MyClipping.txt para o seu PC e colocar em uma diretório $dir.
2. Copiar o PDF que deseja fazer o merge na diretório $dir.
2. Fazer download de JAR do [PDFCLown]() e colocar na pasta lib que deve estar dentro do diretório $dir.
2. Fazer download do JAR do projeto que está dentro da pasta `realeases` no repositório.
2. Rodar comando java de dentro do diretório $dir:

    `java -jar "<MergeJar>.jar;lib/*" main.MergeKindle <$PDF>`

Sendo que deve substituir `<MergeJar>` pelo nome da realease baixada e `$PDF` pelo nome do PDF, sem a extenção ".pdf", do qual deseja realizar o merge de anotações.

##Contribuições

O projeto ainda está no início. Caso tenha alguém interessado em ajudar, estamos aberto a conversa para colaborações. Para mais informações sobre o desenvolvimento, acesse [Link](https://github.com/alexferreiradev/MergeKindleClippingsToPDF/blob/master/Documentation.md).
