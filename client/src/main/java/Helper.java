import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Helper {
    //this method is to hold all of the helper methods for the game.
    public static ArrayList<GameOption> makeArray(JSONArray json) {
        ArrayList<GameOption> arrayList = new ArrayList<GameOption>();
        if (json != null) {
            for (int i = 0; i < json.length(); i++) {
                try {
                    arrayList.add((GameOption) json.get(i));
                } catch (JSONException e) {
                    //swallow
                }
            }
        }
        return arrayList;
    }
}
