sudo apt-get update
sudo apt-get install docker.io
git clone https://github.com/Radziu1202/arrow.git
docker build /arrow/arrowServer -t arrowserver:dev
docker build /arrow/clientarrow -t arrowclient:dev
docker run --rm -p 8815:8815 arrowserver:dev
docker run --rm  arrowclient:dev