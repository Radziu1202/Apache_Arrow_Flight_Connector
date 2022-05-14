docker run \
  -p 27017:27017 \
  -e MONGO_INITDB_ROOT_USERNAME=admin \
  -e MONGO_INITDB_ROOT_PASSWORD=mysecretpassword \
  -e MONGO_INITDB_DATABASE=arrow \
  -v "$(pwd)"/mongo-setup.js:/docker-entrypoint-initdb.d/mongo-setup.js:ro \
  -d mongo:latest
