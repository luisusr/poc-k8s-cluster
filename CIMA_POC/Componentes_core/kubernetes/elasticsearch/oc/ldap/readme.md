PARA PROBAR FUNCIONALIDAD:

Entrar al API de la consola kibana con usurio por defecto (elastic) en la seccion dev tools (icono de llave inglesa) y activar licencia trial por medio de la siguiente operacion:

`POST /_license/start_trial?acknowledge=true`

Luego meter el siguiente mapeo de rol dependiendo del(os) grupo(s) / usuario(s) que se deseen dar de alta

```javascript
PUT /_security/role_mapping/admins
{
  "roles": [ "superuser" ],
  "enabled": true,
  "rules": {
    "field" : { "dn" : "*,ou=Tecnologia,ou=Empleados,o=TIREA,c=ES" }
  }
}
```


Referencias:\
https://www.elastic.co/guide/en/elasticsearch/reference/current/start-trial.html \
https://www.elastic.co/guide/en/elasticsearch/reference/current/role-mapping-resources.html
