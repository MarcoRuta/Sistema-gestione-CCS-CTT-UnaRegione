package it.unisannio.ingegneriaDelSoftware.DataManagers.Codec;

import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Util.DateUtil;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import java.time.format.DateTimeFormatter;


public class SaccaCodec implements Codec<Sacca> {


    @Override
    public Sacca decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        reader.readObjectId();
        Sacca unaSacca = new Sacca(
                Seriale.getSeriale(reader.readString("seriale")),
                GruppoSanguigno.valueOf(reader.readString("gruppo_sanguigno")),
                DateUtil.dateParser(reader.readString("data_di_produzione")),
                DateUtil.dateParser(reader.readString("data_di_scadenza")),
                reader.readBoolean("prenotato"));
        reader.readEndDocument();
        return unaSacca;
    }

    @Override
    public void encode(BsonWriter writer, Sacca value, EncoderContext encoderContext) {

        writer.writeStartDocument();
        writer.writeString("seriale", value.getSeriale().getSeriale());
        writer.writeString("gruppo_sanguigno",value.getGruppoSanguigno().toString());
        writer.writeString("data_di_produzione", DateTimeFormatter.ISO_LOCAL_DATE.format(value.getDataProduzione()));
        writer.writeString("data_di_scadenza",DateTimeFormatter.ISO_LOCAL_DATE.format(value.getDataScadenza()));
        writer.writeBoolean("prenotato", value.isPrenotato());
        writer.writeEndDocument();
    }

    @Override
    public Class<Sacca> getEncoderClass() {
        return Sacca.class;
    }
}
