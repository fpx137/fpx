
import com.ruoyi.cache.bean.DeptData;
import com.ruoyi.cache.bean.DictData;
import com.ruoyi.cache.utils.CacheDictUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootConfiguration
@SpringBootTest(classes = {SpringBootTest.class})
public class HelloTest {
    @Test
    public void test(){
       String res =  CacheDictUtils.toDictLabel("sys_user_sex",1L);
       System.out.println(res);
    }

    }



