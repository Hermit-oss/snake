TODO: Board, Snake, Apples - moving, Obstacles - moving?, Other Snakes?
TODO: Settings - fullscreen, snake's look, other snakes' looks, apple's look, obstacles look - collectibles, achievements?
TODO: Pause, Score, Game Over Screen

Food:
1 - chaotic
2 - lines
3 - static
4 - running away

Board:
1 - nice graphics (pixel art)

Obstacles:
1 - walls (no moving through)
2 - rocks (randomized at the beginning)
3 - other snakes (you can eat them though - cutting through, other snakes can eat you too)??//????????

Pull the snake out of the GameWindow and BoardPanel -> create a snake class
Game loop in Main, not in GameWindow
Separate obstacle class -> for rocks (extract the rocks from other classes and create an obstacle class and a rock class -> inheritance)
Write move methods and draw methods for food in Food class (extract drawing method of the food of the other classes)