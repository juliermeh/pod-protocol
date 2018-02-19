/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.protocol;

import br.edu.ifpb.pod.protocol.Frame;
import java.io.IOException;

/**
 *
 * @author Julierme Heinstein
 */
public interface Transport {
    public Frame send(byte[] content, boolean binary) throws IOException;
    public Frame receive() throws IOException;
}
