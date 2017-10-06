package fr.hadrienmp.codefight.other;

import java.net.URL;
import java.util.Map;

public class Request {
    public final Map<String, String> params;

    private Request(Map<String, String> params) {
        this.params = params;
    }

    public URL referer() {
        return null;
    }
}
