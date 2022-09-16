package org.example;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Scanner;


/**
 * Hello world!
 *
 */
public class ProducerApplication
{
    public static void main( String[] args ) {

       Properties configuration = new Properties();
       configuration.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
       configuration.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, new StringSerializer().getClass().getName());
       configuration.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, new StringSerializer().getClass().getName());
       Producer producer = new KafkaProducer<String,String>(configuration);
       ProducerRecord<String,String> record = new ProducerRecord<String,String>("search","Ayakkabi");
       producer.send(record);

       ProducerRecord<String,String>rec = new ProducerRecord<String, String>("search", "Çanta");


       producer.send(rec);

       Scanner scanner = new Scanner(System.in);
       while(true) {
           System.out.println("Please enter the data you want to send to Kafka: ");
           String data = scanner.nextLine();
           ProducerRecord<String,String>r = new ProducerRecord<String,String>("search",data);
           producer.send(r);
       }

        //producer.close(); (for closing the producer).
    }

    //bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic search --from-beginning
    //linux command to search the sent data in macos terminal.
}
