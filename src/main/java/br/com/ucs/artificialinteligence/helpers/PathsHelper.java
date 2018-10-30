package br.com.ucs.artificialinteligence.helpers;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class PathsHelper {

    private PathsHelper() {
    }

    public static Path getFromResources(final String pathInsideResources) {
        final URL resource = PathsHelper.class.getClassLoader().getResource(pathInsideResources);
        if( resource == null ) {
            throw buildIllegalArgumentException(pathInsideResources);
        }
        try {
            return Paths.get(resource.toURI());
        } catch (URISyntaxException e) {
            throw buildIllegalArgumentException(pathInsideResources);
        }
    }

    private static IllegalArgumentException buildIllegalArgumentException(final String pathInsideResources) {
        return new IllegalArgumentException(String.format("The path %s does not exist", pathInsideResources));
    }
}
