# Obstruction Game
This program will engage the user in a 2-person game of Obstruction. The program use a minimax approach with several-movelook-ahead and alpha-beta pruning. The rules of the game are summarized as follows:

####Obstruction:
The game was invented and analyzed by László Kozma, a Romanian mathematician. It is described on his web site here: http://www.lkozma.net/game.html
* Players: Two
Players take turns in marking squares on a grid. The first player unable to move loses.
* Description: The game is played on a grid; 6 x 6 is a good size. One player is 'O' and the other is 'X'. The players take turns in writing their symbol in an empty cell. Placing a symbol blocks all the neighboring cells from both players, and you can optionally indicate
this by shading them
* The first player unable to move loses.
* START: Start with an empty board and decide who starts. The 1st player is ‘O’ and the 2nd playeris ‘X’.
* GOAL: The first player unable to move loses.
* PLAY: Each turn consists of placing a symbol, anywhere on the board and blocking the neighboring cells from both players.
* TERMINAL STATE: Win for ‘O’ or win for ‘X’. There is no tie situation, as the next player unable to make a move loses.
