import fr.epita.di.ApplicationConfiguration;
import fr.epita.di.IService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestSpringFramework {

    @Autowired
    IService service;

    @Test
    public void test(){
        System.out.println(service.getClass());
    }


}
