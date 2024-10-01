package flashcardsapp.Profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("Capital")
public class CapitalProfile implements ProfileInit {
    @Override
    public String display(String word) {
        return word.toUpperCase();
    }
}
