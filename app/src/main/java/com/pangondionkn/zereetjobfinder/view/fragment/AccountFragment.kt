package com.pangondionkn.zereetjobfinder.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pangondionkn.zereetjobfinder.databinding.FragmentAccountBinding
import com.pangondionkn.zereetjobfinder.viewmodel.AccountViewModel

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var retrievedUsername: String
    private lateinit var retrievedUrlPhoto: String

    private val TAG = AccountFragment::class.java.simpleName

    companion object{
        const val DELIVERED_USERNAME = "DELIVERED_USERNAME"
        const val DELIVERED_URLPHOTO = "DELIVERED_URLPHOTO"
        fun newInstance(deliveredName: String, deliveredUrlPhoto: String): Fragment{
            val fragment = AccountFragment()
            val bundle = Bundle()
            bundle.putString(DELIVERED_USERNAME, deliveredName)
            bundle.putString(DELIVERED_URLPHOTO, deliveredUrlPhoto)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val accountViewModel =
            ViewModelProvider(this).get(AccountViewModel::class.java)

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        accountViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        retrievedUsername = arguments?.getString(DELIVERED_USERNAME) as String
        retrievedUrlPhoto = arguments?.getString(DELIVERED_URLPHOTO) as String

        Log.d(TAG, "username and url : $retrievedUsername and $retrievedUrlPhoto")

        setUpPage(retrievedUsername, retrievedUrlPhoto)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpPage(username: String, urlPhoto: String){

    }
}