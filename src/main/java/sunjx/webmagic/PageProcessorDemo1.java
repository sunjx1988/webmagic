package sunjx.webmagic;

import org.apache.commons.collections.CollectionUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/2/19 0019 13:46
 * @Description: 从和讯网获取股票基本财报数据
 */
public class PageProcessorDemo1 implements PageProcessor {

    //待爬取网站的配置
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);


    public void process(Page page) {
        Selectable tbody = page.getHtml().$("div#zaiyaocontent table.web2 tbody");
        if(null != tbody){
            Selectable trListSelectable = tbody.$("tr");
            if(null != trListSelectable){
                List<Selectable> trList = trListSelectable.nodes();
                if(!CollectionUtils.isEmpty(trList)){
                    for(Selectable tr: trList){
                        page.putField(tr.$("td.dotborder div.tishi").xpath("//strong/text()").toString(), tr.$("td:eq(1)").xpath("//div[@class='tishi']/text()").toString());
                    }
                }
            }
        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new PageProcessorDemo1())
                .addUrl("http://stockdata.stock.hexun.com/2008/zxcwzb.aspx?stockid=000563&accountdate=2018.09.30")
                .thread(10)
                //将结果保存
                .addPipeline(new JsonFilePipeline("C:\\Users\\Administrator\\Desktop\\test\\"))
                .run();
    }
}
