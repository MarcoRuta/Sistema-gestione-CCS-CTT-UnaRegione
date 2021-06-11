package it.unisannio.ingegneriaDelSoftware.DataManagers.Codec;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.*;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import org.apache.commons.codec.digest.DigestUtils;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**Codec usato per salvare {@link Dipendente} all'interno  del DB*/
public class DipendenteCodec implements Codec<Dipendente> {

    @Override
    public Dipendente decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        reader.readObjectId();
        Dipendente unDipendente = new Dipendente(
                Cdf.getCDF(reader.readString(Constants.ELEMENT_CDF)),
                reader.readString(Constants.ELEMENT_NOME),
                reader.readString(Constants.ELEMENT_COGNOME),
                LocalDate.parse(reader.readString(Constants.ELEMENT_DATADINASCITA),
                        DateTimeFormatter.ofPattern(Constants.DATEFORMAT)),
                RuoloDipendente.valueOf(reader.readString(Constants.ELEMENT_RUOLO)),
                reader.readString(Constants.ELEMENT_USERNAME),
                reader.readString(Constants.ELEMENT_PASSWORD));
        reader.readEndDocument();
        return unDipendente;
    }

    @Override
    public void encode(BsonWriter writer, Dipendente value, EncoderContext encoderContext) {

        writer.writeStartDocument();
        writer.writeString(Constants.ELEMENT_CDF, value.getCdf().getCodiceFiscale());
        writer.writeString(Constants.ELEMENT_NOME,value.getNome());
        writer.writeString(Constants.ELEMENT_COGNOME, value.getCognome());
        writer.writeString(Constants.ELEMENT_DATADINASCITA,DateTimeFormatter.ISO_LOCAL_DATE.format(value.getDataDiNascita()));
        writer.writeString(Constants.ELEMENT_RUOLO, value.getRuolo().toString());
        writer.writeString(Constants.ELEMENT_USERNAME, value.getUsername());
        //Viene inserita la password criptata con funzione di hashing SHA-256 dell'utility class DigestUtils
        writer.writeString(Constants.ELEMENT_PASSWORD, DigestUtils.sha256Hex(value.getPassword())+"CryptoCTT");
        writer.writeEndDocument();
    }

    @Override
    public Class<Dipendente> getEncoderClass() {
        return Dipendente.class;
    }
    
}