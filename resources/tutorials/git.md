# What is Git ? 

Git permet de gérer le développement concurrent d’un projet.

# Comment ça marche ?

Oui

# Les commandes utiles

## git clone

Permet de cloner un projet, c’est-à-dire de le rapatrier sur le système de fichier local ; crée un répertoire correspondant au projet

Utilisation `git clone <url du projet>`

## git add

Permet d’ajouter les fichiers à la « file d’attente » pour le prochain commit.

- Utilisation pour ajouter TOUS les fichiers modifiés : `git add --all`
- Utilisation pour ajouter un fichier modifié : `git add </chemin/vers/le/fichier.extension>`

## git commit

Permet de commit, c’est-à-dire de valider tous les fichier mis en file d’attente.

Utilisation : `git commit`

L’utilisation de cette commande demande automatiquement de renseigner un message de commit.

## git push

Permet d’envoyer tous les fichiers validés au serveur.

Utilisation : `git push`

## git pull

Permet de récupérer tous les fichiers du serveur.

Utilisation : `git push`

## git checkout

Permet de passer d’une branche à l’autre.

Pour créer une branche `git checkout -b <nom_branche>`

Pour passer sur une branche existante `git checkout <nom_branche>`