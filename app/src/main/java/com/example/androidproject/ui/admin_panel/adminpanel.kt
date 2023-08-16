package com.example.androidproject.ui.admin_panel

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.androidproject.R
import com.example.androidproject.databinding.FragmentAdminPanelBinding

class adminpanel : Fragment() {



    private var _binding: FragmentAdminPanelBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val IMAGE_PICK_REQUEST = 123
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val adminpanelViewModel = ViewModelProvider(this).get(admin_panel_ViewModel::class.java)

        _binding = FragmentAdminPanelBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Find the button in the layout
        val openActivityButton: Button = root.findViewById(R.id.add_cart_admin_btn)

        // Set click listener for the button
        openActivityButton.setOnClickListener {
            // Create an Intent to start the new activity
            val intent = Intent(requireContext(), insertion_admin::class.java)
            startActivity(intent)
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
