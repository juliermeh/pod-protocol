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
public interface FrameUnmarshaller {
    Frame unmarshal(byte[] f);
}
