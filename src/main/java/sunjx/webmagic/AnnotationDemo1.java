package sunjx.webmagic;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @Auther: sunjx
 * @Date: 2019/2/21 0021 16:18
 * @Description: 注解的方式获取列表数据
 */

/**
 * 详情页
 */
@TargetUrl("http://hao.jobbole.com/\\w+/")

/**
 * 列表链接（页码\下一页链接）
 */
@HelpUrl("http://hao.jobbole.com/latest/page/\\w+/")
public class AnnotationDemo1 {

    @ExtractBy("//div[@class='content-box']/div[@class='container']/div[@class='row']/div[@class='col-xs-12 col-sm-8']/div[@class='left-content']" +
            "/article[@class='rpost-entry']/header[@class='rpost-header']/h1[@class='rpost-title']/text()")
    private String title;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000).setRetryTimes(3),
                new ConsolePageModelPipeline(), AnnotationDemo1.class)
                //起始页
                .addUrl("http://hao.jobbole.com/latest/")
                .thread(10)
                .run();
    }
}
