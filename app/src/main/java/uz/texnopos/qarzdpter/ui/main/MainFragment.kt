package uz.texnopos.qarzdpter.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.qarzdpter.Adapter
import uz.texnopos.qarzdpter.Model
import uz.texnopos.qarzdpter.R
import uz.texnopos.qarzdpter.databinding.FragmentMainBinding
import uz.texnopos.qarzdpter.ui.DebtAndLoanDialog

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var b: FragmentMainBinding
    private val adapter = Adapter()
    private var swm: String = ""
    private val db = FirebaseFirestore.getInstance()
    private val viewModel: MainFragmentViewModel by viewModel()
    private var name: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b = FragmentMainBinding.bind(view)
        recyclerView.adapter = adapter
        floating_action_button.setOnClickListener {
            showEditTextDialog()
            addData()
        }
        val docRef = db.collection("Income").document("CI2c5hbMiybEQUVsBVi9")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    b.tvName.text = document.getString("Name")
                    b.tvSwm.text = document.getString("sum")
                    name = document.getString("Name").toString()
                    swm = document.getString("sum").toString()
                } else {
                    Toast.makeText(requireContext(), "Нет файла", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(requireContext(), exception.toString(), Toast.LENGTH_SHORT).show()
            }
        setData()
    }

    private fun showEditTextDialog() {
        DebtAndLoanDialog(requireContext()).show()
    }

    private fun setData() {
        val models: MutableList<Model> = mutableListOf()
        repeat(10) {
            val list: kotlin.collections.MutableList<Model> = kotlin.collections.mutableListOf()
            val model = Model(name = name, swm = swm)
            list.add(model)
            models.add(model)
        }
    }

    fun addData() {
        viewModel.addUser.observe(viewLifecycleOwner) {
            when (it) {
                "loading" -> {
                    Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
                }
                "success" -> {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}