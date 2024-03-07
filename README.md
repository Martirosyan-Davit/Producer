# Producer

## This microservice is responsible for sending messages to Kafka 📨

* If you don't already have, install it.
* To install, visit this [link](https://kafka.apache.org/quickstart).

### Alternatively, use the *following* scripts 

* STEP 1: GET KAFKA
````
 $ tar -xzf kafka_2.13-3.7.0.tgz
 $ cd kafka_2.13-3.7.0
````
* STEP 2: START THE KAFKA ENVIRONMENT

  Apache Kafka can be started using ZooKeeper.
  Run the following commands in order to start all services in the correct order:

*Start the ZooKeeper service*
````
$ bin/zookeeper-server-start.sh config/zookeeper.properties
````
Open another terminal session and run:

*Start the Kafka broker service*
````
$ bin/kafka-server-start.sh config/server.properties
````
Once all services have successfully launched, you will have a basic Kafka environment running and ready to use.

## Usage

To run the application, you need to pass variables to the `.env` file as done in the `.env.example` file.

The application runs on port 8081.

Application has one endpoint:

* **POST** `/api/v1/user/send`

Example JSON
````
{
    "name": "James",
    "age": 22
}
````