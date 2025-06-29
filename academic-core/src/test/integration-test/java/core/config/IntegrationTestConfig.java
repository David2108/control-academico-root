@Configuration
@ComponentScan(basePackages = {
        "com.web.app.controlacademico.academic.core.service",
        "com.web.app.controlacademico.academic.core.repository"
})
@EntityScan(basePackages = "com.web.app.controlacademico.academic.core.entity")
@EnableJpaRepositories(basePackages = "com.web.app.controlacademico.academic.core.repository")
@EnableTransactionManagement
public class IntegrationTestConfig {

}