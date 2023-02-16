## Class_GUI

NB Methodes: 17 dont 1 constructeur

## Database

Fichier main, instancie Class_GUI avec sa valeur par défaut (0)

## DatabaseConnectionBuilder

Fichier d'utilitaire pour construire le lien de connection à la base de données.
Il sert aussi à obtenir ou/et ajouter des élément conceernant le lien de connection.
Cette classe utiliser le design pattern builder d'ou le nom "DatabseConnectionBuilder

## DatabaseStatus

Fichier de gestion du status de la base de donnée, généralement si on est bien connecté à cellé-ci
Afin d'eviter des crash ou des erreurs de connection sans pour autant savoir comment debugger

## DatabaseUser

Fichier d'utilitaire pour crée un utilisateur afin de nous connecter à la base de donnée
Aussi de récuperer des informations sur celui-ci si nécessaire.
