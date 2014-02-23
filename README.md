***************************************************************
*                                                             *
*             Systèmes d'Exploitation Multitaches             * 
*               Vincente NATTA & Salomé COAVOUX               *
*                                                             *
***************************************************************

Ce projet a pour but de simuler un festival de musique grâce à 
l'utilisation de Threads. Les festivaliers doivent se rendre
sur un site, acheter un billet et prendre une navette pour aller
sur le site du festival. Les navettes et les festivaliers sont 
implémentés par des Threads.

Trois versions sont disponibles :

* La version 0 est une version naïve dans laquelle l'utilisation
des moniteurs a été détournée pour une simulation simple.

* La version 1 est similaire à la version 0, mais utilise les 
moniteurs de manière plus judicieuse.

* La version 2 ajoute des contraintes au problème :
    - Plusieurs guichets par site
	- Nombre limité de billets par site
	- Les visiteurs n'ayant pas de billet avance avec une navette
	jusqu'au site suivant jusqu'à trouver un billet ou revenir
	au site d'origine
	- Les navettes ont une vitesse spécifique
	- Les navettes ne peuvent pas se doubler
