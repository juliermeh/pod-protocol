/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.protocol.def;

import br.edu.ifpb.pod.protocol.Frame;
import br.edu.ifpb.pod.protocol.FrameMarshaller;
import java.nio.ByteBuffer;

/**
 *
 * @author Julierme Heinstein
 */
public class FrameMarshallerImpl implements FrameMarshaller{

    @Override
    public byte[] marshal(Frame frame) {
        int first = 0x00;
        if(!frame.isRequest()){ first = 0x80; }
        if(frame.isText()){ first = 0x40 | first; }

        first = first << 8;
        int length = frame.getLength();
        length = length << 4;

        int twobytes = first | length;

        ByteBuffer  buffer = ByteBuffer.allocate(frame.getLength());
        buffer.put((byte) twobytes);
        buffer.put(frame.payload());
        return buffer.array();
    }
    
}
