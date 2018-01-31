import org.json.JSONException;
import org.json.JSONObject;

public class SessionJSON extends JSONObject {
    public SessionJSON() {
        super();
        try {
            super.put("token,", GameManager.sessionToken);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
