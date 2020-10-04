package core.utils.json_parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import core.collection.CollectionManager;
import core.stored.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.ZonedDateTime;
import java.util.List;

public class ParserJson {
    private static final Logger logger = LoggerFactory.getLogger(ParserJson.class);
    private static String filePath = "C:\\Users\\Nikita\\IdeaProjects\\lab6server\\src\\file.json";
    private static CollectionManager collectionManager = CollectionManager.getCollectionManager();

    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder
            .registerTypeAdapter(ZonedDateTime.class, new TypeAdapter<ZonedDateTime>() {
                @Override
                public void write(JsonWriter out, ZonedDateTime value) throws IOException {
                    out.value(value.toString());
                }

                @Override
                public ZonedDateTime read(JsonReader in) throws IOException {
                    return ZonedDateTime.parse(in.nextString());
                }
            })
            .serializeNulls()
            .setPrettyPrinting()
            .enableComplexMapKeySerialization()
            .create();

    public static void fromCollectionToJson() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(collectionManager.getLinkedList(), writer);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public static void fromJsonToCollection() {
        if (filePath != null) {
            try (Reader reader = new FileReader(filePath)) {
                collectionManager.initList();
                List<Worker> workers = gson.fromJson(reader, new TypeToken<List<Worker>>(){}.getType());
                if (workers.size() > 0) for (Worker worker: workers) { collectionManager.addJsonObject(worker); }
                logger.info("Сохраненная коллекция выгруженна");
            } catch (IOException e) {
                logger.error(e.getMessage());
            } catch (SecurityException e) {
                logger.error("Недостаточно прав для открытия файла.");
            } catch (NullPointerException e) {
                logger.error("В файле нет объектов.");
            } catch (com.google.gson.JsonSyntaxException e) {
                logger.error("Ошибка в содержании файла " + e.getMessage());
            }
        } else { logger.error("Переменная окружения не выставлена."); }
    }
}
