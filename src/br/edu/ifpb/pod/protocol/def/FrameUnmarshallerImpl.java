/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.protocol.def;

import br.edu.ifpb.pod.protocol.Frame;
import br.edu.ifpb.pod.protocol.FrameUnmarshaller;
import java.util.Arrays;

/**
 *
 * @author Julierme Heinstein
 */
public class FrameUnmarshallerImpl implements FrameUnmarshaller{

    @Override
    public Frame unmarshal(byte[] f) {
        Frame frame = new Frame();
        int first = f[0];
        int second = f[1];
        
        byte isReq = (byte)(first & 0xC0 & 0x00);
        if (isReq == 0x80){ frame.setTypeAsRequest(); }
        else{ frame.setTypeAsResponse(); }
        
        int newFirst = (first & 0x3F) << 10;
        int newSecond = second << 2;
        int lenght = (newFirst | newSecond);
        lenght = lenght >> 6;
        if ((lenght & 0x8000) == (0x8000)){ lenght = 2 + Math.abs(lenght); }
        byte[] content = Arrays.copyOfRange(f, 2, lenght+2);
        frame.setPayload(content);
        return null;
    }
    
}
