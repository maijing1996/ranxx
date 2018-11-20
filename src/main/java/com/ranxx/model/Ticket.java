package com.ranxx.model;
 
/**
 * Created by Administrator on 2017/1/9.
 */
public class Ticket {
    private String tid;
    private String ticket;
    private String errcode;
    private String errmsg;
    private String expires_in;
    private String acquiretime;
    private String noncestr;
    private String timestamp;
    public Ticket(String tid, String ticket, String errcode, String errmsg,
                  String expiresIn, String acquiretime, String noncestr,
                  String timestamp) {
        super();
        this.tid = tid;
        this.ticket = ticket;
        this.errcode = errcode;
        this.errmsg = errmsg;
        expires_in = expiresIn;
        this.acquiretime = acquiretime;
        this.noncestr = noncestr;
        this.timestamp = timestamp;
    }
    public String getTid() {
        return tid;
    }
    public void setTid(String tid) {
        this.tid = tid;
    }
    public String getTicket() {
        return ticket;
    }
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
    public String getErrcode() {
        return errcode;
    }
    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }
    public String getErrmsg() {
        return errmsg;
    }
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    public String getExpires_in() {
        return expires_in;
    }
    public void setExpires_in(String expiresIn) {
        expires_in = expiresIn;
    }
    public String getAcquiretime() {
        return acquiretime;
    }
    public void setAcquiretime(String acquiretime) {
        this.acquiretime = acquiretime;
    }
    public String getNoncestr() {
        return noncestr;
    }
    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
