package pro.edvard.blackbox.ui.play

import android.annotation.SuppressLint
import android.view.View
import kotlinx.android.synthetic.main.fragment_play.*
import pro.edvard.blackbox.R
import pro.edvard.blackbox.model.Level

class PlayPresenter(private var f: PlayFragment) {

    init {
        resetUI()
    }

    fun resetUI() {
        f.buttonConfig(false)
        f.play_image_emotion.setImageResource(R.drawable.ic_waiting)
        f.play_editText_answer.text = null
        f.play_editText_answer.isEnabled = true
        f.play_text_check.visibility = View.GONE
        f.play_button_next_level.visibility = View.GONE
    }

    fun handleCorrectAnswerUI() {
        f.play_image_emotion.setImageResource(R.drawable.ic_very_happy)
        f.play_text_check.text = f.requireContext().getString(R.string.play_correct_answer)
        f.play_text_check.visibility = View.VISIBLE
        f.play_button_next_level.visibility = View.VISIBLE
        f.play_editText_answer.isEnabled = false
    }

    fun handleIncorrectAnswerUI() {
        f.buttonConfig(false)
        f.play_image_emotion.setImageResource(R.drawable.ic_sad)
        f.play_text_check.text = f.requireContext().getString(R.string.play_incorrect_answer)
        f.play_text_check.visibility = View.VISIBLE
        f.play_button_next_level.visibility = View.GONE
        f.play_editText_answer.text = null
    }

    fun handleFinishedAllLevelsUI() {
        f.buttonConfig(true)
        f.play_linearLayout.visibility = View.GONE
        f.play_text_info.visibility = View.GONE
        f.play_text_level.visibility = View.GONE
        f.play_image_level.setImageResource(R.drawable.ic_won)
        f.play_text_check.text = f.requireContext().getString(R.string.play_thank_you)
        f.play_button_next_level.text = f.requireContext().getString(R.string.button_go_back)
    }

    @SuppressLint("SetTextI18n")
    fun updateLevelImageAndText(currentLevel: Level) {
        val context = f.requireContext()
        val imageName = "level_" + currentLevel.number.toString()
        val resourceId: Int =
            context.resources.getIdentifier(imageName, "drawable", context.packageName)
        f.play_image_level.setImageResource(resourceId)
        f.play_text_level.text =
            f.requireContext().getString(R.string.play_level) + " " + currentLevel.number.toString()
    }


}