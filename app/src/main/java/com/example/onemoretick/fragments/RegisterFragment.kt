package com.example.onemoretick.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.onemoretick.R
import com.example.onemoretick.helpers.UtilValidators
import com.example.onemoretick.interfaces.ActivitiesFragmentsCommunication
import com.example.onemoretick.models.request.RegisterUserRequest
import com.example.onemoretick.viewModel.RegisterViewModel

class RegisterFragment : Fragment() {
    //    private var auth: FirebaseAuth? = null
//    var database =
//        FirebaseDatabase.getInstance("https://dogwalking-f7488-default-rtdb.firebaseio.com/")
//    var myRef = database.reference
//    private var roleStr = "USER"
//    var fusedLocationProviderClient: FusedLocationProviderClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        auth = FirebaseAuth.getInstance()
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    }

    private val registerViewModel by viewModels<RegisterViewModel>()

    override fun onStart() {
        super.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActivitiesFragmentsCommunication) {
            val fragmentsCommunication: ActivitiesFragmentsCommunication =
                context as ActivitiesFragmentsCommunication
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.sign_up_button)
            .setOnClickListener { validateEmailAndPassword() }
    }

    private fun validateEmailAndPassword() {
        if (view == null) {
            return
        }
        val emailEdtText = requireView().findViewById<EditText>(R.id.email_text_input_editText)
        val passwordEdtText =
            requireView().findViewById<EditText>(R.id.password_text_input_editText)
        val email = emailEdtText.text.toString()
        val password = passwordEdtText.text.toString()
        if (!UtilValidators.isValidEmail(email)) {
            emailEdtText.error = "Invalid Email"
            return
        } else {
            emailEdtText.error = null
        }
        if (!UtilValidators.isValidPassword(password)) {
            passwordEdtText.error = "Invalid Password"
            return
        } else {
            passwordEdtText.error = null
        }
        registerUser(email, password)
    }

    private fun registerUser(email: String, password: String): Boolean {
        val registerUserRequest = RegisterUserRequest(email, password)
        registerViewModel.registerUser(registerUserRequest)
        return true
    }
//
//    private fun createFirebaseUser(
//        email: String,
//        password: String,
//        fullNameStr: String,
//        callingNameStr: String,
//        phoneStr: String,
//        addressStr: String,
//        roleStr: String
//    ) {
//        if (activity == null) {
//            return
//        }
//        auth!!.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(
//                activity!!
//            ) { task ->
//                if (task.isSuccessful) {
//                    val user = auth!!.currentUser
//                    val userProfileId = user!!.uid
//                    val userEmail = user.email
//                    fetchCurrentLocation(
//                        userProfileId,
//                        userEmail,
//                        fullNameStr,
//                        callingNameStr,
//                        phoneStr,
//                        addressStr,
//                        roleStr,
//                        userProfileId
//                    )
//                    Toast.makeText(context, "Authentication success", Toast.LENGTH_SHORT).show()
//                    gotoHomeMapActivity()
//                } else {
//                    Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show()
//                }
//            }
//    }

//    private fun createDialog(email: String, password: String) {
//        val dialog = Dialog(context!!)
//        dialog.setContentView(R.layout.dialog)
//        val fullName = dialog.findViewById<EditText>(R.id.nameTextInputEditText)
//        val collingName = dialog.findViewById<EditText>(R.id.callingNameTextInputEditText)
//        val phoneNumber = dialog.findViewById<EditText>(R.id.phone_text_input_editText)
//        val address = dialog.findViewById<EditText>(R.id.address_text_input_editText)
//        val rGroup = dialog.findViewById<RadioGroup>(R.id.rolesGroup)
//        rGroup.setOnCheckedChangeListener { group: RadioGroup, checkedId: Int ->
//            val checkedRadioButton = group.findViewById<RadioButton>(checkedId)
//            val isChecked = checkedRadioButton.isChecked
//            if (isChecked) {
//                roleStr = checkedRadioButton.text.toString()
//            }
//        }
//        val proceed = dialog.findViewById<Button>(R.id.button)
//        proceed.setOnClickListener { view: View? ->
//            if (TextUtils.isEmpty(
//                    fullName.text.toString()
//                ) && TextUtils.isEmpty(collingName.text.toString()) && TextUtils.isEmpty(
//                    phoneNumber.text.toString()
//                ) && TextUtils.isEmpty(address.text.toString())
//            ) {
//                Toast.makeText(context, "Invalid field", Toast.LENGTH_SHORT).show()
//            } else {
//                try {
//                    if (geoLocate(address.text.toString()) != null) {
//                        createFirebaseUser(
//                            email,
//                            password,
//                            fullName.text.toString(),
//                            collingName.text.toString(),
//                            phoneNumber.text.toString(),
//                            address.text.toString(),
//                            roleStr
//                        )
//                        //Toast.makeText(getContext(), "Ok", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(
//                            context,
//                            "The address is not correct",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//                dialog.dismiss()
//            }
//        }
//        dialog.show()
//    }

//    fun createNewUser(
//        userProfileId: String?,
//        email: String?,
//        fullName: String?,
//        collingName: String?,
//        phoneNumber: String?,
//        address: String?,
//        role: String?,
//        userId: String?
//    ) {
//        val userProfile =
//            UserProfile(email, fullName, collingName, phoneNumber, address, role, userId)
//        myRef.child("users").child(userProfileId!!).setValue(userProfile)
//    }

    fun gotoHomeActivity() {
        Toast.makeText(context, "Login success!", Toast.LENGTH_SHORT).show();
//        val intent = Intent(activity, HomeMapActivity::class.java)
//        startActivity(intent)
//        activity!!.finish()
    }

//    fun fetchCurrentLocation(
//        userProfileId: String?,
//        email: String?,
//        fullName: String?,
//        collingName: String?,
//        phoneNumber: String?,
//        address: String?,
//        role: String?,
//        userId: String?
//    ) {
//        if (ActivityCompat.checkSelfPermission(
//                context!!,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                activity!!, arrayOf(
//                    Manifest.permission.ACCESS_FINE_LOCATION
//                ), REQUEST_CODE
//            )
//            return
//        }
//        val task: Task<Location> = fusedLocationProviderClient.getLastLocation()
//        task.addOnSuccessListener { location: Location? ->
//            if (location != null) {
//                createNewUser(
//                    userProfileId,
//                    email,
//                    fullName,
//                    collingName,
//                    phoneNumber,
//                    address,
//                    role,
//                    userId
//                )
//            }
//        }
//    }

//    @Throws(IOException::class)
//    fun geoLocate(addressString: String?): Address? {
//        var address: Address? = null
//        val geocoder = Geocoder(context)
//        var list: List<Address?> = ArrayList()
//        try {
//            list = geocoder.getFromLocationName(addressString, 1)
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//        if (list.size > 0) {
//            address = list[0]
//        }
//        return address
//    }

    companion object {
        const val TAG_REGISTER = "TAG_REGISTER"

        //private const val REQUEST_CODE = 101
        fun newInstance(): RegisterFragment {
            val args = Bundle()
            val fragment = RegisterFragment()
            fragment.arguments = args
            return fragment
        }
    }
}