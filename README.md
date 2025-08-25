# Cloud Prometheus
## Projektbeschreibung
Eine REST-API die im Rahmen eines Cloud Projektes durch einen Self-Managed Prometheus Server obersivert wird.

## Voraussetzungen
Stelle sicher, dass folgende Tools installiert sind:

- [Docker](https://www.docker.com/)
- [Maven](https://maven.apache.org/)
- [Java](https://adoptium.net/)
- [Git](https://git-scm.com/)

## Projekt klonen und bauen

```bash
git clone https://github.com/SchubideiCroissant/CloudPrometheus.git
cd CloudPrometheus

# Projekt bauen und Abhängigkeiten kopieren
mvn clean package dependency:copy-dependencies
```

## Anwendung starten

```bash
# Container bauen
docker-compose build

# Anwendung starten
docker compose up
```

## Zugriff auf die Dienste

[Rest Schnittstelle](http://localhost:8080/rest/artikel/)
[Prometheus](http://localhost:9090/) 
[Grafana](http://localhost:3000/)
[Alertmanager](http://localhost:9093/)
