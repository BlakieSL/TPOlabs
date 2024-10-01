package flashcardsapp.Profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("Lower")
public class LowerProfile implements ProfileInit {
    @Override
    public String display(String word) {
        return word.toLowerCase();
    }
}
