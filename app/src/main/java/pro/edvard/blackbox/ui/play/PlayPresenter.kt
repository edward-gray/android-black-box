package pro.edvard.blackbox.ui.play

import android.view.View
import kotlinx.android.synthetic.main.fragment_play.*
import pro.edvard.blackbox.R

class PlayPresenter(private var  f: PlayFragment) {

    init {

    }

    private fun resetUI() {
        f.buttonConfig(false)
        f.play_image_emotion.setImageResource(R.drawable.ic_waiting)
        f.play_editText_answer.text = null
        f.play_text_check.visibility = View.GONE
        f.play_button_next_level.visibility = View.GONE
    }

    private fun handleCorrectAnswerUI() {
        f.play_image_emotion.setImageResource(R.drawable.ic_very_happy)
        f.play_text_check.text = f.requireContext().getString(R.string.play_correct_answer)
        f.play_text_check.visibility = View.VISIBLE
        f.play_button_next_level.visibility = View.VISIBLE
    }

    private fun handleIncorrectAnswerUI() {
        f.buttonConfig(false)
        f.play_image_emotion.setImageResource(R.drawable.ic_sad)
        f.play_text_check.text = f.requireContext().getString(R.string.play_incorrect_answer)
        f.play_text_check.visibility = View.VISIBLE
        f.play_button_next_level.visibility = View.GONE
    }

    private fun handleFinishedAllLevelsUI() {
        f.buttonConfig(true)
        f.play_linearLayout.visibility = View.GONE
        f.play_text_info.visibility = View.GONE
        f.play_text_level.visibility = View.GONE
        f.play_image_level.setImageResource(R.drawable.ic_won)
        f.play_text_check.text = f.requireContext().getString(R.string.play_thank_you)
        f.play_button_next_level.text = f.requireContext().getString(R.string.button_go_back)
    }


}