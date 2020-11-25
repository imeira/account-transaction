## Author
Igor Meira de Jesus<br>
meira.igor@gmail.com

## Documentação da Bibioteca Utilizada
### https://docs.spring.io


## Descrição do APP
#### Rotina de transações
Cada portador de cartão (cliente) possui uma conta com seus dados.
A cada operação realizada pelo cliente uma transação é criada e associada à sua
respectiva conta.
Cada transação possui um tipo (compra a vista, compra parcelada, saque ou pagamento),
um valor e uma data de criação.
Transações de tipo compra e saque são registradas com valor negativo , enquanto
transações de pagamento são registradas com valor positivo .
<br>
<br>

## Funcionamento / Instruções
Startar o projeto SpringBoot conforme comando abaixo:
<br>
mvn spring-boot:run (linux) 
<br>ou <br>
mvnw spring-boot:run (windows)
<br><br>
Após procedimento acima, acessar a documentação da API via link abaixo.
<br>
## Documentação da API
http://localhost:8080/swagger-ui.html
<br>
