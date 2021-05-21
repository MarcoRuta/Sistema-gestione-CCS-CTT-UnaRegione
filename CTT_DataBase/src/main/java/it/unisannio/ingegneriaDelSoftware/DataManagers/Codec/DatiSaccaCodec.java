package it.unisannio.ingegneriaDelSoftware.DataManagers.Codec;

import it.unisannio.ingegneriaDelSoftware.Classes.DatiSacca;
import it.unisannio.ingegneriaDelSoftware.Classes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.Classes.Seriale;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DatiSaccaCodec implements Codec<DatiSacca> {


    @Override
    public DatiSacca decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        reader.readObjectId();
        DatiSacca unaSacca = new DatiSacca(
                Seriale.getSeriale(reader.readString(Constants.ELEMENT_SERIALE)),
                GruppoSanguigno.valueOf(reader.readString(Constants.ELEMENT_GRUPPO)),
                LocalDate.parse(reader.readString(Constants.ELEMENT_DATAARRIVO),
                        DateTimeFormatter.ofPattern(Constants.DATEFORMAT)),
                LocalDate.parse(reader.readString(Constants.ELEMENT_DATAAFFIDAMENTO),
                        DateTimeFormatter.ofPattern(Constants.DATEFORMAT)),
                reader.readString(Constants.ELEMENT_ENTEDONATORE),
                reader.readString(Constants.ELEMENT_ENTERICHIEDENTE),
                reader.readString(Constants.ELEMENT_INDIRIZZOENTE));

        reader.readEndDocument();
        return unaSacca;
    }

    @Override
    public void encode(BsonWriter writer, DatiSacca value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeString(Constants.ELEMENT_SERIALE, value.getSeriale().getSeriale());
        writer.writeString(Constants.ELEMENT_GRUPPO,value.getGruppoSanguigno().toString());
        writer.writeString(Constants.ELEMENT_DATAARRIVO, DateTimeFormatter.ISO_LOCAL_DATE.format(value.getDataArrivo()));
        writer.writeString(Constants.ELEMENT_DATAAFFIDAMENTO,DateTimeFormatter.ISO_LOCAL_DATE.format(value.getDataAffidamento()));
        writer.writeString(Constants.ELEMENT_ENTEDONATORE, value.getEnteDonatore());
        writer.writeString(Constants.ELEMENT_ENTERICHIEDENTE, value.getEnteRichiedente());
        writer.writeString(Constants.ELEMENT_INDIRIZZOENTE, value.getIndirizzoEnte());
        writer.writeEndDocument();

    }

    @Override
    public Class<DatiSacca> getEncoderClass() {
        return DatiSacca.class;
    }
}
