# 25E2_3-TP3
## Ponto de Partida
Você foi contratado como desenvolvedor júnior para trabalhar em uma equipe que está construindo um sistema de integração com serviços externos. Para praticar os conceitos de HTTP e REST, a empresa disponibilizou duas APIs de simulação para que você possa desenvolver suas habilidades em Java usando a biblioteca HttpURLConnection.

Essas APIs simulam sistemas de gerenciamento de entidades e itens. São projetadas para aprendizado: respondem de forma previsível, não exigem autenticação e permitem a prática de operações como GET, POST, PUT, DELETE, OPTIONS, entre outras.

## Orientações Gerais
Todos os exercícios devem ser feitos em Java, utilizando HttpURLConnection
Utilize BufferedReader para ler as respostas e DataOutputStream para enviar dados
Sempre trate e imprima o código de status HTTP e a resposta do servidor
Organize os exercícios em classes distintas ou métodos bem definidos dentro do mesmo projeto
## APIs utilizadas
- API de Entidades (Stateless): https://apichallenges.eviltester.com/sim/entities  
Ideal para praticar requisições básicas sem persistência real.

- Simple API (com persistência temporária): https://apichallenges.eviltester.com/simpleapi/items  
Permite testar operações completas com controle de dados, incluindo geração de ISBNs.

### Exercício 1 GET simples de todas as entidades

Endpoint:GET https://apichallenges.eviltester.com/sim/entities

Realize uma requisição GET para listar todas as entidades. Imprima no console o código de status e o corpo da resposta (JSON com 10 entidades fixas).

### Exercício 2 GET de entidade específica

Endpoint:GET https://apichallenges.eviltester.com/sim/entities/1 (e IDs de 2 a 8)

Recupere e imprima os dados de entidades específicas. Observe os conteúdos e compare as respostas.

### Exercício 3 GET de entidade inexistente

Endpoint:GET https://apichallenges.eviltester.com/sim/entities/13

Faça uma requisição para um ID inexistente. O programa deve capturar o status 404 e exibir mensagem adequada.

### Exercício 4 GET com parâmetros na URL

Endpoint:GET https://apichallenges.eviltester.com/sim/entities?categoria=teste&limite=5

Monte uma URL com parâmetros fictícios. Envie a requisição e imprima a URL final montada e o código de resposta.

### Exercício 5 POST criando uma nova entidade

Endpoint:POST https://apichallenges.eviltester.com/sim/entities

Envie o seguinte JSON:

{"name": "aluno"}

Imprima o corpo da resposta e identifique o ID gerado (geralmente 11).

### Exercício 6 GET da entidade criada

Endpoint:GET https://apichallenges.eviltester.com/sim/entities/11

Recupere a entidade recém-criada usando seu ID e verifique o conteúdo retornado.

### Exercício 7 POST para atualizar uma entidade

Endpoint:POST https://apichallenges.eviltester.com/sim/entities/10

Atualize a entidade 10 com o JSON:

{"name": "atualizado"} 

Verifique a mudança por meio de uma requisição GET posterior.

### Exercício 8 PUT para atualizar entidade

Endpoint:PUT https://apichallenges.eviltester.com/sim/entities/10

Use PUT com o mesmo JSON do exercício anterior e avalie o comportamento da API em comparação com POST.

### Exercício 9 DELETE de entidade válida

Endpoint:DELETE https://apichallenges.eviltester.com/sim/entities/9  

Delete a entidade 9 e depois tente acessá-la com um GET para confirmar o retorno 404.

### Exercício 10 DELETE inválido

Endpoint:DELETE https://apichallenges.eviltester.com/sim/entities/2  

Tente deletar uma entidade que não pode ser removida. Capture o código de status (espera-se 403 ou 405) e mostre a resposta.

### Exercício 11 OPTIONS com verificação de métodos

Endpoint:OPTIONS https://apichallenges.eviltester.com/sim/entities  

Envie uma requisição OPTIONS e imprima os métodos HTTP permitidos presentes no cabeçalho Allow.

### Exercício 12 Experimentos com a Simple API

GET todos os itens  
Endpoint:GET https://apichallenges.eviltester.com/simpleapi/items  

Gerar ISBN aleatório  
Endpoint:GET https://apichallenges.eviltester.com/simpleapi/randomisbn  

Criar item com POST  
Endpoint:POST https://apichallenges.eviltester.com/simpleapi/items  

{  
"type": "book",  
"isbn13": "<inserir o ISBN gerado>",  
"price": 5.99,  
"numberinstock": 5  
}  

Atualizar item com PUT  
Endpoint:PUT https://apichallenges.eviltester.com/simpleapi/items  
  Remover item com DELETE
Endpoint:DELETE https://apichallenges.eviltester.com/simpleapi/items/<isbn>  
