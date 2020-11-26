# poc-k8s-cluster
version 2 repositorio  anterior plantilla-cluster

Proyecto base que cuenta con despliegues de componentes necesarios para control sobre una solucion completa basada en arquitecturas orientadas a microservicios y en entornos distribuidos. El cual se despliega sobre un cluster de kubernetes como plataforma orquestador y runtime de Docker para ejecucion de contenedores en los nodos los cuales incluyen.

* Balanceador de carga (Traefik + SSL/TLS para HTTPS)
* Componente de monitorizacion de trazas (EFK + Securizacion con X-Pack)
* Componente de monitorizacion de cluster (Prometeo + Grafana)
* componente de trazabilidad (Jaeger Desarrollo)***
* Kubernetes dashboard con ingress y RBAC cuenta de servicio administrador
* Un microservicio de prueba con dependencias necesarias para interaccion con componentes

Requisitos:
* Maven 3.x
* Jdk 8
* Kubernetes (kubeadm, kubelet 1.18**** y kubectl. Se recomienda instalacion con kubeadm en linux. Para Win y Mac OS es necesario instalar [Docker Desktop](https://www.docker.com/products/docker-desktop))  
* Container runtime (Docker)
* [Ejecutar registry local](https://docs.docker.com/registry/deploying/)

Para el caso de linux se utiliza la instalacion desde el repositorio de kubernetes y arrancar un plano de control minimo con la herramienta [kubeadm](https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/create-cluster-kubeadm/), para Mac OS y Windows se puede utilizar docker for desktop aunque para windows se tienen que ejecutar el contenido de los script ya que son bash (.sh)



PARA DESPLEGAR MODULOS CROSS
* Linux y Mac OS: Dentro de directorio Componentes_core/kubernetes ejecutar: **deploy**
* Windows: Ejectuar contenido dentro de archivo

PARA DESPLEGAR MICROSERVICIO DE PRUEBA
1. Instalar modulo de dependencias comun en directorio: Modulos/cima-comun: **mvn install**
2. Linux y Mac OS: Dentro de directorio de modulo Modulos/pocws ejecutar: **build**
3. Windows: Ejectuar contenido dentro de archivo

Una vez instalado el cluster y desplegados los componentes, es necesario importar el certificado publico en el navegador que se encuentra en el archivo **ca.pem** en el directorio Componentes_core/kubernetes/traefik/tls. Para conexion segura ssl

**Importante:** Para Mac OS y Windows, en el caso de Docker For Desktop es necesario ingresar los directorios compartidos para el caso de los volumenes persistentes, tambien se puede utilizar [Docker Toolbox](https://docs.docker.com/docker-for-mac/docker-toolbox/) o [minikube](https://kubernetes.io/docs/tasks/tools/install-minikube/). Cabe mencionar que este tipo de soluciones son para fines de desarrollo y no se supone que sean utilizadas para entornos productivos. Por tanto no se recomienda seguir esta guia si se desea instalar en un OS **Windows** O **MacOS** como plataforma base ya que las configuraciones de estos componentes se pretenden para entornos productivos. En su caso recomiendo instalar una maquina virtual con una instalacion Linux como OS (de preferencia distribuciones basadas en RPM). Y dentro de la misma instalar un minikube o un plano de control sencillo con kubeadm. Tambien incluye un apartado de despliegues para OpenShift si es que se cuenta con un servicio en la nube. Para el caso de Openshift en entorno de desarrollo mirar [minishift](https://www.okd.io/minishift/). Lo cual puede ser otra alternativa para usuarios de windows y mac. Dentro de cada componente core hay una carpeta **oc**

**Notas:** 
* Quedan tambien pendientes de agregar microservicio de discovery y/o centralizador de api y ejemplo de cloud config para entornos.
* ***Se agrega Jaeger de desarrollo como version inicial. En siguientes versiones se ira sustituyendo por el componente productivo.
* ****Se ha detectado que a partir de la version 1.19.* de kubelet, han dejado de mostrarse las metricas de maquina en el cadvisor como se menciona en este [issue](https://github.com/kubernetes/kubernetes/issues/95204) y en este [PR](https://github.com/kubernetes/kubernetes/pull/95210). Por lo que el tablero de grafana incluido en este repositorio no mostrara las graficas de uso de CPU y RAM en el cluster. Por lo tanto, bien se puede quedarse con la ultima version de kubelet 1.18.9, o bien, desplegar el componenente [kube-state-metrics](https://github.com/kubernetes/kube-state-metrics). No explicado ni incluido en este repositorio.

**Changelog** 
* 1.0 Version inicial


