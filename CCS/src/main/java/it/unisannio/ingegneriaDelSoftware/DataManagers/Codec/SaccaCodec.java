package it.unisannio.ingegneriaDelSoftware.DataManagers.Codec;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.*;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.Sacca;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans.Seriale;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**Codec usato per salvare {@link Sacca} all'interno del DB*/
public class SaccaCodec implements Codec<Sacca> {

    @Override
    public Sacca decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        reader.readObjectId();
        Seriale ser = new Seriale();
        ser.setSeriale( reader.readString(Constants.ELEMENT_SERIALE));
        Sacca unaSacca = new Sacca(
                ser,
                GruppoSanguigno.valueOf(reader.readString(Constants.ELEMENT_GRUPPO)),
                LocalDate.parse(reader.readString(Constants.ELEMENT_DATAPRODUZIONE),
                        DateTimeFormatter.ofPattern(Constants.DATEFORMAT)),
                LocalDate.parse(reader.readString(Constants.ELEMENT_DATASCADENZA),
                        DateTimeFormatter.ofPattern(Constants.DATEFORMAT)),
                reader.readBoolean(Constants.ELEMENT_PRENOTATO));
        reader.readEndDocument();
        return unaSacca;
    }

    @Override
    public void encode(BsonWriter writer, Sacca value, EncoderContext encoderContext) {

        writer.writeStartDocument();
        writer.writeString(Constants.ELEMENT_SERIALE, value.getSeriale().getSeriale());
        writer.writeString(Constants.ELEMENT_GRUPPO,value.getGruppoSanguigno().toString());
        writer.writeString(Constants.ELEMENT_DATAPRODUZIONE, DateTimeFormatter.ISO_LOCAL_DATE.format(value.getDataProduzione()));
        writer.writeString(Constants.ELEMENT_DATASCADENZA,DateTimeFormatter.ISO_LOCAL_DATE.format(value.getDataScadenza()));
        writer.writeBoolean(Constants.ELEMENT_PRENOTATO, value.isPrenotato());
        writer.writeEndDocument();
    }

    @Override
    public Class<Sacca> getEncoderClass() {
        return Sacca.class;
    }
}