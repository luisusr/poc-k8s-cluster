# poc-k8s-cluster
version 2 previous repository cluster-template

Base project that has deployments of components necessary to control a complete solution based on architectures oriented to microservices and in distributed environments. Which is deployed on a cluster of kubernetes as a Docker runtime and orchestrator platform for the execution of containers in the nodes. Which contains the following components:

* Load balancer (Traefik + SSL / TLS for HTTPS)
* Trace monitoring component (EFK + Securization with X-Pack)
* Cluster monitoring component (Prometheus ** + Grafana)
* Traceability component (Jaeger Development) ***
* Kubernetes dashboard with ingress and RBAC service account administrator
* A test microservice with necessary dependencies for interaction with components

Requirements:
* Maven 3.x
* Jdk 8
* Kubernetes (kubeadm, kubelet 1.18 **** and kubectl. Installation with kubeadm on linux is recommended. For Win and Mac OS it is necessary to install [Docker Desktop](https://www.docker.com/products/docker-desktop ))
* Container runtime (Docker)
* [Run local registry](https://docs.docker.com/registry/deploying/). **UPDATE** This step is no longer necessary as the registry is deployed within the cluster.

In the case of linux, the installation is used from the kubernetes repository and starting a minimum control plane with the [kubeadm tool](https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/create-cluster-kubeadm/), for Mac OS and Windows you can use docker for desktop although for windows you have to execute the content of the scripts since they are bash (.sh)



TO DEPLOY CROSS MODULES
* Linux and Mac OS: Inside the Core Components / kubernetes directory execute: **deploy**
* Windows: Run content within file

TO DEPLOY TEST MICROSERVICE
1. For local registry: Execute **deploy** script in CIMA_POC folder
2. For jcr: Execute **jcrdeploy** script in CIMA_POC folder
2. Windows: Run content within file

Once the cluster is installed and the components have been deployed, it is necessary to import the public certificate in the browser that can be found in the **ca.pem** file in the Components_core / kubernetes / traefik / tls directory. For secure ssl connection.

**NOTE:** It is likely that in the most recent versions of **Mac OS** "Catalina" and "Big Sur" will continue to show as an insecure site and in the case of Chrome the following error: ERR_CERT_VALIDITY_TOO_LONG; Despite having added the certificate, this due to the [policies](https://support.apple.com/en-us/HT210176) that has established Mac OS for trusted certificates and in this repository, the certificate of proof that I have generated and self-signed, I have established an expiration for 10 years, which does not fall within the requirements. In the case of **Windows 10**, it has been tested with the latest update in Chrome and Edge browsers and accepts the certificate without any error.

Add the following entries to the **hosts** file: \
127.0.0.1 traefik.cima.es \
127.0.0.1 kibana.cima.es \
127.0.0.1 cima-mspocws-dev.cima.es \
127.0.0.1 cima-mspocws-test.cima.es \
127.0.0.1 prometheus.cima.es \
127.0.0.1 grafana.cima.es \
127.0.0.1 jaeger.cima.es \
127.0.0.1 dashboard.cima.es \
127.0.0.1 registry.cima.es \
127.0.0.1 jcr.cima.es

USERS \
**kibana:** elastic / cimadmin123 \
**traefik:** admin / cimadmin123 \
**grafana:** admin / admin by default. In the first login, you are prompted to change the password in the login screen if not so, you can change later in user preferences. \
**dashboard:** Get token with the following command: \
`kubectl -n kubernetes-dashboard describe secret $ (kubectl -n kubernetes-dashboard get secret | grep kubernetes-dashboard-token | awk '{print $ 1}')` \
Also explained in the readme of the component and for more information in the path: Components_core / kubernetes / dashboard.\
**jcr:** admin / password by default. In the first login screen you are propmted to set a new admin password as well as other user and repository settings and EULA acceptance. However, an admin user **cima** with password Cimadmin123 must be set, if you want to test te deploy and push to jcr the image, since the imagePullSecret in the deployment has been set to match these credentials. As well as docker login with same ones before to execute the deployment with **jcr** registry (previously mentioned in deploy microservice instructions).

To import the grafana dashboards. There are 2 examples within the path: Components_core / kubernetes / grafana / dashboards. Simply selecting the option: **Upload JSON file** within the import dashboard section and navigate to the aforementioned path.


Perform the test by opening the browser entered through a secure connection https to any of the sites and through the secure port of the NodePort to access the load balancer controller, which in this case is **30443**. \
Example: https://dashboard.cima.es:30443. It must be the same with the Ingress access to the another hosts as soon as the other components start running and become available, by checking the status by the command: `kubectl get po -A`\
like: https://traefik.cima.es:30443, https://registry.cima.es:30443, ... and so on.

If the test is done from another machine, it is necessary to add the same routes in the hosts file of the machine where it is being tested and change the IP to the destination address where the cluster has been deployed. It is necessary that the machines are within the same network. Or open the secure connection port of the NodePort of the balancer through a rule for public ip; in case the test machine is within a different network

**Important:** For Mac OS and Windows, in the case of Docker For Desktop it is necessary to enter the shared directories for persistent volumes, you can also use [Docker Toolbox](https://docs.docker.com/docker-for-mac/docker-toolbox/) which by the way is deprecated, or [minikube](https://kubernetes.io/docs/tasks/tools/install-minikube/). It is worth mentioning that these types of solutions are for development purposes and are not supposed to be used for production environments. Therefore, it is not recommended to follow this guide if you want to install on an **Windows host** OR **MacOS** as a base platform since the configurations of these components are intended for productive environments (for example NFS and dynamic volume provider component). In that case, I recommend installing a virtual machine with a Linux installation such as OS (preferably RPM-based distributions). And inside it, install a minikube or a simple control plane with kubeadm. It also includes a section on deployments for OpenShift if you have a cloud service. For the case of Openshift in development environment see [minishift](https://www.okd.io/minishift/). Which can be another alternative for Windows and Mac users. Inside each core component there is a folder **oc**

**Notes:**
* They are also pending to add discovery microservice and / or api centralizer and example of cloud config for environments.
* ** Pending to add prometheus alertmanager.
* *** Development Jaeger is added as initial release. In subsequent versions it will be replaced by the productive component.
* ***** It has been detected that as of kubelet version 1.19. *, The machine metrics have stopped showing in the cache as mentioned in this [issue](https://github.com/kubernetes/kubernetes/issues/95204) and in this [PR](https://github.com/kubernetes/kubernetes/pull/95210). Which have not yet been resolved. So the graphical dashboard found in this repository will not show the graphs of CPU and RAM usage in the cluster. Therefore; It may well be, for the moment, with the latest version 1.18 of kubelet (1.18.9); Or, display the [kube-state-metrics component](https://github.com/kubernetes/kube-state-metrics). Not explained or included in this repository. \
**UPDATE:** The bug has been solved as of version 1.20.2 and the machine metrics are shown again, so it is no longer necessary to deploy the additional component and you can continue updating to the latest versions on kubelet agent in each of the nodes

**Changelog**
* 1.0 Initial version

