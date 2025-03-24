# Étape 1 : Construction de l'application Angular
FROM node:20.18.3 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
RUN npm install -g @angular/cli
COPY . .
RUN ng build --configuration=production

# Étape 2 : Configuration Nginx pour servir l'application
FROM nginx:latest

# Copier la configuration Nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copier les fichiers construits vers le répertoire HTML de Nginx
COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html

# Exposer le port 80
EXPOSE 80

# Commande pour démarrer Nginx
CMD ["nginx", "-g", "daemon off;"]
