# ClickGame

Simple game project. Its current state is that of a basic clicker game, with clickable shapes of different sizes and colours - upon being clicked, green shapes disappear, while clicked red shapes lead to a Game Over. The game finishes when all possible valid shapes have been clicked.

The system is, however, designed to support easy / arbitrary extension, and various other game rules, game entity behaviours, shapes/colours, score storage systems, etc. may be cleanly added.

Dependencies (handled through Gradle): 

* Jedis 5.0.2
* Gson 2.10.1
* JSON-java 20231013
