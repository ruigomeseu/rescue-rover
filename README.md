Rescue Rover
===========

A Java implementation of the classic game Rescue Rover, by id Software. This was an academic project for the [Object Oriented Programming Laboratory](https://sigarra.up.pt/feup/en/UCURR_GERAL.FICHA_UC_VIEW?pv_ocorrencia_id=333122) class at FEUP.

##Screenshots

TBD

##Design Patterns Used

####State Pattern
The State Pattern was used to commute between the Main Menu, the game itself and the various game outcomes (Game Won or Game Lost).

####Singleton Pattern
All the implemented states are singletons, meaning that there won't be duplicate JPanels for the same state.

####Observer Pattern
The usage of the Observer Pattern allowed for an easy switch between the different game states. When a state change is requested, the State Manager will be notified and it will correctly interchange the active state.

####Visitor Pattern
The implementation of the Visitor Pattern allowed for the bullets to visit the hero and thus complying with the Single Responsibility Principle - the bullet doesn't know what happens to the hero when they collide, so the code that makes the hero dead must be implemented within the Hero itself.

##Credits

This was originally developed by [Rui Gomes](http://www.github.com/ruigomeseu) and [Tiago Ferreira](http://www.github.com/tiagommferreira) and is shared under the MIT License.

####Sprites
* Crono, from Chrono Trigger by Square - Downloaded at [The Spriters Resource](http://www.spriters-resource.com/snes/chronotrigger/sheet/2514/)
* Stairs, from Pokemon Fire Red by Nintendo - Downloaded at [The Spriters Resource](http://www.spriters-resource.com/game_boy_advance/pokemonfireredleafgreen/sheet/23724/)

####Sound Effects
