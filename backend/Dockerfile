FROM amazoncorretto:21.0.6-alpine3.21

# Définir un utilisateur non-root sécurisé
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR dans le conteneur
COPY target/boatapp-0.0.1-SNAPSHOT.jar app.jar

# Donner les permissions nécessaires à l'utilisateur non-root
RUN chown -R appuser:appgroup /app && chmod 755 /app/app.jar

# Changer l'utilisateur pour éviter l'exécution en root
USER appuser

# Exposer le port utilisé par Spring Boot (8080 par défaut)
EXPOSE 8080

# Exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
