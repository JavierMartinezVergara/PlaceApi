package com.javiermtz.placescode.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.javiermtz.placescode.R
import com.javiermtz.placescode.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailPlaceFragment : Fragment() {

  private val viewModel : PlacesViewModel by activityViewModels()

  private lateinit var binding: FragmentSecondBinding
  val args: DetailPlaceFragmentArgs by navArgs()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    binding = FragmentSecondBinding.inflate(inflater, container, false)
    return binding.root

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setViews()
    binding.buttonSecond.setOnClickListener {
      viewModel.insertToFavorite(args.placeItem)
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
  }

  fun setViews(){
    binding.namePlace.text = args.placeItem.name
    binding.ratingPlace.text = args.placeItem.rating.toString()
  }
}
