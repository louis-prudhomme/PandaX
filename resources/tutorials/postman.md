# Sommaire 
- [Sommaire](#sommaire)
- [Qu’est-ce que Postman ?](#quest-ce-que-postman)
- [Comment l’utiliser ?](#comment-lutiliser)
  - [Configuration des variables](#configuration-des-variables)
- [Découverte de Postman avec les requêtes déjà présentes ce titre est beaucoup trop long mdr](#d%c3%a9couverte-de-postman-avec-les-requ%c3%aates-d%c3%a9j%c3%a0-pr%c3%a9sentes-ce-titre-est-beaucoup-trop-long-mdr)
  - [La requête](#la-requ%c3%aate)
  - [Le body](#le-body)
  - [Tests](#tests)
  - [Authorization](#authorization)
- [Ok c’est cool, comment je contribue ?](#ok-cest-cool-comment-je-contribue)
  - [Créer une requête](#cr%c3%a9er-une-requ%c3%aate)
  - [Tout mettre sur le git](#tout-mettre-sur-le-git)

# Qu’est-ce que Postman ?

Un outil pour créer des campagnes de test d’API au travers d’appels HTTP.

Postman permet ainsi de créer des *requêtes* et de les regrouper en *collections* afin de les exécuter en chaîne.

Notre collection s’appelle `PandaX`.

# Comment l’utiliser ?

Déjà, il faut l’installer en le téléchargeant [ici](https://www.getpostman.com/downloads/).

Ensuite, ouvrez-le (créez-vous un compte, vérifiez le mail, yada yada…) et utiliser la *fonction d’import* pour… importer (duh) la collection `PandaX`.

Elle contient toutes les requêtes créées jusqu’à présent pour tester les routes REST de notre API.

## Configuration des variables

Afin de rendre nos requêtes génériques, nous utilisons des `variables` Postman.

Il est nécessaire de les configurer au moins une fois pour faire fonctionner les tests.

Afin d’ouvrir le panneau de configuration de la collection, **clic droit dessus → Edit**.

Là allez dans l’onglet `Variables` et configurez-les comme suit ;
- `url` est l’URL à laquelle il est possible de contacter l’API REST lorsqu’elle est déployée ; typiquement, « localhost:8080/PandaX »
- `token` osef pour l’instant
- `login` modifiez-le pour correspondre à un `user` dans votre base de données
- `password` modifiez-le pour correspondre à un `user` dans votre base de données
- `media` osef pour l’instant

Vous pouvez ensuite cliquer sur `Update`

# Découverte de Postman avec les requêtes déjà présentes ce titre est beaucoup trop long mdr

## La requête

En raison de l’architecture de notre REST API, il est nécessaire de s’authentifier en envoyant un `login` et un `password` afin d’obtenir un `token`, lequel servira pour les requêtes suivantes.

Sur Postman, la requête `Auth – Obtain token` se charge de ce rôle. Cliquez dessus.

Dans la barre de requête en haut, vous pouvez voir `{{url}}/token`. Comme vous pouvez sans doute vous en douter, `{{url}}` est une `variable` Postman et sera remplacée, avant l’exécution de la requête, par la valeur que vous lui avez définie un peu avant.

Le gros `POST` juste à côté indique la nature de la requête HTTP (en l’occurrence, POST. duh.). Nous nous servirons exclusivement de `GET` `POST`, `PUT` et `DELETE`.

## Le body

Cliquez ensuite sur l’onglet `Body` de la requête. Cet onglet sert à spécifier la charge utile de la requête.

Vous pouvez voir que deux valeurs sont renseignées, `login` et `password`, et que leur valeur est renseignée par une `variable` Postman (toujours, celle que vous avez définie plus haut).

Si vous appuyez sur `Send` (le gros bouton bleu) et que tout est convenablement configuré (n’oubliez pas de modifier votre `persistence.xml`), la requête HTTP va s’élancer et devrait vous retourner un `token` sous forme d’une longue chaîne de caractère inintelligible.

## Tests

Cliquez ensuite sur l’onglet `Tests` de la requête. Cet onglet sert à spécifier les tests qui sont effectés *après* l’exécution de la requête, sur la réponse obtenue.

Vous pouvez voir trois fonctions ; les deux premières servent respectivement à vérifier si la réponse est un code HTTP 200 (réponse favorable du serveur) et si le `token` reçu en réponse est bien formé (s’il est bien en trois parties).

La troisième partie est plus intéressante ; il s’agit d’une fonction assignant la valeur du `token` à la `variable` Postman correspondante. Cela nous permettra d’en bénéficier durant l’exécution des requêtes suivantes.

Maintenant que `token` est défini, allons le tester.

## Authorization

Sélectionnez la requête `Auth – Verify token` et allez dans l’onglet `Headers`.

Vous pouvez y voir un `header` défini, `Authorization`, lequel vaut `{{token}}`. Comme vous pouvez le deviner, c’est là que l’on définit le `token` à envoyer au serveur pour les requêtes le demandant (c’est-à-dire, à peu près toutes).

Si vous exécutez cette requête, le serveur devrait vous renvoyer « Pong ! ». C’est d’ailleurs l’objet du test dans l’onglet homonyme pour cette requête.

# Ok c’est cool, comment je contribue ?

## Créer une requête

Clic droit `PandaX` → Add request.

Le *nom* de la requête doit être de la forme `<Nom de la resource> – <Effet de la requête>` et se trouver dans un dossier correspondant au nom de la ressource.

La requête doit être au maximum générique et demander le moins de configuration spécifique possible (ne lésinez pas sur les `{{paramètres}}`).

Idéalement, toutes les routes REST doivent avoir une requête sur Postman.

## Tout mettre sur le git

Alors là, c’est super simple.

Clic droit sur `PandaX` → Export et remplacez le fichier `PandaX.postman_collection.json` dans le dossier `/pandax/resources/`, puis commitez.