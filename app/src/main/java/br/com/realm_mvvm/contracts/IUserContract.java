package br.com.realm_mvvm.contracts;

import android.text.Editable;

public interface IUserContract {

    void save();
    void delete();
    void afterTextChangedName(Editable e);
    void afterTextChangedEmail(Editable e);

    void setToastSuccess(String msg);
    void setToastError(String msg);


}
