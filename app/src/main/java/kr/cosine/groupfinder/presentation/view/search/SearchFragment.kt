package kr.cosine.groupfinder.presentation.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kr.cosine.groupfinder.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var  searchAdatper: SearchAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        searchAdatper.itemClick = object : SearchAdapter.ItemClick {
            override fun onItemClick(id: CharSequence) {
                if(!Tags.selectedTagList.contains(id)){
                    Tags.addTag(id)
                }
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView() {
//        searchAdatper = SearchAdapter(
//            micTags,
//            onAddClick = { position ->
//                    if (Tags.selectedTagList.contains()){
//
//                    }
//                }

        binding.apply {
            tagMicRecyClerView.adapter = SearchAdapter(micTags)
            tagStyleRecyClerView.adapter = SearchAdapter(styleTags)

            tagMicRecyClerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            tagStyleRecyClerView.layoutManager =
                StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL)
        }
    }
        //클릭한 태그 observe하는 코드






}