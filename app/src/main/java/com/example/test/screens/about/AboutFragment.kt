package com.example.test.screens.about

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.test.R
import com.example.test.databinding.AboutFragmentBinding
import kotlinx.android.synthetic.main.popup_fragment.view.*

class AboutFragment: Fragment() {

    //binding
    private lateinit var binding: AboutFragmentBinding
    private lateinit var viewModel: AboutViewModel
    private lateinit var myPopupWindow: PopupWindow

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = AboutFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(AboutViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        fun openPopup(): PopupWindow {
            val view = inflater.inflate(R.layout.popup_fragment, null)
            return PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        myPopupWindow = openPopup()

        viewModel.clickedFab.observe(viewLifecycleOwner) { clickedFab ->
            if (clickedFab == true) {
                Log.i("AboutFragment", "You have clicked on the question mark")
                //Open popup
                myPopupWindow?.isOutsideTouchable = true
                myPopupWindow?.isFocusable = true
                myPopupWindow?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
                myPopupWindow.showAsDropDown(binding.about, 0, -200, Gravity.LEFT)

                myPopupWindow.contentView.send_email_button.setOnClickListener{
                    Log.i("AboutFragment", "You have clicked on the button")

                    val i = Intent(Intent.ACTION_SEND)
                    i.type = "message/rfc822"
                    i.putExtra(Intent.EXTRA_EMAIL, arrayOf("jonas.verschueren@outlook.com"))
                    i.putExtra(Intent.EXTRA_SUBJECT, "subject of email")
                    i.putExtra(Intent.EXTRA_TEXT, "body of email")
                    try {
                        startActivity(Intent.createChooser(i, "Send mail..."))
                    } catch (ex: ActivityNotFoundException) {
                        Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show()
                    }
                }

                viewModel.onFabAction()
            }
        }

        return binding.root
    }
}