package dayStatics;

import com.ruoyi.job.service.Impl.AttendRecordDayStaticsServiceImpl;
import com.ruoyi.job.util.DateUtils;
import io.jsonwebtoken.lang.Assert;

public class AttendRecordDayStaticsServiceImplTest {


    public static void main(String[] args) {
        AttendRecordDayStaticsServiceImpl bean = new AttendRecordDayStaticsServiceImpl();
        Assert.isTrue(bean.isWorkMorning(DateUtils.parseDate("2024-12-07 08:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-10 17:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-06","yyyy-MM-dd")));
        Assert.isTrue(bean.isWorkMorning(DateUtils.parseDate("2024-12-07 08:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-10 17:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-11","yyyy-MM-dd")));
        Assert.isTrue(!bean.isWorkMorning(DateUtils.parseDate("2024-12-07 08:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-10 17:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-08","yyyy-MM-dd")));
        Assert.isTrue(!bean.isWorkMorning(DateUtils.parseDate("2024-12-07 08:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-10 17:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-07","yyyy-MM-dd")));
        Assert.isTrue(bean.isWorkMorning(DateUtils.parseDate("2024-12-07 12:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-10 17:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-07","yyyy-MM-dd")));
        Assert.isTrue(!bean.isWorkMorning(DateUtils.parseDate("2024-12-07 12:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-10 12:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-10","yyyy-MM-dd")));
        Assert.isTrue(!bean.isWorkMorning(DateUtils.parseDate("2024-12-07 12:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-10 17:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-10","yyyy-MM-dd")));
        Assert.isTrue(bean.isWorkMorning(DateUtils.parseDate("2024-12-07 08:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-07 17:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-10","yyyy-MM-dd")));
        Assert.isTrue(!bean.isWorkMorning(DateUtils.parseDate("2024-12-07 08:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-07 17:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-07","yyyy-MM-dd")));
        Assert.isTrue(bean.isWorkMorning(DateUtils.parseDate("2024-12-07 12:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-07 17:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-07","yyyy-MM-dd")));

        Assert.isTrue(bean.isWorkAfternoon(DateUtils.parseDate("2024-12-07 08:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-10 17:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-06","yyyy-MM-dd")));
        Assert.isTrue(bean.isWorkAfternoon(DateUtils.parseDate("2024-12-07 08:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-10 17:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-11","yyyy-MM-dd")));
        Assert.isTrue(!bean.isWorkAfternoon(DateUtils.parseDate("2024-12-07 08:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-10 17:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-07","yyyy-MM-dd")));
        Assert.isTrue(!bean.isWorkAfternoon(DateUtils.parseDate("2024-12-07 12:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-10 17:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-07","yyyy-MM-dd")));
        Assert.isTrue(!bean.isWorkAfternoon(DateUtils.parseDate("2024-12-07 12:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-10 17:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-08","yyyy-MM-dd")));
        Assert.isTrue(!bean.isWorkAfternoon(DateUtils.parseDate("2024-12-07 12:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-10 17:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-10","yyyy-MM-dd")));
        Assert.isTrue(bean.isWorkAfternoon(DateUtils.parseDate("2024-12-07 08:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-10 12:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-10","yyyy-MM-dd")));
        Assert.isTrue(bean.isWorkAfternoon(DateUtils.parseDate("2024-12-07 08:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-07 12:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-10","yyyy-MM-dd")));
        Assert.isTrue(bean.isWorkAfternoon(DateUtils.parseDate("2024-12-07 08:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-07 12:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-07","yyyy-MM-dd")));
        Assert.isTrue(!bean.isWorkAfternoon(DateUtils.parseDate("2024-12-07 08:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-07 17:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-07","yyyy-MM-dd")));
        Assert.isTrue(!bean.isWorkAfternoon(DateUtils.parseDate("2024-12-07 14:00:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-07 17:30:00","yyyy-MM-dd HH:mm:ss"),DateUtils.parseDate("2024-12-07","yyyy-MM-dd")));
    }
}
