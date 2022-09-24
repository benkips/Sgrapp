package com.mabnets.sgrapp.fragments
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.mabnets.sgrapp.R
import com.mabnets.sgrapp.databinding.FragmentLauncherBinding
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class Launcher : Fragment(R.layout.fragment_launcher) {
    private var _binding: FragmentLauncherBinding? = null
    private val binding get() = _binding!!
    private val handler= Handler()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLauncherBinding.bind(view)
    }
    private val runnable= Runnable {
        Navigation.findNavController(requireView()).navigate(R.id.action_launcher_to_mainfragment)
    }
    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable,500)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
}