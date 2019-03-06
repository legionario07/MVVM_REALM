package br.com.realm_mvvm.viewsmodels;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.text.Editable;
import android.widget.Toast;

import br.com.realm_mvvm.contracts.IUserContract;
import br.com.realm_mvvm.dao.UserDao;
import br.com.realm_mvvm.models.User;
import br.com.realm_mvvm.views.MainActivity;

public class UserViewModel extends BaseObservable implements IUserContract {

    private User user;
    private Context context;
    private UserDao userDao;

    public UserViewModel(Context context){
        this.context = context;
        user = new User();
        this.userDao = new UserDao(context);
    }


    @Override
    public void delete() {

        boolean wasSave = userDao.delete(user);

        if(wasSave)
            setToastSuccess("DELETE SUCCESSFUL");
        else
            setToastError("ERROR");

    }

    @Override
    public void save() {

        String validModel = user.validModel();

        if(!validModel.isEmpty()){
            setToastError(validModel);
            return;
        }

        user = userDao.save(user);

        if(user !=null)
            setToastSuccess("SAVE SUCCESSUFUL");
        else
            setToastError("ERROR");
    }

    @Override
    public void afterTextChangedName(Editable e) {
        user.setName(e.toString());
    }

    @Override
    public void afterTextChangedEmail(Editable e) {
        user.setEmail(e.toString());
    }

    @Override
    public void setToastSuccess(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    public void setToastError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
