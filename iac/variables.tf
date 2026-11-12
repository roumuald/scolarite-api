
variable "docker_host" {
  type    = string
  default = "npipe:////.//pipe//docker_engine"
}

variable "image_app" {
  type = string
}

variable "port_app" {
  type    = number
  default = 8082
}

variable "db_user" {
  type    = string
  default = "scolarite"
}

variable "db_password" {
  type      = string
  sensitive = true
}
