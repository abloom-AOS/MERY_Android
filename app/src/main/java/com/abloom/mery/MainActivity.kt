package com.abloom.mery

import android.graphics.Color
import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.abloom.mery.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navHostFragment: NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    private val navController: NavController by lazy { navHostFragment.navController }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupBackPressed()
        setupDestinationChangedListener()
    }

    private fun setupBackPressed() {
        onBackPressedDispatcher.addCallback {
            navController.navigateUp() || onSupportNavigateUp()
        }
    }

    // TODO("추후 애니메이션으로 배경이 서서히 변하도록 수정할 예정")
    private fun setupDestinationChangedListener() {
        navController.addOnDestinationChangedListener { _, dest, _ ->
            if (dest.id == R.id.homeFragment) {
                binding.root.background = getColor(R.color.primary_5).toDrawable()
                WindowCompat.getInsetsController(
                    window,
                    window.decorView
                ).isAppearanceLightStatusBars = true
            } else {
                binding.root.background = Color.BLACK.toDrawable()
                WindowCompat.getInsetsController(
                    window,
                    window.decorView
                ).isAppearanceLightStatusBars = false
            }
        }
    }
}

