# Spring Async Controller

Project for testing async controller in Spring Boot.

## How to run load tests

Sync calls
```bash
k6 run --env TEST_TYPE=sync --summary-export=load-test/sync-summary.json load-test/load-test.js
```

Async calls
```bash
k6 run --env TEST_TYPE=async --summary-export=load-test/async-summary.json load-test/load-test.js
```