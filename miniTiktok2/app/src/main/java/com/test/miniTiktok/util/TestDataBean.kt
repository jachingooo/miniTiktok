package com.test.miniTiktok.util

import com.test.miniTiktok.R
import com.test.miniTiktok.bean.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * 测试数据
 */
object TestDataBean {

    /**
     * 抖音视频测试数据
     * @return ArrayList<ZpBean>
     */
    fun getMovieData():ArrayList<ZpBean>{
        var testList=arrayListOf<ZpBean>()
        testList.add(ZpBean(true, R.mipmap.m1, "日常记录1", "2022-08-08 1:00", Random().nextInt(Random().nextInt(9999)), Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999)))
        testList.add(ZpBean(true, R.mipmap.m1, "日常记录2", "2022-08-08 2:00", Random().nextInt(Random().nextInt(9999)), Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999)))
        testList.add(ZpBean(true, R.mipmap.m1, "日常记录3", "2022-08-08 3:00", Random().nextInt(Random().nextInt(9999)), Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999)))
        testList.add(ZpBean(false, R.mipmap.m1, "日常记录4", "2022-08-08 4:00", Random().nextInt(Random().nextInt(9999)), Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999)))
        testList.add(ZpBean(false, R.mipmap.m1, "日常记录5", "2022-08-08 5:00", Random().nextInt(Random().nextInt(9999)), Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999)))
        testList.add(ZpBean(false, R.mipmap.m1, "日常记录6", "2022-08-08 6:00", Random().nextInt(Random().nextInt(9999)), Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999)))
        testList.add(ZpBean(false, R.mipmap.m1, "日常记录7", "2022-08-08 7:00", Random().nextInt(Random().nextInt(9999)), Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999)))
        testList.add(ZpBean(false, R.mipmap.m1, "日常记录8", "2022-08-08 8:00", Random().nextInt(Random().nextInt(9999)), Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999)))
        testList.add(ZpBean(false, R.mipmap.m1, "日常记录9", "2022-08-08 9:00", Random().nextInt(Random().nextInt(9999)), Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999)))
        testList.add(ZpBean(false, R.mipmap.m1, "日常记录10", "2022-08-08 10:00", Random().nextInt(Random().nextInt(9999)), Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999)))
        testList.add(ZpBean(false, R.mipmap.m1, "日常记录11", "2022-08-08 11:00", Random().nextInt(Random().nextInt(9999)), Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999)))
        testList.add(ZpBean(false, R.mipmap.m1, "日常记录12", "2022-08-08 12:00", Random().nextInt(Random().nextInt(9999)), Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999)))
        testList.add(ZpBean(false, R.mipmap.m1, "日常记录13", "2022-08-08 13:00", Random().nextInt(Random().nextInt(9999)), Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999)))
        testList.add(ZpBean(false, R.mipmap.m1, "日常记录14", "2022-08-08 14:00", Random().nextInt(Random().nextInt(9999)), Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999)))
        testList.add(ZpBean(false, R.mipmap.m1, "日常记录15", "2022-08-08 15:00", Random().nextInt(Random().nextInt(9999)), Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999)))
        testList.add(ZpBean(false, R.mipmap.m1, "日常记录16", "2022-08-08 16:00", Random().nextInt(Random().nextInt(9999)), Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999),Random().nextInt(9999)))
        return testList
    }

    /**
     * 电视剧列表测试数据
     * @return ArrayList<DsjBean>
     */
    fun getDsjData():ArrayList<DsjBean>{
        var testList= arrayListOf<DsjBean>()
        testList.add(DsjBean(R.mipmap.m1,"电视剧名称1","dianshijumingcheng1","爱情","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DsjBean(R.mipmap.m2,"电视剧名称2","dianshijumingcheng2","动作","作者2 / 张三2 / 李四2","2022-08-26","美国","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DsjBean(R.mipmap.m3,"电视剧名称3","dianshijumingcheng3","爱情","作者2 / 张三2 / 李四2","2022-08-26","日本","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DsjBean(R.mipmap.m4,"电视剧名称4","dianshijumingcheng4","爱情","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DsjBean(R.mipmap.m5,"电视剧名称5","dianshijumingcheng5","爱情","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DsjBean(R.mipmap.m6,"电视剧名称6","dianshijumingcheng6","爱情","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DsjBean(R.mipmap.m7,"电视剧名称7","dianshijumingcheng7","爱情","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DsjBean(R.mipmap.m8,"电视剧名称8","dianshijumingcheng8","爱情","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DsjBean(R.mipmap.m9,"电视剧名称9","dianshijumingcheng9","爱情","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DsjBean(R.mipmap.m10,"电视剧名称10","dianshijumingcheng10","爱情","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        return testList
    }

    /**
     * 粉丝测试数据
     * @return ArrayList<FsBean>
     */
    fun getFsData():ArrayList<FsBean>{
        var testList = arrayListOf<FsBean>()
        testList.add(FsBean(R.mipmap.touxiang,"粉丝昵称1","男","中国 上海 浦东"))
        testList.add(FsBean(R.mipmap.touxiang,"粉丝昵称2","男","中国 北京 xx"))
        testList.add(FsBean(R.mipmap.touxiang,"粉丝昵称3","男","中国 广东 广州"))
        testList.add(FsBean(R.mipmap.touxiang,"粉丝昵称4","男","中国 上海 浦东"))
        testList.add(FsBean(R.mipmap.touxiang,"粉丝昵称5","男","中国 上海 浦东"))
        testList.add(FsBean(R.mipmap.touxiang,"粉丝昵称6","男","中国 上海 浦东"))
        testList.add(FsBean(R.mipmap.touxiang,"粉丝昵称7","男","中国 上海 浦东"))
        testList.add(FsBean(R.mipmap.touxiang,"粉丝昵称8","男","中国 上海 浦东"))
        testList.add(FsBean(R.mipmap.touxiang,"粉丝昵称9","男","中国 上海 浦东"))
        testList.add(FsBean(R.mipmap.touxiang,"粉丝昵称10","男","中国 上海 浦东"))
        testList.add(FsBean(R.mipmap.touxiang,"粉丝昵称11","男","中国 上海 浦东"))
        testList.add(FsBean(R.mipmap.touxiang,"粉丝昵称12","男","中国 上海 浦东"))
        testList.add(FsBean(R.mipmap.touxiang,"粉丝昵称13","男","中国 上海 浦东"))
        return testList
    }

    /**
     * 关注测试数据
     * @return ArrayList<FsBean>
     */
    fun getGzData():ArrayList<FsBean>{
        var testList = arrayListOf<FsBean>()
        testList.add(FsBean(R.mipmap.touxiang,"关注昵称1","男","中国 上海 浦东"))
        testList.add(FsBean(R.mipmap.touxiang,"关注昵称2","男","中国 北京 xx"))
        testList.add(FsBean(R.mipmap.touxiang,"关注昵称3","男","中国 广东 广州"))
        testList.add(FsBean(R.mipmap.touxiang,"关注昵称4","男","中国 上海 浦东"))
        testList.add(FsBean(R.mipmap.touxiang,"关注昵称5","男","中国 上海 浦东"))
        testList.add(FsBean(R.mipmap.touxiang,"关注昵称6","男","中国 上海 浦东"))
        testList.add(FsBean(R.mipmap.touxiang,"关注昵称7","男","中国 上海 浦东"))
        testList.add(FsBean(R.mipmap.touxiang,"关注昵称8","男","中国 上海 浦东"))
        testList.add(FsBean(R.mipmap.touxiang,"关注昵称9","男","中国 上海 浦东"))
        testList.add(FsBean(R.mipmap.touxiang,"关注昵称10","男","中国 上海 浦东"))
        testList.add(FsBean(R.mipmap.touxiang,"关注昵称11","男","中国 上海 浦东"))
        testList.add(FsBean(R.mipmap.touxiang,"关注昵称12","男","中国 上海 浦东"))
        testList.add(FsBean(R.mipmap.touxiang,"关注昵称13","男","中国 上海 浦东"))
        return testList
    }

    /**
     * 网络电影测试数据
     * @return ArrayList<DyBean>
     */
    fun getWlData():ArrayList<DyBean>{
        var testList= arrayListOf<DyBean>()
        testList.add(DyBean(R.mipmap.m1,"网络电影名称1","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m2,"网络电影名称2","作者2 / 张三2 / 李四2","2022-08-26","美国","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m3,"网络电影名称3","作者2 / 张三2 / 李四2","2022-08-26","日本","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m4,"网络电影名称4","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m5,"网络电影名称5","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m6,"网络电影名称6","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m7,"网络电影名称7","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m8,"网络电影名称8","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m9,"网络电影名称9","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m10,"网络电影名称10","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        return testList
    }

    /**
     * 院线电影测试数据
     * @return ArrayList<DyBean>
     */
    fun getYxData():ArrayList<DyBean>{
        var testList= arrayListOf<DyBean>()
        testList.add(DyBean(R.mipmap.m1,"院线电影名称1","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m2,"院线电影名称2","作者2 / 张三2 / 李四2","2022-08-26","美国","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m3,"院线电影名称3","作者2 / 张三2 / 李四2","2022-08-26","日本","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m4,"院线电影名称4","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m5,"院线电影名称5","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m6,"院线电影名称6","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m7,"院线电影名称7","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m8,"院线电影名称8","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m9,"院线电影名称9","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m10,"院线电影名称10","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        return testList
    }

    /**
     * 综艺测试数据
     * @return ArrayList<ZyBean>
     */
    fun getZyData():ArrayList<ZyBean>{
        var testList= arrayListOf<ZyBean>()
        testList.add(ZyBean(R.mipmap.m1,"综艺名称1","zongyimingcheng1","作者2","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(ZyBean(R.mipmap.m2,"综艺名称2","zongyimingcheng2","作者2","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(ZyBean(R.mipmap.m3,"综艺名称3","zongyimingcheng3","作者2","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(ZyBean(R.mipmap.m4,"综艺名称4","zongyimingcheng4","作者2","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(ZyBean(R.mipmap.m5,"综艺名称5","zongyimingcheng5","作者2","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(ZyBean(R.mipmap.m6,"综艺名称6","zongyimingcheng6","作者2","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(ZyBean(R.mipmap.m7,"综艺名称7","zongyimingcheng7","作者2","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(ZyBean(R.mipmap.m8,"综艺名称8","zongyimingcheng8","作者2","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(ZyBean(R.mipmap.m9,"综艺名称9","zongyimingcheng9","作者2","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(ZyBean(R.mipmap.m10,"综艺名称10","zongyimingcheng10","作者2","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        return testList
    }
}