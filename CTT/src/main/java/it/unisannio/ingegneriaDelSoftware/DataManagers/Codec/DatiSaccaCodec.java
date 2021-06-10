package it.unisannio.ingegneriaDelSoftware.DataManagers.Codec;

import it.unisannio.ingegneriaDelSoftware.DomainTypes.DatiSacca;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.GruppoSanguigno;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Seriale;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**Codec usato per salvare {@link DatiSacca} all'interno  del DB*/
public class DatiSaccaCodec implements Codec<DatiSacca> {


    @Override
    public DatiSacca decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        reader.readObjectId();
        //recupero tutti i valori prima della DataDiAffidamento in quanto devo gestire il fatto che essa è optional
        Seriale seriale = Seriale.getSeriale(reader.readString(Constants.ELEMENT_SERIALE));
        GruppoSanguigno gs = GruppoSanguigno.valueOf(reader.readString(Constants.ELEMENT_GRUPPO));
        LocalDate dataArrivo = LocalDate.parse(reader.readString(Constants.ELEMENT_DATAARRIVO),
                DateTimeFormatter.ofPattern(Constants.DATEFORMAT));
        String dateBuffer = reader.readString(Constants.ELEMENT_DATAAFFIDAMENTO);
        //se non ho un valore setto nel DB "" altrimenti salvo il suo valore
        LocalDate dataAffidamento = dateBuffer.length()==0?null : LocalDate.parse(dateBuffer,
                DateTimeFormatter.ofPattern(Constants.DATEFORMAT));

        //costruisco la sacca recuperando gli ultimi valori
        DatiSacca unaSacca = new DatiSacca(seriale,gs,dataArrivo,dataAffidamento,
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
        // se la sacca non ha una data di affidamento salvo "" nel DB altrimento salvo il suo valore
        writer.writeString(Constants.ELEMENT_DATAAFFIDAMENTO,
                value.getDataAffidamento().isPresent()?
                        DateTimeFormatter.ISO_LOCAL_DATE.format(value.getDataAffidamento().get()):"");
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