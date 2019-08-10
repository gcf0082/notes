设置获取全局变量
org.zaproxy.zap.extension.script.ScriptVars.setGlobalVar("gcf", "xx2");
org.zaproxy.zap.extension.script.ScriptVars.getGlobalVar


function sendingRequest(msg, initiator, helper) {
// Debugging can be done using println like this
//println(msg.getRequestHeader().getURI().toString());
var HttpMessage = Java.type("org.parosproxy.paros.network.HttpMessage");
var URI = Java.type("org.apache.commons.httpclient.URI");
msg2 = msg.cloneAll();
msg2.setRequestBody("yyyy");
uri = new URI("http://10.67.252.200/yyy");
msg3 = new HttpMessage(uri);
//org.parosproxy.paros.network.HttpMessage msg3 = null;

helper.getHttpSender().sendAndReceive(msg3, false);
}

Stand alone脚本需要手工点击Run按钮

可以拷贝只剪贴板
string += "'"+msg.getRequestHeader().getURI().toString()+"'";
selected = new java.awt.datatransfer.StringSelection(string);
clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
clipboard.setContents(selected,null);

实现了Tab接口就有类似断点一样的页签
public class BreakPanel extends AbstractPanel implements Tab

打开url
org.zaproxy.zap.utils.DesktopUtils.openUrlInBrowser(
"http://www.cvedetails.com/google-search-results.php?q=" + encodeURIComponent(header) + 
"&sa=Search");
}

脚本弹框方法
org.parosproxy.paros.view.View.getSingleton().showMessageDialog("xxx")

Output框添加内容
org.parosproxy.paros.view.View.getSingleton().getOutputPanel().append(contents)

插件名字格式要求，例子：
simple-release-1.zap

zap底层发送请求的函数
org.parosproxy.paros.network.HttpSender.runMethod(HttpMessage, boolean)


proxy发送请求回调接口
org.parosproxy.paros.core.proxy.ConnectRequestProxyListener.receivedConnectRequest


zap自带的插件在
org.zaproxy.zap.extension 包下和plugin目录下

通过如下接口可获取指定的插件实例
org.parosproxy.paros.control.Control.getSingleton().getExtensionLoader().getExtension("ExtensionHistory")

org.zaproxy.zap.ZAP.main
org.zaproxy.zap.ZAP.runCommandLine
org.parosproxy.paros.control.Control.initSingletonWithoutView
org.parosproxy.paros.control.Control.init
org.parosproxy.paros.control.Proxy.startServer
org.parosproxy.paros.core.proxy.ProxyServer.startServer
org.parosproxy.paros.core.proxy.ProxyServer.run
org.parosproxy.paros.core.proxy.ProxyThread.start