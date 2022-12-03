package tacos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/*no es necesariocrear un nuevo configuration class, se podrìa agregar directamente al TacoCloudApplication, o a
* cualquier clase con anotación @Configuration. Sin embargo, por orden lo haré de esta manera
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {



    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //WebMvcConfigurer.super.addViewControllers(registry);
        registry.addViewController("/").setViewName("home");
    }
}
