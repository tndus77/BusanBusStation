package com.example.busanbusstation;

public class Station {
    public String bstopid;
    public String bstopnm;
    public String arsno;
    public String gpsx;
    public String gpsy;
    public String stoptype;

    public void clear() {
        bstopid = "";
        bstopnm = "";
        arsno   = "";
        gpsx    = "";
        gpsy    = "";
        stoptype = "";
    }

    boolean checkRecvAllData(){
        return bstopid.length() > 0
                && bstopnm.length()  > 0
                && arsno.length()    > 0
                && gpsx.length()     > 0
                && gpsy.length()     > 0
                && stoptype.length() > 0;
    }
}
