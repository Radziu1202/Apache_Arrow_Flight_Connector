# Airbyte connectors for Apache Arrow Flight

## Setup env
For testing, AWS EC2 has been used. 

Requirements:
- `python 3.9` with `venv`
- `docker`

If the platform doesn't have one:
```
sudo apt-get update
sudo apt-get install docker.io
sudo apt-get install python3.9
sudo apt-get install pip3
sudo pip3 install vitrualenv
``` 

Creating python virtualenv (separately for each docker image directory):
```
virtualenv venv
```


Building docker images:
```
sudo docker build arrow-client -t arrow-client:dev
sudo docker build arrow-server -t arrow-server:dev
sudo docker build source-apache-arrow -t airbyte/source-apache-arrow:dev
sudo docker build destination-apache-arrow -t airbyte/destination-apache-arrow:dev

```

Running docker images:
```
docker run --rm -p 8815:8815 arrow-server:dev
docker run --rm arrow-client:dev
docker run --rm -v $(pwd)/secrets airbyte/source-apache-arrow:dev read --config /secrets/config.json --catalog /secrets/catalog.json
docker run --rm -v $(pwd)/secrets airbyte/destination-apache-arrow:dev write --config /secrets/config.json --catalog /secrets/catalog.json
```


