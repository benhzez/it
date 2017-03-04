package com.example.ali.it;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInstaller;

import java.util.HashMap;

/**
 * Created by ALI on 28/02/2017.
 */

public class UserSessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE=0;
    private static final String PREFER_NAME ="AndroidExamplePref";
    private static final String IS_USER_LOGIN ="IsUserLogedIn";
    public static final String KEY_NAME ="name";
    public static final String KEY_EMAIL ="email";

    public UserSessionManager(Context context){
        this._context=context;
        pref=context.getSharedPreferences(PREFER_NAME,PRIVATE_MODE);
        editor=pref.edit();

    }
     public void createUserLoginSession(String name, String email){
         editor.putBoolean(IS_USER_LOGIN,true);
         editor.putString(KEY_NAME,name);
         editor.putString(KEY_EMAIL,email);
         editor.commit();
     }
     public boolean IsUserLogedIn(){
         return pref.getBoolean(IS_USER_LOGIN, false);
     }
     public boolean checkLogin(){
         if(!this.IsUserLogedIn()){//IS_USER_LOGIN.contentEquals(false)){
             Intent i = new Intent(_context,LoginActivity.class);
             i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             _context.startActivity(i);
             return true;
         }
         return false;
     }
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_NAME,pref.getString(KEY_NAME, null));
        user.put(KEY_EMAIL,pref.getString(KEY_EMAIL, null));

        return user;
    }
    public void  logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context,LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    public String getUserName(){
    return pref.getString(KEY_NAME, null);
}
    public void setUserName(String name){
        editor.putString(KEY_NAME,name);
    }
    public String getUserEmail(){
        return pref.getString(KEY_EMAIL, null);
    }
    public void setUserEmail(String email){
        editor.putString(KEY_EMAIL,email);
    }
    }
