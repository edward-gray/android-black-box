package pro.edvard.blackbox.ui.play

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_play.*
import pro.edvard.blackbox.R
import pro.edvard.blackbox.model.Level
import pro.edvard.blackbox.util.K
import pro.edvard.blackbox.util.NetworkConnection
import pro.edvard.blackbox.util.hideKeyboard

@AndroidEntryPoint
class PlayFragment : Fragment(R.layout.fragment_play) {

    private val viewModel: PlayViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var networkConnection: NetworkConnection
    private lateinit var presenter: PlayPresenter
    private lateinit var currentLevel: Level
    private lateinit var pref: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        networkConnection = NetworkConnection(requireContext())
        pref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        configuration()
    }

    private fun configuration() {
        presenter = PlayPresenter(this)
        subscribers()
        handleCorrectAnswer()
    }

    fun buttonConfig(isLastLevel: Boolean = false) {
        play_button_next_level.setOnClickListener {
            if (isLastLevel) {
                navController.navigate(R.id.action_playFragment_to_gameMenuFragment)
            } else {
                viewModel.goToNextLevel()
            }
        }
    }

    @SuppressLint("CommitPrefEdits")
    private fun subscribers() {
        setCurrentLevel()

        // CURRENT LEVEL
        viewModel.currentLevel.observe(viewLifecycleOwner) {
            presenter.resetUI()
            presenter.updateLevelImageAndText(it)
            currentLevel = it
        }

        // LAST LEVEL
        viewModel.isLastLevel.observe(viewLifecycleOwner) {
            if (it) {
                presenter.handleFinishedAllLevelsUI()
                // saving
                pref.edit().putInt(K.CURRENT_LEVEL_KEY, 0).apply()
            }
            buttonConfig(it)
        }

        // INTERNET CONNECTION
        networkConnection.observe(viewLifecycleOwner) { networkAvailable ->
            if (networkAvailable != null) {
                if (networkAvailable) {
                    play_image_wifi.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), "Network is back ;)", Toast.LENGTH_SHORT).show()
                } else {
                    play_image_wifi.visibility = View.GONE
                    Toast.makeText(requireContext(), "Network is gone again", Toast.LENGTH_SHORT).show()
                }
            } else {
                play_image_wifi.visibility = View.GONE
            }
        }
    }

    private fun setCurrentLevel() {
        // here i'm just loading levels
        viewModel.getLevels()

        // here i'm looking if user already played
        // the game and set the level to the saved one
        pref.getInt(K.CURRENT_LEVEL_KEY, -1).let { levelNumber ->
            if (levelNumber != -1) {
                viewModel.getCurrentLevel(levelNumber)
            } else {
                viewModel.getCurrentLevel(0)
            }
        }
    }

    private fun handleCorrectAnswer() {
        play_editText_answer.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (play_editText_answer.text.isNotEmpty()) {
                    val userAnswer = play_editText_answer.text.toString().toInt()
                    if (userAnswer == currentLevel.answer) {
                        // answer is correct
                        presenter.handleCorrectAnswerUI()
                        // saving level
                        pref.edit().putInt(K.CURRENT_LEVEL_KEY, currentLevel.number-1).apply()
                    } else {
                        // wrong answer
                        presenter.handleIncorrectAnswerUI()
                    }
                    play_editText_answer.hideKeyboard()
                    requireActivity().window.decorView.clearFocus()
                }
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

}