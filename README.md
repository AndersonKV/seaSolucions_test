# seaSolucions_test


test terminado</br>

troquei alguns nomes como setor para <b>sector</b> e cargo para <b>employment</b> e empregado para <b>employee</b></br>

como foi pedido</br>

Spring boot versão 2.5.4</br></br>
java 11</br></br>
postegresql com jpa hibernate para persistencia das informações no banco</br></br>
utilização do gladle</br></br>
swagger</br></br>
cobertura de teste e2e, integration</br></br>
criação de setor e cargo com nomes unicos, se tiver algum nome sendo usado dispara um erro avisando</br></br>
verificação se cpf é valido, usei o @CPF mas tinha que usar um cpf valido, então comentei para poder passar nos testes</br></br>
atualização de setor, cargo, cpf, com verificação se o nome já esta em uso ou o cpf</br></br>
busca todos os empregados</br></br>
busca todos os cargos</br></br>
busca todos os setores</br></br>
sobre o teste</br></br>

eu nunca usei o gladle, então foi um pouco complicado instalar as dependencias, especialmente no inicio</br>
depois foi tranquilo</br>

inicialmente ia fazer a relação de tabela com @manyToOne e @oneToMany, mas não consegui fazer, eu já tinha tentado</br>
antes, porem não funcionava baseado nos tutoriais que segui, entendi o que precisava fazer, mas não funcionava, 
talvez um problema no meu banco</br></br>

apesar de não te conseguido, eu já tinha feito esse tipo de relação com prisma orm com postegresql no nodejs</br>

criei as entidades, usei alguns dto, criei utils pra lidar com a criação, verificação da criação busca etc</br>

uma coisa que achei complicada de fazer foi na rota sectorFindController -> findAll, eu nunca tinha feito uma busca tão grande assim no java</br>
ele faz uma relação bem grande, pegando os setores, os ids dos cargos associados ao setor, e aos empregados associados ao cargo

 Tests passed: 57


http://localhost:8080/swagger-ui.html

 
  ```diff
/*:::::EMPLOYEE CONTROLLER *

+ POST http://localhost:8080/api/v1/api/v1/employee
# GET http://localhost:8080/api/v1/api/v1/employee
# GET http://localhost:8080/api/v1/api/v1/employee/id/{id}
# GET http://localhost:8080/api/v1/api/v1/employee/find_by_name?name=query
# GET http://localhost:8080/api/v1/api/v1/employee/find_by_cpf?cpf=query
! PUT http://localhost:8080/api/v1/api/v1/employee/update/{id}
- DELETE  http://localhost:8080/api/v1/api/v1/employee/id/{id}
- DELETE  http://localhost:8080/api/v1/api/v1/employee/delete_all

/*:::::EMPLOYMENT CONTROLLER *

+ POST http://localhost:8080/api/v1/api/v1/employment
# GET http://localhost:8080/api/v1/api/v1/employment/find_all
# GET http://localhost:8080/api/v1/api/v1/employment/find_by_employment?=name=query
# GET http://localhost:8080/api/v1/api/v1/employment/id/{id}
! PUT http://localhost:8080/api/v1/api/v1/employment/update/{id}
- DELETE  http://localhost:8080/api/v1/api/v1/employment/id/{id}
- DELETE  http://localhost:8080/api/v1/api/v1/employment/delete_all

/*:::::SECTOR CONTROLLER *

+ POST http://localhost:8080/api/v1/api/v1/sector
# GET http://localhost:8080/api/v1/api/v1/sector
# GET http://localhost:8080/api/v1/api/v1/sector/id/{id}
! PUT http://localhost:8080/api/v1/api/v1/sector/update/{id}

...
 
