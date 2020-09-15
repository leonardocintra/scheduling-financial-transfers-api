# Cash Machine - API
![image](https://media.giphy.com/media/ADgfsbHcS62Jy/giphy.gif)


## How to use
### Requisites
- Java 14 (recommended to use the [sdkman](https://sdkman.io/))
- [Maven](https://maven.apache.org/)
- IDE (Visual Studio Code, IntelliJ, Eclipse, etc)

### Usage
```
git clone https://github.com/leonardocintra/scheduling-financial-transfers-api
cd scheduling-financial-transfers-api
```

Open in your favorite IDE

### Run tests
```
mvn clean test
```


## Documentation
### Java 14
Was requested by the client to be developed in the latest version of java.

### MVC Architecture
- Facilitates code reuse
- Easy to maintain and add features
- Greater team integration and / or division of tasks
- Several technologies are adopting this architecture
- Easy to keep your code always clean

### Understanding the project folders
- **business**: contains the business rules. Ex: creation of a bank transfer, search for the customer's CPF
- **commons**: contains contract standards, to facilitate the reuse of the code with all resources (controllers, exceptions, etc.)
- **model**: project entities
- **repositoy**: database access layer
- **v1**: version 1 (rules for version 1). Defined contract. Customers can use different versions
- **v1/controller**: I/O datas 
- **v1/dto**: data transport between different components of a system, different instances or processes of a distributed system or different systems via serialization.
- **v1/mapper**: dto mapping with model 
- **test**: test of project

### Spring Boot
- It helps to optimize your time and increase your productivity, as it simplifies application development.




