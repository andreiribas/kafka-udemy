In order to run the application locally (outside docker compose), for instance on Intellij,
You need to add the 3 kafka hosts to /etc/hosts:

127.0.0.1 kafka-1
127.0.0.1 kafka-2
127.0.0.1 kafka-3

Before starting the project you need to run the docker compose file with the command `docker compose up -d` from the root of the project.