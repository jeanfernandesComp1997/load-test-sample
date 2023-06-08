# load-test-sample
Load test spring web app sample with observability

## Prerequisites
- ### K6 installation on Ubuntu
    ```bash
    sudo gpg -k
    sudo gpg --no-default-keyring --keyring /usr/share/keyrings/k6-archive-keyring.gpg --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys C5AD17C747E3415A3642D57D77C6C491D6AC1D69
    echo "deb [signed-by=/usr/share/keyrings/k6-archive-keyring.gpg] https://dl.k6.io/deb stable main" | sudo tee /etc/apt/sources.list.d/k6.list
    sudo apt-get update
    sudo apt-get install k6
    ```

- ### Java 11

## Technologies
- spring webflux
- k6
- grafana
- prometheus
- docker

## References
- https://k6.io/docs/get-started/running-k6/
- https://grafana.com/grafana/download?edition=oss&pg=get&plcmt=selfmanaged-box1-cta1&platform=docker
- https://betterprogramming.pub/how-to-monitor-a-spring-boot-app-with-prometheus-and-grafana-22e2338f97fc
