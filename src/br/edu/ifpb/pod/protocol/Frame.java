/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.protocol;

/**
 *
 * @author Julierme Heinstein
 */
public class Frame {
    
    private boolean isRequest;
    private boolean isText;
    private int length;
    private byte[] payload;
    
    public Frame(){
        isRequest = true;
        isText = false;
        length = 0;
        payload = new byte[length+1];
    }
    
    public boolean isRequest(){ return isRequest; }
    public void setTypeAsRequest(){ isRequest = true; }
    public void setTypeAsResponse(){ isRequest = false; }
    public boolean isText(){ return isText = true; }
    public boolean isBinary(){ return isText = false; }
    public int getLength(){ return length; }
    public void setLength(int length){ this.length = length; }
    public byte[] payload(){ return payload; }
    public void setPayload(byte[] payload){ this.payload = payload; }
    
}
