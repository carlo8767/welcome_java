package pattern.flyweigth;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FactoryFlyweigth {


    Map<String, Flyweigth> mapObject = new HashMap<>();


    public Flyweigth getExtrinsicAngryBird (String colour){
        // if you have in the memory...
        Flyweigth bird = mapObject.get(colour);
        Optional<Flyweigth> optional = Optional.ofNullable(bird);
        if(optional.isEmpty()){
            bird = new AngryBird("angry "+ (colour.substring(0,1).toUpperCase()),colour);
            mapObject.put(colour,bird);
        }
        return  bird;
    }
}
