# 🏦 Système de Gestion de Comptes Bancaires  

## 📌 Description du projet  
Ce projet est une **application console développée en Java 8** permettant la gestion de comptes bancaires.  
Elle offre des fonctionnalités de base telles que :  
- Création de comptes (courant ou épargne)  
- Versements, retraits et virements entre comptes  
- Consultation du solde d’un compte  
- Historique des opérations (versements, retraits, virements)  

L’application respecte une **architecture en couches** (présentation, métier, utilitaire) et met en œuvre les **principes SOLID** afin d’assurer une bonne organisation et une extensibilité du code.  

---

## ⚙️ Technologies utilisées  
- **Langage** : Java 8 (JDK 1.8)  
- **IDE** : Eclipse  
- **API utilisées** : Java Time API (gestion des dates), UUID (identifiants uniques)  
- **Collections** : ArrayList, HashMap  
- **Outils de gestion** :  
  - Git & GitHub (versioning du projet)  
  - JIRA (gestion des tâches et suivi du projet)  

---

## 📂 Structure du projet  

```bash
BanqueApp/
│── src/
│   ├── presentation/        # Couche UI (Menu console, interactions utilisateur)
│   ├── metier/              # Couche métier (Compte, CompteCourant, CompteEpargne, etc.)
│   ├── util/                # Couche utilitaire (validations, helpers)
│   ├── operations/          # Classe abstraite Operation + Versement, Retrait
│
│── README.md                # Documentation du projet
│── BanqueApp.jar            # Fichier exécutable généré
│── diagramme_classe.png     # Diagramme UML du projet
