package com.example.unscramble.ui.state

//variable para la palabra desordenada actual
data class GameUIState(
    val currentScrambledWord:String = "",
    val isGuessedWordWrong: Boolean = false,
    val score: Int = 0,
    val currentWordCount: Int = 1,
    val isGameOver:Boolean = false
    ) {

}