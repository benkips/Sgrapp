package com.mabnets.sgrapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
/*import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView*/
import com.google.android.material.snackbar.Snackbar
import com.mabnets.sgrapp.R
import com.mabnets.sgrapp.databinding.FragmentMainfragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class mainfragment : Fragment(R.layout.fragment_mainfragment) {
    private var _binding: FragmentMainfragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adView: AdView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainfragmentBinding.bind(view)

        binding.toolbarLayout.title = "Madaraka Express"
        binding.fab.setOnClickListener { view ->
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Book your SGR train Ticket on your phone \n  https://play.google.com/store/apps/details?id=com.mabnets.sgrapp"
            )
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
        binding.content.bt.setOnClickListener {v->
            val c = "https://metickets.krc.co.ke/index.php"
            Navigation.findNavController(v).navigate(R.id.action_mainfragment_to_wvinfo,
                bundleOf("web" to c)
            )

        }
        binding.content.ct.setOnClickListener {v->
            val c = "https://metickets.krc.co.ke/check-ticket.php"
            Navigation.findNavController(v).navigate(R.id.action_mainfragment_to_wvinfo,
                bundleOf("web" to c)
            )
        }
        binding.content.nh.setOnClickListener {v->
            val c = "https://metickets.krc.co.ke/information.php#howitworks"
            Navigation.findNavController(v).navigate(R.id.action_mainfragment_to_wvinfo,
                bundleOf("web" to c)
            )

        }
        binding.content.st.setOnClickListener {v->
            val c = "https://metickets.krc.co.ke/stations.php"
            Navigation.findNavController(v).navigate(R.id.action_mainfragment_to_wvinfo,
                bundleOf("web" to c)
            )
        }


        adView = AdView(context)
        binding.content.bannerContainertwo.addView(adView)
        adView.adUnitId ="ca-app-pub-4814079884774543/5646925535"

        adView.adSize = adSize
        val adRequest = AdRequest
            .Builder()
            .build()
        // Start loading the ad in the background.
        adView.loadAd(adRequest)

    }
    private val adSize: AdSize
        get() {
            val display =activity?.windowManager!!.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)

            val density = outMetrics.density

            var adWidthPixels = binding.content.bannerContainertwo.width.toFloat()
            if (adWidthPixels == 0f) {
                adWidthPixels = outMetrics.widthPixels.toFloat()
            }

            val adWidth = (adWidthPixels / density).toInt()
            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth)
        }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
}