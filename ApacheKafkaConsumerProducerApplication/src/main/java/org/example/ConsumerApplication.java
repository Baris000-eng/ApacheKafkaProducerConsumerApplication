package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConsumerApplication {
    public static void main(String[] args) {
        Properties configuration = new Properties();
        configuration.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configuration.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, new StringDeserializer().getClass().getName());
        configuration.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, new StringDeserializer().getClass().getName());
        configuration.put(ConsumerConfig.GROUP_ID_CONFIG, "bigDataTeam1");
        KafkaConsumer<String,String>kafkaConsumer = new KafkaConsumer<String, String>(configuration);
        List<String> list = new ArrayList<String>();
        list.add("search");
        kafkaConsumer.subscribe(list);

        while(true) {
            ConsumerRecords<String,String> consumerRecords = kafkaConsumer.poll(Duration.ZERO);
            for(ConsumerRecord<String,String> record: consumerRecords) {
                String value = record.value();
                System.out.println("Consumed value: "+value);
            }
        }


    }

    //command to create a producer in console
    //sh bin/kafka-console-producer.sh --broker-list localhost:9092 --topic search
}
