package java.presentation.ui.fragments.add

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.sqlite.db.SimpleSQLiteQuery.bind
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mynoteapp.presentation.ui.fragments.add.AddNoteViewModel
import java.presentation.core.UIState
import java.domain.model.Note
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@SuppressLint("ResourceType")
@AndroidEntryPoint
class AddNoteFragment() : Fragment(R.id.addNoteFragment), Parcelable {

    private val binding by viewBinding(FragmentAddNoteBinding::bind)
    private val viewModel by viewModels<AddNoteViewModel>()

    constructor(parcel: Parcel) : this() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupRequest()
        setupObservers()
        setupClickListeners()
    }

    private fun initialize() {
    }

    private fun setupRequest() {
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.addNoteState.collect { state ->
                    when (state) {
                        is UIState.Loading -> {
                        }
                        is UIState.Success -> {
                            setupClickListeners()
                        }
                        is UIState.Error -> {
                        }
                        is UIState.Empty -> {
                        }
                    }
                }
            }
        }
    }

    private fun setupClickListeners() {
        fun setupClickListeners() = with(binding) {
            btnSave.setOnClickListener {
                viewModel.addNote(
                    Note(
                        title = editTitle.text.toString(),
                        description = editDesciption.text.toString(),
                        createdAt = System.currentTimeMillis()
                    )
                )
                findNavController().navigateUp()
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AddNoteFragment> {
        override fun createFromParcel(parcel: Parcel): AddNoteFragment {
            return AddNoteFragment(parcel)
        }

        override fun newArray(size: Int): Array<AddNoteFragment?> {
            return arrayOfNulls(size)
        }
    }
}