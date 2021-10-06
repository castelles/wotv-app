package castelles.com.github.wotv_app.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import castelles.com.github.api.model.Esper
import castelles.com.github.api.utils.Error
import castelles.com.github.api.utils.ErrorHandler
import castelles.com.github.api.utils.Loading
import castelles.com.github.api.utils.Success
import castelles.com.github.wotv_app.R
import castelles.com.github.wotv_app.databinding.FragmentEspersBinding
import castelles.com.github.wotv_app.databinding.FragmentListBinding
import castelles.com.github.wotv_app.ui.adapter.EsperAdapter
import castelles.com.github.wotv_app.ui.contract.FragmentListContract
import castelles.com.github.wotv_app.viewmodel.EsperViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class EspersFragment: Fragment(), FragmentListContract<Esper> {

    private lateinit var binding: FragmentListBinding
    private val viewModel: EsperViewModel by viewModel()

    private lateinit var adapter: EsperAdapter
    private var isSearching = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = with(FragmentListBinding.inflate(inflater)) {
        binding = this.apply {
            barTitle = getString(R.string.str_espers)
            errorMessage = getString(R.string.str_espers_not_found)
            viewModel = this@EspersFragment.viewModel
        }
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
        bindClicks()
        addSearch()
        bindViewModel()
    }

    override fun bindViewModel() {
        viewModel.apply {
            viewState.onEach { state ->
                when (val handler = state.handler) {
                    is Loading -> {}
                    is Success -> { loadList(handler.result)}
                    is Error<*> -> onError(handler.error)
                }
            }.launchIn(MainScope())

            searchClick.observe(viewLifecycleOwner) {
                onSearchClick()
            }

            getEspers()
        }
    }

    override fun setRecycler() {
        adapter = EsperAdapter(viewModel.items) {
            Log.e("esper", it.toString())
        }

        binding.rclList.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = this@EspersFragment.adapter
        }
    }

    override fun loadList(items: List<Esper>?) {
        items?.let {
            viewModel.addToList(items)
            adapter.notifyDataSetChanged()
        }

        binding.apply {
            rclList.visibility = View.VISIBLE
            txvErrorRequest.visibility = View.GONE
        }
        enablePageButtons()
    }

    override fun onError(error: ErrorHandler<out Any?>) {
        binding.apply {
            rclList.visibility = View.GONE
            txvErrorRequest.visibility = View.VISIBLE
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

    override fun enablePageButtons(enable: Boolean) {
        binding.apply {
            llPageButtons.btnNext.isEnabled = enable
            llPageButtons.btnPrevious.isEnabled = enable
        }
    }

    override fun onSearchClick() {
        binding.apply {
            if (isSearching) {
                imvSearch.setImageDrawable(
                    resources.getDrawable(R.drawable.ic_action_search, null)
                )
                isSearching = false
                ctlSearch.visibility = View.GONE
                svSearchInput.clearFocus()
                svSearchInput.onActionViewCollapsed()
                enablePageButtons()
                this@EspersFragment.viewModel.search("")
            } else {
                imvSearch.setImageDrawable(
                    resources.getDrawable(R.drawable.ic_action_clear, null)
                )
                isSearching = true
                ctlSearch.visibility = View.VISIBLE
                svSearchInput.requestFocus()
                svSearchInput.onActionViewExpanded()
                enablePageButtons(false)
            }

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
}