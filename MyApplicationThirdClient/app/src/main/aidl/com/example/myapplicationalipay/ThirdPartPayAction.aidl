// ThirdPartPayAction.aidl
package com.example.myapplicationalipay;
import com.example.myapplicationalipay.PayResult;


// Declare any non-default types here with import statements

interface ThirdPartPayAction {

 //pay request
  void requestPay(String orderInfo,float payMoney,PayResult callBack)   ;

}
