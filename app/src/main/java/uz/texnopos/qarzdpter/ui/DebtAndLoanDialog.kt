package uz.texnopos.qarzdpter.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import uz.texnopos.qarzdpter.R
import uz.texnopos.qarzdpter.databinding.EditTextLayoutBinding
import uz.texnopos.qarzdpter.ui.main.MainFragmentViewModel
import java.text.SimpleDateFormat

class DebtAndLoanDialog(private val mContext: Context) : Dialog(mContext) {

    private lateinit var binding: EditTextLayoutBinding
    private val sdf = SimpleDateFormat("dd.M.yyyy")
    private lateinit var mainFragmentViewModel: MainFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.edit_text_layout)
        binding = EditTextLayoutBinding.inflate(layoutInflater)
        binding.tvDate.text = sdf.toString()

        binding.btnLoan.setOnClickListener {
            mainFragmentViewModel.addUser(
                binding.editTextForName.text.toString(),
                binding.editTextForPrice.text.toString(),
                binding.editTextForDescription.text.toString()
            )
            Toast.makeText(mContext, "plus", Toast.LENGTH_SHORT).show()
            dismiss()
            return@setOnClickListener
        }
        binding.btnDebt.setOnClickListener(View.OnClickListener {
            mainFragmentViewModel.addUser(
                binding.editTextForName.text.toString(),
                binding.editTextForPrice.text.toString(),
                binding.editTextForDescription.text.toString()
            )
            Toast.makeText(mContext, "minus", Toast.LENGTH_SHORT).show()
            dismiss()
        })
    }



    override fun onStart() {
        super.onStart()
        binding.btnBack.setOnClickListener( var View.OnClickListener() {
            Toast.makeText(mContext, "back", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }
}