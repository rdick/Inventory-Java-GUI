package persistence;

import org.json.JSONObject;

// Represents an interface to implement toJson
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
