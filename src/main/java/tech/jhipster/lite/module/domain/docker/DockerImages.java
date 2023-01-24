package tech.jhipster.lite.module.domain.docker;

import tech.jhipster.lite.error.domain.Assert;

public interface DockerImages {
  /**
   * Get docker images versions
   *
   * @return The docker images versions known in this instance
   */
  DockerImageVersions get();

  /**
   * Get docker image information
   *
   * @param imageName
   *          name of the image to get information for
   * @return The docker image with this name
   * @throws UnknownDockerImageException
   *           if the image can't be found
   */
  default DockerImageVersion get(DockerImageName imageName) {
    Assert.notNull("imageName", imageName);

    return get().get(imageName);
  }

  /**
   * Get docker image information
   *
   * @param imageName
   *          name of the image to get information for
   * @return The docker image with this name
   * @throws UnknownDockerImageException
   *           if the image can't be found
   */
  default DockerImageVersion get(String imageName) {
    return get(new DockerImageName(imageName));
  }
}
