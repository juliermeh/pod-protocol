/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.protocol.def;

import br.edu.ifpb.pod.protocol.Transport;
import br.edu.ifpb.pod.protocol.Frame;
import br.edu.ifpb.pod.protocol.FrameMarshaller;
import br.edu.ifpb.pod.protocol.FrameUnmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 *
 * @author Julierme Heinstein
 */
public abstract class DefaultTransport implements Transport {
    private final Socket socket;
    private final FrameMarshaller marshaller;
    private final FrameUnmarshaller unmarshaller;
    
    public DefaultTransport(Socket socket){
        this.socket = socket;
        this.marshaller = new FrameMarshallerImpl();
        this.unmarshaller = new FrameUnmarshallerImpl();
    }

    @Override
    public Frame send(byte[] content, boolean binary) throws IOException {
        Frame frame = new Frame();
        frame.setTypeAsRequest();
        frame.setPayload(content);
        
        byte[] f = marshaller.marshal(frame);
        socket.getOutputStream().write(f);
        socket.getOutputStream().write(new byte[]{0x00,0x00,0x00});
        socket.getOutputStream().flush();
        
        return frame;
    }

    @Override
    public Frame receive() throws IOException {
        InputStream input = socket.getInputStream();
        ByteBuffer buffer = ByteBuffer.allocate(1026);
        int countZeroByte = 0;
        while(true){
            byte[] b = new byte[1];
            int l = input.read(b);
            if(l > 0){
                buffer.put(b);
                if(b[0] == 0x00){ countZeroByte++; }
                else{ countZeroByte = 0; }
                if(countZeroByte == 3){ break; }
            }
        }
        Arrays.copyOf(buffer.array(), buffer.position()-3);
        return unmarshaller.unmarshal(buffer.array());
    }
    
}
