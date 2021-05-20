package it.unisannio.ingegneriaDelSoftware.DataManagers.Codec;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import it.unisannio.ingegneriaDelSoftware.Classes.*;
import it.unisannio.ingegneriaDelSoftware.Util.Constants;
import it.unisannio.ingegneriaDelSoftware.Util.DateUtil;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class DipendenteCodec implements Codec<Dipendente> {
    public static void main(String[] args) {
        CodecRegistry pojoCodecRegistry =fromRegistries(
                CodecRegistries.fromCodecs(new SaccaCodec(), new DipendenteCodec(), new DatiSaccaCodec()),
                MongoClient.getDefaultCodecRegistry()
        );
        MongoClient mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
        MongoCollection<Dipendente> dipendenti = database.getCollection(Constants.COLLECTION_DIPENDENTI,Dipendente.class);
        Dipendente unDipendente = new Dipendente(Cdf.getCDF("123456789qwertyu"),
                "pino",
                "abete",
                LocalDate.now().minusYears(20),
                RuoloDipendente.AmministratoreCTT,
                "admin","admin3");


        dipendenti.insertOne(unDipendente);
        System.err.println(dipendenti.find(eq(Constants.ELEMENT_CDF,unDipendente.getCdf().getCodiceFiscale())).first());
    }


    @Override
    public Dipendente decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        reader.readObjectId();
        Dipendente unDipendente = new Dipendente(
                Cdf.getCDF(reader.readString(Constants.ELEMENT_CDF)),
                reader.readString(Constants.ELEMENT_NOME),
                reader.readString(Constants.ELEMENT_COGNOME),
                DateUtil.dateParser(reader.readString(Constants.ELEMENT_DATADINASCITA)),
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
        writer.writeString(Constants.ELEMENT_PASSWORD,value.getPassword());
        writer.writeEndDocument();
    }

    @Override
    public Class<Dipendente> getEncoderClass() {
        return Dipendente.class;
    }
}
