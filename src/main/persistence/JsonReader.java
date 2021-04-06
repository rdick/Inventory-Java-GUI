package persistence;

import exceptions.ExceptionIncorrectNumber;
import model.Inventory;
import model.Item;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads inventory from JSON data stored in file
public class JsonReader {
    private String source;
    private int lastId;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads inventory from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Inventory read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseInventory(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses inventory from JSON object and returns it
    private Inventory parseInventory(JSONObject jsonObject) {
        Inventory inv = new Inventory();
        addItems(inv, jsonObject);
        return inv;
    }

    // MODIFIES: Inventory
    // EFFECTS: parses Items from JSON object and adds them to Inventory
    private void addItems(Inventory inv, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("inventory");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(inv, nextItem);
        }
    }

    // MODIFIES: Inventory
    // EFFECTS: parses Inventory from JSON object and adds it to newInventory
    private void addItem(Inventory inv, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int    quantity = jsonObject.getInt("quantity");
        int    costPerUnit = jsonObject.getInt("costPerUnit");
        String details = jsonObject.getString("details");
        Item item = null;
        try {
            item = new Item(name, quantity, costPerUnit, details);
        } catch (ExceptionIncorrectNumber e) {
            System.out.println("Cannot create " + name + "with negative cost or quantity");
        }
        inv.add(item);
    }
}
