package rainbot.baccarat;

import rainbot.components.Request;

public class BaccaratRequest extends Request {
    String data;

    public String getData() {
        return data;
    }

    public BaccaratRequest(String payload) {
        this.data = payload;
    }
}
