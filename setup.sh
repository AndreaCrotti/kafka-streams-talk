#!/usr/bin/env bash

set -x

ZOOKEEPER="localhost:2181"

function delete_topic {
    kafka-topics --delete --zookeeper $ZOOKEEPER \
                 --if-exists \
                 --topic $1
}

function create_topic {
    # drop the topic first if it exists??
    kafka-topics --create --zookeeper $ZOOKEEPER \
                 --replication-factor 1 \
                 --partitions 1 \
                 --if-not-exists \
                 --topic $1
}

delete_topic words
delete_topic words-count

create_topic words
create_topic words-count
