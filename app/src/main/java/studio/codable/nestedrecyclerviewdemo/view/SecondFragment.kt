package studio.codable.nestedrecyclerviewdemo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import studio.codable.nestedrecyclerviewdemo.HomeViewModel
import studio.codable.nestedrecyclerviewdemo.R
import studio.codable.nestedrecyclerviewdemo.adapter.ParentPaletteAdapter
import studio.codable.nestedrecyclerviewdemo.databinding.FragmentHomeBinding
import studio.codable.nestedrecyclerviewdemo.databinding.FragmentSecondBinding
import studio.codable.nestedrecyclerviewdemo.replaceFragment

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListener()
    }

    private fun onClickListener() {
        binding.btnSwitchFragment.setOnClickListener {
            (requireActivity() as AppCompatActivity).replaceFragment(R.id.fragment_container_view, HomeFragment.getInstance())
        }
    }

    companion object {
        @JvmStatic
        fun getInstance() = SecondFragment()
    }
}