package io.tzk.restful.generator.core.support.conf;

import org.apache.maven.repository.internal.MavenRepositorySystemUtils;
import org.eclipse.aether.DefaultRepositorySystemSession;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.connector.basic.BasicRepositoryConnectorFactory;
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.repository.LocalRepositoryManager;
import org.eclipse.aether.spi.connector.RepositoryConnectorFactory;
import org.eclipse.aether.spi.connector.transport.TransporterFactory;
import org.eclipse.aether.transport.http.HttpTransporterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AetherConf {

    @Bean
    RepositorySystemSession repositorySystem() {
        RepositorySystem repositorySystem = MavenRepositorySystemUtils.newServiceLocator()
                .addService(RepositoryConnectorFactory.class, BasicRepositoryConnectorFactory.class)
                .addService(TransporterFactory.class, HttpTransporterFactory.class)
                .getService(RepositorySystem.class);
        LocalRepository localRepository = new LocalRepository("custom/lib");
        DefaultRepositorySystemSession repositorySystemSession = MavenRepositorySystemUtils.newSession();
        LocalRepositoryManager localRepositoryManager = repositorySystem.newLocalRepositoryManager(repositorySystemSession, localRepository);
        repositorySystemSession.setLocalRepositoryManager(localRepositoryManager);
        return repositorySystemSession;
    }
}
