package castelles.com.github.wotv_app.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import castelles.com.github.api.utils.Error
import castelles.com.github.api.utils.Loading
import castelles.com.github.api.utils.Success
import castelles.com.github.wotv_app.databinding.FragmentHomeBinding
import castelles.com.github.wotv_app.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = with(FragmentHomeBinding.inflate(inflater)) {
        binding = this
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(IO).launch {
            viewModel.getBuilds {
                when (it) {
                    is Success -> {
                        Log.e("Success request", it.data.toString())
                    }
                    is Error -> {
                        Log.e("Error request", it.error.toString())
                    }
                    is Loading -> {
                        Log.e("Loading request", "...")
                    }
                }
            }
        }
    }
}