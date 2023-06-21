Food:
1 - chaotic
2 - lines
3 - static
4 - running away

INFO:
Ogólnie to gra chodzi dosyć dziwnie w sensie dziwnie sie wąż przemieszcza -> trzeba zrobić tak żeby co ~0.5 sekundy przemieszczał się o jedną kratkę
Kolizja z samym sobą nie działa
Szybka zmiana ruchu (wąż idzie w lewo, to jak przyciśniemy góra i szybko prawo) spowoduje przenikanie węża przez siebie (jak naprawiona zostanie kolizja z samym sobą wywoła to koniec gry) Move queue?
Pojawia się tylko czerwony owoc
Owoce się nie przemieszczają
Okno jest dosyć małe

Obstacles:
1 - walls (no moving through)
2 - rocks (randomized at the beginning)
3 - other snakes (you can eat them though - cutting through, other snakes can eat you too)??//????????

Pull the snake out of the GameWindow and BoardPanel -> create a snake class
Game loop in Main, not in GameWindow
Separate obstacle class -> for rocks (extract the rocks from other classes and create an obstacle class and a rock class -> inheritance)
Write move methods and draw methods for food in Food class (extract drawing method of the food of the other classes)
TODO:
Naprawa tego co wyżej
Main Menu
Pauza (+ okno pauzy)
Fullscreen
Score (+ zapamiętywanie personal best?)
Game Over Screen
Ogarnięcie tego spaghetti bo ja nie wiem co się w tym kodzie dzieje na tym etapie
Grafika + Muzyka + Efekty ???
