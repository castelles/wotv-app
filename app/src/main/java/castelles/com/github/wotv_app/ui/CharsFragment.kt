package castelles.com.github.wotv_app.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import castelles.com.github.api.model.Character
import castelles.com.github.api.utils.Error
import castelles.com.github.api.utils.ErrorHandler
import castelles.com.github.api.utils.Loading
import castelles.com.github.api.utils.Success
import castelles.com.github.wotv_app.R
import castelles.com.github.wotv_app.databinding.FragmentCharsBinding
import castelles.com.github.wotv_app.databinding.FragmentListBinding
import castelles.com.github.wotv_app.ui.adapter.CharacterAdapter
import castelles.com.github.wotv_app.ui.contract.FragmentListContract
import castelles.com.github.wotv_app.viewmodel.CharacterViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel

class CharsFragment: Fragment(), FragmentListContract<Character> {

    private val viewModel: CharacterViewModel by viewModel()

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: CharacterAdapter

    private var isSearching = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = with(FragmentListBinding.inflate(inflater)) {
        lifecycleOwner = viewLifecycleOwner
        binding = this.apply {
            barTitle = "Personagens"
            errorMessage = "Personagens não encontrados. Tente novamente mais tarde."
            viewModel = this@CharsFragment.viewModel
        }
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
        addSearch()
        bindClicks()
        bindViewModel()
    }

    override fun bindViewModel() {
        viewModel.apply {
            viewState.onEach { state ->
                when(state.handler) {
                    is Loading -> {}
                    is Success -> { loadList(state.handler.result) }
                    is Error<*> -> {
                        onError(state.handler.error)
                        Toast.makeText(
                            requireContext(),
                            "Erro: " + state.handler.error.toString(),
                            LENGTH_LONG
                        ).show()
                    }
                }
            }.launchIn(MainScope())

            searchClick.observe(viewLifecycleOwner) {
                onSearchClick()
            }

            getCharacters()
        }
    }

    override fun setRecycler() {

        adapter = CharacterAdapter(viewModel.items) {
            Log.e("character", it.toString())
        }

        binding.rclList.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = this@CharsFragment.adapter
        }
    }

    private fun addSearch() {
        binding.svSearchInput.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.also {
                    viewModel.search(newText)
                    adapter.notifyDataSetChanged()
                }
                return true
            }
        })
    }

    override fun loadList(items: List<Character>?) {
        items?.let {
            viewModel.addToList(items)
            adapter.notifyDataSetChanged()
        }

        binding.apply {
            rclList.visibility = VISIBLE
            txvErrorRequest.visibility = GONE
        }
        enablePageButtons()
    }

    override fun onError(error: ErrorHandler<out Any?>) {
        binding.apply {
            rclList.visibility = GONE
            txvErrorRequest.visibility = VISIBLE
        }
        enablePageButtons(false)
    }

    override fun bindClicks() {
        binding.llPageButtons.apply {
            btnPrevious.setOnClickListener {
                viewModel.page(false)
                adapter.notifyDataSetChanged()
            }
            btnNext.setOnClickListener {
                viewModel.page(true)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onSearchClick() {
        binding.apply {
            if (isSearching) {
                imvSearch.setImageDrawable(resources.getDrawable(R.drawable.ic_action_search, null))
                isSearching = false
                ctlSearch.visibility = GONE
                svSearchInput.clearFocus()
                svSearchInput.onActionViewCollapsed()
                enablePageButtons()
                this@CharsFragment.viewModel.search("")
            } else {
                imvSearch.setImageDrawable(resources.getDrawable(R.drawable.ic_action_clear, null))
                isSearching = true
                ctlSearch.visibility = VISIBLE
                svSearchInput.requestFocus()
                svSearchInput.onActionViewExpanded()
                enablePageButtons(false)
            }

        }
    }

    override fun enablePageButtons(enable: Boolean) {
        binding.apply {
            llPageButtons.btnNext.isEnabled = enable
            llPageButtons.btnPrevious.isEnabled = enable
        }
    }

}