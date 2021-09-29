This project is gateway to send message to kafka topic. In future many APIs can be added as per the use case.  

To set up kafka & send message please follow below steps. 

1. download kafka from https://kafka.apache.org/downloads or you can use docker image also.
2. Execute below commands from your kafka installed directory e.g D:\kafka_2.12-2.8.0
      
      
      a. Start Zookeeper
        zookeeper-server-start.bat config\zookeeper.properties
        
    
       b. Open another shell - kafka is at localhost:9092
        kafka-server-start.bat config\server.properties
        
    
       c. create input topic
        kafka-topics --zookeeper localhost:2181 --create --partitions  1 --topic book-catalogue_topic --replication-factor=1
    
       d. describe created topic
         kafka-topics --zookeeper localhost:2181 --describe --topic book-catalogue_topic
    
       e. produce message manually - we are going to prouce programatically as per exercise
         kafka-console-producer.bat --broker-list localhost:9092 --topic kafka-console-producer.bat --broker-list localhost:9092 --topic
    
       f. Observer the message - Post event like save book, Delete Book, Update book etc.
        kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic book-catalogue_topic --from-beginning
3. start SpringBootApplication BookCatalogueKafkaApplication. 
4. Access the restApi with this URL http://localhost:8080/bookCatalogue-kafka/producer?event=test. 
5. You should see message reached to kafka as output of 2.e command.
