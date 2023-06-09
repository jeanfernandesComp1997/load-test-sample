# load-test-sample
Load test spring web app sample with observability

## Technologies
- spring webflux
- k6
- grafana
- prometheus
- docker
- wiremock

## Prerequisites
- ### K6 installation on Ubuntu
    ```bash
    sudo gpg -k
    sudo gpg --no-default-keyring --keyring /usr/share/keyrings/k6-archive-keyring.gpg --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys C5AD17C747E3415A3642D57D77C6C491D6AC1D69
    echo "deb [signed-by=/usr/share/keyrings/k6-archive-keyring.gpg] https://dl.k6.io/deb stable main" | sudo tee /etc/apt/sources.list.d/k6.list
    sudo apt-get update
    sudo apt-get install k6
    ```

- ### Java 17

## Run
  ```bash
  docker-compose build
  ```

  ```bash
  docker-compose up
  ```

  Run to project directory and run:
  ```bash
  k6 run load-test.js
  ```

  ## Grafana dashboards id's used
  - 12900
  - 4701 (JVM Micrometer)

  ## URI's
  - Grafana: http://localhost:3000
  - Prometheus: http://localhost:9090
  - Prometheus for add in Grafana: http://10.5.0.4:9090
  - API: GET: http://localhost:8081/v1/characters/1

  ## WireMock Config
  Add mock URI:
```curl
curl --location 'http://localhost:8080/__admin/mappings/import' \
--header 'Content-Type: application/json' \
--data '{
    "mappings": [
        {
            "request": {
                "method": "GET",
                "url": "/api/character/1"
            },
            "response": {
                "status": 200,
                "jsonBody": {
                    "id": 1,
                    "name": "Rick Sanchez",
                    "status": "Alive",
                    "species": "Human",
                    "type": "",
                    "gender": "Male",
                    "origin": {
                        "name": "Earth (C-137)",
                        "url": "https://rickandmortyapi.com/api/location/1"
                    },
                    "location": {
                        "name": "Earth (Replacement Dimension)",
                        "url": "https://rickandmortyapi.com/api/location/20"
                    },
                    "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                    "episode": [
                        "https://rickandmortyapi.com/api/episode/1",
                        "https://rickandmortyapi.com/api/episode/2"
                    ],
                    "url": "https://rickandmortyapi.com/api/character/1",
                    "created": "2017-11-04T18:48:46.250Z"
                },
                "headers": {
                    "Content-Type": "application/json"
                }
            }
        }
    ],
    "importOptions": {
        "duplicatePolicy": "IGNORE",
        "deleteAllNotInImport": true
    }
}'
```

Check if mock is OK:
```curl
curl --location 'http://localhost:8080/api/character/1'
```

## References
- https://k6.io/docs/get-started/running-k6/
- https://grafana.com/grafana/download?edition=oss&pg=get&plcmt=selfmanaged-box1-cta1&platform=docker
- https://betterprogramming.pub/how-to-monitor-a-spring-boot-app-with-prometheus-and-grafana-22e2338f97fc
