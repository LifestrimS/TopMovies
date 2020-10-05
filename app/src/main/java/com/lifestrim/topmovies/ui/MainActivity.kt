package com.lifestrim.topmovies.ui

import android.graphics.Insets
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import com.lifestrim.topmovies.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window?.setDecorFitsSystemWindows(false)

        ViewCompat
            .setOnApplyWindowInsetsListener(tmdbLogo) { view, insets ->
                view.updatePadding(
                    top = insets.systemWindowInsetTop
                )
                insets
            }

    }

}