# seaSolucions_test


test terminado</br>

troquei alguns nomes como setor para <b>sector</b> e cargo para <b>employment</b> e empregado para <b>employee</b></br>

Spring boot versão 2.5.4</br>
java 11</br>
postegresql com jpa hibernate para persistencia das informações no banco</br>
utilização do gladle</br>
swagger</br>
cobertura de teste e2e, integration</br>
criação de setor e cargo com nomes unicos, se tiver algum nome sendo usado dispara um erro avisando</br>
verificação se cpf é valido, usei o @CPF mas tinha que usar um cpf valido, então comentei para poder passar nos testes</br>
atualização de setor, cargo, cpf, com verificação se o nome já esta em uso ou o cpf</br>
busca todos os empregados</br>
busca todos os cargos</br>
busca todos os setores</br>
sobre o teste</br>

eu nunca usei o gladle, então foi um pouco complicado instalar as dependencias, especialmente no inicio</br>
depois foi tranquilo</br>

inicialmente ia fazer a relação de tabela com @manyToOne e @oneToMany, mas não consegui fazer, eu já tinha tentado</br>
antes, porem não funcionava baseado nos tutoriais que segui, entendi o que precisava fazer, mas não funcionava, 
talvez um problema no meu banco</br></br>

apesar de não te conseguido, eu já tinha feito esse tipo de relação com prisma orm com postegresql no nodejs</br>

criei as entidades, usei alguns dto, criei utils pra lidar com a criação, verificação da criação busca etc</br>

uma coisa que achei complicada de fazer foi na rota sectorFindController -> findAll, eu nunca tinha feito isso no java</br>
ele faz uma relação bem grande, pegando os setores, os ids dos cargos associados ao setor, e aos empregados associados ao cargo

 
 
 
Tests passed: 57
