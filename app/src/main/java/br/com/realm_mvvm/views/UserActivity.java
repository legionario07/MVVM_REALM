package br.com.realm_mvvm.views;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.realm_mvvm.R;
import br.com.realm_mvvm.databinding.ActivityUserBinding;
import br.com.realm_mvvm.models.User;
import br.com.realm_mvvm.viewsmodels.UserViewModel;

import static android.view.View.GONE;

public class UserActivity extends AppCompatActivity {

    private ActivityUserBinding userBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userBinding = DataBindingUtil.setContentView(this, R.layout.activity_user);
        userBinding.setUserViewModel(new UserViewModel(this));
        hasUser();
        userBinding.executePendingBindings();

    }

    private void hasUser(){

        if(getIntent() != null && getIntent().getExtras() !=null && getIntent().hasExtra("USER")){

            userBinding.getUserViewModel().setUser((User) getIntent().getExtras().get("USER"));

            if(getIntent().hasExtra("ACTION") && getIntent().getExtras().get("ACTION").equals("DELETE")){
                userBinding.imgSave.setVisibility(View.GONE);
                userBinding.imgDelete.setVisibility(View.VISIBLE);
            }

        }

    }

}
