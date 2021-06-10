package it.unisannio.ingegneriaDelSoftware.DataManagers.Codec;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.*;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

/**Codec usato per salvare {@link Dipendente} all'interno del DB*/
public class CTTCodec implements Codec<CTT> {

    @Override
    public CTT decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        reader.readObjectId();
        CTT unCTT = new CTT(
            CTTName.getCttName(reader.readString(Constants.ELEMENT_DENOMINAZIONE)),
            reader.readString(Constants.ELEMENT_PROVINCIA),
            reader.readString(Constants.ELEMENT_CITTA),
            reader.readString(Constants.ELEMENT_TELEFONO),
            reader.readString(Constants.ELEMENT_INDIRIZZO),
            reader.readString(Constants.ELEMENT_EMAIL),
            reader.readDouble(Constants.ELEMENT_LATITUDINE),
            reader.readDouble(Constants.ELEMENT_LONGITUDINE));
        reader.readEndDocument();
        return unCTT;
    }

    @Override
    public void encode(BsonWriter writer, CTT value, EncoderContext encoderContext) {

        writer.writeStartDocument();
        writer.writeString(Constants.ELEMENT_DENOMINAZIONE,value.getDenominazione().getCttname());
        writer.writeString(Constants.ELEMENT_PROVINCIA, value.getPosizione().getProvincia());
        writer.writeString(Constants.ELEMENT_CITTA, value.getPosizione().getCitta());
        writer.writeString(Constants.ELEMENT_TELEFONO, value.getTelefono());
        writer.writeString(Constants.ELEMENT_INDIRIZZO, value.getPosizione().getIndirizzo());
        writer.writeString(Constants.ELEMENT_EMAIL,value.getEmail());
        writer.writeDouble(Constants.ELEMENT_LATITUDINE,value.getPosizione().getLatitudine());
        writer.writeDouble(Constants.ELEMENT_LONGITUDINE,value.getPosizione().getLongitudine());
        writer.writeEndDocument();
    }

    @Override
    public Class<CTT> getEncoderClass() {
        return CTT.class;
    }
}