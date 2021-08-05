package castelles.com.github.wotv_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import castelles.com.github.api.model.Equipments
import castelles.com.github.api.utils.Error
import castelles.com.github.api.utils.ErrorHandler
import castelles.com.github.api.utils.Loading
import castelles.com.github.api.utils.Success
import castelles.com.github.wotv_app.R
import castelles.com.github.wotv_app.databinding.FragmentListBinding
import castelles.com.github.wotv_app.ui.adapter.EquipmentAdapter
import castelles.com.github.wotv_app.ui.contract.FragmentListContract
import castelles.com.github.wotv_app.viewmodel.EquipmentViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class EquipmentsFragment : Fragment(), FragmentListContract<Equipments> {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: EquipmentAdapter

    private val viewModel: EquipmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = with(FragmentListBinding.inflate(inflater)) {
        binding = this.apply {
            barTitle = getString(R.string.str_equipments)
            errorMessage = getString(R.string.str_equipments_not_found)
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
                    is Success -> loadList(handler.result)
                    is Error<*> -> onError(handler.error)
                }
            }.launchIn(MainScope())
            getEquipments()
        }
    }

    override fun setRecycler() {
        adapter = EquipmentAdapter(viewModel.items) {

        }

        adapter.setHasStableIds(true)
        binding.rclList.apply {
            setHasFixedSize(false)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = this@EquipmentsFragment.adapter
        }
    }

    override fun loadList(items: List<Equipments>?) {
        items?.also {
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