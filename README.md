# Checkers — CS2 Final Project

**Authors:** Madiha Fatima & Selena Tang

A two-player checkers game playable in the terminal, written in Java.

---

## How to Run

**Compile:**
```bash
javac CSFinalCheckers.java
```

**Run:**
```bash
java CSFinalCheckers
```

> Requires Java (any modern version). No external libraries needed.

---

## How to Play

### Setup
1. Both players read the on-screen directions and confirm they understand.
2. Enter Player One's name (Black) and Player Two's name (White).

### Taking a Turn
On your turn, you'll be asked to select a piece and a destination:
- **Row** — enter a number (1–8)
- **Column** — enter a letter (A–H)

Example: to move a piece at row 3, column C → row 4, column D, enter:
```
Which piece would you like to move?
Enter its row #: 3
Enter its column letter: C
Where do you want your piece to move?
Enter its row #: 4
Enter its column letter: D
```

### Rules
- Black starts at the top (rows 1–3), White at the bottom (rows 6–8).
- Pieces move **diagonally forward**, one square at a time.
- If an opponent's piece is diagonally adjacent and the square beyond it is empty, you may **jump over and capture** it.
- **Kings** are earned when a piece reaches the opposite end of the board. Kings can move and capture both forward and backward diagonally.
- The game ends when one player has **no pieces remaining**.

> Note: This version does not enforce forced jumps — capturing is optional.

---

## Board Layout

```
	A  B  C  D  E  F  G  H
1	♟     ♟     ♟     ♟
2	   ♟     ♟     ♟     ♟
3	♟     ♟     ♟     ♟
4	
5	
6	   ♙     ♙     ♙     ♙
7	♙     ♙     ♙     ♙
8	   ♙     ♙     ♙     ♙
```

| Symbol | Meaning     |
|--------|-------------|
| ♟      | Black pawn  |
| ♙      | White pawn  |
| ♚      | Black king  |
| ♕      | White king  |

---

## Project Structure

```
CSFinalCheckers.java
│
├── main()         — Game loop, board display, input handling, move execution
├── BlackTurn()    — Validates a proposed move for the black player
└── WhiteTurn()    — Validates a proposed move for the white player
```

### Board Representation
The board is stored as a 9×9 `String` array (1-indexed). Each cell holds one of five values:

| Value | Piece        |
|-------|--------------|
| `0`   | Empty        |
| `1`   | Black pawn   |
| `2`   | White pawn   |
| `3`   | Black king   |
| `4`   | White king   |

A second `seen[][]` array maps these codes to Unicode chess symbols for display.
