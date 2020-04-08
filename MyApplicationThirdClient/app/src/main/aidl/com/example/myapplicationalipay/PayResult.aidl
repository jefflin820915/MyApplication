// PayResult.aidl
package com.example.myapplicationalipay;

// Declare any non-default types here with import statements

interface PayResult {

   void onPaySuccess();

   void onPayFail(int errorCode,String msg);

}
