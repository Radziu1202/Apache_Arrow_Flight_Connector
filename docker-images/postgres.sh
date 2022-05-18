docker run \
  -p 5432:5432 \
  -e POSTGRES_USER=arrow \
  -e POSTGRES_PASSWORD=mysecretpassword \
  -v "$(pwd)"/postgres-setup.sql:/docker-entrypoint-initdb.d/postgres-setup.sql:ro \
  -d postgres:latest
