# poc-k8s-cluster
version 2 repositorio  anterior plantilla-cluster

Proyecto base que cuenta con despliegues de componentes necesarios para control sobre una solucion completa basada en arquitecturas orientadas a microservicios y en entornos distribuidos. El cual se despliega sobre un cluster de kubernetes como plataforma orquestador y runtime de Docker para ejecucion de contenedores en los nodos. El cual contiene los siguientes componentes:

* Balanceador de carga (Traefik + SSL/TLS para HTTPS)
* Componente de monitorizacion de trazas (EFK + Securizacion con X-Pack)
* Componente de monitorizacion de cluster (Prometeo** + Grafana)
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

Una vez instalado el cluster y desplegados los componentes, es necesario importar el certificado publico en el navegador que se encuentra en el archivo **ca.pem** en el directorio Componentes_core/kubernetes/traefik/tls. Para conexion segura ssl.

**NOTA:** Es probable que en las versiones mas recientes de **Mac OS** "Catalina" y "Big Sur" se siga mostrando como sitio inseguro y en el caso de Chrome el siguiente error: ERR_CERT_VALIDITY_TOO_LONG; a pesar de haber sido agregado el certificado, esto debido a las [politicas](https://support.apple.com/en-us/HT210176) que tiene establecidas Mac OS para certificados de confianza y en este repositorio, el certificado de prueba que he generado y autofirmado, le he establecido una caducidad para 10 años, lo cual no entra dentro de los requerimientos. Para el caso de **Windows 10**, se ha probado con la ultima actualizacion en los navegadores Chrome y Edge y acepta el certificado sin error alguno.

Agregar las siguientes entradas al archivo **hosts**:\
127.0.0.1 traefik.cima.es\
127.0.0.1 kibana.cima.es\
127.0.0.1 cima-mspocws-dev.cima.es\
127.0.0.1 cima-mspocws-test.cima.es\
127.0.0.1 prometheus.cima.es\
127.0.0.1 grafana.cima.es\
127.0.0.1 jaeger.cima.es\
127.0.0.1 dashboard.cima.es

USUARIOS\
kibana: elastic/cimadmin123\
traefik: admin/cimadmin123\
grafana: admin/admin por defecto. En el primer login se manda a la pantalla de cambio de contraseña si se desea cambiar en el momento, si no se puede cambiar despues en preferencias de usuario.

Para importar los dashboards de grafana. Hay 2 ejemplos dentro de la ruta: Componentes_core/kubernetes/grafana/dashboards. Simplemente seleccionando la opcion: **Upload JSON file** dentro de la seccion import dashboard y navegar hasta la ruta anteriormente mencionada.


Realizar la prueba abriendo el navegador entrado por conexion segura https a cualquiera de los sitios y por el puerto seguro del NodePort de acceso al controlador del balanceador de carga que en este caso es el **30443**.\
Ejemplo: https://dashboard.cima.es:30443

Si se realiza la prueba desde otra maquina, es necesario agregar las mimas rutas en el archivo hosts de la maquina en donde se este probando y cambiar la ip por la direccion de destino donde se haya desplegado el cluster. Es necesario que se encuentren dentro de la misma red las maquinas. O bien abrir el puerto de conexion segura del NodePort del balanceador a traves de una regla para ip publica; en caso de que la maquina de pruebas se encuentre dentro de una red diferente

**Importante:** Para Mac OS y Windows, en el caso de Docker For Desktop es necesario ingresar los directorios compartidos para el caso de los volumenes persistentes, tambien se puede utilizar [Docker Toolbox](https://docs.docker.com/docker-for-mac/docker-toolbox/) o [minikube](https://kubernetes.io/docs/tasks/tools/install-minikube/). Cabe mencionar que este tipo de soluciones son para fines de desarrollo y no se supone que sean utilizadas para entornos productivos. Por tanto no se recomienda seguir esta guia si se desea instalar en un OS **Windows** O **MacOS** como plataforma base ya que las configuraciones de estos componentes se pretenden para entornos productivos. En su caso recomiendo instalar una maquina virtual con una instalacion Linux como OS (de preferencia distribuciones basadas en RPM). Y dentro de la misma instalar un minikube o un plano de control sencillo con kubeadm. Tambien incluye un apartado de despliegues para OpenShift si es que se cuenta con un servicio en la nube. Para el caso de Openshift en entorno de desarrollo mirar [minishift](https://www.okd.io/minishift/). Lo cual puede ser otra alternativa para usuarios de windows y mac. Dentro de cada componente core hay una carpeta **oc**

**Notas:** 
* Quedan tambien pendientes de agregar microservicio de discovery y/o centralizador de api y ejemplo de cloud config para entornos.
* **Pendiente de agregar alertmanager de prometeo.
* ***Se agrega Jaeger de desarrollo como version inicial. En siguientes versiones se ira sustituyendo por el componente productivo.
* *****Se ha detectado que a partir de la version 1.19.* de kubelet, han dejado de mostrarse las metricas de maquina en el cadvisor como se menciona en este [issue](https://github.com/kubernetes/kubernetes/issues/95204) y en este [PR](https://github.com/kubernetes/kubernetes/pull/95210). Los cuales aun no han sido resueltos. Por lo que el tablero de grafana que se encuentra en este repositorio, no mostrara las graficas de uso de CPU y RAM en el cluster. Por tanto; bien se puede quedar, de momento, con la ultima version 1.18 de kubelet (1.18.9); o bien, desplegar el componenente [kube-state-metrics](https://github.com/kubernetes/kube-state-metrics). No explicado ni incluido en este repositorio.

**Changelog** 
* 1.0 Version inicial


