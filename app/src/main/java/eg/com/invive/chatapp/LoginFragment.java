package eg.com.invive.chatapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import eg.com.invive.chatapp.databinding.FragmentLoginBinding;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationView;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding fragmentLoginBinding;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentLoginBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false);
        return fragmentLoginBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentLoginBinding.buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile = "+"+"20"+""+"1126372201";//fragmentLoginBinding.editTextMobile.getText().toString().trim();


                if(mobile.isEmpty() || mobile.length() < 10){
                    fragmentLoginBinding.editTextMobile.setError("Enter a valid mobile");
                    fragmentLoginBinding.editTextMobile.requestFocus();
                    return;
                }


                PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
                try {
                    Phonenumber.PhoneNumber numberProto = phoneUtil.parse(mobile, "EG");
                    Log.e("NumberParseException",numberProto.toString());

                } catch (NumberParseException e) {
                   Log.e("NumberParseException",e.getMessage());
                }

                Bundle bundle=new Bundle();
                bundle.putString("mobile", mobile);
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_verificationFragment,bundle);
            }
        });

    }
}