package br.com.realm_mvvm.dao;

import io.realm.Realm;

public class RealmUtil {

    public static Integer nextId(Realm realm, Class realmObject){
        int key = 0;

        try{
            key = realm.where(realmObject).max("id").intValue() + 1;

        }catch (Exception e){
            key = 1;
        }

        return key;

    }

}
