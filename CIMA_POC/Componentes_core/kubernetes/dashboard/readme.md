PARA ENTRAR AL DASHBOARD:

Seleccionar como metodo de acceso Token:

Obtener token con el siguiente comando:

`kubectl -n kubernetes-dashboard describe secret $(kubectl -n kubernetes-dashboard get secret | grep kubernetes-dashboard-token | awk '{print $1}')`

Imprimira algo como esto ejemplo:

```
Name:         kubernetes-dashboard-token-c5zfz
Namespace:    kubernetes-dashboard
Labels:       <none>
Annotations:  kubernetes.io/service-account.name: kubernetes-dashboard
              kubernetes.io/service-account.uid: c04d7449-6dee-4de2-9fed-5f7232875217

Type:  kubernetes.io/service-account-token

Data
====
namespace:  20 bytes
token:      eyJhbGciOiJSUzI1NiIsImtpZCI6ImhaeE5KNGhnVXkxTDNJbnNCc0RqZmFwVWVCRVVRNklyR1NqRlJzWFp3cDQifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlcm5ldGVzLWRhc2hib2FyZCIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJrdWJlcm5ldGVzLWRhc2hib2FyZC10b2tlbi1jNXpmeiIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50Lm5hbWUiOiJrdWJlcm5ldGVzLWRhc2hib2FyZCIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6ImMwNGQ3NDQ5LTZkZWUtNGRlMi05ZmVkLTVmNzIzMjg3NTIxNyIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDprdWJlcm5ldGVzLWRhc2hib2FyZDprdWJlcm5ldGVzLWRhc2hib2FyZCJ9.MAUVMgVsTM3bCPwc8HXY2mgSzXyrTW7CbXPIwuoxb68-HI0vArUen7YI_xYeN3R8kKTd1pTdOQfC8Yx3fnEH0MJ0CAKPN1ISvddIpvQsbsFSLQhKVTSFvtYqXsMamivf6VH39YKR9NopeDFq6rpl7hYjLykeOQY_78xOwxmUKb7Tcgu1dBAxLrNMqNdHbbFLBRjEcFUcCDMLIYvVDTEQPmjrdaf3A73cUoZOGD631LW8rWWQwQUiD0HD4Ja0fiqIsE1qm9hfgZ3PHfOnhtncuO1wYdXUcm9qoUSCNx3GYOJOj-pWOaGYC_SeqMPj8-I1j-Zp-GpIB-Sph1eoxFDNPw
ca.crt:     1066 bytes
```



COPIAR Y PEGAR el sha entero del campo **token**\
referencia: https://github.com/kubernetes/dashboard/blob/master/docs/user/access-control/creating-sample-user.md

