docker build arrow-client -t arrow-client:dev
docker build arrow-server -t arrow-server:dev
docker build source-apache-arrow -t airbyte/source-apache-arrow:dev
docker build destination-apache-arrow -t airbyte/destination-apache-arrow:dev
docker run --rm -p 8815:8815 arrow-server:dev
docker run --rm arrow-client:dev
