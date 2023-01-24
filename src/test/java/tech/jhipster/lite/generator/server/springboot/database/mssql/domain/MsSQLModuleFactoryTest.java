package tech.jhipster.lite.generator.server.springboot.database.mssql.domain;

import static org.mockito.Mockito.*;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.docker.DockerImageVersion;
import tech.jhipster.lite.module.domain.docker.DockerImages;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
@ExtendWith(MockitoExtension.class)
class MsSQLModuleFactoryTest {

  @Mock
  private DockerImages dockerImages;

  @InjectMocks
  private MsSQLModuleFactory factory;

  @Test
  void shouldCreateModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture
      .propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("com.jhipster.test")
      .projectBaseName("myapp")
      .build();

    when(dockerImages.get("mcr.microsoft.com/mssql/server")).thenReturn(new DockerImageVersion("mcr.microsoft.com/mssql/server", "0.0.0"));

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, pomFile(), integrationTestAnnotation())
      .hasFile("src/main/java/com/jhipster/test/technical/infrastructure/secondary/mssql/DatabaseConfiguration.java")
      .and()
      .hasFile("documentation/mssql.md")
      .and()
      .hasFile("src/test/java/com/jhipster/test/MsSQLTestContainerExtension.java")
      .and()
      .hasFile("src/test/resources/container-license-acceptance.txt")
      .and()
      .hasFile("pom.xml")
      .containing(
        """
              <dependency>
                <groupId>com.microsoft.sqlserver</groupId>
                <artifactId>mssql-jdbc</artifactId>
                <scope>runtime</scope>
              </dependency>
          """
      )
      .containing("<groupId>com.zaxxer</groupId>")
      .containing("<artifactId>HikariCP</artifactId>")
      .containing("<groupId>org.hibernate.orm</groupId>")
      .containing("<artifactId>hibernate-core</artifactId>")
      .containing("<groupId>org.testcontainers</groupId>")
      .containing("<artifactId>mssqlserver</artifactId>")
      .and()
      .hasFile("src/main/resources/config/application.properties")
      .containing("spring.datasource.url=jdbc:sqlserver://localhost:1433;database=myapp;trustServerCertificate=true")
      .containing("spring.datasource.username=SA")
      .containing("spring.datasource.password=yourStrong(!)Password")
      .containing("spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver")
      .containing("spring.datasource.type=com.zaxxer.hikari.HikariDataSource")
      .containing("spring.datasource.hikari.poolName=Hikari")
      .containing("spring.datasource.hikari.auto-commit=false")
      .containing("spring.data.jpa.repositories.bootstrap-mode=deferred")
      .containing("spring.jpa.hibernate.ddl-auto=update")
      .containing("spring.jpa.hibernate.naming.implicit-strategy=org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy")
      .containing("spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy")
      .containing("spring.jpa.open-in-view=false")
      .containing("spring.jpa.properties.hibernate.connection.provider_disables_autocommit=true")
      .containing("spring.jpa.properties.hibernate.criteria.literal_handling_mode=BIND")
      .containing("spring.jpa.properties.hibernate.generate_statistics=false")
      .containing("spring.jpa.properties.hibernate.jdbc.batch_size=25")
      .containing("spring.jpa.properties.hibernate.jdbc.fetch_size=150")
      .containing("spring.jpa.properties.hibernate.jdbc.time_zone=UTC")
      .containing("spring.jpa.properties.hibernate.order_inserts=true")
      .containing("spring.jpa.properties.hibernate.order_updates=true")
      .containing("spring.jpa.properties.hibernate.query.fail_on_pagination_over_collection_fetch=true")
      .containing("spring.jpa.properties.hibernate.query.in_clause_parameter_padding=true")
      .and()
      .hasFile("src/test/resources/config/application.properties")
      .containing("spring.datasource.url=jdbc:tc:sqlserver")
      .containing("spring.datasource.username=SA")
      .containing("spring.datasource.password=yourStrong(!)Password")
      .containing("spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver")
      .containing("spring.datasource.hikari.maximum-pool-size=2")
      .containing("spring.jpa.open-in-view=false")
      .containing("spring.jpa.properties.hibernate.connection.provider_disables_autocommit=true")
      .containing("spring.datasource.hikari.auto-commit=false");
  }

  private ModuleFile integrationTestAnnotation() {
    return file("src/test/resources/projects/files/IntegrationTest.java", "src/test/java/com/jhipster/test/IntegrationTest.java");
  }
}
