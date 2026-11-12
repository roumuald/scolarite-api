
terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.0"
    }
  }
}

provider "docker" {
  host = var.docker_host
}

# Réseau Docker
resource "docker_network" "reseau" {
  name = "scolarite-net"
}

# Volume PostgreSQL
resource "docker_volume" "donnees" {
  name = "scolarite-data"
}

# Image PostgreSQL
resource "docker_image" "postgres" {
  name         = "postgres:16-alpine"
  keep_locally = true
}

# Image de l'application Spring Boot
resource "docker_image" "app" {
  name         = var.image_app
  keep_locally = true
}

# Conteneur PostgreSQL
resource "docker_container" "db" {
  name  = "scolarite-db"
  image = docker_image.postgres.image_id

  restart = "unless-stopped"

  networks_advanced {
    name = docker_network.reseau.name
  }

  env = [
    "POSTGRES_DB=scolarite",
    "POSTGRES_USER=${var.db_user}",
    "POSTGRES_PASSWORD=${var.db_password}",
  ]

  volumes {
    volume_name    = docker_volume.donnees.name
    container_path = "/var/lib/postgresql/data"
  }
}

# Conteneur Spring Boot
resource "docker_container" "app" {
  name  = "scolarite-app"
  image = docker_image.app.image_id

  restart = "unless-stopped"

  networks_advanced {
    name = docker_network.reseau.name
  }

  ports {
    internal = 8081
    external = var.port_app
  }

  env = [
    "SPRING_PROFILES_ACTIVE=prod",
    "DB_URL=jdbc:postgresql://scolarite-db:5432/scolarite",
    "DB_USER=${var.db_user}",
    "DB_PASSWORD=${var.db_password}",
    "JAVA_TOOL_OPTIONS=-Xmx256m",
  ]

  depends_on = [docker_container.db]
}
