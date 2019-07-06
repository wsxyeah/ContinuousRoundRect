package xyz.wsxyeah.continuousroundrect

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.TypedValue
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SeekBar

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        cornerRadiusSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val r = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    progress.toFloat(),
                    resources.displayMetrics
                )
                playgroundView.radius = r
                (playgroundView.background as? GradientDrawable)?.let {
                    it.cornerRadius = r
                }
                (roundRectView.background as? GradientDrawable)?.let {
                    it.cornerRadius = r
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) = Unit

            override fun onStopTrackingTouch(p0: SeekBar?) = Unit

        })

        playgroundView.background?.alpha = 0
        bgSwitch.setOnCheckedChangeListener { _, isChecked ->
            playgroundView.background?.alpha = if (isChecked) 255 else 0
        }
        sideBySideSwitch.setOnCheckedChangeListener { _, isChecked ->
            roundRectView.visibility = if (isChecked) View.VISIBLE else View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
