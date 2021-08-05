package castelles.com.github.wotv_app.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = with(FragmentListBinding.inflate(inflater)) {
        binding = this.apply {
            barTitle = getString(R.string.str_espers)
            errorMessage = getString(R.string.str_espers_not_found)
        }
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
        bindClicks()
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
            llPageButtons.btnNext.isEnabled = true
            llPageButtons.btnPrevious.isEnabled = true
        }
    }

    override fun onError(error: ErrorHandler<out Any?>) {
        binding.apply {
            rclList.visibility = View.GONE
            txvErrorRequest.visibility = View.VISIBLE
            llPageButtons.btnNext.isEnabled = false
            llPageButtons.btnPrevious.isEnabled = false
        }
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
        TODO("Not yet implemented")
    }

    override fun onSearchClick() {
        TODO("Not yet implemented")
    }
}