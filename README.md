# Getting Started

## Documentation
Spring (Boot): 
https://docs.google.com/presentation/d/1T3CswEdBDMw9o728fTWQHnnBEWnGD6b3UqgbUoeb0wU/edit?usp=sharing

Rest APIs:
https://docs.google.com/presentation/d/1W-Y-E7hdAlKnaVKMoqnZy3i71fCZ5f51icSvqDb8Mkc/edit?usp=sharing

### podman commands

run mysql container with podman

```shell
podman run -d -p 3307:3306 -e MYSQL_DATABASE=mydatabase -e MYSQL_USER=myuser -e MYSQL_PASSWORD=secret -e MYSQL_ROOT_PASSWORD=alguno mysql:9.0.0
```

```shell

### Installation

```shell
./mvnw install
```

### Run the application

```shell
./mvnw spring-boot:run
```

### Run the tests

```shell
./mvnw test
```

### Build the application

```shell
./mvnw clean package
```



