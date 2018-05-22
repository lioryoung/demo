package com.util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016091100487958";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCt0O93ibg3mTQJthElgjvKGboBIPWLWbUoi6AP6IBSYYlZ/TUHN3aLsXpkBPzw0qn1Vfw7ygoLeeimd5wihNMzTB2NG270J2qY2prWlWSD7GNaBTqKEZGIKTqKLYuen6ZeZyvOcoRJyrfEYnNO9ia6fA/UpsdoD9BH+8vdXNc4UTibikDecPdz9XwAqK81UhV8NAo7uVXBoOUpaWbWnTaB0KVOty24mxbPRxZ4tnSDIF3ej7lCFrV64AwCECFw/uId1H5NkNOlPL/FBkGVmhAADWMeyxndcmsRRwLCpzu3wPBnpML8xJH9C4iYahstDYmZArKxMgO1yQw6it2KjjNJAgMBAAECggEACMzJMu4gC4CQJ+W13IZJbKP8zM3UYKe8Cj93bRbCWGljwNCW/LSMJepoXeP6wl6ICEnAKW7DL22On87ycrTy1GgIHvrClU3hkbePm0mY+yzeotIc8pyNF62urww7Q16l16W1jBA0LmA7CZBKcjb4Ivzg+E0xT01tqBdRbPWkV89/eQ57P5KTaTyhLfdF2F8abfFu7i5pfei5VMzNu0Tn2Fuvu7OHVZhSB4zVdstgRKx6jvW1cZx8kd0XotZbhq5F9gV0FEIRPqIyllze/EtojU47YoIgdPA+iTng8EzSdsMPY3oi7oDGJJ7AJyD7b4+mthNMyIJIDnigBrubdUYsOQKBgQDjNeAaRlB99+oq7i+HpaTUunx8OAmTG5nWV9WW8LS/80fIaaltrsmaFAiCyFIXk1M41w3/kLZMYcQU02bVxK7Qg08meT7CYYX7Zr1hdZc4VuIEG1b/lkgZqg9tIq340ku27fZyC+zKsdP8G3/SRjPQ+wZ+ySvWJzzmTcAha2HfVwKBgQDD1xX9Y6zSEqW/qpZ87AkBaLDaHm7SNYThBn7I8tIoEeNviUvbazbl048MY1OdlrT6LKy/JWxHRvcfxWwpLpDUUeLY2spQKzjBjCgOyn45l6wDJFv0stHI4T6EBRCoT1nmakldsvzDVBkXEUYOONwDyX/qxzpyAycw7jWApPz+XwKBgE1MTumhclDovunMDq9/UwxsRV2dUuSRR0z8pNWtHlEPWyR7jgCarkuU9TBJJmSO4ok74K9JdDuYJzVOlnWzbCXUBmF4bjmDJCnbUiKN9txb8pijFH3viqfwiz79xWIZaVjarDUwiWaMCSqD0IChEY/PVu5bUmaRAs73NJ4qAioRAoGAKZTSg3GVcSkqbi6vMyj+pIDxA+7wQsvAQfMaGlqMSpu5uaXiKF10U4n+1neEenJUTMYNi6xLeDrWH7XQSFItyBb23mpeCHWU4Gt82d8eu+W7TRN8PtF0/3zrxCY7KjFm+ihZGX72cha6GQ6+3CgwdtFiypuwiiKnQJljXMQrP2sCgYAMe0ijBRiVTIgdBBySFbjmHlmfh5MYGLZhGmRsQJ+HfRRM+oE5bWCsUpuBgOPlwPwXFiLjn8Yv4IQmU5NChJyXCH1Z0ORpLLmPPIMIz2gzXGbB5zChjYOe3iydTJ5+Dsoq8ddE+HjSoyrPNChq3ereYCQOAAH0ZkGQ0IIBfpV7bA==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3Jun29M2+c6Qf8q/LXFAj49sOcaHIobz3Rj6JTGKK57WP3/QlHizSngicaG+GywFniVjyo2oz6ezrTZdMzL91/h+1qTnCpyGRa31vzJS5HHlaUtNFMnPLeqpSq1/QkftKsxWruCap4yuiCOXVQHJ2rVi3H89gZevKyMP1PvTVNFX6zEKQ/WoisKWp6hHB4dSr/JieOja9NRoLz2kAv4y9jzBwd7xjet4yMs+xCUhyFsgSVKBeuzDwClTD4PXRf+xY4QSRh6UBFOd5OgOnIc8bf+MFddk3C3Lthvww9ztvxaFn0wvss8NfhjTAXSnd2iPtVdpO144mBKIkX88MA0sLwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/returnurl";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

