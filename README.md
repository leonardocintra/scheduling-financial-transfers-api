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


## Playground

### Postman collection
[Download here](https://www.getpostman.com/collections/c8d14cd9bd4833aa26e7)


### Create a transfer

Eepending on the amount and scheduling date there is a **fee to be paid** on the transfer

REQUEST
```
{
    "amount": 100.00,
    "customer": {
        "name": "Airton Senna",
        "cpf": "21122233344"
    },
    "accountOrigin": "333311",
    "accountTarget": "444444",
    "scheduling": {
        "transferDate": "2020-10-15"
    }
}
```
:warning: all are required fields
- **amount**: amount to transfer
- **customer**: client's data
- **accountOrigin**: account origin
- **accountTarget**: account target
- **scheduling**: transfer data in format yyyy-MM-dd

RESPONSE
```
{
    "meta": {
        "version": "'0.0.1'",
        "server": "pc",
        "recordCount": 1,
        "limit": 50,
        "offset": 0
    },
    "records": [
        {
            "id": 1,
            "accountOrigin": "333311",
            "accountTarget": "444444",
            "amount": 100.00,
            "totalPaid": 104.0,
            "customer": {
                "id": 1,
                "name": "Airton Senna",
                "cpf": "21122233344",
                "createdAt": "2020-09-14T22:10:34.674034-03:00",
                "updatedAt": "2020-09-14T22:10:34.674034-03:00"
            },
            "scheduling": {
                "id": 1,
                "schedulingDate": "2020-09-14T22:10:34.708174-03:00",
                "transferDate": "2020-10-15",
                "createdAt": "2020-09-14T22:10:34.708174-03:00",
                "updatedAt": "2020-09-14T22:10:34.708174-03:00"
            },
            "tax": {
                "id": 1,
                "amount": 4.0,
                "taxDescription": "Operações do tipo C tem uma taxa regressiva conforme a data de transferência: de 10 até 20 dias: 8% de 20 até 30 dias: 6% de 30 até 40 dias: 4% de 40 dias ou mais e valor superior a 100.000: 2%",
                "createdAt": "2020-09-14T22:10:34.714161-03:00",
                "updatedAt": "2020-09-14T22:10:34.714161-03:00"
            },
            "createdAt": "2020-09-14T22:10:34.727547-03:00",
            "updatedAt": "2020-09-14T22:10:34.727547-03:00"
        }
    ],
    "errorMessages": null
}
```
- **totalPaid**: transfer amount plus fee amount
- **tax**: rate details
- **errorMessages**: if there is an error, it will appear here
- **createdAt**: date time the registration with the bank occurred
- **updatedAt**: date time the update with the bank occurred


### Get a transfer by CPF
- GET http://localhost:8080/v1/scheduling/transfer/customer?cpf=21122233344

will return all appointments! :smile: :smile: 

## doubts? I am available
leonardo.ncintra@outlook.com

