package com.example.retrofitproject.login

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.window.SplashScreen
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.retrofitproject.*
import com.example.retrofitproject.databinding.FragmentLoginBinding
import com.example.retrofitproject.mainActivity.MainActivity
import com.example.retrofitproject.myApplication.MyApplication
import com.example.retrofitproject.repository.AppRepository
import com.example.retrofitproject.response.Response
import com.example.retrofitproject.sharePrefrence.SharePrefs
import com.example.retrofitproject.utils.Utils.Companion.getDeviceUniqueID
import com.example.retrofitproject.utils.ViewUtils.Companion.hideProgressDialog
import com.example.retrofitproject.viewModel.LoginViewModel
import com.example.retrofitproject.viewModelFactory.AuthViewModelFactory
import com.google.gson.Gson
import com.google.gson.JsonElement
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private var fcmToken: String = ""
    private lateinit var activity: MainActivity
    private lateinit var appCtx: MyApplication


    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
        appCtx = activity.application as MyApplication
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        var appRepository = AppRepository(activity.applicationContext)
        viewModel =
            ViewModelProvider(this, AuthViewModelFactory(appCtx, appRepository)).get(
                LoginViewModel::class.java
            )

        observe(viewModel.peopleData, ::handleResult)
        observe(viewModel.tokenData, ::handleResultToken)

        binding.btnLogin.setOnClickListener({
            val etMobile = binding.edtUserName.text.toString().trim()
            val etPassword = binding.edtPassword.text.toString().trim()
            val deviceID = getDeviceUniqueID(requireActivity())
            val VersionName = BuildConfig.VERSION_NAME
            val deviceOs = Build.VERSION.RELEASE
            val deviceName = Build.MODEL
            viewModel.doLogin(
                LoginRequest(
                    etMobile,
                    etPassword,
                    fcmToken,
                    VersionName,
                    deviceOs,
                    deviceName,
                    deviceID!!
                )
            )

        })


        return binding.root
    }


    private fun handleResult(it: Response<PeopleModel>) {

        when (it) {
            is Response.Loading -> {

            }
            is Response.Success -> {
                it.data?.let {
                    hideProgressDialog()
                    println("LoginResponse::" + Gson().toJson(it))

                    if (it.active) {
                        SharePrefs.getInstance(requireActivity())
                            .putBoolean(SharePrefs.IS_LOGIN, true)

                        val regApk: UserAuth = it.registeredApk!!
                        if (regApk != null) {
                            val username: String = regApk.userName!!
                            val password: String = regApk.password!!
                            SharePrefs.getInstance(requireActivity())
                                .putString(SharePrefs.TOKEN_NAME, username)
                            SharePrefs.getInstance(requireActivity())
                                .putString(SharePrefs.TOKEN_PASSWORD, password)
                            viewModel.callToken("password", username, password)
                        } else {


                        }
                    } else {


                        Log.e(TAG, "handleResult: 11111111111111111115")
                    }

                }
            }
            is Response.Error -> {

                Log.e(TAG, "handleResult: 11111111111111111114")

            }
        }
    }

    private fun handleResultToken(it: Response<TokenResponse>) {
        when (it) {
            is Response.Loading -> {
                //  showProgressDialog(this)
            }
            is Response.Success -> {
                it.data?.let {
//                    hideProgressDialog()
                    println("LoginResponse::" + Gson().toJson(it))


                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
            }
            is Response.Error -> {
//                hideProgressDialog();

            }
        }
    }


}