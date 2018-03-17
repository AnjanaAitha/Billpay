package com.example.anjana.billpay;

/**
 * Created by ANJANA on 15-03-2018.
 */

public class Customer {
    String cid,pid;
    public Customer()
    {

    }

    public Customer(String cid,String pid) {
        this.pid = pid;
        this.cid = cid;

    }

    public String getPid() {
        return pid;
    }

    public String getCid() {
        return cid;
    }


}
