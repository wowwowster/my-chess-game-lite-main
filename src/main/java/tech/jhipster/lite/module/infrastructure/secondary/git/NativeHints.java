package tech.jhipster.lite.module.infrastructure.secondary.git;

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.internal.*;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.util.sha1.SHA1;
import org.springframework.aot.hint.*;
import tech.jhipster.lite.common.domain.*;

@ExcludeFromGeneratedCodeCoverage(reason = "Not testing native runtime hints")
class NativeHints implements RuntimeHintsRegistrar {

  @Override
  public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
    /*
    Mimics quarkus native configuration. See https://github.com/quarkiverse/quarkus-jgit/blob/main/deployment/src/main/java/io/quarkus/jgit/deployment/JGitProcessor.java
    Their calls to
    ReflectiveClassBuildItem(true, true,
                "org.eclipse.jgit.api.MergeCommand$FastForwardMode",

    means methods, fields and constructors
     */
    hints
      .reflection()
      .registerType(MergeCommand.FastForwardMode.class, MemberCategory.values())
      .registerType(MergeCommand.FastForwardMode.Merge.class, MemberCategory.values())
      .registerType(JGitText.class, MemberCategory.values())
      .registerType(SHA1.Sha1Implementation.class, MemberCategory.values())
      .registerType(CoreConfig.AutoCRLF.class, MemberCategory.values())
      .registerType(CoreConfig.CheckStat.class, MemberCategory.values())
      .registerType(CoreConfig.EOL.class, MemberCategory.values())
      .registerType(CoreConfig.EolStreamType.class, MemberCategory.values())
      .registerType(CoreConfig.HideDotFiles.class, MemberCategory.values())
      .registerType(CoreConfig.SymLinks.class, MemberCategory.values())
      .registerType(CoreConfig.LogRefUpdates.class, MemberCategory.values());

    // patterns for JGit and our own template files
    hints.resources().registerPattern("org.eclipse.jgit.internal.JGitText").registerPattern("generator/**");
  }
}
