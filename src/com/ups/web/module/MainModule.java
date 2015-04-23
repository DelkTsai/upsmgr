/**   
 * 
 * 简介
 * 项目名称:  [MGR]
 * 包:        [com.loyal.module]    
 * 类名称:    [MainModule]  
 * 类描述:    [一句话描述该类的功能]
 * 创建人:    [Loyal]   
 * 创建时间:  [2015-3-3 14:53:38]   
 * 修改人:    [Loyal]   
 * 修改时间:  [2015-3-3 14:53:38]   
 * 修改备注:  [说明本次修改内容]  
 * 版本:      [v1.0]   
 *    
 */

//                            _ooOoo_  
//                           o8888888o  
//                           88" . "88  
//                           (| -_- |)  
//                            O\ = /O  
//                        ____/`---'\____  
//                      .   ' \\| |// `.  
//                       / \\||| : |||// \  
//                     / _||||| -:- |||||- \  
//                       | | \\\ - /// | |  
//                     | \_| ''\---/'' | |  
//                      \ .-\__ `-` ___/-. /  
//                   ___`. .' /--.--\ `. . __  
//                ."" '< `.___\_<|>_/___.' >'"".  
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |  
//                 \ \ `-. \_ __\ /__ _/ .-` / /  
//         ======`-.____`-.___\_____/___.-`____.-'======  
//                            `=---='  
//  
//         .............................................  
//                  佛祖镇楼                  BUG辟易  
//          佛曰:  
//                  写字楼里写字间，写字间里程序员；  
//                  程序人员写程序，又拿程序换酒钱。  
//                  酒醒只在网上坐，酒醉还来网下眠；  
//                  酒醉酒醒日复日，网上网下年复年。  
//                  但愿老死电脑间，不愿鞠躬老板前；  
//                  奔驰宝马贵者趣，公交自行程序员。  
//                  别人笑我忒疯癫，我笑自己命太贱；  
//                  不见满街漂亮妹，哪个归得程序员？  
package com.ups.web.module;

import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.ChainBy;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

import com.ups.web.MainSetup;
import com.ups.web.mvc.SessionCheck;

@Modules(scanPackage = true)
@Ok("json")
@Fail("json")
@IocBy(type=ComboIocProvider.class,args={"*js", "ioc/",
    "*anno", "com.ups.web",
    "*tx"})
@ChainBy(args="mvc/mvc-chain.js")
@SetupBy(value=MainSetup.class)
//@Filters(@By(type = SessionCheck.class, args = { "curruser", "/" }))
public class MainModule {

}