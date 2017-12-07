zookeeper-start:
	./confluent-4.0.0/bin/zookeeper-server-start ./confluent-4.0.0/etc/kafka/zookeeper.properties

kafka-start:
	./confluent-4.0.0/bin/kafka-server-start ./confluent-4.0.0/etc/kafka/server.properties

word-count:
	lein run -m kafka-streams-talk.word-count

kafka-shell:
	docker-compose exec kafka bash
