/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.protocol.client;

import br.edu.ifpb.pod.protocol.Frame;
import br.edu.ifpb.pod.protocol.Transport;
import br.edu.ifpb.pod.protocol.def.DefaultTransport;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Julierme Heinstein
 */
public class Client {
    
    public static void main(String[] args) throws IOException {
      
        Socket socket = new Socket("localhost", 10999);
        Transport transport = new DefaultTransport(socket);
        Frame frame = transport.send("Hello World".getBytes(), true);
        
        
        
    }
    
}
