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
token:      eyJhbGciOiJSUzI1NiIsImtpZCI6Ill2MXNVRTl5bUo0MWVpbXFOS09tSDhEQTdBVm5pbHlBa1NpcG5qR05YRUUifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlcm5ldGVzLWRhc2hib2FyZCIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJrdWJlcm5ldGVzLWRhc2hib2FyZC10b2tlbi01anZ6OCIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50Lm5hbWUiOiJrdWJlcm5ldGVzLWRhc2hib2FyZCIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6ImUzMzk3NGNmLWQ3YTgtNGYyMS1iZDFiLTQzNjE5YmM2MjRhOSIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDprdWJlcm5ldGVzLWRhc2hib2FyZDprdWJlcm5ldGVzLWRhc2hib2FyZCJ9.ZYBJLToGS8ty5Tb-8H2d4_5ai_Kwa6wZ-RCA0-m0WAcQnv2I-T3zlzEns5kX19Db8TP-2GSN2g7yYQI--j7TvLuNktx4e1RntdWz_W71G-vmWkEDS14A9uk339XPmftG4DX8xYN_mXdr-PHtoJ-X9KysPptFFl2ATnB_AUJ6tJsGxZ2jCaWkow4ENOI5CmCRmqWGNgtaQB5N1mQmxMra9k0Klt8tZzNh3okrIeOb6Nhvkd6sblEjw9gKMFkArluihe02gIwj3xR8Ixz7B_ZOrMpJJ8bh5Hv8mHQkj7NB5zSa3b2kpjwxeXd7OtBkzszALgyLaXmV-hYqbd_Mt8y6pw
ca.crt:     1066 bytes

COPIAR Y PEGAR DESPUES DE token:
referencia: [https://github.com/kubernetes/dashboard/blob/master/docs/user/access-control/creating-sample-user.md]

