package guru.springframework.sfgpetclinic.fauxspring;

import org.assertj.core.internal.bytebuddy.matcher.StringMatcher;

import java.util.HashMap;
import java.util.Map;

public class ModelMapImpl implements Model {
    Map<String,Object> map = new HashMap<>();
    @Override
    public void addAttribute(String key, Object o) {
        map.put(key,o);
    }

    @Override
    public void addAttribute(Object o) {
        // Do Nothing...
    }

    public Map<String, Object> getMap() {
        return map;
    }
}
