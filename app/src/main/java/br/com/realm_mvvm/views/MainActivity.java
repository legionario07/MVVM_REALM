package br.com.realm_mvvm.views;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.realm_mvvm.R;
import br.com.realm_mvvm.adapters.UserAdapter;
import br.com.realm_mvvm.dao.UserDao;
import br.com.realm_mvvm.databinding.ActivityMainBinding;
import br.com.realm_mvvm.models.User;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.recyclerview.setHasFixedSize(true);

        mainBinding.fabNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                startActivity(intent);
            }
        });

        userAdapter = new UserAdapter(getUsers());
        mainBinding.recyclerview.setAdapter(userAdapter);
        mainBinding.executePendingBindings();


    }

    private List<User> getUsers() {

        UserDao userDao = new UserDao(this);
        List<User> users = userDao.findAll();

        return users;

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        User user = ((UserAdapter) mainBinding.recyclerview.getAdapter()).getItem(item.getGroupId());
        Intent intent = new Intent(this, UserActivity.class);

        switch (item.getItemId()) {

            case 1:

                intent.putExtra("USER", user);
                startActivity(intent);

                break;

            case 2:

                intent.putExtra("USER", user);
                intent.putExtra("ACTION", "DELETE");
                startActivity(intent);

                break;


        }

        return super.onContextItemSelected(item);
    }
}
