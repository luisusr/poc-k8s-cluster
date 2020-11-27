PARA ENTRAR AL DASHBOARD:

Seleccionar como metodo de acceso Token:

Obtener token con el siguiente comando:

`kubectl -n kubernetes-dashboard describe secret $(kubectl -n kubernetes-dashboard get secret | grep kubernetes-dashboard-token | awk '{print $1}')`


copiar y pegar el SHA que muestra ejemplo:

Name:         kubernetes-dashboard-token-5jvz8
Namespace:    kubernetes-dashboard
Labels:       <none>
Annotations:  kubernetes.io/service-account.name: kubernetes-dashboard
              kubernetes.io/service-account.uid: e33974cf-d7a8-4f21-bd1b-43619bc624a9

Type:  kubernetes.io/service-account-token

Data
====
namespace:  20 bytes
token:      eyJhbGciOiJ ...
ca.crt:     1066 bytes

COPIAR Y PEGAR DESPUES DE token:
referencia: [https://github.com/kubernetes/dashboard/blob/master/docs/user/access-control/creating-sample-user.md]

