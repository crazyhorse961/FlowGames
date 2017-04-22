package it.minecube.flowgames.exceptions;/**
 * Created by nini7 on 22.04.2017.
 */


/**
 * @author crazyhoorse961
 */
public class InvalidMinigameException extends RuntimeException
{

    public InvalidMinigameException(){}

    public InvalidMinigameException(String message){
        super(message);
    }
}
