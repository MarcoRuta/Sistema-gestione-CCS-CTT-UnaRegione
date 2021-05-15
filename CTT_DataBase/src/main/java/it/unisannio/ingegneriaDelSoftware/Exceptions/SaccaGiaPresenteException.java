package it.unisannio.ingegneriaDelSoftware.Exceptions;

/**Eccezzione sollevata quando si cerca di aggiungere una sacca con un seriale gia presente nel database*/
public class SaccaGiaPresenteException extends Throwable {
    public SaccaGiaPresenteException(String s) {
        super(s);
    }
}
