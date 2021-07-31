package castelles.com.github.wotv_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import castelles.com.github.wotv_app.databinding.FragmentRanksBinding

class RanksFragment: Fragment() {

    private lateinit var binding: FragmentRanksBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = with(FragmentRanksBinding.inflate(inflater)) {
        binding = this
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}