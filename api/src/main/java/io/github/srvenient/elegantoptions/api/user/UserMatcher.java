package io.github.srvenient.elegantoptions.api.user;

import java.util.UUID;

public interface UserMatcher {

    User getUserId(UUID uuid);

}
