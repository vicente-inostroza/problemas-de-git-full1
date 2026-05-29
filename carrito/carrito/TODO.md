# TODO - Fix del error de modelo (CartItem)

- [x] Localizar error en `CartItem` (imports javax.validation)
- [x] Cambiar `CartItem` de `javax.validation` a `jakarta.validation`
- [ ] Agregar dependencia necesaria en `pom.xml` para que `jakarta.validation` exista en el classpath (`spring-boot-starter-validation`)
- [ ] Ejecutar `mvn test` / `mvn -q test` para confirmar que el contexto carga

