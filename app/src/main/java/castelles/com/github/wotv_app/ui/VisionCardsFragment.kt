package castelles.com.github.wotv_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import castelles.com.github.api.model.VisionCard
import castelles.com.github.api.utils.Error
import castelles.com.github.api.utils.ErrorHandler
import castelles.com.github.api.utils.Loading
import castelles.com.github.api.utils.Success
import castelles.com.github.wotv_app.R
import castelles.com.github.wotv_app.databinding.FragmentListBinding
import castelles.com.github.wotv_app.databinding.FragmentVisionCardsBinding
import castelles.com.github.wotv_app.ui.adapter.VisionCardAdapter
import castelles.com.github.wotv_app.ui.contract.FragmentListContract
import castelles.com.github.wotv_app.viewmodel.VisionCardViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.viewmodel.ext.android.viewModel

class VisionCardsFragment: Fragment(), FragmentListContract<VisionCard> {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: VisionCardAdapter
    private val viewModel: VisionCardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = with(FragmentListBinding.inflate(inflater)) {
        binding = this.apply {
            barTitle = getString(R.string.str_vision_cards)
            errorMessage = getString(R.string.str_vision_cards_not_found)
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
                    is Success -> { loadList(handler.result) }
                    is Error<*> -> { onError(handler.error) }
                }
            }.launchIn(MainScope())

            getVisionCard()
        }
    }

    override fun setRecycler() {
        adapter = VisionCardAdapter(viewModel.items) {

        }
        adapter.setHasStableIds(true)
        binding.rclList.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3)
            adapter = this@VisionCardsFragment.adapter
        }
    }

    override fun loadList(items: List<VisionCard>?) {
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