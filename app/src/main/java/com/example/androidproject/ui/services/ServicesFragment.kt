package com.example.androidproject.ui.services

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.androidproject.R
import com.example.androidproject.databinding.FragmentServicesBinding

class ServicesFragment : Fragment() {

private var _binding: FragmentServicesBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val servicesViewModel =
            ViewModelProvider(this).get(ServicesViewModel::class.java)

    _binding = FragmentServicesBinding.inflate(inflater, container, false)
    val root: View = binding.root

      // Find the button in the layout
      val openActivityButton: Button = root.findViewById(R.id.button_ship)

      // Set click listener for the button
      openActivityButton.setOnClickListener {
          // Create an Intent to start the new activity
          val intent = Intent(requireContext(), add_to_cart::class.java)
          startActivity(intent)
      }


    return root




  }


override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}