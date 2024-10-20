package com.example.unscramble.ui.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.unscramble.data.MAX_NO_OF_WORDS
import com.example.unscramble.data.SCORE_INCREASE
import com.example.unscramble.data.allWords
import com.example.unscramble.ui.state.GameUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/*
La clase ViewModel está diseñada para almacenar y gestionar los datos de UI de manera más eficiente,
sobrevivir a cambios de configuración (como la rotación de la pantalla) y proporcionar
datos de larga duración a las actividades/fragments.

Habrá que comunicarla con la UI.
 */
class GameViewModel : ViewModel() {

    /*
    StateFlow -> Es un flujo de datos observable que emite el estado actual y anterior
    de los valores.
    MutableStateFlow -> Es una versión de StateFlo que permite actualizar o modificarse.
    _uiState -> campo privado que contiene el estado de la UI.

    */
    private val _uiState =
        MutableStateFlow(GameUIState()) //se inicializa con el estado INICIAL de la UI

    /*
    Funciona como "copia de seguridad"
     Esta es la versión pública y solo de lectura del estado de la UI. Expone el estado actual
     a la UI o a otros componentes, pero NO permite que esos componentes lo modifiquen directamente.
     Así nos aseguramos de que SOLO el ViewModel controla las modificaciones del estado.
     */
    val uiState: StateFlow<GameUIState> =
        _uiState.asStateFlow() // método que convierte mutable en inmutable.

    private lateinit var currentWord: String //elegir una palabra aleatoria de los Datos
    private var usedWords: MutableSet<String> = mutableSetOf() //alamcenar las palabras usadas
    var userGuess by mutableStateOf("") //la palabra que el usuario introduce
        private set

    init {
        resetGame() //al iniciar la app, "reseteamos el juego"
    }

    //Método que elige una palabr aleatoria de la lista y la desordena.
    private fun pickRandomWordAndShuffle(): String {
        currentWord = allWords.random() //allWords se encuentra en el paquete .data
        if (usedWords.contains(currentWord)) {
            return pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }

    //Método para desordenar la palabra actual.
    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()
        // Scramble the word
        tempWord.shuffle()
        while (String(tempWord).equals(word)) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    //Método que recibe una posible palabra correcta y la almacena en la que el usuario cree que es
    fun updateUserGuess(guessedWord: String) {
        userGuess = guessedWord
    }

    //Método que comprueba si la palabra es la correcta.
    fun checkUserGuess() {
        if (userGuess.equals(currentWord, ignoreCase = true)) {
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        } else {
            _uiState.update { currentState ->
                currentState.copy(isGuessedWordWrong = true)
            }
        }
        updateUserGuess("")
    }

    private fun updateGameState(updatedScore: Int) {
        if (usedWords.size == MAX_NO_OF_WORDS){
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    score = updatedScore,
                    isGameOver = true
                )
            }        } else{
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    currentWordCount = currentState.currentWordCount.inc(),
                    score = updatedScore
                )
            }
        }
    }

    //Resetear el juego. Borra todas las palabras usadas y vuelve a inicializar el estado con una palabra nueva.
    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUIState(currentScrambledWord = pickRandomWordAndShuffle())
    }

    fun skipWord() {
        updateGameState(_uiState.value.score)
        updateUserGuess("")
    }

}



