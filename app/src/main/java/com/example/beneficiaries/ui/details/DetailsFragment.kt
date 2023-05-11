package com.example.beneficiaries.ui.details

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.beneficiaries.R

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var itemSecurityNumber: String? = null

    private val viewModel: DetailsViewModel by viewModels { DetailsViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemSecurityNumber = it.getString(ARG_SECURITY_NUMBER)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textSsn = view.findViewById<TextView>(R.id.textSsn)
        val textDateOfBirth = view.findViewById<TextView>(R.id.textDateOfBirth)
        val textPhoneNumber = view.findViewById<TextView>(R.id.textPhoneNumber)
        val textAddress = view.findViewById<TextView>(R.id.textAddress)

        itemSecurityNumber?.let { viewModel.getItemBySecurityNumber(it) }

        viewModel.item.observe(viewLifecycleOwner) {
            textSsn.text = getString(R.string.ssn, it.socialSecurityNumber)
            textDateOfBirth.text = getString(R.string.date_of_birth, it.dateOfBirth)
            textPhoneNumber.text = getString(R.string.phone_number, it.phoneNumber)
            textAddress.text = getString(R.string.address, it.beneficiaryAddress.toString())
        }
    }

    companion object {
        private const val ARG_SECURITY_NUMBER = "security_number"
        fun newInstance(param1: String) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_SECURITY_NUMBER, param1)
            }
        }
    }
}
