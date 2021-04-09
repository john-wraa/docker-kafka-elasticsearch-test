# Log

# Environment setup
- All Kafka Development environment using Docker commands
```
cd docker
docker-compose -f zk-single-kafka-single.yml up -d
docker-compose -f zk-single-kafka-single.yml stop
docker-compose -f zk-single-kafka-single.yml down -v
```

- Only Kafka Development environment using Docker commands
```
cd docker
docker-compose -f zk-single-kafka-single.yml up kafka1 -d
docker-compose -f zk-single-kafka-single.yml stop kafka1
docker-compose -f zk-single-kafka-single.yml down kafka1
```

- Only ELK Development environment using Docker commands
```
cd docker
docker-compose -f zk-single-kafka-single.yml up kibana -d
docker-compose -f zk-single-kafka-single.yml stop kibana
docker-compose -f zk-single-kafka-single.yml down kibana
```

# Kafka topic setup
```
# kafka-basics
kafka-topics --bootstrap-server localhost:9092 --topic first_topic --create --partitions 3 --replication-factor 1

# twitter-samples
kafka-topics --bootstrap-server localhost:9092 --create --topic twitter_tweets --partitions 6 --replication-factor 1
kafka-topics --bootstrap-server localhost:9092 --create --topic important_tweets --partitions 3 --replication-factor 1
kafka-topics --bootstrap-server localhost:9092 --create --topic twitter_status_connect --partitions 3 --replication-factor 1
kafka-topics --bootstrap-server localhost:9092 --create --topic twitter_deletes_connect --partitions 3 --replication-factor 1
kafka-consumer-groups --bootstrap-server localhost:9092 --group kafka-demo-elasticsearch --reset-offsets --execute --to-earliest --topic twitter_tweets
kafka-consumer-groups --bootstrap-server localhost:9092 --describe --group kafka-demo-elasticsearch

```


# Github setup
```
git remote add origin https://github.com/john-wraa/docker-kafka-elasticsearch-test.gi
```
