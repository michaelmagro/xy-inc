# xy-inc
Zup test for job

## Instruções
Ter um servidor tomcat8 rodando na porta 8080 e dar o deploy nele pelo maven, ou só importar pelo STS (Spring Suite Tools) e rodar por lá. Foi usado o banco de dados H2 (em memória), dispensando o uso de scripts. Basta subir o servidor e cadastrar os POIS via requisição POST (exemplo abaixo)

## Usando
Listar todos os POI
```sh
GET http://localhost:8080/poi
```

Salvar um POI
```sh
POST http://localhost:8080/poi

{
	"name" : "Pub",
	"coordinateX" : 12,
	"coordinateY" : 8

}
```

Busca por proximidade
```sh
GET http://localhost:8080/poi/listByProximity/20/10/1
```


