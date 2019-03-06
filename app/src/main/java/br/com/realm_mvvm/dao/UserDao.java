package br.com.realm_mvvm.dao;

import android.content.Context;
import android.util.Log;

import java.util.List;

import br.com.realm_mvvm.models.User;
import io.realm.Realm;

public class UserDao {

    private Realm realm;

    public UserDao(Context context){
        realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    public User save(User user){
        try{

            realm.beginTransaction();

            if(user.getId()==null){
                user.setId(RealmUtil.nextId(realm, user.getClass()));
            }

            realm.insertOrUpdate(user);

            realm.commitTransaction();

        }catch (Exception e){
            Log.i("TAG", "save: "+e.getMessage());
        }

        return user;


    }


    public Boolean delete(User user){

        try{

            realm.beginTransaction();

            User user1 = realm.where(User.class).equalTo("id", user.getId()).findFirst();
            user1.deleteFromRealm();

            realm.commitTransaction();

        }catch (Exception e){
            Log.i("TAG", "delete: "+e.getMessage());
            return false;
        }

        return true;
    }

    public List<User> findAll(){

        return  realm.where(User.class).findAll();

    }
}
