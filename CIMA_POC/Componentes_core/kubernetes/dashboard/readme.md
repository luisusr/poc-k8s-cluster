PARA ENTRAR AL DASHBOARD:

Seleccionar como metodo de acceso Token:

Obtener token con el siguiente comando:

`kubectl -n kubernetes-dashboard describe secret $(kubectl -n kubernetes-dashboard get secret | grep kubernetes-dashboard-token | awk '{print $1}')`

Imprimira algo como esto ejemplo:

**DEPRECADO**

usar el siguiente comando: 

`kubectl get secret kubernetes-dashboard-token  -n kubernetes-dashboard -o jsonpath="{.data.token}" | base64 -d && echo`



COPIAR Y PEGAR el sha entero del campo **token**\
referencia: https://github.com/kubernetes/dashboard/blob/master/docs/user/access-control/creating-sample-user.md
