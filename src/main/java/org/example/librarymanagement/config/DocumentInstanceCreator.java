package org.example.librarymanagement.config;


import com.google.gson.*;
import org.example.librarymanagement.model.Book;
import org.example.librarymanagement.model.Document;
import org.example.librarymanagement.model.Magazine;
import java.lang.reflect.Type;


public class DocumentInstanceCreator implements JsonDeserializer<Document> {

    @Override
    public Document deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();


        String documentType = jsonObject.has("documentType") ? jsonObject.get("documentType").getAsString() : "";

        if ("BOOK".equalsIgnoreCase(documentType)) {
            return context.deserialize(json, Book.class);
        } else if ("MAGAZINE".equalsIgnoreCase(documentType)) {
            return context.deserialize(json, Magazine.class);
        } else {
            throw new JsonParseException("Type de document inconnu : " + documentType);
        }
    }
}
