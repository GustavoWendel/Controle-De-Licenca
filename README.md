# Controle-De-Licenca

Trata-se de uma API que controla a licença de vários produtos dos vários clientes de uma empresa.

### Começando

Para executar o projeto, será necessário instalar os seguintes programas:

JDK 10: Necessário para executar o projeto Java
Maven 3.5.3: Necessário para realizar o build do projeto Java
Eclipse: Para desenvolvimento do projeto

### Desenvolvimento

Para iniciar o desenvolvimento, é necessário clonar o projeto do GitHub num diretório de sua preferência:

cd "diretorio de sua preferencia"
git clone [https://github.com/GustavoWendel/Controle-De-Licenca.git]

### Construção

Para construir o projeto com o Maven, executar os comando abaixo:

mvn clean install

O comando irá baixar todas as dependências do projeto e criar um diretório target com os artefatos construídos, que incluem o arquivo jar do projeto. Além disso, serão executados os testes unitários, e se algum falhar, o Maven exibirá essa informação no console.

### Features

O projeto pode ser usado para controlar clientes, produtos e licenças adquiridas destes produtos numa empresa.

### Configuração

Para executar o projeto, é necessário utilizar o Eclipse, para que o mesmo identifique as dependências necessárias para a execução no repositório .m2 do Maven. Uma vez importado o projeto, será criado um arquivo .classpath que irá informar qual a classe principal para a execução.

### Testes
Para rodar os testes, utilize o comando abaixo:

mvn test

### Contribuições

Contribuições são sempre bem-vindas! Para contribuir lembre-se sempre de adicionar testes unitários para as novas classes com a devida documentação.

### Licença

Não se aplica.
