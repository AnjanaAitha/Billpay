package com.example.anjana.billpay;

/**
 * Created by ANJANA on 14-03-2018.
 */

public class Itemss {
    String itemname,itemid,itemprice;
    public Itemss(String id, String pid){

    }

    public Itemss(String itemname, String itemid, String itemprice) {
        this.itemname = itemname;
        this.itemid = itemid;
        this.itemprice = itemprice;
    }

    public String getItemname() {
        return itemname;
    }

    public String getItemid() {
        return itemid;
    }

    public String getItemprice() {
        return itemprice;
    }
}
