# 📚 Library Management System - Gestion de Bibliothèque

![Java](https://img.shields.io/badge/Java-EE%2011-orange) ![JakartaEE](https://img.shields.io/badge/Jakarta%20EE-11-blue) ![Tomcat](https://img.shields.io/badge/Tomcat-10.1.34-yellow) ![MySQL](https://img.shields.io/badge/MySQL-Database-lightblue) ![REST](https://img.shields.io/badge/REST-API-red)

> **Projet conçu pour la gestion des bibliothèques**, permettant d'enregistrer des **livres, magazines**, de gérer les **utilisateurs et les emprunts**, avec une **sortie JSON via API REST**. 💡

## 🚀 Fonctionnalités Principales
✅ **Lister tous les documents disponibles** 📑  
✅ **Lister les emprunts en cours** 🔄  
✅ **Retourner un document** 📦  
✅ **Emprunter un document** 📚  
✅ **Enregistrer un utilisateur** 👤  
✅ **Ajouter un livre ou un magazine** 🆕  

## 🏗 Architecture du Projet
📌 **Modèle MVC + DAO** avec JPA (EclipseLink) et Servlets Jakarta EE.  
📌 **Sortie JSON via `Gson`**, aucune interface HTML.  
📌 **Base de données MySQL**, persistance des données avec JPA.  
📌 **Déploiement sur Apache Tomcat 10.1.34.**  

### 📂 Structure du Projet
```
📦 library-management
├── 📂 src/main/java/org/example/librarymanagement
│   ├── 📂 config (Gestion de la persistance et des adaptateurs JSON)
│   ├── 📂 controller (Servlets pour gérer les requêtes REST)
│   ├── 📂 dao (Data Access Objects pour interagir avec la base de données)
│   ├── 📂 model (Entités JPA : User, Document, Book, Magazine, Borrow)
├── 📂 src/main/resources/META-INF (Configuration `persistence.xml`)
├── 📂 src/main/webapp (Dossier Web, contient `index.jsp` mais inutilisé)
├── pom.xml (Dépendances Maven)
```

## 💾 Installation et Configuration
### 1️⃣ **Cloner le Projet**
```sh
git clone https://github.com/ton-repo/library-management.git
cd library-management
```

### 2️⃣ **Configurer la Base de Données MySQL**
```sql
CREATE DATABASE library_db;
USE library_db;
```
Vérifie que **persistence.xml** contient :
```xml
<property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/library_db"/>
<property name="jakarta.persistence.jdbc.user" value="root"/>
<property name="jakarta.persistence.jdbc.password" value=""/>
```

### 3️⃣ **Compiler et Exécuter**
```sh
mvn clean package
```
Déployer `library-management.war` sur **Tomcat 10.1.34**.


## 🔥 Tester l’API avec Postman
### ➤ **Ajouter un utilisateur**
```http
POST http://localhost:9090/library_management_war_exploded/users
```
Body (JSON) :
```json
{
  "name": "John Doe",
  "mail": "john.doe@example.com"
}
```

### ➤ **Lister tous les documents**
```http
GET http://localhost:9090/library_management_war_exploded/documents
```

### ➤ **Emprunter un document**
```http
POST http://localhost:9090/library_management_war_exploded/borrows
```
Body (JSON) :
```json
{
  "userId": 1,
  "documentId": 2
}
```


## 🛠 Technologies Utilisées
| Technologie | Usage |
|-------------|---------|
| **Java EE 11** | Développement Backend |
| **Jakarta Servlets** | Gestion des requêtes REST |
| **JPA (EclipseLink)** | ORM pour la base de données |
| **MySQL** | Base de données relationnelle |
| **Gson** | Sérialisation JSON |
| **Apache Tomcat 10.1.34** | Serveur d'application |
| **Maven** | Gestionnaire de dépendances |

## 📌 Améliorations Futures
- 🔹 Ajout d'une authentification avec JWT.
- 🔹 Ajout d’une gestion avancée des retours.
- 🔹 Intégration d’un système de notifications par email.

---



