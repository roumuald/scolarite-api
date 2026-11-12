
output "url_application" {
  description = "URL de vérification de santé de l'application"
  value       = "http://localhost:${var.port_app}/actuator/health"
}
