package com.PolyRepo.PolyRepo.exception;

public class Filenotfoundexception extends RuntimeException {
private  int status;
private String message;
public Filenotfoundexception(){

}
public Filenotfoundexception(int status, String message) {
this.status = status;
this.message = message;
}
}
